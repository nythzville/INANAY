package com.maven.inanay;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements
        View.OnClickListener{
    TextView txt1, txt2, txt3;
    Button opendate, dday, snd1, snd2, btok, btcan;
    MediaPlayer mp0, mp1;

    private int year_x, month_x, day_x;
    private int mYear, mMonth, mDay;
    private int mmYear, mmMonth, mmDay;
    private long maxdatelimit0, mindatelimit0, maxdatelimit1, mindatelimit1;
    private Date date1, date2;
    private String CurrentDate, FinalDate;
    private int x = 1;
    final Calendar c = Calendar.getInstance();
    private String dateInStringArray[];
    private int Null = 0, finishflag, finishflagwkbywk, startupflag;
    public long differenceDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        txt1 = (TextView)findViewById(R.id.txt1);
        txt2 = (TextView)findViewById(R.id.txt2);
        txt3 = (TextView)findViewById(R.id.txt3);

        opendate = (Button)findViewById(R.id.opendate);
        dday = (Button)findViewById(R.id.dday);
        snd1 = (Button)findViewById(R.id.snd1);
        snd2 = (Button)findViewById(R.id.snd2);
        btok = (Button)findViewById(R.id.btok);
        btcan = (Button)findViewById(R.id.btcan);

        SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
        startupflag = shared.getInt("startupflag", Integer.parseInt("0"));

        if(startupflag == 1){
            btcan.setEnabled(false);
        }

        initialconditions();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onClickOK(View view){

        String str0 = opendate.getText().toString();
        if(!Objects.equals(str0, "PILIIN ANG PETSA")){

            SharedPreferences pref;
            pref = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("mYear", mYear);
            editor.putInt("mMonth", mMonth);
            editor.putInt("mDay", mDay);
            editor.putInt("year_x", year_x);
            editor.putInt("month_x", month_x);
            editor.putInt("day_x", day_x);
            editor.putInt("startupflag", 0);
            editor.putLong("differenceDates", differenceDates);
            editor.apply();

            SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
            finishflag = shared.getInt("finishflag", Integer.parseInt("0"));
            if (finishflag == 1) {
                pref = getSharedPreferences("info", MODE_PRIVATE);
                editor = pref.edit();
                editor.putInt("finishflag", 0);
                editor.apply();
            }
            finishflagwkbywk = shared.getInt("finishflagwkbywk", Integer.parseInt("0"));
            if (finishflagwkbywk == 1) {
                pref = getSharedPreferences("info", MODE_PRIVATE);
                editor = pref.edit();
                editor.putInt("finishflagwkbywk", 0);
                editor.apply();
            }

            if(startupflag == 1){
                Intent intent = new Intent(this,TabHostActivity.class);
                startActivity(intent);
                finish();
            }



            finish();

        }
    }

    public void onClickCancel(View view){
        finish();
    }

    public void sound1(View view){
        stopPlaying();
        mp0 = MediaPlayer.create(this, R.raw.lask);
        mp0.start();
    }

    public void sound2(View view){
        stopPlaying();
        mp1 = MediaPlayer.create(this, R.raw.due);
        mp1.start();
    }

    private void stopPlaying() {
        if (mp0 != null) {
            mp0.stop();
            mp0.release();
            mp0 = null;
        }

        if (mp1 != null) {
            mp1.stop();
            mp1.release();
            mp1 = null;
        }
    }

    @Override
    public void onClick(View v) {

        if (v == opendate) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                            mYear = year;
                            mMonth = monthOfYear;
                            mDay = dayOfMonth;

                            String dateInString = year+"-"+(monthOfYear + 1)+"-"+dayOfMonth;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar c = Calendar.getInstance();
                            try {
                                c.setTime(sdf.parse(dateInString));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            c.add(Calendar.DATE, 280);
                            sdf = new SimpleDateFormat("MM/dd/yyyy");
                            Date resultdate = new Date(c.getTimeInMillis());
                            dateInString = sdf.format(resultdate);

                            dateInStringArray = dateInString.split("/");

                            year_x = Integer.parseInt(dateInStringArray[2]);
                            month_x = Integer.parseInt(dateInStringArray[0]) - 1;
                            day_x = Integer.parseInt(dateInStringArray[1]);

                            if(mMonth == 0){
                                opendate.setText(mDay + "-" + "Jan" + "-" + mYear);
                            }
                            else if(mMonth == 1){
                                opendate.setText(mDay + "-" + "Feb" + "-" + mYear);
                            }
                            else if(mMonth == 2){
                                opendate.setText(mDay + "-" + "Mar" + "-" + mYear);
                            }
                            else if(mMonth == 3){
                                opendate.setText(mDay + "-" + "Apr" + "-" + mYear);
                            }
                            else if(mMonth == 4){
                                opendate.setText(mDay + "-" + "May" + "-" + mYear);
                            }
                            else if(mMonth == 5){
                                opendate.setText(mDay + "-" + "Jun" + "-" + mYear);
                            }
                            else if(mMonth == 6){
                                opendate.setText(mDay + "-" + "Jul" + "-" + mYear);
                            }
                            else if(mMonth == 7){
                                opendate.setText(mDay + "-" + "Aug" + "-" + mYear);
                            }
                            else if(mMonth == 8){
                                opendate.setText(mDay + "-" + "Sep" + "-" + mYear);
                            }
                            else if(mMonth == 9){
                                opendate.setText(mDay + "-" + "Oct" + "-" + mYear);
                            }
                            else if(mMonth == 10){
                                opendate.setText(mDay + "-" + "Nov" + "-" + mYear);
                            }
                            else if(mMonth == 11){
                                opendate.setText(mDay + "-" + "Dec" + "-" + mYear);
                            }

                            if(month_x == 0){
                                dday.setText(day_x + "-" + "Jan" + "-" + year_x);
                            }
                            else if(month_x == 1){
                                dday.setText(day_x + "-" + "Feb" + "-" + year_x);
                            }
                            else if(month_x == 2){
                                dday.setText(day_x + "-" + "Mar" + "-" + year_x);
                            }
                            else if(month_x == 3){
                                dday.setText(day_x + "-" + "Apr" + "-" + year_x);
                            }
                            else if(month_x == 4){
                                dday.setText(day_x + "-" + "May" + "-" + year_x);
                            }
                            else if(month_x == 5){
                                dday.setText(day_x + "-" + "Jun" + "-" + year_x);
                            }
                            else if(month_x == 6){
                                dday.setText(day_x + "-" + "Jul" + "-" + year_x);
                            }
                            else if(month_x == 7){
                                dday.setText(day_x + "-" + "Aug" + "-" + year_x);
                            }
                            else if(month_x == 8){
                                dday.setText(day_x + "-" + "Sep" + "-" + year_x);
                            }
                            else if(month_x == 9){
                                dday.setText(day_x + "-" + "Oct" + "-" + year_x);
                            }
                            else if(month_x == 10){
                                dday.setText(day_x + "-" + "Nov" + "-" + year_x);
                            }
                            else if(month_x == 11){
                                dday.setText(day_x + "-" + "Dec" + "-" + year_x);
                            }

                            SharedPreferences pref;
                            pref = getSharedPreferences("info", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();

                            FinalDate = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
                            SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");
                            try {
                                date1 = dates.parse(CurrentDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                date2 = dates.parse(FinalDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            long difference = Math.abs(date1.getTime() - date2.getTime());
                            differenceDates = difference / (24 * 60 * 60 * 1000 * 7);
                            String dayDifference = Long.toString(differenceDates);
                            if(differenceDates == 1){
                                txt3.setText("Congratulations! You are " + dayDifference + " Week Pregnant.");
                            } else {
                                txt3.setText("Congratulations! You are " + dayDifference + " Weeks Pregnant.");
                            }

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMaxDate(maxdatelimit0);
            datePickerDialog.getDatePicker().setMinDate(mindatelimit0);
            datePickerDialog.show();
        }

        else if (v == dday) {
            DatePickerDialog datePickerDialog1 = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                            year_x = year;
                            month_x = monthOfYear;
                            day_x = dayOfMonth;

                            String dateInString = year+"-"+(monthOfYear + 1)+"-"+dayOfMonth;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar c = Calendar.getInstance();
                            try {
                                c.setTime(sdf.parse(dateInString));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            c.add(Calendar.DATE, -280);
                            sdf = new SimpleDateFormat("MM/dd/yyyy");
                            Date resultdate = new Date(c.getTimeInMillis());
                            dateInString = sdf.format(resultdate);

                            dateInStringArray = dateInString.split("/");

                            mYear = Integer.parseInt(dateInStringArray[2]);
                            mMonth = Integer.parseInt(dateInStringArray[0]) - 1;
                            mDay = Integer.parseInt(dateInStringArray[1]);

                            if(mMonth == 0){
                                opendate.setText(mDay + "-" + "Jan" + "-" + mYear);
                            }
                            else if(mMonth == 1){
                                opendate.setText(mDay + "-" + "Feb" + "-" + mYear);
                            }
                            else if(mMonth == 2){
                                opendate.setText(mDay + "-" + "Mar" + "-" + mYear);
                            }
                            else if(mMonth == 3){
                                opendate.setText(mDay + "-" + "Apr" + "-" + mYear);
                            }
                            else if(mMonth == 4){
                                opendate.setText(mDay + "-" + "May" + "-" + mYear);
                            }
                            else if(mMonth == 5){
                                opendate.setText(mDay + "-" + "Jun" + "-" + mYear);
                            }
                            else if(mMonth == 6){
                                opendate.setText(mDay + "-" + "Jul" + "-" + mYear);
                            }
                            else if(mMonth == 7){
                                opendate.setText(mDay + "-" + "Aug" + "-" + mYear);
                            }
                            else if(mMonth == 8){
                                opendate.setText(mDay + "-" + "Sep" + "-" + mYear);
                            }
                            else if(mMonth == 9){
                                opendate.setText(mDay + "-" + "Oct" + "-" + mYear);
                            }
                            else if(mMonth == 10){
                                opendate.setText(mDay + "-" + "Nov" + "-" + mYear);
                            }
                            else if(mMonth == 11){
                                opendate.setText(mDay + "-" + "Dec" + "-" + mYear);
                            }

                            if(month_x == 0){
                                dday.setText(day_x + "-" + "Jan" + "-" + year_x);
                            }
                            else if(month_x == 1){
                                dday.setText(day_x + "-" + "Feb" + "-" + year_x);
                            }
                            else if(month_x == 2){
                                dday.setText(day_x + "-" + "Mar" + "-" + year_x);
                            }
                            else if(month_x == 3){
                                dday.setText(day_x + "-" + "Apr" + "-" + year_x);
                            }
                            else if(month_x == 4){
                                dday.setText(day_x + "-" + "May" + "-" + year_x);
                            }
                            else if(month_x == 5){
                                dday.setText(day_x + "-" + "Jun" + "-" + year_x);
                            }
                            else if(month_x == 6){
                                dday.setText(day_x + "-" + "Jul" + "-" + year_x);
                            }
                            else if(month_x == 7){
                                dday.setText(day_x + "-" + "Aug" + "-" + year_x);
                            }
                            else if(month_x == 8){
                                dday.setText(day_x + "-" + "Sep" + "-" + year_x);
                            }
                            else if(month_x == 9){
                                dday.setText(day_x + "-" + "Oct" + "-" + year_x);
                            }
                            else if(month_x == 10){
                                dday.setText(day_x + "-" + "Nov" + "-" + year_x);
                            }
                            else if(month_x == 11){
                                dday.setText(day_x + "-" + "Dec" + "-" + year_x);
                            }

                            SharedPreferences pref;
                            pref = getSharedPreferences("info", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();

                            FinalDate = (mMonth + 1) + "/" + mDay + "/" + mYear;
                            SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");
                            try {
                                date1 = dates.parse(CurrentDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                date2 = dates.parse(FinalDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            long difference = Math.abs(date1.getTime() - date2.getTime());
                            differenceDates = difference / (24 * 60 * 60 * 1000 * 7);
                            String dayDifference = Long.toString(differenceDates);
                            if(differenceDates == 1){
                                txt3.setText("Congratulations! You are " + dayDifference + " Week Pregnant.");
                            } else {
                                txt3.setText("Congratulations! You are " + dayDifference + " Weeks Pregnant.");
                            }

                        }
                    }, year_x, month_x, day_x);
            datePickerDialog1.getDatePicker().setMaxDate(maxdatelimit1);
            datePickerDialog1.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog1.show();
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }

    private void initialconditions() {
        if(x == 1){
            SharedPreferences shared = getSharedPreferences("info",MODE_PRIVATE);
            mYear = shared.getInt("mYear", Integer.parseInt("0"));
            mMonth = shared.getInt("mMonth", Integer.parseInt("0"));
            mDay = shared.getInt("mDay", Integer.parseInt("0"));
            year_x = shared.getInt("year_x", Integer.parseInt("0"));
            month_x = shared.getInt("month_x", Integer.parseInt("0"));
            day_x = shared.getInt("day_x", Integer.parseInt("0"));

            finishflag = 1;
            finishflagwkbywk = 1;

            SharedPreferences pref;
            pref = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("finishflag",finishflag);
            editor.putInt("finishflagwkbywk",finishflagwkbywk);
            editor.apply();

            if(mYear == Null && mMonth == Null && mDay == Null && year_x == Null && month_x == Null && day_x == Null){
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                String dateInString = mYear+"-"+(mMonth + 1)+"-"+mDay;
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    c.setTime(sdf.parse(dateInString));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.add(Calendar.DATE, 273);
                sdf = new SimpleDateFormat("MM/dd/yyyy");
                Date resultdate = new Date(c.getTimeInMillis());
                dateInString = sdf.format(resultdate);
                dateInStringArray = dateInString.split("/");
                year_x = Integer.parseInt(dateInStringArray[2]);
                month_x = Integer.parseInt(dateInStringArray[0]) - 1;
                day_x = Integer.parseInt(dateInStringArray[1]);
            } else {
                mYear = shared.getInt("mYear", Integer.parseInt("0"));
                mMonth = shared.getInt("mMonth", Integer.parseInt("0"));
                mDay = shared.getInt("mDay", Integer.parseInt("0"));
                year_x = shared.getInt("year_x", Integer.parseInt("0"));
                month_x = shared.getInt("month_x", Integer.parseInt("0"));
                day_x = shared.getInt("day_x", Integer.parseInt("0"));

                if(mMonth == 0){
                    opendate.setText(mDay + "-" + "Jan" + "-" + mYear);
                }
                else if(mMonth == 1){
                    opendate.setText(mDay + "-" + "Feb" + "-" + mYear);
                }
                else if(mMonth == 2){
                    opendate.setText(mDay + "-" + "Mar" + "-" + mYear);
                }
                else if(mMonth == 3){
                    opendate.setText(mDay + "-" + "Apr" + "-" + mYear);
                }
                else if(mMonth == 4){
                    opendate.setText(mDay + "-" + "May" + "-" + mYear);
                }
                else if(mMonth == 5){
                    opendate.setText(mDay + "-" + "Jun" + "-" + mYear);
                }
                else if(mMonth == 6){
                    opendate.setText(mDay + "-" + "Jul" + "-" + mYear);
                }
                else if(mMonth == 7){
                    opendate.setText(mDay + "-" + "Aug" + "-" + mYear);
                }
                else if(mMonth == 8){
                    opendate.setText(mDay + "-" + "Sep" + "-" + mYear);
                }
                else if(mMonth == 9){
                    opendate.setText(mDay + "-" + "Oct" + "-" + mYear);
                }
                else if(mMonth == 10){
                    opendate.setText(mDay + "-" + "Nov" + "-" + mYear);
                }
                else if(mMonth == 11){
                    opendate.setText(mDay + "-" + "Dec" + "-" + mYear);
                }

                if(month_x == 0){
                    dday.setText(day_x + "-" + "Jan" + "-" + year_x);
                }
                else if(month_x == 1){
                    dday.setText(day_x + "-" + "Feb" + "-" + year_x);
                }
                else if(month_x == 2){
                    dday.setText(day_x + "-" + "Mar" + "-" + year_x);
                }
                else if(month_x == 3){
                    dday.setText(day_x + "-" + "Apr" + "-" + year_x);
                }
                else if(month_x == 4){
                    dday.setText(day_x + "-" + "May" + "-" + year_x);
                }
                else if(month_x == 5){
                    dday.setText(day_x + "-" + "Jun" + "-" + year_x);
                }
                else if(month_x == 6){
                    dday.setText(day_x + "-" + "Jul" + "-" + year_x);
                }
                else if(month_x == 7){
                    dday.setText(day_x + "-" + "Aug" + "-" + year_x);
                }
                else if(month_x == 8){
                    dday.setText(day_x + "-" + "Sep" + "-" + year_x);
                }
                else if(month_x == 9){
                    dday.setText(day_x + "-" + "Oct" + "-" + year_x);
                }
                else if(month_x == 10){
                    dday.setText(day_x + "-" + "Nov" + "-" + year_x);
                }
                else if(month_x == 11){
                    dday.setText(day_x + "-" + "Dec" + "-" + year_x);
                }

                mmYear = c.get(Calendar.YEAR);
                mmMonth = c.get(Calendar.MONTH);
                mmDay = c.get(Calendar.DAY_OF_MONTH);

                CurrentDate = (mmMonth + 1) + "/" + mmDay + "/" + mmYear;
                FinalDate = (mMonth + 1) + "/" + mDay + "/" + mYear;
                SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    date1 = dates.parse(CurrentDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    date2 = dates.parse(FinalDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long difference = Math.abs(date1.getTime() - date2.getTime());
                differenceDates = difference / (24 * 60 * 60 * 1000 * 7);
                String dayDifference = Long.toString(differenceDates);
                if(differenceDates == 1){
                    txt3.setText("Congratulations! You are " + dayDifference + " Week Pregnant.");
                } else {
                    txt3.setText("Congratulations! You are " + dayDifference + " Weeks Pregnant.");
                }
            }

            x = 1;
        }

        Calendar cc = Calendar.getInstance();
        cc.add(Calendar.DATE, -7);
        maxdatelimit0 = cc.getTimeInMillis();

        cc.add(Calendar.DATE, -273);
        mindatelimit0 = cc.getTimeInMillis();

        Calendar ccc = Calendar.getInstance();
        ccc.add(Calendar.DATE, 273);
        maxdatelimit1 = ccc.getTimeInMillis();

        opendate.setOnClickListener(this);
        dday.setOnClickListener(this);

        mmYear = c.get(Calendar.YEAR);
        mmMonth = c.get(Calendar.MONTH);
        mmDay = c.get(Calendar.DAY_OF_MONTH);

        CurrentDate = (mmMonth + 1) + "/" + mmDay + "/" + mmYear;
    }


}
