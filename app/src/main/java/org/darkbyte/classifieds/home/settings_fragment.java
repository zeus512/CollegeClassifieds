package org.darkbyte.classifieds.home;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.darkbyte.classifieds.R;
import org.darkbyte.classifieds.settings.Emergency;
import org.darkbyte.classifieds.settings.Redeem_coupon;

/**
 * Created by root on 16/8/16.
 */
public class settings_fragment extends Fragment {
    LinearLayout rateApp,inviteFriends,suggestApp,redeemcoupon,emergency;
    public settings_fragment(){

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_settings_fragment,container,false);
        rateApp = (LinearLayout)v.findViewById(R.id.rateApp);
        inviteFriends = (LinearLayout)v.findViewById(R.id.inviteFriends);
        suggestApp = (LinearLayout)v.findViewById(R.id.suggestApp);
        redeemcoupon=(LinearLayout)v.findViewById(R.id.redeemcoupon);
        emergency=(LinearLayout)v.findViewById(R.id.emergency);

        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Emergency.class));
            }
        });
        redeemcoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Redeem_coupon.class));
            }
        });
        inviteFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"coming soon",Toast.LENGTH_SHORT).show();
            }
        });
        suggestApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"coming soon",Toast.LENGTH_SHORT).show();
            }
        });


        rateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog db = new Dialog(getContext());
                db.setContentView(R.layout.dialograteapp);
                db.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                db.show();
            }
        });

        return v;
    }

}