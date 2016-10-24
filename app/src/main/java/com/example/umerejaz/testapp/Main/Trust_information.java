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
import android.widget.TextView;

import com.example.umerejaz.testapp.LLC.BussinessAddress;
import com.example.umerejaz.testapp.R;

import java.util.ArrayList;
import java.util.List;

public class Trust_information extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView mainTitle;
    ImageView iv_back;
    Button next;
    String titlle;
    Spinner llc_member;
    Strings list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trust_information);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mainTitle = (TextView) findViewById(R.id.tv_title);
        Intent intent = getIntent();
        titlle = getIntent().getStringExtra("tittle");
        titlle = intent.getStringExtra("titlle");
        mainTitle.setText(titlle);
        list=new Strings();
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);

        llc_member=(Spinner)findViewById(R.id.llc_member);
        llc_member.setOnItemSelectedListener(this);
        List<String> llc_member_list=new ArrayList<String>();
        llc_member_list=list.type_of_trust();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, llc_member_list);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        llc_member.setAdapter(dataAdapter);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == next.getId()) {
            Intent i = new Intent(this, trustee_information.class);
            i= i.putExtra("titlle", titlle);
            startActivity(i);
        }

        if(view.getId()==iv_back.getId())        {
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
