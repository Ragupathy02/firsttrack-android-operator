package com.firstpage.user.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firstpage.user.Common.SharedPreference;
import com.firstpage.user.Model.Order_process;
import com.firstpage.user.R;
import com.firstpage.user.Ui.jobdetailstatustrackActivity;

import java.util.ArrayList;
public class jobStatusadapter extends RecyclerView.Adapter<jobStatusadapter.Viewholder> {
    Context context;
    ArrayList<Order_process> jobongoing;
    String process_name;
    SharedPreference sharedPreference = new SharedPreference();
    String is_exception;
    String id;
//    String process_id;
    public jobStatusadapter(Context context, ArrayList<Order_process> jobongoing)
    {
        this.context=context;
        this.jobongoing=jobongoing;
        this.is_exception=is_exception;
    }

    public jobStatusadapter(Context context, ArrayList<Order_process> jobongoing, String id) {
        this.context=context;
        this.jobongoing=jobongoing;
        this.id = id;


    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.job_details_adapter_items, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder viewholder, final int i) {
        Log.e("ragu", "onBindViewHolder:jobstatus adapter "+viewholder.getLayoutPosition());
//        Log.e("ragu", "onBindViewHolder: " );
         final Order_process ongoing = jobongoing.get(i);
         String line_id=id;
        Log.e("ragu", "onBindViewHolder:line id "+line_id );
//         String exception = is_exception;
//        Log.e("ragu", "onBindViewHolder:exception "+exception );

//         int jobby = sharedPreference.getInt(context,"job_finish");
//         int job_finish_process=sharedPreference.getInt(context,"job_finishprocess first");
//        Log.e("ragu", "onBindViewHolder:shared value "+jobby );
        Log.e("ragu", "onBindViewHolder: "+ongoing );
        Log.e("ragu", "onBindViewHolder:status "+ongoing.getStatus());
//        viewholder.linearCleaning.setVisibility(View.VISIBLE);
      viewholder.tvProcess_name.setText("" + ongoing.getProcess().getProcess_name());
//      viewholder.linearcomment.setVisibility(View.VISIBLE);
//      viewholder.linearcomment.setVisibility(View.VISIBLE);
//      viewholder.btnFinish.setActivated(false);
//      viewholder.editcomment.clearFocus();
        if(ongoing.getStatus()==4)
        {

            viewholder.iv_timeline.setImageResource(R.drawable.ic_packing);
            viewholder.tvProcess_name.setText(""+ongoing.getProcess().getProcess_name());
            viewholder.tvProcess_name.setTextColor(context.getResources().getColor(R.color.text_colour));
        }
        else if(ongoing.getStatus()==1)
        {
            viewholder.linearCleaning.setVisibility(View.VISIBLE);
            viewholder.tvProcess_name.setText(""+ongoing.getProcess().getProcess_name());
            viewholder.btnStart.setBackgroundResource(R.drawable.start_button);
            viewholder.btnStart.setText("Start");
            viewholder.btnFinish.setBackgroundResource(R.drawable.finish_button);
            viewholder.btnFinish.setText("Finish");

        }
        else if(ongoing.getStatus()==2)
        {
            viewholder.linearCleaning.setVisibility(View.VISIBLE);
            viewholder.tvProcess_name.setText(""+ongoing.getProcess().getProcess_name());
            viewholder.tvProcess_name.setTextColor(context.getResources().getColor(R.color.text_colour));
            viewholder.btnStart.setBackgroundResource(R.drawable.stop_button);
            viewholder.btnStart.setText("Stop");
            viewholder.btnFinish.setBackgroundResource(R.drawable.finish_button);
            viewholder.iv_timeline.setImageResource(R.drawable.half_circle);
            viewholder.btnFinish.setText("Finish");
            viewholder.btnFinish.setEnabled(true);
//            viewholder.linearcomment.setVisibility(View.VISIBLE);
//            viewholder.editcomment.requestFocus();

        }
        else if(ongoing.getStatus()==3)
        {
            viewholder.linearCleaning.setVisibility(View.VISIBLE);
            viewholder.tvProcess_name.setText(""+ongoing.getProcess().getProcess_name());
//            viewholder.tvProcess_clean.setTextColor(context.getResources().getColor(R.color.text_color));
            viewholder.btnStart.setBackgroundResource(R.drawable.start_button);
            viewholder.btnStart.setText("Start");
            viewholder.btnFinish.setBackgroundResource(R.drawable.finish_button);
            viewholder.iv_timeline.setImageResource(R.drawable.half_circle);
            viewholder.btnFinish.setText("Finish");
            viewholder.btnFinish.setEnabled(true);
//            viewholder.linearcomment.setVisibility(View.VISIBLE);
//            viewholder.editcomment.requestFocus();

        }



//        if(viewholder.getAdapterPosition()==0)
//        {
//            viewholder.linearCleaning.setVisibility(View.VISIBLE);
//            viewholder.tvProcess_clean.setText("" + ongoing.getProcess().getProcess_name());
//
//        }
//        else {
//            viewholder.tvProcess_clean.setText("" + ongoing.getProcess().getProcess_name());
//
//        }
//        if(jobby==0)
//        {
//            if(viewholder.getAdapterPosition()==0 )
//            {
//                viewholder.tvProcess_clean.setText("" + ongoing.getProcess().getProcess_name());
//               viewholder.linearCleaning.setVisibility(View.VISIBLE);
//
//
//            }
//            else {
//                viewholder.tvProcess_clean.setText("" + ongoing.getProcess().getProcess_name());
//            }
//        }
//        else if(jobby==1)
//        {
//            if(viewholder.getAdapterPosition()==0)
//            {
//                viewholder.linearCleaning.setVisibility(View.INVISIBLE);
//                viewholder.tvProcess_clean.setText("" + ongoing.getProcess().getProcess_name());
//                viewholder.tvProcess_clean.setTextColor(context.getResources().getColor(R.color.text_colour));
//                viewholder.iv_timeline.setImageResource(R.drawable.ic_cleaning);
//
//
//            }
//            else if(viewholder.getAdapterPosition()==1)
//            {
//                viewholder.linearCleaning.setVisibility(View.VISIBLE);
//                viewholder.tvProcess_clean.setText("" + ongoing.getProcess().getProcess_name());
//            }
//        }
//        if(job_finish_process==0)
//        {
//            if(viewholder.getAdapterPosition()==0)
//            {
//                viewholder.tvProcess_clean.setText("" + ongoing.getProcess().getProcess_name());
//                viewholder.linearCleaning.setVisibility(View.INVISIBLE);
//
//
//            }
//            else if(viewholder.getAdapterPosition()==1)
//            {
//                viewholder.linearCleaning.setVisibility(View.VISIBLE);
//
//            }
//            else {
//                viewholder.tvProcess_clean.setText("" + ongoing.getProcess().getProcess_name());
//            }
//        }
//        else if(job_finish_process==1)
//        {
//            if(viewholder.getAdapterPosition()==0)
//            {
//                viewholder.linearCleaning.setVisibility(View.INVISIBLE);
//                viewholder.tvProcess_clean.setText("" + ongoing.getProcess().getProcess_name());
//                viewholder.tvProcess_clean.setTextColor(context.getResources().getColor(R.color.text_colour));
//                viewholder.iv_timeline.setImageResource(R.drawable.ic_cleaning);
//
//
//            }
//            else if(viewholder.getAdapterPosition()==1)
//            {
//
//                viewholder.tvProcess_clean.setText("" + ongoing.getProcess().getProcess_name());
//                viewholder.linearCleaning.setVisibility(View.INVISIBLE);
//            }
//        }
//        if(viewholder.getAdapterPosition()==0) {
//            if(jobby==0)
//            {
//                viewholder.tvProcess_clean.setText("" + ongoing.getProcess().getProcess_name());
//                viewholder.linearCleaning.setVisibility(View.VISIBLE);
//
//            }
//            else if(jobby ==1)
//            {
//                if(viewholder.getAdapterPosition()==1)
//                {
//                    viewholder.tvProcess_clean.setText("" + ongoing.getProcess().getProcess_name());
//                    viewholder.linearCleaning.setVisibility(View.VISIBLE);
//
//                }
//            }
//
//        }
//        else {
//            viewholder.tvProcess_clean.setText("" + ongoing.getProcess().getProcess_name());
//            viewholder.linearCleaning.setVisibility(View.INVISIBLE);
//
//        }
//        Log.e("ragu", "onBindViewHolder: " );
//       for(int i1=0;i1<ongoing.getOrder_process().size();i1++)
//       {
////           Log.e("ragu", "onBindViewHolder:process_name "+ongoing.getOrder_process().get(i1).getProcess().getProcess_name());
//           String  process_name=ongoing.getOrder_process().get(i1).getProcess().getProcess_name();
//           Log.e("ragu", "onBindViewHolder:process_name "+process_name );
//           viewholder.tvProcess_clean.setText(ongoing.getOrder_process().get(i1).getProcess().getProcess_name());
//           viewholder.im_clean_timeline.setImageResource(R.drawable.clean_timeline);
//
//       }
//        Log.e("ragu", "onBindViewHolder: "+ongoing );
//        Log.e("ragu", "onBindViewHolder: "+ongoing.getOrder_process().get(i).getProcess().getProcess_name() );
//        String process_name=ongoing.getOrder_process().get(i).get;
//        Log.e("ragu", "onBindViewHolder:processname "+jobongoing.get(i).getOrder_process().get().getProcess().getProcess_name());



//        for(int i1=0;i1<ongoing.getOrder_process().size();i++)
//        {
//            Log.e("ragu", "onBindViewHolder:process_text "+ongoing.getOrder_process().get(i).getProcess().getProcess_name() );
        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"your position is:"+i,Toast.LENGTH_LONG).show();
            }
        });
