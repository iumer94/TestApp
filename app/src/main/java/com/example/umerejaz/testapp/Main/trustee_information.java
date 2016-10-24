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
import com.example.umerejaz.testapp.SolePropreitorship.CustomerAgreement;

import java.util.ArrayList;
import java.util.List;

public class trustee_information extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button next;
    Spinner spinner_suffix;
    ImageView iv_back;
    TextView mainTitle;
    String titlle;
    Strings list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trustee_information);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        iv_back=(ImageView)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);
        list=new Strings();
        mainTitle = (TextView) findViewById(R.id.tv_title);
        Intent intent = getIntent();
        titlle = intent.getStringExtra("titlle");
        mainTitle.setText(titlle);

        spinner_suffix  = (Spinner) findViewById(R.id.spinner_suffix);
        spinner_suffix.setOnItemSelectedListener(this);

        List<String> suffixlist = new ArrayList<String>();
        suffixlist=list.suffix();
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, suffixlist);
        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_suffix.setAdapter(dataAdapter3);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == next.getId()) {
            Intent i = new Intent(this, BussinessAddress.class);
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
