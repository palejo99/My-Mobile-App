package com.alejop.crudpersona.globalstate;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alejop.crudpersona.DashBoard;
import com.alejop.crudpersona.R;
import com.alejop.crudpersona.database.DataBaseHandler;
import com.alejop.crudpersona.domain.Persona;

import java.util.ArrayList;

public class BuscarPersona extends AppCompatActivity {

   // private String languajes[]={"C#","Java","PHP","ABAP","BASIS","HTML","JavaScript"};
    private AutoCompleteTextView key;
    private Button btnBuscar;
    private TextView informacion;
    private GlobalState globalState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_persona);
        initComponents();
    }

    private void initComponents() {

        globalState =(GlobalState)getApplication();
        DataBaseHandler db = globalState.getDataBaseHandler();
        key = (AutoCompleteTextView) findViewById(R.id.buscar);
        // get a list of all names
        ArrayList<String> allNames= db.getAllNames();
        // create an ArrayAdapter to make this collection available to the UI
        ArrayAdapter<String> adaAllNames= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,allNames);
        //Make the adapter availabel to the Auto Complete Text.
        key.setAdapter(adaAllNames);
        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        informacion= (TextView)findViewById(R.id.informacion);

        // Oculta el campo de información

        informacion.setVisibility(View.GONE);


    }

    public void onClickBuscar(View v){

        DataBaseHandler db = globalState.getDataBaseHandler();
        Persona persona = db.findByKey(key.getText().toString());


        if(persona != null){
            //hace visible el campo de información
            informacion.setVisibility(View.VISIBLE);
            informacion.setText(" La frase consultada es: "+persona.getEspanol()+" Translation: "+persona.getIngles());

        }
        else{
            //oculta la información
            informacion.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "No se encuentra información relacionada", Toast.LENGTH_SHORT).show();
        }
    }


}
