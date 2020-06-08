package com.gigabytedevelopersinc.apps.botany.classofchamps18.adapters;


import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.crashlytics.android.Crashlytics;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.R;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.fragment.HomeNewsFragment;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.fragment.StudentFragment;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.models.BirthdayModel;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.models.StudentsModel;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.utils.CardViewClickListener;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.utils.TinyDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class StudentsAdapter extends BaseAdapter {

    private Context context;
    private List<StudentsModel> studentsModels;
    private CardViewClickListener listener;
    private static final int REQUEST_PHONE_CALL = 1;
    private TinyDB tinyDB;
    private boolean haveConnectedWifi = false;
    private boolean haveConnectedMobile = false;

    public StudentsAdapter(Context context, List<StudentsModel> studentsModels, CardViewClickListener listener) {
        this.context = context;
        this.studentsModels = studentsModels;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return studentsModels.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        StudentsModel studentsModel = studentsModels.get(i);
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd"+" "+"MMMM");
        String todayString = formatter.format(todayDate);

        View gridView;
        if (view == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            gridView = inflater.inflate(R.layout.student_card_item, viewGroup, false);


        } else {
            gridView = view;

        }
        ImageView studentI = gridView.findViewById(R.id.student_image);
        TextView studentN = gridView.findViewById(R.id.student_name);
        TextView studentQ = gridView.findViewById(R.id.student_text);
        TextView studentRN = gridView.findViewById(R.id.student_reg_no);
        ImageView studentFB = gridView.findViewById(R.id.student_facebook);
        ImageView studentTW = gridView.findViewById(R.id.student_instagram);
        ImageView studentPhone = gridView.findViewById(R.id.student_phone);
        
        gridView.setOnClickListener((View view2) -> listener.onItemClick(gridView, i));


        tinyDB = new TinyDB(context);

        studentN.setText(studentsModel.getStudentName());
        studentQ.setText(studentsModel.getStudentQuotes());
        studentRN.setText(studentsModel.getStudentRegNo());

        HomeNewsFragment.birthdayList = new ArrayList<>();
        for (StudentsModel studentsModel1 : studentsModels){
            if (todayString.equals(studentsModel1.getDob())){
                BirthdayModel birthdayModel = new BirthdayModel(R.drawable.user_male,
                        studentsModel1.getStudentName(),
                        studentsModel1.getNickName(),
                        studentsModel1.getDob());
                HomeNewsFragment.birthdayList.add(birthdayModel);
            }
        }
        if (haveNetworkConnection()){

              RequestOptions requestOptions = RequestOptions
                  .placeholderOf(R.drawable.placeholder)
                  .diskCacheStrategy(DiskCacheStrategy.ALL)
                  .centerCrop()
                  .format(DecodeFormat.PREFER_ARGB_8888);


              Glide.with(context)
                  .setDefaultRequestOptions(requestOptions)
                  .asBitmap()
                  .load(studentsModel.getStudentImage())
                  .into(studentI);


            StudentFragment.gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                    if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING){
                        Glide.with(context).pauseRequests();
                    }else if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                        Glide.with(context).pauseRequests();
                    }else if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                        Glide.with(context).resumeRequests();
                    }
                }

                @Override
                public void onScroll(AbsListView absListView, int i, int i1, int i2) {

                }
            });

        }else {
            studentI.setImageResource(studentsModel.getStudentIm());
        }

        studentFB.setOnClickListener((View fb) -> {
            Toast.makeText(context, "Fb button clicked", Toast.LENGTH_SHORT).show();
        });

        studentTW.setOnClickListener((View tw) -> {
            Toast.makeText(context, "IG button clicked", Toast.LENGTH_SHORT).show();
        });

        studentPhone.setOnClickListener((View phone) -> {

            try {
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel: " + studentsModel.getCallPhone()));
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                    }else{
                    context.startActivity(call);
                    }
                }else {
                    context.startActivity(call);
                }
            } catch (ActivityNotFoundException activityException) {
                Log.e("Calling a Phone Number", "Call failed", activityException);
                Crashlytics.logException(activityException);
            }
        });

        return gridView;
    }


    private boolean haveNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) Objects.requireNonNull(context).getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo){
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }

        return haveConnectedWifi || haveConnectedMobile;

    }

}