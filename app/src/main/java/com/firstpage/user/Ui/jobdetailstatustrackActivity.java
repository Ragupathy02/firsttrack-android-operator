package com.firstpage.user.Ui;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firstpage.user.Adapter.jobStatusadapter;
import com.firstpage.user.Common.CONST;
import com.firstpage.user.Common.CommonFunctions;
import com.firstpage.user.Common.NetworkChangeReceiver;
import com.firstpage.user.Common.SharedPreference;
import com.firstpage.user.Interfaces.APIClient;
import com.firstpage.user.Interfaces.ApiInterface;
import com.firstpage.user.Model.Exceptioncheck;
import com.firstpage.user.Model.Exceptioncheck_response;
import com.firstpage.user.Model.Exceptionerror;
import com.firstpage.user.Model.Ongoing;
import com.firstpage.user.Model.Ongoing_response;
import com.firstpage.user.R;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import retrofit2.Call;
import retrofit2.Callback;
public class jobdetailstatustrackActivity extends AppCompatActivity {
    public AppCompatTextView tv_job_id;
    public AppCompatTextView tv_customer_name;
    public AppCompatTextView tv_purchase_order;
    public AppCompatTextView tv_line_no;
    public AppCompatTextView tv_qty;
    public AppCompatTextView tv_order_started;
    public AppCompatTextView tv_schedule;
    public AppCompatTextView tv_clean;
    public AppCompatTextView tv_cutting;
    public AppCompatTextView tv__packing;
    public AppCompatButton btn_exception;
    public AppCompatButton btn_start;
    public AppCompatButton btn_finish;
    public AppCompatButton btn_stop;
    public AppCompatImageView im_back;
    ImageView im_clean;
    SharedPreference sharedPreference;
    Socket socket;
    private OkHttpClient client;
    private OkHttpClient okHttpClient;
    private OkHttpClient httpClient;
    public static android.support.v7.app.AlertDialog exception_dialog;
    public static android.support.v7.app.AlertDialog subdialog;
    Ongoing_response ongoing_response;
    WebSocket webSocket;
    String Processid;
    String Processname;
    public ScrollView scrollView;
    String process;
    //    String process_id;
    JSONObject stopjson;
    private WebSocketClient mWebSocketClient;
    public RecyclerView job_statusRecycle;
    Ongoing ongoing;
    public jobStatusadapter job_status_adapter;
    public EditText etException_text;
    public AppCompatButton btnOk;
    public RelativeLayout jobStatus_relative;
    String id_user;
    private BroadcastReceiver mNetworkReceiver;
    public static Button Btnok;
    public static AlertDialog alertDialog;
    static TextView tv_check_connection;
    AppCompatEditText et_exceptionfeed;
    List<String> processlist;
    String total_process;
    String totalorder;
    ArrayAdapter<String> ordertracker;
    Spinner spin_process;
    ArrayAdapter<String> dataAdapter;
    String Lineid;
    String Id;
    AppCompatTextView tv_date;
    DatePickerDialog picker;
//    public void getstopmethod(String stop) {
//    }

//    public void getmethod(String start) {
//
//    }


//    private final class EchoWebSocketListener extends WebSocketListener {
//        private static final int NORMAL_CLOSURE_STATUS = 1000;
//
//        @Override
//        public void onOpen(WebSocket webSocket, Response response) {
//            Log.e("Ragu", "onOpen: socket " + webSocket.toString());
//
//            Log.e("ragu", "onOpen: Start ");
//            JSONObject jsonObject = new JSONObject();
//            try {
//                Log.e("ragu", "onOpen: try ");
//                jsonObject.put("action", "LINE_NUMBER_STATUS_UPDATE");
//                jsonObject.put("_id", Processid);
//                jsonObject.put("status", 2);
//            } catch (Exception e) {
//                Log.e("ragu", "onOpen: exception " + e);
//            }
//            webSocket.send(String.valueOf(jsonObject));
//            Log.e("ragu", "onOpen: Jsonobject " + jsonObject);
//            Log.e("ragu", "onOpen: start response" + response);
//            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
//        }
//
//        @Override
//        public void onMessage(WebSocket webSocket, String text) {
//            Log.e("ragupathy", "onMessage: text " + text);
////            output("Receiving : " + text);
//        }
//
//        @Override
//        public void onMessage(WebSocket webSocket, ByteString bytes) {
////            output("Receiving bytes : " + bytes.hex());
//        }
//
//        @Override
//        public void onClosing(WebSocket webSocket, int code, String reason) {
//            Log.e("ragu", "c ");
////            webSocket.close(NORMAL_CLOSURE_STATUS, null);
//            webSocket.cancel();
//            Log.e("ragu", "CLOSE: " + code + " " + reason);
////            output("Closing : " + code + " / " + reason);
//        }
//
//        @Override
//        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
//            try {
//                Log.e("ragu", "onFailure: socket " + t.getMessage());
//
//            } catch (Exception e) {
//                Log.e("ragu", "onFailure: exception" + e);
//
//            }
//
////            output("Error : " + t.getMessage());
//        }
//    }

//    private final class EchoWebSocketListeners extends WebSocketListener {
//        private static final int NORMAL_CLOSURE_STATUS = 1000;
//
//        @Override
//        public void onOpen(WebSocket webSocket, Response response) {
//            Log.e("Ragu", "onOpen: socket " + webSocket.toString());
//            Log.e("ragu", "onOpen: Start ");
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("action", "LINE_NUMBER_STATUS_UPDATE");
//                jsonObject.put("_id", Processid);
//                jsonObject.put("status", 3);
//
//            } catch (Exception e) {
//                Log.e("ragu", "onOpen: exception " + e);
//            }
//            webSocket.send(String.valueOf(jsonObject));
//            Log.e("ragu", "onOpen: response " + response);
//            Log.e("ragu", "onOpen: Jsonobject" + jsonObject);
//            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
//        }
//
//        @Override
//        public void onMessage(WebSocket webSocket, String text) {
////            output("Receiving : " + text);
//        }
//
//        @Override
//        public void onMessage(WebSocket webSocket, ByteString bytes) {
////            output("Receiving bytes : " + bytes.hex());
//        }
//
//        @Override
//        public void onClosing(WebSocket webSocket, int code, String reason) {
//            webSocket.close(NORMAL_CLOSURE_STATUS, null);
//            webSocket.cancel();
//            Log.e("ragu", "CLOSE: " + code + " " + reason);
////            output("Closing : " + code + " / " + reason);
//        }
//
//        @Override
//        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
////            output("Error : " + t.getMessage());
//        }
//    }

//    private final class EchoWebSocketfinishListeners extends WebSocketListener {
//        private static final int NORMAL_CLOSURE_STATUS = 1000;
//
//        @Override
//        public void onOpen(WebSocket webSocket, Response response) {
//            Log.e("Ragu", "onOpen: socket " + webSocket.toString());
//            Log.e("ragu", "onOpen: Start ");
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("action", "LINE_NUMBER_STATUS_UPDATE");
//                jsonObject.put("_id", Processid);
//                jsonObject.put("status", 4);
//            } catch (Exception e) {
//                Log.e("ragu", "onOpen: exception " + e);
//            }
//            webSocket.send(String.valueOf(jsonObject));
//            Log.e("ragu", "onOpen: response " + response);
//            Log.e("ragu", "onOpen: Jsonobject" + jsonObject);
//            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
//        }
//
//        @Override
//        public void onMessage(WebSocket webSocket, String text) {
////            output("Receiving : " + text);
//        }
//
//        @Override
//        public void onMessage(WebSocket webSocket, ByteString bytes) {
////            output("Receiving bytes : " + bytes.hex());
//        }
//
//        @Override
//        public void onClosing(WebSocket webSocket, int code, String reason) {
//            webSocket.close(NORMAL_CLOSURE_STATUS, null);
//            webSocket.cancel();
//            Log.e("ragu", "CLOSE: " + code + " " + reason);
////            output("Closing : " + code + " / " + reason);
//        }
//
//        @Override
//        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
////            output("Error : " + t.getMessage());
//        }
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobdetails_adapter_activity);
        client = new OkHttpClient();
        okHttpClient = new OkHttpClient();
        httpClient = new OkHttpClient();
        tv_job_id = (AppCompatTextView) findViewById(R.id.tv_job_id);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        String jobno = b.getString("jobno");

        String lineno = b.getString("Line_number");

        String quantity = b.getString("quantity");

        String purchase_order_no = b.getString("purchase_order");
        tv_job_id.setText("" + jobno);

        tv_customer_name = (AppCompatTextView) findViewById(R.id.tv_customer_name);
        tv_purchase_order = (AppCompatTextView) findViewById(R.id.tv_purchase_order);
        tv_purchase_order.setText("" + purchase_order_no);
        tv_line_no = (AppCompatTextView) findViewById(R.id.tv_line_no);
        tv_line_no.setText("" + lineno);
        tv_qty = (AppCompatTextView) findViewById(R.id.tv_quantity);
        tv_qty.setText("" + quantity);
        tv_order_started = (AppCompatTextView) findViewById(R.id.tv_process_order);
        tv_schedule = (AppCompatTextView) findViewById(R.id.tv_process_schedule);
        tv_clean = (AppCompatTextView) findViewById(R.id.tv_process_clean);
        tv_cutting = (AppCompatTextView) findViewById(R.id.tv_process_cutting);
        tv__packing = (AppCompatTextView) findViewById(R.id.tv_process_packing);
        btn_exception = (AppCompatButton) findViewById(R.id.btn_exception);
        btn_start = (AppCompatButton) findViewById(R.id.btn_start);
        btn_finish = (AppCompatButton) findViewById(R.id.btn_finish);
        im_back = (AppCompatImageView) findViewById(R.id.iv_back);
        jobStatus_relative = (RelativeLayout) findViewById(R.id.job_status_relative);
        tv_check_connection = (TextView) findViewById(R.id.tv_check_connection);
        scrollView = (ScrollView) findViewById(R.id.scroll);
