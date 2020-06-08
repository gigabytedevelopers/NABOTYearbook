package com.gigabytedevelopersinc.apps.botany.classofchamps18.adapters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.R;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.models.StaffsModel;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.utils.CardViewClickListener;

import java.util.List;
import java.util.Objects;

/**
 * @author Created by Emmanuel Nwokoma (Founder and CEO at Gigabyte Developers) on 8/20/2018
 **/
public class StaffsAdapter extends RecyclerView.Adapter<StaffsAdapter.MyViewHolder> {
    private Context mContext;
    private List<StaffsModel> staffsList;
    private CardViewClickListener listener;
    private static final int REQUEST_PHONE_CALL = 1;
    private boolean haveConnectedWifi = false;
    private boolean haveConnectedMobile = false;

    public StaffsAdapter(Context mContext, List<StaffsModel> staffsList, CardViewClickListener listener){
        this.mContext = mContext;
        this.staffsList = staffsList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView staffName, staffOffice;
        private ImageView staffImage;
        private Button staffPhone;

        public MyViewHolder(View view){
            super(view);
            staffImage = view.findViewById(R.id.staff_card_image);
            staffName = view.findViewById(R.id.staff_name);
            staffOffice = view.findViewById(R.id.staff_office);
            staffPhone = view.findViewById(R.id.staff_call_btn);
        }
    }

    @NonNull
    @Override
    public StaffsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.staffs_card_item, parent, false);
        final StaffsAdapter.MyViewHolder mViewHolder = new StaffsAdapter.MyViewHolder(itemView);
        itemView.setOnClickListener(view -> listener.onItemClick(itemView, mViewHolder.getAdapterPosition()));

        return  mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaffsAdapter.MyViewHolder holder, int i) {
        StaffsModel staffsModel = staffsList.get(i);
        holder.staffImage.setImageResource(staffsModel.getImageResource());
        holder.staffName.setText(staffsModel.getStaffName());
        holder.staffOffice.setText(staffsModel.getStaffsOffice());

        if (haveNetworkConnection()){
            RequestOptions requestOptions = RequestOptions
                    .placeholderOf(R.drawable.placeholder)
                    .centerCrop()
                    .format(DecodeFormat.PREFER_ARGB_8888);
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .asBitmap()
                    .load(staffsModel.getImageUrl())
                    .into(holder.staffImage);

        } else {
            holder.staffImage.setImageResource(staffsModel.getImageResource());
        }
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
        return staffsList.size();
    }
}
