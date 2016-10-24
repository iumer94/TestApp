package com.example.umerejaz.testapp.SolePropreitorship;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umerejaz.testapp.Main.infodeceasedindividual;
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

public class Ask8 extends AppCompatActivity implements View.OnClickListener{
    Button next,ok;
    ImageView iv_back;
    RadioButton btn_yes, btn_no;
    RadioGroup radio;
    TextView mainTitle;
    String tittle;
    LinearLayout layout;
    EditText et_number,et_number2;
    String st_number,st_number2;
    int num;
    transparentDialog td;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask8);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        mainTitle = (TextView) findViewById(R.id.tv_title);
        Intent intent = getIntent();
        tittle = intent.getStringExtra("tittle");
        mainTitle.setText(tittle);

        iv_back=(ImageView)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);
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

        et_number=(EditText)findViewById(R.id.et_number);
        et_number2=(EditText)findViewById(R.id.et_number2);
        layout=(LinearLayout) findViewById(R.id.layout);
        layout.setVisibility(View.INVISIBLE);
        layout.setOnClickListener(this);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_yes.isChecked())
                {
                    layout.setVisibility(View.VISIBLE);
                }
                else {
                    layout.setVisibility(View.GONE);
                }

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

        st_number=et_number.getText().toString();
        st_number2=et_number2.getText().toString();
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
        if(btn_yes.isChecked()){
            if(view.getId()==next.getId())
            {
                if(st_number.equals("")||st_number2.equals(""))
                    {
                        Toast.makeText(this, "Please enter the correct numbers", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        savedDataOnParse();
                    }

            }

        }
        else if(NetUtil.isNetworkAvailable(this) == false )
        {
            dialog.show();
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
                            if (btn_yes.isChecked()) {
                                obj1.put("firstDigitsEIN", st_number);
                                obj1.put("lastDigitsEIN", st_number2);
                                obj1.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            Toast.makeText(Ask8.this, "Data stored successfully", Toast.LENGTH_SHORT).show();
                                            td.dismiss();

                                            if (tittle.equals("Estate Of Deceased Ind")) {
                                                Intent i = new Intent(Ask8.this, infodeceasedindividual.class);
                                                i = i.putExtra("tittle", tittle);
                                                startActivity(i);

                                            } else {
                                                Intent i = new Intent(Ask8.this, PrincipalInformation.class);
                                                i = i.putExtra("tittle", tittle);
                                                startActivity(i);
                                            }
                                        }

                                    }

                                });
                            }

                        }
                    }
                });


    }
}
