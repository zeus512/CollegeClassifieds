package org.darkbyte.classifieds.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.darkbyte.classifieds.R;
import org.darkbyte.classifieds.SharedPrefs;
import org.darkbyte.classifieds.Upload.Upload;
import org.darkbyte.classifieds.adapter.ProfileAdapter1;
import org.darkbyte.classifieds.adapter.ProfileAdapter2;

/**
 * Created by root on 16/8/16.
 */
public class profile_fragment extends Fragment {

    String list1[]={"Account Information","Your Orders","Sold Items","Payment Options","Support"};
    Integer image1[]={R.drawable.acc_info, R.drawable.your_order, R.drawable.sold_item, R.drawable.rupee, R.drawable.support};
    FloatingActionButton fab;
    ImageView viewprobtn;
    ImageView profilePic;
    SharedPrefs sharedPrefs;
    TextView name;
    public profile_fragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.profile,container,false);
        profilePic=(ImageView)view.findViewById(R.id.profilepic);
        fab=(FloatingActionButton)view.findViewById(R.id.fab_upload);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Upload.class));
            }
        });
        name=(TextView)view.findViewById(R.id.profileusername);

        sharedPrefs=new SharedPrefs(getContext());
        name.setText(sharedPrefs.getLogedInUserName()   );
        setRoundImage(profilePic,BitmapFactory.decodeResource(this.getResources(),R.drawable.user));
//        viewprobtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), ProfileActivity.class));
//            }
//        });
        RecyclerView recyclerView1=(RecyclerView)view.findViewById(R.id.recycler1);

        ProfileAdapter1 obj1=new ProfileAdapter1(getContext(),list1,image1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView1.setAdapter(obj1);


        recyclerView1.setNestedScrollingEnabled(false);
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                getActivity().startActivityForResult(chooserIntent, 12);
            }
        });

        return view;
    }

    public  void setImage(Bitmap bitmap){
        Log.d("TAG","3");
        setRoundImage(profilePic,bitmap);
    }
    void setRoundImage(ImageView view,Bitmap bitmap){

//        Bitmap bitmap = ;
        RoundedBitmapDrawable roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCornerRadius(2.0f);
        roundedBitmapDrawable.setCircular(true);
        view.setImageDrawable(roundedBitmapDrawable);

    }
}