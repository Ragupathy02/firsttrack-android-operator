package com.firstpage.user.Ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firstpage.user.Common.CONST;
import com.firstpage.user.Common.CommonFunctions;
import com.firstpage.user.Common.SharedPreference;
import com.firstpage.user.Interfaces.APIClient;
import com.firstpage.user.Interfaces.ApiInterface;
import com.firstpage.user.Model.Ongoing;
import com.firstpage.user.Model.Ongoing_response;
import com.firstpage.user.R;
import com.rey.material.widget.SnackBar;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import retrofit2.Call;
import retrofit2.Callback;

public class JobdetailstatusActivity extends AppCompatActivity {
    public AppCompatTextView tv_job_id;
    public AppCompatTextView tv_customer_name;
    public AppCompatTextView tv_purchase_order;
    public AppCompatTextView tv_line_no;
    public AppCompatTextView tv_qty;
    public AppCompatTextView tv_order_started;
    public AppCompatTextView  tv_schedule;
    public AppCompatTextView tv_clean;
    public AppCompatTextView tv_cutting;
    public AppCompatTextView  tv__packing;
    public AppCompatButton btnException;
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
    Ongoing_response  ongoing_response;
    WebSocket webSocket;
    String process;
    String process_id;
    JSONObject stopjson;
    private WebSocketClient mWebSocketClient;
    public LinearLayout linearLayout_cutting;
    public AppCompatButton btn_start_cutting;
    public AppCompatButton btn_stop_cutting;
    public AppCompatButton btn_finish_cutting;
    public LinearLayout linearLayout_packing;
    public AppCompatButton btn_start_packing;
    public AppCompatButton btn_stop_packing;
    public AppCompatButton btn_finish_packing;
    public LinearLayout linearCleaning;
    int response_status;
    int response_status_clean_check;
    int response_status_start;
    int response_status_stop;
    int response_status_finish;
    int cutting_response_start;
    int cutting_response_stop;
    int cutting_response_finish;
    ImageView im_cutting;
    ImageView im_packing;
    int packing_response_start;
    int packing_response_stop;
    int packing_response_finish;
    public RelativeLayout relativeJobStatus;
    int position;
    Ongoing ongoing;
    ArrayList<Ongoing> ongoings = new ArrayList<Ongoing>();
    String slack;
    String positions;
    int finish_position;
    String finishvalue;



//    private final class EchoWebSocketListener extends WebSocketListener {
//        private static final int NORMAL_CLOSURE_STATUS = 1000;
//        @Override
//        public void onOpen(WebSocket webSocket, Response response) {
//            Log.e("Ragu", "onOpen: socket "+webSocket.toString() );
//
//                Log.e("ragu", "onOpen: Start ");
//                JSONObject jsonObject = new JSONObject();
//                try {
//                    Log.e("ragu", "onOpen: try " );
//                    jsonObject.put("action","LINE_NUMBER_STATUS_UPDATE");
//                    jsonObject.put(  "_id",process_id);
//                    jsonObject.put("status",2);
//                }
//                catch (Exception e)
//                {
//                    Log.e("ragu", "onOpen: exception "+e );
//                }
//                webSocket.send(String.valueOf(jsonObject));
//                Log.e("ragu", "onOpen: Jsonobject "+jsonObject );
//                Log.e("ragu", "onOpen: start response"+response );
//               webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
//        }
//        @Override
//        public void onMessage(WebSocket webSocket, String text) {
//            Log.e("ragupathy", "onMessage: text "+text );
////            output("Receiving : " + text);
//        }
//        @Override
//        public void onMessage(WebSocket webSocket, ByteString bytes) {
////            output("Receiving bytes : " + bytes.hex());
//        }
//        @Override
//        public void onClosing(WebSocket webSocket, int code, String reason) {
//            Log.e("ragu", "c " );
////            webSocket.close(NORMAL_CLOSURE_STATUS, null);
//            webSocket.cancel();
//            Log.e("ragu", "CLOSE: " + code + " " + reason);
////            output("Closing : " + code + " / " + reason);
//        }
//        @Override
//        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
//            try {
//                Log.e("ragu", "onFailure: socket "+t.getMessage() );
//
//            }
//            catch (Exception e)
//            {
//                Log.e("ragu", "onFailure: exception"+e );
//
//            }
//
////            output("Error : " + t.getMessage());
//        }
//    }
//    private final class EchoWebSocketListeners extends WebSocketListener {
//        private static final int NORMAL_CLOSURE_STATUS = 1000;
//        @Override
//        public void onOpen(WebSocket webSocket, Response response) {
//            Log.e("Ragu", "onOpen: socket "+webSocket.toString() );
//            Log.e("ragu", "onOpen: Start ");
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("action","LINE_NUMBER_STATUS_UPDATE");
//                jsonObject.put(  "_id",process_id);
//                jsonObject.put("status",3);
//
//            }
//            catch (Exception e)
//            {
//                Log.e("ragu", "onOpen: exception "+e );
//            }
//            webSocket.send(String.valueOf(jsonObject));
//            Log.e("ragu", "onOpen: response "+response );
//            Log.e("ragu", "onOpen: Jsonobject"+jsonObject );
//            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
//        }
//        @Override
//        public void onMessage(WebSocket webSocket, String text) {
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
////            output("Error : " + t.getMessage());
//        }
//    }
//    private final class EchoWebSocketfinishListeners extends WebSocketListener {
//        private static final int NORMAL_CLOSURE_STATUS = 1000;
//        @Override
//        public void onOpen(WebSocket webSocket, Response response) {
//            Log.e("Ragu", "onOpen: socket "+webSocket.toString() );
//            Log.e("ragu", "onOpen: Start ");
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("action","LINE_NUMBER_STATUS_UPDATE");
//                jsonObject.put(  "_id",process_id);
//                jsonObject.put("status",4);
//            }
//            catch (Exception e)
//            {
//                Log.e("ragu", "onOpen: exception "+e );
//            }
//            webSocket.send(String.valueOf(jsonObject));
//            Log.e("ragu", "onOpen: response "+response );
//            Log.e("ragu", "onOpen: Jsonobject"+jsonObject );
//            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
//        }
//        @Override
//        public void onMessage(WebSocket webSocket, String text) {
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
////            output("Error : " + t.getMessage());
//        }
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityjob_status);
        tv_job_id=(AppCompatTextView)findViewById(R.id.tv_job_id);
        tv_customer_name=(AppCompatTextView)findViewById(R.id.tv_customer_name);
        tv_purchase_order=(AppCompatTextView)findViewById(R.id.tv_purchase_order);
        tv_line_no=(AppCompatTextView)findViewById(R.id.tv_line_no);
        tv_qty=(AppCompatTextView)findViewById(R.id.tv_quantity);
        tv_order_started=(AppCompatTextView)findViewById(R.id.tv_process_order);
        tv_schedule=(AppCompatTextView)findViewById(R.id.tv_process_schedule);
        tv_clean=(AppCompatTextView)findViewById(R.id.tv_process_clean);
        tv_cutting=(AppCompatTextView)findViewById(R.id.tv_process_cutting);
        tv__packing=(AppCompatTextView)findViewById(R.id.tv_process_packing);
        btnException=(AppCompatButton)findViewById(R.id.btn_exception);
        btn_start=(AppCompatButton)findViewById(R.id.btn_start);
        btn_finish=(AppCompatButton)findViewById(R.id.btn_finish);
        im_back=(AppCompatImageView)findViewById(R.id.iv_back);
        relativeJobStatus=(RelativeLayout)findViewById(R.id.job_status_relative);
