package com.maven.inanay;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import hirondelle.date4j.DateTime;


public class CaldroidSampleCustomAdapter extends CaldroidGridAdapter {
    private int mYear, mMonth, mDay;

    public CaldroidSampleCustomAdapter(Context context, int month, int year,
                                       Map<String, Object> caldroidData,
                                       Map<String, Object> extraData) {
        super(context, month, year, caldroidData, extraData);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cellView = convertView;

        if (convertView == null) {
            cellView = inflater.inflate(R.layout.custom_cell, null);
        }

        int topPadding = cellView.getPaddingTop();
        int leftPadding = cellView.getPaddingLeft();
        int bottomPadding = cellView.getPaddingBottom();
        int rightPadding = cellView.getPaddingRight();

        TextView tv1 = (TextView) cellView.findViewById(R.id.tv1);
        TextView tv2 = (TextView) cellView.findViewById(R.id.tv2);

        tv1.setTextColor(Color.BLACK);

        DateTime dateTime = this.datetimeList.get(position);
        Resources resources = context.getResources();

        if (dateTime.getMonth() != month) {
            tv1.setTextColor(resources
                    .getColor(com.caldroid.R.color.caldroid_darker_gray));
        }

        boolean shouldResetDiabledView = false;
        boolean shouldResetSelectedView = false;

        if ((minDateTime != null && dateTime.lt(minDateTime))
                || (maxDateTime != null && dateTime.gt(maxDateTime))
                || (disableDates != null && disableDates.indexOf(dateTime) != -1)) {

            tv1.setTextColor(CaldroidFragment.disabledTextColor);
            if (CaldroidFragment.disabledBackgroundDrawable == -1) {
                cellView.setBackgroundResource(com.caldroid.R.drawable.disable_cell);
            } else {
                cellView.setBackgroundResource(CaldroidFragment.disabledBackgroundDrawable);
            }

            if (dateTime.equals(getToday())) {
                cellView.setBackgroundResource(com.caldroid.R.drawable.red_border_gray_bg);
            }

        } else {
            shouldResetDiabledView = true;
        }

        if (selectedDates != null && selectedDates.indexOf(dateTime) != -1) {
            cellView.setBackgroundColor(resources
                    .getColor(com.caldroid.R.color.caldroid_sky_blue));

            tv1.setTextColor(Color.BLACK);

        } else {
            shouldResetSelectedView = true;
        }

        if (shouldResetDiabledView && shouldResetSelectedView) {
            if (dateTime.equals(getToday())) {
                cellView.setBackgroundResource(com.caldroid.R.drawable.red_border);
            } else {
                cellView.setBackgroundResource(com.caldroid.R.drawable.cell_bg);
            }
        }

        mYear = (Integer) extraData.get("mYear");
        mMonth = (Integer) extraData.get("mMonth");
        mDay = (Integer) extraData.get("mDay");

        int nYear1 = (Integer) extraData.get("nYear1");
        int nMonth1 = (Integer) extraData.get("nMonth1");
        int nDay1 = (Integer) extraData.get("nDay1");

        int nYear2 = (Integer) extraData.get("nYear2");
        int nMonth2 = (Integer) extraData.get("nMonth2");
        int nDay2 = (Integer) extraData.get("nDay2");

        int nYear3 = (Integer) extraData.get("nYear3");
        int nMonth3 = (Integer) extraData.get("nMonth3");
        int nDay3 = (Integer) extraData.get("nDay3");

        int nYear4 = (Integer) extraData.get("nYear4");
        int nMonth4 = (Integer) extraData.get("nMonth4");
        int nDay4 = (Integer) extraData.get("nDay4");

        int nYear5 = (Integer) extraData.get("nYear5");
        int nMonth5 = (Integer) extraData.get("nMonth5");
        int nDay5 = (Integer) extraData.get("nDay5");

        int nYear6 = (Integer) extraData.get("nYear6");
        int nMonth6 = (Integer) extraData.get("nMonth6");
        int nDay6 = (Integer) extraData.get("nDay6");

        int nYear7 = (Integer) extraData.get("nYear7");
        int nMonth7 = (Integer) extraData.get("nMonth7");
        int nDay7 = (Integer) extraData.get("nDay7");

        int nYear8 = (Integer) extraData.get("nYear8");
        int nMonth8 = (Integer) extraData.get("nMonth8");
        int nDay8 = (Integer) extraData.get("nDay8");

        int nYear9 = (Integer) extraData.get("nYear9");
        int nMonth9 = (Integer) extraData.get("nMonth9");
        int nDay9 = (Integer) extraData.get("nDay9");

        int nYear10 = (Integer) extraData.get("nYear10");
        int nMonth10 = (Integer) extraData.get("nMonth10");
        int nDay10 = (Integer) extraData.get("nDay10");

        int nYear11 = (Integer) extraData.get("nYear11");
        int nMonth11 = (Integer) extraData.get("nMonth11");
        int nDay11 = (Integer) extraData.get("nDay11");

        int nYear12 = (Integer) extraData.get("nYear12");
        int nMonth12 = (Integer) extraData.get("nMonth12");
        int nDay12 = (Integer) extraData.get("nDay12");

        int nYear13 = (Integer) extraData.get("nYear13");
        int nMonth13 = (Integer) extraData.get("nMonth13");
        int nDay13 = (Integer) extraData.get("nDay13");

        int nYear14 = (Integer) extraData.get("nYear14");
        int nMonth14 = (Integer) extraData.get("nMonth14");
        int nDay14 = (Integer) extraData.get("nDay14");

        int nYear15 = (Integer) extraData.get("nYear15");
        int nMonth15 = (Integer) extraData.get("nMonth15");
        int nDay15 = (Integer) extraData.get("nDay15");

        int nYear16 = (Integer) extraData.get("nYear16");
        int nMonth16 = (Integer) extraData.get("nMonth16");
        int nDay16 = (Integer) extraData.get("nDay16");

        int nYear17 = (Integer) extraData.get("nYear17");
        int nMonth17 = (Integer) extraData.get("nMonth17");
        int nDay17 = (Integer) extraData.get("nDay17");

        int nYear18 = (Integer) extraData.get("nYear18");
        int nMonth18 = (Integer) extraData.get("nMonth18");
        int nDay18 = (Integer) extraData.get("nDay18");

        int nYear19 = (Integer) extraData.get("nYear19");
        int nMonth19 = (Integer) extraData.get("nMonth19");
        int nDay19 = (Integer) extraData.get("nDay19");

        int nYear20 = (Integer) extraData.get("nYear20");
        int nMonth20 = (Integer) extraData.get("nMonth20");
        int nDay20 = (Integer) extraData.get("nDay20");

        int nYear21 = (Integer) extraData.get("nYear21");
        int nMonth21 = (Integer) extraData.get("nMonth21");
        int nDay21 = (Integer) extraData.get("nDay21");

        int nYear22 = (Integer) extraData.get("nYear22");
        int nMonth22 = (Integer) extraData.get("nMonth22");
        int nDay22 = (Integer) extraData.get("nDay22");

        int nYear23 = (Integer) extraData.get("nYear23");
        int nMonth23 = (Integer) extraData.get("nMonth23");
        int nDay23 = (Integer) extraData.get("nDay23");

        int nYear24 = (Integer) extraData.get("nYear24");
        int nMonth24 = (Integer) extraData.get("nMonth24");
        int nDay24 = (Integer) extraData.get("nDay24");

        int nYear25 = (Integer) extraData.get("nYear25");
        int nMonth25 = (Integer) extraData.get("nMonth25");
        int nDay25 = (Integer) extraData.get("nDay25");

        int nYear26 = (Integer) extraData.get("nYear26");
        int nMonth26 = (Integer) extraData.get("nMonth26");
        int nDay26 = (Integer) extraData.get("nDay26");

        int nYear27 = (Integer) extraData.get("nYear27");
        int nMonth27 = (Integer) extraData.get("nMonth27");
        int nDay27 = (Integer) extraData.get("nDay27");

        int nYear28 = (Integer) extraData.get("nYear28");
        int nMonth28 = (Integer) extraData.get("nMonth28");
        int nDay28 = (Integer) extraData.get("nDay28");

        int nYear29 = (Integer) extraData.get("nYear29");
        int nMonth29 = (Integer) extraData.get("nMonth29");
        int nDay29 = (Integer) extraData.get("nDay29");

        int nYear30 = (Integer) extraData.get("nYear30");
        int nMonth30 = (Integer) extraData.get("nMonth30");
        int nDay30 = (Integer) extraData.get("nDay30");

        int nYear31 = (Integer) extraData.get("nYear31");
        int nMonth31 = (Integer) extraData.get("nMonth31");
        int nDay31 = (Integer) extraData.get("nDay31");

        int nYear32 = (Integer) extraData.get("nYear32");
        int nMonth32 = (Integer) extraData.get("nMonth32");
        int nDay32 = (Integer) extraData.get("nDay32");

        int nYear33 = (Integer) extraData.get("nYear33");
        int nMonth33 = (Integer) extraData.get("nMonth33");
        int nDay33 = (Integer) extraData.get("nDay33");

        int nYear34 = (Integer) extraData.get("nYear34");
        int nMonth34 = (Integer) extraData.get("nMonth34");
        int nDay34 = (Integer) extraData.get("nDay34");

        int nYear35 = (Integer) extraData.get("nYear35");
        int nMonth35 = (Integer) extraData.get("nMonth35");
        int nDay35 = (Integer) extraData.get("nDay35");

        int nYear36 = (Integer) extraData.get("nYear36");
        int nMonth36 = (Integer) extraData.get("nMonth36");
        int nDay36 = (Integer) extraData.get("nDay36");

        int nYear37 = (Integer) extraData.get("nYear37");
        int nMonth37 = (Integer) extraData.get("nMonth37");
        int nDay37 = (Integer) extraData.get("nDay37");

        int nYear38 = (Integer) extraData.get("nYear38");
        int nMonth38 = (Integer) extraData.get("nMonth38");
        int nDay38 = (Integer) extraData.get("nDay38");

        int nYear39 = (Integer) extraData.get("nYear39");
        int nMonth39 = (Integer) extraData.get("nMonth39");
        int nDay39 = (Integer) extraData.get("nDay39");

        int nYear40 = (Integer) extraData.get("nYear40");
        int nMonth40 = (Integer) extraData.get("nMonth40");
        int nDay40 = (Integer) extraData.get("nDay40");

        int nYear41 = (Integer) extraData.get("nYear41");
        int nMonth41 = (Integer) extraData.get("nMonth41");
        int nDay41 = (Integer) extraData.get("nDay41");

        int nYear42 = (Integer) extraData.get("nYear42");
        int nMonth42 = (Integer) extraData.get("nMonth42");
        int nDay42 = (Integer) extraData.get("nDay42");


        if(dateTime.getYear() == mYear && dateTime.getMonth() == (mMonth + 1) && dateTime.getDay() == mDay){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("0");
        }
        else if(dateTime.getYear() == nYear1 && dateTime.getMonth() == (nMonth1 + 1) && dateTime.getDay() == nDay1){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("1");
        }
        else if(dateTime.getYear() == nYear2 && dateTime.getMonth() == (nMonth2 + 1) && dateTime.getDay() == nDay2){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("2");
        }
        else if(dateTime.getYear() == nYear3 && dateTime.getMonth() == (nMonth3 + 1) && dateTime.getDay() == nDay3){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("3");
        }
        else if(dateTime.getYear() == nYear4 && dateTime.getMonth() == (nMonth4 + 1) && dateTime.getDay() == nDay4){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("4");
        }
        else if(dateTime.getYear() == nYear5 && dateTime.getMonth() == (nMonth5 + 1) && dateTime.getDay() == nDay5){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("5");
        }
        else if(dateTime.getYear() == nYear6 && dateTime.getMonth() == (nMonth6 + 1) && dateTime.getDay() == nDay6){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("6");
        }
        else if(dateTime.getYear() == nYear7 && dateTime.getMonth() == (nMonth7 + 1) && dateTime.getDay() == nDay7){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("7");
        }
        else if(dateTime.getYear() == nYear8 && dateTime.getMonth() == (nMonth8 + 1) && dateTime.getDay() == nDay8){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("8");
        }
        else if(dateTime.getYear() == nYear9 && dateTime.getMonth() == (nMonth9 + 1) && dateTime.getDay() == nDay9){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("9");
        }
        else if(dateTime.getYear() == nYear10 && dateTime.getMonth() == (nMonth10 + 1) && dateTime.getDay() == nDay10){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("10");
        }
        else if(dateTime.getYear() == nYear11 && dateTime.getMonth() == (nMonth11 + 1) && dateTime.getDay() == nDay11){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("11");
        }
        else if(dateTime.getYear() == nYear12 && dateTime.getMonth() == (nMonth12 + 1) && dateTime.getDay() == nDay12){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("12");
        }
        else if(dateTime.getYear() == nYear13 && dateTime.getMonth() == (nMonth13 + 1) && dateTime.getDay() == nDay13){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("13");
        }
        else if(dateTime.getYear() == nYear14 && dateTime.getMonth() == (nMonth14 + 1) && dateTime.getDay() == nDay14){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("14");
        }
        else if(dateTime.getYear() == nYear15 && dateTime.getMonth() == (nMonth15 + 1) && dateTime.getDay() == nDay15){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("15");
        }
        else if(dateTime.getYear() == nYear16 && dateTime.getMonth() == (nMonth16 + 1) && dateTime.getDay() == nDay16){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("16");
        }
        else if(dateTime.getYear() == nYear17 && dateTime.getMonth() == (nMonth17 + 1) && dateTime.getDay() == nDay17){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("17");
        }
        else if(dateTime.getYear() == nYear18 && dateTime.getMonth() == (nMonth18 + 1) && dateTime.getDay() == nDay18){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("18");
        }
        else if(dateTime.getYear() == nYear19 && dateTime.getMonth() == (nMonth19 + 1) && dateTime.getDay() == nDay19){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("19");
        }
        else if(dateTime.getYear() == nYear20 && dateTime.getMonth() == (nMonth20 + 1) && dateTime.getDay() == nDay20){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("20");
        }
        else if(dateTime.getYear() == nYear21 && dateTime.getMonth() == (nMonth21 + 1) && dateTime.getDay() == nDay21){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("21");
        }
        else if(dateTime.getYear() == nYear22 && dateTime.getMonth() == (nMonth22 + 1) && dateTime.getDay() == nDay22){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("22");
        }
        else if(dateTime.getYear() == nYear23 && dateTime.getMonth() == (nMonth23 + 1) && dateTime.getDay() == nDay23){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("23");
        }
        else if(dateTime.getYear() == nYear24 && dateTime.getMonth() == (nMonth24 + 1) && dateTime.getDay() == nDay24){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("24");
        }
        else if(dateTime.getYear() == nYear25 && dateTime.getMonth() == (nMonth25 + 1) && dateTime.getDay() == nDay25){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("25");
        }
        else if(dateTime.getYear() == nYear26 && dateTime.getMonth() == (nMonth26 + 1) && dateTime.getDay() == nDay26){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("26");
        }
        else if(dateTime.getYear() == nYear27 && dateTime.getMonth() == (nMonth27 + 1) && dateTime.getDay() == nDay27){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("27");
        }
        else if(dateTime.getYear() == nYear28 && dateTime.getMonth() == (nMonth28 + 1) && dateTime.getDay() == nDay28){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("28");
        }
        else if(dateTime.getYear() == nYear29 && dateTime.getMonth() == (nMonth29 + 1) && dateTime.getDay() == nDay29){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("29");
        }
        else if(dateTime.getYear() == nYear30 && dateTime.getMonth() == (nMonth30 + 1) && dateTime.getDay() == nDay30){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("30");
        }
        else if(dateTime.getYear() == nYear31 && dateTime.getMonth() == (nMonth31 + 1) && dateTime.getDay() == nDay31){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("31");
        }
        else if(dateTime.getYear() == nYear32 && dateTime.getMonth() == (nMonth32 + 1) && dateTime.getDay() == nDay32){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("32");
        }
        else if(dateTime.getYear() == nYear33 && dateTime.getMonth() == (nMonth33 + 1) && dateTime.getDay() == nDay33){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("33");
        }
        else if(dateTime.getYear() == nYear34 && dateTime.getMonth() == (nMonth34 + 1) && dateTime.getDay() == nDay34){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("34");
        }
        else if(dateTime.getYear() == nYear35 && dateTime.getMonth() == (nMonth35 + 1) && dateTime.getDay() == nDay35){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("35");
        }
        else if(dateTime.getYear() == nYear36 && dateTime.getMonth() == (nMonth36 + 1) && dateTime.getDay() == nDay36){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("36");
        }
        else if(dateTime.getYear() == nYear37 && dateTime.getMonth() == (nMonth37 + 1) && dateTime.getDay() == nDay37){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("37");
        }
        else if(dateTime.getYear() == nYear38 && dateTime.getMonth() == (nMonth38 + 1) && dateTime.getDay() == nDay38){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("38");
        }
        else if(dateTime.getYear() == nYear39 && dateTime.getMonth() == (nMonth39 + 1) && dateTime.getDay() == nDay39){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("39");
        }
        else if(dateTime.getYear() == nYear40 && dateTime.getMonth() == (nMonth40 + 1) && dateTime.getDay() == nDay40){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("40");
        }
        else if(dateTime.getYear() == nYear41 && dateTime.getMonth() == (nMonth41 + 1) && dateTime.getDay() == nDay41){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("41");
        }
        else if(dateTime.getYear() == nYear42 && dateTime.getMonth() == (nMonth42 + 1) && dateTime.getDay() == nDay42){
            tv1.setText("" + dateTime.getDay());
            tv2.setText("42");
        }
        else {
            tv1.setText("" + dateTime.getDay());
            tv2.setText("");
        }

        if (dateTime.equals(getToday())) {
            cellView.setBackgroundResource(com.caldroid.R.drawable.red_border);
        } else {
            cellView.setBackgroundResource(com.caldroid.R.drawable.cell_bg);
        }

        cellView.setPadding(leftPadding, topPadding, rightPadding,
                bottomPadding);

        setCustomResources(dateTime, cellView, tv1);

        if (dateTime.equals(getToday())) {
            cellView.setBackgroundResource(com.caldroid.R.drawable.red_border);
        }

        return cellView;


    }
}
