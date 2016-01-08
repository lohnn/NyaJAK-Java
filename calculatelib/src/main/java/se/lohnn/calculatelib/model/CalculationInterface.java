package se.lohnn.calculatelib.model;

/**
 * Copyright (C) lohnn on 2016.
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
public interface CalculationInterface {
    /**
     * Används för att beräkna ett värde dynamiskt utifrån valfri månad
     *
     * @param i
     * @return
     */
    double calculate(double i);

    //TODO: Check if exceeding last month = throw error
}
