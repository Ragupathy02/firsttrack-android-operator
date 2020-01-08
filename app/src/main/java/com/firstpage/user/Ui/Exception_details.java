package com.firstpage.user.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.firstpage.user.Common.SharedPreference;
import com.firstpage.user.R;

public class Exception_details extends AppCompatActivity {
    public AppCompatTextView tv_customer_name;
    public AppCompatTextView tv_purchase_order;
    public AppCompatTextView tv_line_no;
    public AppCompatTextView tv_quantity;
    public AppCompatTextView tv_job_id;
    SharedPreference sharedPreference;
    public  AppCompatTextView tv_exceptionprocess;
    public AppCompatImageView iv_back;
    String jobnos;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exception_dtails);
        sharedPreference = new SharedPreference();
        tv_customer_name=(AppCompatTextView)findViewById(R.id.tv_customer_name);
        tv_purchase_order=(AppCompatTextView)findViewById(R.id.tv_purchase_order);
        tv_line_no=(AppCompatTextView)findViewById(R.id.tv_line_no);
        tv_quantity=(AppCompatTextView)findViewById(R.id.tv_quantity);
        tv_job_id=(AppCompatTextView)findViewById(R.id.tv_job_id);
        tv_exceptionprocess=(AppCompatTextView)findViewById(R.id.tv_exceptionprocess);
        iv_back=(AppCompatImageView)findViewById(R.id.iv_back);
        String name = sharedPreference.getString(Exception_details.this,"name");
        tv_customer_name.setText(""+name);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        if (b != null) {
            jobnos = b.getString("job_no");
        }
        tv_job_id.setText(""+jobnos);
        Log.e("ragu", "onCreate:details "+jobnos );

        String lineno = b.getString("lno");
        tv_line_no.setText(""+lineno);

        String quantity = b.getString("qty");
        tv_quantity.setText(""+quantity);

        String purchase_order_no = b.getString("ponumber");
        tv_purchase_order.setText(""+purchase_order_no);
        String processorder= b.getString("process");
        tv_exceptionprocess.setText(""+processorder);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(Exception_details.this,ExceptionActivity.class));
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();

            }
        });
    }


}