//        scrollView.fullScroll(ScrollView.FOCUS_UP);
        mNetworkReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastForNougat();

        sharedPreference = new SharedPreference();
        id_user = sharedPreference.getString(getApplicationContext(), "id_user");
        processlist = new ArrayList<String>();
        Log.e("ragu", "onCreate:user id " + id_user);
        getsocketinitconnection();
//        etException_text=(EditText) findViewById(R.id.et_exception_text);
//        btnOk=(AppCompatButton)findViewById(R.id.btn_ok);
        ongoing = (Ongoing) getIntent().getSerializableExtra("keyarray");
        Log.e("ragu", "onCreate:linenumber id " + ongoing.getLine_numbers().get_id());
         Id = ongoing.get_id();

        for (int i1 = 0; i1 < ongoing.getOrder_process().size(); i1++) {
            Log.e("ragu", "onCreate:process name " + ongoing.getOrder_process().get(i1).getProcess().getProcess_name());
            if(ongoing.getOrder_process().get(i1).getStatus()!=4)
            {
                btn_exception.setVisibility(View.VISIBLE);
                break;
            }
            else {
                btn_exception.setVisibility(View.GONE);
                totalorder = ongoing.getOrder_process().get(i1).getProcess().getProcess_name();
//                Log.e("ragu", "onCreate:exception false " );
                processlist.add(totalorder);

            }


//            if(ongoing.getOrder_process().get(i1).getStatus()==4)
//            {
//                Log.e("ragu", "onCreate:exception true " );
//                btn_exception.setVisibility(View.GONE);
//
//            }
//            else if(ongoing.getOrder_process().get(i1).getStatus()!=4)
//            {
//
//                totalorder = ongoing.getOrder_process().get(i1).getProcess().getProcess_name();
//                Log.e("ragu", "onCreate:exception false " );
//                processlist.add(totalorder);
//            }

//            Set<String> set = new HashSet<>(processlist);
//            processlist.clear();
//            processlist.addAll(set);

            Log.e("ragu", "onCreate:ttal " + processlist);


//            Log.e("ragu", "onCreate:arraylist "+processlist );
//            Log.e("ragu", "onCreate:process "+ongoing.getOrder_process().get(i).getProcess_time() );
//            Log.e("ragu", "onCreate: "+ongoing.getOrder_process().get(i).getStatus() );
//            Log.e("ragu", "onCreate: "+ongoing.getOrder_process().get(i).getOrder() );
//            Log.e("ragu", "onCreate:process name "+ongoing.getOrder_process().get(i).getProcess().getProcess_name() );
        }
        processlist.add(0, "Select Exception Process");


