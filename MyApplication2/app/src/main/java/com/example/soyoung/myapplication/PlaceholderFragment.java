package com.example.soyoung.myapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by soyoung on 2017-02-02.
 */

public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    MainActivity mainActivity;

    EditText edit_pressure, edit_Ps_down, edit_Ts;
    EditText edit_Cp, edit_Ps_up;
    EditText edit_Ma1, edit_Ma2, edit_Ta, edit_Vm;
    EditText edit_Tm, edit_delta_H2, edit_time, edit_nozle;

    TextView tv_density, tv_flowrate, tv_wateramount, tv_allofwater, tv_ipercent;

    View view_density;
    View view_flowrate;
    View view_wateramount;
    View view_ipercent;


    public PlaceholderFragment() {
    }

    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainActivity = (MainActivity)getContext();

        view_density  = inflater.inflate(R.layout.activity_density, container, false);
        view_flowrate  = inflater.inflate(R.layout.activity_flowrate, container, false);
        view_wateramount  = inflater.inflate(R.layout.activity_wateramount, container, false);
        view_ipercent  = inflater.inflate(R.layout.activity_ipercent, container, false);

        edit_pressure = (EditText) view_density.findViewById(R.id.edit_pressure);
        edit_Ps_down = (EditText) view_density.findViewById(R.id.edit_Ps_down);
        edit_Ts = (EditText) view_density.findViewById(R.id.edit_Ts);
        edit_Cp = (EditText) view_flowrate.findViewById(R.id.edit_Cp);
        edit_Ps_up = (EditText) view_flowrate.findViewById(R.id.edit_Ps_up);
        edit_Ma1 = (EditText) view_wateramount.findViewById(R.id.edit_Ma1);
        edit_Ma2 = (EditText) view_wateramount.findViewById(R.id.edit_Ma2);
        edit_Ta = (EditText) view_wateramount.findViewById(R.id.edit_Ta);
        edit_Vm = (EditText) view_wateramount.findViewById(R.id.edit_Vm);
        edit_Tm = (EditText) view_ipercent.findViewById(R.id.edit_Tm);
        edit_delta_H2 = (EditText) view_ipercent.findViewById(R.id.edit_delta_H2);
        edit_time = (EditText) view_ipercent.findViewById(R.id.edit_time);
        edit_nozle = (EditText) view_ipercent.findViewById(R.id.edit_nozle);

        tv_density = (TextView) view_density.findViewById(R.id.text_density);
        tv_flowrate = (TextView) view_flowrate.findViewById(R.id.text_FlowRate);
        tv_wateramount = (TextView) view_wateramount.findViewById(R.id.text_WaterAmount);
        tv_allofwater = (TextView) view_wateramount.findViewById(R.id.text_AllofWater);
        tv_ipercent = (TextView) view_ipercent.findViewById(R.id.text_iPercent);

