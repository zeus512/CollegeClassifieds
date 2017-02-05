package org.darkbyte.classifieds.Upload;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.darkbyte.classifieds.R;
import org.darkbyte.classifieds.SharedPrefs;
import org.darkbyte.classifieds.Utils;
import org.darkbyte.classifieds.model.HomeItem_Model;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.R.id.list;
import static java.security.AccessController.getContext;

public class Upload extends AppCompatActivity {
    private ImageView imageView1, imageView2, imageView3, imageView4, mainImage, temp;
    private View view1, view2, view3, view4, mainView;
    private TextView textView1, textView2, textView3, textView4, mainTextView;
    private int anInt = 1;
    private Spinner spinner;
    EditText itemName,orPrice,selPrice,desc;
    byte[]img;
    Bitmap imgbit;
    Button upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        spinner=(Spinner)findViewById(R.id.spinner);
        imageView1 = (ImageView) findViewById(R.id.uploadImg1);
        imageView2 = (ImageView) findViewById(R.id.uploadImg2);
        imageView3 = (ImageView) findViewById(R.id.uploadImg3);
        imageView4 = (ImageView) findViewById(R.id.uploadImg4);
        mainImage = (ImageView) findViewById(R.id.uploadMainImage);
        upload=(Button)findViewById(R.id.uploaddata);
        view1 = findViewById(R.id.uploadViewReal1);
        view2 = findViewById(R.id.uploadViewReal2);
        view3 = findViewById(R.id.uploadViewReal3);
        view4 = findViewById(R.id.uploadViewReal4);
        mainView = findViewById(R.id.uploadMainImageView);

        textView1 = (TextView) findViewById(R.id.uploadView1);
        textView2 = (TextView) findViewById(R.id.uploadView2);
        textView3 = (TextView) findViewById(R.id.uploadView3);
        textView4 = (TextView) findViewById(R.id.uploadView4);
        mainTextView = (TextView) findViewById(R.id.uploadMainImageTextView);

        itemName=(EditText)findViewById(R.id.itemName);
        orPrice=(EditText)findViewById(R.id.originalPrice);
        selPrice=(EditText)findViewById(R.id.sellingPrice);
        desc=(EditText)findViewById(R.id.desc);
        mainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                startActivityForResult(chooserIntent, 5);
            }


        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                startActivityForResult(chooserIntent, 1);
            }


        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                startActivityForResult(chooserIntent, 2);
            }


        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                startActivityForResult(chooserIntent, 3);
            }


        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                startActivityForResult(chooserIntent, 4);
            }


        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
    }

    public void uploadImage(View view) {


        if (CheckPermission()) {
            Log.d("TAG", "ab");


            Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
            getIntent.setType("image/*");

            Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickIntent.setType("image/*");

            Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

            startActivityForResult(chooserIntent, 12);
        } else {
            Log.d("TAG", "cd");
            setPermission();
        }


    }

    public boolean CheckPermission() {

        int result1 = ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE);
        return result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED;

    }


    public void setPermission() {

        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 12 && resultCode == RESULT_OK && data != null && data.getData() != null) {


            try {
                Uri uri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);


                if (anInt == 1) {


                    imageView1.setImageBitmap(bitmap);
                    view1.setVisibility(View.GONE);
                    textView1.setVisibility(View.GONE);
                    view2.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    anInt += 1;

                } else if (anInt == 2) {

                    imageView2.setImageBitmap(bitmap);
                    view2.setVisibility(View.GONE);
                    textView2.setVisibility(View.GONE);
                    view3.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                    anInt += 1;

                } else if (anInt == 3) {

                    imageView3.setImageBitmap(bitmap);
                    view3.setVisibility(View.GONE);
                    textView3.setVisibility(View.GONE);
                    view4.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.VISIBLE);
                    anInt += 1;

                } else if (anInt == 4) {

                    imageView4.setImageBitmap(bitmap);
                    view4.setVisibility(View.GONE);
                    textView4.setVisibility(View.GONE);

                }


            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {


            try {
                Uri uri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                imageView1.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        if (requestCode == 2 && resultCode == RESULT_OK && data != null && data.getData() != null) {


            try {
                Uri uri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                imageView2.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        if (requestCode == 3 && resultCode == RESULT_OK && data != null && data.getData() != null) {


            try {
                Uri uri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                imageView3.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        if (requestCode == 4 && resultCode == RESULT_OK && data != null && data.getData() != null) {


            try {
                Uri uri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                byte[] bytes = Utils.getBytesFromBitmap(bitmap);
                imageView4.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        if (requestCode == 5 && resultCode == RESULT_OK && data != null && data.getData() != null) {


            try {
                Uri uri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                mainImage.setImageBitmap(bitmap);
                img=Utils.getBytesFromBitmap(bitmap);
                imgbit=bitmap;
                mainView.setVisibility(View.GONE);
                mainTextView.setVisibility(View.GONE);

            } catch (Exception e) {
                e.printStackTrace();


            }
        }

    }


    void sendData() {
        String url = getString(R.string.itemupload);
        String appkey = getString(R.string.APPKEY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build();
        AndroidNetworking.post(url)
                .addBodyParameter("APPKEY", appkey)
                .addBodyParameter("ItemName",itemName.getText().toString())
                .addBodyParameter("ItemType",spinner.getSelectedItem().toString())
                .addBodyParameter("ItemOriginalPrice",orPrice.getText().toString())
                .addBodyParameter("ItemPresentPrice",selPrice.getText().toString())
                .addBodyParameter("ItemDescription",desc.getText().toString())
                .addBodyParameter("username", SharedPrefs.LogedInUserName)
                .addBodyParameter("ItemImage","abc")
                .setPriority(Priority.MEDIUM)
                .setOkHttpClient(okHttpClient)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //  loadToast.success();
                        Log.d("LOG", "RESPONSE" + response);
                        Log.v("details",Utils.getStringFromBitmap(imgbit));
                        Toast.makeText(getApplicationContext(), "Uploaded Successfully!!", Toast.LENGTH_SHORT).show();

                        // Toast.makeText(getApplicationContext(), "Response" + response.toString(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(ANError anError) {
                        // loadToast.error();
                        Log.d("LOG", "RESPONSE" + anError);
                        Toast.makeText(getApplicationContext(), "Uploaded Successfully!!", Toast.LENGTH_SHORT).show();


                    }
                });

    }
}