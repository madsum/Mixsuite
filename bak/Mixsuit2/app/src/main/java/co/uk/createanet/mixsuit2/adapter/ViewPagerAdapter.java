package co.uk.createanet.mixsuit2.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import co.uk.createanet.mixsuit2.Fragment.VimeoFragment;
import co.uk.createanet.mixsuit2.Fragment.VineFragment;
import co.uk.createanet.mixsuit2.Fragment.YouTubeFragment;
import co.uk.createanet.mixsuit2.R;

/**
 * Created by masum on 23/10/15.
 */

import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    Context context;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(Context context, FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);
        this.context = context;
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            YouTubeFragment youTubeFragment = new YouTubeFragment();
            return  youTubeFragment;
        }
        if(position == 1){
            VimeoFragment vimeoFragment = new VimeoFragment();
            return  vimeoFragment;
        }
        if(position == 2){
            VineFragment vineFragment = new VineFragment();
            return vineFragment;
        }

        return null;
    }

    // This method return the titles for the Tabs in the Tab Strip

    /*
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }
*/
    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    Drawable myDrawable;
    String title;

    int tabIcons[] = {R.drawable.logo_youtube,
            R.drawable.logo_vimeo,
            R.drawable.logo_vine
    };

    // @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                myDrawable = context.getResources().getDrawable(R.drawable.logo_youtube);
                title = "youtube"; //getResources().getString(R.string.title_section1);
                break;
            case 1:
                myDrawable = context.getResources().getDrawable(R.drawable.logo_vimeo);
                title = "Vimeo"; //getResources().getString(R.string.title_section2);
                break;
            case 2:
                myDrawable = context.getResources().getDrawable(R.drawable.logo_vine);
                title = "Vine"; //getResources().getString(R.string.title_section3);
                break;
            /*case 3:
                myDrawable = getResources().getDrawable(R.drawable.img_section4);
                title = getResources().getString(R.string.title_section4);
                break;
            case 4:
                myDrawable = getResources().getDrawable(R.drawable.img_section5);
                title = getResources().getString(R.string.title_section5);
                break;*/
            default:
                break;
        }
       /* SpannableStringBuilder sb = new SpannableStringBuilder("   " + title); // space added before text for convenience
        try {
            myDrawable.setBounds(3, 3, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(myDrawable, DynamicDrawableSpan.ALIGN_BASELINE);
            sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (Exception e) {
            // TODO: handle exception
        }*/

        Drawable drawable = context.getResources().getDrawable(tabIcons[position]);
        drawable.setBounds(0, 0, 56, 56);
        ImageSpan imageSpan = new ImageSpan(drawable);
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
       // return spannableString;
        return  title;
    }
}

