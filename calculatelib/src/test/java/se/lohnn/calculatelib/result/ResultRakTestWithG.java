package se.lohnn.calculatelib.result;


import org.junit.Before;
import org.junit.Test;

import se.lohnn.calculatelib.settings.Banksettings;
import se.lohnn.calculatelib.settings.LoanSettings;

import static org.junit.Assert.assertEquals;

/**
 * Copyright (C) lohnn on 2016.
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
public class ResultRakTestWithG {
    private ResultRak resultRak;
    private LoanSettings loanSettings;

    @Before
    public void setUp() throws Exception {
        Banksettings banksettings = Banksettings.standard_med_säkerhet;
        //TODO: Skattejämkning av
        loanSettings = new LoanSettings(50000000, 1000000, 20, true, true, true);
        resultRak = new ResultRak(banksettings, loanSettings);
    }

    @Test
    public void testMånadsbetalning() throws Exception {
        assertEquals(5918, resultRak.månadsbetalning().calculate(loanSettings.getFirstMonth()), 0.5);
        assertEquals(5918, resultRak.månadsbetalning().calculate(loanSettings.getLastMonth()), 0.5);
    }

    @Test
    public void testAmortering() throws Exception {
        assertEquals(4167, resultRak.amortering(), 0.5);
    }

    @Test
    public void testEftersparande() throws Exception {
        assertEquals(1, resultRak.eftersparande().calculate(loanSettings.getFirstMonth()), 0.5);
        assertEquals(1744, resultRak.eftersparande().calculate(loanSettings.getLastMonth()), 0.5);
    }

    @Test
    public void testLånekostnad() throws Exception {
        assertEquals(2500, resultRak.lånekostnad().calculate(loanSettings.getFirstMonth()), 0.5);
        assertEquals(10, resultRak.lånekostnad().calculate(loanSettings.getLastMonth()), 0.5);
    }

    @Test
    public void testSkatteavdrag() throws Exception {
        assertEquals(-750, resultRak.skatteavdrag().calculate(loanSettings.getFirstMonth()), 0.5);
        assertEquals(-3, resultRak.skatteavdrag().calculate(loanSettings.getLastMonth()), 0.5);
    }

    @Test
    public void testSparbeloppKvar() throws Exception {
        assertEquals(209413, resultRak.sparbeloppKvar(), 0.5);
    }

    @Test
    public void testSparpoängKvar() throws Exception {
        assertEquals(10804133, resultRak.sparpoängKvar(), 0.5);
    }

    @Test
    public void testFörsparpoängOmräknad() throws Exception {
        assertEquals(77586207, resultRak.försparpoängOmräknad(), 0.5);
    }

    @Test
    public void testAckumuleradeEftersparPoäng() throws Exception {
        assertEquals(16834383, resultRak.ackumuleradeEftersparPoäng(), 0.5);
    }

    @Test
    public void testPoängförbrukning() throws Exception {
        assertEquals(120500000, resultRak.poängförbrukning(), 0.5);
    }

    @Test
    public void testLåneinsats() throws Exception {
        assertEquals(60000, resultRak.låneinsats(), 0.5);
    }

    @Test
    public void testLånekostnadTotal() throws Exception {
        assertEquals(301250, resultRak.lånekostnadTotal(), 0.5);
    }

    @Test
    public void testSkatteavdragTotal() throws Exception {
        assertEquals(-90375, resultRak.skatteavdragTotal(), 0.5);
    }

    @Test
    public void testÅrligSkatteåterbäring() throws Exception {

    }

    @Test
    public void testEffektivRänta() throws Exception {
        //TODO
    }
}