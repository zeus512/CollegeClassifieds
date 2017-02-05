package org.darkbyte.classifieds;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.darkbyte.classifieds.LoginAndRegister.Login;
import org.darkbyte.classifieds.LoginAndRegister.Login_Register_Sel;
import org.darkbyte.classifieds.home.MainActivity;
import org.json.JSONArray;

public class Splash extends AppCompatActivity {
    String username,password;
    SharedPrefs sharedPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPrefs = new SharedPrefs(this);
       startActivity(new Intent(Splash.this,Login_Register_Sel.class));
    }
}
