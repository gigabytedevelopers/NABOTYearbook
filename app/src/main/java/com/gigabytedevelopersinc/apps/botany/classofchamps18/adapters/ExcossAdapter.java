package com.gigabytedevelopersinc.apps.botany.classofchamps18.adapters;

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
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.crashlytics.android.Crashlytics;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.R;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.models.ExcossModel;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.models.StudentsModel;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.utils.CardViewClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExcossAdapter extends RecyclerView.Adapter<ExcossAdapter.MyViewHolder>{

    private Context mContext;
    private List<ExcossModel> excossList;
    private CardViewClickListener listener;
    private static final int REQUEST_PHONE_CALL = 1;
    private boolean haveConnectedWifi = false;
    private boolean haveConnectedMobile = false;

    public ExcossAdapter(Context mContext, List<ExcossModel> excossList, CardViewClickListener listener){
        this.mContext = mContext;
        this.excossList = excossList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView excossName, excossOffice;
        private ImageView excossImage, fbImage, igImage, phone;

        public MyViewHolder(View view){
            super(view);
            excossImage = view.findViewById(R.id.excoss_student_image);
            excossName = view.findViewById(R.id.excoss_student_name);
            excossOffice = view.findViewById(R.id.excoss_student_text);
            fbImage = view.findViewById(R.id.excoss_student_facebook);
            igImage = view.findViewById(R.id.excoss_student_instagram);
            phone = view.findViewById(R.id.excoss_student_phone);
        }
    }
    @NonNull
    @Override
    public ExcossAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.excoss_student_card_item, parent, false);
        final MyViewHolder mViewHolder = new MyViewHolder(itemView);
        itemView.setOnClickListener(view -> listener.onItemClick(itemView, mViewHolder.getAdapterPosition()));

        return  mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExcossAdapter.MyViewHolder holder, int i) {
        ExcossModel excossModel = excossList.get(i);
        holder.excossImage.setImageResource(excossModel.getImageResource());
        holder.excossName.setText(excossModel.getExcossName());
        holder.excossOffice.setText(excossModel.getExcossOffice());

        if (haveNetworkConnection()){

            RequestOptions requestOptions = RequestOptions
                    .placeholderOf(R.drawable.placeholder)
                    .centerCrop()
                    .format(DecodeFormat.PREFER_ARGB_8888);
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .asBitmap()
                    .load(excossModel.getImageUrl())
                    .into(holder.excossImage);

        }else {
            holder.excossImage.setImageResource(excossModel.getImageResource());
        }

        holder.fbImage.setOnClickListener((View view) -> mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(excossModel.getExcossFacebook()))));

        holder.igImage.setOnClickListener((View view) -> mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(excossModel.getExcossInstagram()))));

        holder.phone.setOnClickListener((View view) -> {
            try {
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel: " + excossModel.getCallPhone()));
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
        return excossList.size();
    }
}
