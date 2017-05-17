package com.maven.inanay;


import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.net.Uri;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class AlarmNotification extends AppCompatActivity
{
    private final String TAG = "AlarmMe";

    private Vibrator mVibrator;
    private final long[] mVibratePattern = { 0, 500, 500 };
    private boolean mVibrate;
    private long mPlayTime;
    private Timer mTimer = null;
    private Alarm mAlarm;
    private DateTime mDateTime;
    private TextView mTextView;
    private PlayTimerTask mTimerTask;

    final Context context = this;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        setContentView(R.layout.notification);

        mDateTime = new DateTime(this);
        mTextView = (TextView)findViewById(R.id.alarm_title_text);

        readPreferences();

        if (mVibrate)
            mVibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        start(getIntent());

        mp = MediaPlayer.create(this, R.raw.alarmsound);
        mp.start();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.i(TAG, "AlarmNotification.onDestroy()");

        addNotification(mAlarm);

        stop();
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        Log.i(TAG, "AlarmNotification.onNewIntent()");

        addNotification(mAlarm);

        stop();
        start(intent);
    }

    private void start(Intent intent)
    {
        mAlarm = new Alarm(this);
        mAlarm.fromIntent(intent);

        Log.i(TAG, "AlarmNotification.start('" + mAlarm.getTitle() + "')");

        mTextView.setText(mAlarm.getTitle());

        mTimerTask = new PlayTimerTask();
        mTimer = new Timer();
        mTimer.schedule(mTimerTask, mPlayTime);
        if (mVibrate) {
            mVibrator.vibrate(mVibratePattern, 0);
        }
    }

    private void stop()
    {
        Log.i(TAG, "AlarmNotification.stop()");

        mTimer.cancel();
        mp.stop();
        if (mVibrate) {
            mVibrator.cancel();
        }
    }

    public void onDismissClick(View view)
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

                    finish();

                } else {
                    Toast.makeText(AlarmNotification.this,"Wrong Password",Toast.LENGTH_SHORT).show();
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

    private void readPreferences()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        mVibrate = prefs.getBoolean("vibrate_pref", true);
        mPlayTime = (long) Integer.parseInt(prefs.getString("alarm_play_time_pref", "180")) * 1000;
    }

    private void addNotification(Alarm alarm)
    {
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification;
        PendingIntent activity;
        Intent intent;

        Log.i(TAG, "AlarmNotification.addNotification(" + alarm.getId() + ", '" + alarm.getTitle() + "', '" + mDateTime.formatDetails(alarm) + "')");

        intent = new Intent(this.getApplicationContext(), AlarmMe.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        activity = PendingIntent.getActivity(this, (int)alarm.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        notification = builder
                .setContentIntent(activity)
                .setSmallIcon(R.drawable.ic_notification)
                .setAutoCancel(true)
                .setContentTitle("iNanay: " + alarm.getTitle())
                .setContentText(mDateTime.formatDetails(alarm))
                .build();

        notificationManager.notify((int)alarm.getId(), notification);
    }


    @Override
    public void onBackPressed() {

    }

    @Override
    public void onPause(){
        super.onPause();
    }

    private class PlayTimerTask extends TimerTask
    {
        @Override
        public void run()
        {
            Log.i(TAG, "AlarmNotification.PalyTimerTask.run()");
            addNotification(mAlarm);
            finish();
        }
    }
}
