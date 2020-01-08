package com.firstpage.user.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import com.firstpage.user.Adapter.OngoingworksupervisorAdapter;
import com.firstpage.user.Common.CONST;
import com.firstpage.user.Common.CommonFunctions;
import com.firstpage.user.Common.SharedPreference;
import com.firstpage.user.Interfaces.APIClient;
import com.firstpage.user.Interfaces.ApiInterface;
import com.firstpage.user.Model.Dummyprovider;
import com.firstpage.user.Model.Ongoing;
import com.firstpage.user.Model.Ongoing_response;
import com.firstpage.user.Model.Order_process;
import com.firstpage.user.R;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OngoingsupervisorFragment extends Fragment {
    RecyclerView ongoing_recyclerview;
    ArrayList<Ongoing> ongoing_workArrayList = new ArrayList<Ongoing>();
    ArrayList<Order_process> order_processArrayList = new ArrayList<Order_process>();
    InputMethodManager inputManager;
    OngoingworksupervisorAdapter ongoing_adapter;
    String getArgument;
   Context context1;
    Ongoing_response jsonResponse;
    String data;
    SharedPreference sharedPreference;
    AppCompatTextView tvSearch;
    AppCompatTextView tvEmpty;
    RelativeLayout ongoingrelative;
    SwipeRefreshLayout swipeRefreshLayout;
    private WebSocketClient mWebSocketClient;
    JSONObject jsonObject;
    Boolean socket_status;
    String socket_msg;
    Boolean b;
    ArrayList<String>order_status = new ArrayList<String>();
    String id_user;
    int i;
    OngoingFragment ongoingFragment = new OngoingFragment();
    private Object OngoingFragment;
    FragmentManager manager;


    @SuppressLint("ValidFragment")
    public OngoingsupervisorFragment(Context context1) {
        this.context1=context1;

    }
    public OngoingsupervisorFragment()
    {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("ragu", "onCreate:new instance" );

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
//        if(!isConnected(getActivity()))
//        {
//            Log.e("ragu", "onCreate: connection " );
//            final BottomSheetDialogFragment myBottomSheet = MyBottomSheetDialogFragment.newInstance("Modal Bottom Sheet");
//            myBottomSheet.show(getActivity().getSupportFragmentManager(), myBottomSheet.getTag());
//
//        }
//        else {
//        }
        View view=inflater.inflate(R.layout.fragment_ongoing,container,false);
        sharedPreference=new SharedPreference();
        id_user=sharedPreference.getString(getActivity(),"id_user");
        tvSearch=(AppCompatTextView)view.findViewById(R.id.search_text);
        tvEmpty=(AppCompatTextView)view.findViewById(R.id.tv_empty);
        ongoingrelative=(RelativeLayout)view.findViewById(R.id.ongoing_relative);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.simpleSwipeRefreshLayout);
//         getsocketconnection();

//         getArgument = getArguments().getString("data");
//        System.out.println("the string value is:"+getArgument);

        ongoing_recyclerview=(RecyclerView)view.findViewById(R.id.ongoing_recycle);
        ongoing_recyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        ongoing_recyclerview.setLayoutManager(layoutManager);
//        getsocketconnection();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                getongoingdatas();

            }
        });
