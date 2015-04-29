package com.alfinur.doctors.models;

/**
 * Created by sidorovroman on 28.04.15.
 */
public class Shedule {
    private String pfio;
    private String address;
    private int arid;
    private int tid;
    private String complaints;
    private String data;
    private String time;
    private String phone;

    public Shedule(String pfio, String address, int arid, int tid, String complaints, String data, String time, String phone) {
        this.pfio = pfio;
        this.address = address;
        this.arid = arid;
        this.tid = tid;
        this.complaints = complaints;
        this.data = data;
        this.time = time;
        this.phone = phone;
    }

    public String getFio() {
        return pfio;
    }

    public void setFio(String fio) {
        this.pfio = fio;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getArid() {
        return arid;
    }

    public void setArid(int arid) {
        this.arid = arid;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }
}