//         dataAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, processlist);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


//        btn_stop=(AppCompatButton)findViewById(R.id.btn_stop1);
        im_clean = (ImageView) findViewById(R.id.im_clean_timeline);
        job_statusRecycle = (RecyclerView) findViewById(R.id.job_status_recycle);
//        job_statusRecycle.setNestedScrollingEnabled(false);
//        job_statusRecycle.setLayoutFrozen(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        job_statusRecycle.setLayoutManager(layoutManager);


//        for (int childCount = job_statusRecycle.getChildCount(), i1 = 0; i1 < childCount; ++i1) {
//            final RecyclerView.ViewHolder holder = job_statusRecycle.getChildViewHolder(job_statusRecycle.getChildAt(i1));
//            Log.e("ragu", "onCreate:viewholder "+holder );
//
//        }


//        int firstVisiblePosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
//        Log.e("ragu", "onCreate: demoacivity"+firstVisiblePosition );

//        String jobno = sharedPreference.getString(getApplicationContext(), "Jobno");
//        tv_job_id.setText("" + jobno);

//        String lno = sharedPreference.getString(getApplicationContext(), "lno");
//        tv_line_no.setText("" + lno);
//        String quantity = sharedPreference.getString(getApplicationContext(), "quantity");
//        tv_qty.setText("" + quantity);
//        String purchase_order = sharedPreference.getString(getApplicationContext(), "porder");
//        tv_purchase_order.setText("" + purchase_order);
        String name = sharedPreference.getString(getApplicationContext(), "name");
        tv_customer_name.setText("" + name);
