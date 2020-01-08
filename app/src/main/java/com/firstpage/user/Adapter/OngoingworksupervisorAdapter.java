package com.firstpage.user.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.firstpage.user.Common.SessionManager;
import com.firstpage.user.Common.SharedPreference;
import com.firstpage.user.Fragment.OngoingsupervisorFragment;
import com.firstpage.user.Model.Ongoing;
import com.firstpage.user.Model.Order_process;
import com.firstpage.user.R;
import com.firstpage.user.Ui.ExceptionActivity;
import com.firstpage.user.Ui.jobdetailstatustrackActivity;

import java.util.ArrayList;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;

//import com.vaibhavlakhera.circularprogressview.CircularProgressView;

//import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;

public class OngoingworksupervisorAdapter extends RecyclerView.Adapter<OngoingworksupervisorAdapter.Viewholder> {
    Context context;
    ArrayList<Ongoing> ongoing_workArrayList;
    ArrayList<Order_process> order_processArrayList;
    SharedPreference sharedPreference = new SharedPreference();
    SessionManager sessionManager;
    private int lastPosition = -1;
    private int checkedPosition = 0;
    RecyclerView ongoing_recyclerview;
    int finish_process;
     Ongoing ongoing_work;
    ArrayList<Order_process> jobongoing;
    Order_process order_process;
     String is_exception;
    String exceptior;
    int count=0;
    private ListAdapterListener mListener;
FragmentManager fragmentManager;
OngoingsupervisorFragment ongoingsupervisorFragment;

    public OngoingworksupervisorAdapter(ArrayList<Ongoing> ongoing_workArrayList, String is_exception, Context context, ListAdapterListener mListener) {
        this.ongoing_workArrayList=ongoing_workArrayList;
        this.is_exception=is_exception;
        this.context=context;
        this.mListener=mListener;

    }

    public interface ListAdapterListener { // create an interface
        void onClickAtOKButton(String position); // create callback function
    }
//    private ListAdapterListener mListener;
//    Callback<Ongoing_response> ongoing_responseCallback;

//    public OngoingworksupervisorAdapter(ArrayList<Ongoing> ongoing_workArrayList, String is_exception, Context context,ListAdapterListener mListener ) {
//        this.ongoing_workArrayList=ongoing_workArrayList;
//        this.is_exception=is_exception;
//        this.context=context;
//        this.mListener=mListener;
//
//
//    }

//    public OngoingworksupervisorAdapter(ArrayList<Ongoing> ongoing_workArrayList, String is_exception, Context context, Callback<Ongoing_response> ongoing_responseCallback) {
//        this.ongoing_workArrayList=ongoing_workArrayList;
//        this.is_exception=is_exception;
//        this.context=context;
//        this.ongoing_responseCallback = ongoing_responseCallback;
//
//    }

//    public interface ListAdapterListener { // create an interface
//        void onClickAtOKButton(String position); // create callback function
//    }

    public OngoingworksupervisorAdapter(ArrayList<Ongoing> ongoing_workArrayList, Context context) {
        this.ongoing_workArrayList = ongoing_workArrayList;
        this.context = context;

    }

//    public OngoingworksupervisorAdapter(ArrayList<Ongoing> ongoing_workArrayList, ArrayList<Order_process> order_processArrayList, Context context,ListAdapterListener mListener) {
//        this.ongoing_workArrayList=ongoing_workArrayList;
//        this.order_processArrayList=order_processArrayList;
//        this.context=context;
//        this.mListener=mListener;
//    }

