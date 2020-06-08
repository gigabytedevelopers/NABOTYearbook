package com.gigabytedevelopersinc.apps.botany.classofchamps18.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.gigabytedevelopersinc.apps.botany.classofchamps18.fragment.HomeNewsFragment;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.models.StudentsModel;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.RequiresApi;

/**
 * @author Created by Emmanuel Nwokoma (Founder and CEO at Gigabyte Developers) on 10/6/2018
 **/
public class BirthdayNotificationReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {
        Utils.scheduleJob(context);
    }
}
