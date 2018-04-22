package essthsapp.ihebchiha.com.essthsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import essthsapp.ihebchiha.com.essthsapp.Adapters.ViewPagerAdapter;
import essthsapp.ihebchiha.com.essthsapp.Fragments.FeedFragment;
import essthsapp.ihebchiha.com.essthsapp.Fragments.OpsFragment;
import essthsapp.ihebchiha.com.essthsapp.Fragments.ProfileFragment;

public class MenuActivity extends AppCompatActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter vAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);




        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);
        vAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        //add fragment
        vAdapter.AddFragment(new FeedFragment().newInstance("http://172.16.51.72/otherside/rssfeed.xml",""),"Feed");     //https://www.popsci.com/rss-science.xml
        vAdapter.AddFragment(new OpsFragment(),"Operations");
        vAdapter.AddFragment(new ProfileFragment(),"Profile");
        viewPager.setAdapter(vAdapter);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.getTabAt(0).setIcon(R.drawable.ic_rss_feed_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_work_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_account_box_black_24dp);

        //Remove Shadow from ActionBar
        android.support.v7.app.ActionBar action= getSupportActionBar();
        if (action != null) {
            action.setElevation(0);
        }
    }


}
