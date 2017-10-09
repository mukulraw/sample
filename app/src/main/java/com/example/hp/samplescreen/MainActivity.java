package com.example.hp.samplescreen;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity{


    ProgressBar progress;

    LinearLayout textView,textView1 ;

    TextView next1 , next2;
    /*private CallbackManager callbackManager;


    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;*/

    RelativeLayout btnFacebook,btnGoogle,btnTwitter;

    private HashMap<String, String> userHashmap;
    private ArrayList<HashMap<String, String>> friendList;

    private ProgressDialog pd;

    public static final String USER_MAP = "userHashmap";
    public static final String FRIEND_LIST = "friendList";

    public static final String USER_ID = "userId";
    public static final String NAME = "name";
    public static final String USER_NAME = "userName";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String BIRTHDAY = "birthday";
    public static final String GENDER = "gender";
    public static final String EMAIL_ID = "emailId";
    public static final String IMAGE_URL = "imageUrl";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        textView= (LinearLayout) findViewById(R.id.btn_sign_up);
        textView1= (LinearLayout) findViewById(R.id.btn_signin);
        //textView3= (LinearLayout) findViewById(R.id.btn_next);

        next1 = (TextView)findViewById(R.id.next1);
        next2 = (TextView)findViewById(R.id.next2);



        progress = (ProgressBar)findViewById(R.id.progress);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Signup1.class);
                startActivity(intent);
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Signin1.class);
                startActivity(intent);
            }
        });

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this ,Payment.class);
                startActivity(i);
            }
        });


        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this ,Payment_help.class);
                startActivity(i);
            }
        });



        btnFacebook= (RelativeLayout)findViewById(R.id.btn_facebbok);

    //    LoginButton loginButton = (LoginButton) findViewById(R.id.Linear2);

         btnGoogle= (RelativeLayout)findViewById(R.id.btn_google);
       // LinearLayout mBtnTwitter= (LinearLayout)findViewById(R.id.Linear4);

        btnTwitter = (RelativeLayout)findViewById(R.id.btn_twitter);


        final bean b = (bean)getApplicationContext();

        /*progress.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        allAPIs cr = retrofit.create(allAPIs.class);

        Call<List<externalLoginsBean>> call = cr.getExternalLogins("%2F" , "true");

        call.enqueue(new Callback<List<externalLoginsBean>>() {
            @Override
            public void onResponse(Call<List<externalLoginsBean>> call, Response<List<externalLoginsBean>> response) {

                b.gURL = response.body().get(0).getUrl();
                b.fURL = response.body().get(1).getUrl();
                b.tURL = response.body().get(2).getUrl();
                b.mURL = response.body().get(3).getUrl();

                Log.d("state" , response.body().get(0).getState());
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<externalLoginsBean>> call, Throwable throwable) {
                progress.setVisibility(View.GONE);
            }
        });*/


        PostTask pt = new PostTask();
        pt.execute();


        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("gurl" , b.gURL);

                Intent intent = new Intent(MainActivity.this , WebViewActivity.class);
                intent.putExtra("url" , b.baseURL + b.gURL);
                startActivity(intent);

            }
        });

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("gurl" , b.fURL);

                Intent intent = new Intent(MainActivity.this , WebViewActivity.class);
                intent.putExtra("url" , b.baseURL + b.fURL);
                startActivity(intent);

            }
        });


        btnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("gurl" , b.tURL);

                Intent intent = new Intent(MainActivity.this , WebViewActivity.class);
                intent.putExtra("url" , b.baseURL + b.tURL);
                startActivity(intent);

            }
        });


    }




  /*  private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            displayMessage(profile);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };*/


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //callbackManager.onActivityResult(requestCode, resultCode, data);



    }

  /*  private void displayMessage(Profile profile){
        if(profile != null){
            textView.setText(profile.getName());
        }
    }*/

    @Override
    public void onStop() {
        super.onStop();
        /*accessTokenTracker.stopTracking();
        profileTracker.stopTracking();*/
    }

    @Override
    public void onResume() {
        super.onResume();
       /* ContactsContract.Profile profile = Profile.getCurrentProfile();
        displayMessage(profile);*/
    }







    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            if (pd != null && pd.isShowing())
                pd.dismiss();

            if (msg.what == 1) {
              /*  Intent intent = new Intent(MainActivity.this, UserInfo.class);
                intent.putExtra(USER_MAP, userHashmap);
                startActivity(intent);*/
            } else if (msg.what == 2) {
                if (friendList.size() > 0) {
                    Intent intent = new Intent(MainActivity.this,
                            FriendList.class);
                    intent.putExtra(FRIEND_LIST, friendList);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "No friends found.",
                            Toast.LENGTH_SHORT).show();
                }
            }
            return false;
        }
    });





    private class PostTask extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progress.setVisibility(View.VISIBLE);


        }

        @Override
        protected String doInBackground(String... data) {


            try {
                URL url = new URL("https://test.vyabl.com/api/Account/ExternalLogins?returnUrl=%2F&generateState=true");
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                int statusCode = urlConnection.getResponseCode();
                //if (statusCode ==  200) {
                    InputStream it = new BufferedInputStream(urlConnection.getInputStream());
                    InputStreamReader read = new InputStreamReader(it);
                    BufferedReader buff = new BufferedReader(read);
                    StringBuilder dta = new StringBuilder();
                    String chunks ;
                    while((chunks = buff.readLine()) != null)
                    {
                        dta.append(chunks);
                    }

                    Log.d("data" , dta.toString());

                /*}
                else
                {
                    //Handle else
                }*/


                return dta.toString();

            } catch (IOException e) {
                e.printStackTrace();
                return "LOL NOPE";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("ddd" , s);

            try {
                JSONArray arr = new JSONArray(s);

                bean b = (bean)getApplicationContext();

                JSONObject obj0 = (JSONObject) arr.get(0);
                b.gURL = obj0.getString("Url");

                JSONObject obj1 = (JSONObject) arr.get(1);
                b.fURL = obj1.getString("Url");

                JSONObject obj2 = (JSONObject) arr.get(2);
                b.tURL = obj2.getString("Url");

                JSONObject obj3 = (JSONObject) arr.get(3);
                b.mURL = obj3.getString("Url");



            } catch (JSONException e) {
                e.printStackTrace();
            }

            progress.setVisibility(View.GONE);


        }
    }








    //The login function accepting the result object

}
