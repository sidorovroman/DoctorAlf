package com.alfinur.doctors;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

import com.alfinur.doctors.models.Schedule;

public class AlertShedule {
    private Context context;

    public AlertShedule(Context context) {
        this.context = context;
    }

    public void showSheduleAlert(Schedule schedule) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        StringBuilder stringBuilder = new StringBuilder();

        if(schedule.getTid() == 1) {
            stringBuilder
                    .append("Прием по адресу: ")
                    .append(schedule.getAddress())
                    .append("\n");
        }else{
            stringBuilder
                    .append("Прием в больнице")
                    .append("\n");
        }

        stringBuilder.append(schedule.getFio());

        if(schedule.getPhone()!=null || schedule.getPhone()!=null && !schedule.getPhone().isEmpty()) {
            stringBuilder.append("\n")
                    .append("тел:")
                    .append(schedule.getPhone());
        }

        if(schedule.getComplaints()!=null) {
            stringBuilder.append("\n")
                    .append("жалобы:")
                    .append(schedule.getComplaints());
        }

        String message = stringBuilder.toString();
        Drawable icon = (schedule.getTid() == 1 ) ? context.getResources().getDrawable(R.drawable.home) : context.getResources().getDrawable(R.drawable.clinic);
        builder.setTitle(schedule.getTime().concat("   ").concat(schedule.getData()))
                .setMessage(message)
                .setIcon(icon)
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
