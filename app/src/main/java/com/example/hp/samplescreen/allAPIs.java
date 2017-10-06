package com.example.hp.samplescreen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by mukul on 07/10/17.
 */

public interface allAPIs {

    @GET("api/Account/ExternalLogins")
    Call<List<externalLoginsBean>> getExternalLogins(@Query("returnUrl") String returnURL , @Query("generateState") String regenerateState);


    @GET("api/Account/UserInfo")
    Call<userInfoBean> getUserInfo(@Header("Authorization") String token);



}
