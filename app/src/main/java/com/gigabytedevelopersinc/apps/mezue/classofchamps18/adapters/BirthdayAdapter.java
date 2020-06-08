package com.gigabytedevelopersinc.apps.mezue.classofchamps18.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigabytedevelopersinc.apps.mezue.classofchamps18.R;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.models.BirthdayModel;

import java.util.List;

public class BirthdayAdapter extends RecyclerView.Adapter<BirthdayAdapter.MyViewHolder> {

    private Context mContext;
    private List<BirthdayModel> list;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView name, nickName, date;
        public MyViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.birthday_card_image);
            name = view.findViewById(R.id.birthday_card_student_name);
            nickName = view.findViewById(R.id.birthday_card_student_nickname);
            date = view.findViewById(R.id.birthday_card_student_birthdate);
        }
    }
    public BirthdayAdapter(Context mContext, List<BirthdayModel> list){
        this.mContext = mContext;
        this.list = list;
    }
    @NonNull
    @Override
    public BirthdayAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.birthday_card_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BirthdayAdapter.MyViewHolder holder, int i) {
        BirthdayModel birthdayModel = list.get(i);
        holder.imageView.setImageResource(birthdayModel.getImageResource());
        holder.name.setText(birthdayModel.getName());
        holder.nickName.setText(birthdayModel.getNickName());
        holder.date.setText(birthdayModel.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
