package com.alfinur.doctors.adapters;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alfinur.doctors.R;
import com.alfinur.doctors.models.Doctor;
import com.alfinur.doctors.models.Shedule;

import java.util.ArrayList;


public class SheduleAdapter extends BaseAdapter {

    private Context ctx;
    private TextView fio;
    private TextView date;
    private TextView time;
    private LayoutInflater lInflater;
    ArrayList<Shedule> shedules;

    public SheduleAdapter(Context context, ArrayList<Shedule> shedules) {
        this.ctx = context;
        this.shedules = shedules;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return shedules.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return shedules.get(position);
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
            view = lInflater.inflate(R.layout.row_shedule, parent,false);
        }
        Shedule shedule = getShedule(position);
        fio = (TextView) view.findViewById(R.id.fio);
        date = (TextView) view.findViewById(R.id.date);
        time = (TextView) view.findViewById(R.id.time);
        fio.setText(shedule.getFio());
        date.setText(shedule.getData());
        time.setText(shedule.getTime());
        view.setTag(shedule); //bind model to the view

        if(shedule.getTid()!=0){
            ImageView type = (ImageView) view.findViewById(R.id.type);
            type.setImageDrawable(ctx.getResources().getDrawable(R.drawable.home));
        }
        return view;
    }

    public Shedule getShedule(int position) {
        return ((Shedule) getItem(position));
    }
}