package com.upc.pichanguito.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Esquema extends SQLiteOpenHelper {

    public  Esquema (Context context){
        super(context, Constantes.NOMBRE_BD, null, Constantes.VERSION );

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query =
                "CREATE TABLE "+Constantes.NOMBRE_TABLA+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, "+
                "codigo INTEGER NOT NULL, "+
                "descripcion TEXT NOT NULL, "+
                "ancho INTEGER NOT NULL, "+
                "largo INTEGER NOT NULL, "+
                "precio INTEGER NOT NULL, "+
                "cambio INTEGER NOT NULL, "+
                "categoria TEXT NOT NULL, "+
                "estado TEXT NOT NULL, "+
                "sede TEXT NOT NULL);";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
