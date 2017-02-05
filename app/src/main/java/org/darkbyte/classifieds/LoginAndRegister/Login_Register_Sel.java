package org.darkbyte.classifieds.LoginAndRegister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.darkbyte.classifieds.R;


public class Login_Register_Sel extends AppCompatActivity implements View.OnClickListener {
Button sign_in,createaccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__register__sel);
        sign_in=(Button)findViewById(R.id.mainpage_sign_in);
        createaccount=(Button)findViewById(R.id.main_page_create_account);
        sign_in.setOnClickListener(this);
        createaccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.mainpage_sign_in:
                startActivity(new Intent(this,Login.class));
                break;
            case R.id.main_page_create_account:
                startActivity(new Intent(this,Register.class));
                break;
        }
    }
}
