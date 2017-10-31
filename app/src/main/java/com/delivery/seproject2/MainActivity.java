package com.delivery.seproject2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    CallbackManager callbackManager;
    LoginButton loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        EditText et1 = (EditText) findViewById(R.id.editText);
        EditText et2 = (EditText) findViewById(R.id.editText2);

        Button bt1 = (Button) findViewById(R.id.button2);
        //  FirebaseAuth auth;
        String username = et1.getText().toString().trim();
        String password = et2.getText().toString().trim();
        loginButton = (LoginButton) findViewById(R.id.login_button);
         setContentView(R.layout.activity_main);
         FacebookSdk.sdkInitialize(getApplicationContext());
         callbackManager = CallbackManager.Factory.create();
         loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
             @Override
             public void onSuccess(LoginResult loginResult) {
                 Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG).show();
             }

             @Override
             public void onCancel() {
                 Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
             }

             @Override
             public void onError(FacebookException error) {

             }
         });
    }
    public void login(View v){
        EditText et1 = (EditText) findViewById(R.id.editText);
        EditText et2 = (EditText) findViewById(R.id.editText2);
        String username = et1.getText().toString().trim();
        String password = et2.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"login success",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this,home.class);
                    startActivity(i);

                }
                else{
                    Log.e("error",task.getException().toString());
                    Toast.makeText(MainActivity.this,"can't",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void create(View v){
        Intent i = new Intent(MainActivity.this,create.class);
        startActivity(i);

    }


    protected void onActivityResult(int requestCode,int resultCode,Intent data){

        callbackManager.onActivityResult(requestCode,resultCode,data);

    }

}
