package com.example.hp.samplescreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Payment_help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_help);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            TextView mTitle = (TextView) toolbar.findViewById(R.id.text_title);
         /*   RelativeLayout mBtnBack = (RelativeLayout) toolbar.findViewById(R.id.btn_back);
            mBtnBack.setOnClickListener(this);*/
            mTitle.setText("Payment Help");
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
