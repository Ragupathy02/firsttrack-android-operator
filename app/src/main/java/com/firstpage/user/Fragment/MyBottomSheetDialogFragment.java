package com.firstpage.user.Fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firstpage.user.R;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {

    String mString;
    public static MyBottomSheetDialogFragment newInstance(String string) {
        MyBottomSheetDialogFragment f = new MyBottomSheetDialogFragment();
        Bundle args = new Bundle();
        args.putString("string", string);
        f.setArguments(args);
        return f;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mString = getArguments().getString("string");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cust_layout, container, false);
        final TextView textView=(TextView)v.findViewById(R.id.tv_ok);
        textView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                getActivity().finishAffinity();
            }
        });
        return v;
    }

}
