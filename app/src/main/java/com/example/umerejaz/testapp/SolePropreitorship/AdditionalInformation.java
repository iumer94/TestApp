package com.example.umerejaz.testapp.SolePropreitorship;

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
import android.widget.TextView;

import com.example.umerejaz.testapp.Main.Strings;
import com.example.umerejaz.testapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.umerejaz.testapp.R.id.state;

public class AdditionalInformation extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button next;
    Spinner reason_apply, primary_activity,state_location;
    ImageView iv_back;
    TextView mainTitle;
    String titlle;
    Strings list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_information);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);
        iv_back=(ImageView)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        mainTitle = (TextView) findViewById(R.id.tv_title);
        Intent intent = getIntent();
        titlle = intent.getStringExtra("titlle");
        mainTitle.setText(titlle);
        list=new Strings();
        reason_apply=(Spinner)findViewById(R.id.reason_apply);
        reason_apply.setOnItemSelectedListener(AdditionalInformation.this);
        List<String> reason_apply_list=new ArrayList<String>();
        reason_apply_list=list.reason_for_applying();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, reason_apply_list);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reason_apply.setAdapter(dataAdapter);

        primary_activity=(Spinner)findViewById(R.id.primary_activity);
        primary_activity.setOnItemSelectedListener(AdditionalInformation.this);
        List<String> primary_activity_list=new ArrayList<String>();
        primary_activity_list.add("Primary Activity");
        primary_activity_list=list.month();
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, primary_activity_list);
        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        primary_activity.setAdapter(dataAdapter2);

        state_location=(Spinner)findViewById(R.id.state_location);
        state_location.setOnItemSelectedListener(AdditionalInformation.this);
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
        if (view.getId() == next.getId()) {
            Intent i = new Intent(this, Ask1.class);
            i= i.putExtra("titlle", titlle);
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