//        getsocketinitconnection();
        getsocketconnection();

        getongoingdata();
        return view;
    }

    private void getsocketinitconnection() {

            URI uri;
            String URL= CONST.Websocket_URL;
            try {
                uri = new URI(URL);
                Log.e("ragu", "getsocketconnection: uri"   +uri );
            }
            catch (URISyntaxException e )
            {
                e.printStackTrace();
                Log.e("ragu", "getsocketconnection: connect "  +e.toString() );
                return;
            }
            mWebSocketClient = new WebSocketClient(uri) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    Log.e("ragu", "onOpened: "+handshakedata.getHttpStatus() );
                    Log.e("ragu", "onOpen:connection "+mWebSocketClient.getReadyState());
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("action","INIT");
                        jsonObject.put("_id",id_user);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mWebSocketClient.send(String.valueOf(jsonObject));
                    Log.e("ragu", "onOpen: jsonobject "+jsonObject );
                }

                @Override
                public void onMessage(String message) {
//                Log.e("ragu", "onMessage: "+message );
                    try
                    {
                        JSONObject jsonObject = new JSONObject(message);
                        Log.e("ragu", "onMessage: "+jsonObject );
//                    Log.e("ragu", "onMessage:json "+jsonObject.getString("message"));
//                    Log.e("ragu", "onMessage: "+jsonObject.getBoolean("status"));

                    }
                    catch (Exception e)
                    {

                    }

                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.e("ragu", "onClose: "+reason );
                }

                @Override
                public void onError(Exception ex) {
                    Log.e("ragu", "onError: "+ex );
                }

            };
            mWebSocketClient.connect();

    }

    private void getsocketconnection() {
        URI uri;
        String URL= CONST.websocket_provider_url;
        try {
            uri = new URI(URL);
            Log.e("ragu", "getsocketconnection: uri"   +uri );
        }
        catch (URISyntaxException e )
        {
            e.printStackTrace();
            Log.e("ragu", "getsocketconnection: connect "  +e.toString() );
            return;
        }
        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {

                Log.e("ragu", "onOpen: socket"+handshakedata.getHttpStatusMessage());
                Log.e("ragu", "onOpen: socket status"+handshakedata.getHttpStatus());
                Log.e("ragu", "onOpen:open socket "+mWebSocketClient.getReadyState() );

//                Log.e("ragu", "onOpened: " );
//                JSONObject jsonObject = new JSONObject();
//                try {
//                    jsonObject.put("action","INIT");
//                    jsonObject.put("_id",id);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                mWebSocketClient.send(String.valueOf(jsonObject));
//                Log.e("ragu", "onOpen: jsonobject "+jsonObject );
            }

            @Override
            public void onMessage(String message) {
                Log.e("ragu", "onMessage: "+message );
                try
                {
                     jsonObject = new JSONObject(message);
                    Log.e("ragu", "onMessage: "+jsonObject );
                    Log.e("ragu", "onMessage:json "+jsonObject.getString("message"));
                    Log.e("ragu", "onMessage: "+jsonObject.getBoolean("status"));
                     socket_status=jsonObject.getBoolean("status");
                     socket_msg = jsonObject.getString("message");
                     b = Boolean.valueOf(true);
                    if(socket_status.equals(b))
                    {
                        getongoingdata("socket");
                    }

                }
                catch (Exception e)
                {
                    Log.e("ragu", "onMessage:exception "+e);

                }

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.e("ragu", "onClose: "+reason );

            }

            @Override
            public void onError(Exception ex) {
                Log.e("ragu", "onError: "+ex );
            }
        };
        mWebSocketClient.connect();

    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(Dummy dummy) {
//        Log.e("ragu", "onMessageEvent:dummy" );
//        String a=dummy.getName();
//        Log.e("ragu", "onMessageEvent: "+a );
//
//    };

//    private void getongoingdatas() {
//        CommonFunctions.showloadingProgressDialog(getActivity(),"Loading Data",false);
//        Log.e("ragu", "getdata: ");
//        ApiInterface service = APIClient.getClient().create(ApiInterface.class);
//        Call<Ongoing_response> ongoing_responseCall = service.ongoingdata();
//        ongoing_responseCall.enqueue(new Callback<Ongoing_response>() {
//            @Override
//            public void onResponse(Call<Ongoing_response> call, Response<Ongoing_response> response) {
//                CommonFunctions.removeloadingDialog();
//                Log.e("ragu", "onResponse: "+response);
//
//                if(response.body().getStatus())
//                {
//                    if(response.body().getData().size() > 0)
//                    {
//                        for(int i=0;i<response.body().getData().size();i++)
//                        {
//                            jsonResponse = response.body();
//                            Log.e("ragu", "ongoing onResponse: "+jsonResponse);
////                            ongoing_adapter = new OngoingworksupervisorAdapter(ongoing_workArrayList,order_processArrayList,getActivity());
//                            ongoing_workArrayList.addAll(response.body().getData());
////                            order_processArrayList.addAll(response.body().getData().get(i).getOrder_process());
//                            Log.e("ragu", "onResponse:order_processarraylist "+order_processArrayList );
//                            ongoing_recyclerview.setAdapter(ongoing_adapter);
//
//                        }
//
//
////                        Log.e("ragu", "onResponse: call "+response.body() );
////                        jsonResponse = response.body();
////                        ongoing_adapter = new OngoingworksupervisorAdapter(ongoing_workArrayList,getActivity());
////                        ongoing_workArrayList.addAll(response.body().getData());
////                        ongoing_recyclerview.setAdapter(ongoing_adapter);
//                    }
//
//                    else {
//                        tvEmpty.setVisibility(View.VISIBLE);
//
//
//                    }
//
//                }
//                else if(response.code()==403) {
//                    try {
//                        Ongoing_error posts = new Gson().fromJson(new JSONObject(response.errorBody().string()).toString(), Ongoing_error.class);
//                        Log.e("ragu", "onResponse:posts "+posts.getError_message());
////                        Toast.makeText(getApplicationContext(),"the response is:"+posts.getError_message(),Toast.LENGTH_LONG).show();
//                        CommonFunctions.shortToast(getActivity(),""+posts.getError_message());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        Log.e("ragu", "onResponse:Exception "+e.getMessage() );
//                    }
//
//
////                    tvEmpty.setVisibility(View.VISIBLE);
////                    tvEmpty.setText("No Data is Found");
//                }
//                else {
//                    tvEmpty.setVisibility(View.VISIBLE);
//                    tvEmpty.setText("No Data is Found");
//
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<Ongoing_response> call, Throwable t) {
//                Toast.makeText(getActivity(),"the error is:"+t,Toast.LENGTH_LONG).show();
////                Log.e("ragu", "onFailure: "+t);
//                CommonFunctions.removeloadingDialog();
//                tvEmpty.setVisibility(View.VISIBLE);
//                tvEmpty.setText("No Data");
//
//
//
////                     Toast.makeText(getActivity(),"No Internet Connection",Toast.LENGTH_SHORT).show();
////                      Snackbar.make(getActivity().getWindow().getDecorView().getRootView(), "No internet connnection:", Snackbar.LENGTH_LONG).show();
////                     Snackbar snackbar = Snackbar.make(ongoingrelative,"No internet connection",Snackbar.LENGTH_LONG);
////                     View snackbarView = snackbar.getView();
////                    snackbarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
////                    snackbar.show();
//            }
//        });
//
//
////        Ongoingwork ongoing_work = new Ongoingwork( 43656743,1000,234);
////        ongoing_workArrayList.add(ongoing_work);
////        Ongoingwork ongoing_work1 = new Ongoingwork( 43656743,1000,234);
////        ongoing_workArrayList.add(ongoing_work);
////        Ongoingwork ongoing_work2 = new Ongoingwork( 43656743,1000,234);
////        ongoing_workArrayList.add(ongoing_work);
//
//
//    }


    private boolean isConnected(FragmentActivity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;

    }
    public AlertDialog.Builder buildDialog(Context c) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finishAffinity();
            }
        });

        return builder;
    }

    private void getongoingdata() {
        Log.e("ragu", "getongoingdata:withoutsocket " );
        CommonFunctions.showloadingProgressDialog(getActivity(),"Fetching Job Details",false);

//        Log.e("ragu", "getdatass: ");
        ApiInterface service = APIClient.getClient().create(ApiInterface.class);
        Call<Ongoing_response> ongoing_responseCall = service.ongoingdata();
             ongoing_responseCall.enqueue(new Callback<Ongoing_response>() {
                 @Override
                 public void onResponse(Call<Ongoing_response> call, Response<Ongoing_response> response) {
                     CommonFunctions.removeloadingDialog();
                     if(response.body().getStatus())
                     {
                         if(response.body().getData().size() > 0)
                         {
//                            for( i=0;i<response.body().getData().size();i++)
//                            {
//                                Log.e("ragu", "onResponse:array list "+new
//                                        Gson().toJson(response.body().getData().get(i).getOrder_process()));
//
//                               order_processArrayList.addAll(response.body().getData().get(i).getOrder_process());
//                                Log.e("ragu", "onResponse:orderprocess "+order_processArrayList.size() );
////                                Log.e("ragu", "onResponse:orderarraylist"+order_processArrayList );
////                                order_processArrayList.notify();
////                                Log.e("ragu", "onResponse:order_arraylist "+order_processArrayList.size() );
//                            }
//                             Log.e("ragu", "onResponse: call "+response.body().getData() );
                                jsonResponse = response.body();

//                             Log.e("ragu", "onResponse:array data "+response.body().getData().get(i).getOrder_process() );

                             ongoing_workArrayList.addAll(response.body().getData());

//                             Log.e("ragu", "onResponse:size "+ongoing_workArrayList.size() );
                             Log.e("ragu", "onResponse:work arraylist "+new Gson().toJson(response.body()));

                             ongoing_adapter = new OngoingworksupervisorAdapter(ongoing_workArrayList,response.body().getData().get(i).getLine_numbers().getIs_exception(),getActivity(), new OngoingworksupervisorAdapter.ListAdapterListener() {
                                 @Override
                                 public void onClickAtOKButton(String position) {
                                     Log.e("ragu", "onClickAtOKButton: "+position );
//                                     sharedPreference.putString(getActivity(),"exception",position);


                                 }
                             });
//                             for(int i=0;i<response.body().getData().size();i++)
//                             {
//                                 Log.e("ragu", "onResponse:exception "+response.body().getData().get(i).getLine_numbers().getIs_exception() );
//                             }
//                             Log.e("ragu", "onResponse:wrking arraylist "+ongoing_workArrayList.size() );
                             ongoing_recyclerview.setAdapter(ongoing_adapter);
//                             Log.e("ragu", "onResponse:exception "+ongoing_workArrayList.get(i).getLine_numbers().getIs_exception() );
                             ongoing_adapter.notifyDataSetChanged();
//                             Log.e("ragu", "onResponse:notifydataset " );

                         }
                         else {
                             tvEmpty.setVisibility(View.VISIBLE);

                         }

                     }
                     else {
                        tvEmpty.setVisibility(View.VISIBLE);
                        tvEmpty.setText("No Data is Found");
                     }


                 }

                 @Override
                 public void onFailure(Call<Ongoing_response> call, Throwable t) {
                     Log.e("ragu", "onFailure: " );
                     CommonFunctions.removeloadingDialog();
                     tvEmpty.setVisibility(View.VISIBLE);
                     tvEmpty.setText("No Data");



//                     Toast.makeText(getActivity(),"No Internet Connection",Toast.LENGTH_SHORT).show();
//                      Snackbar.make(getActivity().getWindow().getDecorView().getRootView(), "No internet connnection:", Snackbar.LENGTH_LONG).show();
//                     Snackbar snackbar = Snackbar.make(ongoingrelative,"No internet connection",Snackbar.LENGTH_LONG);
//                     View snackbarView = snackbar.getView();
//                    snackbarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//                    snackbar.show();
                 }
             });


//        Ongoingwork ongoing_work = new Ongoingwork( 43656743,1000,234);
//        ongoing_workArrayList.add(ongoing_work);
//        Ongoingwork ongoing_work1 = new Ongoingwork( 43656743,1000,234);
//        ongoing_workArrayList.add(ongoing_work);
//        Ongoingwork ongoing_work2 = new Ongoingwork( 43656743,1000,234);
//        ongoing_workArrayList.add(ongoing_work);


    }
    private void getongoingdata(String socket) {

        CommonFunctions.showloadingProgressDialog(getActivity(),"Fetching Job Details",false);

        Log.e("ragu", "getdatass: ");
        ApiInterface service = APIClient.getClient().create(ApiInterface.class);
        Call<Ongoing_response> ongoing_responseCall = service.ongoingdata();
        ongoing_responseCall.enqueue(new Callback<Ongoing_response>() {
            @Override
            public void onResponse(Call<Ongoing_response> call, Response<Ongoing_response> response) {
                CommonFunctions.removeloadingDialog();
                if(response.body().getStatus())
                {
                    if(response.body().getData().size()>0)
                    {
                        ongoing_adapter = new OngoingworksupervisorAdapter(response.body().getData(),response.body().getData().get(i).getLine_numbers().getIs_exception(),getActivity(), new OngoingworksupervisorAdapter.ListAdapterListener() {
                            @Override
                            public void onClickAtOKButton(String position) {
                                Log.e("ragu", "onClickAtOKButton: "+position );
//                                     sharedPreference.putString(getActivity(),"exception",position);


                            }
                        });

                    }
                    else
                    {
                        tvEmpty.setVisibility(View.VISIBLE);
                        tvEmpty.setText("No Data is Found");
                    }
//                    for(int i=0;i<response.body().getData().get(i).getOrder_process().size();i++)
//                    {
//                        ongoing_adapter = new OngoingworksupervisorAdapter(response.body().getData(),getActivity());
//                        Log.e("ragu", "onResponse:order_process "+response.body().getData().get(i).getOrder_process() );
//                        ongoing_recyclerview.setAdapter(ongoing_adapter);
//                    }
//                    if(response.body().getData().size()>0)
//                    {
//                        for(int i=0;i<response.body().getData().size();i++)
//                        {
//
//                            order_processArrayList.addAll(response.body().getData().get(i).getOrder_process());
////                                order_processArrayList.notify();
////                                Log.e("ragu", "onResponse:order_arraylist "+order_processArrayList.size() );
//                        }
////                             Log.e("ragu", "onResponse: call "+response.body().getData() );
//                        jsonResponse = response.body();
//                        ongoing_workArrayList.addAll(response.body().getData());
//                        ongoing_adapter = new OngoingworksupervisorAdapter(ongoing_workArrayList,order_processArrayList,getActivity());
////                             Log.e("ragu", "onResponse:wrking arraylist "+ongoing_workArrayList.size() );
//                        ongoing_recyclerview.setAdapter(ongoing_adapter);
//                        ongoing_adapter.notifyDataSetChanged();
////                             Log.e("ragu", "onResponse:notifydataset " );
//
//                    }
//                    else {
//                        tvEmpty.setVisibility(View.VISIBLE);
//
//                    }

//                    for(int i=0;i<response.body().getData().size();i++)
//                    {
//                        ongoing_adapter = new OngoingworksupervisorAdapter(response.body().getData(),response.body().getData().get(i).getOrder_process().get(i),getActivity());
//                        ongoing_recyclerview.setAdapter(ongoing_adapter);
//
//                    }
////                    if(response.body().getData().size()>0)
//                    {
//                        for(int i=0;i<response.body().getData().size();i++)
//                        {
//                            order_processArrayList.addAll(response.body().getData().get(i).getOrder_process());
//
//                        }
//                        Log.e("ragu", "onResponse: call "+response.body() );
//                        jsonResponse = response.body();
//                        ongoing_adapter = new OngoingworksupervisorAdapter(ongoing_workArrayList,order_processArrayList,getActivity());
//                        ongoing_workArrayList.addAll(response.body().getData());
//
//                        ongoing_recyclerview.setAdapter(ongoing_adapter);
//
//
//                    }
//                    else {
//                        tvEmpty.setVisibility(View.VISIBLE);
//
//
//                    }

                }
                else {
                    tvEmpty.setVisibility(View.VISIBLE);
                    tvEmpty.setText("No Data is Found");
                }
            }

            @Override
            public void onFailure(Call<Ongoing_response> call, Throwable t) {
                Log.e("ragu", "onFailure: " );
                CommonFunctions.removeloadingDialog();
                tvEmpty.setVisibility(View.VISIBLE);
                tvEmpty.setText("No Data");



//                     Toast.makeText(getActivity(),"No Internet Connection",Toast.LENGTH_SHORT).show();
//                      Snackbar.make(getActivity().getWindow().getDecorView().getRootView(), "No internet connnection:", Snackbar.LENGTH_LONG).show();
//                     Snackbar snackbar = Snackbar.make(ongoingrelative,"No internet connection",Snackbar.LENGTH_LONG);
//                     View snackbarView = snackbar.getView();
//                    snackbarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//                    snackbar.show();

            }
        });


//        Ongoingwork ongoing_work = new Ongoingwork( 43656743,1000,234);
//        ongoing_workArrayList.add(ongoing_work);
//        Ongoingwork ongoing_work1 = new Ongoingwork( 43656743,1000,234);
//        ongoing_workArrayList.add(ongoing_work);
//        Ongoingwork ongoing_work2 = new Ongoingwork( 43656743,1000,234);
//        ongoing_workArrayList.add(ongoing_work);


    }
    public void search(String jobid)
    {
        Log.e("ragu", "search: "+jobid );
       ArrayList<Ongoing> filterlist = new ArrayList<>();
       for(Ongoing ongoing:ongoing_workArrayList)
       {
           if(ongoing.getLine_numbers().getJob_number().contains(jobid))
           {
               boolean b= ongoing.getLine_numbers().getJob_number().contains(jobid);
               Log.e("ragu", "search:jobid "+b );
               filterlist.add(ongoing);
               Log.e("ragu", "search: "+filterlist.toString() );
               tvSearch.setVisibility(View.INVISIBLE);
//               InputMethodManager imm = (InputMethodManager)getActivity(). getSystemService(Activity.INPUT_METHOD_SERVICE);
//               imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

           }
           else  {
               Log.e("ragu", "search: notfound " );
//               InputMethodManager imm = (InputMethodManager)getActivity(). getSystemService(Activity.INPUT_METHOD_SERVICE);
//               imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

//               Toast.makeText(getActivity(),"we cannot found the result",Toast.LENGTH_SHORT).show();
//               tvSearch.setVisibility(View.VISIBLE);
//               ongoing_recyclerview.setVisibility(View.INVISIBLE);
//               tvSearch.setText("Search result not found");
           }
       }
       ongoing_adapter.filter_list(filterlist);
    }
    @Override
    public void onStart() {
        Log.e("ragu", "onStart:fragment " );
        super.onStart();
//        if(!isConnected(getActivity()))
//        {
//            Log.e("ragu", "onCreate: connection " );
//            final BottomSheetDialogFragment myBottomSheet = MyBottomSheetDialogFragment.newInstance("Modal Bottom Sheet");
//            myBottomSheet.show(getActivity().getSupportFragmentManager(), myBottomSheet.getTag());
//
//        }
//        else {
//        }
//        getsocketconnection();


// add your code here which executes when the fragment gets visible.
    }



