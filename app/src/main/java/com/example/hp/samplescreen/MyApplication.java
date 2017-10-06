package com.example.hp.samplescreen;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

/*import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;*/

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by faheemahmad on 20/08/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
       /* super.onCreate();
        printHashKey();*/
        super.onCreate();
       /* FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);*/
    }

    public void printHashKey(){
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "net.simplifiedcoding.androidlogin",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
