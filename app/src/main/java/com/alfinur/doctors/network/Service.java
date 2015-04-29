package com.alfinur.doctors.network;

import com.alfinur.doctors.exceptions.NoConnectionException;
import com.alfinur.doctors.exceptions.ResourcesNotFoundedException;
import com.squareup.okhttp.Response;

import org.apache.http.NoHttpResponseException;

import retrofit.http.GET;

public interface Service {

    @GET("/token/refresh")
    Response refreshToken() throws NoConnectionException, NoHttpResponseException, ResourcesNotFoundedException;

    @GET("/cars")
    Response getCars() throws NoConnectionException, NoHttpResponseException;

}