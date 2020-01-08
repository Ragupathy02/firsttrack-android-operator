package com.firstpage.user.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firstpage.user.Adapter.Processdetailsadapter;
import com.firstpage.user.Common.CONST;
import com.firstpage.user.Common.CommonFunctions;
import com.firstpage.user.Common.SharedPreference;
import com.firstpage.user.Interfaces.APIClient;
import com.firstpage.user.Interfaces.ApiInterface;
import com.firstpage.user.Model.Dummyprovider;
import com.firstpage.user.Model.Ongoing;
import com.firstpage.user.Model.Ongoing_response;
import com.firstpage.user.Model.Order_process;
import com.firstpage.user.Model.Processdetail;
import com.firstpage.user.R;
import com.firstpage.user.Ui.ExceptionActivity;
import com.firstpage.user.Ui.Workboardsupervisor_Activity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class Process_fragment extends Fragment {
    Context context1;
    public AppCompatButton btn_exception;
    public static android.support.v7.app.AlertDialog subdialog;
    AppCompatEditText et_exceptionfeed;
    Spinner spin_process;
    ArrayAdapter<String> dataAdapter;
    public RecyclerView Process_recyclerview;
    ArrayList<Processdetail> processdetailArrayList;
    SharedPreference sharedPreference;
    public Processdetailsadapter processdetailsadapter;
    String onedata;
    ArrayList<String> processname;
    String Processid;
    String Lineid;
    private WebSocketClient mWebSocketClient;
    public String Processname;
    public String id_user;
    public RelativeLayout jobStatus_relative;
    ArrayList<Ongoing> ongoingArrayList;
    Ongoing ongoing;
    DatePickerDialog picker;
    Order_process order_process;
    String value;
    ArrayList<String> dummydata;
    ArrayList<String> processdetail = new ArrayList<>();

//    Serializable ongoing;
//    Serializable order_process;

    @SuppressLint("ValidFragment")
    public Process_fragment(Context context1, ArrayList<String> processdetail) {

        this.context1 = context1;
        this.processdetail = processdetail;
//        Log.e(TAG, "Process_fragment: ", );

    }

    public Process_fragment(Context context1, ArrayList<String> processdetail, Ongoing ongoing) {
        this.context1=context1;
        this.processdetail=processdetail;
        this.ongoing = ongoing;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("ragu", "onCreate:new instance");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.processdetails_fragment, container, false);

        jobStatus_relative = (RelativeLayout) view.findViewById(R.id.job_status_relative);
        btn_exception = (AppCompatButton) view.findViewById(R.id.btn_exception);

        for(int i=0;i<ongoing.getOrder_process().size();i++)
        {
            if(ongoing.getOrder_process().get(i).getStatus()!=4)
            {
                btn_exception.setVisibility(View.VISIBLE);
                break;

            }
            else {
                btn_exception.setVisibility(View.GONE);
            }

        }
        processdetail.add(0,"Select Exception Process");
//        Bundle ongoing = bundle.getBundle("data");


//        String myValue = null;
//        if (bundle != null) {
//            myValue = bundle.getString("value");
//            Log.e("ragu", "onCreateView:getString "+myValue );
//        }
//
        dataAdapter = new ArrayAdapter<String>(
                getActivity(), R.layout.simple_spinner_item, processdetail) {
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


//        if (getArguments() != null) {
//            value = getArguments().getString("YourKey");
//            Log.e("ragu", "onCreateView:value "+value );
//        }
//        Log.e("ragu", "onCreateView:value "+value );

//        Bundle b = getArguments();
//        if (b != null) {
//            int s = b.getInt("doctor_id");
//            Log.e("ragu", "onCreateView:data "+s );
//
//        }
        sharedPreference = new SharedPreference();
        onedata = sharedPreference.getString(getActivity(), "one");
//        Bundle args = getArguments();
//        if (args != null) {
//            Order_process myClass = (Order_process) args
//                    .getSerializable("order");
//            Log.e("ragu", "onCreateView:order process " );
//        }


//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            int myInt = bundle.getInt("hai", 1);
//            Log.e("ragu", "onCreateView:myInt " );
//        }

//        @Subscribe(threadMode = ThreadMode.MAIN)
//        public void onMessageEvent(Dummyprovider event) {
//            Toast.makeText(getActivity(), event.message, Toast.LENGTH_SHORT).show();
//        }

        //getprocessjob();

//        Bundle b1 = this.getArguments();
//        if (b1!=null)
//        {
//            String a =b1.getString("hai");
//            Log.e("ragu", "onCreatedata function "+a );
//
//        }
//        String value = getArguments().getString("YourKey");
//        Log.e("ragu", "onCreateView:value "+value );


//        Ongoing ongoing = (Ongoing) getArguments().getSerializable("obj");
//        Log.e("ragu", "onCreateView:ongoing "+ongoing.get_id() );

//        if (getArguments() != null) {
//            Log.e("ragu", "onCreateView:getdata" );
//            ongoing=(Ongoing)getArguments().getSerializable("key_array");
//            String name= ongoing.get_id();
////            Log.e("ragu", "onCreateView:name "+name );
//        }
//        Log.e("ragu", "onCreateView:serializable "+ongoing);


//        Bundle b = getArguments();
//        if (b != null) {
//            order_process = b.getSerializable("key_array");
//        }
//        Log.e("ragu", "onCreateView:serializable "+order_process );


//        Bundle b = getArguments();
//        int s = b.getInt("doctor_id");
//        Log.e("ragu", "onCreateView:long int "+s);

//       ongoingArrayList  = (ArrayList<Ongoing>) getArguments().getSerializable("keyarray");
//        Log.e("ragu", "onCreateView:arraylist "+ongoingArrayList );
//        Bundle bundle = getArguments();
//        ongoing =(Ongoing)bundle.getSerializable("keyarray");
//        String name= ongoing.get_id();
//        Log.e("ragu", "onCreateView:name "+name );


        Log.e("ragu", "onCreateView:one found " + onedata);
        id_user = sharedPreference.getString(getActivity(), "id_user");

        Process_recyclerview = (RecyclerView) view.findViewById(R.id.process_recycle);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        Process_recyclerview.setLayoutManager(layoutManager);
        getsocketinitconnection();

        getjob();


        btn_exception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_exception.setBackgroundResource(R.drawable.exception_click_bg);
                btn_exception.setTextColor(getResources().getColor(R.color.exception_text_color));
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                final View dialogview = layoutInflater.inflate(R.layout.exception_dialoglayout, null);
                subdialog = new AlertDialog.Builder(getActivity()).create();

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

                final AppCompatTextView tv_date = (AppCompatTextView) dialogview.findViewById(R.id.tv_date);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy ");
                Date date = new Date();
//                System.out.println(formatter.format(date));
                tv_date.setText("" + formatter.format(date));

                AppCompatImageView im_calendar = (AppCompatImageView) dialogview.findViewById(R.id.iv_calendar);
                im_calendar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Calendar cldr = Calendar.getInstance();
                        int day = cldr.get(Calendar.DAY_OF_MONTH);
                        int month = cldr.get(Calendar.MONTH);
                        int year = cldr.get(Calendar.YEAR);
                        picker = new DatePickerDialog(getActivity(),
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                        tv_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                    }
                                }, year, month, day);
                        picker.show();
                    }
                });
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
//                            getjobdata();
//                            commentexception();
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

        return view;
    }

    @Subscribe
    public void onMessageEvent(Dummyprovider dummyprovider) {

        String name = dummyprovider.getName();

        Log.e("ragu", "onMessageEvent:dummy " + name);

//        Toast.makeText(getActivity(), dummyprovider.getName(), Toast.LENGTH_SHORT).show();
    }

    private void getprocessjob() {
        ApiInterface service = APIClient.getClient().create(ApiInterface.class);
        Call<Ongoing_response> ongoing_responseCalls = service.ongoingdata();
        ongoing_responseCalls.enqueue(new Callback<Ongoing_response>() {
            @Override
            public void onResponse(Call<Ongoing_response> call, Response<Ongoing_response> response) {
                for (int i = 0; i < response.body().getData().size(); i++) {
                    if (onedata.equals(response.body().getData().get(i).get_id())) {
                        for (i = 0; i < response.body().getData().get(i).getOrder_process().size(); i++) {
                            ArrayList<String> stringArrayList = new ArrayList<String>();
                            stringArrayList.add(response.body().getData().get(i).getOrder_process().get(i).getProcess().getProcess_name());
                            Log.e("ragu", "onResponse: " + stringArrayList);
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<Ongoing_response> call, Throwable t) {

            }
        });


    }

    private void getjob() {
//        CommonFunctions.showloadingProgressDialog(getActivity(),"",false);
        ApiInterface service = APIClient.getClient().create(ApiInterface.class);
        Call<Ongoing_response> ongoing_responseCall = service.ongoingdata();
        ongoing_responseCall.enqueue(new Callback<Ongoing_response>() {
            @Override
            public void onResponse(Call<Ongoing_response> call, Response<Ongoing_response> response) {
//                CommonFunctions.removeloadingDialog();
                if (response.body().getData().size() > 0) {

                    for (int i = 0; i < response.body().getData().size(); i++) {

                        if (onedata.equals(response.body().getData().get(i).get_id())) {

                            Log.e("ragu", "onResponse:process name " + processname);
                            processdetailsadapter = new Processdetailsadapter(getActivity(), response.body().getData().get(i).getOrder_process(), response.body().getData().get(i).getLine_numbers().get_id(), Process_fragment.this);
                            Log.e("ragu", "onResponse:processname ");
                            Process_recyclerview.setAdapter(processdetailsadapter);


//                            for( i=0;i<response.body().getData().get(i).getOrder_process().size();i++)
//                            {
//                                processname = new ArrayList<String>();
//                                processname.add(response.body().getData().get(i).getOrder_process().get(i).getProcess().getProcess_name());
//                                Log.e("ragu", "onResponse:add "+processname );
//
//                            }
//                            for(i=0;i<response.body().getData().get(i).getOrder_process().size();i++)
//                            {
//                                processname = new ArrayList<String>();
//                                processname.add(response.body().getData().get(i).getOrder_process().get(i).getProcess().getProcess_name());
//                                Log.e("ragu", "onResponse:proessname "+processname );
//
//                            }


//                            for(int i=0;i<response.body().getData().get(i1).getOrder_process().size();i1++)
//                            {
//                                processname.add(response.body().getData().get(i1).getOrder_process().get(i1).getProcess().getProcess_name());
//                                Log.e("ragu", "onResponse:name "+processname );
//                            }
//                            processname.add(response.body().getData().get(i).getOrder_process().get(i).getProcess().getProcess_name());
//                            Log.e("ragu", "onResponse:processnmae list "+processname);
                        }
//                        processname.add(response.body().getData().get(i).getOrder_process().get(i).getProcess().getProcess_name());
//                        Log.e("ragu", "onResponse:arraylist "+processname );
//                        processdetailsadapter = new Processdetailsadapter(getActivity(),response.body().getData())
                    }
                } else {
                    Toast.makeText(getActivity(), "empty data", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Ongoing_response> call, Throwable t) {

            }
        });


//        processdetailArrayList= new ArrayList<>();
//      Processdetailsadapter processdetailsadapter = new Processdetailsadapter(getActivity(),processdetailArrayList);
//      getmodel();
//        Process_recyclerview.setAdapter(processdetailsadapter);


    }

//    private void getmodel() {
//        Processdetail processdetail = new Processdetail("plumbing");
//        processdetailArrayList.add(processdetail);
//        Processdetail processdetail11 = new Processdetail("screwing");
//        processdetailArrayList.add(processdetail11);
//    }


    public boolean checkexception() {
        if (spin_process.getSelectedItemPosition() == 0) {
//            ((TextView)spin_process.getSelectedView()).setError("Error message");
            CommonFunctions.shortToast(getActivity(), "You Clicked Nothing ");
            return false;

        } else if (et_exceptionfeed.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "enter your Feedback", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public void getmethod(String start, String process_id, String line_id) {

        Processid = process_id;
        Lineid = line_id;
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
                    jsonObject.put("line_number", Lineid);

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
                Toast.makeText(getActivity(), text,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getstopmethod(String stop, String process_id, String lineid) {
        Processid = process_id;
        Lineid = lineid;
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
                    jsonObject.put("line_number", Lineid);

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

    public void getfinishmethod(String finish, String process_id, String process_name, String Line_id) {
        Processid = process_id;
        Processname = process_name;
        Lineid = Line_id;
        getsocketfinishprocessconnection();


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
                    jsonObject.put("line_number", Lineid);

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
                        startActivity(new Intent(getActivity(), Workboardsupervisor_Activity.class));

                        // Run your task here
                    }
                }, 1500);

//                startActivity(new Intent(jobdetailstatustrackActivity.this,Workboardsupervisor_Activity.class));
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

    public void getfinishmethod(String finish, String process_id, String lion_id) {
        Processid = process_id;
        Lineid = lion_id;
        getsocketfinishconnection();

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
                    jsonObject.put("line_number", Lineid);

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

    @Override
    public void onStart() {
        Log.e("ragu", "onStart: eventbus ");
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}

