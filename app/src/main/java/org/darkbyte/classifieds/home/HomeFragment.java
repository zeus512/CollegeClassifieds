package org.darkbyte.classifieds.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import org.darkbyte.classifieds.R;
import org.darkbyte.classifieds.SharedPrefs;
import org.darkbyte.classifieds.model.HomeItem_Model;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.darkbyte.classifieds.adapter.HomeFragementAdapter;
import org.darkbyte.classifieds.payement_gateway.Payement_MainActivity;
import org.darkbyte.classifieds.product_full_details.Product_Full_Details;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private Boolean isFabOpen = false;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    private HomeFragementAdapter homeFragementAdapter;
    List<HomeItem_Model> list;
    private FloatingActionButton fab,fab2,fab3,fab4,fab5;
    private Animation fabopen,fabclose,rotateforward,rotatebackward;
    Button btnfab2,btnfab3,btnfab4,btnfab5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_home, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        fab= (FloatingActionButton) v.findViewById(R.id.fab);

        fab2= (FloatingActionButton) v.findViewById(R.id.fab2);
        fab3= (FloatingActionButton) v.findViewById(R.id.fab3);
        fab4= (FloatingActionButton) v.findViewById(R.id.fab4);
        fab5= (FloatingActionButton) v.findViewById(R.id.fab5);



        btnfab2= (Button) v.findViewById(R.id.btnfab2);
        btnfab3= (Button) v.findViewById(R.id.btnfab3);
        btnfab4= (Button) v.findViewById(R.id.btnfab4);
        btnfab5= (Button) v.findViewById(R.id.btnfab5);
        fabopen = AnimationUtils.loadAnimation(getContext(), R.anim.fabopen);
        fabclose = AnimationUtils.loadAnimation(getContext(),R.anim.fabclose);
        rotateforward = AnimationUtils.loadAnimation(getContext(),R.anim.rotateforward);
        rotatebackward = AnimationUtils.loadAnimation(getContext(),R.anim.rotatebackward);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"on click fab",Toast.LENGTH_SHORT).show();
                animateFAB();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"on click fab",Toast.LENGTH_SHORT).show();
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"on click fab",Toast.LENGTH_SHORT).show();
            }
        });
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"on click fab",Toast.LENGTH_SHORT).show();
            }
        });
        fab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"on click fab",Toast.LENGTH_SHORT).show();
            }
        });



        list = new ArrayList<>();

        homeFragementAdapter = new HomeFragementAdapter(getContext(), list);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(homeFragementAdapter);
        recyclerView.setNestedScrollingEnabled(false);
       recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
           @Override
           public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
               return false;
           }

           @Override
           public void onTouchEvent(RecyclerView rv, MotionEvent e) {
startActivity(new Intent(getContext(), Product_Full_Details.class));
           }

           @Override
           public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

           }
       });
        showNews("1");
        return v;
    }

    private void showNews(String s) {
        String url = getString(R.string.items_url);
        String appkey=getString(R.string.APPKEY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                . writeTimeout(120, TimeUnit.SECONDS)
                .build();
        AndroidNetworking.post(url)
                .addBodyParameter("APPKEY", appkey)
                .addBodyParameter("username", SharedPrefs.LogedInUserName)
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
                        list.clear();
                        for (int i = 0; i < j; i++) {
                            Log.v("fgfgh","vlue"+j);
                            JSONObject json=null;
                            try {
                                HomeItem_Model homeItem_model =new HomeItem_Model();
                                json = response.getJSONObject(i);
                                Log.v("fgfgh","vlue"+json);
                                if(!json.has("AppKeyError")){

                                    if (!json.has("NoItems")){

                                          //  progressBar.setVisibility(View.GONE);
                                            if (response != null) {

                                            homeItem_model.setItemName(json.getString("ItemName"));
                                                homeItem_model.setItemDescription(json.getString("ItemDescription"));
                                                homeItem_model.setItemType(json.getString("ItemType"));
                                                homeItem_model.setOriginalPrice(json.getString("ItemOriginalPrice"));
                                                homeItem_model.setSellingPrice(json.getString("ItemPresentPrice"));
                                                list.add(i,homeItem_model);

                                                if(swipeRefreshLayout.isRefreshing()) {
                                                    swipeRefreshLayout.setRefreshing(false);
                                                    recyclerView.setVisibility(View.VISIBLE);}
                                            }


                                    }else {

                                        Toast.makeText(getContext(), "nothing in database", Toast.LENGTH_SHORT).show();
                                    }

                                }else {
                                    Toast.makeText(getContext(), "AppKeyAuthenticationFailure", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getContext(), "Please try after sometime", Toast.LENGTH_SHORT).show();
                            }

                            homeFragementAdapter.refresh(list);
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        // loadToast.error();
                        Log.d("LOG", "RESPONSE" + anError);
                        Toast.makeText(getContext(), "Check Your Network Connection", Toast.LENGTH_SHORT).show();
                        if(swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(false);
                        }

                    }
                });

    }

    public void animateFAB(){

        if(isFabOpen){
            recyclerView.setVisibility(View.VISIBLE);
            //transparent.setBackgroundColor(Color.WHITE);
            fab.startAnimation(rotatebackward);


            btnfab2.setVisibility(View.INVISIBLE);
            btnfab3.setVisibility(View.INVISIBLE);
            btnfab4.setVisibility(View.INVISIBLE);
            btnfab5.setVisibility(View.INVISIBLE);
            fab2.startAnimation(fabclose);
            fab3.startAnimation(fabclose);
            fab4.startAnimation(fabclose);
            fab5.startAnimation(fabclose);

            fab2.setClickable(false);
            fab3.setClickable(false);
            fab4.setClickable(false);
            fab5.setClickable(false);
            isFabOpen = false;

        } else {
            recyclerView.setVisibility(View.INVISIBLE);
            //transparent.setBackgroundColor(Color.TRANSPARENT);
            fab.startAnimation(rotateforward);


            btnfab2.setVisibility(View.VISIBLE);
            btnfab3.setVisibility(View.VISIBLE);
            btnfab4.setVisibility(View.VISIBLE);
            btnfab5.setVisibility(View.VISIBLE);
            fab2.startAnimation(fabopen);
            fab3.startAnimation(fabopen);
            fab4.startAnimation(fabopen);
            fab5.startAnimation(fabopen);

            fab2.setClickable(true);
            fab3.setClickable(true);
            fab4.setClickable(true);
            fab5.setClickable(true);
            isFabOpen = true;

        }
    }
    @Override
    public void onRefresh() {
        showNews("1");
    }
}

