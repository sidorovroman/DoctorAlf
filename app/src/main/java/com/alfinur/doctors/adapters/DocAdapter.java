package com.alfinur.doctors.adapters;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alfinur.doctors.R;
import com.alfinur.doctors.models.Doctor;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DocAdapter extends BaseAdapter {

    private Context ctx;
    private TextView fio;
    private LayoutInflater lInflater;
    ArrayList<Doctor> doctors;

    public DocAdapter(Context context, ArrayList<Doctor> doctors) {
        this.ctx = context;
        this.doctors = doctors;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return doctors.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return doctors.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.row_doc, parent,false);
        }
        Doctor doctor = getDoctor(position);
        fio = (TextView) view.findViewById(R.id.fio);
        fio.setText(doctor.getFio());
        view.setTag(doctor); //bind model to the view
        return view;
    }

    public Doctor getDoctor(int position) {
        return ((Doctor) getItem(position));
    }
}