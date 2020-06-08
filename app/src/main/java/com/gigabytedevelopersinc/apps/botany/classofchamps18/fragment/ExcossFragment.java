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
import com.gigabytedevelopersinc.apps.botany.classofchamps18.adapters.ExcossAdapter;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.models.ExcossModel;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.utils.TinyDB;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Created by Emmanuel Nwokoma (Founder and CEO at Gigabyte Developers) on 8/9/2018
 **/
public class ExcossFragment extends Fragment {
    public static List<ExcossModel> list, listFull;
    private RecyclerView recyclerView;
    public  ExcossAdapter adapter;
    private String studentName,studentQuotes,studentNickName,studentsPhone,studentsDob,
            studentsEmail,studentsLocalGov,studentsState,studentImage;
    private boolean haveConnectedWifi = false;
    private boolean haveConnectedMobile = false;
    private String IMAGE_URL = "http://class-of-champions-2018.000webhostapp.com/students/";
    private String FACEBOOK_URL ="https://facebook.com/";
    private String INSTAGRAM_URL ="https://instagram.com/";
    private TinyDB tinyDB;

    public ExcossFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_excoss, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.excoss_list);
        list = new ArrayList<>();
        listFull = new ArrayList<>();
        tinyDB = new TinyDB(getActivity());
        adapter = new ExcossAdapter(getContext(), list, (view1, position) -> {

            try {
                JSONArray studentJson = new JSONArray(openFullInfo(listFull,position));

                if (haveNetworkConnection()){
                    for (int j = 0; j < studentJson.length(); j++) {
                        JSONObject obj2 = studentJson.getJSONObject(j);
                        studentImage = obj2.getString("imageUrl");
                        studentName = obj2.getString("excossName");
                        studentQuotes = obj2.getString("excossQuotes");
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
                        JSONObject obj2 = studentJson.getJSONObject(j);
                        studentName = obj2.getString("excossName");
                        studentQuotes = obj2.getString("excossQuotes");
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

        excossList();
        excossListFull();
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void excossList(){
        if (!haveNetworkConnection()) {
            ExcossModel studentsModel0 = new ExcossModel(getString(R.string.name_2014574399_tobechukwu),
                    getString(R.string.office_dept_president),
                    R.drawable.user_male,
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574399_tobechukwu));
            list.add(studentsModel0);
            ExcossModel studentsModel1 = new ExcossModel(getString(R.string.name_2014574331_prisca),
                    getString(R.string.office_dept_vice_president),
                    R.drawable.user_female,
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574331_prisca));
            list.add(studentsModel1);
            ExcossModel studentsModel2 = new ExcossModel(getString(R.string.name_2014574333_francess),
                    getString(R.string.office_dept_sec_general),
                    R.drawable.user_female,
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574333_francess));
            list.add(studentsModel2);
            ExcossModel studentsModel3 = new ExcossModel(getString(R.string.name_2014574360_blessing),
                    getString(R.string.office_dept_treasurer),
                    R.drawable.user_female,
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574360_blessing));
            list.add(studentsModel3);
            ExcossModel studentsModel4 = new ExcossModel(getString(R.string.name_2014574370_gigabyte),
                    getString(R.string.office_dept_dir_ict),
                    R.drawable.user_male,
                    FACEBOOK_URL + "austin.nwokoma.9",
                    INSTAGRAM_URL + "emmanwokoma",
                    getString(R.string.phone_2014574370_gigabyte));
            list.add(studentsModel4);
            ExcossModel studentsModel5 = new ExcossModel(getString(R.string.name_2014574412_smiling),
                    getString(R.string.office_dept_dos),
                    R.drawable.user_male,
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574412_smiling));
            list.add(studentsModel5);
        } else {
            ExcossModel studentsModel0 = new ExcossModel(getString(R.string.name_2014574399_tobechukwu),
                    getString(R.string.office_dept_president),
                    IMAGE_URL + getString(R.string.no_2014574399) + ".jpg",
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574399_tobechukwu));
            list.add(studentsModel0);
            ExcossModel studentsModel1 = new ExcossModel(getString(R.string.name_2014574331_prisca),
                    getString(R.string.office_dept_vice_president),
                    IMAGE_URL + getString(R.string.no_2014574331) + ".jpg",
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574331_prisca));
            list.add(studentsModel1);
            ExcossModel studentsModel2 = new ExcossModel(getString(R.string.name_2014574333_francess),
                    getString(R.string.office_dept_sec_general),
                    IMAGE_URL + getString(R.string.no_2014574333) + ".jpg",
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574333_francess));
            list.add(studentsModel2);
            ExcossModel studentsModel3 = new ExcossModel(getString(R.string.name_2014574360_blessing),
                    getString(R.string.office_dept_treasurer),
                    IMAGE_URL + getString(R.string.no_2014574360) + ".jpg",
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574360_blessing));
            list.add(studentsModel3);
            ExcossModel studentsModel4 = new ExcossModel(getString(R.string.name_2014574370_gigabyte),
                    getString(R.string.office_dept_dir_ict),
                    IMAGE_URL + getString(R.string.no_2014574370) + ".jpg",
                    FACEBOOK_URL + "austin.nwokoma.9",
                    INSTAGRAM_URL + "emmanwokoma",
                    getString(R.string.phone_2014574370_gigabyte));
            list.add(studentsModel4);
            ExcossModel studentsModel5 = new ExcossModel(getString(R.string.name_2014574412_smiling),
                    getString(R.string.office_dept_dos),
                    IMAGE_URL + getString(R.string.no_2014574412) + ".jpg",
                    FACEBOOK_URL,
                    INSTAGRAM_URL,
                    getString(R.string.phone_2014574412_smiling));
            list.add(studentsModel5);
        }
    }

    private void excossListFull(){
        if (!haveNetworkConnection()){
            ExcossModel excossModel = new ExcossModel(getString(R.string.name_2014574399_tobechukwu),
                    getString(R.string.quote_2014574399_tobechukwu),
                    IMAGE_URL + getString(R.string.no_2014574412) + ".jpg",
                    getString(R.string.phone_2014574399_tobechukwu),
                    getString(R.string.day_4) + " " + getString(R.string.may),
                    getString(R.string.email_2014574399_tobechukwu),
                    getString(R.string.lga_2014574399_tobechukwu),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574399_tobechukwu));
            listFull.add(excossModel);

            ExcossModel excossModel1 = new ExcossModel(getString(R.string.name_2014574331_prisca),
                    getString(R.string.quote_2014574331_prisca),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574331_prisca),
                    getString(R.string.day_22) + " " + getString(R.string.jul),
                    getString(R.string.email_2014574331_prisca),
                    getString(R.string.lga_2014574331_prisca),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574331_prisca));

            listFull.add(excossModel1);

            ExcossModel excossModel2 = new ExcossModel(getString(R.string.name_2014574333_francess),
                    getString(R.string.quote_2014574333_francess),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574333_francess),
                    getString(R.string.day_8) + " " + getString(R.string.apr),
                    getString(R.string.email_2014574333_francess),
                    getString(R.string.lga_2014574333_francess),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574333_francess));

            listFull.add(excossModel2);

            ExcossModel excossModel3 = new ExcossModel(getString(R.string.name_2014574360_blessing),
                    getString(R.string.quote_2014574360_blessing),
                    R.drawable.user_female,
                    getString(R.string.phone_2014574360_blessing),
                    getString(R.string.day_3) + " " + getString(R.string.may),
                    getString(R.string.email_2014574360_blessing),
                    getString(R.string.lga_2014574360_blessing),
                    getString(R.string.state_Abia),
                    getString(R.string.nick_2014574360_blessing));
            listFull.add(excossModel3);

            ExcossModel excossModel4 = new ExcossModel(getString(R.string.name_2014574370_gigabyte),
                    getString(R.string.quote_2014574370_gigabyte),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574370_gigabyte),
                    getString(R.string.day_27) + " " + getString(R.string.may),
                    getString(R.string.email_2014574370_gigabyte),
                    getString(R.string.lga_2014574370_gigabyte),
                    getString(R.string.state_Abia),
                    getString(R.string.nick_2014574370_gigabyte));
            listFull.add(excossModel4);

            ExcossModel excossModel5 = new ExcossModel(getString(R.string.name_2014574412_smiling),
                    getString(R.string.quote_2014574412_smiling),
                    R.drawable.user_male,
                    getString(R.string.phone_2014574412_smiling),
                    getString(R.string.day_2) + " " + getString(R.string.jul),
                    getString(R.string.email_2014574412_smiling),
                    getString(R.string.lga_2014574412_smiling),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574412_smiling));

            listFull.add(excossModel5);
        }else {

            ExcossModel excossModel = new ExcossModel(getString(R.string.name_2014574399_tobechukwu),
                    getString(R.string.quote_2014574399_tobechukwu),
                    IMAGE_URL + getString(R.string.no_2014574399) + ".jpg",
                    getString(R.string.phone_2014574399_tobechukwu),
                    getString(R.string.day_4) + " " + getString(R.string.may),
                    getString(R.string.email_2014574399_tobechukwu),
                    getString(R.string.lga_2014574399_tobechukwu),
                    getString(R.string.state_Enugu),
                    getString(R.string.nick_2014574399_tobechukwu));
            listFull.add(excossModel);

            ExcossModel excossModel1 = new ExcossModel(getString(R.string.name_2014574331_prisca),
                    getString(R.string.quote_2014574331_prisca),
                    IMAGE_URL + getString(R.string.no_2014574331) + ".jpg",
                    getString(R.string.phone_2014574331_prisca),
                    getString(R.string.day_22) + " " + getString(R.string.jul),
                    getString(R.string.email_2014574331_prisca),
                    getString(R.string.lga_2014574331_prisca),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574331_prisca));

            listFull.add(excossModel1);

            ExcossModel excossModel2 = new ExcossModel(getString(R.string.name_2014574333_francess),
                    getString(R.string.quote_2014574333_francess),
                    IMAGE_URL + getString(R.string.no_2014574333) + ".jpg",
                    getString(R.string.phone_2014574333_francess),
                    getString(R.string.day_8) + " " + getString(R.string.apr),
                    getString(R.string.email_2014574333_francess),
                    getString(R.string.lga_2014574333_francess),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574333_francess));

            listFull.add(excossModel2);

            ExcossModel excossModel3 = new ExcossModel(getString(R.string.name_2014574360_blessing),
                    getString(R.string.quote_2014574360_blessing),
                    IMAGE_URL + getString(R.string.no_2014574360) + ".jpg",
                    getString(R.string.phone_2014574360_blessing),
                    getString(R.string.day_3) + " " + getString(R.string.may),
                    getString(R.string.email_2014574360_blessing),
                    getString(R.string.lga_2014574360_blessing),
                    getString(R.string.state_Abia),
                    getString(R.string.nick_2014574360_blessing));
            listFull.add(excossModel3);

            ExcossModel excossModel4 = new ExcossModel(getString(R.string.name_2014574370_gigabyte),
                    getString(R.string.quote_2014574370_gigabyte),
                    IMAGE_URL + getString(R.string.no_2014574370) + ".jpg",
                    getString(R.string.phone_2014574370_gigabyte),
                    getString(R.string.day_27) + " " + getString(R.string.may),
                    getString(R.string.email_2014574370_gigabyte),
                    getString(R.string.lga_2014574370_gigabyte),
                    getString(R.string.state_Abia),
                    getString(R.string.nick_2014574370_gigabyte));
            listFull.add(excossModel4);

            ExcossModel excossModel5 = new ExcossModel(getString(R.string.name_2014574412_smiling),
                    getString(R.string.quote_2014574412_smiling),
                    IMAGE_URL + getString(R.string.no_2014574412) + ".jpg",
                    getString(R.string.phone_2014574412_smiling),
                    getString(R.string.day_2) + " " + getString(R.string.jul),
                    getString(R.string.email_2014574412_smiling),
                    getString(R.string.lga_2014574412_smiling),
                    getString(R.string.state_Anambra),
                    getString(R.string.nick_2014574412_smiling));

            listFull.add(excossModel5);
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

    private String openFullInfo(List<ExcossModel> list, int position){
       List<ExcossModel> excossModelList = new ArrayList<>();
        excossModelList.add(list.get(position));
        Gson gson = new Gson();

        return gson.toJson(excossModelList);
    }
}
