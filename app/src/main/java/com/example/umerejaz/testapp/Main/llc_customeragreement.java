package com.example.umerejaz.testapp.Main;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.umerejaz.testapp.R;
import com.example.umerejaz.testapp.SolePropreitorship.Ask1;

public class llc_customeragreement extends AppCompatActivity implements View.OnClickListener {

    ImageView iv_back;
    TextView mainTitle;
    String titlle;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llc_customeragreement);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
         next= (Button) findViewById(R.id.next);
         next.setOnClickListener(this);

        mainTitle = (TextView) findViewById(R.id.tv_title);
        Intent intent = getIntent();
        titlle = intent.getStringExtra("titlle");
        mainTitle.setText(titlle);

        iv_back=(ImageView)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==next.getId()){
            Intent i=new Intent(this, Ask1.class);
            i= i.putExtra("titlle", titlle);
            startActivity(i);
        }
        if(view.getId()== iv_back.getId())
        {
            finish();
        }

    }
}
