package com.gigabytedevelopersinc.apps.mezue.classofchamps18.fragment;

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

import com.gigabytedevelopersinc.apps.mezue.classofchamps18.R;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.adapters.BirthdayAdapter;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.models.BirthdayModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeNewsFragment extends Fragment {

    public static List<BirthdayModel> birthdayList;
    private RecyclerView recyclerView;
    private BirthdayAdapter adapter;

    public HomeNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.news_list);
        adapter = new BirthdayAdapter(getContext(),birthdayList);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }
}
