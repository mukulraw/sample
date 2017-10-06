package com.example.hp.samplescreen;

import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    WebView web;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        url = getIntent().getStringExtra("url");

        Log.d("url" , url);

        web = (WebView)findViewById(R.id.webview);

        web.loadUrl(url);

        web.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed() ;
            }

            @Override
            public boolean shouldOverrideUrlLoading (WebView view, String url){
                //Code to parse url

                Log.d("url2" ,url);

                return false;
            }
        });



    }
}
