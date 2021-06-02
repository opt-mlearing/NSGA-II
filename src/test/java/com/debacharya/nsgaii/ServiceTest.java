package com.debacharya.nsgaii;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author caogaoli
 * @description: TODO
 * @date 2021/6/2下午4:41
 */
@Slf4j
public class ServiceTest {


    @Test
    public void testRoundOff() {
        double val3 = Service.roundOff(2.22222, 3);
        log.info("val3 is {}", val3);
        Assert.assertEquals(new Double(2.222), new Double(val3));
    }

}
