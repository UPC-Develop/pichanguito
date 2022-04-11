package com.upc.pichanguito;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.upc.pichanguito.entity.UserEntity;
import com.upc.pichanguito.repository.UserRepository;

import java.text.ParseException;

public class CreateUserActivity extends AppCompatActivity {

    EditText userNameText, passwordText, firstNameText, lastNameText, documentNumberText, ageText, heightText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        userNameText = findViewById(R.id.userNameText);
        passwordText = findViewById(R.id.passwordText);
        firstNameText = findViewById(R.id.firstNameText);
        lastNameText = findViewById(R.id.lastNameText);
        documentNumberText = findViewById(R.id.documentNumberText);
        ageText = findViewById(R.id.ageText);
        heightText = findViewById(R.id.heightText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                UserRepository userRepository = new UserRepository(CreateUserActivity.this);

                if(!userNameText.getText().toString().equals("") && !passwordText.getText().toString().equals("")
                        && !firstNameText.getText().toString().equals("") && !lastNameText.getText().toString().equals("")
                        && !documentNumberText.getText().toString().equals("") && !ageText.getText().toString().equals("")
                        && !heightText.getText().toString().equals("")){

                    UserEntity userEntity = new UserEntity();

                    userEntity.setUser_name(userNameText.getText().toString());
                    userEntity.setPassword(passwordText.getText().toString());
                    userEntity.setFirst_name(firstNameText.getText().toString());
                    userEntity.setLast_name(lastNameText.getText().toString());
                    userEntity.setDocument_number(documentNumberText.getText().toString());
                    userEntity.setAge(Integer.parseInt(ageText.getText().toString()));
                    userEntity.setHeight(Double.parseDouble(heightText.getText().toString()));


                    userRepository.createUser(userEntity);

                    AlertDialog.Builder message = new AlertDialog.Builder(CreateUserActivity.this);
                    message.setTitle("Informativo");
                    message.setMessage("El usuario se creo correctamente.");
                    message.setPositiveButton("Aceptar", null);
                    message.create().show();
                }else{
                    Toast.makeText(CreateUserActivity.this, "Debe llenar los campos obligatorios", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    private void captureData() throws ParseException {


    }
}