//        subdialog.setCanceledOnTouchOutside(true);
//        btn_exception.setBackgroundResource(R.drawable.exception_btn);
//        btn_exception.setTextColor(getResources().getColor(R.color.welcome_textcolour));

        dataAdapter = new ArrayAdapter<String>(
                this, R.layout.simple_spinner_item, processlist) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;

                } else {
                    return true;
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @SuppressLint("WrongConstant")
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {

                    tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    // Set the hint text color gray
//                    tv.setTextColor(getResources().getColor(R.color.colorAccent));
//                    tv.setText("Select a Exception process");
//                    tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
//                    tv.setGravity(Gravity.CENTER);

                } else {
                    tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_packing, 0, 0, 0);
//                    tv.setTextColor(getResources().getColor(R.color.spinner_textcolour));
//                    tv.setTypeface(tv.getTypeface(), Typeface.NORMAL);
                }
                return view;
            }
        };
        dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);


//        process_id = sharedPreference.getString(getApplicationContext(), "processid");
        getjobdata();

        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WorkboardActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });
        btn_exception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_exception.setBackgroundResource(R.drawable.exception_click_bg);
                btn_exception.setTextColor(getResources().getColor(R.color.exception_text_color));
                LayoutInflater layoutInflater = LayoutInflater.from(jobdetailstatustrackActivity.this);
                final View dialogview = layoutInflater.inflate(R.layout.exception_dialoglayout, null);
                subdialog = new AlertDialog.Builder(jobdetailstatustrackActivity.this).create();

                subdialog.setView(dialogview);

                subdialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        btn_exception.setBackgroundResource(R.drawable.exception_btn);
                        btn_exception.setTextColor(getResources().getColor(R.color.welcome_textcolour));

                    }
                });


//                AppCompatTextView tv_exceptionprocess=(AppCompatTextView)dialogview.findViewById(R.id.tv_slctexception);
                et_exceptionfeed = (AppCompatEditText) dialogview.findViewById(R.id.et_exception_feedback);
                spin_process = (Spinner) dialogview.findViewById(R.id.spinner);
                final AppCompatTextView tv_dummy_text = (AppCompatTextView) dialogview.findViewById(R.id.dummy_text);
                AppCompatImageView iv_spinn_image = (AppCompatImageView) dialogview.findViewById(R.id.spinn_image);
                   tv_date = (AppCompatTextView)dialogview.findViewById(R.id.tv_date);
                AppCompatImageView im_calendar = (AppCompatImageView)dialogview.findViewById(R.id.iv_calendar);

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy ");
                Date date = new Date();
//                System.out.println(formatter.format(date));
                tv_date.setText(""+formatter.format(date));
                im_calendar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Calendar cldr = Calendar.getInstance();
                        int day = cldr.get(Calendar.DAY_OF_MONTH);
                        int month = cldr.get(Calendar.MONTH);
                        int year = cldr.get(Calendar.YEAR);
                        picker = new DatePickerDialog(jobdetailstatustrackActivity.this,
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                        tv_date.setText(dayOfMonth + "/" + "0" + (monthOfYear + 1) + "/" + year);
                                    }
                                }, year, month, day);
                        picker.show();

                    }
                });
//                im_calendar.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        final Calendar cldr = Calendar.getInstance();
//                        int day = cldr.get(Calendar.DAY_OF_MONTH);
//                        int month = cldr.get(Calendar.MONTH);
//                        int year = cldr.get(Calendar.YEAR);
//                        picker = new DatePickerDialog(getApplicationContext(),
//                                new DatePickerDialog.OnDateSetListener() {
//                                    @Override
//                                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                        tv_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
//                                    }
//                                }, year, month, day);
//                        picker.show();
//                    }
//                });

                spin_process.setAdapter(dataAdapter);
                spin_process.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

//                      Toast.makeText(getApplicationContext(),
//                              "OnItemSelectedListener : " + adapterView.getItemAtPosition(i).toString(),
//                              Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                AppCompatButton submit = (AppCompatButton) dialogview.findViewById(R.id.btn_submit_dialog);
                TextView tv_Cancel = (TextView) dialogview.findViewById(R.id.tv_cancel);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (checkexception()) {
                            getjobdata();
                            commentexception();
                            subdialog.dismiss();
                        }

                    }
                });
                tv_Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        subdialog.dismiss();
                        btn_exception.setBackgroundResource(R.drawable.exception_btn);
                        btn_exception.setTextColor(getResources().getColor(R.color.welcome_textcolour));
                    }
                });


//                Button btnyes=(Button)dialogview.findViewById(R.id.btn_yes);
//
//                Button btnno=(Button)dialogview.findViewById(R.id.btn_no);

