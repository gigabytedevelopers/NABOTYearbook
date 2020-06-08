package com.gigabytedevelopersinc.apps.botany.classofchamps18.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.view.ViewCompat;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.gigabytedevelopersinc.apps.botany.classofchamps18.R;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.activities.StudentFullInfoLayout;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.adapters.StudentsAdapter;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.models.StudentsModel;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.utils.TinyDB;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentFragment extends Fragment{
    public static GridView gridView;
    public static List<StudentsModel> list, listFull;
    private StudentsAdapter adapter;
    private TinyDB tinyDB;
    private String studentName;
    private String studentQuotes,studentNickName,studentsPhone,studentsDob,
                   studentsEmail,studentsLocalGov,studentsState,studentImage;
    private boolean haveConnectedWifi = false;
    private boolean haveConnectedMobile = false;
    private String IMAGE_URL = "http://class-of-champions-2018.000webhostapp.com/students/";
    private String FACEBOOK_URL ="https://facebook.com/";
    private String INSTAGRAM_URL ="https://instagram.com/";
    private List<StudentsModel> model;
    private Gson gson2;
    JSONObject obj2;

    public StudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = view.findViewById(R.id.student_list);
        list = new ArrayList<>();
        listFull = new ArrayList<>();
        tinyDB = new TinyDB(getActivity());
        adapter = new StudentsAdapter(getContext(), list, (view1, position) -> {


            try {
                JSONArray studentJson = new JSONArray(openFullInfo(listFull,position));

                if (haveNetworkConnection()){
                    for (int j = 0; j < studentJson.length(); j++) {
                        obj2 = studentJson.getJSONObject(j);
                        studentImage = obj2.getString("studentImage");
                        studentName = obj2.getString("studentName");
                        studentQuotes = obj2.getString("studentQuotes");
                        studentsPhone = obj2.getString("callPhone");
                        studentsDob = obj2.getString("dob");
                        studentsEmail = obj2.getString("emailAddress");
                        studentsLocalGov = obj2.getString("localGov");
                        studentsState = obj2.getString("state");
                        studentNickName = obj2.getString("nickName");

                    }

                    tinyDB.putString("studentImage", studentImage);
                    tinyDB.putString("studentName", studentName);
                    tinyDB.putString("studentQuotes", studentQuotes);
                    tinyDB.putString("studentPhone", studentsPhone);
                    tinyDB.putString("studentsDob", studentsDob);
                    tinyDB.putString("studentsEmail", studentsEmail);
                    tinyDB.putString("studentsLocalGov", studentsLocalGov);
                    tinyDB.putString("studentsState", studentsState);
                    tinyDB.putString("studentNickName", studentNickName);
                }else {
                    for (int j = 0; j < studentJson.length(); j++) {
                        obj2 = studentJson.getJSONObject(j);
                        studentName = obj2.getString("studentName");
                        studentQuotes = obj2.getString("studentQuotes");
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
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(getActivity(), StudentFullInfoLayout.class));
        });
        ViewCompat.setNestedScrollingEnabled(gridView, false);
        gridView.setAdapter(adapter);


        studentList();
        studentFullInfo();
    }


    private void studentList(){
        if (!haveNetworkConnection()) {
            StudentsModel studentsModel = new StudentsModel(getString(R.string.name_2014574329_miriam),
                    getString(R.string.quote_2014574329_miriam),
                    R.drawable.user_female,
                    R.string.no_2014574329,
                    getString(R.string.phone_2014574329_miriam),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel);
            StudentsModel studentsModel1 = new StudentsModel(getString(R.string.name_2014574330_lawrence),
                    getString(R.string.quote_2014574330_lawrence),
                    R.drawable.user_male,
                    R.string.no_2014574330,
                    getString(R.string.phone_2014574330_lawrence),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel1);
            StudentsModel studentsModel2 = new StudentsModel(getString(R.string.name_2014574331_prisca),
                    getString(R.string.quote_2014574331_prisca),
                    R.drawable.user_female,
                    R.string.no_2014574331,
                    getString(R.string.phone_2014574331_prisca),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel2);
            StudentsModel studentsModel3 = new StudentsModel(getString(R.string.name_2014574332_doris),
                    getString(R.string.quote_2014574332_doris),
                    R.drawable.user_female,
                    R.string.no_2014574332,
                    getString(R.string.phone_2014574332_doris),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel3);
            StudentsModel studentsModel4 = new StudentsModel(getString(R.string.name_2014574333_francess),
                    getString(R.string.quote_2014574333_francess),
                    R.drawable.user_female,
                    R.string.no_2014574333,
                    getString(R.string.phone_2014574333_francess),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel4);
            StudentsModel studentsModel5 = new StudentsModel(getString(R.string.name_2014574335_christian),
                    getString(R.string.quote_2014574335_christian),
                    R.drawable.user_male,
                    R.string.no_2014574335,
                    getString(R.string.phone_2014574335_christian),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel5);
            StudentsModel studentsModel6 = new StudentsModel(getString(R.string.name_2014574336_chioma),
                    getString(R.string.quote_2014574336_chioma),
                    R.drawable.user_female,
                    R.string.no_2014574336,
                    getString(R.string.phone_2014574336_chioma),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel6);
            StudentsModel studentsModel7 = new StudentsModel(getString(R.string.name_2014574337_favour),
                    getString(R.string.quote_2014574337_favour),
                    R.drawable.user_female,
                    R.string.no_2014574337,
                    getString(R.string.phone_2014574337_favour),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel7);
            StudentsModel studentsModel8 = new StudentsModel(getString(R.string.name_2014574338_kenechi),
                    getString(R.string.quote_2014574338_kenechi),
                    R.drawable.user_male,
                    R.string.no_2014574338,
                    getString(R.string.phone_2014574338_kenechi),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel8);
            StudentsModel studentsModel9 = new StudentsModel(getString(R.string.name_2014574339_joshua),
                    getString(R.string.quote_2014574339_joshua),
                    R.drawable.user_male,
                    R.string.no_2014574339,
                    getString(R.string.phone_2014574339_joshua),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel9);
            StudentsModel studentsModel10 = new StudentsModel(getString(R.string.name_2014574340_chioma_rita),
                    getString(R.string.quote_2014574340_chioma_rita),
                    R.drawable.user_female,
                    R.string.no_2014574340,
                    getString(R.string.phone_2014574340_chioma_rita),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel10);
            StudentsModel studentsModel11 = new StudentsModel(getString(R.string.name_2014574342_makuochukwu),
                    getString(R.string.quote_2014574342_makuochukwu),
                    R.drawable.user_female,
                    R.string.no_2014574342,
                    getString(R.string.phone_2014574342_makuochukwu),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel11);
            StudentsModel studentsModel12 = new StudentsModel(getString(R.string.name_2014574343_lilian),
                    getString(R.string.quote_2014574343_lilian),
                    R.drawable.user_female,
                    R.string.no_2014574343,
                    getString(R.string.phone_2014574343_lilian),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel12);
            StudentsModel studentsModel13 = new StudentsModel(getString(R.string.name_2014574344_collins),
                    getString(R.string.quote_2014574344_collins),
                    R.drawable.user_male,
                    R.string.no_2014574344,
                    getString(R.string.phone_2014574344_collins),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel13);
            StudentsModel studentsModel14 = new StudentsModel(getString(R.string.name_2014574345_obianuju),
                    getString(R.string.quote_2014574345_obianuju),
                    R.drawable.user_female,
                    R.string.no_2014574345,
                    getString(R.string.phone_2014574345_obianuju),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel14);
            StudentsModel studentsModel15 = new StudentsModel(getString(R.string.name_2014574346_goodness),
                    getString(R.string.quote_2014574346_goodness),
                    R.drawable.user_female,
                    R.string.no_2014574346,
                    getString(R.string.phone_2014574346_goodness),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel15);
            StudentsModel studentsModel16 = new StudentsModel(getString(R.string.name_2014574347_ikechukwu),
                    getString(R.string.quote_2014574347_ikechukwu),
                    R.drawable.user_male,
                    R.string.no_2014574347,
                    getString(R.string.phone_2014574347_ikechukwu),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel16);
            StudentsModel studentsModel17 = new StudentsModel(getString(R.string.name_2014574348_precious),
                    getString(R.string.quote_2014574348_precious),
                    R.drawable.user_female,
                    R.string.no_2014574348,
                    getString(R.string.phone_2014574348_precious),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel17);
            StudentsModel studentsModel18 = new StudentsModel(getString(R.string.name_2014574350_charity),
                    getString(R.string.quote_2014574350_charity),
                    R.drawable.user_female,
                    R.string.no_2014574350,
                    getString(R.string.phone_2014574350_charity),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel18);
            StudentsModel studentsModel19 = new StudentsModel(getString(R.string.name_2014574352_kingsley),
                    getString(R.string.quote_2014574352_kingsley),
                    R.drawable.user_male,
                    R.string.no_2014574352,
                    getString(R.string.phone_2014574352_kingsley),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel19);
            StudentsModel studentsModel20 = new StudentsModel(getString(R.string.name_2014574354_augustine),
                    getString(R.string.quote_2014574354_augustine),
                    R.drawable.user_male,
                    R.string.no_2014574354,
                    getString(R.string.phone_2014574354_augustine),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel20);
            StudentsModel studentsModel21 = new StudentsModel(getString(R.string.name_2014574355_judith),
                    getString(R.string.quote_2014574355_judith),
                    R.drawable.user_female,
                    R.string.no_2014574355,
                    getString(R.string.phone_2014574355_judith),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel21);
            StudentsModel studentsModel22 = new StudentsModel(getString(R.string.name_2014574356_onyinyechi),
                    getString(R.string.quote_2014574356_onyinyechi),
                    R.drawable.user_female,
                    R.string.no_2014574356,
                    getString(R.string.phone_2014574356_onyinyechi),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel22);
            StudentsModel studentsModel243 = new StudentsModel(getString(R.string.name_2014574357_emeka),
                    getString(R.string.quote_2014574357_emeka),
                    R.drawable.user_male,
                    R.string.no_2014574357,
                    getString(R.string.phone_2014574357_emeka),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel243);
            StudentsModel studentsModel24 = new StudentsModel(getString(R.string.name_2014574358_kelvin),
                    getString(R.string.quote_2014574358_kelvin),
                    R.drawable.user_male,
                    R.string.no_2014574358,
                    getString(R.string.phone_2014574358_kelvin),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel24);
            StudentsModel studentsModel25 = new StudentsModel(getString(R.string.name_2014574359_ndidi),
                    getString(R.string.quote_2014574359_ndidi),
                    R.drawable.user_female,
                    R.string.no_2014574359,
                    getString(R.string.phone_2014574359_ndidi),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel25);
            StudentsModel studentsModel26 = new StudentsModel(getString(R.string.name_2014574360_blessing),
                    getString(R.string.quote_2014574360_blessing),
                    R.drawable.user_female,
                    R.string.no_2014574360,
                    getString(R.string.phone_2014574360_blessing),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel26);
            StudentsModel studentsModel27 = new StudentsModel(getString(R.string.name_2014574361_chidiebere),
                    getString(R.string.quote_2014574361_chidiebere),
                    R.drawable.user_male,
                    R.string.no_2014574361,
                    getString(R.string.phone_2014574361_chidiebere),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel27);
            StudentsModel studentsModel28 = new StudentsModel(getString(R.string.name_2014574363_adaobi),
                    getString(R.string.quote_2014574363_adaobi),
                    R.drawable.user_female,
                    R.string.no_2014574363,
                    getString(R.string.phone_2014574363_adaobi),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel28);
            StudentsModel studentsModel29 = new StudentsModel(getString(R.string.name_2014574364_amaobi),
                    getString(R.string.quote_2014574364_amaobi),
                    R.drawable.user_male,
                    R.string.no_2014574364,
                    getString(R.string.phone_2014574364_amaobi),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel29);
            StudentsModel studentsModel30 = new StudentsModel(getString(R.string.name_2014574365_augustina),
                    getString(R.string.quote_2014574365_augustina),
                    R.drawable.user_female,
                    R.string.no_2014574365,
                    getString(R.string.phone_2014574365_augustina),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel30);
            StudentsModel studentsModel31 = new StudentsModel(getString(R.string.name_2014574366_cynthia),
                    getString(R.string.quote_2014574366_cynthia),
                    R.drawable.user_female,
                    R.string.no_2014574366,
                    getString(R.string.phone_2014574366_cynthia),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel31);
            StudentsModel studentsModel32 = new StudentsModel(getString(R.string.name_2014574367_ifechukwu),
                    getString(R.string.quote_2014574367_ifechukwu),
                    R.drawable.user_male,
                    R.string.no_2014574367,
                    getString(R.string.phone_2014574367_ifechukwu),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel32);
            StudentsModel studentsModel33 = new StudentsModel(getString(R.string.name_2014574368_chiamaka),
                    getString(R.string.quote_2014574368_chiamaka),
                    R.drawable.user_female,
                    R.string.no_2014574368,
                    getString(R.string.phone_2014574368_chiamaka),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel33);
            StudentsModel studentsModel34 = new StudentsModel(getString(R.string.name_2014574369_emezie),
                    getString(R.string.quote_2014574369_emezie),
                    R.drawable.user_female,
                    R.string.no_2014574369,
                    getString(R.string.phone_2014574369_emezie),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel34);
            StudentsModel studentsModel35 = new StudentsModel(getString(R.string.name_2014574370_gigabyte),
                    getString(R.string.quote_2014574370_gigabyte),
                    R.drawable.user_male,
                    R.string.no_2014574370,
                    getString(R.string.phone_2014574370_gigabyte),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel35);
            StudentsModel studentsModel36 = new StudentsModel(getString(R.string.name_2014574372_pauline),
                    getString(R.string.quote_2014574372_pauline),
                    R.drawable.user_female,
                    R.string.no_2014574372,
                    getString(R.string.phone_2014574372_pauline),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel36);
            StudentsModel studentsModel37 = new StudentsModel(getString(R.string.name_2014574373_ijeoma),
                    getString(R.string.quote_2014574373_ijeoma),
                    R.drawable.user_female,
                    R.string.no_2014574373,
                    getString(R.string.phone_2014574373_ijeoma),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel37);
            StudentsModel studentsModel38 = new StudentsModel(getString(R.string.name_2014574374_onyekachi),
                    getString(R.string.quote_2014574374_onyekachi),
                    R.drawable.user_female,
                    R.string.no_2014574374,
                    getString(R.string.phone_2014574374_onyekachi),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel38);
            StudentsModel studentsModel39 = new StudentsModel(getString(R.string.name_2014574375_vanessa),
                    getString(R.string.quote_2014574375_vanessa),
                    R.drawable.user_female,
                    R.string.no_2014574375,
                    getString(R.string.phone_2014574375_vanessa),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel39);
            StudentsModel studentsModel40 = new StudentsModel(getString(R.string.name_2014574376_susan),
                    getString(R.string.quote_2014574376_susan),
                    R.drawable.user_female,
                    R.string.no_2014574376,
                    getString(R.string.phone_2014574376_susan),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel40);
            StudentsModel studentsModel41 = new StudentsModel(getString(R.string.name_2014574377_rosemary),
                    getString(R.string.quote_2014574377_rosemary),
                    R.drawable.user_female,
                    R.string.no_2014574377,
                    getString(R.string.phone_2014574377_rosemary),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel41);
            StudentsModel studentsModel42 = new StudentsModel(getString(R.string.name_2014574378_chukwusom),
                    getString(R.string.quote_2014574378_chukwusom),
                    R.drawable.user_male,
                    R.string.no_2014574378,
                    getString(R.string.phone_2014574378_chukwusom),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel42);
            StudentsModel studentsModel43 = new StudentsModel(getString(R.string.name_2014574379_friday),
                    getString(R.string.quote_2014574379_friday),
                    R.drawable.user_male,
                    R.string.no_2014574379,
                    getString(R.string.phone_2014574379_friday),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel43);
            StudentsModel studentsModel44 = new StudentsModel(getString(R.string.name_2014574380_blessing),
                    getString(R.string.quote_2014574380_blessing),
                    R.drawable.user_female,
                    R.string.no_2014574380,
                    getString(R.string.phone_2014574380_blessing),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel44);
            StudentsModel studentsModel45 = new StudentsModel(getString(R.string.name_2014574383_chidiogo),
                    getString(R.string.quote_2014574383_chidiogo),
                    R.drawable.user_female,
                    R.string.no_2014574383,
                    getString(R.string.phone_2014574383_chidiogo),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel45);
            StudentsModel studentsModel46 = new StudentsModel(getString(R.string.name_2014574384_joyce),
                    getString(R.string.quote_2014574384_joyce),
                    R.drawable.user_female,
                    R.string.no_2014574384,
                    getString(R.string.phone_2014574384_joyce),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel46);
            StudentsModel studentsModel47 = new StudentsModel(getString(R.string.name_2014574385_peter),
                    getString(R.string.quote_2014574385_peter),
                    R.drawable.user_male,
                    R.string.no_2014574385,
                    getString(R.string.phone_2014574385_peter),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel47);
            StudentsModel studentsModel48 = new StudentsModel(getString(R.string.name_2014574386_daniel),
                    getString(R.string.quote_2014574386_daniel),
                    R.drawable.user_male,
                    R.string.no_2014574386,
                    getString(R.string.phone_2014574386_daniel),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel48);
            StudentsModel studentsModel49 = new StudentsModel(getString(R.string.name_2014574387_vincent),
                    getString(R.string.quote_2014574387_vincent),
                    R.drawable.user_male,
                    R.string.no_2014574387,
                    getString(R.string.phone_2014574387_vincent),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel49);
            StudentsModel studentsModel50 = new StudentsModel(getString(R.string.name_2014574388_chinasa),
                    getString(R.string.quote_2014574388_chinasa),
                    R.drawable.user_female,
                    R.string.no_2014574388,
                    getString(R.string.phone_2014574388_chinasa),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel50);
            StudentsModel studentsModel51 = new StudentsModel(getString(R.string.name_2014574390_hope),
                    getString(R.string.quote_2014574390_hope),
                    R.drawable.user_female,
                    R.string.no_2014574390,
                    getString(R.string.phone_2014574390_hope),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel51);
            StudentsModel studentsModel52 = new StudentsModel(getString(R.string.name_2014574392_florence),
                    getString(R.string.quote_2014574392_florence),
                    R.drawable.user_female,
                    R.string.no_2014574392,
                    getString(R.string.phone_2014574392_florence),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel52);
            StudentsModel studentsModel53 = new StudentsModel(getString(R.string.name_2014574393_patience),
                    getString(R.string.quote_2014574393_patience),
                    R.drawable.user_female,
                    R.string.no_2014574393,
                    getString(R.string.phone_2014574393_patience),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel53);
            StudentsModel studentsModel54 = new StudentsModel(getString(R.string.name_2014574395_blessing),
                    getString(R.string.quote_2014574395_blessing),
                    R.drawable.user_female,
                    R.string.no_2014574395,
                    getString(R.string.phone_2014574395_blessing),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel54);
            StudentsModel studentsModel55 = new StudentsModel(getString(R.string.name_2014574396_shalom),
                    getString(R.string.quote_2014574396_shalom),
                    R.drawable.user_female,
                    R.string.no_2014574396,
                    getString(R.string.phone_2014574396_shalom),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel55);
            StudentsModel studentsModel56 = new StudentsModel(getString(R.string.name_2014574397_obumneme),
                    getString(R.string.quote_2014574397_obumneme),
                    R.drawable.user_male,
                    R.string.no_2014574397,
                    getString(R.string.phone_2014574397_obumneme),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel56);
            StudentsModel studentsModel57 = new StudentsModel(getString(R.string.name_2014574398_judith),
                    getString(R.string.quote_2014574398_judith),
                    R.drawable.user_female,
                    R.string.no_2014574398,
                    getString(R.string.phone_2014574398_judith),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel57);
            StudentsModel studentsModel58 = new StudentsModel(getString(R.string.name_2014574399_tobechukwu),
                    getString(R.string.quote_2014574399_tobechukwu),
                    R.drawable.user_male,
                    R.string.no_2014574399,
                    getString(R.string.phone_2014574399_tobechukwu),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel58);
            StudentsModel studentsModel59 = new StudentsModel(getString(R.string.name_2014574400_jennifer),
                    getString(R.string.quote_2014574400_jennifer),
                    R.drawable.user_female,
                    R.string.no_2014574400,
                    getString(R.string.phone_2014574400_jennifer),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel59);
            StudentsModel studentsModel60 = new StudentsModel(getString(R.string.name_2014574401_blessing),
                    getString(R.string.quote_2014574401_blessing),
                    R.drawable.user_female,
                    R.string.no_2014574401,
                    getString(R.string.phone_2014574401_blessing),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel60);
            StudentsModel studentsModel61 = new StudentsModel(getString(R.string.name_2014574402_ogechukwu),
                    getString(R.string.quote_2014574402_ogechukwu),
                    R.drawable.user_female,
                    R.string.no_2014574402,
                    getString(R.string.phone_2014574402_ogechukwu),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel61);
            StudentsModel studentsModel62 = new StudentsModel(getString(R.string.name_2014574403_faith),
                    getString(R.string.quote_2014574403_faith),
                    R.drawable.user_female,
                    R.string.no_2014574403,
                    getString(R.string.phone_2014574403_faith),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel62);
            StudentsModel studentsModel63 = new StudentsModel(getString(R.string.name_2014574404_kenneth),
                    getString(R.string.quote_2014574404_kenneth),
                    R.drawable.user_male,
                    R.string.no_2014574404,
                    getString(R.string.phone_2014574404_kenneth),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel63);
            StudentsModel studentsModel64 = new StudentsModel(getString(R.string.name_2014574405_samuel),
                    getString(R.string.quote_2014574405_samuel),
                    R.drawable.user_male,
                    R.string.no_2014574405,
                    getString(R.string.phone_2014574405_samuel),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel64);
            StudentsModel studentsModel65 = new StudentsModel(getString(R.string.name_2014574406_henry),
                    getString(R.string.quote_2014574406_henry),
                    R.drawable.user_male,
                    R.string.no_2014574406,
                    getString(R.string.phone_2014574406_henry),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel65);
            StudentsModel studentsModel66 = new StudentsModel(getString(R.string.name_2014574407_maryjane),
                    getString(R.string.quote_2014574407_maryjane),
                    R.drawable.user_female,
                    R.string.no_2014574407,
                    getString(R.string.phone_2014574407_maryjane),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel66);
            StudentsModel studentsModel67 = new StudentsModel(getString(R.string.name_2014574408_ifeanyi),
                    getString(R.string.quote_2014574408_ifeanyi),
                    R.drawable.user_male,
                    R.string.no_2014574408,
                    getString(R.string.phone_2014574408_ifeanyi),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel67);
            StudentsModel studentsModel68 = new StudentsModel(getString(R.string.name_2014574409_rita),
                    getString(R.string.quote_2014574409_rita),
                    R.drawable.user_female,
                    R.string.no_2014574409,
                    getString(R.string.phone_2014574409_rita),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel68);
            StudentsModel studentsModel69 = new StudentsModel(getString(R.string.name_2014574410_precious),
                    getString(R.string.quote_2014574410_precious),
                    R.drawable.user_female,
                    R.string.no_2014574410,
                    getString(R.string.phone_2014574410_precious),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel69);
            StudentsModel studentsModel70 = new StudentsModel(getString(R.string.name_2014574412_smiling),
                    getString(R.string.quote_2014574412_smiling),
                    R.drawable.user_male,
                    R.string.no_2014574412,
                    getString(R.string.phone_2014574412_smiling),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel70);
            StudentsModel studentsModel71 = new StudentsModel(getString(R.string.name_2014574413_peace),
                    getString(R.string.quote_2014574413_peace),
                    R.drawable.user_female,
                    R.string.no_2014574413,
                    getString(R.string.phone_2014574413_peace),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel71);
            StudentsModel studentsModel72 = new StudentsModel(getString(R.string.name_2014574414_emskaro),
                    getString(R.string.quote_2014574414_emskaro),
                    R.drawable.user_male,
                    R.string.no_2014574414,
                    getString(R.string.phone_2014574414_emskaro),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel72);
            StudentsModel studentsModel73 = new StudentsModel(getString(R.string.name_2014574415_franca),
                    getString(R.string.quote_2014574415_franca),
                    R.drawable.user_female,
                    R.string.no_2014574415,
                    getString(R.string.phone_2014574415_franca),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel73);
            StudentsModel studentsModel74 = new StudentsModel(getString(R.string.name_2014574416_zuma),
                    getString(R.string.quote_2014574416_zuma),
                    R.drawable.user_male,
                    R.string.no_2014574416,
                    getString(R.string.phone_2014574416_zuma),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel74);
            StudentsModel studentsModel75 = new StudentsModel(getString(R.string.name_2014574417_sylvia),
                    getString(R.string.quote_2014574417_sylvia),
                    R.drawable.user_female,
                    R.string.no_2014574417,
                    getString(R.string.phone_2014574417_sylvia),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel75);
            StudentsModel studentsModel76 = new StudentsModel(getString(R.string.name_2014574419_lawrence),
                    getString(R.string.quote_2014574419_lawrence),
                    R.drawable.user_male,
                    R.string.no_2014574419,
                    getString(R.string.phone_2014574419_lawrence),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel76);
            StudentsModel studentsModel77 = new StudentsModel(getString(R.string.name_2014574420_jennifer),
                    getString(R.string.quote_2014574420_jennifer),
                    R.drawable.user_female,
                    R.string.no_2014574420,
                    getString(R.string.phone_2014574420_jennifer),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel77);
            StudentsModel studentsModel78 = new StudentsModel(getString(R.string.name_2014574421_fortunate),
                    getString(R.string.quote_2014574421_fortunate),
                    R.drawable.user_female,
                    R.string.no_2014574421,
                    getString(R.string.phone_2014574421_fortunate),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel78);
            StudentsModel studentsModel79 = new StudentsModel(getString(R.string.name_2014574422_anthony),
                    getString(R.string.quote_2014574422_anthony),
                    R.drawable.user_male,
                    R.string.no_2014574422,
                    getString(R.string.phone_2014574422_anthony),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel79);
            StudentsModel studentsModel80 = new StudentsModel(getString(R.string.name_2014574423_perpetual),
                    getString(R.string.quote_2014574423_perpetual),
                    R.drawable.user_female,
                    R.string.no_2014574423,
                    getString(R.string.phone_2014574423_perpetual),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel80);
            StudentsModel studentsModel81 = new StudentsModel(getString(R.string.name_2014574424_zikora),
                    getString(R.string.quote_2014574424_zikora),
                    R.drawable.user_male,
                    R.string.no_2014574424,
                    getString(R.string.phone_2014574424_zikora),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel81);
            StudentsModel studentsModel82 = new StudentsModel(getString(R.string.name_2014574425_mishael),
                    getString(R.string.quote_2014574425_mishael),
                    R.drawable.user_male,
                    R.string.no_2014574425,
                    getString(R.string.phone_2014574425_mishael),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel82);
            StudentsModel studentsModel83 = new StudentsModel(getString(R.string.name_2014574426_vincent),
                    getString(R.string.quote_2014574426_vincent),
                    R.drawable.user_male,
                    R.string.no_2014574426,
                    getString(R.string.phone_2014574426_vincent),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel83);
            StudentsModel studentsModel84 = new StudentsModel(getString(R.string.name_2014574427_chiemerie),
                    getString(R.string.quote_2014574427_chiemerie),
                    R.drawable.user_male,
                    R.string.no_2014574427,
                    getString(R.string.phone_2014574427_chiemerie),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel84);
            StudentsModel studentsModel85 = new StudentsModel(getString(R.string.name_2014574428_ifeanyi),
                    getString(R.string.quote_2014574428_ifeanyi),
                    R.drawable.user_male,
                    R.string.no_2014574428,
                    getString(R.string.phone_2014574428_ifeanyi),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel85);
            StudentsModel studentsModel86 = new StudentsModel(getString(R.string.name_2014574429_celestina),
                    getString(R.string.quote_2014574429_celestina),
                    R.drawable.user_female,
                    R.string.no_2014574429,
                    getString(R.string.phone_2014574429_celestina),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel86);
            StudentsModel studentsModel87 = new StudentsModel(getString(R.string.name_2014574432_chiazagom),
                    getString(R.string.quote_2014574432_chiazagom),
                    R.drawable.user_female,
                    R.string.no_2014574432,
                    getString(R.string.phone_2014574432_chiazagom),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel87);
            StudentsModel studentsModel88 = new StudentsModel(getString(R.string.name_2014574434_kennedy),
                    getString(R.string.quote_2014574434_kennedy),
                    R.drawable.user_male,
                    R.string.no_2014574434,
                    getString(R.string.phone_2014574434_kennedy),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel88);
            StudentsModel studentsModel89 = new StudentsModel(getString(R.string.name_2014574435_victor),
                    getString(R.string.quote_2014574435_victor),
                    R.drawable.user_male,
                    R.string.no_2014574435,
                    getString(R.string.phone_2014574435_victor),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel89);
            StudentsModel studentsModel90 = new StudentsModel(getString(R.string.name_2014574437_nduka),
                    getString(R.string.quote_2014574437_nduka),
                    R.drawable.user_male,
                    R.string.no_2014574437,
                    getString(R.string.phone_2014574437_nduka),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel90);
            StudentsModel studentsModel91 = new StudentsModel(getString(R.string.name_2014574438_gabriel),
                    getString(R.string.quote_2014574438_gabriel),
                    R.drawable.user_male,
                    R.string.no_2014574438,
                    getString(R.string.phone_2014574438_gabriel),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel91);
            StudentsModel studentsModel92 = new StudentsModel(getString(R.string.name_2014574440_emmanuel),
                    getString(R.string.quote_2014574440_emmanuel),
                    R.drawable.user_male,
                    R.string.no_2014574440,
                    getString(R.string.phone_2014574440_emmanuel),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel92);
            StudentsModel studentsModel93 = new StudentsModel(getString(R.string.name_2014464245_chisom),
                    getString(R.string.quote_2014464245_chisom),
                    R.drawable.user_male,
                    R.string.no_2014464245,
                    getString(R.string.phone_2014464245_chisom),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel93);
            StudentsModel studentsModel94 = new StudentsModel(getString(R.string.name_2013474386_ebuka),
                    getString(R.string.quote_2013474386_ebuka),
                    R.drawable.user_male,
                    R.string.no_2013474386,
                    getString(R.string.phone_2013474386_ebuka),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel94);
            StudentsModel studentsModel95 = new StudentsModel(getString(R.string.name_2013474278_mary),
                    getString(R.string.quote_2013474278_mary),
                    R.drawable.user_female,
                    R.string.no_2013474278,
                    getString(R.string.phone_2013474278_mary),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel95);
            StudentsModel studentsModel96 = new StudentsModel(getString(R.string.name_2013464162_angus),
                    getString(R.string.quote_2013464162_angus),
                    R.drawable.user_male,
                    R.string.no_2013464162,
                    getString(R.string.phone_2013464162_angus),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel96);
            StudentsModel studentsModel97 = new StudentsModel(getString(R.string.name_2013614232_victor),
                    getString(R.string.quote_2013614232_victor),
                    R.drawable.user_male,
                    R.string.no_2013614232,
                    getString(R.string.phone_2013614232_victor),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel97);
            StudentsModel studentsModel98 = new StudentsModel(getString(R.string.name_2013634226_maryjane),
                    getString(R.string.quote_2013634226_maryjane),
                    R.drawable.user_female,
                    R.string.no_2013634226,
                    getString(R.string.phone_2013634226_maryjane),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel98);
            StudentsModel studentsModel99 = new StudentsModel(getString(R.string.name_2012464169_linda),
                    getString(R.string.quote_2012464169_linda),
                    R.drawable.user_female,
                    R.string.no_2012464169,
                    getString(R.string.phone_2012464169_linda),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel99);
            StudentsModel studentsModel100 = new StudentsModel(getString(R.string.name_2011474018_christian),
                    getString(R.string.quote_2011474018_christian),
                    R.drawable.user_male,
                    R.string.no_2011474018,
                    getString(R.string.phone_2011474018_christian),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel100);
        } else {
            StudentsModel studentsModel = new StudentsModel(getString(R.string.name_2014574329_miriam),
                    getString(R.string.quote_2014574329_miriam),
                    IMAGE_URL + getString(R.string.no_2014574329) + ".jpg",
                    R.string.no_2014574329,
                    getString(R.string.phone_2014574329_miriam),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel);
            StudentsModel studentsModel1 = new StudentsModel(getString(R.string.name_2014574330_lawrence),
                    getString(R.string.quote_2014574330_lawrence),
                    IMAGE_URL + getString(R.string.no_2014574330) + ".jpg",
                    R.string.no_2014574330,
                    getString(R.string.phone_2014574330_lawrence),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel1);
            StudentsModel studentsModel2 = new StudentsModel(getString(R.string.name_2014574331_prisca),
                    getString(R.string.quote_2014574331_prisca),
                    IMAGE_URL + getString(R.string.no_2014574331) + ".jpg",
                    R.string.no_2014574331,
                    getString(R.string.phone_2014574331_prisca),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel2);
            StudentsModel studentsModel3 = new StudentsModel(getString(R.string.name_2014574332_doris),
                    getString(R.string.quote_2014574332_doris),
                    IMAGE_URL + getString(R.string.no_2014574332) + ".jpg",
                    R.string.no_2014574332,
                    getString(R.string.phone_2014574332_doris),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel3);
            StudentsModel studentsModel4 = new StudentsModel(getString(R.string.name_2014574333_francess),
                    getString(R.string.quote_2014574333_francess),
                    IMAGE_URL + getString(R.string.no_2014574333) + ".jpg",
                    R.string.no_2014574333,
                    getString(R.string.phone_2014574333_francess),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel4);
            StudentsModel studentsModel5 = new StudentsModel(getString(R.string.name_2014574335_christian),
                    getString(R.string.quote_2014574335_christian),
                    IMAGE_URL + getString(R.string.no_2014574335) + ".jpg",
                    R.string.no_2014574335,
                    getString(R.string.phone_2014574335_christian),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel5);
            StudentsModel studentsModel6 = new StudentsModel(getString(R.string.name_2014574336_chioma),
                    getString(R.string.quote_2014574336_chioma),
                    IMAGE_URL + getString(R.string.no_2014574336) + ".jpg",
                    R.string.no_2014574336,
                    getString(R.string.phone_2014574336_chioma),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel6);
            StudentsModel studentsModel7 = new StudentsModel(getString(R.string.name_2014574337_favour),
                    getString(R.string.quote_2014574337_favour),
                    IMAGE_URL + getString(R.string.no_2014574337) + ".jpg",
                    R.string.no_2014574337,
                    getString(R.string.phone_2014574337_favour),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel7);
            StudentsModel studentsModel8 = new StudentsModel(getString(R.string.name_2014574338_kenechi),
                    getString(R.string.quote_2014574338_kenechi),
                    IMAGE_URL + getString(R.string.no_2014574338) + ".jpg",
                    R.string.no_2014574338,
                    getString(R.string.phone_2014574338_kenechi),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel8);
            StudentsModel studentsModel9 = new StudentsModel(getString(R.string.name_2014574339_joshua),
                    getString(R.string.quote_2014574339_joshua),
                    IMAGE_URL + getString(R.string.no_2014574339) + ".jpg",
                    R.string.no_2014574339,
                    getString(R.string.phone_2014574339_joshua),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel9);
            StudentsModel studentsModel10 = new StudentsModel(getString(R.string.name_2014574340_chioma_rita),
                    getString(R.string.quote_2014574340_chioma_rita),
                    IMAGE_URL + getString(R.string.no_2014574340) + ".jpg",
                    R.string.no_2014574340,
                    getString(R.string.phone_2014574340_chioma_rita),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel10);
            StudentsModel studentsModel11 = new StudentsModel(getString(R.string.name_2014574342_makuochukwu),
                    getString(R.string.quote_2014574342_makuochukwu),
                    IMAGE_URL + getString(R.string.no_2014574342) + ".jpg",
                    R.string.no_2014574342,
                    getString(R.string.phone_2014574342_makuochukwu),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel11);
            StudentsModel studentsModel12 = new StudentsModel(getString(R.string.name_2014574343_lilian),
                    getString(R.string.quote_2014574343_lilian),
                    IMAGE_URL + getString(R.string.no_2014574343) + ".jpg",
                    R.string.no_2014574343,
                    getString(R.string.phone_2014574343_lilian),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel12);
            StudentsModel studentsModel13 = new StudentsModel(getString(R.string.name_2014574344_collins),
                    getString(R.string.quote_2014574344_collins),
                    IMAGE_URL + getString(R.string.no_2014574344) + ".jpg",
                    R.string.no_2014574344,
                    getString(R.string.phone_2014574344_collins),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel13);
            StudentsModel studentsModel14 = new StudentsModel(getString(R.string.name_2014574345_obianuju),
                    getString(R.string.quote_2014574345_obianuju),
                    IMAGE_URL + getString(R.string.no_2014574345) + ".jpg",
                    R.string.no_2014574345,
                    getString(R.string.phone_2014574345_obianuju),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel14);
            StudentsModel studentsModel15 = new StudentsModel(getString(R.string.name_2014574346_goodness),
                    getString(R.string.quote_2014574346_goodness),
                    IMAGE_URL + getString(R.string.no_2014574346) + ".jpg",
                    R.string.no_2014574346,
                    getString(R.string.phone_2014574346_goodness),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel15);
            StudentsModel studentsModel16 = new StudentsModel(getString(R.string.name_2014574347_ikechukwu),
                    getString(R.string.quote_2014574347_ikechukwu),
                    IMAGE_URL + getString(R.string.no_2014574347) + ".jpg",
                    R.string.no_2014574347,
                    getString(R.string.phone_2014574347_ikechukwu),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel16);
            StudentsModel studentsModel17 = new StudentsModel(getString(R.string.name_2014574348_precious),
                    getString(R.string.quote_2014574348_precious),
                    IMAGE_URL + getString(R.string.no_2014574348) + ".jpg",
                    R.string.no_2014574348,
                    getString(R.string.phone_2014574348_precious),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel17);
            StudentsModel studentsModel18 = new StudentsModel(getString(R.string.name_2014574350_charity),
                    getString(R.string.quote_2014574350_charity),
                    IMAGE_URL + getString(R.string.no_2014574350) + ".jpg",
                    R.string.no_2014574350,
                    getString(R.string.phone_2014574350_charity),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel18);
            StudentsModel studentsModel19 = new StudentsModel(getString(R.string.name_2014574352_kingsley),
                    getString(R.string.quote_2014574352_kingsley),
                    IMAGE_URL + getString(R.string.no_2014574352) + ".jpg",
                    R.string.no_2014574352,
                    getString(R.string.phone_2014574352_kingsley),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel19);
            StudentsModel studentsModel20 = new StudentsModel(getString(R.string.name_2014574354_augustine),
                    getString(R.string.quote_2014574354_augustine),
                    IMAGE_URL + getString(R.string.no_2014574354) + ".jpg",
                    R.string.no_2014574354,
                    getString(R.string.phone_2014574354_augustine),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel20);
            StudentsModel studentsModel21 = new StudentsModel(getString(R.string.name_2014574355_judith),
                    getString(R.string.quote_2014574355_judith),
                    IMAGE_URL + getString(R.string.no_2014574355) + ".jpg",
                    R.string.no_2014574355,
                    getString(R.string.phone_2014574355_judith),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel21);
            StudentsModel studentsModel22 = new StudentsModel(getString(R.string.name_2014574356_onyinyechi),
                    getString(R.string.quote_2014574356_onyinyechi),
                    IMAGE_URL + getString(R.string.no_2014574356) + ".jpg",
                    R.string.no_2014574356,
                    getString(R.string.phone_2014574356_onyinyechi),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel22);
            StudentsModel studentsModel243 = new StudentsModel(getString(R.string.name_2014574357_emeka),
                    getString(R.string.quote_2014574357_emeka),
                    IMAGE_URL + getString(R.string.no_2014574357) + ".jpg",
                    R.string.no_2014574357,
                    getString(R.string.phone_2014574357_emeka),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel243);
            StudentsModel studentsModel24 = new StudentsModel(getString(R.string.name_2014574358_kelvin),
                    getString(R.string.quote_2014574358_kelvin),
                    IMAGE_URL + getString(R.string.no_2014574358) + ".jpg",
                    R.string.no_2014574358,
                    getString(R.string.phone_2014574358_kelvin),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel24);
            StudentsModel studentsModel25 = new StudentsModel(getString(R.string.name_2014574359_ndidi),
                    getString(R.string.quote_2014574359_ndidi),
                    IMAGE_URL + getString(R.string.no_2014574359) + ".jpg",
                    R.string.no_2014574359,
                    getString(R.string.phone_2014574359_ndidi),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel25);
            StudentsModel studentsModel26 = new StudentsModel(getString(R.string.name_2014574360_blessing),
                    getString(R.string.quote_2014574360_blessing),
                    IMAGE_URL + getString(R.string.no_2014574360) + ".jpg",
                    R.string.no_2014574360,
                    getString(R.string.phone_2014574360_blessing),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel26);
            StudentsModel studentsModel27 = new StudentsModel(getString(R.string.name_2014574361_chidiebere),
                    getString(R.string.quote_2014574361_chidiebere),
                    IMAGE_URL + getString(R.string.no_2014574361) + ".jpg",
                    R.string.no_2014574361,
                    getString(R.string.phone_2014574361_chidiebere),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel27);
            StudentsModel studentsModel28 = new StudentsModel(getString(R.string.name_2014574363_adaobi),
                    getString(R.string.quote_2014574363_adaobi),
                    IMAGE_URL + getString(R.string.no_2014574363) + ".jpg",
                    R.string.no_2014574363,
                    getString(R.string.phone_2014574363_adaobi),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel28);
            StudentsModel studentsModel29 = new StudentsModel(getString(R.string.name_2014574364_amaobi),
                    getString(R.string.quote_2014574364_amaobi),
                    IMAGE_URL + getString(R.string.no_2014574364) + ".jpg",
                    R.string.no_2014574364,
                    getString(R.string.phone_2014574364_amaobi),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel29);
            StudentsModel studentsModel30 = new StudentsModel(getString(R.string.name_2014574365_augustina),
                    getString(R.string.quote_2014574365_augustina),
                    IMAGE_URL + getString(R.string.no_2014574365) + ".jpg",
                    R.string.no_2014574365,
                    getString(R.string.phone_2014574365_augustina),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel30);
            StudentsModel studentsModel31 = new StudentsModel(getString(R.string.name_2014574366_cynthia),
                    getString(R.string.quote_2014574366_cynthia),
                    IMAGE_URL + getString(R.string.no_2014574366) + ".jpg",
                    R.string.no_2014574366,
                    getString(R.string.phone_2014574366_cynthia),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel31);
            StudentsModel studentsModel32 = new StudentsModel(getString(R.string.name_2014574367_ifechukwu),
                    getString(R.string.quote_2014574367_ifechukwu),
                    IMAGE_URL + getString(R.string.no_2014574367) + ".jpg",
                    R.string.no_2014574367,
                    getString(R.string.phone_2014574367_ifechukwu),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel32);
            StudentsModel studentsModel33 = new StudentsModel(getString(R.string.name_2014574368_chiamaka),
                    getString(R.string.quote_2014574368_chiamaka),
                    IMAGE_URL + getString(R.string.no_2014574368) + ".jpg",
                    R.string.no_2014574368,
                    getString(R.string.phone_2014574368_chiamaka),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel33);
            StudentsModel studentsModel34 = new StudentsModel(getString(R.string.name_2014574369_emezie),
                    getString(R.string.quote_2014574369_emezie),
                    IMAGE_URL + getString(R.string.no_2014574369) + ".jpg",
                    R.string.no_2014574369,
                    getString(R.string.phone_2014574369_emezie),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel34);
            StudentsModel studentsModel35 = new StudentsModel(getString(R.string.name_2014574370_gigabyte),
                    getString(R.string.quote_2014574370_gigabyte),
                    IMAGE_URL + getString(R.string.no_2014574370) + ".jpg",
                    R.string.no_2014574370,
                    getString(R.string.phone_2014574370_gigabyte),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel35);
            StudentsModel studentsModel36 = new StudentsModel(getString(R.string.name_2014574372_pauline),
                    getString(R.string.quote_2014574372_pauline),
                    IMAGE_URL + getString(R.string.no_2014574372) + ".jpg",
                    R.string.no_2014574372,
                    getString(R.string.phone_2014574372_pauline),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel36);
            StudentsModel studentsModel37 = new StudentsModel(getString(R.string.name_2014574373_ijeoma),
                    getString(R.string.quote_2014574373_ijeoma),
                    IMAGE_URL + getString(R.string.no_2014574373) + ".jpg",
                    R.string.no_2014574373,
                    getString(R.string.phone_2014574373_ijeoma),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel37);
            StudentsModel studentsModel38 = new StudentsModel(getString(R.string.name_2014574374_onyekachi),
                    getString(R.string.quote_2014574374_onyekachi),
                    IMAGE_URL + getString(R.string.no_2014574374) + ".jpg",
                    R.string.no_2014574374,
                    getString(R.string.phone_2014574374_onyekachi),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel38);
            StudentsModel studentsModel39 = new StudentsModel(getString(R.string.name_2014574375_vanessa),
                    getString(R.string.quote_2014574375_vanessa),
                    IMAGE_URL + getString(R.string.no_2014574375) + ".jpg",
                    R.string.no_2014574375,
                    getString(R.string.phone_2014574375_vanessa),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel39);
            StudentsModel studentsModel40 = new StudentsModel(getString(R.string.name_2014574376_susan),
                    getString(R.string.quote_2014574376_susan),
                    IMAGE_URL + getString(R.string.no_2014574376) + ".jpg",
                    R.string.no_2014574376,
                    getString(R.string.phone_2014574376_susan),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel40);
            StudentsModel studentsModel41 = new StudentsModel(getString(R.string.name_2014574377_rosemary),
                    getString(R.string.quote_2014574377_rosemary),
                    IMAGE_URL + getString(R.string.no_2014574377) + ".jpg",
                    R.string.no_2014574377,
                    getString(R.string.phone_2014574377_rosemary),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel41);
            StudentsModel studentsModel42 = new StudentsModel(getString(R.string.name_2014574378_chukwusom),
                    getString(R.string.quote_2014574378_chukwusom),
                    IMAGE_URL + getString(R.string.no_2014574378) + ".jpg",
                    R.string.no_2014574378,
                    getString(R.string.phone_2014574378_chukwusom),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel42);
            StudentsModel studentsModel43 = new StudentsModel(getString(R.string.name_2014574379_friday),
                    getString(R.string.quote_2014574379_friday),
                    IMAGE_URL + getString(R.string.no_2014574379) + ".jpg",
                    R.string.no_2014574379,
                    getString(R.string.phone_2014574379_friday),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel43);
            StudentsModel studentsModel44 = new StudentsModel(getString(R.string.name_2014574380_blessing),
                    getString(R.string.quote_2014574380_blessing),
                    IMAGE_URL + getString(R.string.no_2014574380) + ".jpg",
                    R.string.no_2014574380,
                    getString(R.string.phone_2014574380_blessing),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel44);
            StudentsModel studentsModel45 = new StudentsModel(getString(R.string.name_2014574383_chidiogo),
                    getString(R.string.quote_2014574383_chidiogo),
                    IMAGE_URL + getString(R.string.no_2014574383) + ".jpg",
                    R.string.no_2014574383,
                    getString(R.string.phone_2014574383_chidiogo),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel45);
            StudentsModel studentsModel46 = new StudentsModel(getString(R.string.name_2014574384_joyce),
                    getString(R.string.quote_2014574384_joyce),
                    IMAGE_URL + getString(R.string.no_2014574384) + ".jpg",
                    R.string.no_2014574384,
                    getString(R.string.phone_2014574384_joyce),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel46);
            StudentsModel studentsModel47 = new StudentsModel(getString(R.string.name_2014574385_peter),
                    getString(R.string.quote_2014574385_peter),
                    IMAGE_URL + getString(R.string.no_2014574385) + ".jpg",
                    R.string.no_2014574385,
                    getString(R.string.phone_2014574385_peter),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel47);
            StudentsModel studentsModel48 = new StudentsModel(getString(R.string.name_2014574386_daniel),
                    getString(R.string.quote_2014574386_daniel),
                    IMAGE_URL + getString(R.string.no_2014574386) + ".jpg",
                    R.string.no_2014574386,
                    getString(R.string.phone_2014574386_daniel),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel48);
            StudentsModel studentsModel49 = new StudentsModel(getString(R.string.name_2014574387_vincent),
                    getString(R.string.quote_2014574387_vincent),
                    IMAGE_URL + getString(R.string.no_2014574387) + ".jpg",
                    R.string.no_2014574387,
                    getString(R.string.phone_2014574387_vincent),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel49);
            StudentsModel studentsModel50 = new StudentsModel(getString(R.string.name_2014574388_chinasa),
                    getString(R.string.quote_2014574388_chinasa),
                    IMAGE_URL + getString(R.string.no_2014574388) + ".jpg",
                    R.string.no_2014574388,
                    getString(R.string.phone_2014574388_chinasa),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel50);
            StudentsModel studentsModel51 = new StudentsModel(getString(R.string.name_2014574390_hope),
                    getString(R.string.quote_2014574390_hope),
                    IMAGE_URL + getString(R.string.no_2014574390) + ".jpg",
                    R.string.no_2014574390,
                    getString(R.string.phone_2014574390_hope),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel51);
            StudentsModel studentsModel52 = new StudentsModel(getString(R.string.name_2014574392_florence),
                    getString(R.string.quote_2014574392_florence),
                    IMAGE_URL + getString(R.string.no_2014574392) + ".jpg",
                    R.string.no_2014574392,
                    getString(R.string.phone_2014574392_florence),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel52);
            StudentsModel studentsModel53 = new StudentsModel(getString(R.string.name_2014574393_patience),
                    getString(R.string.quote_2014574393_patience),
                    IMAGE_URL + getString(R.string.no_2014574393) + ".jpg",
                    R.string.no_2014574393,
                    getString(R.string.phone_2014574393_patience),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel53);
            StudentsModel studentsModel54 = new StudentsModel(getString(R.string.name_2014574395_blessing),
                    getString(R.string.quote_2014574395_blessing),
                    IMAGE_URL + getString(R.string.no_2014574395) + ".jpg",
                    R.string.no_2014574395,
                    getString(R.string.phone_2014574395_blessing),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel54);
            StudentsModel studentsModel55 = new StudentsModel(getString(R.string.name_2014574396_shalom),
                    getString(R.string.quote_2014574396_shalom),
                    IMAGE_URL + getString(R.string.no_2014574396) + ".jpg",
                    R.string.no_2014574396,
                    getString(R.string.phone_2014574396_shalom),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel55);
            StudentsModel studentsModel56 = new StudentsModel(getString(R.string.name_2014574397_obumneme),
                    getString(R.string.quote_2014574397_obumneme),
                    IMAGE_URL + getString(R.string.no_2014574397) + ".jpg",
                    R.string.no_2014574397,
                    getString(R.string.phone_2014574397_obumneme),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel56);
            StudentsModel studentsModel57 = new StudentsModel(getString(R.string.name_2014574398_judith),
                    getString(R.string.quote_2014574398_judith),
                    IMAGE_URL + getString(R.string.no_2014574398) + ".jpg",
                    R.string.no_2014574398,
                    getString(R.string.phone_2014574398_judith),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel57);
            StudentsModel studentsModel58 = new StudentsModel(getString(R.string.name_2014574399_tobechukwu),
                    getString(R.string.quote_2014574399_tobechukwu),
                    IMAGE_URL + getString(R.string.no_2014574399) + ".jpg",
                    R.string.no_2014574399,
                    getString(R.string.phone_2014574399_tobechukwu),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel58);
            StudentsModel studentsModel59 = new StudentsModel(getString(R.string.name_2014574400_jennifer),
                    getString(R.string.quote_2014574400_jennifer),
                    IMAGE_URL + getString(R.string.no_2014574400) + ".jpg",
                    R.string.no_2014574400,
                    getString(R.string.phone_2014574400_jennifer),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel59);
            StudentsModel studentsModel60 = new StudentsModel(getString(R.string.name_2014574401_blessing),
                    getString(R.string.quote_2014574401_blessing),
                    IMAGE_URL + getString(R.string.no_2014574401) + ".jpg",
                    R.string.no_2014574401,
                    getString(R.string.phone_2014574401_blessing),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel60);
            StudentsModel studentsModel61 = new StudentsModel(getString(R.string.name_2014574402_ogechukwu),
                    getString(R.string.quote_2014574402_ogechukwu),
                    IMAGE_URL + getString(R.string.no_2014574402) + ".jpg",
                    R.string.no_2014574402,
                    getString(R.string.phone_2014574402_ogechukwu),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel61);
            StudentsModel studentsModel62 = new StudentsModel(getString(R.string.name_2014574403_faith),
                    getString(R.string.quote_2014574403_faith),
                    IMAGE_URL + getString(R.string.no_2014574403) + ".jpg",
                    R.string.no_2014574403,
                    getString(R.string.phone_2014574403_faith),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel62);
            StudentsModel studentsModel63 = new StudentsModel(getString(R.string.name_2014574404_kenneth),
                    getString(R.string.quote_2014574404_kenneth),
                    IMAGE_URL + getString(R.string.no_2014574404) + ".jpg",
                    R.string.no_2014574404,
                    getString(R.string.phone_2014574404_kenneth),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel63);
            StudentsModel studentsModel64 = new StudentsModel(getString(R.string.name_2014574405_samuel),
                    getString(R.string.quote_2014574405_samuel),
                    IMAGE_URL + getString(R.string.no_2014574405) + ".jpg",
                    R.string.no_2014574405,
                    getString(R.string.phone_2014574405_samuel),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel64);
            StudentsModel studentsModel65 = new StudentsModel(getString(R.string.name_2014574406_henry),
                    getString(R.string.quote_2014574406_henry),
                    IMAGE_URL + getString(R.string.no_2014574406) + ".jpg",
                    R.string.no_2014574406,
                    getString(R.string.phone_2014574406_henry),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel65);
            StudentsModel studentsModel66 = new StudentsModel(getString(R.string.name_2014574407_maryjane),
                    getString(R.string.quote_2014574407_maryjane),
                    IMAGE_URL + getString(R.string.no_2014574407) + ".jpg",
                    R.string.no_2014574407,
                    getString(R.string.phone_2014574407_maryjane),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel66);
            StudentsModel studentsModel67 = new StudentsModel(getString(R.string.name_2014574408_ifeanyi),
                    getString(R.string.quote_2014574408_ifeanyi),
                    IMAGE_URL + getString(R.string.no_2014574408) + ".jpg",
                    R.string.no_2014574408,
                    getString(R.string.phone_2014574408_ifeanyi),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel67);
            StudentsModel studentsModel68 = new StudentsModel(getString(R.string.name_2014574409_rita),
                    getString(R.string.quote_2014574409_rita),
                    IMAGE_URL + getString(R.string.no_2014574409) + ".jpg",
                    R.string.no_2014574409,
                    getString(R.string.phone_2014574409_rita),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel68);
            StudentsModel studentsModel69 = new StudentsModel(getString(R.string.name_2014574410_precious),
                    getString(R.string.quote_2014574410_precious),
                    IMAGE_URL + getString(R.string.no_2014574410) + ".jpg",
                    R.string.no_2014574410,
                    getString(R.string.phone_2014574410_precious),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel69);
            StudentsModel studentsModel70 = new StudentsModel(getString(R.string.name_2014574412_smiling),
                    getString(R.string.quote_2014574412_smiling),
                    IMAGE_URL + getString(R.string.no_2014574412) + ".jpg",
                    R.string.no_2014574412,
                    getString(R.string.phone_2014574412_smiling),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel70);
            StudentsModel studentsModel71 = new StudentsModel(getString(R.string.name_2014574413_peace),
                    getString(R.string.quote_2014574413_peace),
                    IMAGE_URL + getString(R.string.no_2014574413) + ".jpg",
                    R.string.no_2014574413,
                    getString(R.string.phone_2014574413_peace),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel71);
            StudentsModel studentsModel72 = new StudentsModel(getString(R.string.name_2014574414_emskaro),
                    getString(R.string.quote_2014574414_emskaro),
                    IMAGE_URL + getString(R.string.no_2014574414) + ".jpg",
                    R.string.no_2014574414,
                    getString(R.string.phone_2014574414_emskaro),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel72);
            StudentsModel studentsModel73 = new StudentsModel(getString(R.string.name_2014574415_franca),
                    getString(R.string.quote_2014574415_franca),
                    IMAGE_URL + getString(R.string.no_2014574415) + ".jpg",
                    R.string.no_2014574415,
                    getString(R.string.phone_2014574415_franca),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel73);
            StudentsModel studentsModel74 = new StudentsModel(getString(R.string.name_2014574416_zuma),
                    getString(R.string.quote_2014574416_zuma),
                    IMAGE_URL + getString(R.string.no_2014574416) + ".jpg",
                    R.string.no_2014574416,
                    getString(R.string.phone_2014574416_zuma),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel74);
            StudentsModel studentsModel75 = new StudentsModel(getString(R.string.name_2014574417_sylvia),
                    getString(R.string.quote_2014574417_sylvia),
                    IMAGE_URL + getString(R.string.no_2014574417) + ".jpg",
                    R.string.no_2014574417,
                    getString(R.string.phone_2014574417_sylvia),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel75);
            StudentsModel studentsModel76 = new StudentsModel(getString(R.string.name_2014574419_lawrence),
                    getString(R.string.quote_2014574419_lawrence),
                    IMAGE_URL + getString(R.string.no_2014574419) + ".jpg",
                    R.string.no_2014574419,
                    getString(R.string.phone_2014574419_lawrence),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel76);
            StudentsModel studentsModel77 = new StudentsModel(getString(R.string.name_2014574420_jennifer),
                    getString(R.string.quote_2014574420_jennifer),
                    IMAGE_URL + getString(R.string.no_2014574420) + ".jpg",
                    R.string.no_2014574420,
                    getString(R.string.phone_2014574420_jennifer),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel77);
            StudentsModel studentsModel78 = new StudentsModel(getString(R.string.name_2014574421_fortunate),
                    getString(R.string.quote_2014574421_fortunate),
                    IMAGE_URL + getString(R.string.no_2014574421) + ".jpg",
                    R.string.no_2014574421,
                    getString(R.string.phone_2014574421_fortunate),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel78);
            StudentsModel studentsModel79 = new StudentsModel(getString(R.string.name_2014574422_anthony),
                    getString(R.string.quote_2014574422_anthony),
                    IMAGE_URL + getString(R.string.no_2014574422) + ".jpg",
                    R.string.no_2014574422,
                    getString(R.string.phone_2014574422_anthony),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel79);
            StudentsModel studentsModel80 = new StudentsModel(getString(R.string.name_2014574423_perpetual),
                    getString(R.string.quote_2014574423_perpetual),
                    IMAGE_URL + getString(R.string.no_2014574423) + ".jpg",
                    R.string.no_2014574423,
                    getString(R.string.phone_2014574423_perpetual),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel80);
            StudentsModel studentsModel81 = new StudentsModel(getString(R.string.name_2014574424_zikora),
                    getString(R.string.quote_2014574424_zikora),
                    IMAGE_URL + getString(R.string.no_2014574424) + ".jpg",
                    R.string.no_2014574424,
                    getString(R.string.phone_2014574424_zikora),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel81);
            StudentsModel studentsModel82 = new StudentsModel(getString(R.string.name_2014574425_mishael),
                    getString(R.string.quote_2014574425_mishael),
                    IMAGE_URL + getString(R.string.no_2014574425) + ".jpg",
                    R.string.no_2014574425,
                    getString(R.string.phone_2014574425_mishael),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel82);
            StudentsModel studentsModel83 = new StudentsModel(getString(R.string.name_2014574426_vincent),
                    getString(R.string.quote_2014574426_vincent),
                    IMAGE_URL + getString(R.string.no_2014574426) + ".jpg",
                    R.string.no_2014574426,
                    getString(R.string.phone_2014574426_vincent),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel83);
            StudentsModel studentsModel84 = new StudentsModel(getString(R.string.name_2014574427_chiemerie),
                    getString(R.string.quote_2014574427_chiemerie),
                    IMAGE_URL + getString(R.string.no_2014574427) + ".jpg",
                    R.string.no_2014574427,
                    getString(R.string.phone_2014574427_chiemerie),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel84);
            StudentsModel studentsModel85 = new StudentsModel(getString(R.string.name_2014574428_ifeanyi),
                    getString(R.string.quote_2014574428_ifeanyi),
                    IMAGE_URL + getString(R.string.no_2014574428) + ".jpg",
                    R.string.no_2014574428,
                    getString(R.string.phone_2014574428_ifeanyi),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel85);
            StudentsModel studentsModel86 = new StudentsModel(getString(R.string.name_2014574429_celestina),
                    getString(R.string.quote_2014574429_celestina),
                    IMAGE_URL + getString(R.string.no_2014574429) + ".jpg",
                    R.string.no_2014574429,
                    getString(R.string.phone_2014574429_celestina),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel86);
            StudentsModel studentsModel87 = new StudentsModel(getString(R.string.name_2014574432_chiazagom),
                    getString(R.string.quote_2014574432_chiazagom),
                    IMAGE_URL + getString(R.string.no_2014574432) + ".jpg",
                    R.string.no_2014574432,
                    getString(R.string.phone_2014574432_chiazagom),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel87);
            StudentsModel studentsModel88 = new StudentsModel(getString(R.string.name_2014574434_kennedy),
                    getString(R.string.quote_2014574434_kennedy),
                    IMAGE_URL + getString(R.string.no_2014574434) + ".jpg",
                    R.string.no_2014574434,
                    getString(R.string.phone_2014574434_kennedy),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel88);
            StudentsModel studentsModel89 = new StudentsModel(getString(R.string.name_2014574435_victor),
                    getString(R.string.quote_2014574435_victor),
                    IMAGE_URL + getString(R.string.no_2014574435) + ".jpg",
                    R.string.no_2014574435,
                    getString(R.string.phone_2014574435_victor),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel89);
            StudentsModel studentsModel90 = new StudentsModel(getString(R.string.name_2014574437_nduka),
                    getString(R.string.quote_2014574437_nduka),
                    IMAGE_URL + getString(R.string.no_2014574437) + ".jpg",
                    R.string.no_2014574437,
                    getString(R.string.phone_2014574437_nduka),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel90);
            StudentsModel studentsModel91 = new StudentsModel(getString(R.string.name_2014574438_gabriel),
                    getString(R.string.quote_2014574438_gabriel),
                    IMAGE_URL + getString(R.string.no_2014574438) + ".jpg",
                    R.string.no_2014574438,
                    getString(R.string.phone_2014574438_gabriel),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel91);
            StudentsModel studentsModel92 = new StudentsModel(getString(R.string.name_2014574440_emmanuel),
                    getString(R.string.quote_2014574440_emmanuel),
                    IMAGE_URL + getString(R.string.no_2014574440) + ".jpg",
                    R.string.no_2014574440,
                    getString(R.string.phone_2014574440_emmanuel),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel92);
            StudentsModel studentsModel93 = new StudentsModel(getString(R.string.name_2014464245_chisom),
                    getString(R.string.quote_2014464245_chisom),
                    IMAGE_URL + getString(R.string.no_2014464245) + ".jpg",
                    R.string.no_2014464245,
                    getString(R.string.phone_2014464245_chisom),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel93);
            StudentsModel studentsModel94 = new StudentsModel(getString(R.string.name_2013474386_ebuka),
                    getString(R.string.quote_2013474386_ebuka),
                    IMAGE_URL + getString(R.string.no_2013474386) + ".jpg",
                    R.string.no_2013474386,
                    getString(R.string.phone_2013474386_ebuka),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel94);
            StudentsModel studentsModel95 = new StudentsModel(getString(R.string.name_2013474278_mary),
                    getString(R.string.quote_2013474278_mary),
                    IMAGE_URL + getString(R.string.no_2013474278) + ".jpg",
                    R.string.no_2013474278,
                    getString(R.string.phone_2013474278_mary),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel95);
            StudentsModel studentsModel96 = new StudentsModel(getString(R.string.name_2013464162_angus),
                    getString(R.string.quote_2013464162_angus),
                    IMAGE_URL + getString(R.string.no_2013464162) + ".jpg",
                    R.string.no_2013464162,
                    getString(R.string.phone_2013464162_angus),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel96);
            StudentsModel studentsModel97 = new StudentsModel(getString(R.string.name_2013614232_victor),
                    getString(R.string.quote_2013614232_victor),
                    IMAGE_URL + getString(R.string.no_2013614232) + ".jpg",
                    R.string.no_2013614232,
                    getString(R.string.phone_2013614232_victor),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel97);
            StudentsModel studentsModel98 = new StudentsModel(getString(R.string.name_2013634226_maryjane),
                    getString(R.string.quote_2013634226_maryjane),
                    IMAGE_URL + getString(R.string.no_2013634226) + ".jpg",
                    R.string.no_2013634226,
                    getString(R.string.phone_2013634226_maryjane),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel98);
            StudentsModel studentsModel99 = new StudentsModel(getString(R.string.name_2012464169_linda),
                    getString(R.string.quote_2012464169_linda),
                    IMAGE_URL + getString(R.string.no_2012464169) + ".jpg",
                    R.string.no_2012464169,
                    getString(R.string.phone_2012464169_linda),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel99);
            StudentsModel studentsModel100 = new StudentsModel(getString(R.string.name_2011474018_christian),
                    getString(R.string.quote_2011474018_christian),
                    IMAGE_URL + getString(R.string.no_2011474018) + ".jpg",
                    R.string.no_2011474018,
                    getString(R.string.phone_2011474018_christian),
                    FACEBOOK_URL,
                    INSTAGRAM_URL);
            list.add(studentsModel100);
        }
    }

    private void studentFullInfo(){
        if (!haveNetworkConnection()){
            StudentsModel studentsModel = new StudentsModel(
                    getString(R.string.name_2014574329_miriam),
                    getString(R.string.quote_2014574329_miriam),
                    R.drawable.user_female,
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
                    R.drawable.user_male,
                    getString(R.string.phone_2014574330_lawrence),
                    getString(R.string.day_3) + " " + getString(R.string.jun),
                    getString(R.string.email_2014574330_lawrence),
                    getString(R.string.lga_2014574330_lawrence),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574330_lawrence));
            listFull.add(studentsModel1);
            StudentsModel studentsModel2 = new StudentsModel(getString(R.string.name_2014574331_prisca),
                    getString(R.string.quote_2014574331_prisca),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574331_prisca),
                    getString(R.string.day_22) + " " + getString(R.string.jul),
                    getString(R.string.email_2014574331_prisca),
                    getString(R.string.lga_2014574331_prisca),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574331_prisca));
            listFull.add(studentsModel2);
            StudentsModel studentsModel3 = new StudentsModel(getString(R.string.name_2014574332_doris),
                    getString(R.string.quote_2014574332_doris),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574332_doris),
                    getString(R.string.day_9) + " " + getString(R.string.jul),
                    getString(R.string.email_2014574332_doris),
                    getString(R.string.lga_2014574332_doris),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574332_doris));
            listFull.add(studentsModel3);
            StudentsModel studentsModel4 = new StudentsModel(getString(R.string.name_2014574333_francess),
                    getString(R.string.quote_2014574333_francess),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574333_francess),
                    getString(R.string.day_8) + " " + getString(R.string.apr),
                    getString(R.string.email_2014574333_francess),
                    getString(R.string.lga_2014574333_francess),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574333_francess));
            listFull.add(studentsModel4);
            StudentsModel studentsModel5 = new StudentsModel(getString(R.string.name_2014574335_christian),
                    getString(R.string.quote_2014574335_christian),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574335_christian),
                    getString(R.string.day_29) + " " + getString(R.string.may),
                    getString(R.string.email_2014574335_christian),
                    getString(R.string.lga_2014574335_christian),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574335_christian));
            listFull.add(studentsModel5);
            StudentsModel studentsModel6 = new StudentsModel(getString(R.string.name_2014574336_chioma),
                    getString(R.string.quote_2014574336_chioma),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574336_chioma),
                    getString(R.string.day_21) + " " + getString(R.string.sept),
                    getString(R.string.email_2014574336_chioma),
                    getString(R.string.lga_2014574336_chioma),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574336_chioma));
            listFull.add(studentsModel6);
            StudentsModel studentsModel7 = new StudentsModel(getString(R.string.name_2014574337_favour),
                    getString(R.string.quote_2014574337_favour),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574337_favour),
                    getString(R.string.day_27) + " " + getString(R.string.nov),
                    getString(R.string.email_2014574337_favour),
                    getString(R.string.lga_2014574337_favour),
                    getString(R.string.state_Rivers),
                    getString(R.string.nick_2014574337_favour));
            listFull.add(studentsModel7);
            StudentsModel studentsModel8 = new StudentsModel(getString(R.string.name_2014574338_kenechi),
                    getString(R.string.quote_2014574338_kenechi),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574338_kenechi),
                    getString(R.string.day_13) + " " + getString(R.string.apr),
                    getString(R.string.email_2014574338_kenechi),
                    getString(R.string.lga_2014574338_kenechi),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574338_kenechi));
            listFull.add(studentsModel8);
            StudentsModel studentsModel9 = new StudentsModel(getString(R.string.name_2014574339_joshua),
                    getString(R.string.quote_2014574339_joshua),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574339_joshua),
                    getString(R.string.date_unknown),
                    getString(R.string.email_2014574339_joshua),
                    getString(R.string.lga_2014574339_joshua),
                    getString(R.string.state_Abia),
                    getString(R.string.nick_2014574339_joshua));
            listFull.add(studentsModel9);
            StudentsModel studentsModel10 = new StudentsModel(getString(R.string.name_2014574340_chioma_rita),
                    getString(R.string.quote_2014574340_chioma_rita),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574340_chioma_rita),
                    getString(R.string.day_20) + " " + getString(R.string.sept),
                    getString(R.string.email_2014574340_chioma_rita),
                    getString(R.string.lga_2014574340_chioma_rita),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574340_chioma_rita));
            listFull.add(studentsModel10);
            StudentsModel studentsModel11 = new StudentsModel(getString(R.string.name_2014574342_makuochukwu),
                    getString(R.string.quote_2014574342_makuochukwu),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574342_makuochukwu),
                    getString(R.string.day_4) + " " + getString(R.string.jan),
                    getString(R.string.email_2014574342_makuochukwu),
                    getString(R.string.lga_2014574342_makuochukwu),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574342_makuochukwu));
            listFull.add(studentsModel11);
            StudentsModel studentsModel12 = new StudentsModel(getString(R.string.name_2014574343_lilian),
                    getString(R.string.quote_2014574343_lilian),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574343_lilian),
                    getString(R.string.day_9) + " " + getString(R.string.may),
                    getString(R.string.email_2014574343_lilian),
                    getString(R.string.lga_2014574343_lilian),
                    getString(R.string.state_Imo),
                    getString(R.string.nick_2014574343_lilian));
            listFull.add(studentsModel12);
            StudentsModel studentsModel13 = new StudentsModel(getString(R.string.name_2014574344_collins),
                    getString(R.string.quote_2014574344_collins),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574344_collins),
                    getString(R.string.day_24) + " " + getString(R.string.oct),
                    getString(R.string.email_2014574344_collins),
                    getString(R.string.lga_2014574344_collins),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574344_collins));
            listFull.add(studentsModel13);
            StudentsModel studentsModel14 = new StudentsModel(getString(R.string.name_2014574345_obianuju),
                    getString(R.string.quote_2014574345_obianuju),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574345_obianuju),
                    getString(R.string.day_24) + " " + getString(R.string.jan),
                    getString(R.string.email_2014574345_obianuju),
                    getString(R.string.lga_2014574345_obianuju),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574345_obianuju));
            listFull.add(studentsModel14);
            StudentsModel studentsModel15 = new StudentsModel(getString(R.string.name_2014574346_goodness),
                    getString(R.string.quote_2014574346_goodness),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574346_goodness),
                    getString(R.string.day_22) + " " + getString(R.string.jun),
                    getString(R.string.email_2014574346_goodness),
                    getString(R.string.lga_2014574346_goodness),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574346_goodness));
            listFull.add(studentsModel15);
            StudentsModel studentsModel16 = new StudentsModel(getString(R.string.name_2014574347_ikechukwu),
                    getString(R.string.quote_2014574347_ikechukwu),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574347_ikechukwu),
                    getString(R.string.day_29) + " " + getString(R.string.nov),
                    getString(R.string.email_2014574347_ikechukwu),
                    getString(R.string.lga_2014574347_ikechukwu),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574347_ikechukwu));
            listFull.add(studentsModel16);
            StudentsModel studentsModel17 = new StudentsModel(getString(R.string.name_2014574348_precious),
                    getString(R.string.quote_2014574348_precious),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574348_precious),
                    getString(R.string.day_20) + " " + getString(R.string.may),
                    getString(R.string.email_2014574348_precious),
                    getString(R.string.lga_2014574348_precious),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574348_precious));
            listFull.add(studentsModel17);
            StudentsModel studentsModel18 = new StudentsModel(getString(R.string.name_2014574350_charity),
                    getString(R.string.quote_2014574350_charity),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574350_charity),
                    getString(R.string.day_27) + " " + getString(R.string.apr),
                    getString(R.string.email_2014574350_charity),
                    getString(R.string.lga_2014574350_charity),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574350_charity));
            listFull.add(studentsModel18);
            StudentsModel studentsModel19 = new StudentsModel(getString(R.string.name_2014574352_kingsley),
                    getString(R.string.quote_2014574352_kingsley),
                    R.drawable.user_male,
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
                    R.drawable.user_male,
                    getString(R.string.phone_2014574354_augustine),
                    getString(R.string.day_12) + " " + getString(R.string.aug),
                    getString(R.string.email_2014574354_augustine),
                    getString(R.string.lga_2014574354_augustine),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574354_augustine));
            listFull.add(studentsModel20);
            StudentsModel studentsModel21 = new StudentsModel(getString(R.string.name_2014574355_judith),
                    getString(R.string.quote_2014574355_judith),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574355_judith),
                    getString(R.string.day_17) + " " + getString(R.string.apr),
                    getString(R.string.email_2014574355_judith),
                    getString(R.string.lga_2014574355_judith),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574355_judith));
            listFull.add(studentsModel21);
            StudentsModel studentsModel22 = new StudentsModel(getString(R.string.name_2014574356_onyinyechi),
                    getString(R.string.quote_2014574356_onyinyechi),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574356_onyinyechi),
                    getString(R.string.day_13) + " " + getString(R.string.oct),
                    getString(R.string.email_2014574356_onyinyechi),
                    getString(R.string.lga_2014574356_onyinyechi),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574356_onyinyechi));
            listFull.add(studentsModel22);
            StudentsModel studentsModel243 = new StudentsModel(getString(R.string.name_2014574357_emeka),
                    getString(R.string.quote_2014574357_emeka),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574357_emeka),
                    getString(R.string.day_8) + " " + getString(R.string.aug),
                    getString(R.string.email_2014574357_emeka),
                    getString(R.string.lga_2014574357_emeka),
                    getString(R.string.state_Imo),
                    getString(R.string.nick_2014574357_emeka));
            listFull.add(studentsModel243);
            StudentsModel studentsModel24 = new StudentsModel(getString(R.string.name_2014574358_kelvin),
                    getString(R.string.quote_2014574358_kelvin),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574358_kelvin),
                    getString(R.string.day_26) + " " + getString(R.string.apr),
                    getString(R.string.email_2014574358_kelvin),
                    getString(R.string.lga_2014574358_kelvin),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574358_kelvin));
            listFull.add(studentsModel24);
            StudentsModel studentsModel25 = new StudentsModel(getString(R.string.name_2014574359_ndidi),
                    getString(R.string.quote_2014574359_ndidi),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574359_ndidi),
                    getString(R.string.day_14) + " " + getString(R.string.oct),
                    getString(R.string.email_2014574359_ndidi),
                    getString(R.string.lga_2014574359_ndidi),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574359_ndidi));
            listFull.add(studentsModel25);
            StudentsModel studentsModel26 = new StudentsModel(getString(R.string.name_2014574360_blessing),
                    getString(R.string.quote_2014574360_blessing),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574360_blessing),
                    getString(R.string.day_3) + " " + getString(R.string.may),
                    getString(R.string.email_2014574360_blessing),
                    getString(R.string.lga_2014574360_blessing),
                    getString(R.string.state_Abia),
                    getString(R.string.nick_2014574360_blessing));
            listFull.add(studentsModel26);
            StudentsModel studentsModel27 = new StudentsModel(getString(R.string.name_2014574361_chidiebere),
                    getString(R.string.quote_2014574361_chidiebere),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574361_chidiebere),
                    getString(R.string.day_30) + " " + getString(R.string.may),
                    getString(R.string.email_2014574361_chidiebere),
                    getString(R.string.lga_2014574361_chidiebere),
                    getString(R.string.state_Imo),
                    getString(R.string.nick_2014574361_chidiebere));
            listFull.add(studentsModel27);
            StudentsModel studentsModel28 = new StudentsModel(getString(R.string.name_2014574363_adaobi),
                    getString(R.string.quote_2014574363_adaobi),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574363_adaobi),
                    getString(R.string.day_17) + " " + getString(R.string.mar),
                    getString(R.string.email_2014574363_adaobi),
                    getString(R.string.lga_2014574363_adaobi),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574363_adaobi));
            listFull.add(studentsModel28);
            StudentsModel studentsModel29 = new StudentsModel(getString(R.string.name_2014574364_amaobi),
                    getString(R.string.quote_2014574364_amaobi),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574364_amaobi),
                    getString(R.string.day_16) + " " + getString(R.string.apr),
                    getString(R.string.email_2014574364_amaobi),
                    getString(R.string.lga_2014574364_amaobi),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574364_amaobi));
            listFull.add(studentsModel29);
            StudentsModel studentsModel30 = new StudentsModel(getString(R.string.name_2014574365_augustina),
                    getString(R.string.quote_2014574365_augustina),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574365_augustina),
                    getString(R.string.day_31) + " " + getString(R.string.aug),
                    getString(R.string.email_2014574365_augustina),
                    getString(R.string.lga_2014574365_augustina),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574365_augustina));
            listFull.add(studentsModel30);
            StudentsModel studentsModel31 = new StudentsModel(getString(R.string.name_2014574366_cynthia),
                    getString(R.string.quote_2014574366_cynthia),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574366_cynthia),
                    getString(R.string.day_4) + " " + getString(R.string.feb),
                    getString(R.string.email_2014574366_cynthia),
                    getString(R.string.lga_2014574366_cynthia),
                    getString(R.string.state_Delta),
                    getString(R.string.nick_2014574366_cynthia));
            listFull.add(studentsModel31);
            StudentsModel studentsModel32 = new StudentsModel(getString(R.string.name_2014574367_ifechukwu),
                    getString(R.string.quote_2014574367_ifechukwu),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574367_ifechukwu),
                    getString(R.string.day_1) + " " + getString(R.string.apr),
                    getString(R.string.email_2014574367_ifechukwu),
                    getString(R.string.lga_2014574367_ifechukwu),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574367_ifechukwu));
            listFull.add(studentsModel32);
            StudentsModel studentsModel33 = new StudentsModel(getString(R.string.name_2014574368_chiamaka),
                    getString(R.string.quote_2014574368_chiamaka),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574368_chiamaka),
                    getString(R.string.day_2) + " " + getString(R.string.oct),
                    getString(R.string.email_2014574368_chiamaka),
                    getString(R.string.lga_2014574368_chiamaka),
                    getString(R.string.state_Delta),
                    getString(R.string.nick_2014574368_chiamaka));
            listFull.add(studentsModel33);
            StudentsModel studentsModel34 = new StudentsModel(getString(R.string.name_2014574369_emezie),
                    getString(R.string.quote_2014574369_emezie),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574369_emezie),
                    getString(R.string.day_10) + " " + getString(R.string.nov),
                    getString(R.string.email_2014574369_emezie),
                    getString(R.string.lga_2014574369_emezie),
                    getString(R.string.state_Imo),
                    getString(R.string.nick_2014574369_emezie));
            listFull.add(studentsModel34);
            StudentsModel studentsModel35 = new StudentsModel(getString(R.string.name_2014574370_gigabyte),
                    getString(R.string.quote_2014574370_gigabyte),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574370_gigabyte),
                    getString(R.string.day_27) + " " + getString(R.string.may),
                    getString(R.string.email_2014574370_gigabyte),
                    getString(R.string.lga_2014574370_gigabyte),
                    getString(R.string.state_Abia),
                    getString(R.string.nick_2014574370_gigabyte));
            listFull.add(studentsModel35);
            StudentsModel studentsModel36 = new StudentsModel(getString(R.string.name_2014574372_pauline),
                    getString(R.string.quote_2014574372_pauline),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574372_pauline),
                    getString(R.string.day_31) + " " + getString(R.string.jul),
                    getString(R.string.email_2014574372_pauline),
                    getString(R.string.lga_2014574372_pauline),
                    getString(R.string.state_Imo),
                    getString(R.string.nick_2014574372_pauline));
            listFull.add(studentsModel36);
            StudentsModel studentsModel37 = new StudentsModel(getString(R.string.name_2014574373_ijeoma),
                    getString(R.string.quote_2014574373_ijeoma),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574373_ijeoma),
                    getString(R.string.day_13) + " " + getString(R.string.jan),
                    getString(R.string.email_2014574373_ijeoma),
                    getString(R.string.lga_2014574373_ijeoma),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574373_ijeoma));
            listFull.add(studentsModel37);
            StudentsModel studentsModel38 = new StudentsModel(getString(R.string.name_2014574374_onyekachi),
                    getString(R.string.quote_2014574374_onyekachi),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574374_onyekachi),
                    getString(R.string.day_25) + " " + getString(R.string.apr),
                    getString(R.string.email_2014574374_onyekachi),
                    getString(R.string.lga_2014574374_onyekachi),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574374_onyekachi));
            listFull.add(studentsModel38);
            StudentsModel studentsModel39 = new StudentsModel(getString(R.string.name_2014574375_vanessa),
                    getString(R.string.quote_2014574375_vanessa),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574375_vanessa),
                    getString(R.string.day_7) + " " + getString(R.string.jan),
                    getString(R.string.email_2014574375_vanessa),
                    getString(R.string.lga_2014574375_vanessa),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574375_vanessa));
            listFull.add(studentsModel39);
            StudentsModel studentsModel40 = new StudentsModel(getString(R.string.name_2014574376_susan),
                    getString(R.string.quote_2014574376_susan),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574376_susan),
                    getString(R.string.day_18) + " " + getString(R.string.mar),
                    getString(R.string.email_2014574376_susan),
                    getString(R.string.lga_2014574376_susan),
                    getString(R.string.state_Edo),
                    getString(R.string.nick_2014574376_susan));
            listFull.add(studentsModel40);
            StudentsModel studentsModel41 = new StudentsModel(getString(R.string.name_2014574377_rosemary),
                    getString(R.string.quote_2014574377_rosemary),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574377_rosemary),
                    getString(R.string.day_19) + " " + getString(R.string.feb),
                    getString(R.string.email_2014574377_rosemary),
                    getString(R.string.lga_2014574377_rosemary),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574377_rosemary));
            listFull.add(studentsModel41);
            StudentsModel studentsModel42 = new StudentsModel(getString(R.string.name_2014574378_chukwusom),
                    getString(R.string.quote_2014574378_chukwusom),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574378_chukwusom),
                    getString(R.string.date_unknown),
                    getString(R.string.email_2014574378_chukwusom),
                    getString(R.string.lga_2014574378_chukwusom),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574378_chukwusom));
            listFull.add(studentsModel42);
            StudentsModel studentsModel43 = new StudentsModel(getString(R.string.name_2014574379_friday),
                    getString(R.string.quote_2014574379_friday),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574379_friday),
                    getString(R.string.day_22) + " " + getString(R.string.oct),
                    getString(R.string.email_2014574379_friday),
                    getString(R.string.lga_2014574379_friday),
                    getString(R.string.state_Benue),
                    getString(R.string.nick_2014574379_friday));
            listFull.add(studentsModel43);
            StudentsModel studentsModel44 = new StudentsModel(getString(R.string.name_2014574380_blessing),
                    getString(R.string.quote_2014574380_blessing),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574380_blessing),
                    getString(R.string.day_27) + " " + getString(R.string.dec),
                    getString(R.string.email_2014574380_blessing),
                    getString(R.string.lga_2014574380_blessing),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574380_blessing));
            listFull.add(studentsModel44);
            StudentsModel studentsModel45 = new StudentsModel(getString(R.string.name_2014574383_chidiogo),
                    getString(R.string.quote_2014574383_chidiogo),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574383_chidiogo),
                    getString(R.string.day_23) + " " + getString(R.string.mar),
                    getString(R.string.email_2014574383_chidiogo),
                    getString(R.string.lga_2014574383_chidiogo),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574383_chidiogo));
            listFull.add(studentsModel45);
            StudentsModel studentsModel46 = new StudentsModel(getString(R.string.name_2014574384_joyce),
                    getString(R.string.quote_2014574384_joyce),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574384_joyce),
                    getString(R.string.day_18) + " " + getString(R.string.oct),
                    getString(R.string.email_2014574384_joyce),
                    getString(R.string.lga_2014574384_joyce),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574384_joyce));
            listFull.add(studentsModel46);
            StudentsModel studentsModel47 = new StudentsModel(getString(R.string.name_2014574385_peter),
                    getString(R.string.quote_2014574385_peter),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574385_peter),
                    getString(R.string.day_26) + " " + getString(R.string.may),
                    getString(R.string.email_2014574385_peter),
                    getString(R.string.lga_2014574385_peter),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574385_peter));
            listFull.add(studentsModel47);
            StudentsModel studentsModel48 = new StudentsModel(getString(R.string.name_2014574386_daniel),
                    getString(R.string.quote_2014574386_daniel),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574386_daniel),
                    getString(R.string.day_30) + " " + getString(R.string.sept),
                    getString(R.string.email_2014574386_daniel),
                    getString(R.string.lga_2014574386_daniel),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574386_daniel));
            listFull.add(studentsModel48);
            StudentsModel studentsModel49 = new StudentsModel(getString(R.string.name_2014574387_vincent),
                    getString(R.string.quote_2014574387_vincent),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574387_vincent),
                    getString(R.string.day_16) + " " + getString(R.string.nov),
                    getString(R.string.email_2014574387_vincent),
                    getString(R.string.lga_2014574387_vincent),
                    getString(R.string.state_Ebonyi),
                    getString(R.string.nick_2014574387_vincent));
            listFull.add(studentsModel49);
            StudentsModel studentsModel50 = new StudentsModel(getString(R.string.name_2014574388_chinasa),
                    getString(R.string.quote_2014574388_chinasa),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574388_chinasa),
                    getString(R.string.day_18) + " " + getString(R.string.jul),
                    getString(R.string.email_2014574388_chinasa),
                    getString(R.string.lga_2014574388_chinasa),
                    getString(R.string.state_Ebonyi),
                    getString(R.string.nick_2014574388_chinasa));
            listFull.add(studentsModel50);
            StudentsModel studentsModel51 = new StudentsModel(getString(R.string.name_2014574390_hope),
                    getString(R.string.quote_2014574390_hope),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574390_hope),
                    getString(R.string.day_14) + " " + getString(R.string.apr),
                    getString(R.string.email_2014574390_hope),
                    getString(R.string.lga_2014574390_hope),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574390_hope));
            listFull.add(studentsModel51);
            StudentsModel studentsModel52 = new StudentsModel(getString(R.string.name_2014574392_florence),
                    getString(R.string.quote_2014574392_florence),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574392_florence),
                    getString(R.string.day_25) + " " + getString(R.string.jan),
                    getString(R.string.email_2014574392_florence),
                    getString(R.string.lga_2014574392_florence),
                    getString(R.string.state_CrossRiver),
                    getString(R.string.nick_2014574392_florence));
            listFull.add(studentsModel52);
            StudentsModel studentsModel53 = new StudentsModel(getString(R.string.name_2014574393_patience),
                    getString(R.string.quote_2014574393_patience),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574393_patience),
                    getString(R.string.day_25) + " " + getString(R.string.sept),
                    getString(R.string.email_2014574393_patience),
                    getString(R.string.lga_2014574393_patience),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574393_patience));
            listFull.add(studentsModel53);
            StudentsModel studentsModel54 = new StudentsModel(getString(R.string.name_2014574395_blessing),
                    getString(R.string.quote_2014574395_blessing),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574395_blessing),
                    getString(R.string.day_21) + " " + getString(R.string.mar),
                    getString(R.string.email_2014574395_blessing),
                    getString(R.string.lga_2014574395_blessing),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574395_blessing));
            listFull.add(studentsModel54);
            StudentsModel studentsModel55 = new StudentsModel(getString(R.string.name_2014574396_shalom),
                    getString(R.string.quote_2014574396_shalom),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574396_shalom),
                    getString(R.string.day_5) + " " + getString(R.string.oct),
                    getString(R.string.email_2014574396_shalom),
                    getString(R.string.lga_2014574396_shalom),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574396_shalom));
            listFull.add(studentsModel55);
            StudentsModel studentsModel56 = new StudentsModel(getString(R.string.name_2014574397_obumneme),
                    getString(R.string.quote_2014574397_obumneme),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574397_obumneme),
                    getString(R.string.day_9) + " " + getString(R.string.oct),
                    getString(R.string.email_2014574397_obumneme),
                    getString(R.string.lga_2014574397_obumneme),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574397_obumneme));
            listFull.add(studentsModel56);
            StudentsModel studentsModel57 = new StudentsModel(getString(R.string.name_2014574398_judith),
                    getString(R.string.quote_2014574398_judith),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574398_judith),
                    getString(R.string.day_19) + " " + getString(R.string.jul),
                    getString(R.string.email_2014574398_judith),
                    getString(R.string.lga_2014574398_judith),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574398_judith));
            listFull.add(studentsModel57);
            StudentsModel studentsModel58 = new StudentsModel(getString(R.string.name_2014574399_tobechukwu),
                    getString(R.string.quote_2014574399_tobechukwu),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574399_tobechukwu),
                    getString(R.string.day_4) + " " + getString(R.string.may),
                    getString(R.string.email_2014574399_tobechukwu),
                    getString(R.string.lga_2014574399_tobechukwu),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574399_tobechukwu));
            listFull.add(studentsModel58);
            StudentsModel studentsModel59 = new StudentsModel(getString(R.string.name_2014574400_jennifer),
                    getString(R.string.quote_2014574400_jennifer),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574400_jennifer),
                    getString(R.string.day_28) + " " + getString(R.string.apr),
                    getString(R.string.email_2014574400_jennifer),
                    getString(R.string.lga_2014574400_jennifer),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574400_jennifer));
            listFull.add(studentsModel59);
            StudentsModel studentsModel60 = new StudentsModel(getString(R.string.name_2014574401_blessing),
                    getString(R.string.quote_2014574401_blessing),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574401_blessing),
                    getString(R.string.day_18) + " " + getString(R.string.jul),
                    getString(R.string.email_2014574401_blessing),
                    getString(R.string.lga_2014574401_blessing),
                    getString(R.string.state_Imo),
                    getString(R.string.nick_2014574401_blessing));
            listFull.add(studentsModel60);
            StudentsModel studentsModel61 = new StudentsModel(getString(R.string.name_2014574402_ogechukwu),
                    getString(R.string.quote_2014574402_ogechukwu),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574402_ogechukwu),
                    getString(R.string.day_3) + " " + getString(R.string.apr),
                    getString(R.string.email_2014574402_ogechukwu),
                    getString(R.string.lga_2014574402_ogechukwu),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574402_ogechukwu));
            listFull.add(studentsModel61);
            StudentsModel studentsModel62 = new StudentsModel(getString(R.string.name_2014574403_faith),
                    getString(R.string.quote_2014574403_faith),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574403_faith),
                    getString(R.string.day_9) + " " + getString(R.string.oct),
                    getString(R.string.email_2014574403_faith),
                    getString(R.string.lga_2014574403_faith),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574403_faith));
            listFull.add(studentsModel62);
            StudentsModel studentsModel63 = new StudentsModel(getString(R.string.name_2014574404_kenneth),
                    getString(R.string.quote_2014574404_kenneth),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574404_kenneth),
                    getString(R.string.day_24) + " " + getString(R.string.aug),
                    getString(R.string.email_2014574404_kenneth),
                    getString(R.string.lga_2014574404_kenneth),
                    getString(R.string.state_Imo),
                    getString(R.string.nick_2014574404_kenneth));
            listFull.add(studentsModel63);
            StudentsModel studentsModel64 = new StudentsModel(getString(R.string.name_2014574405_samuel),
                    getString(R.string.quote_2014574405_samuel),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574405_samuel),
                    getString(R.string.day_13) + " " + getString(R.string.mar),
                    getString(R.string.email_2014574405_samuel),
                    getString(R.string.lga_2014574405_samuel),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574405_samuel));
            listFull.add(studentsModel64);
            StudentsModel studentsModel65 = new StudentsModel(getString(R.string.name_2014574406_henry),
                    getString(R.string.quote_2014574406_henry),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574406_henry),
                    getString(R.string.day_15) + " " + getString(R.string.oct),
                    getString(R.string.email_2014574406_henry),
                    getString(R.string.lga_2014574406_henry),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574406_henry));
            listFull.add(studentsModel65);
            StudentsModel studentsModel66 = new StudentsModel(getString(R.string.name_2014574407_maryjane),
                    getString(R.string.quote_2014574407_maryjane),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574407_maryjane),
                    getString(R.string.day_15) + " " + getString(R.string.oct),
                    getString(R.string.email_2014574407_maryjane),
                    getString(R.string.lga_2014574407_maryjane),
                    getString(R.string.state_unknown),
                    getString(R.string.nick_2014574407_maryjane));
            listFull.add(studentsModel66);
            StudentsModel studentsModel67 = new StudentsModel(getString(R.string.name_2014574408_ifeanyi),
                    getString(R.string.quote_2014574408_ifeanyi),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574408_ifeanyi),
                    getString(R.string.day_30) + " " + getString(R.string.jan),
                    getString(R.string.email_2014574408_ifeanyi),
                    getString(R.string.lga_2014574408_ifeanyi),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574408_ifeanyi));
            listFull.add(studentsModel67);
            StudentsModel studentsModel68 = new StudentsModel(getString(R.string.name_2014574409_rita),
                    getString(R.string.quote_2014574409_rita),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574409_rita),
                    getString(R.string.day_27) + " " + getString(R.string.may),
                    getString(R.string.email_2014574409_rita),
                    getString(R.string.lga_2014574409_rita),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574409_rita));
            listFull.add(studentsModel68);
            StudentsModel studentsModel69 = new StudentsModel(getString(R.string.name_2014574410_precious),
                    getString(R.string.quote_2014574410_precious),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574410_precious),
                    getString(R.string.day_26) + " " + getString(R.string.may),
                    getString(R.string.email_2014574410_precious),
                    getString(R.string.lga_2014574410_precious),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574410_precious));
            listFull.add(studentsModel69);
            StudentsModel studentsModel70 = new StudentsModel(getString(R.string.name_2014574412_smiling),
                    getString(R.string.quote_2014574412_smiling),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574412_smiling),
                    getString(R.string.day_2) + " " + getString(R.string.jul),
                    getString(R.string.email_2014574412_smiling),
                    getString(R.string.lga_2014574412_smiling),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574412_smiling));
            listFull.add(studentsModel70);
            StudentsModel studentsModel71 = new StudentsModel(getString(R.string.name_2014574413_peace),
                    getString(R.string.quote_2014574413_peace),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574413_peace),
                    getString(R.string.day_17) + " " + getString(R.string.dec),
                    getString(R.string.email_2014574413_peace),
                    getString(R.string.lga_2014574413_peace),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574413_peace));
            listFull.add(studentsModel71);
            StudentsModel studentsModel72 = new StudentsModel(getString(R.string.name_2014574414_emskaro),
                    getString(R.string.quote_2014574414_emskaro),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574414_emskaro),
                    getString(R.string.day_17) + " " + getString(R.string.jun),
                    getString(R.string.email_2014574414_emskaro),
                    getString(R.string.lga_2014574414_emskaro),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574414_emskaro));
            listFull.add(studentsModel72);
            StudentsModel studentsModel73 = new StudentsModel(getString(R.string.name_2014574415_franca),
                    getString(R.string.quote_2014574415_franca),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574415_franca),
                    getString(R.string.day_20) + " " + getString(R.string.dec),
                    getString(R.string.email_2014574415_franca),
                    getString(R.string.lga_2014574415_franca),
                    getString(R.string.state_Abia),
                    getString(R.string.nick_2014574415_franca));
            listFull.add(studentsModel73);
            StudentsModel studentsModel74 = new StudentsModel(getString(R.string.name_2014574416_zuma),
                    getString(R.string.quote_2014574416_zuma),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574416_zuma),
                    getString(R.string.day_10) + " " + getString(R.string.jun),
                    getString(R.string.email_2014574416_zuma),
                    getString(R.string.lga_2014574416_zuma),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574416_zuma));
            listFull.add(studentsModel74);
            StudentsModel studentsModel75 = new StudentsModel(getString(R.string.name_2014574417_sylvia),
                    getString(R.string.quote_2014574417_sylvia),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574417_sylvia),
                    getString(R.string.day_3) + " " + getString(R.string.jul),
                    getString(R.string.email_2014574417_sylvia),
                    getString(R.string.lga_2014574417_sylvia),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574417_sylvia));
            listFull.add(studentsModel75);
            StudentsModel studentsModel76 = new StudentsModel(getString(R.string.name_2014574419_lawrence),
                    getString(R.string.quote_2014574419_lawrence),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574419_lawrence),
                    getString(R.string.day_26) + " " + getString(R.string.jul),
                    getString(R.string.email_2014574419_lawrence),
                    getString(R.string.lga_2014574419_lawrence),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574419_lawrence));
            listFull.add(studentsModel76);
            StudentsModel studentsModel77 = new StudentsModel(getString(R.string.name_2014574420_jennifer),
                    getString(R.string.quote_2014574420_jennifer),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574420_jennifer),
                    getString(R.string.day_17) + " " + getString(R.string.apr),
                    getString(R.string.email_2014574420_jennifer),
                    getString(R.string.lga_2014574420_jennifer),
                    getString(R.string.state_Imo),
                    getString(R.string.nick_2014574420_jennifer));
            listFull.add(studentsModel77);
            StudentsModel studentsModel78 = new StudentsModel(getString(R.string.name_2014574421_fortunate),
                    getString(R.string.quote_2014574421_fortunate),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574421_fortunate),
                    getString(R.string.day_9) + " " + getString(R.string.oct),
                    getString(R.string.email_2014574421_fortunate),
                    getString(R.string.lga_2014574421_fortunate),
                    getString(R.string.state_Abia),
                    getString(R.string.nick_2014574421_fortunate));
            listFull.add(studentsModel78);
            StudentsModel studentsModel79 = new StudentsModel(getString(R.string.name_2014574422_anthony),
                    getString(R.string.quote_2014574422_anthony),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574422_anthony),
                    getString(R.string.day_13) + " " + getString(R.string.jun),
                    getString(R.string.email_2014574422_anthony),
                    getString(R.string.lga_2014574422_anthony),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574422_anthony));
            listFull.add(studentsModel79);
            StudentsModel studentsModel80 = new StudentsModel(getString(R.string.name_2014574423_perpetual),
                    getString(R.string.quote_2014574423_perpetual),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574423_perpetual),
                    getString(R.string.day_3) + " " + getString(R.string.may),
                    getString(R.string.email_2014574423_perpetual),
                    getString(R.string.lga_2014574423_perpetual),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574423_perpetual));
            listFull.add(studentsModel80);
            StudentsModel studentsModel81 = new StudentsModel(getString(R.string.name_2014574424_zikora),
                    getString(R.string.quote_2014574424_zikora),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574424_zikora),
                    getString(R.string.day_17) + " " + getString(R.string.may),
                    getString(R.string.email_2014574424_zikora),
                    getString(R.string.lga_2014574424_zikora),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574424_zikora));
            listFull.add(studentsModel81);
            StudentsModel studentsModel82 = new StudentsModel(getString(R.string.name_2014574425_mishael),
                    getString(R.string.quote_2014574425_mishael),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574425_mishael),
                    getString(R.string.date_unknown),
                    getString(R.string.email_2014574425_mishael),
                    getString(R.string.lga_2014574425_mishael),
                    getString(R.string.state_unknown),
                    getString(R.string.nick_2014574425_mishael));
            listFull.add(studentsModel82);
            StudentsModel studentsModel83 = new StudentsModel(getString(R.string.name_2014574426_vincent),
                    getString(R.string.quote_2014574426_vincent),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574426_vincent),
                    getString(R.string.day_12) + " " + getString(R.string.oct),
                    getString(R.string.email_2014574426_vincent),
                    getString(R.string.lga_2014574426_vincent),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574426_vincent));
            listFull.add(studentsModel83);
            StudentsModel studentsModel84 = new StudentsModel(getString(R.string.name_2014574427_chiemerie),
                    getString(R.string.quote_2014574427_chiemerie),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574427_chiemerie),
                    getString(R.string.day_11) + " " + getString(R.string.may),
                    getString(R.string.email_2014574427_chiemerie),
                    getString(R.string.lga_2014574427_chiemerie),
                    getString(R.string.state_Imo),
                    getString(R.string.nick_2014574427_chiemerie));
            listFull.add(studentsModel84);
            StudentsModel studentsModel85 = new StudentsModel(getString(R.string.name_2014574428_ifeanyi),
                    getString(R.string.quote_2014574428_ifeanyi),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574428_ifeanyi),
                    getString(R.string.day_13) + " " + getString(R.string.sept),
                    getString(R.string.email_2014574428_ifeanyi),
                    getString(R.string.lga_2014574428_ifeanyi),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574428_ifeanyi));
            listFull.add(studentsModel85);
            StudentsModel studentsModel86 = new StudentsModel(getString(R.string.name_2014574429_celestina),
                    getString(R.string.quote_2014574429_celestina),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574429_celestina),
                    getString(R.string.day_18) + " " + getString(R.string.jan),
                    getString(R.string.email_2014574429_celestina),
                    getString(R.string.lga_2014574429_celestina),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574429_celestina));
            listFull.add(studentsModel86);
            StudentsModel studentsModel87 = new StudentsModel(getString(R.string.name_2014574432_chiazagom),
                    getString(R.string.quote_2014574432_chiazagom),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574432_chiazagom),
                    getString(R.string.day_14) + " " + getString(R.string.feb),
                    getString(R.string.email_2014574432_chiazagom),
                    getString(R.string.lga_2014574432_chiazagom),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574432_chiazagom));
            listFull.add(studentsModel87);
            StudentsModel studentsModel88 = new StudentsModel(getString(R.string.name_2014574434_kennedy),
                    getString(R.string.quote_2014574434_kennedy),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574434_kennedy),
                    getString(R.string.day_22) + " " + getString(R.string.may),
                    getString(R.string.email_2014574434_kennedy),
                    getString(R.string.lga_2014574434_kennedy),
                    getString(R.string.state_Edo),
                    getString(R.string.nick_2014574434_kennedy));
            listFull.add(studentsModel88);
            StudentsModel studentsModel89 = new StudentsModel(getString(R.string.name_2014574435_victor),
                    getString(R.string.quote_2014574435_victor),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574435_victor),
                    getString(R.string.day_15) + " " + getString(R.string.sept),
                    getString(R.string.email_2014574435_victor),
                    getString(R.string.lga_2014574435_victor),
                    getString(R.string.state_Abia),
                    getString(R.string.nick_2014574435_victor));
            listFull.add(studentsModel89);
            StudentsModel studentsModel90 = new StudentsModel(getString(R.string.name_2014574437_nduka),
                    getString(R.string.quote_2014574437_nduka),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574437_nduka),
                    getString(R.string.day_17) + " " + getString(R.string.mar),
                    getString(R.string.email_2014574437_nduka),
                    getString(R.string.lga_2014574437_nduka),
                    getString(R.string.state_Delta),
                    getString(R.string.nick_2014574437_nduka));
            listFull.add(studentsModel90);
            StudentsModel studentsModel91 = new StudentsModel(getString(R.string.name_2014574438_gabriel),
                    getString(R.string.quote_2014574438_gabriel),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574438_gabriel),
                    getString(R.string.day_14) + " " + getString(R.string.aug),
                    getString(R.string.email_2014574438_gabriel),
                    getString(R.string.lga_2014574438_gabriel),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574438_gabriel));
            listFull.add(studentsModel91);
            StudentsModel studentsModel92 = new StudentsModel(getString(R.string.name_2014574440_emmanuel),
                    getString(R.string.quote_2014574440_emmanuel),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574440_emmanuel),
                    getString(R.string.day_7) + " " + getString(R.string.may),
                    getString(R.string.email_2014574440_emmanuel),
                    getString(R.string.lga_2014574440_emmanuel),
                    getString(R.string.state_Delta),
                    getString(R.string.nick_2014574440_emmanuel));
            listFull.add(studentsModel92);
            StudentsModel studentsModel93 = new StudentsModel(getString(R.string.name_2014464245_chisom),
                    getString(R.string.quote_2014464245_chisom),
                    R.drawable.user_male,
                    getString(R.string.phone_2014464245_chisom),
                    getString(R.string.day_25) + " " + getString(R.string.feb),
                    getString(R.string.email_2014464245_chisom),
                    getString(R.string.lga_2014464245_chisom),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014464245_chisom));
            listFull.add(studentsModel93);
            StudentsModel studentsModel94 = new StudentsModel(getString(R.string.name_2013474386_ebuka),
                    getString(R.string.quote_2013474386_ebuka),
                    R.drawable.user_male,
                    getString(R.string.phone_2013474386_ebuka),
                    getString(R.string.day_30) + " " + getString(R.string.sept),
                    getString(R.string.email_2013474386_ebuka),
                    getString(R.string.lga_2013474386_ebuka),
                    getString(R.string.state_Imo),
                    getString(R.string.nick_2013474386_ebuka));
            listFull.add(studentsModel94);
            StudentsModel studentsModel95 = new StudentsModel(getString(R.string.name_2013474278_mary),
                    getString(R.string.quote_2013474278_mary),
                    R.drawable.user_female,
                    getString(R.string.phone_2013474278_mary),
                    getString(R.string.day_25) + " " + getString(R.string.mar),
                    getString(R.string.email_2013474278_mary),
                    getString(R.string.lga_2013474278_mary),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2013474278_mary));
            listFull.add(studentsModel95);
            StudentsModel studentsModel96 = new StudentsModel(getString(R.string.name_2013464162_angus),
                    getString(R.string.quote_2013464162_angus),
                    R.drawable.user_male,
                    getString(R.string.phone_2013464162_angus),
                    getString(R.string.day_13) + " " + getString(R.string.oct),
                    getString(R.string.email_2013464162_angus),
                    getString(R.string.lga_2013464162_angus),
                    getString(R.string.state_Imo),
                    getString(R.string.nick_2013464162_angus));
            listFull.add(studentsModel96);
            StudentsModel studentsModel97 = new StudentsModel(getString(R.string.name_2013614232_victor),
                    getString(R.string.quote_2013614232_victor),
                    R.drawable.user_male,
                    getString(R.string.phone_2013614232_victor),
                    getString(R.string.day_4) + " " + getString(R.string.apr),
                    getString(R.string.email_2013614232_victor),
                    getString(R.string.lga_2013614232_victor),
                    getString(R.string.state_Abia),
                    getString(R.string.nick_2013614232_victor));
            listFull.add(studentsModel97);
            StudentsModel studentsModel98 = new StudentsModel(getString(R.string.name_2013634226_maryjane),
                    getString(R.string.quote_2013634226_maryjane),
                    R.drawable.user_female,
                    getString(R.string.phone_2013634226_maryjane),
                    getString(R.string.day_20) + " " + getString(R.string.jun),
                    getString(R.string.email_2013634226_maryjane),
                    getString(R.string.lga_2013634226_maryjane),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2013634226_maryjane));
            listFull.add(studentsModel98);
            StudentsModel studentsModel99 = new StudentsModel(getString(R.string.name_2012464169_linda),
                    getString(R.string.quote_2012464169_linda),
                    R.drawable.user_female,
                    getString(R.string.phone_2012464169_linda),
                    getString(R.string.day_18) + " " + getString(R.string.aug),
                    getString(R.string.email_2012464169_linda),
                    getString(R.string.lga_2012464169_linda),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2012464169_linda));
            listFull.add(studentsModel99);
            StudentsModel studentsModel100 = new StudentsModel(getString(R.string.name_2011474018_christian),
                    getString(R.string.quote_2011474018_christian),
                    R.drawable.user_male,
                    getString(R.string.phone_2011474018_christian),
                    getString(R.string.day_24) + " " + getString(R.string.dec),
                    getString(R.string.email_2011474018_christian),
                    getString(R.string.lga_2011474018_christian),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2011474018_christian));
            listFull.add(studentsModel100);
        } else {
            //TODO STARTING OF WHERE THERE IS NETWORK
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
                    getString(R.string.date_unknown),
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

    private String openFullInfo(List<StudentsModel> stuList, int position){
        List<StudentsModel> model = new ArrayList<>();
        model.add(stuList.get(position));
        Gson gson = new Gson();

        return gson.toJson(model);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Objects.requireNonNull(getActivity()).getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView sv = (SearchView) searchItem.getActionView();
        sv.setQueryHint(getString(R.string.search_names));

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                return false;
        }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (sv.getQuery().toString().isEmpty()){
                    gridView.smoothScrollToPosition(0);
                }else {
                    for (StudentsModel student : list){
                        if (student.getStudentName().toLowerCase().contains(newText.toLowerCase())){
                            gridView.smoothScrollToPosition(list.indexOf(student));
                        }
                    }
                }
                return false;
            }
        });
    }
}
