package com.firstpage.user.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firstpage.user.Common.CommonFunctions;
import com.firstpage.user.Common.SessionManager;
import com.firstpage.user.Common.SharedPreference;
import com.firstpage.user.Interfaces.APIClient;
import com.firstpage.user.Interfaces.ApiInterface;
import com.firstpage.user.Model.Conform_order;
import com.firstpage.user.Model.Conform_order_response;
import com.firstpage.user.Model.Loginresponse;
import com.firstpage.user.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class JobdetailsActivity extends AppCompatActivity {
    public AppCompatTextView  tv_job_id;
    public AppCompatTextView tv_customer_name;
    public AppCompatTextView tv_purchase_order;
    public AppCompatTextView tv_line_no;
    public AppCompatTextView tv_quantity;
    public AppCompatButton btn_yes;
    public  AppCompatButton btn_no;
    public AppCompatImageView iv_back;
    String id;
    int status;
    String orderno;
    String lineno;
    String quantity;
    String jobno;
    SharedPreference sps = new SharedPreference();
    public LinearLayout linear_job;
    public LinearLayout linear_cofirm;
    SessionManager session;
    public SharedPreference sharedPreference;
    public LinearLayout job_linear_layout;
    String name;
    int position_job;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityjob_details);
        tv_job_id=(AppCompatTextView)findViewById(R.id.tv_job_id);
        tv_customer_name=(AppCompatTextView)findViewById(R.id.tv_customer_name);
        tv_purchase_order=(AppCompatTextView)findViewById(R.id.tv_purchase_order);
        tv_line_no=(AppCompatTextView)findViewById(R.id.tv_line_no);
        tv_quantity=(AppCompatTextView)findViewById(R.id.tv_quantity);
        btn_yes=(AppCompatButton)findViewById(R.id.btn_yes);
        btn_no=(AppCompatButton)findViewById(R.id.btn_no);
        iv_back=(AppCompatImageView)findViewById(R.id.iv_back);
        linear_job=(LinearLayout)findViewById(R.id.job_linear);
        linear_cofirm=(LinearLayout)findViewById(R.id.linear_confirm);
        job_linear_layout=(LinearLayout)findViewById(R.id.job_detail_relative);
        sharedPreference = new SharedPreference();
        session = new SessionManager(getApplicationContext());
         position_job=sharedPreference.getInt(getApplicationContext(),"position_clicked");
        Log.e("ragu", "onCreate:position job "+position_job );
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
         id=bundle.getString("id");
         status=bundle.getInt("status");
         orderno=bundle.getString("purchase_order");
         tv_purchase_order.setText(""+orderno);
         lineno=bundle.getString("lineno");
         tv_line_no.setText(""+lineno);
         quantity=bundle.getString("quantity");
         tv_quantity.setText(""+quantity);
         jobno=bundle.getString("jobno");
         tv_job_id.setText(""+jobno);
         String name= sps.getString(getApplicationContext(),"name");
         tv_customer_name.setText(""+name);
         session = new SessionManager(getApplicationContext());

        Log.e("ragu", "onCreate: order "+orderno );
        Log.e("ragu", "onCreate: line "+lineno );

        Log.e("ragu", "onCreate: id "+id );
        Log.e("ragu", "onCreate: status "+status );


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),WorkboardActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        btn_yes.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conformorder();


            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonFunctions.shortToast(getApplicationContext(),"You are not Conformed the order ");
            }
        });

    }

    private void conformorder() {
        CommonFunctions.showProgressDialog(this," Confirmation status",false);
        Conform_order conformOrder = new Conform_order();
        conformOrder.set_id(id);
        conformOrder.setStatus(status);
        Log.e("ragu", "conformorder: "+conformOrder);
        ApiInterface service = APIClient.getClient().create(ApiInterface.class);
        Call<Conform_order_response> conform_order_responseCall=service.orderconform(conformOrder);
        conform_order_responseCall.enqueue(new Callback<Conform_order_response>() {
            @Override
            public void onResponse(Call<Conform_order_response> call, Response<Conform_order_response> response) {
                CommonFunctions.removeDialog();
                if(response.body().isStatus())
                {
                    Log.e("ragu", "onResponse: "+response.body() );
//                    session.createconformSession(response.body().isStatus());

                    Log.e("ragu", "onResponse: "+response.body().isStatus() );


//                   linear_job.setVisibility(View.INVISIBLE);
//                   linear_cofirm.setVisibility(View.INVISIBLE);
                }
                else {
                    Log.e("ragu", "onResponse: false " );
                }
                startActivity(new Intent(getApplicationContext(),JobdetailstatusActivity.class));
                sharedPreference.putInt(getApplicationContext(),"job_confirm",1);
                sharedPreference.putInt(getApplicationContext(),"job_confirm_position",position_job);
//                int a=sharedPreference.getInt(getApplicationContext(),"job_confirm_position");
//                Log.e("ragu", "onResponse:position "+a );

                  Log.e("ragu", "onResponse: "+session );
            }

            @Override
            public void onFailure(Call<Conform_order_response> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"Check Your Network Connection!",Toast.LENGTH_SHORT).show();
                CommonFunctions.removeProgressDialog();
                Snackbar snackbar = Snackbar.make(job_linear_layout,"Check Your Network Connection!",Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                snackbar.show();
//                Snackbar.make(getWindow().getDecorView().getRootView(), "No internet conmnection", Snackbar.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
