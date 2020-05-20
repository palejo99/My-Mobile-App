package com.alejop.crudpersona.Administrador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alejop.crudpersona.R;
import com.alejop.crudpersona.database.DataBaseHandler;
import com.alejop.crudpersona.domain.Persona;
import com.alejop.crudpersona.globalstate.GlobalState;

public class EliminarUsuario extends AppCompatActivity {

    private Button eliminar;
    private EditText txtID;
    private TextView txtEliminar;
    private GlobalState globalState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_usuario);
        initComponents();

    }

    private void initComponents() {
        eliminar=(Button)findViewById(R.id.btnDelete);
        txtID=(EditText) findViewById(R.id.txtID);
        txtEliminar=(TextView) findViewById(R.id.txtEliminar);
        globalState =(GlobalState)getApplication();

    }

    public void onClickEliminar(View v){

        DataBaseHandler db = globalState.getDataBaseHandler();


        Persona persona = db.findByKey(txtID.getText().toString());

        if(persona != null){
            db.eliminar(txtID.getText().toString());
            Toast.makeText(getApplicationContext(), "La palabra ha sido eliminada exitosamente", Toast.LENGTH_SHORT).show();

        }
        else{

            Toast.makeText(getApplicationContext(), "No se encuentra información relacionada", Toast.LENGTH_SHORT).show();
        }

    }
}
