package com.firstpage.user.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.firstpage.user.Fragment.Process_fragment;
import com.firstpage.user.Model.Order_process;
import com.firstpage.user.Model.Processdetail;
import com.firstpage.user.R;

import java.util.ArrayList;

public class Processdetailsadapter extends RecyclerView.Adapter<Processdetailsadapter.Viewholder> {
    Context context;
    ArrayList<Order_process> order_processes;
    String id;
    Process_fragment process_fragment;
    ArrayList<Processdetail> processdetailArrayList;

//    public Processdetailsadapter(Context context, ArrayList<Order_process> order_processes) {
//        this.context = context;
//        this.order_processes = order_processes;
//    }

    public Processdetailsadapter(Context context, ArrayList<Order_process> order_processes, String id) {
        this.context=context;
        this.order_processes=order_processes;
        this.id = id;
    }

    public Processdetailsadapter(Context context, ArrayList<Order_process> order_processes, String id, Process_fragment process_fragment) {
        this.context=context;
        this.order_processes=order_processes;
        this.id=id;
        this.process_fragment = process_fragment;
    }

    public Processdetailsadapter(Context context, ArrayList<Processdetail> processdetailArrayList) {
        this.context=context;
        this.processdetailArrayList = processdetailArrayList;

    }


    @NonNull
    @Override
    public Processdetailsadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.process_recyleadapter_item, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Processdetailsadapter.Viewholder viewholder, final int i) {
        final Order_process order_process = order_processes.get(i);
        String line_id=id;
        viewholder.tvProcess_clean.setText(""+order_process.getProcess().getProcess_name());
        if(order_process.getStatus()==4)
        {
            viewholder.iv_timeline.setImageResource(R.drawable.ic_packing);
            viewholder.tvProcess_clean.setText(""+order_process.getProcess().getProcess_name());
            viewholder.tvProcess_clean.setTextColor(context.getResources().getColor(R.color.text_colour));

        }
        else if(order_process.getStatus()==1)
        {
            viewholder.linearCleaning.setVisibility(View.VISIBLE);
            viewholder.tvProcess_clean.setText(""+order_process.getProcess().getProcess_name());
            viewholder.btnStart.setBackgroundResource(R.drawable.start_button);
            viewholder.btnStart.setText("Start");
            viewholder.btnFinish.setBackgroundResource(R.drawable.finish_button);
            viewholder.btnFinish.setText("Finish");

        }
        else if(order_process.getStatus()==2)
        {
            viewholder.linearCleaning.setVisibility(View.VISIBLE);
            viewholder.tvProcess_clean.setText(""+order_process.getProcess().getProcess_name());
            viewholder.tvProcess_clean.setTextColor(context.getResources().getColor(R.color.text_colour));
            viewholder.btnStart.setBackgroundResource(R.drawable.stop_button);
            viewholder.btnStart.setText("Stop");
            viewholder.btnFinish.setBackgroundResource(R.drawable.finish_button);
            viewholder.iv_timeline.setImageResource(R.drawable.half_circle);
            viewholder.btnFinish.setText("Finish");
            viewholder.btnFinish.setEnabled(true);

        }
        else if(order_process.getStatus()==3)
        {
            viewholder.linearCleaning.setVisibility(View.VISIBLE);
            viewholder.tvProcess_clean.setText(""+order_process.getProcess().getProcess_name());
//            viewholder.tvProcess_clean.setTextColor(context.getResources().getColor(R.color.text_color));
            viewholder.btnStart.setBackgroundResource(R.drawable.start_button);
            viewholder.btnStart.setText("Start");
            viewholder.btnFinish.setBackgroundResource(R.drawable.finish_button);
            viewholder.iv_timeline.setImageResource(R.drawable.half_circle);
            viewholder.btnFinish.setText("Finish");
            viewholder.btnFinish.setEnabled(true);

        }
        viewholder.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewholder.btnFinish.setEnabled(true);
                if (viewholder.btnStart.getText().toString().equals("Start")) {
                    viewholder.btnFinish.setEnabled(true);
//                     viewholder.btnFinish.setActivated(true);
//                        Toast.makeText(v.getContext(), "you clicked:" + i, Toast.LENGTH_LONG).show();
//                        viewholder.btnStart.setBackgroundResource(R.drawable.stop_button);
//                        viewholder.btnStart.setText("Stop");
//                        viewholder.tvProcess_clean.setTextColor(context.getResources().getColor(R.color.text_colour));
//                        viewholder.iv_timeline.setImageResource(R.drawable.ic_packing);
//                    ((Process_fragment) view.getContext()).getmethod("Start", ongoing.get_id(),id);
                    process_fragment.getmethod("Start",order_process.get_id(),id);
                    viewholder.btnStart.setBackgroundResource(R.drawable.stop_button);
                    viewholder.btnStart.setText("Stop");
                    viewholder.tvProcess_clean.setTextColor(context.getResources().getColor(R.color.text_colour));
                    viewholder.iv_timeline.setImageResource(R.drawable.half_circle);

                }
                else if (viewholder.btnStart.getText().toString().equals("Stop")) {
//                        viewholder.btnFinish.setActivated(true);
//                        Toast.makeText(v.getContext(), "you clicked:" + i, Toast.LENGTH_LONG).show();
//                        viewholder.btnStart.setBackgroundResource(R.drawable.start_button);
//                        viewholder.btnStart.setText("Start");
//                        viewholder.iv_timeline.setImageResource(R.drawable.clean_timeline);
//                        viewholder.tvProcess_clean.setTextColor(context.getResources().getColor(R.color.text_color));
                    process_fragment.getstopmethod("Stop", order_process.get_id(),id);

                    viewholder.btnStart.setBackgroundResource(R.drawable.start_button);
                    viewholder.btnStart.setText("Start");
                    viewholder.iv_timeline.setImageResource(R.drawable.half_circle);
                    viewholder.tvProcess_clean.setTextColor(context.getResources().getColor(R.color.dummy_txt));

                    Log.e("ragu", "onClick: adapter position"+viewholder.getLayoutPosition() );

                }



            }
        });
        viewholder.btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                viewholder.iv_timeline.setImageResource(R.drawable.ic_packing);

                Order_process order_process=order_processes.get(order_processes.size()-1);
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


