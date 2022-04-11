package com.upc.pichanguito.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.upc.pichanguito.database.HelperDatabase;
import com.upc.pichanguito.entity.UserEntity;

import java.util.ArrayList;

public class UserRepository extends HelperDatabase {

    Context context;

    public UserRepository(@Nullable Context context) {
        super(context);
        this.context = context;
    }



    public long createUser(UserEntity userEntity){

        HelperDatabase helperDatabase = new HelperDatabase(context);
        SQLiteDatabase db = helperDatabase.getWritableDatabase();

        long user_id = 0;

        try {

            ContentValues values = new ContentValues();
            values.put("user_name", userEntity.getUser_name());
            values.put("password", userEntity.getPassword());
            values.put("first_name", userEntity.getFirst_name());
            values.put("last_name", userEntity.getLast_name());
            values.put("document_number", userEntity.getDocument_number());
            values.put("age", userEntity.getAge());
            values.put("height", userEntity.getHeight());

            user_id = db.insert(DATABASE_TABLE_USER, null,  values);

        }catch (Exception ex){
            Log.d("message", "Error: " + ex.getMessage());
        }

        return user_id;
    }

    public ArrayList<UserEntity> getUserAll(){
        HelperDatabase helperDatabase = new HelperDatabase(context);
        SQLiteDatabase db = helperDatabase.getWritableDatabase();
        ArrayList<UserEntity> userEntities = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT user_id, user_name, first_name, last_name, document_number FROM " + DATABASE_TABLE_USER, null);

        if (cursor.moveToFirst()){
            do{
                UserEntity userEntity = new UserEntity();
                userEntity.setUser_id(cursor.getInt(0));
                userEntity.setUser_name(cursor.getString(1));
                userEntity.setFirst_name(cursor.getString(2));
                userEntity.setLast_name(cursor.getString(3));
                userEntity.setDocument_number(cursor.getString(4));
                userEntities.add(userEntity);
            }while (cursor.moveToNext());
        }

        cursor.close();

        return  userEntities;
    }

    public UserEntity getUser(int user_id){
        HelperDatabase helperDatabase = new HelperDatabase(context);
        SQLiteDatabase db = helperDatabase.getWritableDatabase();
        UserEntity userEntity = new UserEntity();

        Cursor cursor = db.rawQuery("SELECT user_id, user_name, password , first_name, last_name, document_number, age, height " +
                "FROM " + DATABASE_TABLE_USER + " WHERE user_id=" + user_id , null);

        if (cursor.moveToFirst()){

            userEntity.setUser_id(cursor.getInt(0));
            userEntity.setUser_name(cursor.getString(1));
            userEntity.setPassword(cursor.getString(2));
            userEntity.setFirst_name(cursor.getString(3));
            userEntity.setLast_name(cursor.getString(4));
            userEntity.setDocument_number(cursor.getString(5));
            userEntity.setAge(cursor.getInt(6));
            userEntity.setHeight(cursor.getDouble(7));

        }

        cursor.close();

        return  userEntity;
    }

    public boolean updateUser(UserEntity userEntity){

        HelperDatabase helperDatabase = new HelperDatabase(context);
        SQLiteDatabase db = helperDatabase.getWritableDatabase();

        boolean update = false;

        try {

            ContentValues values = new ContentValues();
            values.put("user_name", userEntity.getUser_name());
            values.put("password", userEntity.getPassword());
            values.put("first_name", userEntity.getFirst_name());
            values.put("last_name", userEntity.getLast_name());
            values.put("document_number", userEntity.getDocument_number());
            values.put("age", userEntity.getAge());
            values.put("height", userEntity.getHeight());

            db.execSQL("UPDATE " + DATABASE_TABLE_USER + " SET user_name='" + userEntity.getUser_name() +
                    "', password='" + userEntity.getPassword() + "', first_name='" + userEntity.getFirst_name() +
                    "', last_name='" + userEntity.getLast_name() + "', document_number='" + userEntity.getDocument_number() +
                    "', age=" + userEntity.getAge() + ", height=" + userEntity.getHeight() + " WHERE user_id=" + userEntity.getUser_id());

            update= true;
        }catch (Exception ex){
            update=false;
            Log.d("message update", "Error: " + ex.getMessage());
        }finally {
            db.close();
        }

        return update;
    }

    public boolean deleteteUser(int userId){

        HelperDatabase helperDatabase = new HelperDatabase(context);
        SQLiteDatabase db = helperDatabase.getWritableDatabase();

        boolean delete = false;

        try {

            db.execSQL("DELETE FROM " + DATABASE_TABLE_USER + " WHERE user_id =" + userId);

            delete= true;
        }catch (Exception ex){
            delete=false;
            Log.d("message delete", "Error: " + ex.getMessage());
        }finally {
            db.close();
        }

        return delete;
    }

}
