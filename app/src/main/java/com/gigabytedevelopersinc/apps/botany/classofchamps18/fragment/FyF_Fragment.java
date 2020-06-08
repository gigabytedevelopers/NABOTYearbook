package com.gigabytedevelopersinc.apps.botany.classofchamps18.fragment;

import android.content.Context;
import android.content.Intent;
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
import com.gigabytedevelopersinc.apps.botany.classofchamps18.activities.StudentFullInfoLayout;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.adapters.FyF_Adapter;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.models.FyF_Model;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.utils.TinyDB;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Created by Emmanuel Nwokoma (Founder and CEO at Gigabyte Developers) on 8/15/2018
 * Edited By Emmanuel Okocha (Co-Founder and CTO at Gigabyte Developers) on 16/8/2018
 **/
public class FyF_Fragment extends Fragment {
    public static List<FyF_Model> list, listFull;
    private RecyclerView recyclerView;
    public FyF_Adapter adapter;
    private String studentName,studentQuotes,studentNickName,studentsPhone,studentsDob,
            studentsEmail,studentsLocalGov,studentsState;
    private boolean haveConnectedWifi = false;
    private boolean haveConnectedMobile = false;
    private String IMAGE_URL = "http://class-of-champions-2018.000webhostapp.com/students/";
    private String FACEBOOK_URL ="https://facebook.com/";
    private String INSTAGRAM_URL ="https://instagram.com/";
    private TinyDB tinyDB;

