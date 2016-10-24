package com.example.umerejaz.testapp.SolePropreitorship;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umerejaz.testapp.Main.Strings;
import com.example.umerejaz.testapp.Main.excutor_address;
import com.example.umerejaz.testapp.NetUtil;
import com.example.umerejaz.testapp.ParseTableName;
import com.example.umerejaz.testapp.R;
import com.example.umerejaz.testapp.transparentDialog;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class PrincipalInformation extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button next,ok;
    Spinner spinner_suffix;
    String st_sufix;
    ImageView iv_back;
    TextView mainTitle;
    String tittle;
    Strings list;
    EditText et_firstname, et_middlename,et_lastname,et_number, et_number2,et_email,et_tittle;
    String st_firstname, st_middlename,st_lastname,st_number, st_number2,st_email,st_tittle;
    transparentDialog td;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_information);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        list=new Strings();
        mainTitle = (TextView) findViewById(R.id.tv_title);
        Intent intent = getIntent();
        tittle = intent.getStringExtra("tittle");
        mainTitle.setText(tittle);
        iv_back=(ImageView)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);

        et_firstname=(EditText)findViewById(R.id.et_firstname);
        et_middlename=(EditText)findViewById(R.id.et_middlename);
        et_lastname=(EditText)findViewById(R.id.et_lastname);
        et_number=(EditText)findViewById(R.id.et_number);
        et_number2=(EditText)findViewById(R.id.et_number2);
        et_email=(EditText)findViewById(R.id.et_email);
        et_tittle=(EditText)findViewById(R.id.et_tittle);

        spinner_suffix  = (Spinner) findViewById(R.id.spinner_suffix);
        spinner_suffix.setOnItemSelectedListener(this);
        List<String> suffixlist = new ArrayList<String>();
        suffixlist=list.suffix();
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, suffixlist);
        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_suffix.setAdapter(dataAdapter3);
        et_number.addTextChangedListener(new TextWatcher()
        {
            int keyDel;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et_number.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {

                        if (keyCode == KeyEvent.KEYCODE_DEL)
                            keyDel = 1;
                        return false;
                    }
                });

                if (keyDel == 0) {
                    int len = et_number.getText().length();
                    if(len == 3 || len == 6) {
                        et_number.setText(et_number.getText() + "-");
                        et_number.setSelection(et_number.getText().length());
                    }
                } else {
                    keyDel = 0;
                }
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
        });
        et_number2.addTextChangedListener(new TextWatcher() {
            int keyDel;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et_number2.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {

                        if (keyCode == KeyEvent.KEYCODE_DEL)
                            keyDel = 1;
                        return false;
                    }
                });

                if (keyDel == 0) {
                    int len = et_number2.getText().length();
                    if(len == 3 || len == 6) {
                        et_number2.setText(et_number2.getText() + "-");
                        et_number2.setSelection(et_number2.getText().length());
                    }
                } else {
                    keyDel = 0;
                }
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
        });
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_internet_dialog);
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ok=(Button) dialog.findViewById(R.id.btn_right);
        ok.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        st_firstname=et_firstname.getText().toString();
        st_middlename=et_middlename.getText().toString();
        st_lastname=et_lastname.getText().toString();
        st_number=et_number.getText().toString();
        st_number2=et_number2.getText().toString();
        st_email=et_email.getText().toString();
        st_tittle=et_tittle.getText().toString();



        if (view.getId() == next.getId()) {

            if(st_firstname.equals("") || st_middlename.equals("") || st_lastname.equals("") || st_number.equals("") || st_sufix.equals("Suffix")||st_number2.equals("") || st_email.equals("") || st_tittle.equals("")){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }

            else if(!Patterns.EMAIL_ADDRESS.matcher(st_email).matches())
            {
                et_email.setError("Enter Valid Email address");
            }
            else if(!st_number.equals(st_number2)){
                Toast.makeText(this, "Security Number and verify security number not matching", Toast.LENGTH_SHORT).show();
            }
            else if(NetUtil.isNetworkAvailable(this) == false )
            {
                dialog.show();
            }
            else {
                saveDataOnParse();
            }
        }
        else if(view.getId() == ok.getId())
        {

            dialog.dismiss();

        }
        else if(view.getId()== iv_back.getId())
        {
            finish();
        }
    }
    public void saveDataOnParse()
    {
        td = new transparentDialog(this, R.drawable.loading_blue);
        td.show();
        final ParseTableName obj = new ParseTableName();
        obj.setParseTableName(tittle);
        String tableName = obj.getParseTableName();
        ParseQuery.getQuery(tableName).whereEqualTo("userID", ParseUser.getCurrentUser())
                .getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject obj1, final ParseException e) {
                        if (e == null) {
                            obj1.put("firstName", st_firstname);
                            obj1.put("middleName", st_middlename);
                            obj1.put("lastName", st_lastname);
                            obj1.put("suffix", st_sufix);
                            obj1.put("socialNumber", st_number);
                            obj1.put("email", st_email);
                            obj1.put("title", st_tittle);
                            obj1.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null)//means data save on parse
                                    {
                                        Toast.makeText(PrincipalInformation.this, "Principle Info Data is saved successfully", Toast.LENGTH_LONG).show();
                                        td.dismiss();
                                        Intent i = new Intent(getApplicationContext(), CustomerAgreement.class);
                                        i = i.putExtra("tittle", tittle);
                                        startActivity(i);

                                        if (tittle.equals("Estate Of Deceased Ind")) {
                                            i = new Intent(PrincipalInformation.this, excutor_address.class);
                                            i = i.putExtra("tittle", tittle);
                                            startActivity(i);

                                        }
                                    }
                                }
                            });
                        }
                    }
                });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String item = adapterView.getItemAtPosition(position).toString();
        if(adapterView.getId() == spinner_suffix.getId())
        {
            st_sufix= item;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