//        btn_stop=(AppCompatButton)findViewById(R.id.btn_stop1);
        im_clean=(ImageView)findViewById(R.id.im_clean_timeline);
        im_cutting=(ImageView)findViewById(R.id.iv_cutting_timeline);
        im_packing=(ImageView)findViewById(R.id.iv_packing_timeline);
        linearLayout_cutting=(LinearLayout)findViewById(R.id.layout_cutting);
        btn_start_cutting=(AppCompatButton)findViewById(R.id.btn_start_cutting);
//        btn_stop_cutting=(AppCompatButton)findViewById(R.id.btn_stop_cutting);
        btn_finish_cutting=(AppCompatButton)findViewById(R.id.btn_finish_cutting);
        linearLayout_packing=(LinearLayout)findViewById(R.id.packing_layout);
        btn_start_packing=(AppCompatButton)findViewById(R.id.btn_start_packing);
//        btn_stop_packing=(AppCompatButton)findViewById(R.id.btn_stop_packing);
        btn_finish_packing=(AppCompatButton)findViewById(R.id.btn_finish_packing);
        linearCleaning=(LinearLayout)findViewById(R.id.linear_cleaning);
        relativeJobStatus=(RelativeLayout)findViewById(R.id.job_status_relative);
        ongoing=(Ongoing) getIntent().getSerializableExtra("keyarray");
        ongoings.add(ongoing);
        Log.e("ragu", "onCreate:ongoings "+ongoings );
        for(int i=0;i<ongoing.getOrder_process().size();i++)
        {
            Log.e("ragu", "onCreate:slacktime "+ongoing.getOrder_process().get(i).getSlack_time() );
            Log.e("ragu", "onCreate:process "+ongoing.getOrder_process().get(i).getProcess_time() );
            Log.e("ragu", "onCreate: "+ongoing.getOrder_process().get(i).getStatus() );
            Log.e("ragu", "onCreate: "+ongoing.getOrder_process().get(i).getOrder() );
            Log.e("ragu", "onCreate:process name "+ongoing.getOrder_process().get(i).getProcess().getProcess_name() );


        }
