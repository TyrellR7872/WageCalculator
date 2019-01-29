package com.troberts.salarycalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class WageCalculator {

    Map<String,BigDecimal> result;

    public WageCalculator(){
        result = new HashMap<>();
    }

    public void calculateResult(BigDecimal salaryAmount, String unit, BigDecimal hoursPerWeek, BigDecimal daysPerWeek, BigDecimal holidaysPerYear, BigDecimal vacationDaysPerYear){

        switch(unit){
            case "Day":
                salaryAmount = salaryAmount.divide(hoursPerWeek.divide(daysPerWeek));
                break;
            case "Week":
                salaryAmount =salaryAmount.divide(hoursPerWeek);
                break;
            case "Bi-Week":
                salaryAmount =salaryAmount.divide(hoursPerWeek.multiply(new BigDecimal(2)));
                break;
            case "Semi-Month":
                salaryAmount =salaryAmount.divide((hoursPerWeek.multiply(new BigDecimal(52))).divide(new BigDecimal(24),2,RoundingMode.HALF_UP),2,RoundingMode.HALF_UP);
                break;
            case "Month":
                salaryAmount =salaryAmount.divide(hoursPerWeek.multiply(new BigDecimal(52)).divide(new BigDecimal(12),2,RoundingMode.HALF_UP),2,RoundingMode.HALF_UP);
                break;
            case "Quarter":
                salaryAmount =salaryAmount.divide((hoursPerWeek.multiply(new BigDecimal(52))).divide(new BigDecimal(4),2,RoundingMode.HALF_UP),2,RoundingMode.HALF_UP);
                break;
            case "Annual":
                salaryAmount =salaryAmount.divide((hoursPerWeek.multiply(new BigDecimal(52))));
                break;
        }

        BigDecimal annualRate = salaryAmount.multiply(hoursPerWeek.divide(daysPerWeek)).multiply(daysPerWeek.multiply(new BigDecimal(52)));
        result.put("annual", annualRate);
        result.put("quarterly", annualRate.divide(new BigDecimal(4),2,RoundingMode.HALF_UP));
        result.put("monthly", annualRate.divide(new BigDecimal(12),2,RoundingMode.HALF_UP));
        result.put("semi-monthly",annualRate.divide(new BigDecimal(24),2,RoundingMode.HALF_UP));
        result.put("bi-weekly",annualRate.divide(new BigDecimal(26),2,RoundingMode.HALF_UP));
        result.put("weekly",annualRate.divide(new BigDecimal(52),2,RoundingMode.HALF_UP));
        result.put("daily",annualRate.divide((new BigDecimal(52).multiply(daysPerWeek)),2,RoundingMode.HALF_UP));
        result.put("hourly",salaryAmount);

        BigDecimal daysOff = holidaysPerYear.add(vacationDaysPerYear);
        BigDecimal annualRateA = salaryAmount.multiply(hoursPerWeek.divide(daysPerWeek)).multiply((daysPerWeek.multiply(new BigDecimal(52)).subtract(daysOff)));
        result.put("annualA", annualRateA);
        result.put("quarterlyA", annualRateA.divide(new BigDecimal(4),2,RoundingMode.HALF_UP));
        result.put("monthlyA", annualRateA.divide(new BigDecimal(12),2,RoundingMode.HALF_UP));
        result.put("semi-monthlyA",annualRateA.divide(new BigDecimal(24),2,RoundingMode.HALF_UP));
        result.put("bi-weeklyA",annualRateA.divide(new BigDecimal(26),2,RoundingMode.HALF_UP));
        result.put("weeklyA",annualRateA.divide(new BigDecimal(52),2,RoundingMode.HALF_UP));
        result.put("dailyA",annualRateA.divide((new BigDecimal(52).multiply(daysPerWeek)),2,RoundingMode.HALF_UP));
        result.put("hourlyA",annualRateA.divide(hoursPerWeek.multiply(new BigDecimal(52)),2,RoundingMode.HALF_UP));
    }



}
