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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umerejaz.testapp.Main.Splash;
import com.example.umerejaz.testapp.Nav_Bar;
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

public class CustomerAgreement extends AppCompatActivity implements View.OnClickListener{
    Button submmit_application,ok;
    ImageView iv_back;
    TextView mainTitle;
    String tittle;
    CheckBox checkBox1;
    int num;
    transparentDialog td;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_agreement);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        submmit_application = (Button) findViewById(R.id.submmit_application);
        submmit_application.setOnClickListener(this);
        mainTitle = (TextView) findViewById(R.id.tv_title);
        Intent intent = getIntent();
        tittle = intent.getStringExtra("tittle");
        mainTitle.setText(tittle);

        checkBox1=(CheckBox)findViewById(R.id.checkBox1);
        checkBox1.setOnClickListener(this);

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
        if(view.getId()== submmit_application.getId())
        {
            if (checkBox1.isChecked())
            {
                savedDataOnParse();
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
        ParseTableName obj = new ParseTableName();
        obj.setParseTableName(tittle);
        String tableName = obj.getParseTableName();
        ParseQuery.getQuery(tableName).whereEqualTo("userID", ParseUser.getCurrentUser())
                .getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(final ParseObject obj1, final ParseException e) {
                        if (e == null) {
                            obj1.put("customerAgreement", 1);
                            obj1.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        Toast.makeText(CustomerAgreement.this, "Submit Data Successfully", Toast.LENGTH_SHORT).show();
                                        td.dismiss();
                                        Intent i = new Intent(CustomerAgreement.this, Nav_Bar.class);
                                        startActivity(i);
                                    }
                                }
                            });
                        }
                    }
                });


    }
}
