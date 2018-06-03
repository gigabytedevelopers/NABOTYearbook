package com.gigabytedevelopersinc.apps.mezue.nabotyearbook.fragment;


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
import android.widget.GridView;

import com.gigabytedevelopersinc.apps.mezue.nabotyearbook.R;
import com.gigabytedevelopersinc.apps.mezue.nabotyearbook.adapters.StudentsAdapter;
import com.gigabytedevelopersinc.apps.mezue.nabotyearbook.adapters.models.StudentsModel;
import com.gigabytedevelopersinc.apps.mezue.nabotyearbook.utils.CardViewClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentFragment extends Fragment {
    private GridView gridView;
    private List<StudentsModel> list;
    private StudentsAdapter adapter;


    public StudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = view.findViewById(R.id.student_list);
        list = new ArrayList<>();
        adapter = new StudentsAdapter(getContext(), list);
        gridView.setAdapter(adapter);
        StudentsModel studentsModel = new StudentsModel("Mezue Chukwudi",
                "Fuck you",
                    R.drawable.image_1);
        list.add(studentsModel);
        StudentsModel studentsModel2 = new StudentsModel("Mezue Chukwudi",
                "You",
                R.drawable.image_1);
        list.add(studentsModel2);
        StudentsModel studentsModel3 = new StudentsModel("Mezue Chukwudi",
                "hello",
                R.drawable.image_1);
        list.add(studentsModel3);


    }
}
