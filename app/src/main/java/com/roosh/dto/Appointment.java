package com.roosh.dto;

import com.google.gson.annotations.JsonAdapter;

import java.sql.Date;
import java.sql.Time;
import java.util.List;


public class Appointment {
	int id;
	String name;
	String purpose;

	Date startDate;

	Date endDate;

	String startTime;

	String endTime;

	List<String> people;
	
	//File agenda;
	//File otherDocs;
	public Appointment(String start, String end, String startT, String endT){
		System.out.println(start);
		System.out.println(end);
		System.out.println(startT);
		System.out.println(endT);
		this.startDate = Date.valueOf(start);
		this.endDate = Date.valueOf(end);
		//startTime = Time.valueOf(startT);
		//endTime = Time.valueOf(endT);
		startTime = startT;
		endTime = endT;
		System.out.println(startTime);
		System.out.println(endTime);
	}
}
