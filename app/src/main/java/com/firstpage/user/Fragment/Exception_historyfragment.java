package com.firstpage.user.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firstpage.user.Adapter.Exceptionhistory_itemsadapter;
import com.firstpage.user.Common.SharedPreference;
import com.firstpage.user.Interfaces.APIClient;
import com.firstpage.user.Interfaces.ApiInterface;
import com.firstpage.user.Model.Exception_history;
import com.firstpage.user.Model.Ongoing_response;
import com.firstpage.user.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Exception_historyfragment extends Fragment {
    public RecyclerView exception_Recycler;
    public ArrayList<Exception_history> exception_historyArrayList;
    public Exceptionhistory_itemsadapter exceptionhistory_itemsadapter;
    SharedPreference sharedPreference;
    String onedata;
    public Exception_historyfragment() {

// Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.exceptionrecycler_activity,container,false);
        exception_Recycler=(RecyclerView)view.findViewById(R.id.exception_recycler);
//        Bundle bundle = this.getArguments();
//        String myValue = null;
//        if (bundle != null) {
//            myValue = bundle.getString("hai");
//            Log.e("ragu", "onCreateView:getString "+myValue );
//        }
        sharedPreference = new SharedPreference();
        onedata = sharedPreference.getString(getActivity(),"one");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        exception_Recycler.setLayoutManager(layoutManager);
        getjob();
        return view;
    }

//    private void getjob() {
//        exception_historyArrayList = new ArrayList<>();
//        exceptionhistory_itemsadapter = new Exceptionhistory_itemsadapter(getActivity(),exception_historyArrayList);
//        getexception();
//        exception_Recycler.setAdapter(exceptionhistory_itemsadapter);
//    }
//
//    private void getexception() {
//        Exception_history exception_history = new Exception_history("Plumbing");
//        exception_historyArrayList.add(exception_history);
//        Exception_history exception_history1 = new Exception_history("Screwing");
//        exception_historyArrayList.add(exception_history1);
//        Exception_history exception_history2 = new Exception_history("Cutting");
//        exception_historyArrayList.add(exception_history2);
//        Exception_history exception_history3 = new Exception_history("Packing");
//        exception_historyArrayList.add(exception_history3);
//    }
    private void getjob()
    {
//        CommonFunctions.showloadingProgressDialog(getActivity(),"",false);
        ApiInterface service = APIClient.getClient().create(ApiInterface.class);
        Call<Ongoing_response> ongoing_responseCall = service.ongoingdata();
        ongoing_responseCall.enqueue(new Callback<Ongoing_response>() {
            @Override
            public void onResponse(Call<Ongoing_response> call, Response<Ongoing_response> response) {
//                CommonFunctions.removeloadingDialog();
                if(response.body().getData().size()>0)
                {

                    for(int i=0;i<response.body().getData().size();i++)
                    {

                        if(onedata.equals(response.body().getData().get(i).get_id()))
                        {

//                            Log.e("ragu", "onResponse:process name "+processname );
                            exceptionhistory_itemsadapter = new Exceptionhistory_itemsadapter(getActivity(),response.body().getData().get(i).getOrder_process(),response.body().getData().get(i).getLine_numbers().get_id(),response.body().getData().get(i).getLine_numbers().getJob_number(),response.body().getData().get(i).getLine_numbers().getLine_number(),response.body().getData().get(i).getLine_numbers().getQuantity(),response.body().getData().get(i).getPurchase_order().getPurchase_order_number());
                            Log.e("ragu", "onResponse:processname " );
                            exception_Recycler.setAdapter(exceptionhistory_itemsadapter);

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
                }
                else
                {
                    Toast.makeText(getActivity(),"empty data",Toast.LENGTH_LONG).show();
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
}
