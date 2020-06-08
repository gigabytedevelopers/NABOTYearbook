package com.gigabytedevelopersinc.apps.botany.classofchamps18.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigabytedevelopersinc.apps.botany.classofchamps18.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {
    private ImageView gig_fb, gig_wb,gig_tw, gig_lin, gig_instagram;
    private TextView nabot_facebook, nabot_twitter;
    private CardView feedback;



    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gig_fb = view.findViewById(R.id.gig_facebook);
        gig_fb.setOnClickListener(view1 -> {
            Intent fb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/gigabytedevelopersinc"));
            startActivity(fb);
        });
        gig_tw = view.findViewById(R.id.gig_twitter);
        gig_tw.setOnClickListener(view1 -> {
            Intent twb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com/gigabytedevsinc"));
            startActivity(twb);
        });
        gig_lin = view.findViewById(R.id.gig_linkedin);
        gig_lin.setOnClickListener(view1 -> {
            Intent lin = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/gigabytedevelopers"));
            startActivity(lin);
        });
        gig_instagram = view.findViewById(R.id.gig_instagram);
        gig_instagram.setOnClickListener(view1 -> {
            Intent ig = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/gigabytedevelopersinc"));
            startActivity(ig);
        });
        gig_wb = view.findViewById(R.id.gig_web);
        gig_wb.setOnClickListener(view1 -> {
            Intent wb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gigabytedevelopersinc.com"));
            startActivity(wb);
        });
        nabot_facebook = view.findViewById(R.id.nabot_facebook);
        nabot_facebook.setOnClickListener(view1 -> {
            Intent nfb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/NABOTsUnizikChapter"));
            startActivity(nfb);
        });
        nabot_twitter = view.findViewById(R.id.nabot_tw);
        nabot_twitter.setOnClickListener(view1 -> {
            Intent ntw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com/NABOTsUnizik"));
            startActivity(ntw);
        });

        feedback = view.findViewById(R.id.card_feedback_about);
        feedback.setOnClickListener(view1 -> {
            BottomSheetDialog sheetDialog = new BottomSheetDialog(Objects.requireNonNull(getContext()));
            final View bottomSheetFeedback = getLayoutInflater().inflate(R.layout.bottom_sheet_feedback, null);
            (bottomSheetFeedback.findViewById(R.id.button_yes)).setOnClickListener(view2 -> {
                Intent mail = new Intent(Intent.ACTION_SENDTO);
                mail.setType("text/html");
                mail.setData(Uri.parse("mailto:"));
                mail.putExtra(Intent.EXTRA_EMAIL, new String[]{ "gigabytedevelopers@gmail.com"});
                mail.putExtra(Intent.EXTRA_SUBJECT, "Hello Gigabyte Developers");
                startActivity(Intent.createChooser(mail, "Send a Feedback to Gigabyte Developers with"));
                sheetDialog.dismiss();
            });
            (bottomSheetFeedback.findViewById(R.id.button_no)).setOnClickListener(view2 -> {
                sheetDialog.dismiss();
            });
            sheetDialog.setContentView(bottomSheetFeedback);
            sheetDialog.setCancelable(false);
            sheetDialog.show();
        });

    }
}
