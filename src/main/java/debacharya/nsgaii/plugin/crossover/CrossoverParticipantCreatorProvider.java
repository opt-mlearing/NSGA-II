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

package debacharya.nsgaii.plugin.crossover;

import debacharya.nsgaii.Service;
import debacharya.nsgaii.datastructure.Chromosome;
import debacharya.nsgaii.plugin.CrossoverParticipantCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * 从父种群中选择两条待交叉的染色体.
 */
public class CrossoverParticipantCreatorProvider {

    public static CrossoverParticipantCreator selectByBinaryTournamentSelection() {

        return population -> {

            List<Chromosome> selected = new ArrayList<>();
            selected.add(Service.crowdedBinaryTournamentSelection(population));
            selected.add(Service.crowdedBinaryTournamentSelection(population));
            return selected;

        };

    }

}
