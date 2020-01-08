package com.firstpage.user.Common;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.firstpage.user.R;


public class CommonFunctions {

    public static Dialog mDialog, load_dialog;
    public static Dialog mProgressDialog;

    public static void showSimpleProgressDialog(Context context, String msg, boolean isCancelable) {
        if (context != null) {
            mProgressDialog = new Dialog(context, R.style.AppTheme);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mProgressDialog.setCancelable(false);
            mProgressDialog.setContentView(R.layout.progress_bar_layout);
            TextView tv_title = (TextView) mProgressDialog.findViewById(R.id.text_load);
            tv_title.setText(msg);
            if(!((Activity) context).isFinishing())
            {
                //show dialog
                mProgressDialog.show();
            }
        }
    }

    public static void removeProgressDialog() {
        if (null != mProgressDialog)
            mProgressDialog.dismiss();
    }

    //Short Toast
    public static void shortToast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

    }

    public static boolean isValidEmail(CharSequence email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }


    public static boolean checkLocationPermission(Context context)
    {
        String coarse = Manifest.permission.ACCESS_COARSE_LOCATION;
        String fine = Manifest.permission.ACCESS_FINE_LOCATION;
        int res = context.checkCallingOrSelfPermission(coarse);
        int res1 = context.checkCallingOrSelfPermission(fine);
        return (res == PackageManager.PERMISSION_GRANTED && res1 == PackageManager.PERMISSION_GRANTED);
    }

    public static boolean isGPSEnabled(Context mContext){
        LocationManager locationManager = (LocationManager)
                mContext.getSystemService(Context.LOCATION_SERVICE);
        boolean status = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.e("Giri ", "isGPSEnabled:status "+status );
        return status;
    }
    @SuppressLint("ResourceAsColor")
    public static void showProgressDialog(Context context, String msg, boolean isCancelable) {
        if (context != null) {
            mProgressDialog = new Dialog(context, R.style.AppTheme);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mProgressDialog.setCancelable(false);
            mProgressDialog.setContentView(R.layout.progress_bar_confirm);
            TextView tv_title = (TextView) mProgressDialog.findViewById(R.id.confirm_text);
            tv_title.setText(msg);
            if(!((Activity) context).isFinishing())
            {
                //show dialog
                mProgressDialog.show();
            }
        }
    }
    public static void removeDialog() {
        if (null != mProgressDialog)
            mProgressDialog.dismiss();
    }
    public static void showloadingProgressDialog(Context context, String msg, boolean isCancelable) {
        if (context != null) {
            mProgressDialog = new Dialog(context, R.style.AppTheme);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mProgressDialog.setCancelable(false);
            mProgressDialog.setContentView(R.layout.progress_bar_loading);
            TextView tv_title = (TextView) mProgressDialog.findViewById(R.id.confirm_text);
            tv_title.setText(msg);
            if(!((Activity) context).isFinishing())
            {
                //show dialog
                mProgressDialog.show();
            }
        }
    }
    public static void removeloadingDialog() {
        if (null != mProgressDialog)
            mProgressDialog.dismiss();
    }


}
