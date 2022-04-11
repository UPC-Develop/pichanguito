package com.upc.pichanguito.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HelperDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=2;
    public static final String DATABASE_NAME="security.db";
    public static final String DATABASE_TABLE_USER="user";

    public HelperDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + DATABASE_TABLE_USER + "(" +
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_name TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "first_name TEXT NOT NULL," +
                "last_name TEXT NOT NULL," +
                "document_number TEXT NOT NULL," +
                "age INTEGER NOT NULL," +
                "height DOUBLE NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + DATABASE_TABLE_USER);
        onCreate(sqLiteDatabase);
    }
}
