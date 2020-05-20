package com.alejop.crudpersona;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.felipecsl.gifimageview.library.GifImageView;
import com.alejop.crudpersona.Administrador.EliminarUsuario;
import com.alejop.crudpersona.database.DataBaseHandler;
import com.alejop.crudpersona.globalstate.BuscarPersona;
import com.alejop.crudpersona.globalstate.GlobalState;
import com.alejop.crudpersona.globalstate.ListaPersona;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class DashBoard extends AppCompatActivity {

    private Button btnListar;
    private Button btnIngresar;
    private Button btnConsultar;
    private Button btnEliminar;
    private DataBaseHandler db;
    private GlobalState globalState;
    private ListaPersona persona;
    private GifImageView gifImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        initComponents();
        configureBtnIngresar();
        configureBtnListar();
        configureBtnConsultar();

    }

    private void configureBtnConsultar() {
        btnConsultar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DashBoard.this,BuscarPersona.class);
                startActivity(intent);
            }
        });

    }

    private void configureBtnIngresar() {
        btnIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DashBoard.this,IngresoPersona.class);
                startActivity(intent);
            }
        });
    }

  public void onClickEliminar(View v){
      Intent intent = new Intent(DashBoard.this,EliminarUsuario.class);
      startActivity(intent);

 }
    private void configureBtnListar() {
        btnListar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DashBoard.this,ListaPersona.class);
                startActivity(intent);
            }
        });
    }


    private void initComponents() {
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnListar = (Button) findViewById(R.id.btnListar);
        btnConsultar = (Button) findViewById(R.id.btnConsultar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        globalState = (GlobalState) getApplication();
        db = new DataBaseHandler(this, "Registro", null, 1); //Creación de la BD
        globalState.setDataBaseHandler(db); //Se envía la instancia de la BD al global State para almacenar
        gifImageView=(GifImageView)findViewById(R.id.gifImageView);
        //Mirar esta línea
        gifImageView.setBackgroundResource(0);

        // Se abre el gif animado de la carpeta creada nombrada assets
        try {
            InputStream inputStream= getAssets().open("sasuke.gif");
            // No olvidar importar las dependencias en el build.gradle, ya que allí es donde se añade la librería
            byte[] bytes= IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();



        }
        catch(IOException ex){

        }

    }

}


