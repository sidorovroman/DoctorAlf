package com.alfinur.doctors.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alfinur.doctors.AlertShedule;
import com.alfinur.doctors.R;
import com.alfinur.doctors.adapters.SheduleAdapter;
import com.alfinur.doctors.exceptions.NoConnectionException;
import com.alfinur.doctors.models.Schedule;
import com.alfinur.doctors.network.Api;

import org.apache.http.NoHttpResponseException;

import java.util.ArrayList;


public class ArchiveActivity extends ActionBarActivity {

    private ListView mSheduleListView;
    private TextView noNotes;
    private ProgressDialog pd;
    private long docId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        Intent intent = getIntent();
        docId = intent.getLongExtra("doctorId",0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSheduleListView = (ListView) findViewById(R.id.schedule);
        noNotes = (TextView) findViewById(R.id.noNotes);

        getScheduleAndShow();

    }
    private void getScheduleAndShow() {
        final Context context = this;
        new AsyncTask<Void, Void, ArrayList<Schedule>>() {
            @Override
            protected void onPreExecute(){
                pd = new ProgressDialog(context);
                pd.setMessage("Загрузка архива..");
                pd.show();
            }
            @Override
            protected ArrayList<Schedule> doInBackground(Void... params) {
                ArrayList<Schedule> schedules = null;
                try {
                    schedules = Api.getService(context).getArchive(docId);
                } catch (NoConnectionException | NoHttpResponseException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return schedules;
            }
            @Override
            protected void onPostExecute(ArrayList<Schedule> schedules) {

                if (schedules != null) {

                    SheduleAdapter sheduleAdapter = new SheduleAdapter(context, schedules);

                    mSheduleListView.setAdapter(sheduleAdapter);
                    mSheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Schedule schedule = (Schedule) view.getTag();
                            new AlertShedule(context).showSheduleAlert(schedule);
                        }
                    });
                } else {
                    noNotes.setVisibility(View.VISIBLE);
                }
                if (pd.isShowing()){
                    pd.dismiss();
                }
            }
        }.execute();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
