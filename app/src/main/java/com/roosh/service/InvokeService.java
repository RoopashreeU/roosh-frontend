package com.roosh.service;

import android.app.AlertDialog;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.roosh.ChooseEndTimeActivity;
import com.roosh.dto.Appointment;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class InvokeService extends AsyncTask<String, String, Integer> {
    AppointmentService service;
    TextView view;

    public InvokeService(TextView view){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.106:8080/get_appointment/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(AppointmentService.class);
        this.view = view;
    }

    @Override
    protected Integer doInBackground(String... voids) {
        //return getHello();
        return getAppointment((String)voids[0], (String)voids[1], (String)voids[2]);
    }

    @Override
    protected void onPostExecute(Integer avoid) {
        System.out.println("Executed");
        view.setText(avoid.toString());
        super.onPostExecute(avoid);
    }

    public String getHello() {
        Call<String> x = service.sayHello();
        try {
            return x.execute().body();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int getAppointment(String date, String start, String end){
        Appointment app = new Appointment(date, date, start, end);
        Call<ResponseBody> x = service.getAppointment(app);
        try {
            int code = x.execute().code();
            System.out.println(code);
            return code;
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
