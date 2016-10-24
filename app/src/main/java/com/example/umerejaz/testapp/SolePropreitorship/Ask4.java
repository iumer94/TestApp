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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

public class Ask4 extends AppCompatActivity implements View.OnClickListener {
    RadioButton btn_yes;
    RadioButton btn_no;
    RadioGroup radio;
    ImageView iv_back;
    TextView mainTitle;
    String tittle;
    int num;
    transparentDialog td;
    Dialog dialog;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask4);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        mainTitle = (TextView) findViewById(R.id.tv_title);
        Intent intent = getIntent();
        tittle = intent.getStringExtra("tittle");
        mainTitle.setText(tittle);
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

        iv_back=(ImageView)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);

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
        int selectedId = radio.getCheckedRadioButtonId();
        // find which radioButton is checked by id
        if(selectedId == btn_yes.getId()) {
            num=1;
            savedDataOnParse();
        } else if(selectedId == btn_no.getId()) {
            num=0;
            savedDataOnParse();

        }
        else if(NetUtil.isNetworkAvailable(this) == false )
        {

            dialog.show();

        }
        if(view.getId() == ok.getId())
        {

            dialog.dismiss();

        }
        if(view.getId()== iv_back.getId())
        {
            finish();
        }


    }
    public void savedDataOnParse() {
        td = new transparentDialog(this, R.drawable.loading_blue);
        td.show();
        ParseTableName obj = new ParseTableName();
        obj.setParseTableName(tittle);
        String tableName = obj.getParseTableName();
        ParseQuery.getQuery(tableName).whereEqualTo("userID", ParseUser.getCurrentUser())
                .getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject obj1, final ParseException e) {
                        if (e == null) {
                            obj1.put("sellManufacture", num);
                            obj1.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        Toast.makeText(Ask4.this, "Your Answer save successfully", Toast.LENGTH_SHORT).show();
                                        td.dismiss();
                                        Intent i = new Intent(Ask4.this, Ask6.class);
                                        i = i.putExtra("tittle", tittle);
                                        startActivity(i);
                                    }
                                }
                            });
                        }
                    }
                });

    }
}
