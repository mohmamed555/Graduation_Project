package com.example.graduationproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.graduationproject.R;

import java.util.List;

public class AdapterForListOfServices  extends ArrayAdapter {



    Context mContext ;

    public AdapterForListOfServices(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);


        mContext = context ;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(R.layout.list_item, parent,false);






        return convertView;
    }
}
