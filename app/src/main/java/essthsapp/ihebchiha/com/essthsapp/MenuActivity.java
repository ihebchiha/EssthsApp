package essthsapp.ihebchiha.com.essthsapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import essthsapp.ihebchiha.com.essthsapp.Adapters.ViewPagerAdapter;
import essthsapp.ihebchiha.com.essthsapp.Fragments.FeedFragment;
import essthsapp.ihebchiha.com.essthsapp.Fragments.NotifFragment;
import essthsapp.ihebchiha.com.essthsapp.Fragments.OpsFragment;
import essthsapp.ihebchiha.com.essthsapp.Fragments.ProfileFragment;
import essthsapp.ihebchiha.com.essthsapp.Utils.UtilsSharedPreferences;

public class MenuActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter vAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /*checkUser();
        SharedPreferences SP = getApplicationContext().getSharedPreferences("NAME", 0);*/


        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);
        vAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        //add fragment
        vAdapter.AddFragment(new FeedFragment(),"Feed");
        vAdapter.AddFragment(new NotifFragment(),"Notification");
        vAdapter.AddFragment(new OpsFragment(),"Operations");
        vAdapter.AddFragment(new ProfileFragment(),"Profile");
        viewPager.setAdapter(vAdapter);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.getTabAt(0).setIcon(R.drawable.ic_rss_feed_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_notifications_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_work_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_account_box_black_24dp);

        //Remove Shadow from ActionBar
        android.support.v7.app.ActionBar action= getSupportActionBar();
        if (action != null) {
            action.setElevation(0);
        }
    }

    public void checkUser(){
        Boolean Check = Boolean.valueOf(UtilsSharedPreferences.readSharedSetting(MenuActivity.this, "PassingThrough", "true"));

        Intent introIntent = new Intent(MenuActivity.this, LoginActivity.class);
        introIntent.putExtra("PassingThrough", Check);

        if (Check) {
            startActivity(introIntent);
        }
    }
}
