package co.uk.createanet.mixsuit2.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import co.uk.createanet.mixsuit2.R;
import co.uk.createanet.mixsuit2.SearchVideoActivity;
import co.uk.createanet.mixsuit2.model.Country;


public class GrideViewWithBaseAdapter extends AppCompatActivity implements AdapterView.OnItemClickListener {

    GridView gridView;
   public static ArrayList<Country> list = new ArrayList<Country>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gride_view_with_base_adapter);

        if(list.size() == 0){
            int[] countryFlag = {R.drawable.v5, R.drawable.v5, R.drawable.v5, R.drawable.v5};
            for (int i = 0; i < countryFlag.length; i++) {
                list.add(new Country(countryFlag[i]));
            }
        }



        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new CountryBaseAdapter(this, list));
        gridView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gride_view_with_base_adapter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // first get ViewHolder from root view
        ViewHolder holder = (ViewHolder) view.getTag();
        // second get Country as we set before.
        //  Country country = (Country) holder.countryName.getTag();
        /*Intent intent = new Intent(this, MyDialog.class);
        intent.putExtra("countryName", country.countryName);
        intent.putExtra("countryFlagId", country.countryFlag);
        startActivity(intent);*/
    }

    public void addVideo(View view) {
        startActivity(new Intent(view.getContext(), SearchVideoActivity.class));
    }

    public void tesYou(View view) {
      //  startActivity(new Intent(this, VideoAudioActivity.class));
    }

    public void searchVideo(View view) {
        startActivity(new Intent(view.getContext(), SearchVideoActivity.class));
    }

    public void shareVideo(View view) {
        Intent textShareIntent = new Intent(Intent.ACTION_SEND);
        textShareIntent.putExtra(Intent.EXTRA_TEXT, "test text");
        textShareIntent.setType("text/plain");

        //startActivity(textShareIntent);
        // ^^ this auto-picks the defined default program for a content type, but since we want users to
        //    have options, we instead use the OS to create a chooser for users to pick from

        startActivity(Intent.createChooser(textShareIntent, "Share video with..."));
    }
}

/*class Country {
    // String countryName;;
    int countryFlag;

    public Country(int countryFlag) {
        this.countryFlag = countryFlag;
        // this.countryName = countryName;
    }
}*/


class ViewHolder {

    ImageView countryFlag;
    //  TextView countryName;

    public ViewHolder(View view) {
        // now using root view we can find child views
        //countryName = (TextView) view.findViewById(R.id.tvCountryName);
        countryFlag = (ImageView) view.findViewById(R.id.countryFlag);
    }
}

class CountryBaseAdapter extends BaseAdapter {

    ArrayList<Country> list;
    Context context;

    public CountryBaseAdapter(Context context, ArrayList<Country> list) {
        this.context = context;
        this.list =list;
        /*list = new ArrayList<Country>();
        //String[] countryName = context.getResources().getStringArray(R.array.country);
        int[] countryFlag = {R.drawable.v5, R.drawable.v5, R.drawable.v5, R.drawable.v5};
        for (int i = 0; i < countryFlag.length; i++) {
            list.add(new Country(countryFlag[i]));
        }*/

    }

    public void setListItem(Country country){
        list.add(country);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // for simple static list this method is meaningless. But for database it means the row id.
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*
        1. find the root view (int this case )
        2. find the clid vies inside root
        3. set the values
         */

        View row = convertView;
        ViewHolder holder = null;
        /*
        Here is resource optimization tric. For the very fast time convertView is null.
        Once it is created it has ref to previously created view.
        */
        if (row == null) {
            // If we want to find a existing view Just use findViewById. If want to create a new view we must use LayoutInflater
            // In this case we have to create new view each time for new row.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // row contains a ref to root view LinearLayout named activity_list_single_row.xml
            row = inflater.inflate(R.layout.gride_single_item, parent, false);
            // inflate and findViewById is an expensive call. So we will do once and save for future by view.setTag().
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else {

            holder = (ViewHolder) row.getTag();

        }

        Country temp = list.get(position);
        // holder.countryName.setText(temp.countryName);
        // saving temp to retive later in onItemClick to pass to other activity
        //holder.countryName.setTag(temp);
        holder.countryFlag.setImageResource(temp.countryFlag);

        return row;
    }
}

