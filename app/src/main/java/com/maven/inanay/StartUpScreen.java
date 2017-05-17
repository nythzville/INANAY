package com.maven.inanay;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class StartUpScreen extends AppCompatActivity {
    public int mYear, mMonth, mDay, month_x, year_x, day_x;
    private int Null = 0;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up_screen);

        SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
        String password = shared.getString("password", "0");

        mYear = shared.getInt("mYear", Integer.parseInt("0"));
        mMonth = shared.getInt("mMonth", Integer.parseInt("0"));
        mDay = shared.getInt("mDay", Integer.parseInt("0"));
        year_x = shared.getInt("year_x", Integer.parseInt("0"));
        month_x = shared.getInt("month_x", Integer.parseInt("0"));
        day_x = shared.getInt("day_x", Integer.parseInt("0"));

        if(Objects.equals(password, "0")){
            editor.putString("password", "bamboohay");
            editor.apply();
        }


        Runnable runnable3secs = new Runnable() {
            @Override
            public void run() {
                nextActivity();
                finish();
            }
        };

        Handler myHandler = new Handler();
        myHandler.postDelayed(runnable3secs,3000);

    }

    public void nextActivity(){

        if(mYear == Null && mMonth == Null && mDay == Null && year_x == Null && month_x == Null && day_x == Null){
            SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("startupflag", 1);
            editor.apply();

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this,TabHostActivity.class);
            startActivity(intent);
        }
    }
}