//        }
        viewholder.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"your position is:"+viewholder.getAdapterPosition(),Toast.LENGTH_LONG).show();
                viewholder.btnFinish.setEnabled(true);
//                if(viewholder.getAdapterPosition()==1)
//                {
//                    viewholder.btnStart.setEnabled(false);
//                }
//                else if(viewholder.getAdapterPosition()==2)
//                {
//                    viewholder.btnStart.setEnabled(false);
//                }
//                if(viewholder.getAdapterPosition()==1)
//                {
//                    viewholder.btnStart.setEnabled(false);
//
//                }
//                else if(viewholder.getAdapterPosition()==2)
//                {
//                    viewholder.btnStart.setEnabled(false);
//                }
//                if(viewholder.getPosition()==1)
//                {
//                    viewholder.btnStart.setEnabled(false);
//                }
//                else if(viewholder.getAdapterPosition()==2)
//                {
//                    viewholder.btnStart.setEnabled(false);
//                }

//                Toast.makeText(v.getContext(),"you clicked:"+i,Toast.LENGTH_LONG).show();
//                if(viewholder.getAdapterPosition()==0)
//                {
//
//                }

                    if (viewholder.btnStart.getText().toString().equals("Start")) {

//                        viewholder.btnFinish.setEnabled(true);
//                     viewholder.btnFinish.setActivated(true);
//                        Toast.makeText(v.getContext(), "you clicked:" + i, Toast.LENGTH_LONG).show();
//                        viewholder.btnStart.setBackgroundResource(R.drawable.stop_button);
//                        viewholder.btnStart.setText("Stop");
//                        viewholder.tvProcess_clean.setTextColor(context.getResources().getColor(R.color.text_colour));
//                        viewholder.iv_timeline.setImageResource(R.drawable.ic_packing);
                        ((jobdetailstatustrackActivity) v.getContext()).getmethod("Start", ongoing.get_id(),id);
                        viewholder.btnStart.setBackgroundResource(R.drawable.stop_button);
                        viewholder.btnStart.setText("Stop");
                        viewholder.tvProcess_name.setTextColor(context.getResources().getColor(R.color.text_colour));
                        viewholder.iv_timeline.setImageResource(R.drawable.half_circle);

                    }
                    else if (viewholder.btnStart.getText().toString().equals("Stop")) {

//                        viewholder.btnFinish.setEnabled(true);
//                        viewholder.btnFinish.setActivated(true);
//                        Toast.makeText(v.getContext(), "you clicked:" + i, Toast.LENGTH_LONG).show();
//                        viewholder.btnStart.setBackgroundResource(R.drawable.start_button);
//                        viewholder.btnStart.setText("Start");
//                        viewholder.iv_timeline.setImageResource(R.drawable.clean_timeline);
//                        viewholder.tvProcess_clean.setTextColor(context.getResources().getColor(R.color.text_color));
                        ((jobdetailstatustrackActivity) v.getContext()).getstopmethod("Stop", ongoing.get_id(),id);

                        viewholder.btnStart.setBackgroundResource(R.drawable.start_button);
                        viewholder.btnStart.setText("Start");
                        viewholder.iv_timeline.setImageResource(R.drawable.half_circle);
                       viewholder.tvProcess_name.setTextColor(context.getResources().getColor(R.color.dummy_txt));

                        Log.e("ragu", "onClick: adapter position"+viewholder.getLayoutPosition() );

                    }

