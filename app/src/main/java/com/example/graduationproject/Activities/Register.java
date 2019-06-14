package com.example.graduationproject.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.graduationproject.R;
import com.example.graduationproject.Utils.MyUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText email_register, pass_register, repass, user_name;
    Button btn_register;
    FirebaseAuth auth;

    LinearLayout parent ;
    ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        definitions();



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });


    }

    private void definitions(){

        auth = FirebaseAuth.getInstance();

        email_register = findViewById(R.id.enter_email_register);
        pass_register = findViewById(R.id.enter_pass_register);
        repass = findViewById(R.id.enter_repass);
        btn_register = findViewById(R.id.to_login);
        user_name = findViewById(R.id.enter_userName);

        progressBar = findViewById(R.id.progressOfRejester);

        parent = findViewById(R.id.parentOfRejester);


    }

    private void register() {

        String email = email_register.getText().toString().trim();
        String password = pass_register.getText().toString().trim();
        String repassword = repass.getText().toString().trim();
        String userName = user_name.getText().toString().trim();

        if (userName.equals("")){
            user_name.setError("Required");




        }else if(email.equals("")){
            email_register.setError("Required");


        }else if(password.equals("")){
            pass_register.setError("Required");


        }else if(password.length() < 6){
            pass_register.setError("password should be more than 5 characters");



        }
        else if(!password.equals(repassword)){
            repass.setError("password is not equals re-password");

        }else {
            parent.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            actionOfLogin(email,password);
        }





    }

    private void actionOfLogin(final String email , final String password){

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Register is Succeed", Toast.LENGTH_LONG).show();

                            startActivity(new Intent( Register.this , Login.class));
                            finish();

                            MyUtils.mailforLogin= email ;
                            MyUtils.passwordforLogin = password  ;


                        } else {

                            parent.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            email_register.setError("please check your email ");


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register.this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });

    }
}