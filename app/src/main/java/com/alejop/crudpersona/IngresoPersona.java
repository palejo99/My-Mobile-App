package com.alejop.crudpersona;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alejop.crudpersona.database.DataBaseHandler;
import com.alejop.crudpersona.domain.Persona;
import com.alejop.crudpersona.globalstate.GlobalState;

public class IngresoPersona extends AppCompatActivity {


    private EditText espanol;
    private EditText ingles;
    private Button btnGuardar;
    private ImageView imageView;
    private GlobalState globalState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_persona);
        initComponents();

    }

    private void initComponents() {

        espanol = (EditText)findViewById(R.id.espanol);
        ingles = (EditText)findViewById(R.id.ingles);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        globalState = (GlobalState) getApplication();
    }


    public void onClickBtnGuardar(View v) {
        DataBaseHandler db = new DataBaseHandler(this, "Registro", null, 1);
        Persona persona = new Persona();
        persona.setEspanol(espanol.getText().toString());
        persona.setIngles(ingles.getText().toString());

        if (espanol.getText().toString().equals("")|| ingles.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Debe completar el campo vacio", Toast.LENGTH_LONG).show();
        }
        else {

            db.insert(persona);
            espanol.setText("");
            ingles.setText("");
            Toast.makeText(getApplicationContext(), "La información se ha insertado con éxito", Toast.LENGTH_SHORT).show();
        }
    }
}