//                else if(viewholder.getAdapterPosition()==1)
//                {
//                    if (viewholder.btnStart.getText().toString().equals("Start")) {
////                        Toast.makeText(v.getContext(), "you clicked:" + i, Toast.LENGTH_LONG).show();
//                        viewholder.btnStart.setBackgroundResource(R.drawable.stop_button);
//                        viewholder.btnStart.setText("Stop");
//                        viewholder.tvProcess_clean.setTextColor(context.getResources().getColor(R.color.text_colour));
//                        viewholder.iv_timeline.setImageResource(R.drawable.ic_cutting);
//                        ((jobdetailstatustrackActivity) v.getContext()).getmethod("Start", ongoing.getProcess().get_id());
//
//
//                    } else if (viewholder.btnStart.getText().toString().equals("Stop")) {
////                        Toast.makeText(v.getContext(), "you clicked:" + i, Toast.LENGTH_LONG).show();
//                        viewholder.btnStart.setBackgroundResource(R.drawable.start_button);
//                        viewholder.btnStart.setText("Start");
//                        ((jobdetailstatustrackActivity) v.getContext()).getstopmethod("Stop", ongoing.getProcess().get_id());
//
//                    }
//
//
//
//
//
//                }

//                else if(i==1)
//                {
//                    if (viewholder.btnStart.getText().toString().equals("Start")) {
//                        Toast.makeText(v.getContext(), "you clicked:" + i, Toast.LENGTH_LONG).show();
//                        viewholder.btnStart.setBackgroundResource(R.drawable.stop_button);
//                        viewholder.btnStart.setText("Stop");
//                        viewholder.tvProcess_clean.setTextColor(context.getResources().getColor(R.color.text_colour));
//                        ((jobdetailstatustrackActivity) v.getContext()).getmethod("Start", ongoing.getProcess().get_id());
//
//
//                    } else if (viewholder.btnStart.getText().toString().equals("Stop")) {
//                        Toast.makeText(v.getContext(), "you clicked:" + i, Toast.LENGTH_LONG).show();
//                        viewholder.btnStart.setBackgroundResource(R.drawable.start_button);
//                        viewholder.btnStart.setText("Start");
//                        ((jobdetailstatustrackActivity) v.getContext()).getstopmethod("Stop", ongoing.getProcess().get_id());
//
//                    }
//
//                }
            }
        });
        viewholder.btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              viewholder.iv_timeline.setImageResource(R.drawable.ic_packing);

                Order_process order_process=jobongoing.get(jobongoing.size()-1);
