package com.example.umerejaz.testapp.Main;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.umerejaz.testapp.R;
import com.example.umerejaz.testapp.SolePropreitorship.AdditionalInformation;
import com.example.umerejaz.testapp.SolePropreitorship.StartBussinessDate;

import java.util.ArrayList;
import java.util.List;

public class excutor_address extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button next;
    Spinner state_location;
    ImageView iv_back;
    Strings list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excutor_address);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        list=new Strings();
        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);
        iv_back=(ImageView)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        state_location=(Spinner)findViewById(R.id.state_location);
        state_location.setOnItemSelectedListener(this);
        List<String> state_location_list=new ArrayList<String>();
        state_location_list.add("State/Ter Where articles of org.filed");
        state_location_list=list.state();
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, state_location_list);
        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state_location.setAdapter(dataAdapter3);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==next.getId()){
            Intent i=new Intent(this, StartBussinessDate.class);
            startActivity(i);
        }
        if(view.getId()== iv_back.getId())
        {
            finish();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