//                btnyes.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
////                        LayoutInflater factory = LayoutInflater.from(jobdetailstatustrackActivity.this);
////               final View Exceptiondialogview = factory.inflate(R.layout.exception_alertdummy, null);
////                exception_dialog = new AlertDialog.Builder(jobdetailstatustrackActivity.this).create();
////                exception_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
////              overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
////                exception_dialog.setView(Exceptiondialogview);
////                etException_text=(EditText) Exceptiondialogview.findViewById(R.id.et_exception_text);
////               btnOk=(AppCompatButton)Exceptiondialogview.findViewById(R.id.btn_ok);
////               Exceptiondialogview.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
////                    @Override
////                  public void onClick(View v) {
////                       if(checkexception())
////                       {
////                            Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
////                             commentexception();
////                             exception_dialog.dismiss();
////                           btn_exception.setBackgroundResource(R.drawable.exception_btn);
////                           btn_exception.setTextColor(getResources().getColor(R.color.welcome_textcolour));
////
////                       }
////                   }
////               });
////               exception_dialog.show();
////               subdialog.dismiss();
//
//
//
//                    }
//                });
//                btnno.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        btn_exception.setBackgroundResource(R.drawable.exception_btn);
//                        btn_exception.setTextColor(getResources().getColor(R.color.welcome_textcolour));
//                        subdialog.dismiss();
//                    }
//                });
//                subdialog.setCanceledOnTouchOutside(true);

//
                subdialog.show();


//                LayoutInflater factory = LayoutInflater.from(jobdetailstatustrackActivity.this);
//                final View Exceptiondialogview = factory.inflate(R.layout.exception_alertdummy, null);
//                exception_dialog = new AlertDialog.Builder(jobdetailstatustrackActivity.this).create();
//                exception_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//
//
////            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                exception_dialog.setView(Exceptiondialogview);
//                etException_text=(EditText) Exceptiondialogview.findViewById(R.id.et_exception_text);
//                btnOk=(AppCompatButton)Exceptiondialogview.findViewById(R.id.btn_ok);
//                Exceptiondialogview.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if(checkexception())
//                        {
////                            Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
//                             commentexception();
//                             exception_dialog.dismiss();
//                        }
//                    }
//                });
//                exception_dialog.show();


            }
        });
//        btn_start.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Log.e("Ragu", "onClick: " );
//                        getsocketconnection();
////                        startdata();
//                        //getapi();
////                im_clean.setImageResource(R.drawable.ic_cleaning);
////                tv_clean.setTextColor(getResources().getColor(R.color.text_colour));
//                    }
//                });
//        btn_stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"the toast is:",Toast.LENGTH_LONG).show();
////                stop();
//            }
//        });
//        btn_finish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                finishdata();
//
//            }
//        });
//
//    }
//
//    private void getsocketconnection() {
//
//        URI uri;
//        try {
//            uri = new URI("ws://3.13.34.41:8081");
//        }
//        catch (URISyntaxException e )
//        {
//             e.printStackTrace();
//             return;
//        }
//        mWebSocketClient = new WebSocketClient(uri) {
//            @Override
//            public void onOpen(ServerHandshake handshakedata) {
//                Log.e("ragu", "onOpened: " );
//                JSONObject jsonObject = new JSONObject();
//                try {
//                    jsonObject.put("action","LINE_NUMBER_STATUS_UPDATE");
//                    jsonObject.put(  "_id",process_id);
//                    jsonObject.put("status",2);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                mWebSocketClient.send(String.valueOf(jsonObject));
//                Log.e("ragu", "onOpen: jsonobject "+jsonObject );
//            }
//
//            @Override
//            public void onMessage(String message) {
//                Log.e("ragu", "onMessage: "+message );
//
//            }
//
//            @Override
//            public void onClose(int code, String reason, boolean remote) {
//                Log.e("ragu", "onClose: "+reason );
//
//            }
//
//            @Override
//            public void onError(Exception ex) {
//                Log.e("ragu", "onError: "+ex );
//
//            }
//        };
//        mWebSocketClient.connect();
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        startActivity(new Intent(getApplicationContext(),WorkboardActivity.class));
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//    }
////    private void startdata() {
////        Log.e("ragu", "start: " );
////        Request request = new Request.Builder().url("ws://3.13.34.41:8081").build();
////        JobdetailstatusActivity.EchoWebSocketListener listener = new JobdetailstatusActivity.EchoWebSocketListener();
////        WebSocket ws = client.newWebSocket(request, listener);
////        client.dispatcher().executorService().shutdown();
////    }
////    private void stop() {
////        Log.e("ragu", "start: " );
////        Request request = new Request.Builder().url("ws://3.13.34.41:8081").build();
////        JobdetailstatusActivity.EchoWebSocketListeners listener = new JobdetailstatusActivity.EchoWebSocketListeners();
////        WebSocket ws = okHttpClient.newWebSocket(request, listener);
////        okHttpClient.dispatcher().executorService().shutdown();
////    }
////    public void finishdata() {
////        Log.e("ragu", "start: " );
////        Request request = new Request.Builder().url("ws://3.13.34.41:8081").build();
////        JobdetailstatusActivity.EchoWebSocketfinishListeners listener = new JobdetailstatusActivity.EchoWebSocketfinishListeners();
////        WebSocket ws = httpClient.newWebSocket(request, listener);
////        httpClient.dispatcher().executorService().shutdown();
////    }
//
//    public void getapi()
//    {
//        ApiInterface service = APIClient.getClient().create(ApiInterface.class);
//        Call<Ongoing_response> ongoing_responseCall = service.ongoingdata();
//        ongoing_responseCall.enqueue(new Callback<Ongoing_response>() {
//            @Override
//            public void onResponse(Call<Ongoing_response> call, retrofit2.Response<Ongoing_response> response) {
//                Log.e("Ragu", "onResponse: "+response.body());
//                for(int i=0;i<response.body().getData().size();i++)
//                {
////                    process=response.body().getData().get(i).getProcess().get(i).getProcess_name();
//                    Log.e("Ragu", "onResponse: "+process );
//
//                }
//                im_clean.setImageResource(R.drawable.ic_cleaning);
////                tv_clean.setText(""+process);
//                tv_clean.setTextColor(getResources().getColor(R.color.text_colour));
////                    btn_start.setText("Stop");
////                    btn_start.setBackgroundResource(R.drawable.stop_button);
//                btn_stop.setVisibility(View.VISIBLE);
//
//
//            }
//
//            @Override
//            public void onFailure(Call<Ongoing_response> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),""+t,Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();

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

