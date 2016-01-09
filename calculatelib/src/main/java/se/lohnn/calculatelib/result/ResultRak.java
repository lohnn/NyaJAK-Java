package se.lohnn.calculatelib.result;

import java.util.ArrayList;

import se.lohnn.calculatelib.model.CalculationInterface;
import se.lohnn.calculatelib.model.FirstLast;
import se.lohnn.calculatelib.settings.Banksettings;
import se.lohnn.calculatelib.settings.LoanSettings;

/**
 * Copyright (C) lohnn on 2015.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class ResultRak extends Result {
    public ResultRak(Banksettings banksettings, LoanSettings loanSettings) {
        super(banksettings, loanSettings);
    }

    @Override
    public CalculationInterface månadsbetalning() {
        return i -> eftersparande().calculate(i) + amortering() + lånekostnad().calculate(i) * skatteavdragVärde();
    }

    @Override
    public double amortering() {
        return (loanSettings.getLånebelopp() / (loanSettings.getLastMonth()));
    }

    protected double nyttEftersparkrav() {
        return Math.max(0, ((poängförbrukning() * (eftersparprocent() / 100)) - försparpoängOmräknad()));
    }

    protected double genomsnittligtEftersparande() {
        return ((nyttEftersparkrav() / (loanSettings.getLastMonth())) / ((loanSettings.getLastMonth()) + 1)) * 2;
    }

    protected double skatteavdragVärde() {
        return loanSettings.isSkattejämkning() ? 0.7 : 1;
    }

    @Override
    public CalculationInterface lånekostnad() {
        return i -> bankSettings.getLånekostnad() * (kvarILån().calculate(i - 1));
    }

    @Override
    public CalculationInterface skatteavdrag() {
        return i -> -0.3 * lånekostnad().calculate(i);
    }

    @Override
    public double sparbeloppKvar() {
        return ((eftersparande().calculate(loanSettings.getFirstMonth()) + eftersparande().calculate(loanSettings.getLastMonth())) / 2) *
                (loanSettings.getLastMonth());
    }

    @Override
    public double sparpoängKvar() {
        double sparpoängKvar = 0;
        try { //Make sure we catch divide by zero exception
            sparpoängKvar = Math.max(
                    0,
                    (försparpoängOmräknad() - (poängförbrukning() * (eftersparprocent() / 100)) + ackumuleradeEftersparPoäng()) * (loanSettings.getFörsparpoäng() / försparpoängOmräknad()));
        } catch (ArithmeticException ignored) {
        }
        return sparpoängKvar;
    }

    protected double eftersparprocent() {
        double minstaSparprocent = 100 / (bankSettings.getOptimal_ukvot() / bankSettings.getAktuell_ukvot());
        return bankSettings.getTurboEffekt() * Math.max(minstaSparprocent,
                ((bankSettings.bestAmorteringstid() * minstaSparprocent) + ((loanSettings.getAmorteringstid() - bankSettings.bestAmorteringstid()) * 100)) / loanSettings.getAmorteringstid());
    }

    @Override
    public double försparpoängOmräknad() {
        return loanSettings.getFörsparpoäng() * (1 / (bankSettings.getAktuell_ukvot() / bankSettings.getOptimal_ukvot()));
    }

    public CalculationInterface kvarILån() {
        return i -> loanSettings.getLånebelopp() - amortering() * i;

    }

    @Override
    public CalculationInterface eftersparande() {
        CalculationInterface eftersparande = i -> {
            double tempLånekostnad = (skatteavdragVärde() * bankSettings.getLånekostnad() * kvarILån().calculate(i - 1));
            return (genomsnittligtEftersparande() / 2) + (lånekostnad().calculate(loanSettings.getFirstMonth()) * skatteavdragVärde() - tempLånekostnad);
        };
        ArrayList<Double> postSavingsSums = beräknaAckumulation(eftersparande, (int) loanSettings.getLastMonth());
        ArrayList<Double> ackumuleradePoäng = beräknaAckumulation(postSavingsSums);


        //Utifall låneinställningar är inställda på att jämka, så multiplicerar vi värdet med 0.7
        CalculationInterface lånekostnad = i -> lånekostnad().calculate(i) * skatteavdragVärde();


        CalculationInterface calculationInterface = i -> ((genomsnittligtEftersparande() / 2)
                + (lånekostnad.calculate(loanSettings.getFirstMonth()) - lånekostnad.calculate(i)) +
                ((2 * (nyttEftersparkrav() - ackumuleradePoäng.get(ackumuleradePoäng.size() - 1))) / ((loanSettings.getLastMonth() + 1) * loanSettings.getLastMonth())));

        //Använd I
        if (calculationInterface.calculate(loanSettings.getFirstMonth()) < 0) {
            calculationInterface = i -> (genomsnittligtEftersparande() / 2) +
                    (lånekostnad.calculate(loanSettings.getFirstMonth()) - lånekostnad.calculate(i));
        }

        return calculationInterface;
    }

    @Override
    public double ackumuleradeEftersparPoäng() {
        //G
        ArrayList<Double> postSavingsSums = beräknaAckumulation(eftersparande(), (int) loanSettings.getLastMonth());
        ArrayList<Double> ackumuleradePoäng = beräknaAckumulation(postSavingsSums);
        return ackumuleradePoäng.get(ackumuleradePoäng.size() - 1);
    }

    protected ArrayList<Double> beräknaAckumulation(CalculationInterface calc, int times) {
        ArrayList<Double> toReturn = new ArrayList<>();
        double ackVal = 0;
        for (int i = 1; i <= times; i++) {
            toReturn.add(ackVal += calc.calculate(i));
        }
        return toReturn;
    }

    protected ArrayList<Double> beräknaAckumulation(ArrayList<Double> calc) {
        ArrayList<Double> toReturn = new ArrayList<>();
        double ackVal = 0;
        for (double d : calc) {
            toReturn.add(ackVal += d);
        }
        return toReturn;
    }

    @Override
    public double poängförbrukning() {
        return ((amortering() / 2 * ((loanSettings.getLastMonth()) + 1)) * (loanSettings.getLastMonth()));
    }

    @Override
    public double låneinsats() {
        return loanSettings.getLånebelopp() * bankSettings.getLåneinsats();
    }

    @Override
    public double lånekostnadTotal() {
        return (lånekostnad().calculate(loanSettings.getFirstMonth()) + lånekostnad().calculate(loanSettings.getLastMonth()))
                / 2 * (loanSettings.getLastMonth());
    }

    @Override
    public double skatteavdragTotal() {
        return lånekostnadTotal() * -0.3;
    }

    @Override
    public FirstLast årligSkatteåterbäring() {
        return new FirstLast(getSumFromTime(0, 11),
                getSumFromTime(loanSettings.getLastMonth() - 12, loanSettings.getLastMonth() - 1));
    }

    @Override
    public double effektivRänta() {
        //TODO
        return 0;
    }

    protected double getSumFromTime(double firstMonth, double lastMonth) {
        double skatteavdrag = 0.3;
        double tempAmount1 = kvarILån().calculate(firstMonth) * skatteavdrag;
        double tempAmount2 = kvarILån().calculate(lastMonth) * skatteavdrag;
        return (tempAmount1 + tempAmount2) * ((firstMonth - lastMonth) / 2);
    }
}
