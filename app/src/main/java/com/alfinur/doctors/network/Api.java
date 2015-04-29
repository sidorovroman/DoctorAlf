package com.alfinur.doctors.network;

import android.content.Context;

import com.alfinur.doctors.Utils;
import com.alfinur.doctors.exceptions.CustomErrorHandler;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class Api {
    private static Service service;

    public static Service getService(final Context context) {
        if (service == null) {
            RequestInterceptor requestInterceptor = new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("User-Agent", Utils.getUserAgent(context));
                }
            };

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setClient(new OkClient(new OkHttpClient()))
                    .setLogLevel(RestAdapter.LogLevel.NONE)
                    .setEndpoint("dsigning")
                    .setRequestInterceptor(requestInterceptor)
                    .setErrorHandler(new CustomErrorHandler())
                    .build();

            service = restAdapter.create(Service.class);
        }
        return service;
    }

    public static void deleteService() {
        service = null;
    }
}
