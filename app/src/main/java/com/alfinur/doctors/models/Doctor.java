package com.alfinur.doctors.models;

/**
 * Created by sidorovroman on 27.04.15.
 */
public class Doctor {
    private long did;
    private String fio;

    //temp constructor
    public Doctor(long did, String fio) {
        this.did = did;
        this.fio = fio;
    }

    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
