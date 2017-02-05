package org.darkbyte.classifieds.LoginAndRegister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.darkbyte.classifieds.R;
import org.darkbyte.classifieds.SharedPrefs;
import org.darkbyte.classifieds.Splash;
import org.darkbyte.classifieds.home.MainActivity;
import org.darkbyte.classifieds.payement_gateway.Payement_MainActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class studentlogintab extends Fragment {
Button signin;
    SharedPrefs sharedPrefs;
    EditText username,password;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_login_student_tab, container, false);
        signin=(Button)rootView.findViewById(R.id.student_login_btn);
        username=(EditText)rootView.findViewById(R.id.student_login_username);
        password=(EditText)rootView.findViewById(R.id.student_login_password);
        sharedPrefs=new SharedPrefs(getContext());
        final String appkey = getString(R.string.APPKEY);
        final String url=getString(R.string.login_url);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // loadToast.show();
                OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                        .connectTimeout(120, TimeUnit.SECONDS)
                        .readTimeout(120, TimeUnit.SECONDS)
                        . writeTimeout(120, TimeUnit.SECONDS)
                        .build();
                AndroidNetworking.post(url)
                        .addBodyParameter("APPKEY", appkey)
                        .addBodyParameter("username", username.getText().toString())
                        .addBodyParameter("password", password.getText().toString())
                        .setPriority(Priority.MEDIUM)
                        .setOkHttpClient(okHttpClient)
                        .build()
                        .getAsJSONArray(new JSONArrayRequestListener() {
                            @Override
                            public void onResponse(JSONArray response) {
                              //  loadToast.success();
                                Log.d("LOG", "RESPONSE" + response);
                                // Toast.makeText(getApplicationContext(), "Response" + response.toString(), Toast.LENGTH_SHORT).show();
                                int j = response.length();
                                for (int i = 0; i < j; i++) {
                                    JSONObject json;
                                    try {
                                        json = response.getJSONObject(i);
                                        if(!json.has("AppKeyError")){

                                            if (!json.has("AuthenticationError")){
                                                String LogedInUserName = json.getString(SharedPrefs.LogedInUserName);
                                                String LogedInEmail = json.getString(SharedPrefs.LogedInEmail);
                                                String LogedInAuthKey = json.getString(SharedPrefs.LogedInKey);
                                                String LogedInPassword = json.getString(SharedPrefs.LogedInPassword);
                                                // Toast.makeText(getApplicationContext(), "Details"+LogedInAuthKey+LogedInUserName+LogedInEmail, Toast.LENGTH_SHORT).show();
                                                sharedPrefs.saveprefs(LogedInUserName, LogedInEmail, LogedInAuthKey,LogedInPassword);
                                                // Toast.makeText(getApplicationContext(), "sharedprfs"+sharedPrefs.getEmail()+sharedPrefs.getLogedInKey()+sharedPrefs.getLogedInUserName(), Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getContext(),MainActivity.class));

                                            }else {
                                                username.setText(null);
                                                password.setText(null);
                                                Toast.makeText(getContext(), "Invalid username or Password", Toast.LENGTH_SHORT).show();
                                            }

                                        }else {
                                            Toast.makeText(getContext(), "AppKeyAuthenticationFailure", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(getContext(), "Please try after sometime", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            @Override
                            public void onError(ANError anError) {
                               // loadToast.error();
                                Log.d("LOG", "RESPONSE" + anError);
                                Toast.makeText(getContext(), "Check Your Network Connection", Toast.LENGTH_SHORT).show();

                            }
                        });


            }
        });
        return rootView;
    }
}