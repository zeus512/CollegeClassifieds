package org.darkbyte.classifieds.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import org.darkbyte.classifieds.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    CharSequence action_bar_name;
    private int[] tabIcons = {
            R.drawable.newsfeed,
            R.drawable.profile_icon,
            R.drawable.favourites,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tab_view);
        sharedPreferences = getSharedPreferences("my preferences", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                switch (pos) {
                    case 0:
                        action_bar_name = "Catalog";
                        break;

                         case 1:
                        action_bar_name = "Profile";
                        break;

                    case 2:
                        action_bar_name = "Settings";
                        break;
                    default:
                        action_bar_name = "Classified";
                }
                getSupportActionBar().setTitle(action_bar_name);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);

        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "ONE");

        adapter.addFragment(new profile_fragment(), "TWO");
        adapter.addFragment(new settings_fragment(), "THREE");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // return mFragmentTitleList.get(position);

            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 12 && resultCode == RESULT_OK && data != null && data.getData() != null) {




            try {
                Uri uri = data.getData();
                Log.d("TAG","1");
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                profile_fragment profile_fragmenta = (profile_fragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.main_viewpager + ":" + viewPager.getCurrentItem());
                profile_fragmenta.setImage(bitmap);



            } catch (Exception e){}  }}}