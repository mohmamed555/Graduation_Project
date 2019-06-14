package com.example.graduationproject.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.graduationproject.Models.ModelOfDataListOfServices;
import com.example.graduationproject.R;
import com.example.graduationproject.Utils.MyUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin extends AppCompatActivity {


    RadioGroup radioGroup ;
    RadioButton crane , mechanical , electric , tiers , garage ;

    EditText name , location , phone , maps ;
    
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    
    LinearLayout parent  ; 
    ProgressBar progressBar  ; 



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

         definitions();
         setRadioGroup();




    }

    private void definitions(){

        radioGroup = findViewById(R.id.radiogroup1);

        crane = findViewById(R.id.craneBtn);
        mechanical = findViewById(R.id.mechanicalBtn);
        electric = findViewById(R.id.electricBtn);
        tiers = findViewById(R.id.tiersBtn);
        garage = findViewById(R.id.garageBtn);


        name = findViewById(R.id.editName);
        location = findViewById(R.id.editlocationName);
        phone = findViewById(R.id.editPhone);
        maps = findViewById(R.id.editMaps);
        
        parent = findViewById(R.id.parent);
        progressBar = findViewById(R.id.progress);


    }

    private void setRadioGroup (){

       crane.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               MyUtils.keyOfCategory= "crane";

           }
       });

       mechanical.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               MyUtils.keyOfCategory="mechanical";
           }
       });

        electric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.keyOfCategory="electric";
            }
        });
        tiers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.keyOfCategory="tiers";
            }
        });
        garage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.keyOfCategory="garage";
            }
        });
    }


    private void dataValidation (){


        if (name.getText().toString().equals("")){

            name.setError("Required");



        }else  if (location.getText().toString().equals("")){


            location.setError("Required");
        }else if(phone.getText().toString().equals("")){
            phone.setError("Required");



        }else if(maps.getText().toString().equals("")){
            maps.setError("Required");




        }else if(MyUtils.keyOfCategory.equals("")){

            Toast.makeText(this, "please choose your category ", Toast.LENGTH_SHORT).show();
        }
        
        else {
            

             progressBar.setVisibility(View.VISIBLE);
             parent.setVisibility(View.GONE);
             
            data(name.getText().toString().trim() , location.getText().toString().trim()  , maps.getText().toString().trim() , 
                    phone.getText().toString().trim()  , MyUtils.keyOfCategory
                    );



        }




    }

    private void data (String nameD , String locationD ,  String mapD  , String phoneD , String categoryD  ){


        ModelOfDataListOfServices model  = new ModelOfDataListOfServices();
        model.setCategory(categoryD);
        model.setLocation(locationD);
        model.setName(nameD);
        model.setPhone(phoneD);
        model.setMaps(mapD);
        
        ref.child("services").child(categoryD).push().setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                MyUtils.keyOfCategory = "";

                location.setText("");

                maps.setText("");

                phone.setText("");

                name.setText("");

                radioGroup.clearCheck();

                progressBar.setVisibility(View.GONE);

                parent.setVisibility(View.VISIBLE);

                Toast.makeText(Admin.this, "Uploaded", Toast.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressBar.setVisibility(View.GONE);
                parent.setVisibility(View.VISIBLE);

                Toast.makeText(Admin.this, "Upload Feiled", Toast.LENGTH_SHORT).show();


            }
        });



    }


    public void sendData(View view) {
        dataValidation();
    }
}
