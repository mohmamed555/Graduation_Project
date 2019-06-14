package com.example.graduationproject.Adapters;


import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.TextView;


import com.example.graduationproject.Models.ModelOfDataListOfServices;
import com.example.graduationproject.R;

import java.util.ArrayList;



public class AdapterForListOfServices extends ArrayAdapter {

    ArrayList<ModelOfDataListOfServices> arrayList = new ArrayList<>();

    Context mContext;
    ImageView imageView ;

    public AdapterForListOfServices(@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);

        arrayList = objects;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(R.layout.list_item, parent, false);

        TextView name = convertView.findViewById(R.id.name_txt);
        TextView location = convertView.findViewById(R.id.location_txt);
        ImageView phone = convertView.findViewById(R.id.phoneBtn);
        ImageView maps = convertView.findViewById(R.id.locationBtn);

           imageView  = convertView.findViewById(R.id.item_image);

           setLogo(position);





        name.setText(arrayList.get(position).getName());
        location.setText(arrayList.get(position).getLocation());


        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(String.format("tel:%s", arrayList.get(position).getPhone())));
                mContext.startActivity(intent);


            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String geoUri = "http://maps.google.com/maps?q=loc:" + arrayList.get(position).getMaps();
                Intent mapss = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                mContext.startActivity(mapss);

            }
        });








        return convertView;
    }


    private void setLogo(int pos){


        if (arrayList.get(pos).getCategory().equals("crane")){

            imageView.setImageResource(R.drawable.crane);


        }else if(arrayList.get(pos).getCategory().equals("mechanical") ){

            imageView.setImageResource(R.drawable.mec);



        }else if(arrayList.get(pos).getCategory().equals("electric") ){

            imageView.setImageResource(R.drawable.elictric);



        }else if(arrayList.get(pos).getCategory().equals("tiers") ){

            imageView.setImageResource(R.drawable.tiers);



        }else if(arrayList.get(pos).getCategory().equals("garage") ){

            imageView.setImageResource(R.drawable.garage);



        }


    }


}
