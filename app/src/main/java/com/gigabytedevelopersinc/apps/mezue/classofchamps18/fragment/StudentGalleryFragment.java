package com.gigabytedevelopersinc.apps.mezue.classofchamps18.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigabytedevelopersinc.apps.mezue.classofchamps18.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentGalleryFragment extends Fragment {


    public StudentGalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_gallery, container, false);
    }

}
