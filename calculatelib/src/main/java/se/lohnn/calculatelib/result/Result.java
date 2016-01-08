package se.lohnn.calculatelib.result;

import java.util.ArrayList;

import se.lohnn.calculatelib.model.CalculationInterface;
import se.lohnn.calculatelib.model.FirstLast;
import se.lohnn.calculatelib.settings.Banksettings;
import se.lohnn.calculatelib.settings.LoanSettings;

/**
 * Copyright (C) lohnn on 2015.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public abstract class Result {
    protected Banksettings bankSettings;
    protected LoanSettings loanSettings;

    public Result(Banksettings banksettings, LoanSettings loanSettings) {
        this.bankSettings = banksettings;
        this.loanSettings = loanSettings;
    }

    public abstract CalculationInterface månadsbetalning();

    public abstract double amortering();

    public abstract CalculationInterface eftersparande();

    public abstract CalculationInterface lånekostnad();

    public abstract CalculationInterface skatteavdrag();

    abstract double försparpoängOmräknad();

    abstract double ackumuleradeEftersparPoäng();

    public abstract double poängförbrukning();

    public abstract double sparbeloppKvar();

    public abstract double sparpoängKvar();

    public abstract double låneinsats();

    public abstract double lånekostnadTotal();

    public abstract double skatteavdragTotal();

    public abstract FirstLast årligSkatteåterbäring();

    public abstract double effektivRänta();
}
