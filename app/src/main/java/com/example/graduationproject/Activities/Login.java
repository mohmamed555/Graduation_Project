package com.example.graduationproject.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduationproject.R;
import com.example.graduationproject.Utils.MyUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    FirebaseAuth auth ;

    EditText email_login , pass_login ;

    Button btn_login ;

    TextView btn_register , header  ;

    LinearLayout parent  ;

    ProgressBar progressBar ;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        definitions();
        seadDataAuto();
        onClick();



    }

    private void onClick (){


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email_login.getText().toString().trim().equals("admin123@gmail.com") &&
                        pass_login.getText().toString().trim().equals("123000789")){

                    startActivity(new Intent(Login.this, Admin.class));
                    finish();

                }else {

                    login();
                }

            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });


    }

    private void definitions(){
        auth = FirebaseAuth.getInstance();
        email_login = findViewById(R.id.enter_email);
        pass_login = findViewById(R.id.enter_pass);
        btn_register = findViewById(R.id.register_btn);
        btn_login = findViewById(R.id.login_btn);

        parent = findViewById(R.id.parentOfLogin);

        progressBar = findViewById(R.id.progressOfLogin);

        header = findViewById(R.id.headertext);




    }

    private void login(){

        String account = email_login.getText().toString();
        String pass = pass_login.getText().toString();

        if(account.length()> 0  && pass.length()>0) {

               progressBar.setVisibility(View.VISIBLE);
               parent.setVisibility(View.GONE);
               header.setVisibility(View.GONE);
            btn_register.setVisibility(View.GONE);

            auth.signInWithEmailAndPassword(account , pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){

                        startActivity(new Intent(Login.this , Home.class));
                        finish();



                    }else {
                        progressBar.setVisibility(View.GONE);
                        parent.setVisibility(View.VISIBLE);
                        header.setVisibility(View.VISIBLE);
                        btn_register.setVisibility(View.VISIBLE);

                        email_login.setError("please check your email ");
                        pass_login.setError("please check your password");
                        Toast.makeText(getApplicationContext(), "email or password is wrong ", Toast.LENGTH_LONG).show();

                    }
                }
            });

        }else{
            email_login.setError("please enter your email ");
            pass_login.setError("please enter your password");
            email_login.setHintTextColor(Color.RED);
            pass_login.setHintTextColor(Color.RED);


        }

    }


    private void  seadDataAuto(){


        if(!MyUtils.mailforLogin.equals("")){
            email_login.setText(MyUtils.mailforLogin);
            pass_login.setText(MyUtils.passwordforLogin);



        }



    }


}

