package com.cetys.angelarambula.android.practica_02_base.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cetys.angelarambula.android.practica_01_base.R;
import com.cetys.angelarambula.android.practica_02_base.controller.CoachesAdapter;
import com.cetys.angelarambula.android.practica_02_base.model.Coach;
import com.cetys.angelarambula.android.practica_02_base.utils.ParserUtils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;

import java.util.ArrayList;


public class DataListingActivity extends Activity {

    ListView lstView = null;
    CoachesAdapter adapter = null;
    ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_listing);

        lstView = (ListView) findViewById(R.id.lstList);
        adapter = new CoachesAdapter(this);

        //http://www.androidhive.info/2011/10/android-listview-tutorial/
        //No Idea how to implement this stuff ^^ :c

        //lstView.setAdapter(adapter);

        /*ArrayList<Coach> miLista = new ArrayList<Coach>();
        miLista.add(new Coach(1, "nombre", "equipo"));
        miLista.add(new Coach(2, "nombre", "equipo"));
        miLista.add(new Coach(3, "nombre", "equipo"));
        miLista.add(new Coach(4, "nombre", "equipo"));
        miLista.add(new Coach(5, "nombre", "equipo"));

        ParserUtils PU = new ParserUtils();
        //llenarDatos(miLista);
        try {
            llenarDatos(PU.getCoaches());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */
        new CoachAsyncTask().execute("");
    }

    public void llenarDatos(ArrayList<Coach> coachesList) {
        for (Coach coach : coachesList) {
            adapter.add(coach);
        }
        adapter.notifyDataSetChanged();
    }

    public class CoachAsyncTask extends AsyncTask<String,Void,ArrayList<Coach>>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(DataListingActivity.this, "Espera", "Descargando...");
        }

        @Override
        protected ArrayList<Coach> doInBackground(String... params) {
            try {
                ParserUtils PU = new ParserUtils();
                return PU.getCoaches();
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

        }
        @Override
        protected void onPostExecute(ArrayList<Coach> result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            if(result==null)
            {
                //Mensaje de error OP GG WP...TRYNDAMERE IS GOD!!!
            }
            else
            {
                llenarDatos(result);
                lstView.setAdapter(adapter);
            }
        }
    }
}


