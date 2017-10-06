package com.example.hp.samplescreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

    LinearLayout textView,textView1,textView3;
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
        textView3= (LinearLayout) findViewById(R.id.btn_next);

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
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Payment_help.class);
                startActivity(intent);
            }
        });

         btnFacebook= (RelativeLayout)findViewById(R.id.btn_facebbok);

    //    LoginButton loginButton = (LoginButton) findViewById(R.id.Linear2);

         btnGoogle= (RelativeLayout)findViewById(R.id.btn_google);
       // LinearLayout mBtnTwitter= (LinearLayout)findViewById(R.id.Linear4);

        btnTwitter = (RelativeLayout)findViewById(R.id.btn_twitter);


        final bean b = (bean)getApplicationContext();

        progress.setVisibility(View.VISIBLE);

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
        });


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






    //The login function accepting the result object

}
