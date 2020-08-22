package com.roosh.service;

import com.roosh.dto.Appointment;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface AppointmentService {
    @GET("/")
    public Call<String> sayHello();

    @PUT("/get_appointment")
    public Call<ResponseBody> getAppointment(@Body Appointment app);
}
