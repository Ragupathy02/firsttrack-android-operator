package com.firstpage.user.Ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firstpage.user.Adapter.TabAdapter;
import com.firstpage.user.Common.CONST;
import com.firstpage.user.Common.CommonFunctions;
import com.firstpage.user.Common.NetworkChangeReceiver;
import com.firstpage.user.Common.SessionManager;
import com.firstpage.user.Common.SharedPreference;
import com.firstpage.user.Fragment.OngoingsupervisorFragment;
import com.firstpage.user.Fragment.ProfilesupervisorFragment;
import com.firstpage.user.Model.Dummy;
import com.firstpage.user.R;
import com.nex3z.notificationbadge.NotificationBadge;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;

public class Workboardsupervisor_Activity extends AppCompatActivity {


    ArrayList<String> processdetail = new ArrayList<String>();


    TabLayout tabLayout;
    FrameLayout simpleFrameLayout;
    AppCompatEditText et_search;
    private TabAdapter adapter;
    AppCompatImageView iv_notification;
    private ViewPager viewPager;
    SessionManager session;
    SharedPreference sharedPreference;
    String name;
    public static String query;
    Boolean isShow = true;
    public AppCompatImageView iv_searches;
    OngoingsupervisorFragment ongoingsupervisorFragment;
    private OkHttpClient client;
    String id;
    private WebSocketClient mWebSocketClient;
    int count = 0;
    public android.support.v7.app.AlertDialog deleteDialog;
    public NotificationBadge notificationBadge;
    private BroadcastReceiver MyReceiver = null;
    Dummy dummy;
    private BroadcastReceiver mNetworkReceiver;
    static TextView tv_check_connection;
    public static LinearLayout linearLayout_workboard;
    public static android.support.v7.app.AlertDialog exception_dialog;
    public static AlertDialog.Builder alertDialogBuilder;
    public static AlertDialog alertDialog;
    public static Button Btnok;
    String exception_text;
    public AppCompatButton btn_exception;


    //    private final class EchoWebSocketListener extends WebSocketListener {
//        private static final int NORMAL_CLOSURE_STATUS = 1000;
//        @Override
//        public void onOpen(WebSocket webSocket, Response response) {
//            Log.e("ragu", "onOpen Socket test: " );
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("action","INIT");
//                jsonObject.put("_id",id);
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//
//            }
//            webSocket.send(String.valueOf(jsonObject));
//            Log.e("ragu", "onOpen: String "+String.valueOf(jsonObject) );
//            Log.e("ragu", "onOpen: Socket "+response.message());
//            webSocket.close(NORMAL_CLOSURE_STATUS, "finish");
//        }
//        @Override
//        public void onMessage(WebSocket webSocket, String text) {
//            Log.e("ragu", "onMessage: text "+text );
////            output("Receiving : " + text);
//        }
//        @Override
//        public void onMessage(WebSocket webSocket, ByteString bytes) {
////            output("Receiving bytes : " + bytes.hex());
//        }
//        @Override
//        public void onClosing(WebSocket webSocket, int code, String reason) {
//            webSocket.close(NORMAL_CLOSURE_STATUS, null);
//            webSocket.cancel();
//            Log.e("ragu", "CLOSE: " + code + " " + reason);
////            output("Closing : " + code + " / " + reason);
//        }
//        @Override
//        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
//            Log.e("ragu", "onFailure: message "+t.getMessage() );
////            output("Error : " + t.getMessage());
//        }
//    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if(!isConnected(Workboardsupervisor_Activity.this))
//        {
//            Log.e("ragu", "onCreate: connection " );
//            final BottomSheetDialogFragment myBottomSheet = MyBottomSheetDialogFragment.newInstance("Modal Bottom Sheet");
//            myBottomSheet.show(getSupportFragmentManager(), myBottomSheet.getTag());
//
//        }
//       else {
//       }
        setContentView(R.layout.activity_workboard);
        sharedPreference = new SharedPreference();
        id = sharedPreference.getString(getApplicationContext(), "id");
        tabLayout = (TabLayout) findViewById(R.id.simpleTabLayout);
        //getsocketconnection();
//        start();
        et_search = (AppCompatEditText) findViewById(R.id.et_search_order_id);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        iv_searches = (AppCompatImageView) findViewById(R.id.iv_search);
        btn_exception = (AppCompatButton) findViewById(R.id.btn_exception);
        linearLayout_workboard = (LinearLayout) findViewById(R.id.work_relative);
        tv_check_connection = (TextView) findViewById(R.id.tv_check_connection);
//        exception_text=sharedPreference.getString(getApplicationContext(),"exception");
//        if(exception_text.isEmpty())
//        {
//            Log.e("ragu", "onCreate:no data " );
//        }
//        else {
//            Log.e("ragu", "onexception:date "+exception_text );
//        }

        mNetworkReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastForNougat();
//        MyReceiver = new MyReceiver();
//        broadcastIntent();
        session = new SessionManager(getApplicationContext());
        count++;
        notificationBadge = (NotificationBadge) findViewById(R.id.im_notifications);
        notificationBadge.setText("0");
        notificationBadge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "you clicked", Toast.LENGTH_LONG).show();
            }
        });
        Log.e("ragu", "onCreate:count" + count);

        Log.e("ragu", "onCreate: id is " + id);
//        HashMap<String, String> user = session.getUserDetails();
//        String email = user.get(SessionManager.KEY_EMAIL);
//        start();
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    et_search.clearFocus();
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        iv_searches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // senddata();
            }
        });

//        InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputManager.toggleSoftInput(0, 0);


//        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    if(et_search.getText().toString().isEmpty()){
//
//
//                    }else{
//                        et_search.setCursorVisible(false);
//
//                    }
//
//
//                }
//                return false;
//            }
//        });
        adapter = new TabAdapter(getSupportFragmentManager());
        ongoingsupervisorFragment = new OngoingsupervisorFragment(Workboardsupervisor_Activity.this);
        adapter.addFragment(ongoingsupervisorFragment, "OnGoing");
        adapter.addFragment(new ProfilesupervisorFragment(), "Profile");
        iv_notification = (AppCompatImageView) findViewById(R.id.im_notification);
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(getResources().getColor(R.color.tab_colour));
        drawable.setSize(1, 1);
        linearLayout.setDividerPadding(40);
        linearLayout.setDividerDrawable(drawable);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
//        EventBus.getDefault().post(new Dummy("sandy"));
        Log.e("ragu", "onCreate:dummy " + dummy);
        // the text status
//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected()) {
//            changeTextStatus(true);
//        } else {
//            changeTextStatus(false);
//        }

//        if(count==1) {
//            new SimpleTooltip.Builder(this)
//                    .anchorView(iv_notification)
//                    .textColor(getResources().getColor(R.color.screen_status_color))
//                    .text("This is Notification")
//                    .gravity(Gravity.BOTTOM)
//                    .animated(true)
//                    .transparentOverlay(false)
//                    .build()
//                    .show();
//        }
//        else {
//            new SimpleTooltip.Builder(this)
//                    .anchorView(iv_notification)
//                    .textColor(getResources().getColor(R.color.screen_status_color))
//                    .text("This is Notification")
//                    .gravity(Gravity.BOTTOM)
//                    .animated(false)
//                    .transparentOverlay(false)
//                    .build()
//                    .dismiss();
//
//        }
//        ongoingsupervisorFragment.search(et_search.getText().toString());

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ongoingsupervisorFragment.search(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("ragu", "afterTextChanged: ");

            }
        });
//        dummy = new Dummy();
//        dummy.setName("santhosh");
//        EventBus.getDefault().post(dummy);


//    new SimpleTooltip.Builder(this)
//            .anchorView(iv_notification)
//            .textColor(getResources().getColor(R.color.screen_status_color))
//            .text("This is Notification")
//            .gravity(Gravity.BOTTOM)
//            .animated(true)
//            .transparentOverlay(false)
//            .build()
//            .show();