    public OngoingworksupervisorAdapter(ArrayList<Ongoing> ongoing_workArrayList, String is_exception, Context context) {
        this.ongoing_workArrayList=ongoing_workArrayList;
        this.is_exception=is_exception;
        this.context=context;

    }

//    public OngoingworksupervisorAdapter(ArrayList<Ongoing> ongoing_workArrayList, Order_process order_process, Context context) {
//        this.ongoing_workArrayList=ongoing_workArrayList;
//        this.order_process=order_process;
//        this.context=context;
//
//    }

//    public OngoingworksupervisorAdapter(ArrayList<Ongoing> ongoing_workArrayList, ArrayList<Integer> order_processArrayLists, Context context) {
//        this.ongoing_workArrayList=ongoing_workArrayList;
//        this.order_processArrayLists = order_processArrayLists;
//        this.context=context;
//    }

//    public OngoingworksupervisorAdapter(ArrayList<Ongoing> ongoing_workArrayList, ArrayList<Order_process> order_processArrayList, FragmentActivity activity) {
//    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ongoing_workadapter_items, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder viewholder,  int i) {
//        viewholder.bind(ongoing_workArrayList.get(i));

       final Ongoing ongoing_work= ongoing_workArrayList.get(i);
       ongoingsupervisorFragment = new OngoingsupervisorFragment();
        exceptior= is_exception;
//        if(exceptior.equals("false"))
//        {
//            count++;
//        }
//        Log.e("ragu", "onBindViewHolder:total count "+count );
//        Log.e("ragu", "onBindViewHolder:exceptior "+exceptior );

//        Log.e("ragu", "onBindViewHolder:click status "+ongoing_work.getOrder_process().get(i).getStatus() );

//       for(int i1=0;i<ongoing_work.getOrder_process().size();i1++)
//       {
//           Log.e("ragu", "onBindViewHolder:order status "+ongoing_work.getOrder_process().get(i).getStatus() );
//
//       }
//        Log.e("ragu", "onBindViewHolder:status order "+ongoing_work.getOrder_process().get(i).getStatus() );



//        Log.e("ragu", "onBindViewHolder:test "+ongoing_work.getOrder_process().get(i).getStatus() );

//        Log.e("ragu", "onBindViewHolder:orderproceessarraylist "+order_processArrayList.get(i).getStatus() );
//        Log.e("ragu", "onBindViewHolder:arraylist "+order_process.getStatus());
//        Log.e("ragu", "onBindViewHolder:order_process "+order_processArrayList.get(i).getStatus());


       sessionManager = new SessionManager(context);
   viewholder.job_no.setText(""+ongoing_work.getLine_numbers().getCustomer_id().getCustomer_code());
   viewholder.quantity.setText(""+ongoing_work.getLine_numbers().getJob_number());
   viewholder.line_no.setText(""+ongoing_work.getLine_numbers().getQuantity());
//   viewholder.tvPart_no.setText(""+ongoing_work.getLine_numbers().getPart_number());
   viewholder.tv_Customercodeno.setText(""+ongoing_work.getLine_numbers().getPart_number());

        for( i=0;i<ongoing_work.getOrder_process().size();i++)
        {
            if(ongoing_work.getOrder_process().get(i).getStatus() !=4)
            {
                viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color1));
                break;

            }
            else
            {
                viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color4));
            }
