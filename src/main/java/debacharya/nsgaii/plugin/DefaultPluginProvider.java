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

import debacharya.nsgaii.datastructure.BooleanAllele;
import debacharya.nsgaii.datastructure.Chromosome;
import debacharya.nsgaii.datastructure.Population;
import debacharya.nsgaii.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class DefaultPluginProvider {

    /**
     * 产生基因.
     *
     * @return
     */
    public static GeneticCodeProducer defaultGeneticCodeProducer() {

        return (length) -> {

            List<BooleanAllele> geneticCode = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                // 随机产生.
                geneticCode.add(i, new BooleanAllele(ThreadLocalRandom.current().nextBoolean()));
            }
            return geneticCode;

        };

    }

    /**
     * 产生种群.
     *
     * @return
     */
    public static PopulationProducer defaultPopulationProducer() {

        return (populationSize, chromosomeLength, geneticCodeProducer, fitnessCalculator) -> {

            List<Chromosome> populace = new ArrayList<>();
            for (int i = 0; i < populationSize; i++) {
                // 通过一组有序基因构建一条染色体. -->  进而多条染色体组成一个种群.
                populace.add(new Chromosome(geneticCodeProducer.produce(chromosomeLength)));
            }
            return new Population(populace);

        };

    }

    /**
     * 产生子种群 --> 子种群通过交叉变异的方式产生.
     * 且子种群中染色体的个数与父种群中染色体的个数一致.
     *
     * @return
     */
    public static ChildPopulationProducer defaultChildPopulationProducer() {

        return (parentPopulation, crossover, mutation, populationSize) -> {

            List<Chromosome> populace = new ArrayList<>();
            while (populace.size() < populationSize) {
                if ((populationSize - populace.size()) == 1) {
                    populace.add(mutation.perform(Service.crowdedBinaryTournamentSelection(parentPopulation)));
                } else {
                    // 父种群->遍历交叉染色体->变异染色体. 原生的实现是产生两个交叉的染色体，然后再逐个变异.
                    for (Chromosome chromosome : crossover.perform(parentPopulation)) {
                        populace.add(mutation.perform(chromosome));
                    }
                }
            }
            return new Population(populace);

        };

    }

}
