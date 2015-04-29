package com.alfinur.doctors.network;

import com.alfinur.doctors.exceptions.NoConnectionException;
import com.alfinur.doctors.exceptions.ResourcesNotFoundedException;
import com.alfinur.doctors.models.Doctor;
import com.alfinur.doctors.models.Schedule;

import org.apache.http.NoHttpResponseException;

import java.util.ArrayList;

import retrofit.http.GET;
import retrofit.http.Path;

public interface DoctorService {

    @GET("/doctors")
    ArrayList<Doctor> getDoctors() throws NoConnectionException, NoHttpResponseException, ResourcesNotFoundedException;

    @GET("/doctors/{id}")
    Doctor getDoctor( @Path("id") Long doctorId) throws NoConnectionException, NoHttpResponseException;

    @GET("/schedule/{id}")
    ArrayList<Schedule> getSchedule( @Path("id") Long doctorId) throws NoConnectionException, NoHttpResponseException;

    @GET("/archive/{id}")
    ArrayList<Schedule> getArchive( @Path("id") Long doctorId) throws NoConnectionException, NoHttpResponseException;

}