//            if(ongoing_work.getOrder_process().get(i).getStatus()==4)
//            {
//                viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color4));
//
//            }
//            else {
//                Log.e("ragu", "onBindViewHolder:hello " );
//                viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color1));
//
//            }
//            Log.e("ragu", "onBindViewHolder: status"+ongoing_work.getOrder_process().get(i).getStatus() );
//            if(ongoing_work.getOrder_process().size()== 1)
//            {
////                if(ongoing_work.getOrder_process().get(i).getStatus().equals("4"))
////                {
////                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color4));
////
////                }
////                else {
////                    Log.e("ragu", "onBindViewHolder:hello " );
////                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color1));
////
////                }
//            }
//            else if(ongoing_work.getOrder_process().size() == 2)
//            {
//                if(ongoing_work.getOrder_process().get(i).getStatus().equals("4"))
//                {
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color4));
//
//                }
//                else {
//                    Log.e("ragu", "onBindViewHolder:hellos data " );
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color1));
//
//                }
//
//            }
//            else if(ongoing_work.getOrder_process().size() == 3)
//            {
//                if(ongoing_work.getOrder_process().get(i).getStatus().equals("4"))
//                {
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color4));
//
//                }
//                else {
//                    Log.e("ragu", "onBindViewHolder:hello text " );
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color1));
//
//                }
//
//            }
//            else if(ongoing_work.getOrder_process().size() == 4)
//            {
//                if(ongoing_work.getOrder_process().get(i).getStatus().equals("4"))
//                {
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color4));
//
//                }
//                else {
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color1));
//
//                }
//
//            }
//            else if(ongoing_work.getOrder_process().size() == 5)
//            {
//                if(ongoing_work.getOrder_process().get(i).getStatus().equals("4"))
//                {
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color4));
//
//                }
//                else {
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color1));
//
//                }
//
//            }
//            else if(ongoing_work.getOrder_process().size() == 6)
//            {
//                if(ongoing_work.getOrder_process().get(i).getStatus().equals("4"))
//                {
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color4));
//
//                }
//                else {
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color1));
//
//                }
//
//            }
//            else if(ongoing_work.getOrder_process().size() == 7)
//            {
//                if(ongoing_work.getOrder_process().get(i).getStatus().equals("4"))
//                {
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color4));
//
//                }
//                else {
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color1));
//
//                }
//
//            }
//            else if(ongoing_work.getOrder_process().size() == 8)
//            {
//                if(ongoing_work.getOrder_process().get(i).getStatus().equals("4"))
//                {
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color4));
//
//                }
//                else {
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color1));
//
//                }
//
//            }
//            else if(ongoing_work.getOrder_process().size() == 9)
//            {
//                if(ongoing_work.getOrder_process().get(i).getStatus().equals("4"))
//                {
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color4));
//
//                }
//                else {
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color1));
//
//                }
//
//            }
//            else if(ongoing_work.getOrder_process().size() == 10)
//            {
//                if(ongoing_work.getOrder_process().get(i).getStatus().equals("4"))
//                {
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color4));
//
//                }
//                else {
//                    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color1));
//
//                }
//
//            }
//            if(ongoing_work.getOrder_process().get(i).getStatus().equals("4"))
//            {
//                viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color4));
//
//            }
//            else
//            {
//                viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color1));
//            }
//            Log.e("ragu", "onBindViewHolder:order status "+ongoing_work.getOrder_process().get(i).getStatus() );

        }

        setZoomInAnimation(viewholder.itemView, i);


//    if(order_process.getStatus().equals("4"))
//    {
////    viewholder.circularProgressIndicator.setProgress(0, 4);
////    viewholder.circularProgressIndicator.setDotColor(context.getResources().getColor(R.color.progress_color1));
////    viewholder.circularProgressIndicator.setTextSizeSp(15);
////    viewholder.circularProgressIndicator.setTextColor(context.getResources().getColor(R.color.progress_text_color));
//    viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color4));
////    viewholder.circularProgressIndicator.setProgressTextAdapter(new CircularProgressIndicator.ProgressTextAdapter() {
////        @NonNull
////        @Override
////        public String formatText(double currentProgress) {
////            double a = currentProgress;
////            int b = (int) a;
////            String divide = "/4";
////            String c = String.valueOf(b);
////            String d = c.concat(divide);
////            Log.e("ragu", "formatText: " + d);
////            return String.valueOf(d);
////        }
////    });
//
//    }
//    else {
//        viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color1));
//
//    }

//       Log.e("ragu", "onBindViewHolder:status "+ongoing_work.getOrder_process().get(i).getStatus() );


        int progress_stage = sharedPreference.getInt(context, "progress");
//        final int confirm_key=sharedPreference.getInt(context,"job_confirm");
        final int job_confirm = sharedPreference.getInt(context, "job_confirm_position");
        int finish_stage = sharedPreference.getInt(context, "finish");
        Log.e("ragu", "onBindViewHolder: " + finish_stage);
        Log.e("ragu", "onBindViewHolder: " + finish_stage);
        Log.e("ragu", "onBindViewHolder:job_confirm" + job_confirm);

//        String process_name=ongoing_work.getOrder_process().get(i).getProcess().getProcess_name();
//        if(process_name.isEmpty())
//        {
//            Log.e("ragu", "onBindViewHolder: empty" );
//        }
//        else {
//            Log.e("ragu", "onBindViewHolder:not empty " );
//        }

