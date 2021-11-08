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

package com.baidu.debacharya.nsgaii.plugin.crossover;

import com.baidu.debacharya.nsgaii.datastructure.AbstractAllele;
import com.baidu.debacharya.nsgaii.datastructure.Chromosome;
import com.baidu.debacharya.nsgaii.datastructure.Population;
import com.baidu.debacharya.nsgaii.plugin.AbstractCrossover;
import com.baidu.debacharya.nsgaii.plugin.CrossoverParticipantCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Uniform 交叉策略.
 */
public class UniformCrossover extends AbstractCrossover {

    public UniformCrossover(CrossoverParticipantCreator crossoverParticipantCreator) {
        super(crossoverParticipantCreator);
    }

    /**
     * @param population 种群.
     * @return 类似返回一个亚种群，根据该实现该亚种群中染色体的个数一定为2.
     */
    @Override
    public List<Chromosome> perform(Population population) {

        List<Chromosome> result = new ArrayList<>();
        List<Chromosome> selected = this.crossoverParticipantCreator.create(population);
        // 是否交叉
        if (this.shouldPerformCrossover()) {
            // 注意：循环两次.
            for (int i = 0; i < 2; i++) {
                result.add(this.prepareChildChromosome(selected.get(0), selected.get(1)));
            }
        } else {
            result.add(selected.get(0).getCopy());
            result.add(selected.get(1).getCopy());
        }

        return result;

    }

    /**
     * @param chromosome1 父染色体1
     * @param chromosome2 父染色体2
     * @return Chromosome 交叉操作后的子染色体.
     */
    private Chromosome prepareChildChromosome(Chromosome chromosome1, Chromosome chromosome2) {

        List<AbstractAllele> geneticCode = new ArrayList<>();
        for (int i = 0; i < chromosome1.getLength(); i++) {
            switch (Math.random() <= 0.5 ? 1 : 2) {
                case 1:
                    geneticCode.add(i, chromosome1.getGeneticCode().get(i).getCopy());
                    break;
                case 2:
                    geneticCode.add(i, chromosome2.getGeneticCode().get(i).getCopy());
                    break;
            }
        }
        return new Chromosome(geneticCode);

    }

}
