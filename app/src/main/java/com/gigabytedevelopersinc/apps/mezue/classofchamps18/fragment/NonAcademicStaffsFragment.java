package com.gigabytedevelopersinc.apps.mezue.classofchamps18.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigabytedevelopersinc.apps.mezue.classofchamps18.R;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.adapters.StaffsAdapter;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.models.StaffsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Created by Emmanuel Nwokoma (Founder and CEO at Gigabyte Developers) on 8/20/2018
 **/
public class NonAcademicStaffsFragment extends Fragment {
    RecyclerView mRecyclerView;
    public static List<StaffsModel> list;
    public StaffsAdapter adapter;
    private String staffsName,staffsPhone,staffsOffice;
    private boolean haveConnectedWifi = false;
    private boolean haveConnectedMobile = false;
    private String IMAGE_URL = "http://class-of-champions-2018.000webhostapp.com/students/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.non_academic_staffs, container, false);

        mRecyclerView = view.findViewById(R.id.non_academic_staffs_list);
        list = new ArrayList<>();
        adapter = new StaffsAdapter(getContext(), list, (view1, position) -> {

        });

        nonAcademicSstaffList();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    private void nonAcademicSstaffList() {
        if (!haveNetworkConnection()) {

        } else {

        }
    }

    private boolean haveNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
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
