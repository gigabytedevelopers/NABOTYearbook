package com.gigabytedevelopersinc.apps.mezue.classofchamps18.fragment;


import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.R;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.activities.StudentFullInfoLayout;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.models.StudentsModel;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.utils.TinyDB;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentAboutFragment extends Fragment {
    private TextView student_about_quotes,student_about_dob,student_about_phone_no,
                     student_about_email_address,student_about_lga,student_about_state;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private TinyDB tinyDB;
    private static final int REQUEST_PHONE_CALL = 1;

    public StudentAboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tinyDB = new TinyDB(getActivity());
        student_about_dob = view.findViewById(R.id.student_about_dob);
        student_about_phone_no = view.findViewById(R.id.student_about_phone_no);
        student_about_email_address = view.findViewById(R.id.student_about_email_address);
        student_about_lga = view.findViewById(R.id.student_about_lga);
        student_about_state = view.findViewById(R.id.student_about_state);
        student_about_quotes = view.findViewById(R.id.student_about_quotes);

        student_about_dob.setText(tinyDB.getString("studentsDob"));
        student_about_phone_no.setText(tinyDB.getString("studentPhone"));
        student_about_email_address.setText(tinyDB.getString("studentsEmail"));
        student_about_lga.setText(tinyDB.getString("studentsLocalGov"));
        student_about_state.setText(tinyDB.getString("studentsState"));
        student_about_quotes.setText(tinyDB.getString("studentQuotes"));

        student_about_phone_no.setOnClickListener(view1 -> {
            try {
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel: " + student_about_phone_no.getText().toString()));
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                    } else {
                        getContext().startActivity(call);
                    }
                } else {
                    Objects.requireNonNull(getContext()).startActivity(call);
                }
            } catch (ActivityNotFoundException activityException) {
                Log.e("Calling a Phone Number", "Call failed", activityException);
                Crashlytics.logException(activityException);
            }
        });

        student_about_email_address.setOnClickListener(view1 -> {
            if (!student_about_email_address.getText().toString().matches(EMAIL_PATTERN)) {
                Snackbar.make(view, "No Email Address Provided", Snackbar.LENGTH_LONG).show();
            } else {
                Intent mail = new Intent(Intent.ACTION_SENDTO);
                mail.setType("text/html");
                mail.setData(Uri.parse("mailto:"));
                mail.putExtra(Intent.EXTRA_EMAIL, new String[]{ student_about_email_address.getText().toString()});
                mail.putExtra(Intent.EXTRA_SUBJECT, "Hello " + StudentFullInfoLayout.student_name_collapse.getText().toString());
                startActivity(Intent.createChooser(mail, "Send an Email to " + StudentFullInfoLayout.student_nickname_collapse.getText().toString() + " with"));
            }
        });

        tinyDB.remove("studentsDob");
        tinyDB.remove("studentPhone");
        tinyDB.remove("studentsEmail");
        tinyDB.remove("studentsLocalGov");
        tinyDB.remove("studentsState");
        tinyDB.remove("studentQuotes");
    }
}
