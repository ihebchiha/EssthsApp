package essthsapp.ihebchiha.com.essthsapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import essthsapp.ihebchiha.com.essthsapp.Adapters.ViewPagerAdapter;
import essthsapp.ihebchiha.com.essthsapp.Fragments.FeedFragment;
import essthsapp.ihebchiha.com.essthsapp.Fragments.OpsFragment;
import essthsapp.ihebchiha.com.essthsapp.Fragments.ProfileFragment;
import essthsapp.ihebchiha.com.essthsapp.Utils.UtilsSharedPreferences;
import essthsapp.ihebchiha.com.essthsapp.rss.ArticleActivity;
import essthsapp.ihebchiha.com.essthsapp.rss.ArticleFragment;
import essthsapp.ihebchiha.com.essthsapp.rss.RssAdapter;

public class MenuActivity extends AppCompatActivity implements FeedFragment.OnFragmentInteractionListener,RssAdapter.UrlLoader{

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
        vAdapter.AddFragment(FeedFragment.newInstance("http://192.168.1.6/khedma/iheb.xml",""),"Feed");
        vAdapter.AddFragment(new OpsFragment(),"Operations");
        vAdapter.AddFragment(new ProfileFragment(),"Profile");
        viewPager.setAdapter(vAdapter);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.getTabAt(0).setIcon(R.drawable.ic_rss_feed_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_work_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_account_box_black_24dp);

        //Remove Shadow from ActionBar
        android.support.v7.app.ActionBar action= getSupportActionBar();
        if (action != null) {
            action.setElevation(0);
        }
    }

   /* public void checkUser(){
        Boolean Check = Boolean.valueOf(UtilsSharedPreferences.readSharedSetting(MenuActivity.this, "PassingThrough", "true"));

        Intent introIntent = new Intent(MenuActivity.this, LoginActivity.class);
        introIntent.putExtra("PassingThrough", Check);

        if (Check) {
            startActivity(introIntent);
        }
    }*/

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void load(String title, String link) {
        if(findViewById(R.id.articlefragment)!=null)
        {
            ArticleFragment fragment= ArticleFragment.newInstance(getIntent().getStringExtra("Link"),"");
            getSupportFragmentManager().beginTransaction().replace(R.id.articlefragment,fragment).addToBackStack(null).commit();

        }
        else
        {
            Intent intent=new Intent(this,ArticleActivity.class);
            intent.putExtra("Title",title);
            intent.putExtra("Link",link);
            startActivity(intent);
        }
    }
}
