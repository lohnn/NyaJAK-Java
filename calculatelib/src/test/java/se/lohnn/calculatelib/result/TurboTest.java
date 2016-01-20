package se.lohnn.calculatelib.result;

import org.junit.Before;
import org.junit.Test;

import se.lohnn.calculatelib.settings.Banksettings;
import se.lohnn.calculatelib.settings.LoanSettings;

import static junit.framework.Assert.assertEquals;

/**
 * Created by lohnn on 2016-01-17.
 * © Infomaker Scandinavia AB
 */
public class TurboTest {
    private ResultRak resultRak;
    private Banksettings banksettings;
    private LoanSettings loanSettings;

    @Before
    public void setUp() throws Exception {
        banksettings = Banksettings.getStandardMedSäkerhet();
        loanSettings = new LoanSettings(5000000, 1000000, 20, true, true, true);
        resultRak = new ResultRak(banksettings, loanSettings);
        banksettings.setTurbo(5);
    }

    @Test
    public void testOptimalAmorteringstidTurbo() throws Exception {
        assertEquals(4.7, banksettings.bestAmorteringstid(), 0.05);
    }

    @Test
    public void testSparpoängKvarTurbo() throws Exception {
        assertEquals(7919323, resultRak.sparpoängKvar(), 0.5);
    }

    @Test
    public void testeftersparandeTurbo() throws Exception {
        banksettings.setTurbo(3);
        assertEquals(96, resultRak.eftersparande().calculate(loanSettings.getFirstMonth()), 0.5);
        assertEquals(1839, resultRak.eftersparande().calculate(loanSettings.getLastMonth()), 0.5);
    }

    @Test
    public void testSparbeloppKvarTurbo() throws Exception {
        banksettings.setTurbo(3);
        assertEquals(232263, resultRak.sparbeloppKvar(), 0.5);
    }
}
