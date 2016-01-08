package se.lohnn.calculatelib.result;

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
public class ResultFallande extends ResultRak {
    public ResultFallande(Banksettings banksettings, LoanSettings loanSettings) {
        super(banksettings, loanSettings);
    }

    @Override
    public CalculationInterface eftersparande() {
//        double eftersparPerMånad = ((nyttEftersparkrav() / (loanSettings.getAmorteringstid() * 12)) / ((loanSettings.getAmorteringstid() * 12) + 1)) * 2;
//        return new FirstLast(eftersparPerMånad, eftersparPerMånad);
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
