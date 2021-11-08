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

package debacharya.nsgaii.plugin.fitness;

import debacharya.nsgaii.Service;
import debacharya.nsgaii.datastructure.BooleanAllele;
import debacharya.nsgaii.plugin.FitnessCalculator;

import java.util.stream.Collectors;

/**
 * 染色体适应度计算器.
 */
public class FitnessCalculatorProvider {

    private static final String NON_BOOLEAN_ALLELE_UNSUPPORTED =
            "FitnessCalculator.normalizedGeneticCodeValue does not work with AbstractAllele type " +
                    " other than BooleanAllele. If you are implementing a different type of AbstractAllele object " +
                    " use your own implementation of FitnessCalculator.";

    public static FitnessCalculator normalizedGeneticCodeValue(double actualMin, double actualMax,
                                                               double normalizedMin, double normalizedMax) {

        return chromosome -> {

            // 异常校验，必须基因类型为BooleanAllele.
            if (!(chromosome.getGeneticCode().get(0) instanceof BooleanAllele)) {
                throw new UnsupportedOperationException(FitnessCalculatorProvider.NON_BOOLEAN_ALLELE_UNSUPPORTED);
            }

            return Service.getNormalizedGeneticCodeValue(
                    chromosome.getGeneticCode().stream().map(e -> (BooleanAllele) e).collect(Collectors.toList()),
                    actualMin,
                    actualMax,
                    normalizedMin,
                    normalizedMax
            );
        };

    }

}
