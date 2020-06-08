package com.gigabytedevelopersinc.apps.mezue.classofchamps18.activities;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.R;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.utils.TinyDB;

import java.util.Objects;

/**
 * @author Created by Emmanuel Nwokoma (Founder and CEO at Gigabyte Developers) on 8/3/2018
 **/
public class NoNetworkWarningActivity extends AppCompatActivity {
    Button internetProceedButton;
    TinyDB tinyDB;
    private boolean haveConnectedWifi = false;
    private boolean haveConnectedMobile = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_internet_warning_layout);

        if (haveNetworkConnection()){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

        tinyDB = new TinyDB(this);
        internetProceedButton = findViewById(R.id.warning_no_internet_proceed_button);

        internetProceedButton.setOnClickListener((View view)->{
            startActivity(new Intent(this,MainActivity.class));
            finish();
        });

    }
    private boolean haveNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) Objects.requireNonNull(this).getSystemService(Context.CONNECTIVITY_SERVICE);
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
