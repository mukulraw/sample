package com.example.hp.samplescreen;

import com.android.volley.VolleyError;

/**
 * Created by JeannyPrAndroid on 10/17/2016.
 */

public interface VolleyCallback {
    void onSuccess(String response);
    void onError(VolleyError error);
}
