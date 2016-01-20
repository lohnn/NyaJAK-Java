package se.lohnn.calculatelib.settings;

import org.junit.Before;
import org.junit.Test;

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
public class BanksettingsMedSäkerhetTest {
    Banksettings banksettings;

    @Before
    public void setUp() throws Exception {
        banksettings = Banksettings.getStandardMedSäkerhet();
    }

    @Test
    public void testBestAmorteringstid() throws Exception {
        assertEquals(26.5, banksettings.bestAmorteringstid(), 0.1);
    }

    @Test
    public void testGetTurboEffekt() throws Exception {
        assertEquals(1, banksettings.getTurboEffekt(), 0.01);

        banksettings.setTurbo(1);
        assertEquals(0.64, banksettings.getTurboEffekt(), 0.01);

        banksettings.setTurbo(2);
        assertEquals(0.42, banksettings.getTurboEffekt(), 0.01);

        banksettings.setTurbo(3);
        assertEquals(0.27, banksettings.getTurboEffekt(), 0.01);

        banksettings.setTurbo(4);
        assertEquals(0.17, banksettings.getTurboEffekt(), 0.01);

        banksettings.setTurbo(5);
        assertEquals(0.11, banksettings.getTurboEffekt(), 0.01);

        banksettings.setTurbo(10);
        assertEquals(0.01, banksettings.getTurboEffekt(), 0.005);
    }
}