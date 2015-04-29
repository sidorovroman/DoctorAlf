package com.alfinur.doctors.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alfinur.doctors.R;
import com.alfinur.doctors.models.Schedule;

import java.util.ArrayList;


public class SheduleAdapter extends BaseAdapter {

    private Context ctx;
    private TextView fio;
    private TextView date;
    private TextView time;
    private LayoutInflater lInflater;
    ArrayList<Schedule> schedules;

    public SheduleAdapter(Context context, ArrayList<Schedule> schedules) {
        this.ctx = context;
        this.schedules = schedules;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return schedules.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return schedules.get(position);
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
        Schedule schedule = getShedule(position);
        fio = (TextView) view.findViewById(R.id.fio);
        date = (TextView) view.findViewById(R.id.date);
        time = (TextView) view.findViewById(R.id.time);
        fio.setText(schedule.getFio());
        date.setText(schedule.getData());
        time.setText(schedule.getTime());
        view.setTag(schedule); //bind model to the view

        if(schedule.getTid()!=0){
            ImageView type = (ImageView) view.findViewById(R.id.type);
            type.setImageDrawable(ctx.getResources().getDrawable(R.drawable.home));
        }
        return view;
    }

    public Schedule getShedule(int position) {
        return ((Schedule) getItem(position));
    }
}