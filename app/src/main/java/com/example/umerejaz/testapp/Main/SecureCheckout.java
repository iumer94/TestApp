package com.example.umerejaz.testapp.Main;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.umerejaz.testapp.R;
import com.example.umerejaz.testapp.SolePropreitorship.StartBussinessDate;

import java.util.ArrayList;
import java.util.List;

public class SecureCheckout extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    Spinner select_month , select_year;
    Button complete_order;
    ImageView back;
    LinearLayout parentlayout;
    int validate;
    Strings list;

    EditText txtCardNumber,f_name,l_name,billing_add,zipcode;
    String scard_no,sf_name,sl_name,sbilling_add,szipcode;

    boolean isAmex = false;
    public static final int MAX_LENGTH_CARD_NUMBER_AMEX = 17;
    public static final int MAX_LENGTH_CARD_NUMBER_VISA_MASTERCARD = 19;
    Integer[] imageArray = { R.drawable.icon_visa, R.drawable.icon_mastercard, R.drawable.icon_discover, R.drawable.icon_amex };

    public static ArrayList<String> listOfPattern()
    {
        ArrayList<String> listOfPattern=new ArrayList<>();

        String ptVisa = "^4[0-9]$";

        listOfPattern.add(ptVisa);

        String ptMasterCard = "^5[1-5]$";

        listOfPattern.add(ptMasterCard);

        String ptDiscover = "^6(?:011|5[0-9]{2})$";

        listOfPattern.add(ptDiscover);

        String ptAmeExp = "^3[47]$";

        listOfPattern.add(ptAmeExp);

        return listOfPattern;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secure_checkout);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();


        select_month = (Spinner) findViewById(R.id.exp_month);
        select_year = (Spinner) findViewById(R.id.exp_year);
        complete_order = (Button) findViewById(R.id.complete_order);
        back = (ImageView) findViewById(R.id.iv_back);
        back.setOnClickListener(SecureCheckout.this);

        txtCardNumber = (EditText) findViewById( R.id.txtCardNumber);
        f_name = (EditText) findViewById( R.id.f_name);
        l_name = (EditText) findViewById( R.id.l_name);
        billing_add = (EditText) findViewById( R.id.billing_add);
        zipcode = (EditText) findViewById( R.id.zipcode);


        select_month=(Spinner)findViewById(R.id.select_month);
        select_month.setOnItemSelectedListener(SecureCheckout.this);
        List<String> select_month_list=new ArrayList<String>();
        list = new Strings();
        select_month_list=list.month();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, select_month_list);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_month.setAdapter(dataAdapter);



    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