//         Tooltip.Builder builder = new Tooltip.Builder(iv_notification, R.style.Tooltip)
//                .setCancelable(true)
//                .setDismissOnClick(false)
//                .setBackgroundColor(getResources().getColor(R.color.colorAccent))
//                .setGravity(Gravity.BOTTOM)
//                 .setCornerRadius(10f)
//                .setText("This is a Notification");
//        builder.show();

        iv_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonFunctions.shortToast(getApplicationContext(), "You Clicked Notification");
            }
        });
//
    }

    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void dialog(boolean value, final Context context) {

        if (value) {
            alertDialog.dismiss();

//            Toast.makeText(context,"hello",Toast.LENGTH_LONG).show();
//            tv_check_connection.setText("We are back !!!");
//            tv_check_connection.setBackgroundColor(Color.GREEN);
//            tv_check_connection.setTextColor(Color.WHITE);

            Handler handler = new Handler();
            Runnable delayrunnable = new Runnable() {
                @Override
                public void run() {
                    tv_check_connection.setVisibility(View.GONE);
                }
            };
            handler.postDelayed(delayrunnable, 3000);
        } else {
            LayoutInflater factory = LayoutInflater.from(context);
            final View Exceptiondialogview = factory.inflate(R.layout.internet_check, null);
            exception_dialog = new AlertDialog.Builder(context).create();
//            exception_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;


//            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            exception_dialog.setView(Exceptiondialogview);
            Btnok = (AppCompatButton) Exceptiondialogview.findViewById(R.id.btn_internet);
            Exceptiondialogview.findViewById(R.id.btn_internet).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) context).finishAffinity();


//                    if(checkexception())
//                    {
////                            Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
//                        commentexception();
//                        exception_dialog.dismiss();
//                    }
                }
            });


            exception_dialog.show();
//            CustomAlertDialog dialog = new CustomAlertDialog(context, CustomAlertDialog.DialogStyle.DEFAULT);
//            dialog.setDialogType(CustomAlertDialog.DialogType.WARNING);
//          dialog.setAlertTitle("No Internet Connection You need to have mobile data or wifi to access this");
//
//
//
//            dialog.create();
//            dialog.show();


//             alertDialogBuilder = new AlertDialog.Builder(context);
//            // Setting Alert Dialog Title
//            alertDialogBuilder.setTitle("No Internet Connection");
//            // Icon Of Alert Dialog
//            // Setting Alert Dialog Message
//           alertDialogBuilder.setMessage("You need to have mobile data or wifi to access this");
//
//            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface arg0, int arg1) {
//                    ((Activity) context).finishAffinity();
//
//
//                }
//            });
//
//             alertDialog = alertDialogBuilder.create();
//            alertDialog.show();

//            Toast.makeText(context,"hello",Toast.LENGTH_LONG).show();
//            final BottomSheetDialogFragment myBottomSheet = MyBottomSheetDialogFragment.newInstance("Modal Bottom Sheet");
//           myBottomSheet.show(getSupportFragmentManager(), myBottomSheet.getTag());
//            CommonFunctions.removeProgressDialog();

//            Snackbar snackbar = Snackbar.make(linearLayout_workboard,"Check Your Network Connection!",Snackbar.LENGTH_LONG);
//            View snackbarView = snackbar.getView();
//            snackbarView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
//            snackbar.show();


//            tv_check_connection.setVisibility(View.VISIBLE);
//            tv_check_connection.setText("Could not Connect to internet");
//            tv_check_connection.setBackgroundColor(Color.RED);
//            tv_check_connection.setTextColor(Color.WHITE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }


    private void broadcastIntent() {
        Log.e("ragu", "broadcastIntent:network ");
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
//        unregisterReceiver(MyReceiver);
    }

