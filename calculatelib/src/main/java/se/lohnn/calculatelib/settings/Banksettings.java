package se.lohnn.calculatelib.settings;

import se.lohnn.calculatelib.model.FirstLast;

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
public class Banksettings {
    private FirstLast låneblopp;
    private FirstLast amorteringstid;
    private double lånekostnad;
    private double låneinsats;
    private double aktuell_ukvot;
    private double optimal_ukvot;
    private double turbo;

    public static Banksettings standard_med_säkerhet = new Banksettings(
            new FirstLast(20000, 6000000),
            new FirstLast(2, 50),
            3,
            6,
            0.58,
            0.9);
    public static Banksettings standard_utan_säkerhet = new Banksettings(new FirstLast(20000, 200000),
            new FirstLast(2, 10),
            4.5,
            6,
            0.58,
            0.9);

    public Banksettings(FirstLast laneblopp, FirstLast amorteringstid, double lanekostnad, double laneinsats, double aktuell_ukvot, double optimal_ukvot) {
        this.låneblopp = laneblopp;
        this.amorteringstid = amorteringstid;
        this.lånekostnad = lanekostnad;
        this.låneinsats = laneinsats;
        this.aktuell_ukvot = aktuell_ukvot;
        this.optimal_ukvot = optimal_ukvot;
    }

    /**
     * Beräknar den längsta amorteringstid du kan välja med lägsta eftersparande
     *
     * @return
     */
    public double bestAmorteringstid() {
        double bestAmortering = ((amorteringstid.getLast() - amorteringstid.getFirst()) /
                optimal_ukvot) * getTurboEffekt() * aktuell_ukvot + amorteringstid.getFirst();
        return (amorteringstid.getLast() < bestAmortering) ? amorteringstid.getLast() : bestAmortering;
    }

    public FirstLast getLåneblopp() {
        return låneblopp;
    }

    public void setLåneblopp(FirstLast låneblopp) {
        this.låneblopp = låneblopp;
    }

    public FirstLast getAmorteringstid() {
        return amorteringstid;
    }

    public void setAmorteringstid(FirstLast amorteringstid) {
        this.amorteringstid = amorteringstid;
    }

    public double getLånekostnad() {
        return lånekostnad / 100 / 12;
    }

    public void setLånekostnad(float lånekostnad) {
        this.lånekostnad = lånekostnad;
    }

    /**
     * Låneinsats i procent (0.5 = 50%)
     *
     * @return
     */
    public double getLåneinsats() {
        return låneinsats / 100;
    }

    public void setLåneinsats(float låneinsats) {
        this.låneinsats = låneinsats;
    }

    public double getAktuell_ukvot() {
        return aktuell_ukvot;
    }

    public void setAktuell_ukvot(float aktuell_ukvot) {
        this.aktuell_ukvot = aktuell_ukvot;
    }

    public double getOptimal_ukvot() {
        return optimal_ukvot;
    }

    public void setOptimal_ukvot(float optimal_ukvot) {
        this.optimal_ukvot = optimal_ukvot;
    }

    private double getTurbo() {
        return turbo;
    }

    public double getTurboEffekt(){
        return Math.pow(getAktuell_ukvot() / getOptimal_ukvot(), this.getTurbo());
    }

    public void setTurbo(double turbo) {
        this.turbo = turbo;
    }
}
