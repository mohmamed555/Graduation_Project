package com.example.graduationproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void LogIn(View view)
    {
Intent i=new Intent(this,SIGN_IN.class);
startActivity(i);

    }
    public void SignUp(View view)
    {

        Intent j=new Intent(this,SIGN_UP.class);
        startActivity(j);


    }
    public void DrivrerApp (View view)
    {
        Intent x=new Intent(this,DriverApp.class);
        startActivity(x);
    }

}