//        Log.e("ragu", "onBindViewHolder:process_name"+ongoing_work.getOrder_process().get(i).getProcess().getProcess_name() );
//        Log.e("ragu", "onBindViewHolder:process_desc"+ongoing_work.getOrder_process().get(i).getProcess().getProcess_desc() );


//      job_confirm=sharedPreference.getInt(context,"job_confirm_position");
//        Log.e("ragu", "onBindViewHolder: "+job_confirm );


//        if (progress_stage == 0) {
//
//            Log.e("ragu", "onBindViewHolder:position " + viewholder.getAdapterPosition());
//            viewholder.circularProgressIndicator.setProgress(0, 4);
//            viewholder.circularProgressIndicator.setDotColor(context.getResources().getColor(R.color.progress_color1));
//            viewholder.circularProgressIndicator.setTextSizeSp(15);
//            viewholder.circularProgressIndicator.setTextColor(context.getResources().getColor(R.color.progress_text_color));
//            viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color1));
//            viewholder.circularProgressIndicator.setProgressTextAdapter(new CircularProgressIndicator.ProgressTextAdapter() {
//                @NonNull
//                @Override
//                public String formatText(double currentProgress) {
//                    double a = currentProgress;
//                    int b = (int) a;
//                    String divide = "/4";
//                    String c = String.valueOf(b);
//                    String d = c.concat(divide);
//                    Log.e("ragu", "formatText: " + d);
//                    return String.valueOf(d);
//                }
//            });
//
//        }
//        else if (progress_stage == 2) {
//            viewholder.circularProgressIndicator.setProgress(progress_stage, 4);
//            viewholder.circularProgressIndicator.setDotColor(context.getResources().getColor(R.color.progress_color2));
//            viewholder.circularProgressIndicator.setProgressColor(context.getResources().getColor(R.color.progress_color2));
//            viewholder.circularProgressIndicator.setTextSizePx(20);
//            viewholder.circularProgressIndicator.setTextColor(context.getResources().getColor(R.color.progress_text_color));
//            viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color2));
//            viewholder.circularProgressIndicator.setProgressTextAdapter(new CircularProgressIndicator.ProgressTextAdapter() {
//                @NonNull
//                @Override
//                public String formatText(double currentProgress) {
//                    double a = currentProgress;
//                    int b = (int) a;
//                    String divide = "/4";
//                    String c = String.valueOf(b);
//                    String d = c.concat(divide);
//                    Log.e("ragu", "formatText: " + d);
//                    return String.valueOf(d);
//                }
//            });
//
//
//        }
//        else if (progress_stage == 3) {
//            viewholder.circularProgressIndicator.setProgress(progress_stage, 4);
//            viewholder.circularProgressIndicator.setDotColor(context.getResources().getColor(R.color.progress_color3));
//            viewholder.circularProgressIndicator.setProgressColor(context.getResources().getColor(R.color.progress_color3));
//            viewholder.circularProgressIndicator.setTextSizePx(20);
//            viewholder.circularProgressIndicator.setTextColor(context.getResources().getColor(R.color.progress_text_color));
//            viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color3));
//            viewholder.circularProgressIndicator.setProgressTextAdapter(new CircularProgressIndicator.ProgressTextAdapter() {
//                @NonNull
//                @Override
//                public String formatText(double currentProgress) {
//                    double ab = currentProgress;
//                    int b = (int) ab;
//                    String divider = "/4";
//                    String e = String.valueOf(b);
//                    String f = e.concat(divider);
//                    Log.e("ragu", "formatText: " + b);
//                    return String.valueOf(f);
//                }
//            });
//        }
//        else if (progress_stage == 4) {
//            viewholder.circularProgressIndicator.setProgress(progress_stage, 4);
//            viewholder.circularProgressIndicator.setDotColor(context.getResources().getColor(R.color.progress_color4));
//            viewholder.circularProgressIndicator.setProgressColor(context.getResources().getColor(R.color.progress_color4));
//            viewholder.circularProgressIndicator.setTextSizePx(20);
//            viewholder.circularProgressIndicator.setTextColor(context.getResources().getColor(R.color.progress_text_color));
//            viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color4));
//            viewholder.circularProgressIndicator.setProgressTextAdapter(new CircularProgressIndicator.ProgressTextAdapter() {
//                @NonNull
//                @Override
//                public String formatText(double currentProgress) {
//                    double ab = currentProgress;
//                    int b = (int) ab;
//                    String divider = "/4";
//                    String e = String.valueOf(b);
//                    String f = e.concat(divider);
//                    Log.e("ragu", "formatText: " + b);
//                    return String.valueOf(f);
//                }
//            });
//
//        }
//        setZoomInAnimation(viewholder.itemView, i);


