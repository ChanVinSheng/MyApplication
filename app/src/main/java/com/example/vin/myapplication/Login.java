package com.example.vin.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogin;
    private EditText txtEmail;
    private EditText txtPassword;
    private TextView txtRegister;
    FirebaseUser firebaseUser;
    ProgressBar progressBar;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        txtEmail = findViewById(R.id.txtLoginEmail);
        txtPassword = findViewById(R.id.txtLoginPassword);
        txtRegister = findViewById(R.id.txtLoginRegister);
        buttonLogin = findViewById(R.id.btnLoginLogin);
        progressBar = findViewById(R.id.LoginProgressBar);
        txtRegister.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);

        if(firebaseUser != null){
            Intent intent = new Intent(Login.this , MainMenu.class);
            startActivity(intent);
            finish();
        }
    }

    public void onClick(View view) {
        if(view == txtRegister){
            startActivity(new Intent(Login.this, Register.class));
        }
        else if(view == buttonLogin){
            userLogin();
        }

    }

    public void userLogin() {
        final String email = txtEmail.getText().toString().trim();
        final String password = txtPassword.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);
        boolean fieldsOK = validate(new EditText[]{txtEmail, txtPassword});
        if (fieldsOK) {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(Login.this , MainMenu.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Email or Password is wrong", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else{
            Toast.makeText(Login.this, "Text Box is empty", Toast.LENGTH_LONG).show();
        }
        progressBar.setVisibility(View.GONE);
    }

        private boolean validate(EditText[] fields){
            for(int i = 0; i < fields.length; i++){
                EditText currentField = fields[i];
                if(currentField.getText().toString().length() <= 0){
                    return false;
                }
            }
            return true;
        }
}