//                ((jobdetailstatustrackActivity) v.getContext()).getfinishmethod("Finish", ongoing.get_id(),id);
                Log.e("ragu", "onClick:contain found " );
//                int lastposition = getItemCount()-1;
//                Log.e("ragu", "onClick:count "+lastposition );

//                Log.e("ragu", "onClick: order last"+order_process.getProcess().getProcess_name() );

//                Log.e("ragu", "onClick:adapter change "+viewholder.getPosition() );
//                Log.e("ragu", "onClick:process name "+ongoing.getProcess().getProcess_name() );

//                if(i == getItemCount()-1)
//                {
//                    Log.e("ragu", "onClick:job last"+order_process.getProcess().getProcess_name() );
//                    ((jobdetailstatustrackActivity) v.getContext()).getfinishmethod("Finish",ongoing.getProcess().get_id(),order_process.getProcess().getProcess_name());
//
//                }

//                    ((jobdetailstatustrackActivity) v.getContext()).getfinishmethod("Finish",ongoing.getProcess().get_id());



//                ((jobdetailstatustrackActivity) v.getContext()).getfinishmethod("Finish",ongoing.getProcess().get_id());
//                if(!viewholder.btnFinish.isActivated())
//                {
//                    viewholder.iv_timeline.setImageResource(R.drawable.clean_timeline);
//                    Toast.makeText(v.getContext(),"you should click start button first",Toast.LENGTH_LONG).show();
//
//                }
//                else {
////                    Toast.makeText(context,"button activated",Toast.LENGTH_LONG).show();
//                }


                    if (i == jobongoing.size() - 1 )
                    {
                        Log.e("ragu", "onClick:last position ");
                        ((jobdetailstatustrackActivity) v.getContext()).getfinishmethod("Finish", ongoing.get_id(), ongoing.getProcess().getProcess_name(),id);


                    }
                    else
                        {

                        ((jobdetailstatustrackActivity) v.getContext()).getfinishmethod("Finish", ongoing.get_id(),id);
                    }


                    Log.e("ragu", "onClick:adapter_position " + viewholder.getPosition());
                    if (viewholder.getPosition() == 0) {
                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);
                    } else if (viewholder.getPosition() == 1) {
                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);

                    } else if (viewholder.getPosition() == 2) {
                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);

                    } else if (viewholder.getPosition() == 3) {
                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);

                    } else if (viewholder.getPosition() == 4) {

                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);

                    } else if (viewholder.getPosition() == 5) {

                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);

                    } else if (viewholder.getPosition() == 6) {

                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);

                    } else if (viewholder.getPosition() == 7) {

                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);

                    } else if (viewholder.getPosition() == 8) {
                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);
                    } else if (viewholder.getPosition() == 9) {
                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);
                    } else if (viewholder.getPosition() == 10) {
                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);
                    } else if (viewholder.getPosition() == 11) {
                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);
                    } else if (viewholder.getPosition() == 12) {
                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);
                    } else if (viewholder.getPosition() == 13) {
                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);
                    }
                    else if (viewholder.getPosition() == 14) {
                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);
                    }
                    else if (viewholder.getPosition() == 15) {
                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);
                    }
                    else if (viewholder.getPosition() == 16) {
                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);
                    }
                    else if (viewholder.getPosition() == 17) {
                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);
                    }
                    else if (viewholder.getPosition() == 18) {
                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);
                    }
                    else if (viewholder.getPosition() == 19) {
                        viewholder.linearCleaning.setVisibility(View.INVISIBLE);
                    }