//        Log.e("ragu", "onCreate:slack "+slack );
//        for(int i=0;i<ongoings.size();i++)
//        {
////            Log.e("ragu", "onCreate:slacktime "+ongoings.get(i).getOrder_process().get(i).getSlack_time() );
//             slack = ongoings.get(i).getOrder_process().get(i).getSlack_time() ;
//
//        }
        Log.e("ragu", "onCreate:slack "+slack);
        String id=ongoing.get_id();
        Log.e("ragu", "onCreate:jobdetails"+id);

        sharedPreference=new SharedPreference();
        Intent i =getIntent();
        Bundle b=i.getExtras();
        String jobno=b.getString("jobno");

        String lineno=b.getString("Line_number");

        String quantity=b.getString("quantity");

        String purchase_order_no=b.getString("purchase_order");

//        String jobno=sharedPreference.getString(getApplicationContext(),"Jobno");
       tv_job_id.setText(""+jobno);
//        String lineno=sharedPreference.getString(getApplicationContext(),"Line_number");
        tv_line_no.setText(""+lineno);
//        String quantity=sharedPreference.getString(getApplicationContext(),"quantity");
       tv_qty.setText(""+quantity);
//        String Purchase_order=sharedPreference.getString(getApplicationContext(),"purchase_order");
       tv_purchase_order.setText(""+purchase_order_no);
        String name=sharedPreference.getString(getApplicationContext(),"name");
        tv_customer_name.setText(""+name);

         position=sharedPreference.getInt(getApplicationContext(),"position_clicked");
        Log.e("ragu", "onCreate:position clicked "+position );

          positions=String.valueOf(position);

           finish_position=sharedPreference.getInt(getApplicationContext(),"position");

           finishvalue= String.valueOf(finish_position);

        Log.e("ragu", "onCreate:position "+position );

         process_id=sharedPreference.getString(getApplicationContext(),"processid");
        response_status_clean_check=sharedPreference.getInt(getApplicationContext(),"status");
         cleaningcheck();
         response_status_start=sharedPreference.getInt(getApplicationContext(),"start_response");
          checkstartresponse();
        response_status_stop=sharedPreference.getInt(getApplicationContext(),"stop_response");
        checkstopresponse();
        response_status_finish=sharedPreference.getInt(getApplicationContext(),"finish_response");
        checkfinish();

        cutting_response_start=sharedPreference.getInt(getApplicationContext(),"cutting_response_start");
         checkcuttingstartresponse();
        cutting_response_finish=sharedPreference.getInt(getApplicationContext(),"cutting_response_finish");
       checkcuttingfinishresponse();
       packing_response_start=sharedPreference.getInt(getApplicationContext(),"response_packing_start");
       checkpackingstartresponse();
       packing_response_finish=sharedPreference.getInt(getApplicationContext(),"response_packing_finish");
       checkpackingfinishresponse();
       tv_clean.setText("");

        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),WorkboardActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        btn_start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(btn_start.getText().toString().equals("Start"))
                        {
                            Log.e("Ragu", "onClick: " );
                            getsocketconnection();
                            getapi();
                        }
                        else if(btn_start.getText().toString().equals("Stop"))
                        {
                            Toast.makeText(getApplicationContext(),"you clicked stop",Toast.LENGTH_LONG).show();
                            getsocketstopconnection();
                            btn_start.setBackgroundResource(R.drawable.start_button);
                            btn_start.setText("Start");
                            im_clean.setImageResource(R.drawable.clean_timeline);
                            tv_clean.setTextColor(getResources().getColor(R.color.process_notviewed_text_colour));
                            sharedPreference.putInt(getApplicationContext(),"stop_response",3);
                        }