//    if(progress_stage==0)
//    {
//        viewholder.circularProgressView.setProgress(0,true);
//
//    }
//    else if(progress_stage==2)
//    {
//        viewholder.circularProgressView.setProgress(progress_stage,true);
//        viewholder.circularProgressView.setProgressColor(context.getResources().getColor(R.color.colorAccent));
//        viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
//    }
//    else if(progress_stage==3)
//    {
//        viewholder.circularProgressView.setProgress(progress_stage,true);
//        viewholder.circularProgressView.setProgressColor(context.getResources().getColor(R.color.progress_color));
//        viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color));
//    }
//    else if(progress_stage==4)
//    {
//        viewholder.circularProgressView.setProgress(progress_stage,true);
//        viewholder.circularProgressView.setProgressColor(context.getResources().getColor(R.color.progress_color1));
//        viewholder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.progress_color1));
//    }

        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Log.e("ragu", "onBindViewHolder:exceptior "+exceptior );
//                ongoingsupervisorFragment.gettake("Hello",viewholder.getPosition(),ongoing_work.getOrder_process());
                mListener.onClickAtOKButton(is_exception);
//                v.getContext().startActivity(new Intent(context, ExceptionActivity.class));






//                fragment fragment = new OngoingsupervisorFragment();
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                FragmentManager fragmentManager = activity.getSupportFragmentManager(); // this is basically context of the class
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                Bundle bundle=new Bundle();
//                bundle.putString("name", "From adapter"); //key and value
////set Fragmentclass Arguments
//                fragment.setArguments(bundle);
//                fragmentTransaction.replace(R.id.content_frame, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();

//                Log.e("ragu", "onBindViewHolder:orderproceessarraylist "+order_processArrayList.get(i).getStatus() );
//                Log.e("ragu", "onBindViewHolder:test "+ongoing_work.getOrder_process().get(i).getStatus() );
//                Log.e("ragu", "onBindViewHolder: click item"+order_processArrayLists.get(i) );
//                Log.e("ragu", "onClick:onprocess status "+order_process.getStatus() );
//                Log.e("ragu", "onClick:position "+i );
//                Log.e("ragu", "onClick:job_status "+order_process.getStatus());
//                Log.e("ragu", "onClick: company_id"+order_process.get_id() );
//                Log.e("ragu", "onClick:job no " + ongoing_work.getLine_numbers().getJob_number());
//                Log.e("ragu", "onClick:orderprocess " + ongoing_work.getOrder_process());
//                if (checkedPosition != viewholder.getAdapterPosition()) {
//                    notifyItemChanged(checkedPosition);
//                    checkedPosition = viewholder.getAdapterPosition();
//                }

//            sharedPreference.putString(v.getContext(),"Jobno",ongoing_work.getLine_numbers().getJob_number());
//            sharedPreference.putString(v.getContext(),"Line_number",ongoing_work.getLine_numbers().getLine_number());
//            sharedPreference.putString(v.getContext(),"quantity",ongoing_work.getLine_numbers().getQuantity());
//            sharedPreference.putString(v.getContext(),"purchase_order",ongoing_work.getPurchase_order().getPurchase_order_number());
//                sharedPreference.putString(v.getContext(), "processid", ongoing_work.getOrder_process().get(i).getProcess().get_id());
//                sharedPreference.putInt(v.getContext(), "position_clicked", i);
                Log.e("ragu", "onClick:exception "+ongoing_work.getLine_numbers().getIs_exception() );
                final int confirm_key = sharedPreference.getInt(context, "job_confirm");
                Log.e("ragu", "onClick:status " + ongoing_work.getLine_numbers().getStatus());