//                    sharedPreference.putInt(context,"job_finish",1);


//                 if(jobongoing.size() == 2)
//                 {
//                     Log.e("ragu", "onClick:size of viewholder " );
//
//
//
//
//                 }
//                    ((jobdetailstatustrackActivity) v.getContext()).getfinishmethod("Finish",ongoing.getProcess().get_id());



//                if(viewholder.getAdapterPosition()==1)
//                {
//                    sharedPreference.putInt(context,"job_finishprocess first",1);
//
//                }



//                 if(jobongoing.size() == 2)
//                 {
//                     Log.e("ragu", "onClick:size of viewholder " );
//
//
//
//
//                 }
//                ((jobdetailstatustrackActivity) v.getContext()).getfinishmethod("Finish",ongoing.getProcess().get_id());





//                Toast.makeText(v.getContext(),"you clicked:"+i,Toast.LENGTH_LONG).show();

//                ((jobdetailstatustrackActivity) v.getContext()).getfinishmethod("Finish",ongoing.getProcess().get_id());



            }
        });
//        viewholder.editcomment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//               ((jobdetailstatustrackActivity) view.getContext()).getopen("Start");
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        if(jobongoing.size()==0)
        {
//            Log.e("ragu", "empty data: " );
            Toast.makeText(context,"empty data",Toast.LENGTH_LONG).show();
        }
//        Log.e("ragu", "getItemCount:jobstatus "+jobongoing.size());
        return jobongoing.size();
    }
    public void notifyDataChanged() {
        notifyDataSetChanged();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public ImageView im_clean_timeline;
        public AppCompatTextView tvProcess_name;
        public AppCompatButton btnStart;
        public AppCompatButton btnFinish;
        public LinearLayout linearCleaning;
        public ImageView iv_timeline;
        public LinearLayout linearcomment;
        public AppCompatTextView editcomment;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            im_clean_timeline=(ImageView)itemView.findViewById(R.id.im_clean_timeline);
            tvProcess_name=(AppCompatTextView)itemView.findViewById(R.id.tv_process_clean);
            btnStart=(AppCompatButton)itemView.findViewById(R.id.btn_start);
            btnFinish=(AppCompatButton)itemView.findViewById(R.id.btn_finish);
            linearCleaning=(LinearLayout)itemView.findViewById(R.id.linear_cleaning);
            linearcomment=(LinearLayout)itemView.findViewById(R.id.layout_comment);
            iv_timeline=(ImageView)itemView.findViewById(R.id.im_clean_timeline);
            editcomment=(AppCompatTextView) itemView.findViewById(R.id.et_comment);
//            editcomment.requestFocus();
//            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.showSoftInput(editcomment, InputMethodManager.SHOW_IMPLICIT);

        }
    }
}
