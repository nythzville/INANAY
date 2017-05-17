package com.maven.inanay;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class CaldroidSampleActivity extends AppCompatActivity {
    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;
    private int mYear, mMonth, mDay, year_x, month_x, day_x, xxx = 1, xyz = 1, yyy = 2, mmMonth, mmYear, mmDay;
    private int nYear, nMonth, nDay, finishflag, finishflagedit;
    private String lastmens, dday;
    private Date date1, date2, date3, date4, resultdate, dateprev1, dateprev2, d1, d2, d3, d4, d5, d6, d7, d1prev, d2prev, d3prev, d4prev, d5prev, d6prev, d7prev;
    private String dateInStringArray[], reminderdates[], reminder;
    private String dateInString, CurrentDate;
    private SimpleDateFormat sdf;
    private Calendar c;
    public long differenceDates;
    public int differenceDates0, differenceDates1, calremnum, prevcount;
    public Date datespres[] = new Date[1000], datesprev[] = new Date[1000], dateprev3;
    public String dateString[] = new String[1000];

    final Context context = this;

    private AlarmListAdapter mAlarmListAdapter;
    private Alarm mCurrentAlarm;


    @SuppressLint("SimpleDateFormat")
    private void setCustomResourceForDates() {


        caldroidFragment.refreshView();

        SharedPreferences shared = getSharedPreferences("info",MODE_PRIVATE);
        //Using getXXX- with XX is type date you wrote to file "name_file"
        mYear = shared.getInt("mYear", Integer.parseInt("0"));
        mMonth = shared.getInt("mMonth", Integer.parseInt("0"));
        mDay = shared.getInt("mDay", Integer.parseInt("0"));
        year_x = shared.getInt("year_x", Integer.parseInt("0"));
        month_x = shared.getInt("month_x", Integer.parseInt("0"));
        day_x = shared.getInt("day_x", Integer.parseInt("0"));


        Calendar c0 = Calendar.getInstance();

        mmYear = c0.get(Calendar.YEAR);
        mmMonth = c0.get(Calendar.MONTH);
        mmDay = c0.get(Calendar.DAY_OF_MONTH);

        String CurrentDate0 = (mmMonth + 1) + "/" + mmDay + "/" + mmYear;
        String FinalDate0 = (mMonth + 1) + "/" + mDay + "/" + mYear;
        SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");
        try {
            date3 = dates.parse(CurrentDate0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            date4 = dates.parse(FinalDate0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long difference = Math.abs(date3.getTime() - date4.getTime());
        differenceDates = difference / (24 * 60 * 60 * 1000 * 7);
        differenceDates0 = (int) differenceDates;
        differenceDates1 = differenceDates0*7;

        // To set the extraData:
        HashMap<String, Object> extraData = (HashMap<String, Object>) caldroidFragment.getExtraData();
        extraData.put("mYear", mYear);
        extraData.put("mMonth", mMonth);
        extraData.put("mDay", mDay);

        dateInString = mYear + "-" + (mMonth + 1) + "-" + mDay;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear1 = Integer.parseInt(dateInStringArray[2]);
        int nMonth1 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay1 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear1", nYear1);
        extraData.put("nMonth1", nMonth1);
        extraData.put("nDay1", nDay1);

        dateInString = nYear1 + "-" + (nMonth1 + 1) + "-" + nDay1;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear2 = Integer.parseInt(dateInStringArray[2]);
        int nMonth2 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay2 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear2", nYear2);
        extraData.put("nMonth2", nMonth2);
        extraData.put("nDay2", nDay2);

        dateInString = nYear2 + "-" + (nMonth2 + 1) + "-" + nDay2;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear3 = Integer.parseInt(dateInStringArray[2]);
        int nMonth3 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay3 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear3", nYear3);
        extraData.put("nMonth3", nMonth3);
        extraData.put("nDay3", nDay3);

        dateInString = nYear3 + "-" + (nMonth3 + 1) + "-" + nDay3;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear4 = Integer.parseInt(dateInStringArray[2]);
        int nMonth4 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay4 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear4", nYear4);
        extraData.put("nMonth4", nMonth4);
        extraData.put("nDay4", nDay4);

        dateInString = nYear4 + "-" + (nMonth4 + 1) + "-" + nDay4;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear5 = Integer.parseInt(dateInStringArray[2]);
        int nMonth5 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay5 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear5", nYear5);
        extraData.put("nMonth5", nMonth5);
        extraData.put("nDay5", nDay5);

        dateInString = nYear5 + "-" + (nMonth5 + 1) + "-" + nDay5;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear6 = Integer.parseInt(dateInStringArray[2]);
        int nMonth6 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay6 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear6", nYear6);
        extraData.put("nMonth6", nMonth6);
        extraData.put("nDay6", nDay6);

        dateInString = nYear6 + "-" + (nMonth6 + 1) + "-" + nDay6;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear7 = Integer.parseInt(dateInStringArray[2]);
        int nMonth7 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay7 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear7", nYear7);
        extraData.put("nMonth7", nMonth7);
        extraData.put("nDay7", nDay7);

        dateInString = nYear7 + "-" + (nMonth7 + 1) + "-" + nDay7;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear8 = Integer.parseInt(dateInStringArray[2]);
        int nMonth8 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay8 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear8", nYear8);
        extraData.put("nMonth8", nMonth8);
        extraData.put("nDay8", nDay8);

        dateInString = nYear8 + "-" + (nMonth8 + 1) + "-" + nDay8;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear9 = Integer.parseInt(dateInStringArray[2]);
        int nMonth9 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay9 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear9", nYear9);
        extraData.put("nMonth9", nMonth9);
        extraData.put("nDay9", nDay9);

        dateInString = nYear9 + "-" + (nMonth9 + 1) + "-" + nDay9;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear10 = Integer.parseInt(dateInStringArray[2]);
        int nMonth10 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay10 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear10", nYear10);
        extraData.put("nMonth10", nMonth10);
        extraData.put("nDay10", nDay10);

        dateInString = nYear10 + "-" + (nMonth10 + 1) + "-" + nDay10;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear11 = Integer.parseInt(dateInStringArray[2]);
        int nMonth11 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay11 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear11", nYear11);
        extraData.put("nMonth11", nMonth11);
        extraData.put("nDay11", nDay11);

        dateInString = nYear11 + "-" + (nMonth11 + 1) + "-" + nDay11;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear12 = Integer.parseInt(dateInStringArray[2]);
        int nMonth12 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay12 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear12", nYear12);
        extraData.put("nMonth12", nMonth12);
        extraData.put("nDay12", nDay12);

        dateInString = nYear12 + "-" + (nMonth12 + 1) + "-" + nDay12;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear13 = Integer.parseInt(dateInStringArray[2]);
        int nMonth13 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay13 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear13", nYear13);
        extraData.put("nMonth13", nMonth13);
        extraData.put("nDay13", nDay13);

        dateInString = nYear13 + "-" + (nMonth13 + 1) + "-" + nDay13;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear14 = Integer.parseInt(dateInStringArray[2]);
        int nMonth14 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay14 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear14", nYear14);
        extraData.put("nMonth14", nMonth14);
        extraData.put("nDay14", nDay14);

        dateInString = nYear14 + "-" + (nMonth14 + 1) + "-" + nDay14;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear15 = Integer.parseInt(dateInStringArray[2]);
        int nMonth15 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay15 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear15", nYear15);
        extraData.put("nMonth15", nMonth15);
        extraData.put("nDay15", nDay15);

        dateInString = nYear15 + "-" + (nMonth15 + 1) + "-" + nDay15;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear16 = Integer.parseInt(dateInStringArray[2]);
        int nMonth16 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay16 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear16", nYear16);
        extraData.put("nMonth16", nMonth16);
        extraData.put("nDay16", nDay16);

        dateInString = nYear16 + "-" + (nMonth16 + 1) + "-" + nDay16;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear17 = Integer.parseInt(dateInStringArray[2]);
        int nMonth17 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay17 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear17", nYear17);
        extraData.put("nMonth17", nMonth17);
        extraData.put("nDay17", nDay17);

        dateInString = nYear17 + "-" + (nMonth17 + 1) + "-" + nDay17;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear18 = Integer.parseInt(dateInStringArray[2]);
        int nMonth18 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay18 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear18", nYear18);
        extraData.put("nMonth18", nMonth18);
        extraData.put("nDay18", nDay18);

        dateInString = nYear18 + "-" + (nMonth18 + 1) + "-" + nDay18;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear19 = Integer.parseInt(dateInStringArray[2]);
        int nMonth19 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay19 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear19", nYear19);
        extraData.put("nMonth19", nMonth19);
        extraData.put("nDay19", nDay19);

        dateInString = nYear19 + "-" + (nMonth19 + 1) + "-" + nDay19;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear20 = Integer.parseInt(dateInStringArray[2]);
        int nMonth20= Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay20 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear20", nYear20);
        extraData.put("nMonth20", nMonth20);
        extraData.put("nDay20", nDay20);

        dateInString = nYear20 + "-" + (nMonth20 + 1) + "-" + nDay20;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear21 = Integer.parseInt(dateInStringArray[2]);
        int nMonth21 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay21 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear21", nYear21);
        extraData.put("nMonth21", nMonth21);
        extraData.put("nDay21", nDay21);

        dateInString = nYear21 + "-" + (nMonth21 + 1) + "-" + nDay21;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear22 = Integer.parseInt(dateInStringArray[2]);
        int nMonth22 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay22 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear22", nYear22);
        extraData.put("nMonth22", nMonth22);
        extraData.put("nDay22", nDay22);

        dateInString = nYear22 + "-" + (nMonth22 + 1) + "-" + nDay22;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear23 = Integer.parseInt(dateInStringArray[2]);
        int nMonth23 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay23 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear23", nYear23);
        extraData.put("nMonth23", nMonth23);
        extraData.put("nDay23", nDay23);

        dateInString = nYear23 + "-" + (nMonth23 + 1) + "-" + nDay23;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear24 = Integer.parseInt(dateInStringArray[2]);
        int nMonth24 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay24 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear24", nYear24);
        extraData.put("nMonth24", nMonth24);
        extraData.put("nDay24", nDay24);

        dateInString = nYear24 + "-" + (nMonth24 + 1) + "-" + nDay24;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear25 = Integer.parseInt(dateInStringArray[2]);
        int nMonth25 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay25 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear25", nYear25);
        extraData.put("nMonth25", nMonth25);
        extraData.put("nDay25", nDay25);

        dateInString = nYear25 + "-" + (nMonth25 + 1) + "-" + nDay25;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear26 = Integer.parseInt(dateInStringArray[2]);
        int nMonth26 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay26 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear26", nYear26);
        extraData.put("nMonth26", nMonth26);
        extraData.put("nDay26", nDay26);

        dateInString = nYear26 + "-" + (nMonth26 + 1) + "-" + nDay26;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear27 = Integer.parseInt(dateInStringArray[2]);
        int nMonth27 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay27 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear27", nYear27);
        extraData.put("nMonth27", nMonth27);
        extraData.put("nDay27", nDay27);

        dateInString = nYear27 + "-" + (nMonth27 + 1) + "-" + nDay27;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear28 = Integer.parseInt(dateInStringArray[2]);
        int nMonth28 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay28 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear28", nYear28);
        extraData.put("nMonth28", nMonth28);
        extraData.put("nDay28", nDay28);

        dateInString = nYear28 + "-" + (nMonth28 + 1) + "-" + nDay28;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear29 = Integer.parseInt(dateInStringArray[2]);
        int nMonth29 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay29 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear29", nYear29);
        extraData.put("nMonth29", nMonth29);
        extraData.put("nDay29", nDay29);

        dateInString = nYear29 + "-" + (nMonth29 + 1) + "-" + nDay29;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear30 = Integer.parseInt(dateInStringArray[2]);
        int nMonth30= Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay30 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear30", nYear30);
        extraData.put("nMonth30", nMonth30);
        extraData.put("nDay30", nDay30);

        dateInString = nYear30 + "-" + (nMonth30 + 1) + "-" + nDay30;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear31 = Integer.parseInt(dateInStringArray[2]);
        int nMonth31 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay31 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear31", nYear31);
        extraData.put("nMonth31", nMonth31);
        extraData.put("nDay31", nDay31);

        dateInString = nYear31 + "-" + (nMonth31 + 1) + "-" + nDay31;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear32 = Integer.parseInt(dateInStringArray[2]);
        int nMonth32 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay32 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear32", nYear32);
        extraData.put("nMonth32", nMonth32);
        extraData.put("nDay32", nDay32);

        dateInString = nYear32 + "-" + (nMonth32 + 1) + "-" + nDay32;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear33 = Integer.parseInt(dateInStringArray[2]);
        int nMonth33 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay33 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear33", nYear33);
        extraData.put("nMonth33", nMonth33);
        extraData.put("nDay33", nDay33);

        dateInString = nYear33 + "-" + (nMonth33 + 1) + "-" + nDay33;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear34 = Integer.parseInt(dateInStringArray[2]);
        int nMonth34 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay34 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear34", nYear34);
        extraData.put("nMonth34", nMonth34);
        extraData.put("nDay34", nDay34);

        dateInString = nYear34 + "-" + (nMonth34 + 1) + "-" + nDay34;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear35 = Integer.parseInt(dateInStringArray[2]);
        int nMonth35 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay35 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear35", nYear35);
        extraData.put("nMonth35", nMonth35);
        extraData.put("nDay35", nDay35);

        dateInString = nYear35 + "-" + (nMonth35 + 1) + "-" + nDay35;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear36 = Integer.parseInt(dateInStringArray[2]);
        int nMonth36 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay36 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear36", nYear36);
        extraData.put("nMonth36", nMonth36);
        extraData.put("nDay36", nDay36);

        dateInString = nYear36 + "-" + (nMonth36 + 1) + "-" + nDay36;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear37 = Integer.parseInt(dateInStringArray[2]);
        int nMonth37 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay37 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear37", nYear37);
        extraData.put("nMonth37", nMonth37);
        extraData.put("nDay37", nDay37);

        dateInString = nYear37 + "-" + (nMonth37 + 1) + "-" + nDay37;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear38 = Integer.parseInt(dateInStringArray[2]);
        int nMonth38 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay38 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear38", nYear38);
        extraData.put("nMonth38", nMonth38);
        extraData.put("nDay38", nDay38);

        dateInString = nYear38 + "-" + (nMonth38 + 1) + "-" + nDay38;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear39 = Integer.parseInt(dateInStringArray[2]);
        int nMonth39 = Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay39 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear39", nYear39);
        extraData.put("nMonth39", nMonth39);
        extraData.put("nDay39", nDay39);

        dateInString = nYear39 + "-" + (nMonth39 + 1) + "-" + nDay39;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear40 = Integer.parseInt(dateInStringArray[2]);
        int nMonth40= Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay40 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear40", nYear40);
        extraData.put("nMonth40", nMonth40);
        extraData.put("nDay40", nDay40);

        dateInString = nYear40 + "-" + (nMonth40 + 1) + "-" + nDay40;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear41 = Integer.parseInt(dateInStringArray[2]);
        int nMonth41= Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay41 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear41", nYear41);
        extraData.put("nMonth41", nMonth41);
        extraData.put("nDay41", nDay41);

        dateInString = nYear41 + "-" + (nMonth41 + 1) + "-" + nDay41;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 7);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        dateInStringArray = dateInString.split("/");
        int nYear42 = Integer.parseInt(dateInStringArray[2]);
        int nMonth42= Integer.parseInt(dateInStringArray[0]) - 1;
        int nDay42 = Integer.parseInt(dateInStringArray[1]);
        extraData.put("nYear42", nYear42);
        extraData.put("nMonth42", nMonth42);
        extraData.put("nDay42", nDay42);



        mmYear = c.get(Calendar.YEAR);
        mmMonth = c.get(Calendar.MONTH);
        mmDay = c.get(Calendar.DAY_OF_MONTH);

        CurrentDate = (mmMonth + 1) + "/" + mmDay + "/" + mmYear;
        lastmens = (mMonth + 1) + "/" + mDay + "/" + mYear;
        dday =  (month_x + 1) + "/" + day_x + "/" + year_x;

        try {
            date1 = dates.parse(lastmens);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            date2 = dates.parse(dday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            date3 = dates.parse(CurrentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        dateInString = mYear + "-" + (mMonth + 1) + "-" + mDay;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, differenceDates1);
        d1 = new Date(c.getTimeInMillis());

        dateInString = mYear + "-" + (mMonth + 1) + "-" + mDay;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, differenceDates1 + 1);
        d2 = new Date(c.getTimeInMillis());

        dateInString = mYear + "-" + (mMonth + 1) + "-" + mDay;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, differenceDates1 + 2);
        d3 = new Date(c.getTimeInMillis());

        dateInString = mYear + "-" + (mMonth + 1) + "-" + mDay;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, differenceDates1 + 3);
        d4 = new Date(c.getTimeInMillis());

        dateInString = mYear + "-" + (mMonth + 1) + "-" + mDay;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, differenceDates1 + 4);
        d5 = new Date(c.getTimeInMillis());

        dateInString = mYear + "-" + (mMonth + 1) + "-" + mDay;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, differenceDates1 + 5);
        d6 = new Date(c.getTimeInMillis());

        dateInString = mYear + "-" + (mMonth + 1) + "-" + mDay;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, differenceDates1 + 6);
        d7 = new Date(c.getTimeInMillis());


        reminder = shared.getString("calreminders", "0");
        calremnum = shared.getInt("calremnum", Integer.parseInt("0"));

        if(calremnum > 0){
            reminderdates = reminder.split("/");

            for (int i = 0; i < calremnum; i++){

                Calendar cal0 = Calendar.getInstance();
                cal0.setTimeInMillis(Long.parseLong(reminderdates[i]));
                datespres[i] = (Date) cal0.getTime();
            }

            if(xyz == 1){
                prevcount = calremnum;
                xyz = 2;
            }

        } else {
            prevcount = 0;
            xyz = 1;
            yyy = 1;
        }

        if(xxx == 1) {
            Calendar cal = Calendar.getInstance();

            // Min date is last 7 days
            cal.add(Calendar.DATE, -1);
            dateprev1 = cal.getTime();

            // Max date is next 7 days
            cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            dateprev2 = cal.getTime();

            d1prev = dateprev1;
            d2prev = dateprev1;
            d3prev = dateprev1;
            d4prev = dateprev1;
            d5prev = dateprev1;
            d6prev = dateprev1;
            d7prev = dateprev1;


            for (int i = 0; i < 1000; i++) {
                Calendar cal1 = Calendar.getInstance();
                cal1.add(Calendar.DATE, -500);
                datesprev[i] = (Date) cal1.getTime();
            }

            xxx = 2;
        }


        if (caldroidFragment != null) {
            ColorDrawable pink = new ColorDrawable(getResources().getColor(R.color.Pink));
            ColorDrawable orange = new ColorDrawable(getResources().getColor(R.color.Orange));
            ColorDrawable prev = new ColorDrawable(getResources().getColor(R.color.white));
            ColorDrawable azure = new ColorDrawable(getResources().getColor(R.color.LawnGreen));
            ColorDrawable remcolor = new ColorDrawable(getResources().getColor(R.color.caldroid_sky_blue));

            caldroidFragment.setBackgroundDrawableForDate(prev, dateprev1);
            caldroidFragment.setBackgroundDrawableForDate(prev, dateprev2);

            caldroidFragment.setBackgroundDrawableForDate(prev, d1prev);
            caldroidFragment.setBackgroundDrawableForDate(prev, d2prev);
            caldroidFragment.setBackgroundDrawableForDate(prev, d3prev);
            caldroidFragment.setBackgroundDrawableForDate(prev, d4prev);
            caldroidFragment.setBackgroundDrawableForDate(prev, d5prev);
            caldroidFragment.setBackgroundDrawableForDate(prev, d6prev);
            caldroidFragment.setBackgroundDrawableForDate(prev, d7prev);

            if (calremnum > 0) {
                for (int i = 0; i < prevcount; i++) {
                    caldroidFragment.setBackgroundDrawableForDate(prev, datesprev[i]);
                }
                prevcount = calremnum;
            } else {
                for (int i = 0; i < 1000; i++) {
                    caldroidFragment.setBackgroundDrawableForDate(prev, datesprev[i]);
                }
            }

            caldroidFragment.setBackgroundDrawableForDate(pink, date1);
            caldroidFragment.setBackgroundDrawableForDate(orange, date2);

            caldroidFragment.setBackgroundDrawableForDate(azure, d1);
            caldroidFragment.setBackgroundDrawableForDate(azure, d2);
            caldroidFragment.setBackgroundDrawableForDate(azure, d3);
            caldroidFragment.setBackgroundDrawableForDate(azure, d4);
            caldroidFragment.setBackgroundDrawableForDate(azure, d5);
            caldroidFragment.setBackgroundDrawableForDate(azure, d6);
            caldroidFragment.setBackgroundDrawableForDate(azure, d7);

            if (calremnum > 0) {
                for (int i = 0; i < calremnum; i++) {
                    caldroidFragment.setBackgroundDrawableForDate(remcolor, datespres[i]);
                    datesprev[i] = datespres[i];
                }
            }

            dateprev1 = date1;
            dateprev2 = date2;

            d1prev = d1;
            d2prev = d2;
            d3prev = d3;
            d4prev = d4;
            d5prev = d5;
            d6prev = d6;
            d7prev = d7;

        }
        caldroidFragment.refreshView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Alarm Adapter
        mAlarmListAdapter = new AlarmListAdapter(this);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        caldroidFragment = new CaldroidSampleCustomFragment();

        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState,
                    "CALDROID_SAVED_STATE");
        }
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

            caldroidFragment.setArguments(args);
        }

        setCustomResourceForDates();

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();

        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.view_reminder);
                final TextView content_view = (TextView) dialog.findViewById(R.id.content_content);

                Button dialogButton2 = (Button) dialog.findViewById(R.id.dialogButtonOK);
                dialogButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                // Check if reminder on date
                int r_count = mAlarmListAdapter.getCount();
                Date r_date;
                String title = null;
                for (int x_count = 0; x_count < r_count; x_count++){
                    r_date = new Date(mAlarmListAdapter.getItem(x_count).getDate());
                    title = mAlarmListAdapter.getItem(x_count).getTitle();

                    if(r_date.getYear() == date.getYear()&& r_date.getMonth() == date.getMonth() && r_date.getDay() == date.getDay()){
                        Log.i("Reminder Match Date", "TRUE");
                        Log.i("Title", title);

                        // Setting Dialog content

                        DateFormat df = new SimpleDateFormat("MMM d yyyy 'at' h:mm a");
                        String str_date = df.format(r_date);

                        Log.i("String Date Formatted", str_date);
                        dialog.setTitle(str_date.toString());
                        content_view.setText(title);

                        dialog.show();

                    }else{
                        Log.i("Reminder Match Date", "FALSE");
                    }

                }
            }

            @Override
            public void onChangeMonth(int month, int year) {

            }

            @Override
            public void onLongClickDate(Date date, View view) {



            }

            @Override
            public void onCaldroidViewCreated() {
                if (caldroidFragment.getLeftArrowButton() != null) {

                }
            }

        };

        caldroidFragment.setCaldroidListener(listener);

        final TextView textView = (TextView) findViewById(R.id.textview);

        final Button customizeButton = (Button) findViewById(R.id.customize_button);

        customizeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                setCustomResourceForDates();

                if (undo) {
                    customizeButton.setText(getString(R.string.customize));
                    textView.setText("");

                    caldroidFragment.clearDisableDates();
                    caldroidFragment.clearSelectedDates();
                    caldroidFragment.setMinDate(null);
                    caldroidFragment.setMaxDate(null);
                    caldroidFragment.setShowNavigationArrows(true);
                    caldroidFragment.setEnableSwipe(true);
                    caldroidFragment.refreshView();
                    undo = false;
                    return;
                }

                undo = true;
                customizeButton.setText(getString(R.string.undo));
                Calendar cal = Calendar.getInstance();

                cal.add(Calendar.DATE, -7);
                Date minDate = cal.getTime();

                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 14);
                Date maxDate = cal.getTime();

                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 2);
                Date fromDate = cal.getTime();

                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 3);
                Date toDate = cal.getTime();

                ArrayList<Date> disabledDates = new ArrayList<Date>();
                for (int i = 5; i < 8; i++) {
                    cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, i);
                    disabledDates.add(cal.getTime());
                }

                caldroidFragment.setMinDate(minDate);
                caldroidFragment.setMaxDate(maxDate);
                caldroidFragment.setDisableDates(disabledDates);
                caldroidFragment.setSelectedDates(fromDate, toDate);
                caldroidFragment.setShowNavigationArrows(false);
                caldroidFragment.setEnableSwipe(false);

                caldroidFragment.refreshView();

                String text = "Today: " + formatter.format(new Date()) + "\n";
                text += "Min Date: " + formatter.format(minDate) + "\n";
                text += "Max Date: " + formatter.format(maxDate) + "\n";
                text += "Select From Date: " + formatter.format(fromDate)
                        + "\n";
                text += "Select To Date: " + formatter.format(toDate) + "\n";
                for (Date date : disabledDates) {
                    text += "Disabled Date: " + formatter.format(date) + "\n";
                }

                textView.setText(text);
            }
        });

        Button showDialogButton = (Button) findViewById(R.id.show_dialog_button);

        final Bundle state = savedInstanceState;
        showDialogButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogCaldroidFragment = new CaldroidFragment();
                dialogCaldroidFragment.setCaldroidListener(listener);

                final String dialogTag = "CALDROID_DIALOG_FRAGMENT";
                if (state != null) {
                    dialogCaldroidFragment.restoreDialogStatesFromKey(
                            getSupportFragmentManager(), state,
                            "DIALOG_CALDROID_SAVED_STATE", dialogTag);
                    Bundle args = dialogCaldroidFragment.getArguments();
                    if (args == null) {
                        args = new Bundle();
                        dialogCaldroidFragment.setArguments(args);
                    }
                } else {
                    Bundle bundle = new Bundle();
                    dialogCaldroidFragment.setArguments(bundle);
                }

                dialogCaldroidFragment.show(getSupportFragmentManager(),
                        dialogTag);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);

        if (caldroidFragment != null) {
            caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
        }

        if (dialogCaldroidFragment != null) {
            dialogCaldroidFragment.saveStatesToKey(outState,
                    "DIALOG_CALDROID_SAVED_STATE");
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences shared = getSharedPreferences("info",MODE_PRIVATE);
        finishflag = shared.getInt("finishflag", Integer.parseInt("0"));
        if(finishflag == 0){
            SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("finishflag",1);;
            editor.apply();

            setCustomResourceForDates();

        }

        finishflagedit = shared.getInt("finishflagedit", Integer.parseInt("0"));
        if(finishflagedit == 1){
            SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("finishflagedit",0);;
            editor.apply();

            setCustomResourceForDates();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (R.id.menu_duedate == item.getItemId())
        {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if (R.id.menu_about == item.getItemId())
        {
            Intent intent = new Intent(getBaseContext(), About.class);
            startActivity(intent);
            return true;
        }
        else if(R.id.menu_changepass == item.getItemId()){
            Intent intent = new Intent(getBaseContext(), PasswordActivity.class);
            startActivity(intent);
            return true;
        }
        else if(R.id.menu_em == item.getItemId()){
            Intent intent = new Intent(getBaseContext(), CallActivity.class);
            startActivity(intent);
            return true;
        }else if(R.id.menu_danger == item.getItemId()){
            Intent intent = new Intent(getBaseContext(), DangerSigns.class);
            startActivity(intent);
            return true;
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }
    }

}

