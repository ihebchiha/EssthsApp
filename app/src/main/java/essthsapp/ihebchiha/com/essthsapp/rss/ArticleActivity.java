package essthsapp.ihebchiha.com.essthsapp.rss;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import essthsapp.ihebchiha.com.essthsapp.R;

public class ArticleActivity extends AppCompatActivity implements ArticleFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        setTitle(getIntent().getStringExtra("Title"));
        ArticleFragment fragment= ArticleFragment.newInstance(getIntent().getStringExtra("Link"),"");
        getSupportFragmentManager().beginTransaction().add(R.id.article,fragment).commit();
        Log.e("loaded","loaded");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

