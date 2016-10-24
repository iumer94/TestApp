package com.example.umerejaz.testapp.Main;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.umerejaz.testapp.R;

/**
 * Created by Umer Ejaz on 9/30/2016.
 */

public class CustomAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] itemname;

    public CustomAdapter(Activity context, String[] itemname ) {
        super(context, R.layout.entitylist, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView =inflater.inflate(R.layout.entitylist, null,true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.entitynames);
        txtTitle.setText(itemname[position]);
        return rowView;
    };
}
