package com.maven.inanay;


import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.TabHost;

import java.util.Objects;

public class TabHostActivity extends TabActivity {

    private int x = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);

        final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        TabHost.TabSpec spec;
        Intent intent;

        spec = tabHost.newTabSpec("Calendario");
        spec.setIndicator("CALENDAR");

        intent = new Intent(this, CaldroidSampleActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("LastMENS");
        spec.setIndicator("WK by WK");

        intent = new Intent(this, Main2Activity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("AlarmMe");
        spec.setIndicator("REMINDERS");

        intent = new Intent(this, AlarmMe.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        if(x == 1){
            tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#FFF47187"));
            tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#888888"));
            tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#888888"));
            x = 2;
        }



        tabHost.setCurrentTab(1);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onTabChanged(String tabId) {

                if(Objects.equals(tabId, "LastMENS")){
                    tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#FFF47187"));
                    tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#888888"));
                    tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#888888"));
                }
                else if (Objects.equals(tabId, "Calendario")){
                    tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#FFF47187"));
                    tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#888888"));
                    tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#888888"));
                }
                else if (Objects.equals(tabId, "AlarmMe")){
                    tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#FFF47187"));
                    tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#888888"));
                    tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#888888"));
                }
            }
        });
    }
}
