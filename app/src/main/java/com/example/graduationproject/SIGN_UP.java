package com.example.graduationproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SIGN_UP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
    }
    public void DrivrerApp (View view)
    {
        Intent x=new Intent(this,DriverApp.class);
        startActivity(x);
    }
}
