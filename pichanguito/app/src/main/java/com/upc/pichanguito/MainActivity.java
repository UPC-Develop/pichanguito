package com.upc.pichanguito;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.upc.pichanguito.adapter.UserEntityAdapter;
import com.upc.pichanguito.entity.UserEntity;
import com.upc.pichanguito.repository.UserRepository;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Button createButton;
    RecyclerView listItemView;
    ArrayList<UserEntity> userEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //createButton = findViewById(R.id.CreateButton);

        listItemView = findViewById(R.id.listItemView);

        listItemView.setLayoutManager(new LinearLayoutManager(this));

        UserRepository userRepository = new UserRepository(MainActivity.this);

        userEntities = new ArrayList<>();

        UserEntityAdapter adapter = new UserEntityAdapter(userRepository.getUserAll());

        listItemView.setAdapter(adapter);

        /*createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HelperDatabase helperDatabase = new HelperDatabase(MainActivity.this);

                SQLiteDatabase db = helperDatabase.getWritableDatabase();
                if (db != null){
                    Toast.makeText(MainActivity.this, "¡La db se creo satisfactoriamente!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "¡Error al crear La db!", Toast.LENGTH_LONG).show();
                }

            }
        });*/

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_option, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.addMenu:
                addNewItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void addNewItem(){
        Intent intent = new Intent(this, CreateUserActivity.class);
        startActivity(intent);
    }

}