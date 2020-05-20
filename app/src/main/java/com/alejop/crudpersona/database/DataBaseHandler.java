package com.alejop.crudpersona.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import com.alejop.crudpersona.domain.Persona;
import com.alejop.crudpersona.globalstate.ListaPersona;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static android.R.attr.id;

/**
 * Created by Alejo on 17/12/2017.
 * Manejador de Base de Datos
 */

public class DataBaseHandler extends SQLiteOpenHelper{

    String sqlCreate = "CREATE TABLE persona (espanol TEXT, ingles TEXT)";

    public DataBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /*
    Se crea la Base de Datos
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS persona");
        db.execSQL(sqlCreate);
    }

    public void insert(Persona persona){
        SQLiteDatabase db = this.getWritableDatabase();
        UUID id = UUID.randomUUID();
        String sql = "INSERT INTO persona (espanol, ingles) VALUES ('" + persona.getEspanol() + "','" + persona.getIngles()+"')";
        db.execSQL(sql);
    }
/*
    public void update(String Salario,String Documento){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE persona SET saldo='"+Salario+"' WHERE documento='"+Documento+"' ");
        db.close();

        }
*/
   public void eliminar(String Espanol){
       SQLiteDatabase db = this.getWritableDatabase();
       db.execSQL("DELETE FROM persona  WHERE espanol='"+Espanol+"' OR ingles='"+Espanol+"' ");
       db.close();

   }
    public List <Persona> listAll(){
        List <Persona> listaPersona =new ArrayList<>();
        String sql ="SELECT * FROM persona";
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor= db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                int index =0;
                Persona persona = new Persona();
                persona.setEspanol(cursor.getString(index++));
                persona.setIngles(cursor.getString(index++));
                listaPersona.add(persona);


            }while(cursor.moveToNext());

        }
        return listaPersona;
    }


    public ArrayList<String> getAllNames(){
    // declare our return value
        ArrayList<String> allNames= new ArrayList<String>();

    // Generate a select statment.
    // String sql ="SELECT * FROM persona";
        SQLiteDatabase db =this.getReadableDatabase();


        String idioms= "select DISTINCT * FROM persona ";
      //  String sql ="SELECT * FROM persona";
        Cursor cursor= db.rawQuery(idioms,null);

    // Execute the query
    // Para tomar los resultados de esta consulta, se crea
    // un cursor, q itere sobre cada uno de estos elementos



    // do we have a result?
        if(cursor.getCount()>0){
    // move to the first result.
            cursor.moveToFirst();

    // iterate over the results as we have results

            while(!cursor.isAfterLast()){
    // add this name to the collection
                allNames.add(cursor.getString(0));
                cursor.moveToNext();

            }
        }

    // close the cursor.
        cursor.close();

        return allNames;
    }
      public Persona findByKey(String key){
          Persona persona =null;
          String sql ="select * from persona where (espanol='"+key+"' OR ingles='"+key+"')";
          SQLiteDatabase db = this.getWritableDatabase();
          Cursor cursor =db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            int index =0;
            persona = new Persona();
            persona.setEspanol(cursor.getString(index++));
            persona.setIngles(cursor.getString(index++));

        }
        return persona;

    }
}
