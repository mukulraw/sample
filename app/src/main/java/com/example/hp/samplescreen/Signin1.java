package com.example.hp.samplescreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Signin1 extends AppCompatActivity {
//    TextView textView;

    Button button;
    TextView signup;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin1);
        dialog = new ProgressDialog(Signin1.this);

        signup = (TextView)findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Signin1.this , Signup1.class);
                startActivity(intent);

            }
        });


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


    private class PostTask2 extends AsyncTask<String, String, String> {

        String e;

        public PostTask2(String u)
        {
            this.e = u;
        }

        @Override
        protected String doInBackground(String... data) {

            dialog.show();

            String dat = "";

            try {
                URL url = new URL("https://test.vyabl.com/api/Account/RegisterExternal");


                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("POST");

                urlConnection.setDoOutput(true);

                bean b = (bean)getApplicationContext();

                Log.d("Bearer" , b.access);

                //urlConnection.setRequestProperty("Authorization",b.access);
                urlConnection.setRequestProperty("Content-Type","application/json");
                //int statusCode = urlConnection.getResponseCode();
                //if (statusCode ==  200) {
                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                wr.writeBytes(e);
                wr.flush();
                wr.close();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in);

                int inputStreamData = inputStreamReader.read();
                while (inputStreamData != -1) {
                    char current = (char) inputStreamData;
                    inputStreamData = inputStreamReader.read();
                    dat += current;
                }

                Log.d("data" , dat);

                /*}
                else
                {
                    //Handle else
                }*/


                return dat;

            } catch (IOException e) {
                e.printStackTrace();
                return "LOL NOPE";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            dialog.dismiss();

            Log.d("ddd" , s);





        }
    }


}

