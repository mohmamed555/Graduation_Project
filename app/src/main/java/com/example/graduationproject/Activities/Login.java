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
import android.widget.Toast;

import com.example.graduationproject.R;
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

    Button btn_login , btn_register;

    public static boolean admin = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        auth = FirebaseAuth.getInstance();




        email_login = findViewById(R.id.enter_email);
        pass_login = findViewById(R.id.enter_pass);


        btn_login = findViewById(R.id.login_btn);
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

        btn_register = findViewById(R.id.register_btn);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });




    }

    private void login(){

        String account = email_login.getText().toString();
        String pass = pass_login.getText().toString();

        if(account.length()> 0  && pass.length()>0) {

            auth.signInWithEmailAndPassword(account , pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        startActivity(new Intent(Login.this , Home.class));
                        finish();
                    }else {
                        email_login.setError("من فضلك راجع بريدك الألكتروني");
                        pass_login.setError("من فضلك راجع كلمة المرور");
                        Toast.makeText(getApplicationContext(), "البريد الألكتروني أو كلمة المرور غير صحيح" , Toast.LENGTH_LONG).show();

                    }
                }
            });

        }else{
            email_login.setError("من فضلم أدخل البريد الألكتروني");
            pass_login.setError("من فضلم أدخل كلمة المرور");
            email_login.setHintTextColor(Color.RED);
            pass_login.setHintTextColor(Color.RED);
            Toast.makeText(getApplicationContext(),"من فضلك أنهى الطلب بأكمله" ,Toast.LENGTH_LONG).show();

        }

    }


}

