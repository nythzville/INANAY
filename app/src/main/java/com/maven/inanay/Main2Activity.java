package com.maven.inanay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static com.maven.inanay.R.color.pinnk;


public class Main2Activity extends AppCompatActivity {
    private int year_x, month_x, day_x;
    private int mYear, mMonth, mDay;
    private int mmYear, mmMonth, mmDay;
    private Date date1, date2, date3;
    private String CurrentDate, FinalDate, DelDate, tri;
    private int x = 1, wk, a = 1;
    final Calendar c = Calendar.getInstance();
    private int nutri_tips_arr[] = {3,4,6,7,8,9,10,11,14,15,16,18,19,20,22,23,24,26,27,28,31,34,36};
    private String babysize[] = {
            "----",
            "----",
            "----",
            "Asin",
            "Lunga",
            "Butil Sang Mais",
            "Bugnay",
            "Bangkiling",
            "Lumboy",
            "Cherisa",
            "Batwan",
            "Limon",
            "Tambis",
            "Bayabas",
            "Mansanas",
            "Avocado",
            "Chico",
            "Katumbal Nga Dalagko",
            "Mangosteen ",
            "Mais",
            "Guyabano",
            "Gamay Nga Talong",
            "Dako Nga Mangga",
            "Papaya",
            "Pomelo",
            "Talong",
            "Cauliflower",
            "Gamay Nga Kalabasa",
            "Gamay Nga Kalabasa",
            "Isa Ka Sipi Sang Saging",
            "Buko",
            "Repolyo",
            "Pinya",
            "Melon",
            "Melon",
            "Isa Ka Punpong Sang Ubas",
            "Isa Ka Bugkos Sang Pechay",
            "Isa Ka Bugkos Sang Pechay",
            "Dako Nga Kalabasa",
            "Sandiya",
            "Dako Nga Sandiya",
            "Dako Nga Sandiya"
    }, babynutri[] = {
            "Dapat tandaan, healthy nanay, healthy baby",
            "Matilawan sang baby sa matris ang mga ginakaon sang nanay",
            "Folic acid (Mani, Avocado, Brocolli, Alugbati, Okra, Calamansi, Dalandan)",
            "Bitamina B6 (Saging, Karne, Manok, Alugbati, Kamote)",
            "Indi gid mag-inom sang mga makahulubog nga ilimnon",
            "Kaon ka sang preska nga utan",
            "Magkaon kada duwa ka oras sang gagmay nga masustansya nga pagkaon, inom man sang damo nga tubig",
            "Calcium ( Gatas, Malunggay )",
            "Bitamina E (Alugbati, Avocado, Kamote, Kalabasa)",
            "Protina (Gatas, Karne, Manok, Isda, Mani, Itlog) ",
            "Bitamina B1 (Kan-on, Itlog, Mani, Gatas, Baboy, Atay)",
            "Ma-obserbahan mo nga ang imo suso/titi maga sugod sa pag-wwa sng gatas ",
            "Pagkatapos sang bente uno ka semana gaporma ang una nga pagtae sang baby, ukon ang ginatawag nga Meconium. Pero maggwa lang ni sa baby pagtapos sang pagbata sa iya",
            "Dapat mag inom gid sang gatas",
            "Dapat magkaon sang Itlog",
            "Omega 3 (Isda, Itlog, Preska nga utan)",
            "Otomatik ang pag-gwa sang gatas sa titi sang gabusong nanay kung may mabatian siya nga gahibi nga bata, bisan indi iya ang bata nga na",
            "Bitamina C (Calamansi, Guava, Brocolli, Orange, Red bell pepper)",
            "Bitamina A (Atay sang karne, Karot, Kamote, Alugbati, Itlog, Brocolli) ",
            "Kaon gid sang sakto nga kantidad sang itlog, avocado ka mani",
            "Indi mo mabatian kung kis'a kay gahibi ang bata sa sulod sng matris",
            "Iron (Pula nga karne, Manok, Preska nga utan)",
            "I-dyutayon ang pag gamit sang asin sa pag preparar sang pagkaon",
            "Bitamina B2 (Manok, Isda, Mushrooms, Gatas, Itlog)",
            "Kung gabusong ang nanay, gadako man ang tiil kag tagipusuon upod sa matris sang nanay",
            "Bitamina D (Pagbutlak sang adlaw, Itlog, Gatas, Isda)",
            "Iron (Para ind magka-anemia) ",
            "Inom permi sang gatas",
            "Nakaporma na ang fingerprints sang bata sa unanga tatlo ka bulan sang pagbusong",
            "Sa ikaduwa nga trimester, gaihi ang baby sa sulod matris. Ginainom nila ang ihi kag nagaihi sila liwat",
            "Kaon permi sang isda para iwas goiter",
            "Dako ang chansa nga galain ang kolor sang panit sang nanay kung gabusong siya",
            "Mas gamay ang oxygen sa mga gabusong nga nanay, kag amo ni ang rason ngaa naga kalipaton sila",
            "Bitamina K (Tinapay kag preska nga utan)",
            "Sa matrisgGadevelopar ang tanan nga itlog sang bayi",
            "Mani, Isda, Oatmeal, Tsokolate ",
            "gakabatyag man sang simptomas ang tatay kung gabusong ang nanay",
            "Kung taas ka ukon overweight, mas dako ang chansa nga magbata ka sang kapid ukon triplets",
            "Pirme gakadugo ang ilong kag ngipon sang nanay nga gabusong kag gadasig ang pagdaloy sang iya dugo",
            "Ang pagkalam sang titi sang bayi lang gid ang masaligan nga pamaagi para mabal-an nga manug bata aa ang nanay",
            "Kung ga heart burn ang nanay, mas dako ang chansa nga puno sang buhok ang ulo sang iya baby",
            "Gaunat ang matris sang bayi kung gakalam iya clitoris"

    };