//           final int  job_confirm=sharedPreference.getInt(context,"job_confirm_position");
//            Log.e("ragu", "onBindViewHolder:job_confirm"+job_confirm );
//                 Check activity_login status
//                    ongoingsupervisorFragment.getmethod("hello");
                    Log.e("ragu", "checkconfirm: inside ");
                sharedPreference.putString(context,"one",ongoing_work.get_id());
                Intent i = new Intent(context, ExceptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("keyarrays", ongoing_work);
                i.putExtras(bundle);
                context.startActivity(i);

//                i.putExtra("jobno", ongoing_work.getLine_numbers().getJob_number()).putExtra("Line_number", ongoing_work.getLine_numbers().getLine_number())
//                        .putExtra("quantity", ongoing_work.getLine_numbers().getQuantity()).putExtra("purchase_order", ongoing_work.getPurchase_order().getPurchase_order_number());
//                Log.e("ragu", "checkconfirm: ");
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("keyarrays", ongoing_work);
//                i.putExtras(bundle);
//                context.startActivity(i);

//                    v.getContext().startActivity(new Intent(context,ExceptionActivity.class).putExtra("jobno",ongoing_work.getLine_numbers().getJob_number()).putExtra("Line_number",ongoing_work.getLine_numbers().getLine_number())
//                    .putExtra("quantity",ongoing_work.getLine_numbers().getQuantity()).putExtra("purchase_order",ongoing_work.getPurchase_order().getPurchase_order_number()));





//                Bundle b = new Bundle();
//                b.putString("user_name","simon");
//                b.putString("user_address","nepal");
////fragment operation
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                Fragment fragment=new friendfragment();
//                fragment.setArguments(b);
//                transaction.replace(R.id.container, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//                Fragment fr=new Process_fragment();
//                FragmentManager fm=v.getContext().getFra();
//                FragmentTransaction ft=fm.beginTransaction();
//                Bundle args = new Bundle();
//                args.putString("CID", "cid");
//                fr.setArguments(args);
//                ft.replace(R.id.relative_ongoing, fr);
//                ft.commit();
//                Process_fragment secondFragmentName = new Process_fragment();
//                Bundle args = new Bundle();
//                args.putString("key", "value");
//                secondFragmentName.setArguments(args);
//                FragmentManager fragmentManager = context.getSupportFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.frame_container, fragment).commit();


//                OngoingsupervisorFragment ldf = new OngoingsupervisorFragment ();
//                Bundle args = new Bundle();
//                args.putString("YourKey", "YourValue");
//                ldf.setArguments(args);

//Inflate the fragment
//                v.getContext().getFragmentManager().beginTransaction().add(R.id.container, ldf).commit();

//                Bundle bundle=new Bundle();
//                bundle.putSerializable("obj",ongoing_work);
//                Process_fragment fragment=new Process_fragment();
//                fragment.setArguments(bundle);
//                fragmentManager = getActivity().getSupportFragmentManager();
//                fragmentTransaction = fragmentManager .beginTransaction();
//                fragmentTransaction.add(R.id.container, fragment);
//                fragmentTransaction.commit();


//                    Process_fragment process_fragment = new Process_fragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("key_array",ongoing_work);
//                    process_fragment.setArguments(bundle);
//                Log.e("ragu", "onClick:process " );

//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("key_array",ongoing_work);
//                    Process_fragment process_fragment = new Process_fragment();
//                    process_fragment.setArguments(bundle);




//                Bundle args = new Bundle();
//                args.putInt("doctor_id",1);
//                Process_fragment newFragment = new Process_fragment ();
//                newFragment.setArguments(args);

//                Bundle bundle = new Bundle();
//                bundle.putSerializable("keyarray",ongoing_work);
//                Process_fragment process_fragment = new Process_fragment();
//                process_fragment.setArguments(bundle);

//                    Intent i = new Intent(context, jobdetailstatustrackActivity.class);
//                    i.putExtra("jobno", ongoing_work.getLine_numbers().getJob_number()).putExtra("Line_number", ongoing_work.getLine_numbers().getLine_number())
//                            .putExtra("quantity", ongoing_work.getLine_numbers().getQuantity()).putExtra("purchase_order", ongoing_work.getPurchase_order().getPurchase_order_number());
//                    Log.e("ragu", "checkconfirm: ");
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("keyarray", ongoing_work);
//                    i.putExtras(bundle);
//                    v.getContext().startActivity(i);

//                else if (ongoing_work.getLine_numbers().getStatus().equals("1"))
//                {
//                    Log.e("ragu", "checkconfirm: outside ");
//                    v.getContext().startActivity(new Intent(context, JobdetailsActivity.class).putExtra("id", ongoing_work.getLine_numbers().get_id())
//                            .putExtra("status", ongoing_work.getLine_numbers().getStatus()).putExtra("purchase_order", ongoing_work.getPurchase_order().getPurchase_order_number())
//                            .putExtra("lineno", ongoing_work.getLine_numbers().getLine_number()).putExtra("quantity", ongoing_work.getLine_numbers().getQuantity()).putExtra("jobno", ongoing_work.getLine_numbers().getJob_number()));
//                }
            }
        });

    }

    private void setZoomInAnimation(View itemView, int i) {
        if (i > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.itemanimationfalldown);
            itemView.startAnimation(animation);
            lastPosition = i;
        }

    }

    @Override
    public int getItemCount() {
        Log.e("ragu", "getItemCount: worklist"+ongoing_workArrayList.size() );
        if (ongoing_workArrayList.size() == 0) {

//            Toast.makeText(context, "Job no is not found here", Toast.LENGTH_LONG).show();
        }
        return ongoing_workArrayList.size();
    }

    public void filter_list(ArrayList<Ongoing> ongoings) {
        ongoing_workArrayList = ongoings;
        Log.e("ragu", "filter_list: "+ongoings.size() );
        if(ongoing_workArrayList.size()==0)
        {
            Toast.makeText(context, "Job no is not found here", Toast.LENGTH_SHORT).show();

        }
        notifyDataSetChanged();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        public AppCompatTextView job_no;
        public AppCompatTextView quantity;
        public AppCompatTextView line_no;
        public AppCompatTextView tvPart_no;
        //       public CircularProgressView circularProgressView;
        public CircularProgressIndicator circularProgressIndicator;
        public RelativeLayout relativeLayout;
        public AppCompatTextView tv_Customercodeno;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            job_no = (AppCompatTextView) itemView.findViewById(R.id.tv_ongoing_job_no);
//            relative_ongoing=(LinearLayout)itemView.findViewById(R.id.relative_ongoing);
            quantity = (AppCompatTextView) itemView.findViewById(R.id.ongoing_quantity);
            line_no = (AppCompatTextView) itemView.findViewById(R.id.ongoing_line_no);
            tvPart_no=(AppCompatTextView)itemView.findViewById(R.id.parts_no);
            tv_Customercodeno=(AppCompatTextView)itemView.findViewById(R.id.tv_customercodes_no);
//            circularProgressView=(CircularProgressView)itemView.findViewById(R.id.progressView);
            circularProgressIndicator = (CircularProgressIndicator) itemView.findViewById(R.id.circular_progress);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.iv_line);
        }

//        void bind(final Ongoing ongoing) {
//            if (checkedPosition == -1) {
//
//            } else {
//                if (checkedPosition == getAdapterPosition()) {
//
//                } else {
//
//                }
//            }
//            job_no.setText(""+ongoing_work.getLine_numbers().getJob_number());
//            quantity.setText(""+ongoing_work.getLine_numbers().getQuantity());
//            line_no.setText(""+ongoing_work.getLine_numbers().getLine_number());
//        }
    }

//    public interface AdapterInterface
//    {
//        void onClick(String value);
//    }
}
