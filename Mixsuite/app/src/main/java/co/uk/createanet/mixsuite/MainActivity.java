package co.uk.createanet.mixsuite;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        OnClickListener {
    //Button button;
    GridView gridView;
    ArrayAdapter<String> adapter;
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object


        setSupportActionBar(toolbar);

        findViewsById();

/*
        ArrayList<String> mylist = new ArrayList<String>();

        for(int i=0; i<mylist.size(); i++){
            mylist.add("video "+i);
        }
*/

        String[] sports = getResources().getStringArray(R.array.video);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, sports);
        gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);
        gridView.setAdapter(adapter);
       // button.setOnClickListener(this);
    }

    private void findViewsById() {
        gridView = (GridView) findViewById(R.id.gridView);
       // button = (Button) findViewById(R.id.testbutton);
    }

    public void onClick(View v) {
        SparseBooleanArray checked = gridView.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add sport if it is checked i.e.) == TRUE!
            if (checked.valueAt(i))
                selectedItems.add(adapter.getItem(position));
        }

        String[] outputStrArr = new String[selectedItems.size()];

        for (int i = 0; i < selectedItems.size(); i++) {
            outputStrArr[i] = selectedItems.get(i);
        }

        Intent intent = new Intent(getApplicationContext(),
                ResultActivity.class);
        // Create a bundle object
        Bundle b = new Bundle();
        b.putStringArray("selectedItems", outputStrArr);
        // Add the bundle to the intent.
        intent.putExtras(b);
        // start the ResultActivity
        startActivity(intent);
    }

    private  void  test(){
        SparseBooleanArray checked = gridView.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add sport if it is checked i.e.) == TRUE!
            if (checked.valueAt(i))
                selectedItems.add(adapter.getItem(position));
        }

        String[] outputStrArr = new String[selectedItems.size()];
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < selectedItems.size(); i++) {
            outputStrArr[i] = selectedItems.get(i);
            sb.append(selectedItems.get(i)+"\n");
        }

        Toast.makeText(this, sb, Toast.LENGTH_LONG).show();
/*

        Intent intent = new Intent(getApplicationContext(),
                ResultActivity.class);
        // Create a bundle object
        Bundle b = new Bundle();
        b.putStringArray("selectedItems", outputStrArr);
        // Add the bundle to the intent.
        intent.putExtras(b);
        // start the ResultActivity
        startActivity(intent);
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id == R.id.add){
            Toast.makeText(this, "add clicked: " + item.getTitle(), Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, AddVideoActivity.class));
        }

        if(id == R.id.delete){
            //Toast.makeText(this, "delete clicked: " + item.getTitle(), Toast.LENGTH_LONG).show();
            //startActivity(new Intent(this, SubActivity.class));
            test();
        }

        return super.onOptionsItemSelected(item);
    }
}