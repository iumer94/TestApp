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

public class npo_bussinessinfo extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button next;
    Spinner state;
    ImageView iv_back;
    TextView mainTitle;
    String titlle;
    Strings list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npo_bussinessinfo);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);
        list=new Strings();
        mainTitle = (TextView) findViewById(R.id.tv_title);
        Intent intent = getIntent();
        titlle = getIntent().getStringExtra("tittle");
        mainTitle.setText(titlle);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);

        state = (Spinner) findViewById(R.id.state);
        state.setOnItemSelectedListener(this);
        List<String> liststate = new ArrayList<String>();
        liststate=list.state();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, liststate);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(dataAdapter);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == next.getId()) {
            Intent i = new Intent(this, StartBussinessDate.class);
            i = i.putExtra("titlle", titlle);
            startActivity(i);
        }
        if (view.getId() == iv_back.getId()) {
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
