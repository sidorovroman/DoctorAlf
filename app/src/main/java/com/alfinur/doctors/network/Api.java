package com.alfinur.doctors.network;

import android.content.Context;

import com.alfinur.doctors.Utils;
import com.alfinur.doctors.exceptions.CustomErrorHandler;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class Api {
    private static final String DIRTY_SERVER = "http://alf.ngrok.io/doctor.php";
    private static DoctorService doctorService;

    public static DoctorService getService(final Context context) {
        if (doctorService == null) {
            RequestInterceptor requestInterceptor = new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("User-Agent", Utils.getUserAgent(context));
                }
            };

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setClient(new OkClient(new OkHttpClient()))
                    .setLogLevel(RestAdapter.LogLevel.NONE)
                    .setEndpoint(DIRTY_SERVER)
                    .setRequestInterceptor(requestInterceptor)
                    .setErrorHandler(new CustomErrorHandler())
                    .build();

            doctorService = restAdapter.create(DoctorService.class);
        }
        return doctorService;
    }

    public static void deleteService() {
        doctorService = null;
    }
}
