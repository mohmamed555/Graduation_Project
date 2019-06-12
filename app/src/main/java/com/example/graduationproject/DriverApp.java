package com.example.graduationproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DriverApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_app);
    }
    public void NearestLocation (View view)
    {
        Intent z=new Intent(this,NearestLocation_of_your_Service.class);
        startActivity(z);
    }
}
