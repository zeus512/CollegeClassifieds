package org.darkbyte.classifieds;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.instamojo.android.Instamojo;

/**
 * Created by root on 24/1/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
        Instamojo.initialize(this);
    }
}
