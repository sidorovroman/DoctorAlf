package com.alfinur.doctors.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alfinur.doctors.AlertShedule;
import com.alfinur.doctors.R;
import com.alfinur.doctors.adapters.SheduleAdapter;
import com.alfinur.doctors.models.Shedule;

import java.util.ArrayList;


public class SheduleActivity extends ActionBarActivity {

    private ListView mSheduleListView;
    private ProgressDialog pd;
    private long docId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shedule);

        Intent intent = getIntent();
        docId = intent.getLongExtra("doctorId",0);
        String docFio = intent.getStringExtra("doctorName");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(docFio);

        mSheduleListView = (ListView) findViewById(R.id.shedule);
        getSheduleAndShow();

    }
    private void getSheduleAndShow() {
        final Context context = this;
        new AsyncTask<Void, Void, ArrayList<Shedule>>() {
            @Override
            protected void onPreExecute(){
                pd = new ProgressDialog(context);
                pd.setMessage("Загрузка расписания..");
                pd.show();
            }
            @Override
            protected ArrayList<Shedule> doInBackground(Void... params) {
                ArrayList<Shedule> shedules = getShedule();
                return shedules;
            }
            @Override
            protected void onPostExecute(ArrayList<Shedule> shedules) {

                SheduleAdapter sheduleAdapter = new SheduleAdapter(context,shedules);

                mSheduleListView.setAdapter(sheduleAdapter);
                mSheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Shedule shedule = (Shedule) view.getTag();
                        new AlertShedule(context).showSheduleAlert(shedule);
                    }
                });
                if (pd.isShowing()){
                    pd.dismiss();
                }
            }
        }.execute();

    }

    private ArrayList<Shedule> getShedule() {

        ArrayList<Shedule> shedules = new ArrayList<>();

        shedules.add(new Shedule("Фамилия пациента","Казань ул.Гвардейская 21",1, 0, null ,"24.12.15","14:00 - 15:00","123-12-1"));
        shedules.add(new Shedule("Фамилия пациента_2","Казань ул.Гвардейская 21",1, 1, "","24.12.15","14:00 - 15:00","123-12-1"));
        shedules.add(new Shedule("Фамилия пациента_3","Казань ул.Гвардейская 21",1, 0, "жалобы","24.12.15","14:00 - 15:00","123-12-1"));
        shedules.add(new Shedule("Фамилия пациента_4","Казань ул.Гвардейская 21",1, 1, null,"24.12.15","14:00 - 15:00","123-12-1"));

        return shedules;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_archive:
                Intent myIntent = new Intent(SheduleActivity.this, ArchiveActivity.class);
                myIntent.putExtra("doctorId", docId); //Optional parameters
                startActivity(myIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