//    private void commentexception() {
//    }

    private void commentexception() {
        Exceptioncheck exceptioncheck = new Exceptioncheck();
        exceptioncheck.setLine_number(ongoing.getLine_numbers().get_id());
        exceptioncheck.setComment(et_exceptionfeed.getText().toString());
        Log.e("ragu", "commentexception:check " + exceptioncheck);
        ApiInterface service = APIClient.getClient().create(ApiInterface.class);
        Call<Exceptioncheck_response> exceptioncheckResponseCall = service.exception(exceptioncheck);
        exceptioncheckResponseCall.enqueue(new Callback<Exceptioncheck_response>() {
            @Override
            public void onResponse(Call<Exceptioncheck_response> call, retrofit2.Response<Exceptioncheck_response> response) {

                if (response.body().isStatus() == true) {
                    Log.e("ragu", "onResponse:exception " + response.body());

                } else if (response.code() == 403) {

                    try {
                        Exceptionerror posts = new Gson().fromJson(new JSONObject(response.errorBody().string()).toString(), Exceptionerror.class);
                        Log.e("ragu", "onResponse:posts " + posts.getError_message());
//                        Toast.makeText(getApplicationContext(),"the response is:"+posts.getError_message(),Toast.LENGTH_LONG).show();
                        CommonFunctions.shortToast(getApplicationContext(), "" + posts.getError_message());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("ragu", "onResponse:Exception " + e.getMessage());
                    }

                } else {
                    Log.e("ragu", "onResponse: " + response.body().isStatus());
                }
            }

            @Override
            public void onFailure(Call<Exceptioncheck_response> call, Throwable t) {
                Log.e("ragu", "onFailure: " + t);
                Snackbar snackbar = Snackbar.make(jobStatus_relative, "Check Your Network Connection!", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                snackbar.show();
            }
        });
    }

    public boolean checkexception() {

        if (spin_process.getSelectedItemPosition() == 0) {
//            ((TextView)spin_process.getSelectedView()).setError("Error message");
            CommonFunctions.shortToast(jobdetailstatustrackActivity.this, "You Clicked Nothing ");
            return false;

        } else if (et_exceptionfeed.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "enter your Feedback", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void getjobdata() {
        Log.e("ragu", "getjobdata:ongoing_process " + ongoing.getOrder_process());
        job_status_adapter = new jobStatusadapter(jobdetailstatustrackActivity.this, ongoing.getOrder_process(),ongoing.getLine_numbers().get_id());
        job_statusRecycle.setAdapter(job_status_adapter);
//        job_status_adapter.notifyDataSetChanged();
    }

    public void getmethod(String start, String process_id,String line_id) {
        Processid = process_id;
      Lineid = line_id;

//     Toast.makeText(getApplicationContext(),"you clicked"+Processid,Toast.LENGTH_LONG).show();
        getsocketconnection();


    }

    private void getsocketconnection() {
        Log.e("ragu", "getsocketconnection: ");
        URI uri;
        String url = CONST.Websocket_URL;

        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("ragu", "getsocketconnection: not ");
            return;
        }
        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.e("ragu", "onOpened: ");
                Log.e("ragu", "onOpen:start " + mWebSocketClient.getReadyState());
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("action", "LINE_NUMBER_STATUS_UPDATE");
                    jsonObject.put("_id", Processid);
                    jsonObject.put("status", 2);
                    jsonObject.put("line_number",Lineid);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mWebSocketClient.send(String.valueOf(jsonObject));
                Log.e("ragu", "onOpen: jsonobjectss " + jsonObject);
            }

            @Override
            public void onMessage(String message) {
                Log.e("ragu", "onMessage: " + message);

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.e("ragu", "onClose: socket " + reason);

            }

            @Override
            public void onError(Exception ex) {
                Log.e("ragu", "onError: " + ex);
                toastAnywhere("" + ex);

            }
        };
        mWebSocketClient.connect();
    }

    public void toastAnywhere(final String text) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getstopmethod(String stop, String process_id,String lineid) {
        Processid = process_id;
       Lineid=lineid;
        getsocketstopconnection();

    }

    private void getsocketstopconnection() {
        Log.e("ragu", "getsocketstopconnection: ");

        URI uri;
        String url = CONST.Websocket_URL;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.e("ragu", "onOpened: ");
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("action", "LINE_NUMBER_STATUS_UPDATE");
                    jsonObject.put("_id", Processid);
                    jsonObject.put("status", 3);
                   jsonObject.put("line_number",Lineid);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mWebSocketClient.send(String.valueOf(jsonObject));
                Log.e("ragu", "onOpen: jsonobjects stop " + jsonObject);
            }

            @Override
            public void onMessage(String message) {
                Log.e("ragu", "onMessage: " + message);

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.e("ragu", "onClose: " + reason);
//                Toast.makeText(getApplicationContext(),"the reason is:"+reason,Toast.LENGTH_LONG).show();
//                toastAnywhere(""+reason);


            }

            @Override
            public void onError(Exception ex) {
                Log.e("ragu", "onError: " + ex);
                toastAnywhere("" + ex);

            }
        };
        mWebSocketClient.connect();

    }

    public void getfinishmethod(String finish, String process_id,String lion_id) {
        Processid = process_id;
        Lineid=lion_id;
        getsocketfinishconnection();

    }

    public void getfinishmethod(String finish, String process_id, String process_name,String Line_id) {
        Log.e("ragu", "getfinishmethod: " + process_name);


        Processid = process_id;
        Processname = process_name;
        Lineid = Line_id;
//        Toast.makeText(getApplicationContext(),"last position is:"+process_name,Toast.LENGTH_LONG).show();
        getsocketfinishprocessconnection();

    }

    private void getsocketfinishconnection() {
        Log.e("ragu", "getsocketfinishconnection: ");

        URI uri;
        String url = CONST.Websocket_URL;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.e("ragu", "onOpened: ");
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("action", "LINE_NUMBER_STATUS_UPDATE");
                    jsonObject.put("_id", Processid);
                    jsonObject.put("status", 4);
                  jsonObject.put("line_number",Lineid);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mWebSocketClient.send(String.valueOf(jsonObject));
                Log.e("ragu", "onOpen: jsonobjects finish " + jsonObject);
            }

            @Override
            public void onMessage(String message) {
                Log.e("ragu", "onMessage: " + message);
//                Snackbar snackbar = Snackbar.make(jobStatus_relative,"Process are completed sucessfully",Snackbar.LENGTH_LONG);
//                View snackbarView = snackbar.getView();
//                snackbarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//                snackbar.show();
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.e("ragu", "onClose: " + reason);

            }

            @Override
            public void onError(Exception ex) {
                Log.e("ragu", "onError: " + ex);
                toastAnywhere("" + ex);

            }
        };
        mWebSocketClient.connect();
    }

    private void getsocketfinishprocessconnection() {
        Log.e("ragu", "getsocketfinishconnection: ");

        URI uri;
        String url = CONST.Websocket_URL;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.e("ragu", "onOpened: ");
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("action", "LINE_NUMBER_STATUS_UPDATE");
                    jsonObject.put("_id", Processid);
                    jsonObject.put("status", 4);
                   jsonObject.put("line_number",Lineid);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mWebSocketClient.send(String.valueOf(jsonObject));
                Log.e("ragu", "onOpen: jsonobjects finish " + jsonObject);
            }

            @Override
            public void onMessage(String message) {
                Log.e("ragu", "onMessage: " + message);
                Snackbar snackbar = Snackbar.make(jobStatus_relative, "Process are completed sucessfully", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                snackbar.show();
                Handler handler = new Handler(Looper.getMainLooper());

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(jobdetailstatustrackActivity.this, WorkboardActivity.class));

                        // Run your task here
                    }
                }, 1500);

