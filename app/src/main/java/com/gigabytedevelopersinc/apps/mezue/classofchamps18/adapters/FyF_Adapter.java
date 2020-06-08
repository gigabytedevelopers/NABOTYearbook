package com.gigabytedevelopersinc.apps.mezue.classofchamps18.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.crashlytics.android.Crashlytics;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.R;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.models.FyF_Model;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.utils.CardViewClickListener;

import java.util.List;
import java.util.Objects;

/**
 * @author Created by Emmanuel Nwokoma (Founder and CEO at Gigabyte Developers) on 8/15/2018
 **/
public class FyF_Adapter extends RecyclerView.Adapter<FyF_Adapter.MyViewHolder>{

    private Context mContext;
    private List<FyF_Model> fyf_ExcossList;
    private CardViewClickListener listener;
    private static final int REQUEST_PHONE_CALL = 1;
    private boolean haveConnectedWifi = false;
    private boolean haveConnectedMobile = false;

    public FyF_Adapter(Context mContext, List<FyF_Model> fyf_ExcossList, CardViewClickListener listener){
        this.mContext = mContext;
        this.fyf_ExcossList = fyf_ExcossList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView fyf_ExcossName, fyf_ExcossOffice;
        private ImageView fyf_ExcossImage, fbImage, igImage, phone;

        public MyViewHolder(View view){
            super(view);
            fyf_ExcossImage = view.findViewById(R.id.fyf_excoss_student_image);
            fyf_ExcossName = view.findViewById(R.id.fyf_excoss_student_name);
            fyf_ExcossOffice = view.findViewById(R.id.fyf_excoss_student_text);
            fbImage = view.findViewById(R.id.fyf_excoss_student_facebook);
            igImage = view.findViewById(R.id.fyf_excoss_student_instagram);
            phone = view.findViewById(R.id.fyf_excoss_student_phone);
        }
    }
    @NonNull
    @Override
    public FyF_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fyf_student_card_item, parent, false);
        final FyF_Adapter.MyViewHolder mViewHolder = new MyViewHolder(itemView);
        itemView.setOnClickListener(view -> listener.onItemClick(itemView, mViewHolder.getAdapterPosition()));

        return  mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FyF_Adapter.MyViewHolder holder, int i) {
        FyF_Model fyf_ExcossModel = fyf_ExcossList.get(i);
        holder.fyf_ExcossImage.setImageResource(fyf_ExcossModel.getImageResource());
        holder.fyf_ExcossName.setText(fyf_ExcossModel.getFyfExcossName());
        holder.fyf_ExcossOffice.setText(fyf_ExcossModel.getFyfExcossOffice());

        if (haveNetworkConnection()){
            RequestOptions requestOptions = RequestOptions
                    .placeholderOf(R.drawable.placeholder)
                    .centerCrop()
                    .format(DecodeFormat.PREFER_ARGB_8888);
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .asBitmap()
                    .load(fyf_ExcossModel.getImageUrl())
                    .into(holder.fyf_ExcossImage);

        } else {
            holder.fyf_ExcossImage.setImageResource(fyf_ExcossModel.getImageResource());
        }

        holder.fbImage.setOnClickListener((View view) -> mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(fyf_ExcossModel.getFyfExcosFacebook()))));

        holder.igImage.setOnClickListener((View view) -> mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(fyf_ExcossModel.getFyfExcosInstagram()))));

        holder.phone.setOnClickListener((View view) -> {
            try {
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel: " + fyf_ExcossModel.getCallPhone()));
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                    } else{
                        mContext.startActivity(call);
                    }
                } else {
                    mContext.startActivity(call);
                }
            } catch (ActivityNotFoundException activityException) {
                Log.e("Calling a Phone Number", "Call failed", activityException);
                Crashlytics.logException(activityException);
            }
        });
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

    @Override
    public int getItemCount() {
        return fyf_ExcossList.size();
    }
}