//                        startdata();

//                im_clean.setImageResource(R.drawable.ic_cleaning);
//                tv_clean.setTextColor(getResources().getColor(R.color.text_colour));
                    }
                });
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getsocketfinishconnection();
                linearLayout_cutting.setVisibility(View.VISIBLE);
                linearCleaning.setVisibility(View.INVISIBLE);
                sharedPreference.putInt(getApplicationContext(),"progress",2);
                sharedPreference.putInt(getApplicationContext(),"status",4);
                sharedPreference.putInt(getApplicationContext(),"finish_response",4);
                sharedPreference.putInt(getApplicationContext(),"finish",position);
//             sharedPreference.removeInt(getApplicationContext(),"start_response");


            }
        });
        btn_start_cutting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_start_cutting.getText().toString().equals("Start"))
                {
                    Log.e("ragu", "onClick: start_packing " );
                    getsocketconnection();
                    im_cutting.setImageResource(R.drawable.ic_cutting);
                    tv_cutting.setTextColor(getResources().getColor(R.color.text_colour));
                    btn_start_cutting.setText("Stop");
                    btn_start_cutting.setBackgroundResource(R.drawable.stop_button);
                    linearCleaning.setVisibility(View.INVISIBLE);
                    sharedPreference.putInt(getApplicationContext(),"cutting_response_start",2);
                }
                else if(btn_start_cutting.getText().toString().equals("Stop"))
                {
                    Log.e("ragu", "onClick: stoppacking " );
                    getsocketstopconnection();
                    btn_start_cutting.setBackgroundResource(R.drawable.start_button);
                    btn_start_cutting.setText("Start");
                    im_cutting.setImageResource(R.drawable.cutting_timeline_round);
                    tv_cutting.setTextColor(getResources().getColor(R.color.process_notviewed_text_colour));
                    sharedPreference.putInt(getApplicationContext(),"cutting_response_stop",3);
                }


            }
        });
        btn_finish_cutting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getsocketfinishconnection();
                linearLayout_cutting.setVisibility(View.INVISIBLE);
                linearLayout_packing.setVisibility(View.VISIBLE);
                sharedPreference.putInt(getApplicationContext(),"progress",3);
                sharedPreference.putInt(getApplicationContext(),"cutting_response_finish",4);
                sharedPreference.putInt(getApplicationContext(),"finish",position);

            }
        });
        btn_start_packing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_start_packing.getText().toString().equals("Start"))
                {
                    getsocketconnection();
                    im_packing.setImageResource(R.drawable.ic_packing);
                    tv__packing.setTextColor(getResources().getColor(R.color.text_colour));
                    btn_start_packing.setText("Stop");
                    btn_start_packing.setBackgroundResource(R.drawable.stop_button);
                    sharedPreference.putInt(getApplicationContext(),"response_packing_start",2);
                }
                else if(btn_start_packing.getText().toString().equals("Stop"))
                {
                    getsocketstopconnection();
                    btn_start_packing.setBackgroundResource(R.drawable.start_button);
                    btn_start_packing.setText("Start");
                    im_packing.setImageResource(R.drawable.packing_timeline_round);
                    tv__packing.setTextColor(getResources().getColor(R.color.process_notviewed_text_colour));
                    sharedPreference.putInt(getApplicationContext(),"response_packing_stop",3);
                }

            }
        });
        btn_finish_packing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getsocketfinishconnection();


                sharedPreference.putInt(getApplicationContext(),"progress",4);
                sharedPreference.putInt(getApplicationContext(),"response_packing_finish",4);
                sharedPreference.putInt(getApplicationContext(),"finish",position);

