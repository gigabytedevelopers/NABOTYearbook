package com.gigabytedevelopersinc.apps.mezue.classofchamps18.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigabytedevelopersinc.apps.mezue.classofchamps18.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeMainFragment extends Fragment {
    private CardView classofchampion, finalyearforum, excoss, staffs;


    public HomeMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
                classofchampion = view.findViewById(R.id.class_of_champion);
        finalyearforum = view.findViewById(R.id.fyf);
        excoss = view.findViewById(R.id.excoss_cardview);
        staffs = view.findViewById(R.id.staffs_cardview);
        classofchampion.setOnClickListener(view1 -> {

            Fragment studentFragment;
            studentFragment = new StudentFragment();
            FragmentManager student = getFragmentManager();
            assert student != null;
            student.beginTransaction()
                    .replace(R.id.container_frame, studentFragment)
                    .addToBackStack(null)
                    .commit();
        });
        finalyearforum.setOnClickListener(view1 -> {
            Fragment fyf_ExcossFragment = new FyF_Fragment();
            replaceFragment(fyf_ExcossFragment);
        });
        excoss.setOnClickListener(view1 -> {
            Fragment excossFragment = new ExcossFragment();
            replaceFragment(excossFragment);
        });
        staffs.setOnClickListener(view1 -> {
            Fragment staffsFragment = new StaffsFragment();
            replaceFragment(staffsFragment);
        });
    }
    public void replaceFragment(Fragment homeFragment) {
        assert getFragmentManager() != null;
        FragmentTransaction home = getFragmentManager().beginTransaction();
        home.replace(R.id.container_frame, homeFragment);
        home.addToBackStack(null);
        home.commit();
    }
}
