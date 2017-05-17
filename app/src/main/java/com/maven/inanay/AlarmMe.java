package com.maven.inanay;


import java.lang.System;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Objects;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import android.app.AlarmManager;
import android.app.PendingIntent;


public class AlarmMe extends AppCompatActivity
{
    private final String TAG = "AlarmMe";

    private ListView mAlarmList;
    private AlarmListAdapter mAlarmListAdapter;
    private Alarm mCurrentAlarm;

    private final int NEW_ALARM_ACTIVITY = 0;
    private final int EDIT_ALARM_ACTIVITY = 1;
    private final int PREFERENCES_ACTIVITY = 2;
    private final int ABOUT_ACTIVITY = 3;

    private final int CONTEXT_MENU_EDIT = 0;
    private final int CONTEXT_MENU_DELETE = 1;
    private final int CONTEXT_MENU_DUPLICATE = 2;

    public String longdate;

    final Context context = this;

    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.main);

        Log.i(TAG, "AlarmMe.onCreate()");

        mAlarmList = (ListView)findViewById(R.id.alarm_list);

        mAlarmListAdapter = new AlarmListAdapter(this);
        mAlarmList.setAdapter(mAlarmListAdapter);
        mAlarmList.setOnItemClickListener(mListOnItemClickListener);
        registerForContextMenu(mAlarmList);

        mCurrentAlarm = null;

        reminders();
    }

    public void reminders(){
        int count = mAlarmListAdapter.getCount();

        if(count > 0) {

            longdate = "";

            for (int i = 0; i < count; i++) {
                long abc = mAlarmListAdapter.getItem(i).getDate();
                longdate += "" + abc + "/";
            }

            SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("calreminders", longdate);
            editor.putInt("calremnum", count);
            editor.apply();

        } else {
            longdate = "";

            SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("calreminders", longdate);
            editor.putInt("calremnum", 0);
            editor.apply();
        }

    }


    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i(TAG, "AlarmMe.onDestroy()");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.i(TAG, "AlarmMe.onResume()");
        mAlarmListAdapter.updateAlarms();

        reminders();
    }

    public void onAddAlarmClick(View view)
    {

        /* Dialog box to enter password before adding reminder */
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom);
        dialog.setTitle("Enter Password");

        final EditText password = (EditText) dialog.findViewById(R.id.password) ;

        final Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                String passwordstr = password.getText().toString();
                SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
                String passwordmain = shared.getString("password", "0");


                if(Objects.equals(passwordstr, passwordmain)) {
                    dialog.dismiss();

                    Intent intent = new Intent(getBaseContext(), EditAlarm.class);

                    mCurrentAlarm = new Alarm(AlarmMe.this);
                    mCurrentAlarm.toIntent(intent);

                    AlarmMe.this.startActivityForResult(intent, NEW_ALARM_ACTIVITY);


                } else {
                    Toast.makeText(AlarmMe.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button dialogButton2 = (Button) dialog.findViewById(R.id.dialogButtonCAN);
        dialogButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == NEW_ALARM_ACTIVITY)
        {
            if (resultCode == RESULT_OK)
            {
                mCurrentAlarm.fromIntent(data);
                mAlarmListAdapter.add(mCurrentAlarm);
            }
            mCurrentAlarm = null;
        }
        else if (requestCode == EDIT_ALARM_ACTIVITY)
        {
            if (resultCode == RESULT_OK)
            {
                mCurrentAlarm.fromIntent(data);
                mAlarmListAdapter.update(mCurrentAlarm);
            }
            mCurrentAlarm = null;
        }
        else if (requestCode == PREFERENCES_ACTIVITY)
        {
            mAlarmListAdapter.onSettingsUpdated();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (R.id.menu_settings == item.getItemId())
        {
            Intent intent = new Intent(getBaseContext(), Preferences.class);
            startActivityForResult(intent, PREFERENCES_ACTIVITY);
            return true;
        }
        else if (R.id.menu_about == item.getItemId())
        {
            Intent intent = new Intent(getBaseContext(), About.class);
            startActivity(intent);
            return true;
        }
        else if (R.id.menu_duedate == item.getItemId())
        {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {
        if (v.getId() == R.id.alarm_list)
        {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;

            menu.setHeaderTitle(mAlarmListAdapter.getItem(info.position).getTitle());

            menu.add(Menu.NONE, CONTEXT_MENU_EDIT, Menu.NONE, "Edit");
            menu.add(Menu.NONE, CONTEXT_MENU_DELETE, Menu.NONE, "Delete");
            menu.add(Menu.NONE, CONTEXT_MENU_DUPLICATE, Menu.NONE, "Duplicate");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int index = item.getItemId();

        if (index == CONTEXT_MENU_EDIT)
        {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom);
            dialog.setTitle("Enter Password");

            final EditText password = (EditText) dialog.findViewById(R.id.password) ;

            final Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {

                    String passwordstr = password.getText().toString();
                    SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
                    String passwordmain = shared.getString("password", "0");


                    if(Objects.equals(passwordstr, passwordmain)) {
                        dialog.dismiss();

                        Intent intent = new Intent(getBaseContext(), EditAlarm.class);
                        mCurrentAlarm = mAlarmListAdapter.getItem(info.position);
                        mCurrentAlarm.toIntent(intent);
                        startActivityForResult(intent, EDIT_ALARM_ACTIVITY);

                    } else {
                        Toast.makeText(AlarmMe.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                    }

                }
            });

            Button dialogButton2 = (Button) dialog.findViewById(R.id.dialogButtonCAN);
            dialogButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();

                }
            });

            dialog.show();
        }
        else if (index == CONTEXT_MENU_DELETE)
        {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom);
            dialog.setTitle("Enter Password");

            final EditText password = (EditText) dialog.findViewById(R.id.password) ;

            final Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {

                    String passwordstr = password.getText().toString();
                    SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
                    String passwordmain = shared.getString("password", "0");


                    if(Objects.equals(passwordstr, passwordmain)) {
                        dialog.dismiss();

                        mAlarmListAdapter.delete(info.position);

                        reminders();

                        SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor = pref.edit();
                        editor.putInt("finishflagedit", 1);
                        editor.apply();

                    } else {
                        Toast.makeText(AlarmMe.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                    }

                }
            });

            Button dialogButton2 = (Button) dialog.findViewById(R.id.dialogButtonCAN);
            dialogButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();

                }
            });

            dialog.show();

        }
        else if (index == CONTEXT_MENU_DUPLICATE)
        {
            Alarm alarm = mAlarmListAdapter.getItem(info.position);
            Alarm newAlarm = new Alarm(this);
            Intent intent = new Intent();

            alarm.toIntent(intent);
            newAlarm.fromIntent(intent);
            newAlarm.setTitle(alarm.getTitle() + " (copy)");
            mAlarmListAdapter.add(newAlarm);

        }

        return true;
    }

    protected AdapterView.OnItemClickListener mListOnItemClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
        {

            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom);
            dialog.setTitle("Enter Password");

            final EditText password = (EditText) dialog.findViewById(R.id.password) ;

            final Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {

                    String passwordstr = password.getText().toString();
                    SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
                    String passwordmain = shared.getString("password", "0");


                    if(Objects.equals(passwordstr, passwordmain)) {
                        dialog.dismiss();

                        Intent intent = new Intent(getBaseContext(), EditAlarm.class);
                        mCurrentAlarm = mAlarmListAdapter.getItem(position);
                        mCurrentAlarm.toIntent(intent);
                        AlarmMe.this.startActivityForResult(intent, EDIT_ALARM_ACTIVITY);

                    } else {
                        Toast.makeText(AlarmMe.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                    }

                }
            });

            Button dialogButton2 = (Button) dialog.findViewById(R.id.dialogButtonCAN);
            dialogButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();

                }
            });

            dialog.show();
        }
    };

}
