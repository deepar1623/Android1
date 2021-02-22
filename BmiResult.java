package com.example.sonyvaio.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class BmiResult extends AppCompatActivity {

    TextView tvResult, tvUn, tvNo, tvOv, tvOb;
    Button btnBack, btnShare, btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_result);

        tvResult= (TextView) findViewById(R.id.tvResult);
        tvUn= (TextView) findViewById(R.id.tvUn);
        tvNo= (TextView) findViewById(R.id.tvNo);
        tvOv= (TextView) findViewById(R.id.tvOv);
        tvOb= (TextView) findViewById(R.id.tvOb);

        btnBack= (Button) findViewById(R.id.btnBack);
        btnShare= (Button) findViewById(R.id.btnShare);
        btnSave= (Button) findViewById(R.id.btnSave);




        final Intent i=getIntent();
        String bmi=i.getStringExtra("bmi");
        final String msg=i.getStringExtra("msg");
        String txt="Your BMI is "+bmi+ "and "+msg;
        tvResult .setText(txt);

        final double bmid= Double.parseDouble(bmi);
        if(bmid<18.5){
            tvUn.setTextColor(Color.parseColor("#ff0000"));
        } else if(bmid>=18.5 & bmid<25) {
            tvNo.setTextColor(Color.parseColor("#ff0000"));

        }else if(bmid>=25 & bmid<30){
            tvOv.setTextColor(Color.parseColor("#ff0000"));
        }else if(bmid>=30 ){
            tvOb.setTextColor(Color.parseColor("#ff0000"));

        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp1=getSharedPreferences("userdata",MODE_PRIVATE);
                Intent i= new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                String name=sp1.getString("name", "");
                String age=sp1.getString("age", "");
                String phone=sp1.getString("phonenumber", "");
                String sex=sp1.getString("sex", "");
                String details="Name--"+name+"\n"
                        +"Age--"+age+"\n"
                        +"Phone--"+phone+"\n"
                        +"Sex--"+sex+"\n"
                        +"BMI"+bmid+"\n"
                        +msg+"\n";
                i.putExtra(Intent.EXTRA_TEXT, details);
                  startActivity(i);


            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String b=i.getStringExtra("bmi");
                String w=i.getStringExtra("wt");
                Date d=new Date();
                MyDbHandler db=new MyDbHandler(getApplicationContext());
                db.addRecord(d.toString(),b,w);

            }
        });


    }
}