//                startActivity(new Intent(jobdetailstatustrackActivity.this,WorkboardActivity.class));
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.e("ragu", "onClose: " + reason);

            }

            @Override
            public void onError(Exception ex) {
                Log.e("ragu", "onError: " + ex);
                toastAnywhere("" + ex);

            }
        };
        mWebSocketClient.connect();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), WorkboardActivity.class));
       overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
       finish();
    }

    private void getsocketinitconnection() {
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
                Log.e("ragu", "onOpened: " + handshakedata.getHttpStatus());
                Log.e("ragu", "onOpen:connection " + mWebSocketClient.getReadyState());
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("action", "INIT");
                    jsonObject.put("_id", id_user);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mWebSocketClient.send(String.valueOf(jsonObject));
                Log.e("ragu", "onOpen: jsonobject " + jsonObject);
            }

            @Override
            public void onMessage(String message) {
//                Log.e("ragu", "onMessage: "+message );
                try {
                    JSONObject jsonObject = new JSONObject(message);
                    Log.e("ragu", "onMessage: " + jsonObject);
//                    Log.e("ragu", "onMessage:json "+jsonObject.getString("message"));
//                    Log.e("ragu", "onMessage: "+jsonObject.getBoolean("status"));

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

    public void getopen(String start) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }



//    public static void dialog(boolean value, final Context context ){
//
//        if(value){
//            alertDialog.dismiss();
//
////            Toast.makeText(context,"hello",Toast.LENGTH_LONG).show();
////            tv_check_connection.setText("We are back !!!");
////            tv_check_connection.setBackgroundColor(Color.GREEN);
////            tv_check_connection.setTextColor(Color.WHITE);
//
//            Handler handler = new Handler();
//            Runnable delayrunnable = new Runnable() {
//                @Override
//                public void run() {
////                    tv_check_connection.setVisibility(View.GONE);
//                }
//            };
//            handler.postDelayed(delayrunnable, 3000);
//        }else {
//            LayoutInflater factory = LayoutInflater.from(context);
//            final View Exceptiondialogview = factory.inflate(R.layout.internet_check, null);
//            exception_dialog = new AlertDialog.Builder(context).create();
////            exception_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//
//
////            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            exception_dialog.setView(Exceptiondialogview);
//            Btnok=(AppCompatButton) Exceptiondialogview.findViewById(R.id.btn_internet);
//            Exceptiondialogview.findViewById(R.id.btn_internet).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ((Activity) context).finishAffinity();
//
//
////                    if(checkexception())
////                    {
//////                            Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
////                        commentexception();
////                        exception_dialog.dismiss();
////                    }
//                }
//            });
//
//
//            exception_dialog.show();
////            CustomAlertDialog dialog = new CustomAlertDialog(context, CustomAlertDialog.DialogStyle.DEFAULT);
////            dialog.setDialogType(CustomAlertDialog.DialogType.WARNING);
////          dialog.setAlertTitle("No Internet Connection You need to have mobile data or wifi to access this");
////
////
////
////            dialog.create();
////            dialog.show();
//
//
//
////             alertDialogBuilder = new AlertDialog.Builder(context);
////            // Setting Alert Dialog Title
////            alertDialogBuilder.setTitle("No Internet Connection");
////            // Icon Of Alert Dialog
////            // Setting Alert Dialog Message
////           alertDialogBuilder.setMessage("You need to have mobile data or wifi to access this");
////
////            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
////
////                @Override
////                public void onClick(DialogInterface arg0, int arg1) {
////                    ((Activity) context).finishAffinity();
////
////
////                }
////            });
////
////             alertDialog = alertDialogBuilder.create();
////            alertDialog.show();
//
////            Toast.makeText(context,"hello",Toast.LENGTH_LONG).show();
////            final BottomSheetDialogFragment myBottomSheet = MyBottomSheetDialogFragment.newInstance("Modal Bottom Sheet");
////           myBottomSheet.show(getSupportFragmentManager(), myBottomSheet.getTag());
////            CommonFunctions.removeProgressDialog();
//
////            Snackbar snackbar = Snackbar.make(linearLayout_workboard,"Check Your Network Connection!",Snackbar.LENGTH_LONG);
////            View snackbarView = snackbar.getView();
////            snackbarView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
////            snackbar.show();
//
//
////            tv_check_connection.setVisibility(View.VISIBLE);
////            tv_check_connection.setText("Could not Connect to internet");
////            tv_check_connection.setBackgroundColor(Color.RED);
////            tv_check_connection.setTextColor(Color.WHITE);
//        }
//    }

}