//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    public void gettake(String hello, int position, ArrayList<Order_process> order_process) {
//        Order_process orderProcess = order_process.get(position);
////        Log.e("ragu", "gettake:method"+order_process.get(i).getStatus() );
////        Process_fragment ldf = new Process_fragment ();
////        Bundle args = new Bundle();
////        args.putString("YourKey", "YourValue");
////        ldf.setArguments(args);
////        getFragmentManager().beginTransaction().add(R.id.container,ldf).commit();
////         getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, ldf).commit();
////        Bundle args = new Bundle();
////        args.putInt("doctor_id",1);
////        Process_fragment newFragment = new Process_fragment ();
////        newFragment.setArguments(args);
////        OngoingsupervisorFragment fragment = new OngoingsupervisorFragment(); // replace your custom fragment class
////        Bundle bundle = new Bundle();
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
////            manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
////        }
////        FragmentTransaction fragmentTransaction =manager.beginTransaction();
////        bundle.putSerializable("order",orderProcess);
////        fragment.setArguments(bundle);
////        fragmentTransaction.addToBackStack(null);
////        fragmentTransaction.replace(R.id.ongoing_relative,fragment);
////        fragmentTransaction.commit();
////        Bundle args = new Bundle();
////        args.putSerializable("order",order_process);
////        Process_fragment toFragment = new Process_fragment();
////        toFragment.setArguments(args);
////        getFragmentManager()
////                .beginTransaction()
////        fragmentTransaction.replace(R.id.fragment_container, mFeedFragment);
//////                .replace(R.id.ongoing_relative, toFragment,ongoingFragment )
////                .addToBackStack(ongoingFragment).commit();
//
//
//
//
//    }

