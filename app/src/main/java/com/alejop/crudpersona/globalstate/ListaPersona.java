package com.alejop.crudpersona.globalstate;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alejop.crudpersona.R;
import com.alejop.crudpersona.database.DataBaseHandler;
import com.alejop.crudpersona.domain.Persona;

import java.util.List;

public class ListaPersona extends AppCompatActivity {

    private ListView listaPersona;
    private GlobalState globalState;
    private MediaPlayer mp = new MediaPlayer();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_persona);
        initComponents();

    }

    private void initComponents() {
        listaPersona = (ListView) findViewById(R.id.ListPersona);
        globalState = (GlobalState) getApplication();
        DataBaseHandler db = globalState.getDataBaseHandler();
        List<Persona> personas = db.listAll();
        String[] arrayNombres = getArrayNombres(personas);
        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayNombres);
        listaPersona.setAdapter(arrayadapter);


        listaPersona.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // Se obtiene la posición presionada en el listview, y de acuerdo a esto se asigna un sonido
                if (position == 0) {



                    if (!mp.isPlaying())
                    {
                        mp = MediaPlayer.create(getApplicationContext(),R.raw.mitsui);
                        mp.start();
                    }
                    else {
                        mp.stop();
                    }

                }
                else if(position==1){


                    if(!mp.isPlaying()){
                        mp = MediaPlayer.create(getApplicationContext(),R.raw.slayerending);
                        mp.start();
                    }
                    else {

                        mp.stop();
                    }
                }

            }
            });

    }

    private String[] getArrayNombres(List<Persona> personas) {
        String [] arrayPersonas = new String[personas.size()];

        for(int i=0;i<personas.size();i++){
            arrayPersonas[i]=personas.get(i).getEspanol()+" Translation "+personas.get(i).getIngles();

        }

        return arrayPersonas;
    }

}

