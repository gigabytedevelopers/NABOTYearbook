package com.gigabytedevelopersinc.apps.botany.classofchamps18.receivers;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import androidx.core.app.NotificationCompat;
import android.util.Log;

import com.gigabytedevelopersinc.apps.botany.classofchamps18.R;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.activities.MainActivity;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.fragment.HomeNewsFragment;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.models.StudentsModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Created by Emmanuel Nwokoma (Founder and CEO at Gigabyte Developers) on 8/24/2018
 **/

public class AlarmReciever extends BroadcastReceiver {
    public static List<StudentsModel> listFull;
    AlarmManager alarmMgr;
    PendingIntent alarmIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        listFull = new ArrayList<>();
        if(Objects.requireNonNull(intent.getAction()).equalsIgnoreCase("android.intent.action.BOOT_COMPLETED")) {
            try {
                startBirthdayAlarmService();
                //fetchbirthday();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if(Objects.requireNonNull(intent.getAction()).equalsIgnoreCase("BirthdayReminder")) {
            try {
                fetchbirthday();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void fetchbirthday() throws  ParseException{
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd"+ " "+"MMMM");
        String todayString = formatter.format(todayDate);

        for (StudentsModel studentsModel : HomeNewsFragment.listFull){
            if (todayString.equals(studentsModel.getDob())){
                //code to generate notification

                Intent notificationIntent = new Intent(MainActivity.context, HomeNewsFragment.class);
                PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.context,
                        0, notificationIntent,
                        0);

                NotificationManager nm = (NotificationManager) MainActivity.context
                        .getSystemService(Context.NOTIFICATION_SERVICE);

                Resources res = MainActivity.context.getResources();
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.context);
                builder.setContentText("Someone in Class of Champions 2018 has a Birthday today")
                        .setTicker("New Message Alert")
                        .setWhen(System.currentTimeMillis())
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher_round))
                        .setContentIntent(contentIntent);
                Notification n = builder.getNotification();

                n.defaults |= Notification.DEFAULT_ALL;
                assert nm != null;
                nm.notify(0, n);
            }
        }
    }

    public void startBirthdayAlarmService() {
        alarmMgr = (AlarmManager)MainActivity.context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(MainActivity.context, AlarmReciever.class);
        intent.setAction("BirthdayReminder");
        alarmIntent = PendingIntent.getBroadcast(MainActivity.context, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);

        //code for triggering alarm
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Log.d("Saurav","Alarm triggering time is:"+Calendar.HOUR_OF_DAY+":"+Calendar.MINUTE);
    }

}
