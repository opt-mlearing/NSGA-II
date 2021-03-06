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

import debacharya.nsgaii.datastructure.Chromosome;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 变异抽象类.
 */
public abstract class AbstractMutation {

    // mutation 编译的阈值.
    protected float mutationProbability = 0.03f;

    public AbstractMutation() {
    }

    /**
     * @param mutationProbability 外部自定义编译阈值.
     */
    public AbstractMutation(float mutationProbability) {
        this.mutationProbability = mutationProbability;
    }

    /**
     * 执行变异的方法.
     *
     * @param chromosome 染色体.
     * @return 变异后的染色体.
     */
    public abstract Chromosome perform(Chromosome chromosome);

    public boolean shouldPerformMutation() {
        return ThreadLocalRandom.current().nextFloat() <= this.mutationProbability;
    }

}
