package com.roosh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assert getSupportActionBar()!=null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button scheduleMeetButton = findViewById(R.id.scheduleMeetingButton);
        scheduleMeetButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openDateActivity();
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
