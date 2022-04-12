package com.upc.pichanguito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class ResetUserPasswordActivity extends AppCompatActivity {

    EditText useremailEditText;
    Button resetPasswordButton;
    TextView returnSignInTextView;

    AwesomeValidation awesomeValidation;
    FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_user_password);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        useremailEditText = findViewById(R.id.useremailEditText);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);
        returnSignInTextView = findViewById(R.id.returnSignInTextView);

        awesomeValidation.addValidation(this, R.id.useremailEditText, Patterns.EMAIL_ADDRESS, R.string.invalid_useremail);


        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (awesomeValidation.validate())
                {
                    progressDialog.setMessage("Espere un momento...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    resetPassword();
                }else{
                    Toast.makeText(ResetUserPasswordActivity.this, "Debe ingresar un email valido.", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }
        });

        returnSignInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSignIn();
            }
        });

    }

    private void resetPassword(){

        firebaseAuth.setLanguageCode("es");
        firebaseAuth.sendPasswordResetEmail(useremailEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ResetUserPasswordActivity.this, "Se ha enviado un correo para restablecer tu contraseña.", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                    getError(errorCode);
                }

            }
        });
    }

    private void getError(String error){

        switch (error){
            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(ResetUserPasswordActivity.this, "El formato del token personalizado es incorrecto. Por favor revise la documentación", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(ResetUserPasswordActivity.this, "El token personalizado corresponde a una audiencia diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_CREDENTIAL":
                Toast.makeText(ResetUserPasswordActivity.this, "La credencial de autenticación proporcionada tiene un formato incorrecto o ha caducado.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_EMAIL":
                Toast.makeText(ResetUserPasswordActivity.this, "La dirección de correo electrónico está mal formateada.", Toast.LENGTH_LONG).show();
                useremailEditText.setError("La dirección de correo electrónico está mal formateada.");
                useremailEditText.requestFocus();
                break;

            case "ERROR_WRONG_PASSWORD":
                Toast.makeText(ResetUserPasswordActivity.this, "La contraseña no es válida o el usuario no tiene contraseña.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_MISMATCH":
                Toast.makeText(ResetUserPasswordActivity.this, "Las credenciales proporcionadas no corresponden al usuario que inició sesión anteriormente..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_REQUIRES_RECENT_LOGIN":
                Toast.makeText(ResetUserPasswordActivity.this,"Esta operación es sensible y requiere autenticación reciente. Inicie sesión nuevamente antes de volver a intentar esta solicitud.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                Toast.makeText(ResetUserPasswordActivity.this, "Ya existe una cuenta con la misma dirección de correo electrónico pero diferentes credenciales de inicio de sesión. Inicie sesión con un proveedor asociado a esta dirección de correo electrónico.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_EMAIL_ALREADY_IN_USE":
                Toast.makeText(ResetUserPasswordActivity.this, "La dirección de correo electrónico ya está siendo utilizada por otra cuenta..   ", Toast.LENGTH_LONG).show();
                useremailEditText.setError("La dirección de correo electrónico ya está siendo utilizada por otra cuenta.");
                useremailEditText.requestFocus();
                break;

            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                Toast.makeText(ResetUserPasswordActivity.this, "Esta credencial ya está asociada con una cuenta de usuario diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_DISABLED":
                Toast.makeText(ResetUserPasswordActivity.this, "La cuenta de usuario ha sido inhabilitada por un administrador..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_TOKEN_EXPIRED":
                Toast.makeText(ResetUserPasswordActivity.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(ResetUserPasswordActivity.this, "No hay ningún registro de usuario que corresponda a este identificador. Es posible que se haya eliminado al usuario.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_USER_TOKEN":
                Toast.makeText(ResetUserPasswordActivity.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_OPERATION_NOT_ALLOWED":
                Toast.makeText(ResetUserPasswordActivity.this, "Esta operación no está permitida. Debes habilitar este servicio en la consola.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_WEAK_PASSWORD":
                Toast.makeText(ResetUserPasswordActivity.this, "La contraseña proporcionada no es válida..", Toast.LENGTH_LONG).show();
                break;
        }

    }

    private void setSignIn(){

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}