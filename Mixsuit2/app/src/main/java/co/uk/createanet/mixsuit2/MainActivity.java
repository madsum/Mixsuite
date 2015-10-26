package co.uk.createanet.mixsuit2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import java.util.ArrayList;
import java.util.Arrays;

import jp.wasabeef.recyclerview.animators.BaseItemAnimator;
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class MainActivity extends AppCompatActivity {

    private static String[] data = new String[] {
            "Java", "Go", "Swift", "Objective-c", "Ruby", "PHP", "Bash", "ksh", "C", "Groovy", "Kotlin"
    };

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
       // toolbar.setTitle("test");
      //  getSupportActionBar().setDisplayShowTitleEnabled(true);
       // getSupportActionBar().setTitle(R.string.app_name);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        recyclerView.setItemAnimator(new SlideInLeftAnimator());

        final MainAdapter adapter = new MainAdapter(this, new ArrayList<>(Arrays.asList(data)));
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new FadeInUpAnimator(new OvershootInterpolator(1f)));
        recyclerView.getItemAnimator().setAddDuration(500);
        recyclerView.getItemAnimator().setRemoveDuration(500);
/*

        findViewById(R.id.youtube_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SearchVideoActivity.class));
            }
        });

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add("newly added item", 1);
            }
        });

        findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.remove(1);
            }
        });
*/


    }
    
    public void onYoutubeTest(View view) {
        startActivity(new Intent(view.getContext(), SearchVideoActivity.class));
    }
}
