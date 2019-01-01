package com.example.vin.myapplication.Login;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vin.myapplication.MainMenu;
import com.example.vin.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class Register extends AppCompatActivity implements View.OnClickListener {

    Button buttonRegister;
    EditText txtusername;
    EditText txtemail;
    EditText txtpassword;
    EditText txtconfirmedPassword;
    ImageView ImguserPhoto;
    static int PReqCode = 1;
    static int REQUESCODE = 1;
    Uri pickUrlImage;
    ProgressBar progressBar;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        txtusername = findViewById(R.id.txtRegisterUsername);
        txtemail = findViewById(R.id.txtRegisterEmail);
        txtpassword = findViewById(R.id.txtRegisterPassword);
        txtconfirmedPassword = findViewById(R.id.txtRegisterConPassword);
        buttonRegister = findViewById(R.id.btnRegisterRegister);
        progressBar = findViewById(R.id.registerProgressBar);
        buttonRegister.setOnClickListener(this);
        ImguserPhoto = findViewById(R.id.Regiter_image);

        ImguserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 21) {
                    checkAndRequestForPermission();
                } else {
                    openGallery();
                }
            }
        });

    }

    private void openGallery() {


        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESCODE);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {
            pickUrlImage = data.getData();
            ImguserPhoto.setImageURI(pickUrlImage);
        }
    }


    private void checkAndRequestForPermission() {


        if (ContextCompat.checkSelfPermission(Register.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Register.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(Register.this, "Please accept for required permission", Toast.LENGTH_SHORT).show();

            } else {
                ActivityCompat.requestPermissions(Register.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }

        } else
            openGallery();

    }

    public void registerUser() {
        final String email = txtemail.getText().toString().trim();
        final String username = txtusername.getText().toString().trim();
        final String password = txtpassword.getText().toString().trim();

        boolean fieldsOK = validate(new EditText[]{txtusername, txtemail, txtpassword, txtconfirmedPassword});
        if (fieldsOK) {
            if (!isValidEmail(txtemail.getText().toString().trim())) {
                txtemail.setError("Invalid Email");
                txtemail.setText("");
                txtemail.requestFocus();
            } else if (!txtconfirmedPassword.getText().toString().trim().equals(txtpassword.getText().toString().trim())) {

                txtconfirmedPassword.setError("Password Not same");
                txtconfirmedPassword.setText("");
                txtconfirmedPassword.requestFocus();
            } else if (txtpassword.getText().toString().length() < 6) {
                txtpassword.setError("Minimum length of password should be 6 ");
                txtpassword.setText("");
                txtpassword.requestFocus();
                txtconfirmedPassword.setText("");

            } else {
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    updateUserInfo(username,email,pickUrlImage,mAuth.getCurrentUser());

                                } else {
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                        Toast.makeText(Register.this, "This email already exist", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(Register.this, "Register fail... please try again", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });
            }
        } else {
            Toast.makeText(Register.this, "Text Box is empty", Toast.LENGTH_LONG).show();
        }


    }



    private void updateUserInfo(final String username, final String email , Uri pickedImgUri, final FirebaseUser currentUser) {

            // first we need to upload user photo to firebase storage and get url

            StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");

        final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());

        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    // image uploaded succesfully
                    // now we can get our image url

                    imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            User user = new User(username, email,uri.toString());

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        Log.d("PICTURECHECK" , imageFilePath.getDownloadUrl().toString());

                                        finish();
                                        Intent intent = new Intent(Register.this, Login.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        Toast.makeText(Register.this, "Register Successfully", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                            UserProfileChangeRequest profleUpdate = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(username)
                                    .setPhotoUri(uri)
                                    .build();


                            currentUser.updateProfile(profleUpdate)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                // user info updated successfully
                                                showMessage("Register Complete");
                                            }

                                        }
                                    });

                        }
                    });


                }
            });


    }

    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }


    public void onClick(View view) {
        if (view == buttonRegister) {
            registerUser();
        }
    }

    private boolean validate(EditText[] fields) {
        for (int i = 0; i < fields.length; i++) {
            EditText currentField = fields[i];
            if (currentField.getText().toString().length() <= 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


}