    public FyF_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_fyf, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.fyf_excoss_list);
        list = new ArrayList<>();
        listFull = new ArrayList<>();
        tinyDB = new TinyDB(getActivity());
        adapter = new FyF_Adapter(getContext(), list, (view1, position) -> {
            try {
                JSONArray studentJson = new JSONArray(openFullInfo(listFull,position));

                for (int j = 0; j < studentJson.length(); j++) {
                    JSONObject obj2 = studentJson.getJSONObject(j);
                    studentName = obj2.getString("fyfExcossName");
                    studentQuotes = obj2.getString("fyfQuotes");
                    studentsPhone = obj2.getString("callPhone");
                    studentsDob = obj2.getString("dob");
                    studentsEmail = obj2.getString("emailAddress");
                    studentsLocalGov = obj2.getString("localGov");
                    studentsState = obj2.getString("state");
                    studentNickName = obj2.getString("nickName");

                }


                tinyDB.putString("studentName", studentName);
                tinyDB.putString("studentQuotes", studentQuotes);
                tinyDB.putString("studentPhone", studentsPhone);
                tinyDB.putString("studentsDob", studentsDob);
                tinyDB.putString("studentsEmail", studentsEmail);
                tinyDB.putString("studentsLocalGov", studentsLocalGov);
                tinyDB.putString("studentsState", studentsState);
                tinyDB.putString("studentNickName", studentNickName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(getActivity(), StudentFullInfoLayout.class));
        });

        fyfList();
        fyfListFull();
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void fyfList(){
        if (!haveNetworkConnection()) {
            FyF_Model studentsModel0 = new FyF_Model(getString(R.string.name_2014574424_zikora),
                    getString(R.string.office_fyf_president),
                    R.drawable.user_male,
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574424_zikora));
            list.add(studentsModel0);
            FyF_Model studentsModel1 = new FyF_Model(getString(R.string.name_2014574384_joyce),
                    getString(R.string.office_fyf_vice_president),
                    R.drawable.user_female,
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574384_joyce));
            list.add(studentsModel1);
            FyF_Model studentsModel2 = new FyF_Model(getString(R.string.name_2014574409_rita),
                    getString(R.string.office_fyf_vice_president),
                    R.drawable.user_female,
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574409_rita));
            list.add(studentsModel2);
            FyF_Model studentsModel3 = new FyF_Model(getString(R.string.name_2014574333_francess),
                    getString(R.string.office_fyf_sec_general),
                    R.drawable.user_female,
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574333_francess));
            list.add(studentsModel3);
            FyF_Model studentsModel4 = new FyF_Model(getString(R.string.name_2014574360_blessing),
                    getString(R.string.office_fyf_treasurer),
                    R.drawable.user_female,
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574360_blessing));
            list.add(studentsModel4);
            FyF_Model studentsModel5 = new FyF_Model(getString(R.string.name_2014574330_lawrence),
                    getString(R.string.office_fyf_provost),
                    R.drawable.user_male,
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574330_lawrence));
            list.add(studentsModel5);
        } else {
            FyF_Model studentsModel0 = new FyF_Model(getString(R.string.name_2014574424_zikora),
                    getString(R.string.office_fyf_president),
                    IMAGE_URL + getString(R.string.no_2014574424) + ".jpg",
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574424_zikora));
            list.add(studentsModel0);
            FyF_Model studentsModel1 = new FyF_Model(getString(R.string.name_2014574384_joyce),
                    getString(R.string.office_fyf_vice_president),
                    IMAGE_URL + getString(R.string.no_2014574384) + ".jpg",
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574384_joyce));
            list.add(studentsModel1);
            FyF_Model studentsModel2 = new FyF_Model(getString(R.string.name_2014574409_rita),
                    getString(R.string.office_fyf_vice_president),
                    IMAGE_URL + getString(R.string.no_2014574409) + ".jpg",
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574409_rita));
            list.add(studentsModel2);
            FyF_Model studentsModel3 = new FyF_Model(getString(R.string.name_2014574333_francess),
                    getString(R.string.office_fyf_sec_general),
                    IMAGE_URL + getString(R.string.no_2014574333) + ".jpg",
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574333_francess));
            list.add(studentsModel3);
            FyF_Model studentsModel4 = new FyF_Model(getString(R.string.name_2014574360_blessing),
                    getString(R.string.office_fyf_treasurer),
                    IMAGE_URL + getString(R.string.no_2014574360) + ".jpg",
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574360_blessing));
            list.add(studentsModel4);
            FyF_Model studentsModel5 = new FyF_Model(getString(R.string.name_2014574330_lawrence),
                    getString(R.string.office_fyf_provost),
                    IMAGE_URL + getString(R.string.no_2014574330) + ".jpg",
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574330_lawrence));
            list.add(studentsModel5);
        }
    }

    private void fyfListFull(){
        FyF_Model fyF_model = new FyF_Model(getString(R.string.name_2014574424_zikora),
                getString(R.string.quote_2014574424_zikora),
                R.drawable.user_male,
                getString(R.string.phone_2014574424_zikora),
                getString(R.string.day_17) + " " + getString(R.string.may),
                getString(R.string.email_2014574424_zikora),
                getString(R.string.lga_2014574424_zikora),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574424_zikora));
        listFull.add(fyF_model);

        FyF_Model fyF_model1 = new FyF_Model(getString(R.string.name_2014574384_joyce),
                getString(R.string.quote_2014574384_joyce),
                R.drawable.user_female,
                getString(R.string.phone_2014574384_joyce),
                getString(R.string.day_18) + " " + getString(R.string.oct),
                getString(R.string.email_2014574384_joyce),
                getString(R.string.lga_2014574384_joyce),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574384_joyce));
        listFull.add(fyF_model1);

        FyF_Model fyF_model2 = new FyF_Model(getString(R.string.name_2014574409_rita),
                getString(R.string.quote_2014574409_rita),
                R.drawable.user_female,
                getString(R.string.phone_2014574409_rita),
                getString(R.string.day_27) + " " + getString(R.string.may),
                getString(R.string.email_2014574409_rita),
                getString(R.string.lga_2014574409_rita),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574409_rita));
        listFull.add(fyF_model2);

        FyF_Model fyF_model3 = new FyF_Model(getString(R.string.name_2014574333_francess),
                getString(R.string.quote_2014574333_francess),
                R.drawable.user_female,
                getString(R.string.phone_2014574333_francess),
                getString(R.string.day_8) + " " + getString(R.string.apr),
                getString(R.string.email_2014574333_francess),
                getString(R.string.lga_2014574333_francess),
                getString(R.string.state_Anambra),
                getString(R.string.nick_2014574333_francess));
        listFull.add(fyF_model3);

        FyF_Model fyF_model4 = new FyF_Model(getString(R.string.name_2014574360_blessing),
                getString(R.string.quote_2014574360_blessing),
                R.drawable.user_female,
                getString(R.string.phone_2014574360_blessing),
                getString(R.string.day_3) + " " + getString(R.string.may),
                getString(R.string.email_2014574360_blessing),
                getString(R.string.lga_2014574360_blessing),
                getString(R.string.state_Abia),
                getString(R.string.nick_2014574360_blessing));
        listFull.add(fyF_model4);

        FyF_Model fyF_model5 = new FyF_Model(getString(R.string.name_2014574330_lawrence),
                getString(R.string.quote_2014574330_lawrence),
                R.drawable.user_male,
                getString(R.string.phone_2014574330_lawrence),
                getString(R.string.day_3) + " " + getString(R.string.jun),
                getString(R.string.email_2014574330_lawrence),
                getString(R.string.lga_2014574330_lawrence),
                getString(R.string.state_Enugu),
                getString(R.string.nick_2014574330_lawrence));
        listFull.add(fyF_model5);
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

    private String openFullInfo(List<FyF_Model> list, int position){
        List<FyF_Model> model = new ArrayList<>();
        model.add(list.get(position));
        Gson gson = new Gson();

        return gson.toJson(model);
    }
}
