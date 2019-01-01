package com.example.vin.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vin.myapplication.Login.Login;
import com.example.vin.myapplication.Report.Report;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase mFirebaseDatabase;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference myRef;
    String userID;
    ImageView imgUserProfile;

    private DatabaseReference mDatabaseUser_name,mDatabaseUser_email, mDatabaseUser_image;

    private DrawerLayout drawer;
    TextView txtUsername;
    TextView txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //firebase
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("Users");
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        userID = firebaseUser.getUid();



        //navigation
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        txtUsername = headerView.findViewById(R.id.HeaderUsername);
        txtEmail = headerView.findViewById(R.id.HeaderEmail);
        imgUserProfile = headerView.findViewById(R.id.HeaderImage);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mDatabaseUser_name = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("username");
        mDatabaseUser_email = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("email");
        mDatabaseUser_image = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("imageURL");

        mDatabaseUser_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtUsername.setText(dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabaseUser_email.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtEmail.setText(dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mDatabaseUser_image.addChildEventListener((new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                FirebaseUser url = mAuth.getCurrentUser();
                if(url.getPhotoUrl() != null){
                    Glide.with(navigationView).load(url.getPhotoUrl().toString()).into(imgUserProfile);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                FirebaseUser url = mAuth.getCurrentUser();
                if(url.getPhotoUrl() != null){
                    Glide.with(navigationView).load(url.getPhotoUrl().toString()).into(imgUserProfile);
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }));
//        mDatabaseUser_image.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                FirebaseUser url = mAuth.getCurrentUser();
//                if(url.getPhotoUrl() != null){
//                    Glide.with(navigationView).load(url.getPhotoUrl().toString()).into(imgUserProfile);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Report()).commit();
            navigationView.setCheckedItem(R.id.nav_report);
        }


        if(firebaseUser == null){
            Intent intent = new Intent(this , Login.class);
            startActivity(intent);
            finish();
        }


    }



//    private void showData(DataSnapshot dataSnapshot) {
//
//        for(DataSnapshot ds:dataSnapshot.getChildren()){
//
//            Log.d("SHOWMETHEEEROR",test.toString());
//            User user = new User();
//            user.setEmail(ds.child(userID).getValue(User.class).getEmail());
//            user.setUsername(ds.child(userID).getValue(User.class).getUsername());
//
//            txtUsername.setText(user.getUsername());
//            txtEmail.setText(user.getEmail());
//        }
//    }


    //change to fragment base on what the user click
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        NavigationView navigationView = findViewById(R.id.nav_view);
        switch (menuItem.getItemId()){
            case R.id.nav_logout:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this,Login.class));
                break;

            case R.id.nav_report:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Report()).commit();
                navigationView.setCheckedItem(R.id.nav_report);

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}
