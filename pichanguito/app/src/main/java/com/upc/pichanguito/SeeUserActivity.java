package com.upc.pichanguito;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.upc.pichanguito.entity.UserEntity;
import com.upc.pichanguito.repository.UserRepository;

public class SeeUserActivity extends AppCompatActivity {

    EditText userNameText, passwordText, firstNameText, lastNameText, documentNumberText, ageText, heightText;
    Button saveButton;
    FloatingActionButton editFloatingButton, deleteFloatingButton;

    UserEntity userEntity;

    int user_id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see);

        userNameText = findViewById(R.id.userNameText);
        passwordText = findViewById(R.id.passwordText);
        firstNameText = findViewById(R.id.firstNameText);
        lastNameText = findViewById(R.id.lastNameText);
        documentNumberText = findViewById(R.id.documentNumberText);
        ageText = findViewById(R.id.ageText);
        heightText = findViewById(R.id.heightText);
        saveButton= findViewById(R.id.saveButton);
        editFloatingButton = findViewById(R.id.editFloatingButton);
        deleteFloatingButton = findViewById(R.id.deleteFloatingButton);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();

            if (extras == null){
                user_id = Integer.parseInt(null);

            }else{
                user_id = extras.getInt("user_id");
            }
        }else{
            user_id = (int) savedInstanceState.getSerializable("user_id");
        }

        UserRepository userRepository = new UserRepository(SeeUserActivity.this);
        userEntity = userRepository.getUser(user_id);

        if (userEntity != null){
            userNameText.setText(userEntity.getUser_name());
            passwordText.setText(userEntity.getPassword());
            firstNameText.setText(userEntity.getFirst_name());
            lastNameText.setText(userEntity.getLast_name());
            documentNumberText.setText(userEntity.getDocument_number());
            ageText.setText(String.valueOf(userEntity.getAge()));
            heightText.setText(String.valueOf(userEntity.getHeight()));
            saveButton.setVisibility(View.INVISIBLE);

            userNameText.setInputType(InputType.TYPE_NULL);
            passwordText.setInputType(InputType.TYPE_NULL);
            firstNameText.setInputType(InputType.TYPE_NULL);
            lastNameText.setInputType(InputType.TYPE_NULL);
            documentNumberText.setInputType(InputType.TYPE_NULL);
            ageText.setInputType(InputType.TYPE_NULL);
            heightText.setInputType(InputType.TYPE_NULL);
        }

        editFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeeUserActivity.this, UpdateUserActivity.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });

        deleteFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SeeUserActivity.this);
                builder.setMessage("¿Desea eliminar este usuario?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (userRepository.deleteteUser(user_id)){
                                    mainReturn();
                                }
                            }
                        })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });
    }
    private void mainReturn(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}