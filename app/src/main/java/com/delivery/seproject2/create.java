package com.delivery.seproject2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.delivery.seproject2.Data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class create extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("user");
    private ProgressDialog progressDialog;
    private int progressPercentage = 0;
    private FirebaseAuth mAuth;




    private DatabaseReference mDatabase;
    private String passwords;
// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        //mDatabase = FirebaseDatabase.getInstance().getReference();
        //mDatabase.child("users").child("waithiid").setValue("fuck");
       // User u = new User();
       // u.setUser("waithid");
       // u.setTel("0933943986");
       // String key = mDatabase.child("users").push().getKey();
       // mDatabase.child("users").push().setValue(u);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    protected void create_account(View v){

        EditText email = (EditText) findViewById(R.id.email);
        EditText pass = (EditText) findViewById(R.id.passwords);
        EditText tel = (EditText) findViewById(R.id.tel);
        RadioGroup gendar = (RadioGroup) findViewById(R.id.radioGroup2);
        User u = new User();
        u.setUser(email.getText().toString().trim());
        u.setTel(tel.getText().toString().trim());
        int selectid = gendar.getCheckedRadioButtonId();
        RadioButton gen = (RadioButton) findViewById(selectid);
        u.setGendar(gen.getText().toString());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = mDatabase.child("users").push().getKey();
        mDatabase.child("users").push().setValue(u);


        mAuth = FirebaseAuth.getInstance();

        String em = email.getText().toString().trim();
        String pas = pass.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(em,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(create.this,"create success",Toast.LENGTH_LONG).show();

                }
                else{
                    Log.e("error",task.getException().toString());
                    Toast.makeText(create.this,"can't",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}
