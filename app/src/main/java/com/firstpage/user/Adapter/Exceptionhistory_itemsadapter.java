package com.firstpage.user.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firstpage.user.Model.Exception_history;
import com.firstpage.user.Model.Ongoing;
import com.firstpage.user.Model.Order_process;
import com.firstpage.user.Model.Purchase_order;
import com.firstpage.user.R;
import com.firstpage.user.Ui.Exception_details;
import com.firstpage.user.Ui.jobdetailstatustrackActivity;

import java.util.ArrayList;

public class Exceptionhistory_itemsadapter extends RecyclerView.Adapter<Exceptionhistory_itemsadapter.Viewholder> {
    Context context;
   ArrayList<Exception_history> exception_historyArrayList;
    ArrayList<Order_process> orderProcesses;
    ArrayList<Ongoing> ongoingArrayList;
    public String id;
   public String job_number;
    String Jobno;
    String line_number;
    String quantity;
    String purchase_order;
    public Exceptionhistory_itemsadapter(Context context, ArrayList<Exception_history> exception_historyArrayList)
    {
        this.context=context;
        this.exception_historyArrayList=exception_historyArrayList;
    }

    public Exceptionhistory_itemsadapter(Context context, ArrayList<Order_process> orderProcesses, String id) {
        this.context=context;
        this.orderProcesses=orderProcesses;
    }

    public Exceptionhistory_itemsadapter(Context context, ArrayList<Order_process> orderProcesses, String id, ArrayList<Ongoing> ongoingArrayList) {
        this.context=context;
        this.orderProcesses=orderProcesses;
        this.id = id;
        this.ongoingArrayList = ongoingArrayList;

    }

    public Exceptionhistory_itemsadapter(Context context, ArrayList<Order_process> orderProcesses, String id, String job_number) {
        this.context=context;
        this.orderProcesses = orderProcesses;
        this.id = id;
        this.job_number = job_number;
    }

    public Exceptionhistory_itemsadapter(Context context, ArrayList<Order_process> orderProcesses, String id, String job_number, String line_number, String quantity, String purchase_order) {
        this.context=context;
        this.orderProcesses = orderProcesses;
        this.id = id;
        this.job_number= job_number;
        this.line_number=line_number;
        this.quantity=quantity;
        this.purchase_order = purchase_order;
    }

    @NonNull
    @Override
    public Exceptionhistory_itemsadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.exceptionhistory_adapteritem, viewGroup, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Exceptionhistory_itemsadapter.Viewholder viewholder, int i) {
        final Order_process order_process= orderProcesses.get(i);
//        Jobno = job_number;
        Log.e("ragu", "onBindViewHolder:jobno "+Jobno );

        viewholder.tv_exceptiontext_process.setText(""+order_process.getProcess().getProcess_name());
        viewholder.tv_linearexception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Exception_details.class);
//                i.putExtra("jobno", ongoingArrayList.get(i).getLine_numbers().getJob_number()));
                i.putExtra("job_no",job_number).putExtra("ponumber",purchase_order).putExtra("lno",line_number)
                .putExtra("qty",quantity).putExtra("process",order_process.getProcess().getProcess_name());
                Log.e("ragu", "checkconfirm: ");
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("keyarray", ongoing);
//                i.putExtras(bundle);
                view.getContext().startActivity(i);


            }
        });


    }

    @Override
    public int getItemCount() {
        return orderProcesses.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public AppCompatTextView tv_exceptiontext_process;
        public LinearLayout tv_linearexception;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_exceptiontext_process=(AppCompatTextView)itemView.findViewById(R.id.tv_exceptionprocess);
            tv_linearexception=(LinearLayout)itemView.findViewById(R.id.tv_exception);

        }
    }
}
