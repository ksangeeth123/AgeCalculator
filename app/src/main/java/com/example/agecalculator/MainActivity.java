package com.example.agecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCurrentDate;
    private EditText editTextBirthDate;
    private Button buttonCalculate;
    private TextView textViewAge;
    private Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCurrentDate = findViewById(R.id.editTextCurrentDate);
        editTextBirthDate = findViewById(R.id.editTextBirthDate);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewAge = findViewById(R.id.textViewAge);
        buttonClear = findViewById(R.id.buttonClear);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAge();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    private void calculateAge() {
        String currentDateStr = editTextCurrentDate.getText().toString();
        String birthDateStr = editTextBirthDate.getText().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date currentDate = sdf.parse(currentDateStr);
            Date birthDate = sdf.parse(birthDateStr);

            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(currentDate);
            int currentYear = currentCalendar.get(Calendar.YEAR);
            int currentMonth = currentCalendar.get(Calendar.MONTH);
            int currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);

            Calendar birthCalendar = Calendar.getInstance();
            birthCalendar.setTime(birthDate);
            int birthYear = birthCalendar.get(Calendar.YEAR);
            int birthMonth = birthCalendar.get(Calendar.MONTH);
            int birthDay = birthCalendar.get(Calendar.DAY_OF_MONTH);

            int ageInYears = currentYear - birthYear;
            int ageInMonths = currentMonth - birthMonth;
            int ageInDays = currentDay - birthDay;

            if (ageInDays < 0) {
                ageInMonths--;
                currentCalendar.add(Calendar.MONTH, -1);
                ageInDays += currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            }

            if (ageInMonths < 0) {
                ageInYears--;
                ageInMonths += 12;
            }

            String ageText = "Age: " + ageInYears + " years, " + ageInMonths + " months, " + ageInDays + " days";
            textViewAge.setText(ageText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        editTextCurrentDate.setText("");
        editTextBirthDate.setText("");
        textViewAge.setText("YYYY MM DD");
    }
}
