package asus.com.bwie.day1227lx;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import asus.com.bwie.day1227lx.adapter.TabAdapter;

public class TwoActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        viewPager = findViewById(R.id.vp);
        tabLayout = findViewById(R.id.tablayout);
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        Intent intent=getIntent();
        String pid = intent.getStringExtra("pid");

    }
    public void showPage(int position){
        viewPager.setCurrentItem(position);
    }
}
