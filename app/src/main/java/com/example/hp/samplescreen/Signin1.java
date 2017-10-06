package com.example.hp.samplescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Signin1 extends AppCompatActivity {
//    TextView textView;

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin1);


        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            TextView mTitle = (TextView) toolbar.findViewById(R.id.text_title);
         /*   RelativeLayout mBtnBack = (RelativeLayout) toolbar.findViewById(R.id.btn_back);
            mBtnBack.setOnClickListener(this);*/
            mTitle.setText("Sign In");
        }


        button= (Button) findViewById(R.id.signin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Signin1.this,Payment.class);
                startActivity(intent);
            }
        });
//        textView= (TextView) findViewById(R.id.tv2);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(Signin1.this,Signup1.class);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

