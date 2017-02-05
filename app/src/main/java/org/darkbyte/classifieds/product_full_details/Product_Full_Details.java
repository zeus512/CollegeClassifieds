package org.darkbyte.classifieds.product_full_details;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.audiofx.AudioEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.darkbyte.classifieds.ChatService.ChatAdapter;
import org.darkbyte.classifieds.R;
import org.darkbyte.classifieds.SharedPrefs;
import org.darkbyte.classifieds.model.HomeItem_Model;
import org.darkbyte.classifieds.payement_gateway.Payement_MainActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static java.security.AccessController.getContext;

public class Product_Full_Details extends AppCompatActivity {
    private RecyclerView recyclerView;
    product_image_adapter mAdapter;
    private List<HomeItem_Model> list;
    private  RecyclerView.LayoutManager mLayoutManager;
    Button buynow;
    private List<Bitmap> bitmaps = new ArrayList<>();
TextView title,description,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__full__details);
        list=new ArrayList<>();
        buynow=(Button)findViewById(R.id.buynowitem);
        title=(TextView)findViewById(R.id.productTitle);
        price=(TextView)findViewById(R.id.productPrice);
        description=(TextView)findViewById(R.id.productDescription);
        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Product_Full_Details.this, Payement_MainActivity.class));
            }
        });
        int id=  getIntent().getIntExtra("ItemId",0);
        if(id!=0){
            String url = getString(R.string.itemidurl);
            String appkey=getString(R.string.APPKEY);
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    . writeTimeout(120, TimeUnit.SECONDS)
                    .build();
            AndroidNetworking.post(url)
                    .addBodyParameter("APPKEY", appkey)
                    .addBodyParameter("username", SharedPrefs.LogedInUserName)
                    .addBodyParameter("ItemId",Integer.toString(id))
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
                                Log.v("fgfgh","vlue"+j);
                                JSONObject json=null;
                                try {

                                    json = response.getJSONObject(i);
                                    Log.v("fgfgh","vlue"+json);
                                    if(!json.has("AppKeyError")){

                                        if (!json.has("NoItems")){

                                            //  progressBar.setVisibility(View.GONE);
                                            if (response != null) {
                                                Log.d("RESPONSE ",json.getString("ItemName"));
                                                title.setText(json.getString("ItemName"));
                                                price.setText(json.getString("ItemPresentPrice"));
                                                description.setText(json.getString("ItemDescription"));
                                            }


                                        }else {

                                            Toast.makeText(getApplicationContext(), "nothing in database", Toast.LENGTH_SHORT).show();
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
//        HomeItem_Model item=list.get(id);

        recyclerView = (RecyclerView)findViewById(R.id.recylerViewProduct);


        Bitmap original = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.i1);
        Bitmap original2 = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.i2);




        bitmaps.add( original);
        bitmaps.add( original2);
        bitmaps.add( original);
        bitmaps.add( original2);
        bitmaps.add( original);
        bitmaps.add( original2);
        bitmaps.add( original);
        bitmaps.add( original2);


        mAdapter = new product_image_adapter(bitmaps,this);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }


}