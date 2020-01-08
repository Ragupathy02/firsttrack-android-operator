package com.firstpage.user.Ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
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
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.firstpage.user.Adapter.TabAdapter;
import com.firstpage.user.Common.NetworkChangeReceiver;
import com.firstpage.user.Common.SharedPreference;
import com.firstpage.user.Fragment.Exception_historyfragment;
import com.firstpage.user.Fragment.Process_fragment;
import com.firstpage.user.Model.Ongoing;
import com.firstpage.user.R;

import java.util.ArrayList;

public class ExceptionActivity extends AppCompatActivity {
    TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter adapter;
    Process_fragment process_fragment;
    AppCompatImageView iv_notification;
    public AppCompatImageView iv_Arrow;
    public AppCompatTextView tv_jobno;
    public AppCompatTextView tv_name;
    public AppCompatTextView tv_purchaseorderno;
    public AppCompatTextView tv_Lineno;
    public AppCompatTextView tv_qty;
    private BroadcastReceiver mNetworkReceiver;
    SharedPreference sharedPreference;
    public static AlertDialog alertDialog;
    static TextView tv_check_connection;
    public  static  android.support.v7.app.AlertDialog exception_dialog;
    public static Button Btnok;
    Ongoing ongoing;
    public FrameLayout frameLayout;
    ArrayList<String> processdetail = new ArrayList<String>();
    Exception_historyfragment exception_historyfragment = new Exception_historyfragment();
//    public AppCompatButton btn_exception;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exceptionfragment);
        tabLayout=(TabLayout)findViewById(R.id.simpleTabLayout);
        tv_check_connection=(TextView) findViewById(R.id.tv_check_connection);
        iv_Arrow=(AppCompatImageView)findViewById(R.id.iv_arrow);
        frameLayout=(FrameLayout)findViewById(R.id.frame_container);
        sharedPreference = new SharedPreference();
        ongoing = (Ongoing) getIntent().getSerializableExtra("keyarrays");

        for(int i=0;i<ongoing.getOrder_process().size();i++)
        {
            processdetail.add(ongoing.getOrder_process().get(i).getProcess().getProcess_name());
            Log.e("ragu", "onCreate:processdetails "+processdetail );

        }


//        Bundle bundle = new Bundle();
//        String data ="haihello";
//        bundle.putStringArrayList("dummy",processdetail);
//         process_fragment = new Process_fragment();
//        process_fragment.setArguments(bundle);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_container,process_fragment);
//        fragmentTransaction.commit();

//        Bundle bundle1 = new Bundle();
//        bundle1.putString("hai",data);
//        FragmentManager fragmentManager1 = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
//        fragmentTransaction1.replace(R.id.frame_container,exception_historyfragment);
//        fragmentTransaction1.commit();

//        FragmentManager fm = getSupportFragmentManager();
//// create a FragmentTransaction to begin the transaction and replace the Fragment
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_container,process_fragment);
//        fragmentTransaction.commit();

//        getFragmentManager().beginTransaction().add(R.id.frame_container, process_fragment).commit();






        String cname = sharedPreference.getString(getApplicationContext(),"name");
        iv_Arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExceptionActivity.this, Workboardsupervisor_Activity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();

            }
        });
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tv_jobno=(AppCompatTextView)findViewById(R.id.tv_job_id);
        tv_name=(AppCompatTextView)findViewById(R.id.tv_customer_name);
        tv_purchaseorderno=(AppCompatTextView)findViewById(R.id.tv_purchase_order);
        tv_Lineno=(AppCompatTextView)findViewById(R.id.tv_line_no);
        tv_qty=(AppCompatTextView)findViewById(R.id.tv_quantity);



//        String job_no= null;
//        if (bundle != null) {
//            job_no = bundle.getString("jobno");
//        }

//        String porder=bundle.getString("purchase_order");
//        String lno= bundle.getString("Line_number");
//        String qty= bundle.getString("quantity");
        tv_jobno.setText(""+ongoing.getLine_numbers().getJob_number());
        tv_purchaseorderno.setText(""+ongoing.getPurchase_order().getPurchase_order_number());
        tv_Lineno.setText(""+ongoing.getLine_numbers().getLine_number());
        tv_qty.setText(""+ongoing.getLine_numbers().getQuantity());
        tv_name.setText(""+cname);
        mNetworkReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastForNougat();

//        btn_exception = (AppCompatButton) findViewById(R.id.btn_exception);
        adapter = new TabAdapter(getSupportFragmentManager());
        process_fragment = new Process_fragment(ExceptionActivity.this,processdetail,ongoing);
        adapter.addFragment(process_fragment,"Process Details");
//        Bundle bundle = new Bundle();
//        String data ="haihello";
//        bundle.putStringArrayList("dummy",processdetail);
//        process_fragment = new Process_fragment();
//        process_fragment.setArguments(bundle);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_container,process_fragment);
//        fragmentTransaction.commit();
        adapter.addFragment(new Exception_historyfragment(), "Exception History");

        iv_notification=(AppCompatImageView)findViewById(R.id.im_notification);
        LinearLayout linearLayout = (LinearLayout)tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(getResources().getColor(R.color.tab_colour));
        drawable.setSize(1, 1);
        linearLayout.setDividerPadding(40);
        linearLayout.setDividerDrawable(drawable);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


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
    public static void dialog(boolean value, final Context context )
    {

        if(value){
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
        }else {
            LayoutInflater factory = LayoutInflater.from(context);
            final View Exceptiondialogview = factory.inflate(R.layout.internet_check, null);
            exception_dialog = new AlertDialog.Builder(context).create();
//            exception_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;


//            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            exception_dialog.setView(Exceptiondialogview);
            Btnok=(AppCompatButton) Exceptiondialogview.findViewById(R.id.btn_internet);
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            startActivity(new Intent(getApplicationContext(), Workboardsupervisor_Activity.class));
        }
        else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);

        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }

}
