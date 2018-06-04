package com.gigabytedevelopersinc.apps.mezue.nabotyearbook.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigabytedevelopersinc.apps.mezue.nabotyearbook.R;
import com.gigabytedevelopersinc.apps.mezue.nabotyearbook.adapters.models.StudentsModel;
import com.gigabytedevelopersinc.apps.mezue.nabotyearbook.utils.CardViewClickListener;

import java.util.List;

public class StudentsAdapter extends BaseAdapter {

    private Context context;
    private List<StudentsModel> studentsModels;
    private CardViewClickListener listener;

    public StudentsAdapter(Context context, List<StudentsModel> studentsModels){
        this.context = context;
        this.studentsModels = studentsModels;
    }
    @Override
    public int getCount() {
        return studentsModels.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final StudentsModel studentsModel = studentsModels.get(i);
        View gridView;
        if(view == null){
            gridView = inflater.inflate(R.layout.student_card_item, null);
            final TextView studentN = gridView.findViewById(R.id.student_name);
            final TextView studentQ = gridView.findViewById(R.id.student_text);
            final ImageView studentI = gridView.findViewById(R.id.student_image);
//            Student Name for ebube
            studentN.setText(studentsModel.getStudentName());
            studentI.setImageResource(studentsModel.getStudentImage());
            studentQ.setText(studentsModel.getStudentQuotes());
        }else{
            gridView = view;
        }
        return gridView;
    }
}