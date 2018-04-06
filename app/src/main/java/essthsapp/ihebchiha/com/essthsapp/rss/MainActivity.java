package essthsapp.ihebchiha.com.essthsapp.rss;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import essthsapp.ihebchiha.com.essthsapp.R;


public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener,ArticleFragment.OnFragmentInteractionListener,RssAdapter.UrlLoader ,NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Science Feed");
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){return;}
        getSupportFragmentManager().beginTransaction().add(R.id.listfragment,MainFragment.newInstance("https://www.popsci.com/rss-science.xml","")).commit();
        Log.e("Loaded","loaded");

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
       if(getFragmentManager().getBackStackEntryCount()>0)
        {
            getFragmentManager().popBackStack();
        }else
        super.onBackPressed();

    }

    @Override
    public void load(String title, String link) {
    if(findViewById(R.id.articlefragment)!=null)
    {
        ArticleFragment fragment= ArticleFragment.newInstance(getIntent().getStringExtra("link"),"");
        getSupportFragmentManager().beginTransaction().replace(R.id.articlefragment,fragment).addToBackStack(null).commit();

    }
        else
    {
        Intent intent=new Intent(this,ArticleActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("link",link);
        startActivity(intent);
    }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.Science:
            {  MainFragment fragment= MainFragment.newInstance("https://www.popsci.com/rss-science.xml","");
            getSupportFragmentManager().beginTransaction().replace(R.id.listfragment,fragment).commit();}
            return true;
            case R.id.Music:
            {     MainFragment fragment_music= MainFragment.newInstance("http://itunes.apple.com/WebObjects/MZStore.woa/wpa/MRSS/newreleases/sf=143441/limit=25/rss.xml","");
                getSupportFragmentManager().beginTransaction().replace(R.id.listfragment,fragment_music).commit();}
            return true;
            case R.id.Books:
            {MainFragment fragment_books= MainFragment.newInstance("https://www.bookbrowse.com/rss/newest_reader_reviews.rss","");
                getSupportFragmentManager().beginTransaction().replace(R.id.listfragment,fragment_books).commit();}
            return true;
                default:
                {MainFragment fragment= MainFragment.newInstance("https://www.ciepopsci.com/rss-science.xml","");
                    getSupportFragmentManager().beginTransaction().replace(R.id.listfragment,fragment).commit();
                    }
        }
        return super.onOptionsItemSelected(item);
    }
}