//                Toast.makeText(getApplicationContext(),"you finished all activities",Toast.LENGTH_SHORT).show();
                linearLayout_packing.setVisibility(View.INVISIBLE);
                btnException.setVisibility(View.GONE);

                Snackbar snackbar = Snackbar.make(relativeJobStatus,"Process Completed successfully",Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                snackbar.show();

//                startActivity(new Intent(getApplicationContext(),WorkboardActivity.class));
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


            }
        });
        btnException.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonFunctions.shortToast(getApplicationContext(),"you clicked exception");

            }
        });

    }

    private void checkfinish() {
        if(response_status_finish==0)
        {


        }
        else {
            linearLayout_cutting.setVisibility(View.VISIBLE);
            linearCleaning.setVisibility(View.INVISIBLE);
        }
    }

    private void checkpackingfinishresponse() {
        if(packing_response_finish==0)
        {
            Log.e("ragu", "checkpackingfinishresponse: sucess " );

        }
        else {
            Log.e("ragu", "checkpackingfinishresponse: failure " );
            Snackbar snackbar = Snackbar.make(relativeJobStatus,"Process Completed successfully",Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();
            linearLayout_packing.setVisibility(View.INVISIBLE);
            btnException.setVisibility(View.GONE);

        }

    }

    private void checkpackingstartresponse() {
        if(packing_response_start==0)
        {
            Log.e("ragu", "checkpackingstartresponse: " );
        }
        else
        {

            linearLayout_packing.setVisibility(View.VISIBLE);
            im_packing.setImageResource(R.drawable.ic_packing);
            tv__packing.setTextColor(getResources().getColor(R.color.text_colour));
            btn_start_packing.setText("Stop");
            btn_start_packing.setBackgroundResource(R.drawable.stop_button);
            btn_finish_packing.setText("Finish");
            btn_finish_packing.setBackgroundResource(R.drawable.finish_button);
            Log.e("ragu", "checkpackingstartresponse:failure ");
        }


    }

    private void checkcuttingfinishresponse() {
        if(cutting_response_finish==0)
        {
//            linearLayout_cutting.setVisibility(View.INVISIBLE);
            Log.e("ragu", "checkcuttingfinishresponse: " );
        }
        else
        {
            linearLayout_cutting.setVisibility(View.INVISIBLE);
            linearLayout_packing.setVisibility(View.VISIBLE);
            im_cutting.setImageResource(R.drawable.ic_cutting);
            tv_cutting.setTextColor(getResources().getColor(R.color.text_colour));
            Log.e("ragu", "onCreate: status1 " );
            Log.e("ragu", "checkcuttingfinishresponse: failure ");

        }
    }

    private void checkcuttingstartresponse() {
        if(cutting_response_start==0)
        {
//            linearLayout_cutting.setVisibility(View.VISIBLE);

            Log.e("ragu", "cuttingcheckstartresponse:success " );
        }
        else {
            linearLayout_cutting.setVisibility(View.VISIBLE);
            im_cutting.setImageResource(R.drawable.ic_cutting);
            tv_cutting.setTextColor(getResources().getColor(R.color.text_colour));
            btn_start_cutting.setText("Stop");
            btn_start_cutting.setBackgroundResource(R.drawable.stop_button);
            btn_finish_cutting.setText("Finish");
            btn_finish_cutting.setBackgroundResource(R.drawable.finish_button);

        }
    }

    private void checkstopresponse() {
        if(response_status_stop==0)
        {
            Log.e("ragu", "checkstopresponse: success " );
        }
        else
        {
            Log.e("ragu", "checkstopresponse: failure " );

        }
    }

    private void checkstartresponse() {
        if(response_status_start==0)
        {
            Log.e("ragu", "checkstartresponse:success " );

        }
        else {
            Log.e("ragu", "checkstartresponse: failure  " );
            im_clean.setImageResource(R.drawable.ic_cleaning);
            tv_clean.setTextColor(getResources().getColor(R.color.text_colour));
            btn_start.setText("Stop");
            btn_start.setBackgroundResource(R.drawable.stop_button);
            btn_finish.setText("Finish");
            btn_finish.setBackgroundResource(R.drawable.finish_button);

        }
    }

    private void getsocketfinishconnection() {
        Log.e("ragu", "getsocketfinishconnection: " );

        URI uri;
        String url = CONST.Websocket_URL;
        try {
            uri = new URI(url);
        }
        catch (URISyntaxException e )
        {
            e.printStackTrace();
            return;
        }
        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.e("ragu", "onOpened: " );
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("action","LINE_NUMBER_STATUS_UPDATE");
                    jsonObject.put(  "_id",process_id);
                    jsonObject.put("status",4);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mWebSocketClient.send(String.valueOf(jsonObject));
                Log.e("ragu", "onOpen: jsonobjects finish "+jsonObject );
            }

            @Override
            public void onMessage(String message) {
                Log.e("ragu", "onMessage: "+message );

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.e("ragu", "onClose: "+reason );

            }

            @Override
            public void onError(Exception ex) {
                Log.e("ragu", "onError: "+ex );
                toastAnywhere(""+ex);

            }
        };
        mWebSocketClient.connect();
    }

    private void getsocketstopconnection() {
        Log.e("ragu", "getsocketstopconnection: " );

        URI uri;
        String url=CONST.Websocket_URL;
        try {
            uri = new URI(url);
        }
        catch (URISyntaxException e )
        {
            e.printStackTrace();
            return;
        }
        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.e("ragu", "onOpened: " );
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("action","LINE_NUMBER_STATUS_UPDATE");
                    jsonObject.put(  "_id",process_id);
                    jsonObject.put("status",3);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mWebSocketClient.send(String.valueOf(jsonObject));
                Log.e("ragu", "onOpen: jsonobjects stop "+jsonObject );
            }

            @Override
            public void onMessage(String message) {
                Log.e("ragu", "onMessage: "+message );

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.e("ragu", "onClose: "+reason );
//                Toast.makeText(getApplicationContext(),"the reason is:"+reason,Toast.LENGTH_LONG).show();
//                toastAnywhere(""+reason);


            }

            @Override
            public void onError(Exception ex) {
                Log.e("ragu", "onError: "+ex );
                toastAnywhere(""+ex);

            }
        };
        mWebSocketClient.connect();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),WorkboardActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
