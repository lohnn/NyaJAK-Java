package se.lohnn.calculatelib.settings;

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
public class LoanSettings {
    private double försparpoäng;
    private double lånebelopp;
    private double amorteringstid;
    private boolean säkerhet;
    private boolean skattejämkning;
    private boolean rakBetalning;

    /**
     *
     * @param försparpoäng Låntagarens totala försparade poäng vid lånetillfället
     * @param lånebelopp Beloppen låntagaren vill låna
     * @param amorteringstid Hur lång tid att betala tillbaka lånet på
     * @param säkerhet Om det är ett lån med säkerhet
     * @param skattejämkning Om lånet ska skattejämkas
     * @param rakBetalning Om lånet ska använda rak månadsbetalning
     */
    public LoanSettings(float försparpoäng, float lånebelopp, float amorteringstid, boolean säkerhet, boolean skattejämkning, boolean rakBetalning) {
        this.försparpoäng = försparpoäng;
        this.lånebelopp = lånebelopp;
        this.amorteringstid = amorteringstid;
        this.säkerhet = säkerhet;
        this.skattejämkning = skattejämkning;
        this.rakBetalning = rakBetalning;
    }

    public double getFörsparpoäng() {
        return försparpoäng;
    }

    public void setFörsparpoäng(float försparpoäng) {
        this.försparpoäng = försparpoäng;
    }

    public double getLånebelopp() {
        return lånebelopp;
    }

    public void setLånebelopp(float lånebelopp) {
        this.lånebelopp = lånebelopp;
    }

    /**
     * Amorteringstid i år
     */
    public double getAmorteringstid() {
        return amorteringstid;
    }

    public double getFirstMonth() {
        return 1;
    }

    public double getLastMonth() {
        return amorteringstid * 12;
    }

    public void setAmorteringstid(float amorteringstid) {
        this.amorteringstid = amorteringstid;
    }

    public boolean isSäkerhet() {
        return säkerhet;
    }

    public void setSäkerhet(boolean säkerhet) {
        this.säkerhet = säkerhet;
    }

    public boolean isSkattejämkning() {
        return skattejämkning;
    }

    public void setSkattejämkning(boolean skattejämkning) {
        this.skattejämkning = skattejämkning;
    }

    public boolean isRakBetalning() {
        return rakBetalning;
    }

    public void setRakBetalning(boolean rakBetalning) {
        this.rakBetalning = rakBetalning;
    }
}
