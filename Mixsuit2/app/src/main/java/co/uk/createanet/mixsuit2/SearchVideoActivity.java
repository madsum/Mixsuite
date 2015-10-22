package co.uk.createanet.mixsuit2;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import co.uk.createanet.mixsuit2.activity.YoutubeActivity;
import co.uk.createanet.mixsuit2.test.MyIntentService;


public class SearchVideoActivity extends AppCompatActivity {

    public final static String ACTION = "co.uk.createanet.mixsuit2.SearchVideoActivity.BroadcastReceiver";


    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            progressBar.dismiss();
            startActivity(new Intent(getBaseContext(), YoutubeActivity.class));
        }
    };

    private String keyword = null;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_video);

        IntentFilter inf = new IntentFilter();
        inf.addAction(SearchVideoActivity.ACTION);
        registerReceiver(br, inf);
    }

    void searchYoutubeVideo(String keyword) {
        Intent intent = new Intent(this, MyIntentService.class);
        Bundle bundle = new Bundle();
        bundle.putString("keyword", keyword);
        intent.putExtras(bundle);
        startService(intent);
        Log.i(MainActivity.TAG, "done");
    }

    public void onSearchClick(View view) {
        EditText et = (EditText) findViewById(R.id.searchEt);
        keyword = et.getText().toString();
        searchYoutubeVideo(keyword);
        displayProgressbar();
    }

    private void displayProgressbar() {
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Searching video ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
        progressBar.setCancelable(false);
        progressBar.setCanceledOnTouchOutside(false);
    }
}