//                if (i == order_processes.size() - 1) {
//
//                    Log.e("ragu", "onClick:last position ");
//                    process_fragment.getfinishmethod("Finish", order_process.get_id(), order_process.getProcess().getProcess_name(),id);
//
//
//                }


                    process_fragment.getfinishmethod("Finish", order_process.get_id(),id);



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


    }

    @Override
    public int getItemCount() {
        return order_processes.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public AppCompatTextView tvProcess_clean;
        public ImageView im_clean_timeline;
        public AppCompatButton btnStart;
        public AppCompatButton btnFinish;
        public LinearLayout linearCleaning;
        public ImageView iv_timeline;
        public LinearLayout linearcomment;
        public AppCompatTextView editcomment;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvProcess_clean=(AppCompatTextView)itemView.findViewById(R.id.tv_process_clean);
            im_clean_timeline=(ImageView)itemView.findViewById(R.id.im_clean_timeline);
            btnStart=(AppCompatButton)itemView.findViewById(R.id.btn_start);
            btnFinish=(AppCompatButton)itemView.findViewById(R.id.btn_finish);
            linearCleaning=(LinearLayout)itemView.findViewById(R.id.linear_cleaning);
            linearcomment=(LinearLayout)itemView.findViewById(R.id.layout_comment);
            iv_timeline=(ImageView)itemView.findViewById(R.id.im_clean_timeline);
            editcomment=(AppCompatTextView) itemView.findViewById(R.id.et_comment);


        }
    }
}
