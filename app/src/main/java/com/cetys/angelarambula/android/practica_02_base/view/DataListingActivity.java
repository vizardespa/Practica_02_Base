package com.cetys.angelarambula.android.practica_02_base.view;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_listing);

        lstView = (ListView) findViewById(R.id.lstList);
        adapter = new CoachesAdapter(this);

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

    public class CoachAsyncTask extends AsyncTask<String,Void,Boolean>
    {
        @Override
        protected Boolean doInBackground(String... params) {
            try {
                ParserUtils PU = new ParserUtils();
                llenarDatos(PU.getCoaches());
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }

        }
        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if(result==false)
            {
                //Mensaje de error OP GG WP...TRYNDAMERE IS GOD!!!
            }
            else
            {
                lstView.setAdapter(adapter);
            }
        }
    }
}


