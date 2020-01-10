package com.firstpage.user.Ui;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.firstpage.user.Adapter.TabAdapter;
import com.firstpage.user.Fragment.FragmentA;
import com.firstpage.user.Fragment.FragmentB;
import com.firstpage.user.R;


public class Exceptiondetails2 extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    private TabAdapter adapter;
    FragmentA fragmentA;
    FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new TabAdapter(getSupportFragmentManager());
        fragmentA = new FragmentA(Exceptiondetails2.this);
        fragmentB = new FragmentB(Exceptiondetails2.this);

        adapter.addFragment(fragmentA,"Process Details");
        adapter.addFragment(fragmentB,"exception details");


//        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
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




}
