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

package com.debacharya.nsgaii.datastructure;

import com.debacharya.nsgaii.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 染色体.
 * 一条染色体由一组等位基因序列构成.
 */
public class Chromosome {

    // 多目标的各个目标的计算取值.
    private final List<Double> objectiveValues;
    // 多目标每个结果的正则化结果.
    private final List<Double> normalizedObjectiveValues;
    // 由多个等位基因构成的序列.
    private final List<AbstractAllele> geneticCode;
    // 当前染色体积支配的染色体.
    private List<Chromosome> dominatedChromosomes;
    // 拥挤度.
    private double crowdingDistance = 0;
    // 当前染色体可支配的染色体的数量.
    private int dominatedCount = 0;
    // 当前染色体的适应度.
    private double fitness = Double.MIN_VALUE;
    // 当前染色体所在的层级排名.
    private int rank = -1;

    /**
     * 染色体构造函数.
     *
     * @param geneticCode 等位基因序列.
     */
    public Chromosome(List<? extends AbstractAllele> geneticCode) {

        this.geneticCode = new ArrayList<>();
        this.objectiveValues = new ArrayList<>();
        this.normalizedObjectiveValues = new ArrayList<>();
        this.dominatedChromosomes = new ArrayList<>();
        for (AbstractAllele allele : geneticCode) {
            this.geneticCode.add(allele.getCopy());
        }

    }

    public Chromosome(Chromosome chromosome) {

        this(chromosome.geneticCode);

        for (int i = 0; i < chromosome.objectiveValues.size(); i++) {
            this.objectiveValues.add(i, chromosome.objectiveValues.get(i));
        }
        this.crowdingDistance = chromosome.crowdingDistance;
        this.dominatedCount = chromosome.dominatedCount;
        this.fitness = chromosome.fitness;
        this.rank = chromosome.rank;

    }

    public void addDominatedChromosome(Chromosome chromosome) {
        this.dominatedChromosomes.add(chromosome);
    }

    /**
     * 当前染色体可支配的染色体数量.
     *
     * @param incrementValue
     */
    public void incrementDominatedCount(int incrementValue) {
        this.dominatedCount += incrementValue;
    }

    public List<Chromosome> getDominatedChromosomes() {
        return dominatedChromosomes;
    }

    public void setDominatedChromosomes(List<Chromosome> dominatedChromosomes) {
        this.dominatedChromosomes = dominatedChromosomes;
    }

    public List<Double> getObjectiveValues() {
        return objectiveValues;
    }

    public void addObjectiveValue(int index, double value) {

        // 精确到小数点后四位.
        double roundedValue = Service.roundOff(value, 4);
        if (this.getObjectiveValues().size() <= index) {
            this.objectiveValues.add(index, roundedValue);
        } else {
            this.objectiveValues.set(index, roundedValue);
        }

    }

    public List<Double> getNormalizedObjectiveValues() {
        return this.normalizedObjectiveValues;
    }

    public void setNormalizedObjectiveValue(int index, double value) {

        if (this.getNormalizedObjectiveValues().size() <= index) {
            this.normalizedObjectiveValues.add(index, value);
        } else {
            this.normalizedObjectiveValues.set(index, value);
        }

    }

    public List<AbstractAllele> getGeneticCode() {
        return geneticCode;
    }

    public double getCrowdingDistance() {
        return crowdingDistance;
    }

    public void setCrowdingDistance(double crowdingDistance) {
        this.crowdingDistance = crowdingDistance;
    }

    public int getDominatedCount() {
        return dominatedCount;
    }

    public void setDominatedCount(int dominationCount) {
        this.dominatedCount = dominationCount;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getLength() {
        return this.geneticCode.size();
    }

    public Chromosome getCopy() {
        return new Chromosome(this);
    }

    public void reset() {
        this.dominatedCount = 0;
        this.rank = Integer.MAX_VALUE;
        this.dominatedChromosomes = new ArrayList<>();
    }

    @Override
    public String toString() {

        StringBuilder response = new StringBuilder("Objective values: [");

        for (double value : this.objectiveValues) {
            response.append(value).append(" ");
        }
        response.append("] | Rank: ").append(this.rank).append(" | Crowding Distance: ").append(this.crowdingDistance);

        return response.toString();
    }

}
