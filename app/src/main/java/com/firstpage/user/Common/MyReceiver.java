package com.firstpage.user.Common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.firstpage.user.R;

public class MyReceiver extends BroadcastReceiver {
    Snackbar snackbar;
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        if(status.isEmpty()) {
            status="No Internet Connection";
        }

        Toast.makeText(context, status, Toast.LENGTH_LONG).show();
//        snackbar = Snackbar.make(MyReceiver.this.getWindow().getDecorView().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
//        View snackView = snackbar.getView();
//        snackView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
//        snackbar.show();
    }

//    private Window getWindow() {
//
//    }


}