/*        m_fDensity = Float.parseFloat(tv_density.getText().toString().substring(6));
        m_fDensity = Float.parseFloat(tv_density.getText().toString().substring(6));
        m_fDensity = Float.parseFloat(tv_density.getText().toString().substring(6));
        m_fDensity = Float.parseFloat(tv_density.getText().toString().substring(6));*/

        switch(getArguments().getInt(ARG_SECTION_NUMBER)){
            case 1:
                Button btn_density = (Button) view_density.findViewById(R.id.btn_Density);
                btn_density.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String get_pressure = edit_pressure.getText().toString();
                        get_pressure = get_pressure.trim(); //공백 처리

                        String get_psdown = edit_Ps_down.getText().toString();
                        get_psdown = get_psdown.trim(); //공백 처리

                        String get_ts = edit_Ts.getText().toString();
                        get_ts = get_ts.trim(); //공백 처리

                        if (get_pressure.getBytes().length <= 0) {//빈값이 넘어올때의 처리
                            Toast.makeText(view_density.getContext(), "대기압을 입력하세요.", Toast.LENGTH_SHORT).show();
                        } else if (get_psdown.getBytes().length <= 0) {//빈값이 넘어올때의 처리
                            Toast.makeText(view_density.getContext(), "배출가스정압을 입력하세요.", Toast.LENGTH_SHORT).show();
                        } else if (get_ts.getBytes().length <= 0) {//빈값이 넘어올때의 처리
                            Toast.makeText(view_density.getContext(), "배출가스온도를 입력하세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            mainActivity.setPressure(Float.parseFloat(get_pressure));
                            mainActivity.setPs_down(Float.parseFloat(get_psdown));
                            mainActivity.setTs(Float.parseFloat(get_ts));
                            mainActivity.setDensity(getDensity());
                        }

                        tv_density.setText(String.format("밀도 : %.2f", mainActivity.getDensity()));
                    }
                });
                return view_density;

            case 2:
                Button btn_flowrate = (Button) view_flowrate.findViewById(R.id.btn_FlowRate);
                btn_flowrate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String get_cp = edit_Cp.getText().toString();
                        get_cp = get_cp.trim(); //공백 처리

                        String get_psup = edit_Ps_up.getText().toString();
                        get_psup = get_psup.trim(); //공백 처리

                        if(get_cp.getBytes().length <= 0){//빈값이 넘어올때의 처리
                            Toast.makeText(view_flowrate.getContext(), "피토우관계수를 입력하세요.", Toast.LENGTH_SHORT).show();
                        }
                        else if(get_psup.getBytes().length <= 0){//빈값이 넘어올때의 처리
                            Toast.makeText(view_flowrate.getContext(), "배출가스동압을 입력하세요.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            mainActivity.setFlowRate(getFlowRate());
                        }
                        tv_flowrate.setText(String.format("유속 : %.2f", mainActivity.getFlowRate()));
                    }
                });
                return view_flowrate;

            case 3:
                Button btn_wateramount = (Button) view_wateramount.findViewById(R.id.btn_WaterAmount);
                btn_wateramount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String get_ma1 = edit_Ma1.getText().toString();
                        get_ma1 = get_ma1.trim(); //공백 처리

                        String get_ma2 = edit_Ma2.getText().toString();
                        get_ma2 = get_ma2.trim(); //공백 처리

                        String get_ta = edit_Ta.getText().toString();
                        get_ta = get_ta.trim(); //공백 처리

                        if(get_ma1.getBytes().length <= 0){//빈값이 넘어올때의 처리
                            Toast.makeText(view_wateramount.getContext(), "흡습 전을 입력하세요.", Toast.LENGTH_SHORT).show();
                        }
                        else if(get_ma2.getBytes().length <= 0){//빈값이 넘어올때의 처리
                            Toast.makeText(view_wateramount.getContext(), "흡습 후를 입력하세요.", Toast.LENGTH_SHORT).show();
                        }
                        else if(get_ta.getBytes().length <= 0){//빈값이 넘어올때의 처리
                            Toast.makeText(view_wateramount.getContext(), "흡인가스온도를 입력하세요.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            mainActivity.setWaterAmount(getWaterAmount());
                        }
                        tv_wateramount.setText(String.format("수분량 : %.2f", mainActivity.getWaterAmount()));
                    }
                });
                Button btn_allofwater = (Button)view_wateramount.findViewById(R.id.btn_AllofWater);
                btn_allofwater.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String get_vm = edit_Vm.getText().toString();
                        get_vm = get_vm.trim(); //공백 처리

                        if(get_vm.getBytes().length <= 0){//빈값이 넘어올때의 처리
                            Toast.makeText(view_wateramount.getContext(), "시료채취량을 입력하세요.", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            mainActivity.setVm(Float.parseFloat(get_vm));
                            mainActivity.setAllofWater(getAllofWater());
                        }
                        tv_allofwater.setText(String.format("물의 총량 : %.2f", mainActivity.getAllofWater()));
                    }
                });
                return view_wateramount;

            case 4:
                Button btn_ipercent = (Button) view_ipercent.findViewById(R.id.btn_iPercent);
                btn_ipercent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String get_tm = edit_Tm.getText().toString();
                        get_tm = get_tm.trim(); //공백 처리

                        String get_delta_h2 = edit_delta_H2.getText().toString();
                        get_delta_h2 = get_delta_h2.trim(); //공백 처리

                        String get_time = edit_time.getText().toString();
                        get_time = get_time.trim(); //공백 처리

                        String get_nozle = edit_nozle.getText().toString();
                        get_nozle = get_nozle.trim(); //공백 처리

                        if(get_tm.getBytes().length <= 0){//빈값이 넘어올때의 처리
                            Toast.makeText(view_ipercent.getContext(), "가스미터온도를 입력하세요.", Toast.LENGTH_SHORT).show();
                        }
                        else if(get_delta_h2.getBytes().length <= 0){//빈값이 넘어올때의 처리
                            Toast.makeText(view_ipercent.getContext(), "오리피스압착을 입력하세요.", Toast.LENGTH_SHORT).show();
                        }
                        else if(get_time.getBytes().length <= 0){//빈값이 넘어올때의 처리
                            Toast.makeText(view_ipercent.getContext(), "시료채취시간을 입력하세요.", Toast.LENGTH_SHORT).show();
                        }
                        else if(get_nozle.getBytes().length <= 0){//빈값이 넘어올때의 처리
                            Toast.makeText(view_ipercent.getContext(), "흡인노즐을 입력하세요.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            mainActivity.setiPercent(getIpercent());
                        }
                        tv_ipercent.setText(String.format("i 퍼센트 : %.2f", mainActivity.getiPercent()));
                    }
                });
                return view_ipercent;
        }
        return null;
    }

    public float getDensity()
    {
        float r = 1.3f;

        float density;
        float ts = Float.valueOf(""+edit_Ts.getText());
        float pressure = Float.valueOf(""+edit_pressure.getText());
        float ps_down = Float.valueOf(""+edit_Ps_down.getText());

        density = r * (273/(273+ts)) * ((pressure+ps_down)/760);

        return density;
    }
    public float getFlowRate()
    {
        float Cp = Float.parseFloat(""+edit_Cp.getText());
        float ps_up = Float.parseFloat(""+edit_Ps_up.getText());
        float density = mainActivity.getDensity();
        float value = (2.0f * 9.81f * ps_up) / density;
        float flowrate = Cp * (float)Math.sqrt(value);

        return flowrate;
    }
    public float getWaterAmount()
    {
        float water = Float.parseFloat(""+edit_Ma2.getText()) - Float.parseFloat(""+edit_Ma1.getText());
        float son = (22.4f/18.0f) * water;
        float ta = Float.parseFloat(""+edit_Ta.getText());
        float pressure = mainActivity.getPressure();
        float mom = 10.0f * (273.0f/(273.0f+ta)) * ((pressure+2.94f)/760.0f) + (22.4f/18.0f) * water;

        return (son / mom) * 100.0f;
    }
    public float getAllofWater()
    {
        float vm = Float.parseFloat(""+edit_Vm.getText());
        float water_amount = getWaterAmount();
        float water = vm * 1000.0f * (water_amount/(100.0f-water_amount)) * (18.0f/22.4f);

        return water;
    }
    public float getIpercent()
    {
        float ts = mainActivity.getTs();
        float vm = mainActivity.getVm();
        float tm = Float.parseFloat(""+edit_Tm.getText());
        float pressure = mainActivity.getPressure();
        float delta_h2 = Float.parseFloat(""+edit_delta_H2.getText());
        float ps = mainActivity.getPs_down();
        float time = Float.parseFloat(""+edit_time.getText());
        float nozle = Float.parseFloat(""+edit_nozle.getText());
        float wateramount = mainActivity.getWaterAmount();
        float flowrate = mainActivity.getFlowRate();

        float son = (273.0f+ts) * ((0.00346f * wateramount) + (vm/(273.0f+tm)) * (pressure+(delta_h2/13.6f))) * (1.667f*10000.0f);
        float mom = (760.0f+ps) * time * flowrate * ((3.14f*nozle*nozle)/4.0f);

        return son / mom;
    }
}
