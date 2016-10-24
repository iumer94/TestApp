package com.example.umerejaz.testapp.SolePropreitorship;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umerejaz.testapp.Main.Strings;
import com.example.umerejaz.testapp.Main.llc_customeragreement;
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

import static com.example.umerejaz.testapp.R.id.btn_yes;
import static com.example.umerejaz.testapp.R.id.spinner_question2;

public class Ask6 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    Button next,ok;
    ImageView iv_back;
    Spinner spinner_question,spinner_question2;
    RadioButton btn_yes, btn_no;
    RadioGroup radio;
    LinearLayout layout;
    TextView mainTitle;
    String tittle;
    Strings list;
    CheckBox checkBox;
    EditText et_number, et_number2, et_number3;
    String st_number, st_number2, st_number3,st_spQuestion1,st_spQuestion2;
    int num=1;
    transparentDialog td;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask6);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        checkBox=(CheckBox)findViewById(R.id.checkbox1);

        et_number=(EditText)findViewById(R.id.et_number);
        et_number2=(EditText)findViewById(R.id.et_number2);
        et_number3=(EditText)findViewById(R.id.et_number3);
        mainTitle = (TextView) findViewById(R.id.tv_title);
        Intent intent = getIntent();
        tittle = intent.getStringExtra("tittle");
        mainTitle.setText(tittle);
        iv_back=(ImageView)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);
        list=new Strings();
        btn_yes=(RadioButton)findViewById(R.id.btn_yes);
        btn_yes.setOnClickListener(this);
        btn_no=(RadioButton)findViewById(R.id.btn_no);
        btn_no.setOnClickListener(this);

        radio = (RadioGroup) findViewById(R.id.radio);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.btn_yes) {
                    num=1;
                } else if(checkedId == R.id.btn_no) {
                    num=0;
                }
            }
        });

        layout=(LinearLayout) findViewById(R.id.layout);
        layout.setVisibility(View.INVISIBLE);
        layout.setOnClickListener(this);


        spinner_question=(Spinner)findViewById(R.id.spinner_question1);
        spinner_question.setOnItemSelectedListener(this);
        List<String> spinner_question_list=new ArrayList<String>();
        spinner_question_list.add("Mo.of first Date Wages were paid");
        spinner_question_list=list.month();
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner_question_list);
        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_question.setAdapter(dataAdapter3);

        spinner_question2=(Spinner)findViewById(R.id.spinner_question2);
        spinner_question2.setOnItemSelectedListener(this);
        List<String> spinner_question_list2=new ArrayList<String>();
        spinner_question_list2.add("Year Of First Date Wages were paid");
        spinner_question_list2=list.year();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner_question_list2);
        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_question2.setAdapter(dataAdapter);

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
        st_number = et_number.getText().toString();
        st_number2 = et_number2.getText().toString();
        st_number3 = et_number3.getText().toString();
        num=0;
        int selectedId = radio.getCheckedRadioButtonId();
        // find which radioButton is checked by id
        if(selectedId == btn_yes.getId()) {
            num=1;
        } else if(selectedId == btn_no.getId()) {
            num=0;
            savedDataOnParse();

        }
        else if (view.getId() == R.id.btn_yes)
        {
            layout.setVisibility(View.VISIBLE);
            num=1;
        }
        else if (view.getId() == R.id.btn_no)
        {
            layout.setVisibility(View.GONE);
            num=0;
        }
        if (view.getId() == btn_yes.getId())
        {
            layout.setVisibility(View.VISIBLE);

        }
        else if (view.getId() == btn_no.getId())
        {
            layout.setVisibility(View.GONE);
        }
        else if(btn_yes.isChecked())
        {
            if(view.getId()==next.getId()) {
                if (st_number.equals("") || st_number2.equals("") || st_number3.equals("") || st_spQuestion1.equals("Mo. of First Date Wages were paid") || st_spQuestion1.equals("Select Month") || st_spQuestion2.equals("Yr. of First Date Wages were paid") || st_spQuestion2.equals("Select Year")) {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }else{
                savedDataOnParse();
                }
            }

        }
        else if(NetUtil.isNetworkAvailable(this) == false )
        {
            dialog.show();
        }
        else if(view.getId()== iv_back.getId())
        {
            finish();
        }
        else if(view.getId() == ok.getId())
        {

            dialog.dismiss();

        }
    }

    public void savedDataOnParse() {

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
                            obj1.put("taxLiabilityLow", num);
                            if (btn_yes.isChecked()) {
                                obj1.put("agriculturalEmployees", st_number);
                                obj1.put("householdEmployees", st_number2);
                                obj1.put("otherEmployees", st_number3);
                                obj1.put("monthWages", st_spQuestion1);
                                obj1.put("yearWages", st_spQuestion2);
                                obj1.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            Toast.makeText(Ask6.this, "Data stored successfully", Toast.LENGTH_SHORT).show();
                                            td.dismiss();
                                            Intent i = new Intent(Ask6.this, Ask8.class);
                                            i = i.putExtra("tittle", tittle);
                                            startActivity(i);
                                        }

                                    }

                                });
                            }
                            Intent i = new Intent(Ask6.this, Ask8.class);
                            i = i.putExtra("tittle", tittle);
                            startActivity(i);
                        }
                    }
                });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String item = adapterView.getItemAtPosition(position).toString();
        if (adapterView.getId() == spinner_question.getId()) {
            st_spQuestion1 = item;
        }
        if (adapterView.getId() == spinner_question2.getId()) {
            st_spQuestion2 = item;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
