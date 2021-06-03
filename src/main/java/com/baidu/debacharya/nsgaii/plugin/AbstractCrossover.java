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

package com.baidu.debacharya.nsgaii.plugin;

import com.baidu.debacharya.nsgaii.datastructure.Chromosome;
import com.baidu.debacharya.nsgaii.datastructure.Population;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 遗传交叉操作抽象类.
 */
public abstract class AbstractCrossover {

	// 交叉操作的
    protected final CrossoverParticipantCreator crossoverParticipantCreator;

    // 执行交叉操作的default阈值.
    protected float crossoverProbability = 0.7f;

    public AbstractCrossover(CrossoverParticipantCreator crossoverParticipantCreator) {
        this.crossoverParticipantCreator = crossoverParticipantCreator;
    }

    public abstract List<Chromosome> perform(Population population);

    public boolean shouldPerformCrossover() {
        return ThreadLocalRandom.current().nextFloat() <= this.crossoverProbability;
    }

}
