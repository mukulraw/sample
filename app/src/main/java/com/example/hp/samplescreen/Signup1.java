package com.example.hp.samplescreen;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public class Signup1 extends AppCompatActivity {
Button button;

    API api;
    NetworkManagerCheck networkcheck;
    EditText etFirstName,etlastName,etEmail,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

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
                        LoginRequest(firstName,lastName,email,password);
                    } else {
                        CommonMethod.showAlert("Please connect to internet...",
                                Signup1.this);
                    }
                }

            }
        });

    }

    private void  LoginRequest(String firstname,String lastname,String email,String password){
        final Dialog mDialog = ProgressDialog.show(Signup1.this, "Sign Up", "Please wait....", true);
        api.SignUp(firstname, lastname,email,password, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {

                Log.d("response",response);

                /*try {
                    JSONObject object = new JSONObject(response);
                    String userid = object.optString("userId");
                    String companyName = object.optString("cmpname");
                    String userName = object.optString("usernName");
                    if(!userid.equals("0")){
                        startActivity(new Intent(Signup1.this, MainActivity.class));
                        mDialog.dismiss();
                    }
                    else{
                        mDialog.dismiss();
                        CommonMethod
                                .showAlert("User Not found", Signup1.this);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onError(VolleyError error) {
                Log.d("response",""+error);
                mDialog.dismiss();
                CommonMethod
                        .showAlert("Network Connection error", Signup1.this);
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

}
