package com.alejop.crudpersona.globalstate;

import android.app.Application;

import com.alejop.crudpersona.database.DataBaseHandler;

/**
 * Created by Alejo
 */

public class GlobalState extends Application{

    private DataBaseHandler dataBaseHandler;

    public DataBaseHandler getDataBaseHandler() {

        return dataBaseHandler;
    }

    public void setDataBaseHandler(DataBaseHandler dataBaseHandler) {
        this.dataBaseHandler = dataBaseHandler;
    }
}
