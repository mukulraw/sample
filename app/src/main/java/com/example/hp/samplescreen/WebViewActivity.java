package com.example.hp.samplescreen;

import android.app.ProgressDialog;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.json.JSONArray;
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
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class WebViewActivity extends AppCompatActivity {

    WebView web;

    String url111;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        dialog = new ProgressDialog(WebViewActivity.this);


        url111 = getIntent().getStringExtra("url");

        Log.d("url" , url111);

        web = (WebView)findViewById(R.id.webview);

        web.getSettings().setUserAgentString("Mozilla/5.0 (Linux; U; Android 2.2; en-gb; Nexus One Build/FRF50) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");

        //      web.loadUrl("https://test.vyabl.com/api/Account/ExternalLogin?provider=Twitter&response_type=token&client_id=self&redirect_uri=https%3A%2F%2Ftest.vyabl.com%2F&state=Ncm0vao1vGn_7O4YNi-p5-1VQgnIZPuxcvYAx93E9zQ1");
        web.loadUrl(url111);

        web.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed() ;
            }

            @Override
            public boolean shouldOverrideUrlLoading (WebView view, String url){
                //Code to parse url

                Log.d("url2" ,url);

                String sub = url.substring(0 , 24);

                Log.d("sub" , sub);

                if (Objects.equals(sub, "https://test.vyabl.com/#"))
                {

                    String[] spl = url.split("&");
                    String tokURL = spl[0];
                    Log.d("tokURL" , tokURL);

                    String[] spl2 = tokURL.split("/");

                    String tok = spl2[3];

                    Log.d("tok" , tok);

                    String token = tok.substring(14 , tok.length());

                    Log.d("token" , token);


                    final bean b = (bean)getApplicationContext();

                    b.access = "Bearer " + token;



                    dialog.setMessage("Logging In...");
                    dialog.setCancelable(false);
                    dialog.show();





                    PostTask pt = new PostTask();
                    pt.execute();







                    /*Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    final allAPIs cr = retrofit.create(allAPIs.class);

                    Log.d("ttookkeenn" , b.access);

                    Call<userInfoBean> call = cr.getUserInfo(b.access);

                    call.enqueue(new Callback<userInfoBean>() {
                        @Override
                        public void onResponse(Call<userInfoBean> call, Response<userInfoBean> response) {

                            Log.d("has" , String.valueOf(response.body().getHasRegistered()));

                            try {
                                if (!response.body().getHasRegistered())
                                {

                                    sendBean bea = new sendBean();

                                    bea.setEmail(response.body().getEmail());

                                    Call<String> call2 = cr.registerExternal("application/json" , b.access , bea);

                                    call2.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {

                                            web.loadUrl(url111);

                                            dialog.dismiss();

                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {

                                        }
                                    });











                                }
                                else
                                {

                                    Toast.makeText(WebViewActivity.this , "Successfully Logged In" , Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    finish();


                                }
                            }catch (Exception e)
                            {
                                Toast.makeText(WebViewActivity.this , "Successfully Logged In" , Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                finish();

                                e.printStackTrace();
                            }





                        }

                        @Override
                        public void onFailure(Call<userInfoBean> call, Throwable t) {

                            Toast.makeText(WebViewActivity.this , t.toString() , Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            finish();

                        }
                    });
*/






                }

                return false;
            }
        });



    }



    private class PostTask extends AsyncTask<String, String, String> {



        @Override
        protected String doInBackground(String... data) {

            try {
                URL url = new URL("https://test.vyabl.com/api/Account/UserInfo");
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");

                bean b = (bean)getApplicationContext();

                urlConnection.setRequestProperty("Authorization",b.access);

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

                JSONObject obj = new JSONObject(s);

                String email = obj.getString("Email");

                Boolean has = obj.getBoolean("HasRegistered");

                if (has)
                {
                    Toast.makeText(WebViewActivity.this , "Successfully Logged In" , Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    finish();
                }
                else
                {


                    JSONObject oo = new JSONObject();
                    oo.put("Email" , email);

                    PostTask2 pp = new PostTask2(oo.toString());

                    pp.execute();



                    /*call2.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            web.loadUrl(url111);

                            dialog.dismiss();

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });*/
                }





            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
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

                urlConnection.setRequestProperty("Authorization",b.access);
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
            web.loadUrl(url111);




        }
    }





}