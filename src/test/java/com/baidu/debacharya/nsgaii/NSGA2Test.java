package com.baidu.debacharya.nsgaii;

import org.junit.Test;

import com.baidu.debacharya.nsgaii.datastructure.Population;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NSGA2Test {

    @Test
    public void testNSGA2Run() {

        NSGA2 nsga2 = new NSGA2();
        Population paretoFront = nsga2.run();
        log.info("finally return the front Population {}", paretoFront.toString());

    }

}