    TextView txtpreg, txtduedate, txtdetails, bbsize, otherdetails, nutritipsview;
    ImageView img;
    Button button3, button4, tips;
    ProgressBar progbar1;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtpreg = (TextView)findViewById(R.id.txtpreg);
        txtduedate = (TextView)findViewById(R.id.txtduedate);
        txtdetails = (TextView)findViewById(R.id.txtdetails);
        bbsize = (TextView)findViewById(R.id.bbsize);
        otherdetails = (TextView)findViewById(R.id.otherdetails);
        img = (ImageView)findViewById(R.id.img);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        progbar1 = (ProgressBar)findViewById(R.id.progressBar2);
        tips = (Button)findViewById(R.id.tips);
        nutritipsview = (TextView)findViewById(R.id.textView13);

        initialconditions();


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initialconditions() {
        SharedPreferences shared = getSharedPreferences("info",MODE_PRIVATE);
        mYear = shared.getInt("mYear", Integer.parseInt("0"));
        mMonth = shared.getInt("mMonth", Integer.parseInt("0"));
        mDay = shared.getInt("mDay", Integer.parseInt("0"));
        year_x = shared.getInt("year_x", Integer.parseInt("0"));
        month_x = shared.getInt("month_x", Integer.parseInt("0"));
        day_x = shared.getInt("day_x", Integer.parseInt("0"));

        mmYear = c.get(Calendar.YEAR);
        mmMonth = c.get(Calendar.MONTH);
        mmDay = c.get(Calendar.DAY_OF_MONTH);

        CurrentDate = (mmMonth + 1) + "/" + mmDay + "/" + mmYear;
        FinalDate = (mMonth + 1) + "/" + mDay + "/" + mYear;
        DelDate = (month_x + 1) + "/" + day_x + "/" + year_x;
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
        try {
            date3 = dates.parse(DelDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long difference = Math.abs(date1.getTime() - date2.getTime());
        long differenceDates = difference / (24 * 60 * 60 * 1000 * 7);

        if(differenceDates == 1){

            txtpreg.setText("You Are 1 Week Pregnant");
        }
        else if(differenceDates == 2){

            txtpreg.setText("You Are 2 Weeks Pregnant");
        }
        else if(differenceDates == 3){

            txtpreg.setText("You Are 3 Weeks Pregnant");
        }
        else if(differenceDates == 4){

            txtpreg.setText("You Are 1 Month");
        }
        else if(differenceDates == 5){

            txtpreg.setText("You Are 1 Month and 1 Week Pregnant");
        }
        else if(differenceDates == 6){

            txtpreg.setText("You Are 1 Month and 2 Weeks Pregnant");
        }
        else if(differenceDates == 7){

            txtpreg.setText("You Are 1 Month and 3 Weeks Pregnant");
        }
        else if(differenceDates == 8){

            txtpreg.setText("You Are 1 Month and 3 Weeks Pregnant");
        }
        else if(differenceDates == 9){

            txtpreg.setText("You Are 2 Months");
        }
        else if(differenceDates == 10){

            txtpreg.setText("You Are 2 Months and 1 Week Pregnant");
        }
        else if(differenceDates == 11){

            txtpreg.setText("You Are 2 Months and 2 Weeks Pregnant");
        }
        else if(differenceDates == 12){

            txtpreg.setText("You Are 2 Months and 3 Weeks Pregnant");
        }
        else if(differenceDates == 13){

            txtpreg.setText("You Are 3 Months");
        }
        else if(differenceDates == 14){

            txtpreg.setText("You Are 3 Months and 1 Week Pregnant");
        }
        else if(differenceDates == 15){

            txtpreg.setText("You Are 3 Months and 2 Weeks Pregnant");
        }
        else if(differenceDates == 16){

            txtpreg.setText("You Are 3 Months and 3 Weeks Pregnant");
        }
        else if(differenceDates == 17){

            txtpreg.setText("You Are 3 Months and 4 Weeks Pregnant");
        }
        else if(differenceDates == 18){

            txtpreg.setText("You Are 4 Months");
        }
        else if(differenceDates == 19){

            txtpreg.setText("You Are 4 Months and 1 Week Pregnant");
        }
        else if(differenceDates == 20){

            txtpreg.setText("You Are 4 Months and 2 Weeks Pregnant");
        }
        else if(differenceDates == 21){

            txtpreg.setText("You Are 4 Months and 3 Weeks Pregnant");
        }
        else if(differenceDates == 22){

            txtpreg.setText("You Are 5 Months");
        }
        else if(differenceDates == 23){

            txtpreg.setText("You Are 5 Months and 1 Week Pregnant");
        }
        else if(differenceDates == 24){

            txtpreg.setText("You Are 5 Months and 2 Weeks Pregnant");
        }
        else if(differenceDates == 25){

            txtpreg.setText("You Are 5 Months and 3 Weeks Pregnant");
        }
        else if(differenceDates == 26){

            txtpreg.setText("You Are 6 Months");
        }
        else if(differenceDates == 27){

            txtpreg.setText("You Are 6 Months and 1 Week Pregnant");
        }
        else if(differenceDates == 28){

            txtpreg.setText("You Are 6 Months and 2 Weeks Pregnant");
        }
        else if(differenceDates == 29){

            txtpreg.setText("You Are 6 Months and 3 Weeks Pregnant");
        }
        else if(differenceDates == 30){

            txtpreg.setText("You Are 6 Months and 4 Weeks Pregnant");
        }
        else if(differenceDates == 31){

            txtpreg.setText("You Are 7 Months");
        }
        else if(differenceDates == 32){

            txtpreg.setText("You Are 7 Months and 1 Week Pregnant");
        }
        else if(differenceDates == 33){

            txtpreg.setText("You Are 7 Months and 2 Weeks Pregnant");
        }
        else if(differenceDates == 34){

            txtpreg.setText("You Are 7 Months and 3 Weeks Pregnant");
        }
        else if(differenceDates == 35){

            txtpreg.setText("You Are 8 Months");
        }
        else if(differenceDates == 36){

            txtpreg.setText("You Are 8 Months and 1 Week Pregnant");
        }
        else if(differenceDates == 37){

            txtpreg.setText("You Are 8 Months and 2 Weeks Pregnant");
        }
        else if(differenceDates == 38){

            txtpreg.setText("You Are 8 Months and 3 Weeks Pregnant");
        }
        else if(differenceDates == 39){

            txtpreg.setText("You Are 9 Months");
        }
        else if(differenceDates == 40){

            txtpreg.setText("You Are 9 Months and 1 Week Pregnant");
        }
        else if(differenceDates == 41){

            txtpreg.setText("You Are 9 Months and 2 Weeks Pregnant");
        }
        else if(differenceDates == 42){

            txtpreg.setText("You Are 9 Months and 3 Weeks Pregnant");
        }

        if(x == 1){
            wk = (int) differenceDates;
            progbar1.setProgress(wk);
            x = 2;
        }

        long daysleft = (40 - wk) * 7;

        if(wk <= 13){
            tri = "1st Trimester";
        }
        else if(wk >= 14 && wk <= 27){
            tri = "2nd Trimester";
        }
        else if(wk >= 28){
            tri = "3rd Trimester";
        }

        if (wk == 1) {
            txtdetails.setText("" + wk + " Week\n" + tri + "\n" + daysleft + " Days Left");
        }
        else if(wk >= 40){
            txtdetails.setText("" + wk + " Weeks\n" + tri + "\n" + "Few Days Left");
        } else {
            txtdetails.setText("" + wk + " Weeks\n" + tri + "\n" + daysleft + " Days Left");
        }

        if(month_x == 0){
            txtduedate.setText("Mabata ka sa\n" + "January " + day_x + ", " + year_x);
        }
        else if(month_x == 1){
            txtduedate.setText("Mabata ka sa\n" + "February " + day_x + ", " + year_x);
        }
        else if(month_x == 2){
            txtduedate.setText("Mabata ka sa\n" + "March " + day_x + ", " + year_x);
        }
        else if(month_x == 3){
            txtduedate.setText("Mabata ka sa\n" + "April " + day_x + ", " + year_x);
        }
        else if(month_x == 4){
            txtduedate.setText("Mabata ka sa\n" + "May " + day_x + ", " + year_x);
        }
        else if(month_x == 5){
            txtduedate.setText("Mabata ka sa\n" + "June " + day_x + ", " + year_x);
        }
        else if(month_x == 6){
            txtduedate.setText("Mabata ka sa\n" + "July " + day_x + ", " + year_x);

        }
        else if(month_x == 7){
            txtduedate.setText("Mabata ka sa\n" + "August " + day_x + ", " + year_x);

        }
        else if(month_x == 8){
            txtduedate.setText("Mabata ka sa\n" + "September " + day_x + ", " + year_x);

        }
        else if(month_x == 9){
            txtduedate.setText("Mabata ka sa\n" + "October " + day_x + ", " + year_x);

        }
        else if(month_x == 10){
            txtduedate.setText("Mabata ka sa\n" + "November " + day_x + ", " + year_x);

        }
        else if(month_x == 11){
            txtduedate.setText("Mabata ka sa\n" + "December " + day_x + ", " + year_x);

        }

        bbsize.setText("Ang size sang Bata kay daw " + babysize[wk - 1] + "\n");
        otherdetails.setText("" + babynutri[wk - 1]);

        if(wk == 1){
            img.setImageResource(R.drawable.picture1);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);
        }
        else if(wk == 2){
            img.setImageResource(R.drawable.picture2);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);
        }
        else if(wk == 3){
            img.setImageResource(R.drawable.picture3);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 4){
            img.setImageResource(R.drawable.picture4);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 5){
            img.setImageResource(R.drawable.picture5);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);
        }
        else if(wk == 6){
            img.setImageResource(R.drawable.picture6);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 7){
            img.setImageResource(R.drawable.picture7);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 8){
            img.setImageResource(R.drawable.picture8);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 9){
            img.setImageResource(R.drawable.picture9);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 10){
            img.setImageResource(R.drawable.picture10);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 11){
            img.setImageResource(R.drawable.picture11);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 12){
            img.setImageResource(R.drawable.picture12);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);
        }
        else if(wk == 13){
            img.setImageResource(R.drawable.picture13);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);
        }
        else if(wk == 14){
            img.setImageResource(R.drawable.picture14);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 15){
            img.setImageResource(R.drawable.picture15);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 16){
            img.setImageResource(R.drawable.picture16);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 17){
            img.setImageResource(R.drawable.picture17);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);
        }
        else if(wk == 18){
            img.setImageResource(R.drawable.picture18);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 19){
            img.setImageResource(R.drawable.picture19);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 20){
            img.setImageResource(R.drawable.picture20);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 21){
            img.setImageResource(R.drawable.picture21);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);
        }
        else if(wk == 22){
            img.setImageResource(R.drawable.picture22);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 23){
            img.setImageResource(R.drawable.picture23);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 24){
            img.setImageResource(R.drawable.picture24);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 25){
            img.setImageResource(R.drawable.picture25);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);
        }
        else if(wk == 26){
            img.setImageResource(R.drawable.picture26);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 27){
            img.setImageResource(R.drawable.picture27);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 28){
            img.setImageResource(R.drawable.picture28);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 29){
            img.setImageResource(R.drawable.picture29);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);
        }
        else if(wk == 30){
            img.setImageResource(R.drawable.picture30);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);
        }
        else if(wk == 31){
            img.setImageResource(R.drawable.picture31);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 32){
            img.setImageResource(R.drawable.picture32);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);
        }
        else if(wk == 33){
            img.setImageResource(R.drawable.picture33);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);
        }
        else if(wk == 34){
            img.setImageResource(R.drawable.picture34);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);
        }
        else if(wk == 35){
            img.setImageResource(R.drawable.picture35);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);
        }
        else if(wk == 36){
            img.setImageResource(R.drawable.picture36);
            a = 1;
            nutritipsview.setText("Nutrition Tips");
            nutritipsview.setBackgroundResource(R.drawable.nutrition_tips);

        }
        else if(wk == 37){
            img.setImageResource(R.drawable.picture37);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);

        }
        else if(wk == 38){
            img.setImageResource(R.drawable.picture38);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);

        }
        else if(wk == 39){
            img.setImageResource(R.drawable.picture39);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);

        }
        else if(wk == 40){
            img.setImageResource(R.drawable.picture40);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);

        }
        else if(wk == 41){
            img.setImageResource(R.drawable.picture41);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);

        }
        else if(wk == 42){
            img.setImageResource(R.drawable.picture42);
            a = 1;
            nutritipsview.setText("Health Facts");
            nutritipsview.setBackgroundResource(R.drawable.health_facts);

        }

        // Check week if content is Nutrition tips or Health Facts

    }

    public void onClickNext(View view){
        if(wk == 41){
            button4.setEnabled(false);
            button3.setEnabled(true);
        }
        else if(wk >= 1 && wk <= 42) {
            button3.setEnabled(true);
            button4.setEnabled(true);
        }
        wk++;
        progbar1.setProgress(wk);
        initialconditions();
    }

    public void onClickPrev(View view){
        if(wk == 2){
            button3.setEnabled(false);
            button4.setEnabled(true);
        }
        else if(wk >= 1 && wk <= 42) {
            button3.setEnabled(true);
            button4.setEnabled(true);
        }
        wk--;
        progbar1.setProgress(wk);
        initialconditions();
    }

    @Override
    public void onResume(){
        super.onResume();
        x = 1;
        initialconditions();
    }

    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    public void tip(View view){

        if(a == 1) {
            stopPlaying();
            a = 0;
        }

        if(a == 0) {
            if (wk == 1) {
                mp = MediaPlayer.create(this, R.raw.wk1);

            } else if (wk == 2) {
                mp = MediaPlayer.create(this, R.raw.wk2);

            } else if (wk == 3) {
                mp = MediaPlayer.create(this, R.raw.wk3);

            } else if (wk == 4) {
                mp = MediaPlayer.create(this, R.raw.wk4);

            } else if (wk == 5) {
                mp = MediaPlayer.create(this, R.raw.wk5);

            } else if (wk == 6) {
                mp = MediaPlayer.create(this, R.raw.wk6);

            } else if (wk == 7) {
                mp = MediaPlayer.create(this, R.raw.wk7);

            } else if (wk == 8) {
                mp = MediaPlayer.create(this, R.raw.wk8);

            } else if (wk == 9) {
                mp = MediaPlayer.create(this, R.raw.wk9);

            } else if (wk == 10) {
                mp = MediaPlayer.create(this, R.raw.wk10);

            } else if (wk == 11) {
                mp = MediaPlayer.create(this, R.raw.wk11);

            } else if (wk == 12) {
                mp = MediaPlayer.create(this, R.raw.wk12);

            } else if (wk == 13) {
                mp = MediaPlayer.create(this, R.raw.wk13);

            } else if (wk == 14) {
                mp = MediaPlayer.create(this, R.raw.wk14);

            } else if (wk == 15) {
                mp = MediaPlayer.create(this, R.raw.wk15);

            } else if (wk == 16) {
                mp = MediaPlayer.create(this, R.raw.wk16);

            } else if (wk == 17) {
                mp = MediaPlayer.create(this, R.raw.wk17);

            } else if (wk == 18) {
                mp = MediaPlayer.create(this, R.raw.wk18);

            } else if (wk == 19) {
                mp = MediaPlayer.create(this, R.raw.wk19);

            } else if (wk == 20) {
                mp = MediaPlayer.create(this, R.raw.wk20);

            } else if (wk == 21) {
                mp = MediaPlayer.create(this, R.raw.wk21);

            } else if (wk == 22) {
                mp = MediaPlayer.create(this, R.raw.wk22);

            } else if (wk == 23) {
                mp = MediaPlayer.create(this, R.raw.wk23);

            } else if (wk == 24) {
                mp = MediaPlayer.create(this, R.raw.wk24);

            } else if (wk == 25) {
                mp = MediaPlayer.create(this, R.raw.wk25);

            } else if (wk == 26) {
                mp = MediaPlayer.create(this, R.raw.wk26);

            } else if (wk == 27) {
                mp = MediaPlayer.create(this, R.raw.wk27);

            } else if (wk == 28) {
                mp = MediaPlayer.create(this, R.raw.wk28);

            } else if (wk == 29) {
                mp = MediaPlayer.create(this, R.raw.wk29);

            } else if (wk == 30) {
                mp = MediaPlayer.create(this, R.raw.wk30);

            } else if (wk == 31) {
                mp = MediaPlayer.create(this, R.raw.wk31);

            } else if (wk == 32) {
                mp = MediaPlayer.create(this, R.raw.wk32);

            } else if (wk == 33) {
                mp = MediaPlayer.create(this, R.raw.wk33);

            } else if (wk == 34) {
                mp = MediaPlayer.create(this, R.raw.wk34);

            } else if (wk == 35) {
                mp = MediaPlayer.create(this, R.raw.wk35);

            } else if (wk == 36) {
                mp = MediaPlayer.create(this, R.raw.wk36);

            } else if (wk == 37) {
                mp = MediaPlayer.create(this, R.raw.wk37);

            } else if (wk == 38) {
                mp = MediaPlayer.create(this, R.raw.wk38);

            } else if (wk == 39) {
                mp = MediaPlayer.create(this, R.raw.wk39);

            } else if (wk == 40) {
                mp = MediaPlayer.create(this, R.raw.wk40);

            } else if (wk == 41) {
                mp = MediaPlayer.create(this, R.raw.wk41);

            } else if (wk == 42) {
                mp = MediaPlayer.create(this, R.raw.wk42);
            }
        }


        if(mp.isPlaying()){
            mp.stop();
            a = 0;
        }
        else if(!mp.isPlaying()) {
            mp.start();
            a = 2;
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