//    public void changeTextStatus(boolean b) {
//        if (b) {
//            Toast.makeText(Workboardsupervisor_Activity.this,"your status internet connected",Toast.LENGTH_LONG).show();
////            internetStatus.setText("Internet Connected.");
////            internetStatus.setTextColor(Color.parseColor("#00ff00"));
//        } else {
//            Toast.makeText(Workboardsupervisor_Activity.this,"your status not internet connected",Toast.LENGTH_LONG).show();
////            internetStatus.setText("Internet Disconnected.");
////            internetStatus.setTextColor(Color.parseColor("#ff0000"));
//        }
//    }
//    @Override
//    protected void onPause() {
//
//        super.onPause();
//        MyApplication.activityPaused();// On Pause notify the Application
//    }
//
//    @Override
//    protected void onResume() {
//
//        super.onResume();
//        MyApplication.activityResumed();// On Resume notify the Application
//    }
//


//    private void senddata() {
//        try {
//            Bundle bundle = new Bundle();
//            bundle.putString("key",et_search.getText().toString());
//            OngoingsupervisorFragment fragment = new OngoingsupervisorFragment();
//            fragment.setArguments(bundle);
////            FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
////            fts.replace(R.id.framelayout, fragment);
////            fts.addToBackStack(fragment.getClass().getSimpleName());
////            fts.commit();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            LayoutInflater factory = LayoutInflater.from(Workboardsupervisor_Activity.this);
            final View deleteDialogView = factory.inflate(R.layout.alert_layout, null);
            deleteDialog = new AlertDialog.Builder(Workboardsupervisor_Activity.this).create();
            deleteDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            deleteDialog.setView(deleteDialogView);
            deleteDialogView.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finishAffinity();

                }
            });
            deleteDialogView.findViewById(R.id.btn_no).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteDialog.dismiss();
                }
            });
            deleteDialog.show();

        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        Log.e("ragu", "isConnected: network" + cm);

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog buildDialog(Context c) {
        LayoutInflater factory = LayoutInflater.from(Workboardsupervisor_Activity.this);
        final View deleteDialogView = factory.inflate(R.layout.cust_layout, null);
        deleteDialog = new android.support.v7.app.AlertDialog.Builder(Workboardsupervisor_Activity.this).create();
        deleteDialog.setView(deleteDialogView);
        deleteDialogView.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
        return deleteDialog;


//        final AlertDialog.Builder builder = new AlertDialog.Builder(c);
//        builder.setTitle("No Internet Connection");
//        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");
//
//        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//
//            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finishAffinity();
//            }
//        });
//
//        return builder;
    }

    //    private void start() {
//        Request request = new Request.Builder().url("ws://3.13.34.41:8081").build();
//        EchoWebSocketListener listener = new EchoWebSocketListener();
//        WebSocket ws = client.newWebSocket(request, listener);
//        client.dispatcher().executorService().shutdown();
//    }
    private void getsocketconnection() {
        URI uri;
        String URL = CONST.Websocket_URL;
        try {
            uri = new URI(URL);
            Log.e("ragu", "getsocketconnection: uri" + uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("ragu", "getsocketconnection: connect " + e.toString());
            return;
        }
        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.e("ragu", "onOpened: ");
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("action", "INIT");
                    jsonObject.put("_id", id);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mWebSocketClient.send(String.valueOf(jsonObject));
                Log.e("ragu", "onOpen: jsonobject " + jsonObject);
            }

            @Override
            public void onMessage(String message) {
                Log.e("ragu", "onMessage: " + message);
                try {
                    JSONObject jsonObject = new JSONObject(message);
                    Log.e("ragu", "onMessage: " + jsonObject);
                    Log.e("ragu", "onMessage:json " + jsonObject.getString("message"));
                    Log.e("ragu", "onMessage: " + jsonObject.getBoolean("status"));

                } catch (Exception e) {

                }

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.e("ragu", "onClose: " + reason);
            }

            @Override
            public void onError(Exception ex) {
                Log.e("ragu", "onError: " + ex);
            }
        };
        mWebSocketClient.connect();
    }
}
