package com.alfinur.doctors.exceptions;

import com.alfinur.doctors.exceptions.AccessDeniedException;
import com.alfinur.doctors.exceptions.NoConnectionException;
import com.alfinur.doctors.exceptions.NoHttpResponseException;
import com.alfinur.doctors.exceptions.ResourcesNotFoundedException;
import com.alfinur.doctors.exceptions.UnknownServerException;
import com.alfinur.doctors.network.NetworkUtil;

import java.net.UnknownHostException;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CustomErrorHandler implements ErrorHandler {
    public Throwable handleError(RetrofitError cause) {
        if (cause.isNetworkError()) {
            if (!NetworkUtil.isOnline())
                return new NoConnectionException(cause);


            if (cause.getCause() instanceof UnknownHostException
                    || cause.getMessage().contains("Illegal")
                    || cause.getMessage().contains("No address associated with hostname"))
                return new UnknownServerException();

            if ((cause.getMessage().contains("403")))
                return new AccessDeniedException(cause);

            if ((cause.getMessage().contains("404")))
                return new ResourcesNotFoundedException(cause);

            if ((cause.getMessage().contains("500")))
                return new NoHttpResponseException(cause);

            return new Exception();
        }

        Response r = cause.getResponse();
        if (r != null && (r.getStatus()/100 == 5)) {
            return new NoHttpResponseException(cause);
        } else if (r !=null && (r.getStatus() == 404)) {
            return new ResourcesNotFoundedException(cause);
        } else if (r !=null && (r.getStatus() == 403)) {
            return new AccessDeniedException(cause);
        }
        return cause;
    }
}