//    public void getmethod(String dummy) {
//
//        String texts = dummy;
//        Process_fragment process_fragment = new Process_fragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("hai",texts);
//        process_fragment.setArguments(bundle);
//        if (getFragmentManager() != null) {
//            getFragmentManager().beginTransaction().add(R.id.job_status_relative,process_fragment).commit();
//        }
//
//
//    }

//    @Override
//    public void onClickAtOKButton(String data) {
//
//
//    }

//    @Override
//    public void onClickAtOKButton(String position) {
//        Log.e("ragu", "onClickAtOKButton: "+position );
//
//    }


//    @Override
//    public void onStart() {
//        Log.e("ragu", "onStart: eventbus " );
//        super.onStart();
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onDestroy() {
//        Log.e("ragu", "onDestroy: eventbus " );
//        super.onDestroy();
//        EventBus.getDefault().register(this);
//    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        Log.e("ragu", "onPause:selected " );
//        if(!isConnected(getActivity()))
//        {
//            Log.e("ragu", "onCreate: connection " );
//            final BottomSheetDialogFragment myBottomSheet = MyBottomSheetDialogFragment.newInstance("Modal Bottom Sheet");
//            myBottomSheet.show(getActivity().getSupportFragmentManager(), myBottomSheet.getTag());
//        }
//        else {
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        Log.e("ragu", "onresume:selected " );
//        if(!isConnected(getActivity()))
//        {
//            Log.e("ragu", "onCreate: connection " );
//            final BottomSheetDialogFragment myBottomSheet = MyBottomSheetDialogFragment.newInstance("Modal Bottom Sheet");
//            myBottomSheet.show(getActivity().getSupportFragmentManager(), myBottomSheet.getTag());
//        }
//        else {
//        }
//
//    }
}


