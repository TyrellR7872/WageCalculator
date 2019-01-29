package com.troberts.salarycalculator;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void salaryisCorrect() {
        WageCalculator calc = new WageCalculator();
        calc.calculateResult(new BigDecimal(50),"Hour",new BigDecimal(40),new BigDecimal(5),new BigDecimal(10),new BigDecimal(15));
        String test = calc.result.get("hourly").toString();
        Map<String,BigDecimal> result = calc.result;


    }
}