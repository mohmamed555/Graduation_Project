package com.example.graduationproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.graduationproject.R;
import com.example.graduationproject.Utils.MyUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {



     RelativeLayout crane  , ecl , mec  , tier , garage   , aboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        definitions();
        onClick();


    }

    private void definitions(){
        crane = findViewById(R.id.craneParent);
        ecl = findViewById(R.id.electricParent);
        mec = findViewById(R.id.mechanicalParent);
        tier = findViewById(R.id.tiersParent);
        garage = findViewById(R.id.garageParent);
        aboutUs = findViewById(R.id.aboutParent);




    }

    private void onClick (){
        crane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.keyOfCategory= "crane";

                startActivity(new Intent(Home.this, ListOfServices.class));

            }
        });

        mec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.keyOfCategory="mechanical";
                startActivity(new Intent(Home.this, ListOfServices.class));
            }
        });

        ecl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.keyOfCategory="electric";
                startActivity(new Intent(Home.this, ListOfServices.class));
            }
        });
        tier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.keyOfCategory="tiers";
                startActivity(new Intent(Home.this, ListOfServices.class));
            }
        });
        garage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.keyOfCategory="garage";
                startActivity(new Intent(Home.this, ListOfServices.class));
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Home.this, AboutUS.class));
            }
        });

    }

}
