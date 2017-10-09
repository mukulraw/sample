package com.example.hp.samplescreen;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Signup1 extends AppCompatActivity {
Button button;

    API api;
    NetworkManagerCheck networkcheck;
    EditText etFirstName,etlastName,etEmail,etPassword;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        dialog = new ProgressDialog(Signup1.this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            TextView mTitle = (TextView) toolbar.findViewById(R.id.text_title);
         /*   RelativeLayout mBtnBack = (RelativeLayout) toolbar.findViewById(R.id.btn_back);
            mBtnBack.setOnClickListener(this);*/
            mTitle.setText("Sign Up");
        }


        button = (Button) findViewById(R.id.signup);
        api = new API(this);
        networkcheck = new NetworkManagerCheck(Signup1.this);

         etFirstName = (EditText) findViewById(R.id.et_first_name);
         etlastName = (EditText) findViewById(R.id.et_last_name);
         etEmail = (EditText) findViewById(R.id.et_email);
         etPassword = (EditText) findViewById(R.id.et_password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(Signup1.this, Signin1.class);
                startActivity(intent)*/;
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etlastName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (Checkvalidation()) {
                    if (networkcheck.isConnectToInternet()) {


                        JSONObject jsonObject = new JSONObject();

                        try {
                            jsonObject.put("Email" , email);
                            jsonObject.put("Password" , password);
                            jsonObject.put("ConfirmPassword" , password);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        dialog.setMessage("Logging In...");
                        dialog.setCancelable(false);
                        dialog.show();

                        PostTask2 pt = new PostTask2(jsonObject.toString());
                        pt.execute();


                    } else {
                        CommonMethod.showAlert("Please connect to internet...",
                                Signup1.this);
                    }
                }

            }
        });

    }




    private boolean Checkvalidation() {

        if (etFirstName.getText().toString().trim().length() == 0) {
            CommonMethod
                    .showAlert("Please enter first Name", Signup1.this);
            return false;

        }
        else if (etlastName.getText().toString().trim().length() == 0) {
            CommonMethod.showAlert("Please enter last name", Signup1.this);
            return false;

        }

        else if (etEmail.getText().toString().trim().length() == 0) {
            CommonMethod.showAlert("Please enter last email", Signup1.this);
            return false;

        }

        else if (etPassword.getText().toString().trim().length() == 0) {
            CommonMethod.showAlert("Please enter last password", Signup1.this);
            return false;

        }
        return true;
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
                URL url = new URL("https://test.vyabl.com/api/Account/Register");


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

                Log.d("dataaaa" , dat);

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

            if (s.length() == 0)
            {
                Toast.makeText(Signup1.this , "Registered Successfully" , Toast.LENGTH_SHORT).show();
            }

            Log.d("dddaaa" , s);





        }
    }




}
