package com.roosh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.roosh.service.InvokeService;

public class ChooseStartTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_start_time);
        assert getSupportActionBar()!=null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final String date = intent.getStringExtra("DATE_CHOSEN");
        TextView dateView = findViewById(R.id.dateView);
        dateView.setText(date);

        Button editDateButton = findViewById(R.id.editDateButton);
        editDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateActivity();
            }
        });

        Button scheduleMeetingButton = findViewById(R.id.scheduleButton);
        scheduleMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePicker timePicker = findViewById(R.id.timePicker2);
                final String time = timePicker.getHour() + ":" + timePicker.getMinute() + ":00";
                //new InvokeService(dateView).execute();
                //Toast.makeText(getApplicationContext(), date+" and "+time+" ", Toast.LENGTH_LONG).show();
                openEndDateActivity(date,time);
            }
        });


    }

    private void openDateActivity(){
        Intent intent = new Intent(this, ChooseDateActivity.class);
        startActivity(intent);
    }

    private void openEndDateActivity(String date, String time){
        Intent intent = new Intent(this, ChooseEndTimeActivity.class);
        intent.putExtra("TIME_CHOSEN", time);
        intent.putExtra("DATE_CHOSEN", date);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}