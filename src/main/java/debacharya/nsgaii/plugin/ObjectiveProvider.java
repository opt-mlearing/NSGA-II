/*
 * MIT License
 *
 * Copyright (c) 2019 Debabrata Acharya
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package debacharya.nsgaii.plugin;

import debacharya.nsgaii.objectivefunction.AbstractObjectiveFunction;
import debacharya.nsgaii.objectivefunction.SCH_1;
import debacharya.nsgaii.objectivefunction.SCH_2;
import debacharya.nsgaii.plugin.fitness.FitnessCalculatorProvider;

import java.util.ArrayList;
import java.util.List;

public class ObjectiveProvider {

    public static List<AbstractObjectiveFunction> provideSCHObjectives(int chromosomeLength) {

        return ObjectiveProvider.provideSCHObjectives(

                FitnessCalculatorProvider.normalizedGeneticCodeValue(
                        0,
                        Math.pow(2, chromosomeLength) - 1,
                        0,
                        2)

        );

    }

    private static List<AbstractObjectiveFunction> provideSCHObjectives(FitnessCalculator fitnessCalculator) {

        List<AbstractObjectiveFunction> objectives = new ArrayList<>();
        objectives.add(new SCH_1(fitnessCalculator));
        objectives.add(new SCH_2(fitnessCalculator));
        return objectives;

    }

}
