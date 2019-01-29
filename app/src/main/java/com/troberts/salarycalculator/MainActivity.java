package com.troberts.salarycalculator;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> units = new ArrayList<>(Arrays.asList("Hour","Day","Week",
            "Bi-Week","Semi-Month","Month","Quarter","Year"));

    ViewFlipper salaryFlipper;
    WageCalculator calculator;

    EditText etSalary, etHours,etDays,etHolidays, etVacation;
    Spinner spinnerUnit;

    TextView tvHourlyU, tvHourlyA, tvDailyU, tvDailyA, tvWeeklyU, tvWeeklyA, tvBiWeeklyU, tvBiWeeklyA, tvSemiMonthlyU,
            tvSemiMonthlyA, tvMonthlyU, tvMonthlyA, tvQuarterlyU, tvQuarterlyA, tvAnnualU, tvAnnualA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new WageCalculator();
        salaryFlipper = findViewById(R.id.salaryFlipper);

        etSalary = findViewById(R.id.etSalary);
        etHours = findViewById(R.id.etHours);
        etDays = findViewById(R.id.etDays);
        etHolidays = findViewById(R.id.etHolidays);
        etVacation = findViewById(R.id.etVacation);
        spinnerUnit = findViewById(R.id.spinnerUnit);

        tvHourlyU = findViewById(R.id.tvHourlyU);
        tvHourlyA = findViewById(R.id.tvHourlyA);
        tvDailyU = findViewById(R.id.tvDailyU);
        tvDailyA = findViewById(R.id.tvDailyA);
        tvWeeklyU = findViewById(R.id.tvWeeklyU);
        tvWeeklyA = findViewById(R.id.tvWeeklyA);
        tvBiWeeklyU = findViewById(R.id.tvBiWeeklyU);
        tvBiWeeklyA = findViewById(R.id.tvBiWeeklyA);
        tvSemiMonthlyU = findViewById(R.id.tvSemiMonthlyU);
        tvSemiMonthlyA = findViewById(R.id.tvSemiMonthlyA);
        tvMonthlyU = findViewById(R.id.tvMonthlyU);
        tvMonthlyA = findViewById(R.id.tvMonthlyA);
        tvQuarterlyU = findViewById(R.id.tvQuarterlyU);
        tvQuarterlyA = findViewById(R.id.tvQuarterlyA);
        tvAnnualU = findViewById(R.id.tvAnnualU);
        tvAnnualA = findViewById(R.id.tvAnnualA);


        ArrayAdapter<String> spinnerUnitAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, units);
        spinnerUnit.setAdapter(spinnerUnitAdapter);

    }

    @SuppressLint("SetTextI18n")
    public void submit(View v){
        if (etSalary.getText().toString().isEmpty()|| etHours.getText().toString().isEmpty()|| etDays.getText().toString().isEmpty()||
                etHolidays.getText().toString().isEmpty()|| etVacation.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this, "Please make sure all fields are complete", Toast.LENGTH_SHORT);
        } else{
            salaryFlipper.showNext();
            BigDecimal salaryAmount = new BigDecimal(Integer.parseInt(etSalary.getText().toString()));
            String unit = spinnerUnit.getSelectedItem().toString();
            BigDecimal hoursPerWeek = new BigDecimal(Integer.parseInt(etHours.getText().toString()));
            BigDecimal daysPerWeek = new BigDecimal(Integer.parseInt(etDays.getText().toString()));
            BigDecimal holidaysPerYear = new BigDecimal(Integer.parseInt(etHolidays.getText().toString()));
            BigDecimal vacationDaysPerYear = new BigDecimal(Integer.parseInt(etVacation.getText().toString()));

            calculator.calculateResult(salaryAmount,unit,hoursPerWeek,daysPerWeek,holidaysPerYear,vacationDaysPerYear);

            tvHourlyU.setText("$"+calculator.result.get("hourly").toString());
            tvDailyU.setText("$"+calculator.result.get("daily").toString());
            tvWeeklyU.setText("$"+calculator.result.get("weekly").toString());
            tvBiWeeklyU.setText("$"+calculator.result.get("bi-weekly").toString());
            tvSemiMonthlyU.setText("$"+calculator.result.get("semi-monthly").toString());
            tvMonthlyU.setText("$"+calculator.result.get("monthly").toString());
            tvQuarterlyU.setText("$"+calculator.result.get("quarterly").toString());
            tvAnnualU.setText("$"+calculator.result.get("annual").toString());
            tvHourlyA.setText("$"+calculator.result.get("hourlyA").toString());
            tvDailyA.setText("$"+calculator.result.get("dailyA").toString());
            tvWeeklyA.setText("$"+calculator.result.get("weeklyA").toString());
            tvBiWeeklyA.setText("$"+calculator.result.get("bi-weeklyA").toString());
            tvSemiMonthlyA.setText("$"+calculator.result.get("semi-monthlyA").toString());
            tvMonthlyA.setText("$"+calculator.result.get("monthlyA").toString());
            tvQuarterlyA.setText("$"+calculator.result.get("quarterlyA").toString());
            tvAnnualA.setText("$"+calculator.result.get("annualA").toString());


        }

    }

    public void back(View v){
        salaryFlipper.showPrevious();
    }
}
