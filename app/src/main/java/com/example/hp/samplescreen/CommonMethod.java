package com.example.hp.samplescreen;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JeannyPrAndroid on 10/17/2016.
 */

public class CommonMethod {


    public static void showAlert(String message, Activity context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        try {
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static   void ShowLocationAccessAlert(String message, final Activity context, final LocationManager locationManager) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                 //       context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    long fortySecondsFromNow = System.currentTimeMillis() + 40*1000;
                                    while((System.currentTimeMillis() < fortySecondsFromNow)
                                            && !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                                        Thread.sleep(300);
                                    }

                                    if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                                        Intent intent = new Intent(context, MainActivity.class);
                                        intent.setAction(Intent.ACTION_MAIN);
                                        intent.addCategory(Intent.CATEGORY_LAUNCHER);
                                        context.startActivity(intent);
    //                                    Activity activity = (Activity) context;
//                                        Toast.makeText(activity,"Returning to Dashboard",Toast.LENGTH_SHORT).show();
//                                        MainActivity.getInstance().callAttendenceFragment();

                                     /*   Activity activity = (Activity) context;
                                        FragmentManager fragmentManager = activity.getFragmentManager();
                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        AttendenceFragment fragment = new AttendenceFragment();
                                        fragmentTransaction.add(R.id.container,fragment);
                                        fragmentTransaction.commit();*/
                                    }

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                    }
                });
        try {
            builder.show();
            builder.setCancelable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAlertSetting(String message, Activity context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //                     startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
        try {
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    /** Called for checking Email Validation */
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }



}
