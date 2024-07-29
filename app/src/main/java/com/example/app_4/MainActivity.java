package com.example.app_4;

import static com.example.app_4.StringConst.*;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_4.databinding.ActivityMainBinding;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.showAlertDialogButton.setOnClickListener(v -> showAlertDialog());
        binding.exitButton.setOnClickListener(v -> finish());
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(CHOOSE_AN_ACTION)
                .setMessage(SELECT_ONE_OF_THE_ACTIONS_BELOW)
                .setPositiveButton(SET_TIME, (dialog, which) -> showTimePickerDialog())
                .setNegativeButton(SET_DATE, (dialog, which) -> showDatePickerDialog())
                .setNeutralButton(CANCEL, (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    private void showTimePickerDialog() {
        @SuppressLint("SetTextI18n")
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                (view, hourOfDay, minuteOfHour) -> {
                    @SuppressLint("DefaultLocale")
                    String selectedTime = String.format("%02d:%02d", hourOfDay, minuteOfHour);
                    binding.timeTextView.setText(SELECTED_TIME + selectedTime);
                },
                LocalDateTime.now().getHour(),
                LocalDateTime.now().getMinute(),
                true);
        timePickerDialog.show();
    }

    private void showDatePickerDialog() {
        @SuppressLint("SetTextI18n")
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                (view, year, month, dayOfMonth) -> {
                    @SuppressLint("DefaultLocale")
                    String selectedDate = String.format("%02d-%02d-%04d", dayOfMonth, month + 1, year);
                    binding.dateTextView.setText(SELECTED_DATE + selectedDate);
                },
                LocalDateTime.now().getYear(),
                LocalDateTime.now().getMonthValue()-1,
                LocalDateTime.now().getDayOfMonth());
        datePickerDialog.show();
    }
}