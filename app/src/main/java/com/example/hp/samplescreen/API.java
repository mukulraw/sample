package com.example.hp.samplescreen;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by faheemahmad on 21/08/17.
 */

public class API {

    private Context activityContext;

    public API(Context context){
        activityContext = context;
    }

    public void SignUp(final String firstname, final String lastname , final String email, final String password, final VolleyCallback callback){
        StringRequest strRequest = new StringRequest(Request.Method.POST, APIConstants.BASEAPI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("group response:", "" + response);

                        callback.onSuccess(response);
                        //
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("response:", "" + error);
                        callback.onError(error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("FirstName", firstname);
                params.put("LastName", lastname);
                params.put("Email", email);
                params.put("Password", password);
                params.put("ConfirmPassword", password);
                return params;
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }

        };

        SingletonAPI.getInstance(activityContext).addToRequestQueue(strRequest);
    }



}
