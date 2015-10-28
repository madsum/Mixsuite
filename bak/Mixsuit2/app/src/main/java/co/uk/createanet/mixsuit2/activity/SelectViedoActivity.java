package co.uk.createanet.mixsuit2.activity;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import co.uk.createanet.mixsuit2.R;
import co.uk.createanet.mixsuit2.SlidingTab.SlidingTabLayout;
import co.uk.createanet.mixsuit2.adapter.ViewPagerAdapter;

/**
 * Created by Edwin on 15/02/2015.
 */
public class SelectViedoActivity extends AppCompatActivity {

    // Declaring Your View and Variables

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"YouTube","Vimeo", "Vine"};
    int Numboftabs =3;
    public static boolean menu_active = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_viedo);


        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.youtube_toolbar);
        //setActionBar(toolbar);
        setSupportActionBar(toolbar);

       // getSupportActionBar().hide();

        getWindow().
                getDecorView().
                setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(this, getSupportFragmentManager(),Titles,Numboftabs);
      //  adapter =  new ViewPagerAdapter

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

    }

    public void selectVideo(View view) {
        if(menu_active){

            startActivity(new Intent(this, SelectAudioActivity.class));
        }
    }

/*

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.getItem(0).setEnabled(menu_active);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.video_selection_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_selected:
               startActivity(new Intent(this, SelectAudioActivity.class));
                break;
            default:
                break;
        }

        return true;
    }
*/

/*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      */
/*  if (id == R.id.action_settings) {
            return true;
        }*//*


        return super.onOptionsItemSelected(item);
    }
*/

    Drawable myDrawable;
    String title;

   // @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                myDrawable = getResources().getDrawable(R.drawable.logo_youtube);
                title = "youtube"; //getResources().getString(R.string.title_section1);
                break;
            case 1:
                myDrawable = getResources().getDrawable(R.drawable.logo_vimeo);
                title = "Vimeo"; //getResources().getString(R.string.title_section2);
                break;
            case 2:
                myDrawable = getResources().getDrawable(R.drawable.logo_vine);
                title = "Vine"; //getResources().getString(R.string.title_section3);
                break;

            default:
                break;
        }
        SpannableStringBuilder sb = new SpannableStringBuilder("   " + title); // space added before text for convenience
        try {
            myDrawable.setBounds(5, 5, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(myDrawable, DynamicDrawableSpan.ALIGN_BASELINE);
            sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return  title;
    }
}