package com.example.graduationproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.graduationproject.R;

public class ListOfServices extends AppCompatActivity {
   ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_services);

        listView = findViewById(R.id.list_item);
    }
}
