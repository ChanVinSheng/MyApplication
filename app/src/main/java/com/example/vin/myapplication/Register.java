package com.example.vin.myapplication;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity implements View.OnClickListener {

    Button buttonRegister;
    EditText txtusername;
    EditText txtemail;
    EditText txtpassword;
    EditText txtconfirmedPassword;
    ProgressBar progressBar;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        txtusername = findViewById(R.id.txtRegisterUsername);
        txtemail= findViewById(R.id.txtRegisterEmail);
        txtpassword = findViewById(R.id.txtRegisterPassword);
        txtconfirmedPassword= findViewById(R.id.txtRegisterConPassword);
        buttonRegister  = findViewById(R.id.btnRegisterRegister);
        progressBar = findViewById(R.id.registerProgressBar);
        buttonRegister.setOnClickListener(this);

    }

    public void registerUser() {
        final String email = txtemail.getText().toString().trim();
        final String username = txtusername.getText().toString().trim();
        final String password = txtpassword.getText().toString().trim();

        boolean fieldsOK = validate(new EditText[] { txtusername, txtemail, txtpassword,txtconfirmedPassword });
        if(fieldsOK){
            if(!isValidEmail(txtemail.getText().toString().trim())){
                txtemail.setError("Invalid Email");
                txtemail.setText("");
                txtemail.requestFocus();
            }
            else if(!txtconfirmedPassword.getText().toString().trim().equals(txtpassword.getText().toString().trim())){

                txtconfirmedPassword.setError("Password Not same");
                txtconfirmedPassword.setText("");
                txtconfirmedPassword.requestFocus();
            }
            else if(txtpassword.getText().toString().length() < 6){
                txtpassword.setError("Minimum length of password should be 6 ");
                txtpassword.setText("");
                txtpassword.requestFocus();
                txtconfirmedPassword.setText("");

            }
            else{
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    User user = new User(username,email,password);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(Register.this, "Register Successfully", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                    Toast.makeText(Register.this, "Register Successfully", Toast.LENGTH_LONG).show();
                                }else{

                                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                        Toast.makeText(Register.this, "This email already exist", Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(Register.this, "Register fail... please try again", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });
            }
        }
        else{
            Toast.makeText(Register.this, "Text Box is empty", Toast.LENGTH_LONG).show();
        }


    }

    public void onClick(View view){
        if(view == buttonRegister){
            registerUser();
        }
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

    public boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }



}
