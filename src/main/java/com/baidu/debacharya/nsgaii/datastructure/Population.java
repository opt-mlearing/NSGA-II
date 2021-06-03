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

package com.baidu.debacharya.nsgaii.datastructure;

import java.util.List;

/**
 * 种群 --> 种群由多个染色体组成.
 */
public class Population {

    // 种群由多个个体组成，一个个体由一条染色体表示.
    private final List<Chromosome> populace;

    public Population(final List<Chromosome> populace) {
        this.populace = populace;
    }

    public List<Chromosome> getPopulace() {
        return populace;
    }

    public int size() {
        return this.populace.size();
    }

    public Chromosome get(int index) {
        return this.populace.get(index);
    }

    public Chromosome getLast() {
        return this.populace.get(this.populace.size() - 1);
    }

    /**
     * 获取指定索引对应的正则后最大目标评价值.
     *
     * @param objectiveIndex 某个目标对应的索引.
     * @return 正则化之后的取值.
     */
    public double selectMaximumNormalizedObjectiveValue(int objectiveIndex) {

        // todo 实现逻辑可优化，效率可提升.
        double result = this.populace.get(0).getNormalizedObjectiveValues().get(objectiveIndex);

        for (Chromosome chromosome : this.populace)
            if (chromosome.getNormalizedObjectiveValues().get(objectiveIndex) > result) {
                result = chromosome.getNormalizedObjectiveValues().get(objectiveIndex);
            }
        return result;
    }

    /**
     * 获取指定索引对应的正则后最小的目标评价值.
     *
     * @param objectiveIndex 目标的索引.
     * @return 正则化后的取值.
     */
    public double selectMinimumNormalizedObjectiveValue(int objectiveIndex) {

        double result = this.populace.get(0).getNormalizedObjectiveValues().get(objectiveIndex);

        for (Chromosome chromosome : this.populace)
            if (chromosome.getNormalizedObjectiveValues().get(objectiveIndex) < result)
                result = chromosome.getNormalizedObjectiveValues().get(objectiveIndex);

        return result;
    }

    @Override
    public String toString() {

        StringBuilder response = new StringBuilder();

        for (Chromosome chromosome : this.populace)
            response.append(chromosome.toString()).append("\n");

        return response.toString();
    }

}
