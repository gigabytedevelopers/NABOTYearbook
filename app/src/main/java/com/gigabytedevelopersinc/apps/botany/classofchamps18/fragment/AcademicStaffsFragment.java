package com.gigabytedevelopersinc.apps.botany.classofchamps18.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigabytedevelopersinc.apps.botany.classofchamps18.R;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.adapters.StaffsAdapter;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.models.StaffsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Created by Emmanuel Nwokoma (Founder and CEO at Gigabyte Developers) on 8/20/2018
 **/
public class AcademicStaffsFragment extends Fragment {
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
        View view = inflater.inflate(R.layout.academic_staffs, container, false);

        mRecyclerView = view.findViewById(R.id.academic_staffs_list);
        list = new ArrayList<>();
        adapter = new StaffsAdapter(getContext(), list, (view1, position) -> {

        });

        academicSstaffList();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    private void academicSstaffList() {
        if (!haveNetworkConnection()) {
            StaffsModel staffsModel0 = new StaffsModel(getString(R.string.ekwealor),
                getString(R.string.ekwealor_office),
                R.drawable.user_male,
                getString(R.string.ekwealor_phone));
            list.add(staffsModel0);
            StaffsModel staffsModel1 = new StaffsModel(getString(R.string.okigbo),
                getString(R.string.okigbo_office),
                R.drawable.user_male,
                getString(R.string.okigbo_phone));
            list.add(staffsModel1);
            StaffsModel staffsModel2 = new StaffsModel(getString(R.string.amadi),
                getString(R.string.amadi_office),
                R.drawable.user_male,
                getString(R.string.amadi_phone));
            list.add(staffsModel2);
            StaffsModel staffsModel3 = new StaffsModel(getString(R.string.okeke),
                getString(R.string.okeke_office),
                R.drawable.user_male,
                getString(R.string.okeke_phone));
            list.add(staffsModel3);
            StaffsModel staffsModel4 = new StaffsModel(getString(R.string.mbaekwe),
                getString(R.string.mbaekwe_office),
                R.drawable.user_male,
                getString(R.string.mbaekwe_phone));
            list.add(staffsModel4);
            StaffsModel staffsModel5 = new StaffsModel(getString(R.string.ugwuoke),
                getString(R.string.ugwuoke_office),
                R.drawable.user_male,
                getString(R.string.ugwuoke_phone));
            list.add(staffsModel5);
            StaffsModel staffsModel6 = new StaffsModel(getString(R.string.ezeabara),
                getString(R.string.ezeabara_office),
                R.drawable.user_female,
                getString(R.string.ezeabara_phone));
            list.add(staffsModel6);
            StaffsModel staffsModel7 = new StaffsModel(getString(R.string.ada),
                getString(R.string.ada_office),
                R.drawable.user_female,
                getString(R.string.ada_phone));
            list.add(staffsModel7);
        } else {
            StaffsModel staffsModel0 = new StaffsModel(getString(R.string.ekwealor),
                    getString(R.string.ekwealor_office),
                    IMAGE_URL + getString(R.string.ekwealor_pic) + ".jpg",
                    getString(R.string.ekwealor_phone));
            list.add(staffsModel0);
            StaffsModel staffsModel1 = new StaffsModel(getString(R.string.okigbo),
                    getString(R.string.okigbo_office),
                    IMAGE_URL + getString(R.string.okigbo_pic) + ".jpg",
                    getString(R.string.okigbo_phone));
            list.add(staffsModel1);
            StaffsModel staffsModel2 = new StaffsModel(getString(R.string.amadi),
                    getString(R.string.amadi_office),
                    IMAGE_URL + getString(R.string.amadi_pic) + ".jpg",
                    getString(R.string.amadi_phone));
            list.add(staffsModel2);
            StaffsModel staffsModel3 = new StaffsModel(getString(R.string.okeke),
                    getString(R.string.okeke_office),
                    IMAGE_URL + getString(R.string.okeke_pic) + ".jpg",
                    getString(R.string.okeke_phone));
            list.add(staffsModel3);
            StaffsModel staffsModel4 = new StaffsModel(getString(R.string.mbaekwe),
                    getString(R.string.mbaekwe_office),
                    IMAGE_URL + getString(R.string.mbaekwe_pic) + ".jpg",
                    getString(R.string.mbaekwe_phone));
            list.add(staffsModel4);
            StaffsModel staffsModel5 = new StaffsModel(getString(R.string.ugwuoke),
                    getString(R.string.ugwuoke_office),
                    IMAGE_URL + getString(R.string.ugwuoke_pic) + ".jpg",
                    getString(R.string.ugwuoke_phone));
            list.add(staffsModel5);
            StaffsModel staffsModel6 = new StaffsModel(getString(R.string.ezeabara),
                    getString(R.string.ezeabara_office),
                    IMAGE_URL + getString(R.string.ezeabara_pic) + ".jpg",
                    getString(R.string.ezeabara_phone));
            list.add(staffsModel6);
            StaffsModel staffsModel7 = new StaffsModel(getString(R.string.ada),
                    getString(R.string.ada_office),
                    IMAGE_URL + getString(R.string.ada_pic) + ".jpg",
                    getString(R.string.ada_phone));
            list.add(staffsModel7);
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
