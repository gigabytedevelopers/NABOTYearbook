package com.gigabytedevelopersinc.apps.botany.classofchamps18.adapters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.R;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.fragment.StudentFragment;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.models.BirthdayModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BirthdayAdapter extends RecyclerView.Adapter<BirthdayAdapter.MyViewHolder> {

    private Context mContext;
    private List<BirthdayModel> list;
    private boolean haveConnectedWifi = false;
    private boolean haveConnectedMobile = false;

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

        holder.name.setText(birthdayModel.getName());
        holder.nickName.setText(birthdayModel.getNickName());
        holder.date.setText(birthdayModel.getDate());
        if (haveNetworkConnection()){

            RequestOptions requestOptions = RequestOptions
                    .placeholderOf(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .format(DecodeFormat.PREFER_ARGB_8888);


            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .asBitmap()
                    .load(birthdayModel.getImageUrl())
                    .into(holder.imageView);

        }else {
            holder.imageView.setImageResource(birthdayModel.getImageResource());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private boolean haveNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) Objects.requireNonNull(mContext).getSystemService(Context.CONNECTIVITY_SERVICE);
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
