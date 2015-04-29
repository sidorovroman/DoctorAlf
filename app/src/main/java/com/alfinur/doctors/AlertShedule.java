package com.alfinur.doctors;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

import com.alfinur.doctors.models.Shedule;

/**
 * Created by sidorovroman on 29.04.15.
 */
public class AlertShedule {
    private Context context;

    public AlertShedule(Context context) {
        this.context = context;
    }

    public void showSheduleAlert(Shedule shedule) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        StringBuilder stringBuilder = new StringBuilder();

        if(shedule.getTid() == 1) {
            stringBuilder
                    .append("Прием по адресу: ")
                    .append(shedule.getAddress())
                    .append("\n");
        }else{
            stringBuilder
                    .append("Прием в больнице")
                    .append("\n");
        }

        stringBuilder.append(shedule.getFio());

        if(shedule.getPhone()!=null || shedule.getPhone()!=null && !shedule.getPhone().isEmpty()) {
            stringBuilder.append("\n")
                    .append("тел:")
                    .append(shedule.getPhone());
        }

        if(shedule.getComplaints()!=null) {
            stringBuilder.append("\n")
                    .append("жалобы:")
                    .append(shedule.getComplaints());
        }

        String message = stringBuilder.toString();
        Drawable icon = (shedule.getTid() == 1 ) ? context.getResources().getDrawable(R.drawable.home) : context.getResources().getDrawable(R.drawable.clinic);
        builder.setTitle(shedule.getTime().concat("   ").concat(shedule.getData()))
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