//    private void startdata() {
//        Log.e("ragu", "start: " );
//        Request request = new Request.Builder().url("ws://3.13.34.41:8081").build();
//        EchoWebSocketListener listener = new EchoWebSocketListener();
//        WebSocket ws = client.newWebSocket(request, listener);
//        client.dispatcher().executorService().shutdown();
//    }
//    private void stop() {
//        Log.e("ragu", "start: " );
//        Request request = new Request.Builder().url("ws://3.13.34.41:8081").build();
//        EchoWebSocketListeners listener = new EchoWebSocketListeners();
//        WebSocket ws = okHttpClient.newWebSocket(request, listener);
//        okHttpClient.dispatcher().executorService().shutdown();
//    }
//    public void finishdata() {
//        Log.e("ragu", "start: " );
//        Request request = new Request.Builder().url("ws://3.13.34.41:8081").build();
//        EchoWebSocketfinishListeners listener = new EchoWebSocketfinishListeners();
//        WebSocket ws = httpClient.newWebSocket(request, listener);
//        httpClient.dispatcher().executorService().shutdown();
//    }

    public void getapi()
    {
        ApiInterface service = APIClient.getClient().create(ApiInterface.class);
            Call<Ongoing_response> ongoing_responseCall = service.ongoingdata();
            ongoing_responseCall.enqueue(new Callback<Ongoing_response>() {
                @Override
                public void onResponse(Call<Ongoing_response> call, retrofit2.Response<Ongoing_response> response) {
                    Log.e("Ragu", "onResponse: body "+response.body());
                    for(int i=0;i<response.body().getData().size();i++)
                    {
//                        process=response.body().getData().get(i).getProcess().get(i).getProcess_name();
                        Log.e("Ragu", "onResponse: "+process );
                        Log.e("ragu", "onResponse: status "+response.body().getData().get(i).getLine_numbers().getStatus());
                         response_status=response.body().getData().get(i).getStatus();

                    }
                    im_clean.setImageResource(R.drawable.ic_cleaning);
//                    tv_clean.setText(""+process);
                    tv_clean.setTextColor(getResources().getColor(R.color.text_colour));
//                    Typeface typeface=Typeface.createFromAsset(getAssets(),"font/sourcesanspro_semibold.ttf");
//                    tv_clean.setTypeface(typeface);

                   btn_start.setText("Stop");
                   btn_start.setBackgroundResource(R.drawable.stop_button);
                   sharedPreference.putInt(getApplicationContext(),"start_response",response_status);


                }

                @Override
                public void onFailure(Call<Ongoing_response> call, Throwable t) {


//                    Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar.make(relativeJobStatus,"Check Your Network Connection!",Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    snackbar.show();
//                    Snackbar.make(getWindow().getDecorView().getRootView(),"No internet Connection",Toast.LENGTH_SHORT).show();

                }
            });
    }
    private void getsocketconnection() {
        Log.e("ragu", "getsocketconnection: " );

        URI uri;
        String url=CONST.Websocket_URL;

        try {
            uri = new URI(url);
        }
        catch (URISyntaxException e )
        {
            e.printStackTrace();
            Log.e("ragu", "getsocketconnection: not " );
            return;
        }
        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.e("ragu", "onOpened: " );
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("action","LINE_NUMBER_STATUS_UPDATE");
                    jsonObject.put(  "_id",process_id);
                    jsonObject.put("status",2);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mWebSocketClient.send(String.valueOf(jsonObject));
                Log.e("ragu", "onOpen: jsonobjectss "+jsonObject );
            }

            @Override
            public void onMessage(String message) {
                Log.e("ragu", "onMessage: "+message );

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.e("ragu", "onClose: socket "+reason );

            }

            @Override
            public void onError(Exception ex) {
                Log.e("ragu", "onError: "+ex );
                toastAnywhere(""+ex);

            }
        };
        mWebSocketClient.connect();
    }
    public void cleaningcheck()
    {
        if(response_status_clean_check ==0)
        {
            linearCleaning.setVisibility(View.VISIBLE);
            Log.e("ragu", "onCreate: status0 " );

        }
        else
        {
            linearCleaning.setVisibility(View.INVISIBLE);
            linearLayout_cutting.setVisibility(View.VISIBLE);
            im_clean.setImageResource(R.drawable.ic_cleaning);
            tv_clean.setTextColor(getResources().getColor(R.color.text_colour));
            Log.e("ragu", "onCreate: status1 " );
        }
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

}
