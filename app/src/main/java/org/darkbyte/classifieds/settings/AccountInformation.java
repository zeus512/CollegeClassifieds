package org.darkbyte.classifieds.settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.darkbyte.classifieds.R;
import org.darkbyte.classifieds.SharedPrefs;
import org.darkbyte.classifieds.home.MainActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class AccountInformation extends AppCompatActivity {

    private EditText name,email,password,address,phone;
    private Button save,save2 ;
    private LinearLayout linearLayout,linearLayout2;

SharedPrefs sharedPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);
        save = (Button) findViewById(R.id.student_reg);
        name = (EditText) findViewById(R.id.student_reg_name);
        email = (EditText) findViewById(R.id.student_reg_email);
        password = (EditText) findViewById(R.id.student_reg_passwd);
        address = (EditText) findViewById(R.id.student_reg_address);
        phone = (EditText) findViewById(R.id.student_reg_phone)  ;
        linearLayout = (LinearLayout)findViewById(R.id.stundt_reg_verify_lyout);
        linearLayout2 = (LinearLayout)findViewById(R.id.stundt_reg_verify_lyout2);
        save2=(Button)findViewById(R.id.student_reg_verify);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayout.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);
            }
        });
        sharedPrefs=new SharedPrefs(this);
        final String username=sharedPrefs.getLogedInUserName();
        final String pwd=sharedPrefs.getLogedInPassword();
        save2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String appkey = getString(R.string.APPKEY);
                String url =getString(R.string.updateprofileurl);

                    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                            .connectTimeout(120, TimeUnit.SECONDS)
                            .readTimeout(120, TimeUnit.SECONDS)
                            . writeTimeout(120, TimeUnit.SECONDS)
                            .build();
                    AndroidNetworking.post(url)
                            .addBodyParameter("APPKEY",appkey)
                            .addBodyParameter("username", username)
                            .addBodyParameter("email", email.getText().toString())
                            .addBodyParameter("fullname",name.getText().toString() )
                            .addBodyParameter("address", address.getText().toString())
                            .addBodyParameter("phonenumber",phone.getText().toString())
                            .addBodyParameter("password", pwd)
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
                                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                                }else {

                                                    password.setText(null);
                                                    Toast.makeText(getApplicationContext(), "Invalid  Password", Toast.LENGTH_SHORT).show();
                                                }

                                            }else {
                                                Toast.makeText(getApplicationContext(), "AppKeyAuthenticationFailure", Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(getApplicationContext(), "Please try after sometime", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                @Override
                                public void onError(ANError anError) {
                                    // loadToast.error();
                                    Log.d("LOG", "RESPONSE" + anError);
                                    Toast.makeText(getApplicationContext(), "Check Your Network Connection", Toast.LENGTH_SHORT).show();

                                }
                            });

                }

        });




    }
    public void setData(String name ,String email, String address, String phone){
        this.name.setText(name);
        this.email.setText(email);

        this.address.setText(address);
        this.phone.setText(phone);
    }
}
