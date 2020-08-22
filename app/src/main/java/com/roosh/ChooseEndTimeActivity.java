package com.roosh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.roosh.service.InvokeService;

import java.util.concurrent.ExecutionException;

public class ChooseEndTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_end_time);
        assert getSupportActionBar()!=null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final String date = intent.getStringExtra("DATE_CHOSEN");
        final String time = intent.getStringExtra("TIME_CHOSEN");
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
                final String endtime = timePicker.getHour() + ":" + timePicker.getMinute() + ":00";
                String[] args = new String[]{date, time, endtime};
                try {
                    Integer result = new InvokeService(dateView).execute(args).get();
                    if(result == 202){
                        new AlertDialog.Builder(ChooseEndTimeActivity.this)
                                .setTitle("Appointment")
                                .setMessage("Successfully Scheduled Meeting")
                                .show();
                    }else{
                        new AlertDialog.Builder(ChooseEndTimeActivity.this)
                                .setTitle("Appointment")
                                .setMessage("There is a conflict")
                                .show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), date+" and "+endtime+" ", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void openDateActivity(){
        Intent intent = new Intent(this, ChooseDateActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}