package com.example.vin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private Button buttonReigster;
    private EditText txtEmail;
    private EditText txtPassword;
    private TextView txtRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtRegister = (TextView)findViewById(R.id.txtLoginRegister);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

    }
}
