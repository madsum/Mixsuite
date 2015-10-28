package co.uk.createanet.mixsuit2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import co.uk.createanet.mixsuit2.adapter.RecyclerAdapter;
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class MainActivity extends AppCompatActivity {

    private static String[] data = new String[] {
            "Java", "Go", "Swift", "Objective-c", "Ruby", "PHP", "Bash", "ksh", "C", "Groovy", "Kotlin", "Add new video"
    };

    RecyclerAdapter adapter;

    public static final String TAG = "mixsuite";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_tool_bar);

        setSupportActionBar(toolbar);
        CharSequence ch = toolbar.getTitle();
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("test");

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_list);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        recyclerView.setItemAnimator(new SlideInLeftAnimator());

        adapter = new RecyclerAdapter(this, new ArrayList<>(Arrays.asList(data)));
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new FadeInUpAnimator(new OvershootInterpolator(1f)));
        recyclerView.getItemAnimator().setAddDuration(500);
        recyclerView.getItemAnimator().setRemoveDuration(500);

    }



    public void onYoutubeTest(View view) {
        startActivity(new Intent(view.getContext(), SearchVideoActivity.class));
    }

    public void onAddImg(View view) {
        adapter.add("new item added", 1);
    }

    public void onDelImg(View view) {
        adapter.remove(1);
    }
}
