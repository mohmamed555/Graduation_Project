package com.example.graduationproject.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.graduationproject.Adapters.AdapterForListOfServices;
import com.example.graduationproject.Models.ModelOfDataListOfServices;
import com.example.graduationproject.R;
import com.example.graduationproject.Utils.MyUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListOfServices extends AppCompatActivity {
   ListView listView ;

   ArrayList<ModelOfDataListOfServices> arrayList = new ArrayList<>();

   DatabaseReference ref   = FirebaseDatabase.getInstance().getReference();

   AdapterForListOfServices adapter  ;
   LinearLayout parent  ; 
   ProgressBar progressBar ; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_services);
        definitions();



        adapter = new AdapterForListOfServices(this , R.layout.list_item , arrayList );
        listView.setAdapter(adapter);


     checkOnData();

    }

    private void  definitions(){

        listView = findViewById(R.id.list_item);
        parent = findViewById(R.id.parentOfList);
        progressBar = findViewById(R.id.progressOfList);

    }

    private void checkOnData(){


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child("services").hasChild(MyUtils.keyOfCategory)) {

                    getData();


                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ListOfServices.this, "No data", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void getData(){


        ref.child("services").child(MyUtils.keyOfCategory).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                parent.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    arrayList.add(dataSnapshot1.getValue(ModelOfDataListOfServices.class));

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ListOfServices.this, "Connection field", Toast.LENGTH_SHORT).show();


            }
        });
    }
}
