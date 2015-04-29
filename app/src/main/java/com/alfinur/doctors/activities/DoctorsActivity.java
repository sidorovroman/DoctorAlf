package com.alfinur.doctors.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alfinur.doctors.R;
import com.alfinur.doctors.adapters.DocAdapter;
import com.alfinur.doctors.models.Doctor;

import java.util.ArrayList;


public class DoctorsActivity extends ActionBarActivity {

    private ListView mDocsListView;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDocsListView = (ListView) findViewById(R.id.docs);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(getLayoutInflater().inflate(R.layout.action_bar, null),
                new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.MATCH_PARENT,
                        Gravity.CENTER
                )
        );
        getDoctorsAndShow();
    }

    private void getDoctorsAndShow() {
        final Context context = this;
        new AsyncTask<Void, Void, ArrayList<Doctor>>() {
            @Override
            protected void onPreExecute(){
                pd = new ProgressDialog(context);
                pd.setMessage("Загрузка докторов..");
                pd.show();
            }
            @Override
            protected ArrayList<Doctor> doInBackground(Void... params) {
                ArrayList<Doctor> doctors = getDoctors();
                return doctors;
            }
            @Override
            protected void onPostExecute(ArrayList<Doctor> doctors) {
                DocAdapter docAdapter = new DocAdapter(context, doctors);

                mDocsListView.setAdapter(docAdapter);
                mDocsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Doctor doctor = (Doctor) view.getTag();
                        Long doctorId = doctor.getDid();
                        Intent myIntent = new Intent(DoctorsActivity.this, SheduleActivity.class);
                        myIntent.putExtra("doctorId", doctorId); //Optional parameters
                        myIntent.putExtra("doctorName", doctor.getFio()); //Optional parameters
                        startActivity(myIntent);
                    }
                });
                if (pd.isShowing()){
                    pd.dismiss();
                }
            }
        }.execute();

    }

    private ArrayList<Doctor> getDoctors() {

        ArrayList<Doctor> doctors = new ArrayList<>();

        doctors.add(new Doctor(1,"Лаврентий"));
        doctors.add(new Doctor(2,"Онегин"));
        doctors.add(new Doctor(3,"Другой"));
        doctors.add(new Doctor(4,"4444"));
        doctors.add(new Doctor(5,"55555"));

        return doctors;
    }

}
