package com.upc.pichanguito;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.upc.pichanguito.entity.UserEntity;
import com.upc.pichanguito.repository.UserRepository;

public class UpdateUserActivity extends AppCompatActivity {

    EditText userNameText, passwordText, firstNameText, lastNameText, documentNumberText, ageText, heightText;
    Button saveButton;
    FloatingActionButton editFloatingButton, deleteFloatingButton;

    UserEntity userEntity;

    int user_id=0;

    boolean update = false;

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
        editFloatingButton.setVisibility(View.INVISIBLE);
        deleteFloatingButton = findViewById(R.id.deleteFloatingButton);
        deleteFloatingButton.setVisibility(View.INVISIBLE);

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

        UserRepository userRepository = new UserRepository(UpdateUserActivity.this);
        userEntity = userRepository.getUser(user_id);

        if (userEntity != null){
            userNameText.setText(userEntity.getUser_name());
            passwordText.setText(userEntity.getPassword());
            firstNameText.setText(userEntity.getFirst_name());
            lastNameText.setText(userEntity.getLast_name());
            documentNumberText.setText(userEntity.getDocument_number());
            ageText.setText(String.valueOf(userEntity.getAge()));
            heightText.setText(String.valueOf(userEntity.getHeight()));
        }

        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(!userNameText.getText().toString().equals("") && !passwordText.getText().toString().equals("")
                && !firstNameText.getText().toString().equals("") && !lastNameText.getText().toString().equals("")
                && !documentNumberText.getText().toString().equals("") && !ageText.getText().toString().equals("")
                && !heightText.getText().toString().equals("")){

                    userEntity.setUser_id(user_id);
                    userEntity.setUser_name(userNameText.getText().toString());
                    userEntity.setPassword(passwordText.getText().toString());
                    userEntity.setFirst_name(firstNameText.getText().toString());
                    userEntity.setLast_name(lastNameText.getText().toString());
                    userEntity.setDocument_number(documentNumberText.getText().toString());
                    userEntity.setAge(Integer.parseInt(ageText.getText().toString()));
                    userEntity.setHeight(Double.parseDouble(heightText.getText().toString()));

                    update = userRepository.updateUser(userEntity);

                    if (update == true){
                        AlertDialog.Builder message = new AlertDialog.Builder(UpdateUserActivity.this);
                        message.setTitle("Informativo");
                        message.setMessage("El usuario se actualizó correctamente.");
                        message.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                seeRecord();
                            }
                        }).show();


                    }else{
                        AlertDialog.Builder message = new AlertDialog.Builder(UpdateUserActivity.this);
                        message.setTitle("Informativo");
                        message.setMessage("El usuario NO se actualizó.");
                        message.setPositiveButton("Aceptar", null);
                        message.create().show();
                    }

                }else{
                    Toast.makeText(UpdateUserActivity.this, "Debe llenar los campos obligatorios", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void seeRecord(){
        Intent intent = new Intent(this, SeeUserActivity.class);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }
}