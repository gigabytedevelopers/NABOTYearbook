package com.gigabytedevelopersinc.apps.botany.classofchamps18.receivers;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import android.util.Log;
import android.view.View;

import com.gigabytedevelopersinc.apps.botany.classofchamps18.R;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.activities.MainActivity;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.fragment.HomeNewsFragment;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.models.StudentsModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Created by Emmanuel Nwokoma (Founder and CEO at Gigabyte Developers) on 8/24/2018
 **/
public final class NotificationServiceReceiver extends Service {

    private List<StudentsModel> listFull;
    AlarmManager alarmMgr;
    private String IMAGE_URL = "http://class-of-champions-2018.000webhostapp.com/students/";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        listFull = new ArrayList<>();
        fillList();
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd"+ " "+"MMMM", Locale.ENGLISH);
        String todayString = formatter.format(todayDate);

        for (StudentsModel studentsModel : listFull){
            if (todayString.equals(studentsModel.getDob())){
                //code for triggering alarm
                final Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, 10);
                calendar.set(Calendar.MINUTE, 00 );
                calendar.set(Calendar.SECOND, 00);
            } else {
                //code for triggering alarm
                final Calendar calendar = Calendar.getInstance();
                calendar.clear();
            }
        }
//        timer = new Timer();
//        timer.schedule(timerTask,3000,3*1000);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            timerTask.run();
        } catch (Exception e){
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);

    }

    private Timer timer;
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            notifiyNoti();
        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            timer.cancel();
            timerTask.cancel();
        }catch (Exception e){
            e.printStackTrace();
        }

        Intent intent = new Intent("com.gigabytedevelopersinc.apps.botany.classofchamps18");
        intent.putExtra("myvalue", "torestore");
        sendBroadcast(intent);

    }

    public void notifiyNoti(){
        alarmMgr = (AlarmManager) MainActivity.context.getSystemService(Context.ALARM_SERVICE);
        IntentFilter filter = new IntentFilter();
        filter.addAction("RSSPullService");

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.gigabytedevelopersinc.apps.botany.classofchamps18", "com.gigabytedevelopersinc.apps.botany.classofchamps18.fragment.HomeNewsFragment");
        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);
        Context context = getApplicationContext();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Resources res = context.getResources();
        Notification notification = builder.setContentTitle("Birthday Notification")
                .setContentText("Someone in Class of Champions 2018 has a Birthday today!")
                .setTicker("New Message Alert")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher_round))
                .setContentIntent(pendingIntent).build();

        notification.defaults |= Notification.DEFAULT_ALL;

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.notify(1, notification);
    }

    private void fillList(){
        StudentsModel studentsModel = new StudentsModel(
                getString(R.string.name_2014574329_miriam),
                getString(R.string.quote_2014574329_miriam),
                IMAGE_URL + getString(R.string.no_2014574329) + ".jpg",
                getString(R.string.phone_2014574329_miriam),
                getString(R.string.day_16) + " " + getString(R.string.oct),
                getString(R.string.email_2014574329_miriam),
                getString(R.string.lga_2014574329_miriam),
                getString(R.string.state_Imo),
                getString(R.string.nick_2014574329_miriam));
        listFull.add(studentsModel);
        StudentsModel studentsModel1 = new StudentsModel(
                getString(R.string.name_2014574330_lawrence),
                getString(R.string.quote_2014574330_lawrence),
                IMAGE_URL + getString(R.string.no_2014574330) + ".jpg",
                getString(R.string.phone_2014574330_lawrence),
                getString(R.string.day_3) + " " + getString(R.string.jun),
                getString(R.string.email_2014574330_lawrence),
                getString(R.string.lga_2014574330_lawrence),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574330_lawrence));
        listFull.add(studentsModel1);
        StudentsModel studentsModel2 = new StudentsModel(getString(R.string.name_2014574331_prisca),
                getString(R.string.quote_2014574331_prisca),
                IMAGE_URL + getString(R.string.no_2014574331) + ".jpg",
                getString(R.string.phone_2014574331_prisca),
                getString(R.string.day_22) + " " + getString(R.string.jul),
                getString(R.string.email_2014574331_prisca),
                getString(R.string.lga_2014574331_prisca),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574331_prisca));
        listFull.add(studentsModel2);
        StudentsModel studentsModel3 = new StudentsModel(getString(R.string.name_2014574332_doris),
                getString(R.string.quote_2014574332_doris),
                IMAGE_URL + getString(R.string.no_2014574332) + ".jpg",
                getString(R.string.phone_2014574332_doris),
                getString(R.string.day_9) + " " + getString(R.string.jul),
                getString(R.string.email_2014574332_doris),
                getString(R.string.lga_2014574332_doris),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574332_doris));
        listFull.add(studentsModel3);
        StudentsModel studentsModel4 = new StudentsModel(getString(R.string.name_2014574333_francess),
                getString(R.string.quote_2014574333_francess),
                IMAGE_URL + getString(R.string.no_2014574333) + ".jpg",
                getString(R.string.phone_2014574333_francess),
                getString(R.string.day_8) + " " + getString(R.string.apr),
                getString(R.string.email_2014574333_francess),
                getString(R.string.lga_2014574333_francess),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574333_francess));
        listFull.add(studentsModel4);
        StudentsModel studentsModel5 = new StudentsModel(getString(R.string.name_2014574335_christian),
                getString(R.string.quote_2014574335_christian),
                IMAGE_URL + getString(R.string.no_2014574335) + ".jpg",
                getString(R.string.phone_2014574335_christian),
                getString(R.string.day_29) + " " + getString(R.string.may),
                getString(R.string.email_2014574335_christian),
                getString(R.string.lga_2014574335_christian),
                getString(R.string.state_unknown),
                getString(R.string.nick_2014574335_christian));
        listFull.add(studentsModel5);
        StudentsModel studentsModel6 = new StudentsModel(getString(R.string.name_2014574336_chioma),
                getString(R.string.quote_2014574336_chioma),
                IMAGE_URL + getString(R.string.no_2014574336) + ".jpg",
                getString(R.string.phone_2014574336_chioma),
                getString(R.string.day_21) + " " + getString(R.string.sept),
                getString(R.string.email_2014574336_chioma),
                getString(R.string.lga_2014574336_chioma),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574336_chioma));
        listFull.add(studentsModel6);
        StudentsModel studentsModel7 = new StudentsModel(getString(R.string.name_2014574337_favour),
                getString(R.string.quote_2014574337_favour),
                IMAGE_URL + getString(R.string.no_2014574337) + ".jpg",
                getString(R.string.phone_2014574337_favour),
                getString(R.string.day_27) + " " + getString(R.string.nov),
                getString(R.string.email_2014574337_favour),
                getString(R.string.lga_2014574337_favour),
                getString(R.string.state_Rivers),
                getString(R.string.nick_2014574337_favour));
        listFull.add(studentsModel7);
        StudentsModel studentsModel8 = new StudentsModel(getString(R.string.name_2014574338_kenechi),
                getString(R.string.quote_2014574338_kenechi),
                IMAGE_URL + getString(R.string.no_2014574338) + ".jpg",
                getString(R.string.phone_2014574338_kenechi),
                getString(R.string.day_13) + " " + getString(R.string.apr),
                getString(R.string.email_2014574338_kenechi),
                getString(R.string.lga_2014574338_kenechi),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574338_kenechi));
        listFull.add(studentsModel8);
        StudentsModel studentsModel9 = new StudentsModel(getString(R.string.name_2014574339_joshua),
                getString(R.string.quote_2014574339_joshua),
                IMAGE_URL + getString(R.string.no_2014574339) + ".jpg",
                getString(R.string.phone_2014574339_joshua),
                getString(R.string.day_27) + " " + getString(R.string.may),
                getString(R.string.email_2014574339_joshua),
                getString(R.string.lga_2014574339_joshua),
                getString(R.string.state_Abia),
                getString(R.string.nick_2014574339_joshua));
        listFull.add(studentsModel9);
        StudentsModel studentsModel10 = new StudentsModel(getString(R.string.name_2014574340_chioma_rita),
                getString(R.string.quote_2014574340_chioma_rita),
                IMAGE_URL + getString(R.string.no_2014574340) + ".jpg",
                getString(R.string.phone_2014574340_chioma_rita),
                getString(R.string.day_20) + " " + getString(R.string.sept),
                getString(R.string.email_2014574340_chioma_rita),
                getString(R.string.lga_2014574340_chioma_rita),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574340_chioma_rita));
        listFull.add(studentsModel10);
        StudentsModel studentsModel11 = new StudentsModel(getString(R.string.name_2014574342_makuochukwu),
                getString(R.string.quote_2014574342_makuochukwu),
                IMAGE_URL + getString(R.string.no_2014574342) + ".jpg",
                getString(R.string.phone_2014574342_makuochukwu),
                getString(R.string.day_4) + " " + getString(R.string.jan),
                getString(R.string.email_2014574342_makuochukwu),
                getString(R.string.lga_2014574342_makuochukwu),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574342_makuochukwu));
        listFull.add(studentsModel11);
        StudentsModel studentsModel12 = new StudentsModel(getString(R.string.name_2014574343_lilian),
                getString(R.string.quote_2014574343_lilian),
                IMAGE_URL + getString(R.string.no_2014574343) + ".jpg",
                getString(R.string.phone_2014574343_lilian),
                getString(R.string.day_9) + " " + getString(R.string.may),
                getString(R.string.email_2014574343_lilian),
                getString(R.string.lga_2014574343_lilian),
                getString(R.string.state_Imo),
                getString(R.string.nick_2014574343_lilian));
        listFull.add(studentsModel12);
        StudentsModel studentsModel13 = new StudentsModel(getString(R.string.name_2014574344_collins),
                getString(R.string.quote_2014574344_collins),
                IMAGE_URL + getString(R.string.no_2014574344) + ".jpg",
                getString(R.string.phone_2014574344_collins),
                getString(R.string.day_24) + " " + getString(R.string.oct),
                getString(R.string.email_2014574344_collins),
                getString(R.string.lga_2014574344_collins),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574344_collins));
        listFull.add(studentsModel13);
        StudentsModel studentsModel14 = new StudentsModel(getString(R.string.name_2014574345_obianuju),
                getString(R.string.quote_2014574345_obianuju),
                IMAGE_URL + getString(R.string.no_2014574345) + ".jpg",
                getString(R.string.phone_2014574345_obianuju),
                getString(R.string.day_24) + " " + getString(R.string.jan),
                getString(R.string.email_2014574345_obianuju),
                getString(R.string.lga_2014574345_obianuju),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574345_obianuju));
        listFull.add(studentsModel14);
        StudentsModel studentsModel15 = new StudentsModel(getString(R.string.name_2014574346_goodness),
                getString(R.string.quote_2014574346_goodness),
                IMAGE_URL + getString(R.string.no_2014574346) + ".jpg",
                getString(R.string.phone_2014574346_goodness),
                getString(R.string.day_22) + " " + getString(R.string.jun),
                getString(R.string.email_2014574346_goodness),
                getString(R.string.lga_2014574346_goodness),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574346_goodness));
        listFull.add(studentsModel15);
        StudentsModel studentsModel16 = new StudentsModel(getString(R.string.name_2014574347_ikechukwu),
                getString(R.string.quote_2014574347_ikechukwu),
                IMAGE_URL + getString(R.string.no_2014574347) + ".jpg",
                getString(R.string.phone_2014574347_ikechukwu),
                getString(R.string.day_29) + " " + getString(R.string.nov),
                getString(R.string.email_2014574347_ikechukwu),
                getString(R.string.lga_2014574347_ikechukwu),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574347_ikechukwu));
        listFull.add(studentsModel16);
        StudentsModel studentsModel17 = new StudentsModel(getString(R.string.name_2014574348_precious),
                getString(R.string.quote_2014574348_precious),
                IMAGE_URL + getString(R.string.no_2014574348) + ".jpg",
                getString(R.string.phone_2014574348_precious),
                getString(R.string.day_20) + " " + getString(R.string.may),
                getString(R.string.email_2014574348_precious),
                getString(R.string.lga_2014574348_precious),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574348_precious));
        listFull.add(studentsModel17);
        StudentsModel studentsModel18 = new StudentsModel(getString(R.string.name_2014574350_charity),
                getString(R.string.quote_2014574350_charity),
                IMAGE_URL + getString(R.string.no_2014574350) + ".jpg",
                getString(R.string.phone_2014574350_charity),
                getString(R.string.day_27) + " " + getString(R.string.apr),
                getString(R.string.email_2014574350_charity),
                getString(R.string.lga_2014574350_charity),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574350_charity));
        listFull.add(studentsModel18);
        StudentsModel studentsModel19 = new StudentsModel(getString(R.string.name_2014574352_kingsley),
                getString(R.string.quote_2014574352_kingsley),
                IMAGE_URL + getString(R.string.no_2014574352) + ".jpg",
                getString(R.string.phone_2014574352_kingsley),
                getString(R.string.day_6) + " " + getString(R.string.jan),
                getString(R.string.email_2014574352_kingsley),
                getString(R.string.lga_2014574352_kingsley),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574352_kingsley));
        listFull.add(studentsModel19);
        StudentsModel studentsModel20 = new StudentsModel(
                getString(R.string.name_2014574354_augustine),
                getString(R.string.quote_2014574354_augustine),
                IMAGE_URL + getString(R.string.no_2014574354) + ".jpg",
                getString(R.string.phone_2014574354_augustine),
                getString(R.string.day_12) + " " + getString(R.string.aug),
                getString(R.string.email_2014574354_augustine),
                getString(R.string.lga_2014574354_augustine),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574354_augustine));
        listFull.add(studentsModel20);
        StudentsModel studentsModel21 = new StudentsModel(getString(R.string.name_2014574355_judith),
                getString(R.string.quote_2014574355_judith),
                IMAGE_URL + getString(R.string.no_2014574355) + ".jpg",
                getString(R.string.phone_2014574355_judith),
                getString(R.string.day_17) + " " + getString(R.string.apr),
                getString(R.string.email_2014574355_judith),
                getString(R.string.lga_2014574355_judith),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574355_judith));
        listFull.add(studentsModel21);
        StudentsModel studentsModel22 = new StudentsModel(getString(R.string.name_2014574356_onyinyechi),
                getString(R.string.quote_2014574356_onyinyechi),
                IMAGE_URL + getString(R.string.no_2014574356) + ".jpg",
                getString(R.string.phone_2014574356_onyinyechi),
                getString(R.string.day_13) + " " + getString(R.string.oct),
                getString(R.string.email_2014574356_onyinyechi),
                getString(R.string.lga_2014574356_onyinyechi),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574356_onyinyechi));
        listFull.add(studentsModel22);
        StudentsModel studentsModel243 = new StudentsModel(getString(R.string.name_2014574357_emeka),
                getString(R.string.quote_2014574357_emeka),
                IMAGE_URL + getString(R.string.no_2014574357) + ".jpg",
                getString(R.string.phone_2014574357_emeka),
                getString(R.string.day_8) + " " + getString(R.string.aug),
                getString(R.string.email_2014574357_emeka),
                getString(R.string.lga_2014574357_emeka),
                getString(R.string.state_Imo),
                getString(R.string.nick_2014574357_emeka));
        listFull.add(studentsModel243);
        StudentsModel studentsModel24 = new StudentsModel(getString(R.string.name_2014574358_kelvin),
                getString(R.string.quote_2014574358_kelvin),
                IMAGE_URL + getString(R.string.no_2014574358) + ".jpg",
                getString(R.string.phone_2014574358_kelvin),
                getString(R.string.day_26) + " " + getString(R.string.apr),
                getString(R.string.email_2014574358_kelvin),
                getString(R.string.lga_2014574358_kelvin),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574358_kelvin));
        listFull.add(studentsModel24);
        StudentsModel studentsModel25 = new StudentsModel(getString(R.string.name_2014574359_ndidi),
                getString(R.string.quote_2014574359_ndidi),
                IMAGE_URL + getString(R.string.no_2014574359) + ".jpg",
                getString(R.string.phone_2014574359_ndidi),
                getString(R.string.day_14) + " " + getString(R.string.oct),
                getString(R.string.email_2014574359_ndidi),
                getString(R.string.lga_2014574359_ndidi),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574359_ndidi));
        listFull.add(studentsModel25);
        StudentsModel studentsModel26 = new StudentsModel(getString(R.string.name_2014574360_blessing),
                getString(R.string.quote_2014574360_blessing),
                IMAGE_URL + getString(R.string.no_2014574360) + ".jpg",
                getString(R.string.phone_2014574360_blessing),
                getString(R.string.day_3) + " " + getString(R.string.may),
                getString(R.string.email_2014574360_blessing),
                getString(R.string.lga_2014574360_blessing),
                getString(R.string.state_Abia),
                getString(R.string.nick_2014574360_blessing));
        listFull.add(studentsModel26);
        StudentsModel studentsModel27 = new StudentsModel(getString(R.string.name_2014574361_chidiebere),
                getString(R.string.quote_2014574361_chidiebere),
                IMAGE_URL + getString(R.string.no_2014574361) + ".jpg",
                getString(R.string.phone_2014574361_chidiebere),
                getString(R.string.day_30) + " " + getString(R.string.may),
                getString(R.string.email_2014574361_chidiebere),
                getString(R.string.lga_2014574361_chidiebere),
                getString(R.string.state_Imo),
                getString(R.string.nick_2014574361_chidiebere));
        listFull.add(studentsModel27);
        StudentsModel studentsModel28 = new StudentsModel(getString(R.string.name_2014574363_adaobi),
                getString(R.string.quote_2014574363_adaobi),
                IMAGE_URL + getString(R.string.no_2014574363) + ".jpg",
                getString(R.string.phone_2014574363_adaobi),
                getString(R.string.day_17) + " " + getString(R.string.mar),
                getString(R.string.email_2014574363_adaobi),
                getString(R.string.lga_2014574363_adaobi),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574363_adaobi));
        listFull.add(studentsModel28);
        StudentsModel studentsModel29 = new StudentsModel(getString(R.string.name_2014574364_amaobi),
                getString(R.string.quote_2014574364_amaobi),
                IMAGE_URL + getString(R.string.no_2014574364) + ".jpg",
                getString(R.string.phone_2014574364_amaobi),
                getString(R.string.day_16) + " " + getString(R.string.apr),
                getString(R.string.email_2014574364_amaobi),
                getString(R.string.lga_2014574364_amaobi),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574364_amaobi));
        listFull.add(studentsModel29);
        StudentsModel studentsModel30 = new StudentsModel(getString(R.string.name_2014574365_augustina),
                getString(R.string.quote_2014574365_augustina),
                IMAGE_URL + getString(R.string.no_2014574365) + ".jpg",
                getString(R.string.phone_2014574365_augustina),
                getString(R.string.day_31) + " " + getString(R.string.aug),
                getString(R.string.email_2014574365_augustina),
                getString(R.string.lga_2014574365_augustina),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574365_augustina));
        listFull.add(studentsModel30);
        StudentsModel studentsModel31 = new StudentsModel(getString(R.string.name_2014574366_cynthia),
                getString(R.string.quote_2014574366_cynthia),
                IMAGE_URL + getString(R.string.no_2014574366) + ".jpg",
                getString(R.string.phone_2014574366_cynthia),
                getString(R.string.day_4) + " " + getString(R.string.feb),
                getString(R.string.email_2014574366_cynthia),
                getString(R.string.lga_2014574366_cynthia),
                getString(R.string.state_Delta),
                getString(R.string.nick_2014574366_cynthia));
        listFull.add(studentsModel31);
        StudentsModel studentsModel32 = new StudentsModel(getString(R.string.name_2014574367_ifechukwu),
                getString(R.string.quote_2014574367_ifechukwu),
                IMAGE_URL + getString(R.string.no_2014574367) + ".jpg",
                getString(R.string.phone_2014574367_ifechukwu),
                getString(R.string.day_1) + " " + getString(R.string.apr),
                getString(R.string.email_2014574367_ifechukwu),
                getString(R.string.lga_2014574367_ifechukwu),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574367_ifechukwu));
        listFull.add(studentsModel32);
        StudentsModel studentsModel33 = new StudentsModel(getString(R.string.name_2014574368_chiamaka),
                getString(R.string.quote_2014574368_chiamaka),
                IMAGE_URL + getString(R.string.no_2014574368) + ".jpg",
                getString(R.string.phone_2014574368_chiamaka),
                getString(R.string.day_2) + " " + getString(R.string.oct),
                getString(R.string.email_2014574368_chiamaka),
                getString(R.string.lga_2014574368_chiamaka),
                getString(R.string.state_Delta),
                getString(R.string.nick_2014574368_chiamaka));
        listFull.add(studentsModel33);
        StudentsModel studentsModel34 = new StudentsModel(getString(R.string.name_2014574369_emezie),
                getString(R.string.quote_2014574369_emezie),
                IMAGE_URL + getString(R.string.no_2014574369) + ".jpg",
                getString(R.string.phone_2014574369_emezie),
                getString(R.string.day_10) + " " + getString(R.string.nov),
                getString(R.string.email_2014574369_emezie),
                getString(R.string.lga_2014574369_emezie),
                getString(R.string.state_Imo),
                getString(R.string.nick_2014574369_emezie));
        listFull.add(studentsModel34);
        StudentsModel studentsModel35 = new StudentsModel(getString(R.string.name_2014574370_gigabyte),
                getString(R.string.quote_2014574370_gigabyte),
                IMAGE_URL + getString(R.string.no_2014574370) + ".jpg",
                getString(R.string.phone_2014574370_gigabyte),
                getString(R.string.day_27) + " " + getString(R.string.may),
                getString(R.string.email_2014574370_gigabyte),
                getString(R.string.lga_2014574370_gigabyte),
                getString(R.string.state_Abia),
                getString(R.string.nick_2014574370_gigabyte));
        listFull.add(studentsModel35);
        StudentsModel studentsModel36 = new StudentsModel(getString(R.string.name_2014574372_pauline),
                getString(R.string.quote_2014574372_pauline),
                IMAGE_URL + getString(R.string.no_2014574372) + ".jpg",
                getString(R.string.phone_2014574372_pauline),
                getString(R.string.day_31) + " " + getString(R.string.jul),
                getString(R.string.email_2014574372_pauline),
                getString(R.string.lga_2014574372_pauline),
                getString(R.string.state_Imo),
                getString(R.string.nick_2014574372_pauline));
        listFull.add(studentsModel36);
        StudentsModel studentsModel37 = new StudentsModel(getString(R.string.name_2014574373_ijeoma),
                getString(R.string.quote_2014574373_ijeoma),
                IMAGE_URL + getString(R.string.no_2014574373) + ".jpg",
                getString(R.string.phone_2014574373_ijeoma),
                getString(R.string.day_13) + " " + getString(R.string.jan),
                getString(R.string.email_2014574373_ijeoma),
                getString(R.string.lga_2014574373_ijeoma),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574373_ijeoma));
        listFull.add(studentsModel37);
        StudentsModel studentsModel38 = new StudentsModel(getString(R.string.name_2014574374_onyekachi),
                getString(R.string.quote_2014574374_onyekachi),
                IMAGE_URL + getString(R.string.no_2014574374) + ".jpg",
                getString(R.string.phone_2014574374_onyekachi),
                getString(R.string.day_25) + " " + getString(R.string.apr),
                getString(R.string.email_2014574374_onyekachi),
                getString(R.string.lga_2014574374_onyekachi),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574374_onyekachi));
        listFull.add(studentsModel38);
        StudentsModel studentsModel39 = new StudentsModel(getString(R.string.name_2014574375_vanessa),
                getString(R.string.quote_2014574375_vanessa),
                IMAGE_URL + getString(R.string.no_2014574375) + ".jpg",
                getString(R.string.phone_2014574375_vanessa),
                getString(R.string.day_7) + " " + getString(R.string.jan),
                getString(R.string.email_2014574375_vanessa),
                getString(R.string.lga_2014574375_vanessa),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574375_vanessa));
        listFull.add(studentsModel39);
        StudentsModel studentsModel40 = new StudentsModel(getString(R.string.name_2014574376_susan),
                getString(R.string.quote_2014574376_susan),
                IMAGE_URL + getString(R.string.no_2014574376) + ".jpg",
                getString(R.string.phone_2014574376_susan),
                getString(R.string.day_18) + " " + getString(R.string.mar),
                getString(R.string.email_2014574376_susan),
                getString(R.string.lga_2014574376_susan),
                getString(R.string.state_Edo),
                getString(R.string.nick_2014574376_susan));
        listFull.add(studentsModel40);
        StudentsModel studentsModel41 = new StudentsModel(getString(R.string.name_2014574377_rosemary),
                getString(R.string.quote_2014574377_rosemary),
                IMAGE_URL + getString(R.string.no_2014574377) + ".jpg",
                getString(R.string.phone_2014574377_rosemary),
                getString(R.string.day_19) + " " + getString(R.string.feb),
                getString(R.string.email_2014574377_rosemary),
                getString(R.string.lga_2014574377_rosemary),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574377_rosemary));
        listFull.add(studentsModel41);
        StudentsModel studentsModel42 = new StudentsModel(getString(R.string.name_2014574378_chukwusom),
                getString(R.string.quote_2014574378_chukwusom),
                IMAGE_URL + getString(R.string.no_2014574378) + ".jpg",
                getString(R.string.phone_2014574378_chukwusom),
                getString(R.string.date_unknown),
                getString(R.string.email_2014574378_chukwusom),
                getString(R.string.lga_2014574378_chukwusom),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574378_chukwusom));
        listFull.add(studentsModel42);
        StudentsModel studentsModel43 = new StudentsModel(getString(R.string.name_2014574379_friday),
                getString(R.string.quote_2014574379_friday),
                IMAGE_URL + getString(R.string.no_2014574379) + ".jpg",
                getString(R.string.phone_2014574379_friday),
                getString(R.string.day_22) + " " + getString(R.string.oct),
                getString(R.string.email_2014574379_friday),
                getString(R.string.lga_2014574379_friday),
                getString(R.string.state_Benue),
                getString(R.string.nick_2014574379_friday));
        listFull.add(studentsModel43);
        StudentsModel studentsModel44 = new StudentsModel(getString(R.string.name_2014574380_blessing),
                getString(R.string.quote_2014574380_blessing),
                IMAGE_URL + getString(R.string.no_2014574380) + ".jpg",
                getString(R.string.phone_2014574380_blessing),
                getString(R.string.day_27) + " " + getString(R.string.dec),
                getString(R.string.email_2014574380_blessing),
                getString(R.string.lga_2014574380_blessing),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574380_blessing));
        listFull.add(studentsModel44);
        StudentsModel studentsModel45 = new StudentsModel(getString(R.string.name_2014574383_chidiogo),
                getString(R.string.quote_2014574383_chidiogo),
                IMAGE_URL + getString(R.string.no_2014574383) + ".jpg",
                getString(R.string.phone_2014574383_chidiogo),
                getString(R.string.day_23) + " " + getString(R.string.mar),
                getString(R.string.email_2014574383_chidiogo),
                getString(R.string.lga_2014574383_chidiogo),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574383_chidiogo));
        listFull.add(studentsModel45);
        StudentsModel studentsModel46 = new StudentsModel(getString(R.string.name_2014574384_joyce),
                getString(R.string.quote_2014574384_joyce),
                IMAGE_URL + getString(R.string.no_2014574384) + ".jpg",
                getString(R.string.phone_2014574384_joyce),
                getString(R.string.day_18) + " " + getString(R.string.oct),
                getString(R.string.email_2014574384_joyce),
                getString(R.string.lga_2014574384_joyce),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574384_joyce));
        listFull.add(studentsModel46);
        StudentsModel studentsModel47 = new StudentsModel(getString(R.string.name_2014574385_peter),
                getString(R.string.quote_2014574385_peter),
                IMAGE_URL + getString(R.string.no_2014574385) + ".jpg",
                getString(R.string.phone_2014574385_peter),
                getString(R.string.day_26) + " " + getString(R.string.may),
                getString(R.string.email_2014574385_peter),
                getString(R.string.lga_2014574385_peter),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574385_peter));
        listFull.add(studentsModel47);
        StudentsModel studentsModel48 = new StudentsModel(getString(R.string.name_2014574386_daniel),
                getString(R.string.quote_2014574386_daniel),
                IMAGE_URL + getString(R.string.no_2014574386) + ".jpg",
                getString(R.string.phone_2014574386_daniel),
                getString(R.string.day_30) + " " + getString(R.string.sept),
                getString(R.string.email_2014574386_daniel),
                getString(R.string.lga_2014574386_daniel),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574386_daniel));
        listFull.add(studentsModel48);
        StudentsModel studentsModel49 = new StudentsModel(getString(R.string.name_2014574387_vincent),
                getString(R.string.quote_2014574387_vincent),
                IMAGE_URL + getString(R.string.no_2014574387) + ".jpg",
                getString(R.string.phone_2014574387_vincent),
                getString(R.string.day_16) + " " + getString(R.string.nov),
                getString(R.string.email_2014574387_vincent),
                getString(R.string.lga_2014574387_vincent),
                getString(R.string.state_Ebonyi),
                getString(R.string.nick_2014574387_vincent));
        listFull.add(studentsModel49);
        StudentsModel studentsModel50 = new StudentsModel(getString(R.string.name_2014574388_chinasa),
                getString(R.string.quote_2014574388_chinasa),
                IMAGE_URL + getString(R.string.no_2014574388) + ".jpg",
                getString(R.string.phone_2014574388_chinasa),
                getString(R.string.day_18) + " " + getString(R.string.jul),
                getString(R.string.email_2014574388_chinasa),
                getString(R.string.lga_2014574388_chinasa),
                getString(R.string.state_Ebonyi),
                getString(R.string.nick_2014574388_chinasa));
        listFull.add(studentsModel50);
        StudentsModel studentsModel51 = new StudentsModel(getString(R.string.name_2014574390_hope),
                getString(R.string.quote_2014574390_hope),
                IMAGE_URL + getString(R.string.no_2014574390) + ".jpg",
                getString(R.string.phone_2014574390_hope),
                getString(R.string.day_14) + " " + getString(R.string.apr),
                getString(R.string.email_2014574390_hope),
                getString(R.string.lga_2014574390_hope),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574390_hope));
        listFull.add(studentsModel51);
        StudentsModel studentsModel52 = new StudentsModel(getString(R.string.name_2014574392_florence),
                getString(R.string.quote_2014574392_florence),
                IMAGE_URL + getString(R.string.no_2014574392) + ".jpg",
                getString(R.string.phone_2014574392_florence),
                getString(R.string.day_25) + " " + getString(R.string.jan),
                getString(R.string.email_2014574392_florence),
                getString(R.string.lga_2014574392_florence),
                getString(R.string.state_CrossRiver),
                getString(R.string.nick_2014574392_florence));
        listFull.add(studentsModel52);
        StudentsModel studentsModel53 = new StudentsModel(getString(R.string.name_2014574393_patience),
                getString(R.string.quote_2014574393_patience),
                IMAGE_URL + getString(R.string.no_2014574393) + ".jpg",
                getString(R.string.phone_2014574393_patience),
                getString(R.string.day_25) + " " + getString(R.string.sept),
                getString(R.string.email_2014574393_patience),
                getString(R.string.lga_2014574393_patience),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574393_patience));
        listFull.add(studentsModel53);
        StudentsModel studentsModel54 = new StudentsModel(getString(R.string.name_2014574395_blessing),
                getString(R.string.quote_2014574395_blessing),
                IMAGE_URL + getString(R.string.no_2014574395) + ".jpg",
                getString(R.string.phone_2014574395_blessing),
                getString(R.string.day_21) + " " + getString(R.string.mar),
                getString(R.string.email_2014574395_blessing),
                getString(R.string.lga_2014574395_blessing),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574395_blessing));
        listFull.add(studentsModel54);
        StudentsModel studentsModel55 = new StudentsModel(getString(R.string.name_2014574396_shalom),
                getString(R.string.quote_2014574396_shalom),
                IMAGE_URL + getString(R.string.no_2014574396) + ".jpg",
                getString(R.string.phone_2014574396_shalom),
                getString(R.string.day_5) + " " + getString(R.string.oct),
                getString(R.string.email_2014574396_shalom),
                getString(R.string.lga_2014574396_shalom),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574396_shalom));
        listFull.add(studentsModel55);
        StudentsModel studentsModel56 = new StudentsModel(getString(R.string.name_2014574397_obumneme),
                getString(R.string.quote_2014574397_obumneme),
                IMAGE_URL + getString(R.string.no_2014574397) + ".jpg",
                getString(R.string.phone_2014574397_obumneme),
                getString(R.string.day_9) + " " + getString(R.string.oct),
                getString(R.string.email_2014574397_obumneme),
                getString(R.string.lga_2014574397_obumneme),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574397_obumneme));
        listFull.add(studentsModel56);
        StudentsModel studentsModel57 = new StudentsModel(getString(R.string.name_2014574398_judith),
                getString(R.string.quote_2014574398_judith),
                IMAGE_URL + getString(R.string.no_2014574398) + ".jpg",
                getString(R.string.phone_2014574398_judith),
                getString(R.string.day_19) + " " + getString(R.string.jul),
                getString(R.string.email_2014574398_judith),
                getString(R.string.lga_2014574398_judith),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574398_judith));
        listFull.add(studentsModel57);
        StudentsModel studentsModel58 = new StudentsModel(getString(R.string.name_2014574399_tobechukwu),
                getString(R.string.quote_2014574399_tobechukwu),
                IMAGE_URL + getString(R.string.no_2014574399) + ".jpg",
                getString(R.string.phone_2014574399_tobechukwu),
                getString(R.string.day_4) + " " + getString(R.string.may),
                getString(R.string.email_2014574399_tobechukwu),
                getString(R.string.lga_2014574399_tobechukwu),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574399_tobechukwu));
        listFull.add(studentsModel58);
        StudentsModel studentsModel59 = new StudentsModel(getString(R.string.name_2014574400_jennifer),
                getString(R.string.quote_2014574400_jennifer),
                IMAGE_URL + getString(R.string.no_2014574400) + ".jpg",
                getString(R.string.phone_2014574400_jennifer),
                getString(R.string.day_28) + " " + getString(R.string.apr),
                getString(R.string.email_2014574400_jennifer),
                getString(R.string.lga_2014574400_jennifer),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574400_jennifer));
        listFull.add(studentsModel59);
        StudentsModel studentsModel60 = new StudentsModel(getString(R.string.name_2014574401_blessing),
                getString(R.string.quote_2014574401_blessing),
                IMAGE_URL + getString(R.string.no_2014574401) + ".jpg",
                getString(R.string.phone_2014574401_blessing),
                getString(R.string.day_18) + " " + getString(R.string.jul),
                getString(R.string.email_2014574401_blessing),
                getString(R.string.lga_2014574401_blessing),
                getString(R.string.state_Imo),
                getString(R.string.nick_2014574401_blessing));
        listFull.add(studentsModel60);
        StudentsModel studentsModel61 = new StudentsModel(getString(R.string.name_2014574402_ogechukwu),
                getString(R.string.quote_2014574402_ogechukwu),
                IMAGE_URL + getString(R.string.no_2014574402) + ".jpg",
                getString(R.string.phone_2014574402_ogechukwu),
                getString(R.string.day_3) + " " + getString(R.string.apr),
                getString(R.string.email_2014574402_ogechukwu),
                getString(R.string.lga_2014574402_ogechukwu),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574402_ogechukwu));
        listFull.add(studentsModel61);
        StudentsModel studentsModel62 = new StudentsModel(getString(R.string.name_2014574403_faith),
                getString(R.string.quote_2014574403_faith),
                IMAGE_URL + getString(R.string.no_2014574403) + ".jpg",
                getString(R.string.phone_2014574403_faith),
                getString(R.string.day_9) + " " + getString(R.string.oct),
                getString(R.string.email_2014574403_faith),
                getString(R.string.lga_2014574403_faith),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574403_faith));
        listFull.add(studentsModel62);
        StudentsModel studentsModel63 = new StudentsModel(getString(R.string.name_2014574404_kenneth),
                getString(R.string.quote_2014574404_kenneth),
                IMAGE_URL + getString(R.string.no_2014574404) + ".jpg",
                getString(R.string.phone_2014574404_kenneth),
                getString(R.string.day_24) + " " + getString(R.string.aug),
                getString(R.string.email_2014574404_kenneth),
                getString(R.string.nick_2014574404_kenneth),
                getString(R.string.state_Imo),
                getString(R.string.nick_2014574404_kenneth));
        listFull.add(studentsModel63);
        StudentsModel studentsModel64 = new StudentsModel(getString(R.string.name_2014574405_samuel),
                getString(R.string.quote_2014574405_samuel),
                IMAGE_URL + getString(R.string.no_2014574405) + ".jpg",
                getString(R.string.phone_2014574405_samuel),
                getString(R.string.day_13) + " " + getString(R.string.mar),
                getString(R.string.email_2014574405_samuel),
                getString(R.string.lga_2014574405_samuel),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574405_samuel));
        listFull.add(studentsModel64);
        StudentsModel studentsModel65 = new StudentsModel(getString(R.string.name_2014574406_henry),
                getString(R.string.quote_2014574406_henry),
                IMAGE_URL + getString(R.string.no_2014574406) + ".jpg",
                getString(R.string.phone_2014574406_henry),
                getString(R.string.day_15) + " " + getString(R.string.oct),
                getString(R.string.email_2014574406_henry),
                getString(R.string.lga_2014574406_henry),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574406_henry));
        listFull.add(studentsModel65);
        StudentsModel studentsModel66 = new StudentsModel(getString(R.string.name_2014574407_maryjane),
                getString(R.string.quote_2014574407_maryjane),
                IMAGE_URL + getString(R.string.no_2014574407) + ".jpg",
                getString(R.string.phone_2014574407_maryjane),
                getString(R.string.day_15) + " " + getString(R.string.oct),
                getString(R.string.email_2014574407_maryjane),
                getString(R.string.lga_2014574407_maryjane),
                getString(R.string.state_unknown),
                getString(R.string.nick_2014574407_maryjane));
        listFull.add(studentsModel66);
        StudentsModel studentsModel67 = new StudentsModel(getString(R.string.name_2014574408_ifeanyi),
                getString(R.string.quote_2014574408_ifeanyi),
                IMAGE_URL + getString(R.string.no_2014574408) + ".jpg",
                getString(R.string.phone_2014574408_ifeanyi),
                getString(R.string.day_30) + " " + getString(R.string.jan),
                getString(R.string.email_2014574408_ifeanyi),
                getString(R.string.lga_2014574408_ifeanyi),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574408_ifeanyi));
        listFull.add(studentsModel67);
        StudentsModel studentsModel68 = new StudentsModel(getString(R.string.name_2014574409_rita),
                getString(R.string.quote_2014574409_rita),
                IMAGE_URL + getString(R.string.no_2014574409) + ".jpg",
                getString(R.string.phone_2014574409_rita),
                getString(R.string.day_27) + " " + getString(R.string.may),
                getString(R.string.email_2014574409_rita),
                getString(R.string.lga_2014574409_rita),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574409_rita));
        listFull.add(studentsModel68);
        StudentsModel studentsModel69 = new StudentsModel(getString(R.string.name_2014574410_precious),
                getString(R.string.quote_2014574410_precious),
                IMAGE_URL + getString(R.string.no_2014574410) + ".jpg",
                getString(R.string.phone_2014574410_precious),
                getString(R.string.day_26) + " " + getString(R.string.may),
                getString(R.string.email_2014574410_precious),
                getString(R.string.lga_2014574410_precious),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574410_precious));
        listFull.add(studentsModel69);
        StudentsModel studentsModel70 = new StudentsModel(getString(R.string.name_2014574412_smiling),
                getString(R.string.quote_2014574412_smiling),
                IMAGE_URL + getString(R.string.no_2014574412) + ".jpg",
                getString(R.string.phone_2014574412_smiling),
                getString(R.string.day_2) + " " + getString(R.string.jul),
                getString(R.string.email_2014574412_smiling),
                getString(R.string.lga_2014574412_smiling),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574412_smiling));
        listFull.add(studentsModel70);
        StudentsModel studentsModel71 = new StudentsModel(getString(R.string.name_2014574413_peace),
                getString(R.string.quote_2014574413_peace),
                IMAGE_URL + getString(R.string.no_2014574413) + ".jpg",
                getString(R.string.phone_2014574413_peace),
                getString(R.string.day_17) + " " + getString(R.string.dec),
                getString(R.string.email_2014574413_peace),
                getString(R.string.lga_2014574413_peace),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574413_peace));
        listFull.add(studentsModel71);
        StudentsModel studentsModel72 = new StudentsModel(getString(R.string.name_2014574414_emskaro),
                getString(R.string.quote_2014574414_emskaro),
                IMAGE_URL + getString(R.string.no_2014574414) + ".jpg",
                getString(R.string.phone_2014574414_emskaro),
                getString(R.string.day_17) + " " + getString(R.string.jun),
                getString(R.string.email_2014574414_emskaro),
                getString(R.string.lga_2014574414_emskaro),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574414_emskaro));
        listFull.add(studentsModel72);
        StudentsModel studentsModel73 = new StudentsModel(getString(R.string.name_2014574415_franca),
                getString(R.string.quote_2014574415_franca),
                IMAGE_URL + getString(R.string.no_2014574415) + ".jpg",
                getString(R.string.phone_2014574415_franca),
                getString(R.string.day_20) + " " + getString(R.string.dec),
                getString(R.string.email_2014574415_franca),
                getString(R.string.lga_2014574415_franca),
                getString(R.string.state_Abia),
                getString(R.string.nick_2014574415_franca));
        listFull.add(studentsModel73);
        StudentsModel studentsModel74 = new StudentsModel(getString(R.string.name_2014574416_zuma),
                getString(R.string.quote_2014574416_zuma),
                IMAGE_URL + getString(R.string.no_2014574416) + ".jpg",
                getString(R.string.phone_2014574416_zuma),
                getString(R.string.day_10) + " " + getString(R.string.jun),
                getString(R.string.email_2014574416_zuma),
                getString(R.string.lga_2014574416_zuma),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574416_zuma));
        listFull.add(studentsModel74);
        StudentsModel studentsModel75 = new StudentsModel(getString(R.string.name_2014574417_sylvia),
                getString(R.string.quote_2014574417_sylvia),
                IMAGE_URL + getString(R.string.no_2014574417) + ".jpg",
                getString(R.string.phone_2014574417_sylvia),
                getString(R.string.day_3) + " " + getString(R.string.jul),
                getString(R.string.email_2014574417_sylvia),
                getString(R.string.lga_2014574417_sylvia),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574417_sylvia));
        listFull.add(studentsModel75);
        StudentsModel studentsModel76 = new StudentsModel(getString(R.string.name_2014574419_lawrence),
                getString(R.string.quote_2014574419_lawrence),
                IMAGE_URL + getString(R.string.no_2014574419) + ".jpg",
                getString(R.string.phone_2014574419_lawrence),
                getString(R.string.day_26) + " " + getString(R.string.jul),
                getString(R.string.email_2014574419_lawrence),
                getString(R.string.lga_2014574419_lawrence),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574419_lawrence));
        listFull.add(studentsModel76);
        StudentsModel studentsModel77 = new StudentsModel(getString(R.string.name_2014574420_jennifer),
                getString(R.string.quote_2014574420_jennifer),
                IMAGE_URL + getString(R.string.no_2014574420) + ".jpg",
                getString(R.string.phone_2014574420_jennifer),
                getString(R.string.day_17) + " " + getString(R.string.apr),
                getString(R.string.email_2014574420_jennifer),
                getString(R.string.lga_2014574420_jennifer),
                getString(R.string.state_Imo),
                getString(R.string.nick_2014574420_jennifer));
        listFull.add(studentsModel77);
        StudentsModel studentsModel78 = new StudentsModel(getString(R.string.name_2014574421_fortunate),
                getString(R.string.quote_2014574421_fortunate),
                IMAGE_URL + getString(R.string.no_2014574421) + ".jpg",
                getString(R.string.phone_2014574421_fortunate),
                getString(R.string.day_9) + " " + getString(R.string.oct),
                getString(R.string.email_2014574421_fortunate),
                getString(R.string.lga_2014574421_fortunate),
                getString(R.string.state_Abia),
                getString(R.string.nick_2014574421_fortunate));
        listFull.add(studentsModel78);
        StudentsModel studentsModel79 = new StudentsModel(getString(R.string.name_2014574422_anthony),
                getString(R.string.quote_2014574422_anthony),
                IMAGE_URL + getString(R.string.no_2014574422) + ".jpg",
                getString(R.string.phone_2014574422_anthony),
                getString(R.string.day_13) + " " + getString(R.string.jun),
                getString(R.string.email_2014574422_anthony),
                getString(R.string.lga_2014574422_anthony),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574422_anthony));
        listFull.add(studentsModel79);
        StudentsModel studentsModel80 = new StudentsModel(getString(R.string.name_2014574423_perpetual),
                getString(R.string.quote_2014574423_perpetual),
                IMAGE_URL + getString(R.string.no_2014574423) + ".jpg",
                getString(R.string.phone_2014574423_perpetual),
                getString(R.string.day_3) + " " + getString(R.string.may),
                getString(R.string.email_2014574423_perpetual),
                getString(R.string.lga_2014574423_perpetual),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574423_perpetual));
        listFull.add(studentsModel80);
        StudentsModel studentsModel81 = new StudentsModel(getString(R.string.name_2014574424_zikora),
                getString(R.string.quote_2014574424_zikora),
                IMAGE_URL + getString(R.string.no_2014574424) + ".jpg",
                getString(R.string.phone_2014574424_zikora),
                getString(R.string.day_17) + " " + getString(R.string.may),
                getString(R.string.email_2014574424_zikora),
                getString(R.string.lga_2014574424_zikora),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574424_zikora));
        listFull.add(studentsModel81);
        StudentsModel studentsModel82 = new StudentsModel(getString(R.string.name_2014574425_mishael),
                getString(R.string.quote_2014574425_mishael),
                IMAGE_URL + getString(R.string.no_2014574425) + ".jpg",
                getString(R.string.phone_2014574425_mishael),
                getString(R.string.date_unknown),
                getString(R.string.email_2014574425_mishael),
                getString(R.string.lga_2014574425_mishael),
                getString(R.string.state_unknown),
                getString(R.string.nick_2014574425_mishael));
        listFull.add(studentsModel82);
        StudentsModel studentsModel83 = new StudentsModel(getString(R.string.name_2014574426_vincent),
                getString(R.string.quote_2014574426_vincent),
                IMAGE_URL + getString(R.string.no_2014574426) + ".jpg",
                getString(R.string.phone_2014574426_vincent),
                getString(R.string.day_12) + " " + getString(R.string.oct),
                getString(R.string.email_2014574426_vincent),
                getString(R.string.lga_2014574426_vincent),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574426_vincent));
        listFull.add(studentsModel83);
        StudentsModel studentsModel84 = new StudentsModel(getString(R.string.name_2014574427_chiemerie),
                getString(R.string.quote_2014574427_chiemerie),
                IMAGE_URL + getString(R.string.no_2014574427) + ".jpg",
                getString(R.string.phone_2014574427_chiemerie),
                getString(R.string.day_11) + " " + getString(R.string.may),
                getString(R.string.email_2014574427_chiemerie),
                getString(R.string.lga_2014574427_chiemerie),
                getString(R.string.state_Imo),
                getString(R.string.nick_2014574427_chiemerie));
        listFull.add(studentsModel84);
        StudentsModel studentsModel85 = new StudentsModel(getString(R.string.name_2014574428_ifeanyi),
                getString(R.string.quote_2014574428_ifeanyi),
                IMAGE_URL + getString(R.string.no_2014574428) + ".jpg",
                getString(R.string.phone_2014574428_ifeanyi),
                getString(R.string.day_13) + " " + getString(R.string.sept),
                getString(R.string.email_2014574428_ifeanyi),
                getString(R.string.lga_2014574428_ifeanyi),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574428_ifeanyi));
        listFull.add(studentsModel85);
        StudentsModel studentsModel86 = new StudentsModel(getString(R.string.name_2014574429_celestina),
                getString(R.string.quote_2014574429_celestina),
                IMAGE_URL + getString(R.string.no_2014574429) + ".jpg",
                getString(R.string.phone_2014574429_celestina),
                getString(R.string.day_18) + " " + getString(R.string.jan),
                getString(R.string.email_2014574429_celestina),
                getString(R.string.lga_2014574429_celestina),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574429_celestina));
        listFull.add(studentsModel86);
        StudentsModel studentsModel87 = new StudentsModel(getString(R.string.name_2014574432_chiazagom),
                getString(R.string.quote_2014574432_chiazagom),
                IMAGE_URL + getString(R.string.no_2014574432) + ".jpg",
                getString(R.string.phone_2014574432_chiazagom),
                getString(R.string.day_14) + " " + getString(R.string.feb),
                getString(R.string.email_2014574432_chiazagom),
                getString(R.string.nick_2014574432_chiazagom),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574432_chiazagom));
        listFull.add(studentsModel87);
        StudentsModel studentsModel88 = new StudentsModel(getString(R.string.name_2014574434_kennedy),
                getString(R.string.quote_2014574434_kennedy),
                IMAGE_URL + getString(R.string.no_2014574434) + ".jpg",
                getString(R.string.phone_2014574434_kennedy),
                getString(R.string.day_22) + " " + getString(R.string.may),
                getString(R.string.email_2014574434_kennedy),
                getString(R.string.lga_2014574434_kennedy),
                getString(R.string.state_Imo),
                getString(R.string.nick_2014574434_kennedy));
        listFull.add(studentsModel88);
        StudentsModel studentsModel89 = new StudentsModel(getString(R.string.name_2014574435_victor),
                getString(R.string.quote_2014574435_victor),
                IMAGE_URL + getString(R.string.no_2014574435) + ".jpg",
                getString(R.string.phone_2014574435_victor),
                getString(R.string.day_15) + " " + getString(R.string.sept),
                getString(R.string.email_2014574435_victor),
                getString(R.string.lga_2014574435_victor),
                getString(R.string.state_Abia),
                getString(R.string.nick_2014574435_victor));
        listFull.add(studentsModel89);
        StudentsModel studentsModel90 = new StudentsModel(getString(R.string.name_2014574437_nduka),
                getString(R.string.quote_2014574437_nduka),
                IMAGE_URL + getString(R.string.no_2014574437) + ".jpg",
                getString(R.string.phone_2014574437_nduka),
                getString(R.string.day_17) + " " + getString(R.string.mar),
                getString(R.string.email_2014574437_nduka),
                getString(R.string.lga_2014574437_nduka),
                getString(R.string.state_Delta),
                getString(R.string.nick_2014574437_nduka));
        listFull.add(studentsModel90);
        StudentsModel studentsModel91 = new StudentsModel(getString(R.string.name_2014574438_gabriel),
                getString(R.string.quote_2014574438_gabriel),
                IMAGE_URL + getString(R.string.no_2014574438) + ".jpg",
                getString(R.string.phone_2014574438_gabriel),
                getString(R.string.day_14) + " " + getString(R.string.aug),
                getString(R.string.email_2014574438_gabriel),
                getString(R.string.lga_2014574438_gabriel),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574438_gabriel));
        listFull.add(studentsModel91);
        StudentsModel studentsModel92 = new StudentsModel(getString(R.string.name_2014574440_emmanuel),
                getString(R.string.quote_2014574440_emmanuel),
                IMAGE_URL + getString(R.string.no_2014574440) + ".jpg",
                getString(R.string.phone_2014574440_emmanuel),
                getString(R.string.day_7) + " " + getString(R.string.may),
                getString(R.string.email_2014574440_emmanuel),
                getString(R.string.lga_2014574440_emmanuel),
                getString(R.string.state_Delta),
                getString(R.string.nick_2014574440_emmanuel));
        listFull.add(studentsModel92);
        StudentsModel studentsModel93 = new StudentsModel(getString(R.string.name_2014464245_chisom),
                getString(R.string.quote_2014464245_chisom),
                IMAGE_URL + getString(R.string.no_2014464245) + ".jpg",
                getString(R.string.phone_2014464245_chisom),
                getString(R.string.day_25) + " " + getString(R.string.feb),
                getString(R.string.email_2014464245_chisom),
                getString(R.string.lga_2014464245_chisom),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014464245_chisom));
        listFull.add(studentsModel93);
        StudentsModel studentsModel94 = new StudentsModel(getString(R.string.name_2013474386_ebuka),
                getString(R.string.quote_2013474386_ebuka),
                IMAGE_URL + getString(R.string.no_2013474386) + ".jpg",
                getString(R.string.phone_2013474386_ebuka),
                getString(R.string.day_30) + " " + getString(R.string.sept),
                getString(R.string.email_2013474386_ebuka),
                getString(R.string.lga_2013474386_ebuka),
                getString(R.string.state_Imo),
                getString(R.string.nick_2013474386_ebuka));
        listFull.add(studentsModel94);
        StudentsModel studentsModel95 = new StudentsModel(getString(R.string.name_2013474278_mary),
                getString(R.string.quote_2013474278_mary),
                IMAGE_URL + getString(R.string.no_2013474278) + ".jpg",
                getString(R.string.phone_2013474278_mary),
                getString(R.string.day_25) + " " + getString(R.string.mar),
                getString(R.string.email_2013474278_mary),
                getString(R.string.lga_2013474278_mary),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2013474278_mary));
        listFull.add(studentsModel95);
        StudentsModel studentsModel96 = new StudentsModel(getString(R.string.name_2013464162_angus),
                getString(R.string.quote_2013464162_angus),
                IMAGE_URL + getString(R.string.no_2013464162) + ".jpg",
                getString(R.string.phone_2013464162_angus),
                getString(R.string.day_13) + " " + getString(R.string.oct),
                getString(R.string.email_2013464162_angus),
                getString(R.string.lga_2013464162_angus),
                getString(R.string.state_Imo),
                getString(R.string.nick_2013464162_angus));
        listFull.add(studentsModel96);
        StudentsModel studentsModel97 = new StudentsModel(getString(R.string.name_2013614232_victor),
                getString(R.string.quote_2013614232_victor),
                IMAGE_URL + getString(R.string.no_2013614232) + ".jpg",
                getString(R.string.phone_2013614232_victor),
                getString(R.string.day_4) + " " + getString(R.string.apr),
                getString(R.string.email_2013614232_victor),
                getString(R.string.lga_2013614232_victor),
                getString(R.string.state_Abia),
                getString(R.string.nick_2013614232_victor));
        listFull.add(studentsModel97);
        StudentsModel studentsModel98 = new StudentsModel(getString(R.string.name_2013634226_maryjane),
                getString(R.string.quote_2013634226_maryjane),
                IMAGE_URL + getString(R.string.no_2013634226) + ".jpg",
                getString(R.string.phone_2013634226_maryjane),
                getString(R.string.day_20) + " " + getString(R.string.jun),
                getString(R.string.email_2013634226_maryjane),
                getString(R.string.lga_2013634226_maryjane),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2013634226_maryjane));
        listFull.add(studentsModel98);
        StudentsModel studentsModel99 = new StudentsModel(getString(R.string.name_2012464169_linda),
                getString(R.string.quote_2012464169_linda),
                IMAGE_URL + getString(R.string.no_2012464169) + ".jpg",
                getString(R.string.phone_2012464169_linda),
                getString(R.string.day_18) + " " + getString(R.string.aug),
                getString(R.string.email_2012464169_linda),
                getString(R.string.lga_2012464169_linda),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2012464169_linda));
        listFull.add(studentsModel99);
        StudentsModel studentsModel100 = new StudentsModel(getString(R.string.name_2011474018_christian),
                getString(R.string.quote_2011474018_christian),
                IMAGE_URL + getString(R.string.no_2011474018) + ".jpg",
                getString(R.string.phone_2011474018_christian),
                getString(R.string.day_24) + " " + getString(R.string.dec),
                getString(R.string.email_2011474018_christian),
                getString(R.string.lga_2011474018_christian),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2011474018_christian));
        listFull.add(studentsModel100);
    }
}
