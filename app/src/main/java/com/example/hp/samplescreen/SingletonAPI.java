package com.example.hp.samplescreen;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by JeannyPrAndroid on 10/17/2016.
 */

public class SingletonAPI {

    private static SingletonAPI mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private SingletonAPI(Context context){
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized SingletonAPI getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SingletonAPI(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setRetryPolicy(new DefaultRetryPolicy(
                9000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }
}
