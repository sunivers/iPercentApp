
package com.example.soyoung.myapplication;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;

import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TabHost tabHost;
    private Toolbar toolbar;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    //공유되는 데이터
    private float m_Density, m_FlowRate, m_WaterAmount, m_AllofWater, m_iPercent = 0.0f;
    private float m_fpressure, m_fTs, m_fPs_down, m_fVm;

    public void setDensity(float value){
        m_Density = value;
    }
    public float getDensity(){
        return m_Density;
    }
    public void setFlowRate(float value){ m_FlowRate = value; }
    public float getFlowRate(){ return m_FlowRate; }
    public void setWaterAmount(float value){
        m_WaterAmount = value;
    }
    public float getWaterAmount(){
        return m_WaterAmount;
    }
    public void setAllofWater(float value){
        m_AllofWater = value;
    }
    public float getAllofWater(){
        return m_AllofWater;
    }
    public void setiPercent(float value){
        m_iPercent = value;
    }
    public float getiPercent(){
        return m_iPercent;
    }

    public void setPressure(float value){ m_fpressure = value; }
    public float getPressure(){ return m_fpressure; }
    public void setTs(float value){
        m_fTs = value;
    }
    public float getTs(){
        return m_fTs;
    }
    public void setPs_down(float value){
        m_fPs_down = value;
    }
    public float getPs_down(){
        return m_fPs_down;
    }
    public void setVm(float value){
        m_fVm = value;
    }
    public float getVm(){
        return m_fVm;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //툴바
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //탭 구현
        tabHost = (TabHost)findViewById(R.id.tabhost) ;
        tabHost.setup();
        tabHost.getTabWidget().setGravity(Gravity.BOTTOM);
        Resources res = getResources();
        Drawable icon;
        icon = getResources().getDrawable(R.drawable.btn_density_1);
        TabHost.TabSpec spec1 = tabHost.newTabSpec("density").setContent(R.id.tab1);
        spec1.setIndicator("밀도", icon);
        tabHost.addTab(spec1);
        icon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_flowrate_off);
        TabHost.TabSpec spec2 = tabHost.newTabSpec("flowrate").setContent(R.id.tab2);
        spec2.setIndicator("유속", icon);
        tabHost.addTab(spec2);
        icon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_wateramount);
        TabHost.TabSpec spec3 = tabHost.newTabSpec("wateramount").setContent(R.id.tab3);
        spec3.setIndicator("수분", icon);
        tabHost.addTab(spec3);
        icon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_ipercent);
        TabHost.TabSpec spec4 = tabHost.newTabSpec("ipercent").setContent(R.id.tab4);
        spec4.setIndicator("i퍼센트", icon);
        tabHost.addTab(spec4);

        for(int i=0; i<tabHost.getTabWidget().getChildCount(); i++){
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#dae6da"));
        }

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                mViewPager.setCurrentItem(tabHost.getCurrentTab());
                toolbar.setTitle(mSectionsPagerAdapter.getPageTitle(tabHost.getCurrentTab()));
                //toolbar.setNavigationIcon(R.mipmap.ic_launcher_ryan);
                for(int i=0; i<tabHost.getTabWidget().getChildCount(); i++){
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#dae6da"));
                }
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorActiveGreen));
            }
        });

        //뷰페이저 구현
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabHost.setCurrentTab(position);
                toolbar.setTitle(mSectionsPagerAdapter.getPageTitle(position));
                for(int i=0; i<tabHost.getTabWidget().getChildCount(); i++){
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#dae6da"));
                }
                tabHost.getTabWidget().getChildAt(position).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorActiveGreen));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //페이지 0으로 초기화
        tabHost.setCurrentTab(0);
        getSupportActionBar().setTitle(mSectionsPagerAdapter.getPageTitle(0));
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_ryan);
        mViewPager.setCurrentItem(0);
        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorActiveGreen));


        /*Button.OnClickListener onClickListener = new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_Density:
                        m_fDensity = getDensity();
                        tv_density.setText(String.format("밀도 : %.2f", m_fDensity));
                        break;
                    case R.id.btn_FlowRate:
                        m_fFlowRate = getFlowRate();
                        tv_density.setText(String.format("유속 : %.2f", m_fFlowRate));
                        break;
                    case R.id.btn_WaterAmount:
                        m_fWaterAmount = getAllofWater();
                        tv_density.setText(String.format("수분량 : %.2f", m_fWaterAmount));
                        break;
                    case R.id.btn_iPercent:
                        m_fiPercent = getIpercent();
                        tv_density.setText(String.format("i 퍼센트 : %.2f", m_fiPercent));
                        break;
                }
            }
        };*/


/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }
}
