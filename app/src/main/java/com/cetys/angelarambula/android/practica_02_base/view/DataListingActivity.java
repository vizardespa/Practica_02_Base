package com.cetys.angelarambula.android.practica_02_base.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.cetys.angelarambula.android.practica_01_base.R;
import com.cetys.angelarambula.android.practica_02_base.controller.CoachesAdapter;
import com.cetys.angelarambula.android.practica_02_base.model.Coach;

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

        lstView.setAdapter(adapter);

        ArrayList<Coach> miLista = new ArrayList<Coach>();
        miLista.add(new Coach(1, "nombre", "equipo"));
        miLista.add(new Coach(2, "nombre", "equipo"));
        miLista.add(new Coach(3, "nombre", "equipo"));
        miLista.add(new Coach(4, "nombre", "equipo"));
        miLista.add(new Coach(5, "nombre", "equipo"));

        llenarDatos(miLista);
    }

    public void llenarDatos(ArrayList<Coach> coachesList) {
        for (Coach coach : coachesList) {
            adapter.add(coach);
        }
        adapter.notifyDataSetChanged();
    }
}
