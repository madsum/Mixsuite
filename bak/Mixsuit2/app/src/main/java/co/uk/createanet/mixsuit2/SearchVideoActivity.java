package co.uk.createanet.mixsuit2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import co.uk.createanet.mixsuit2.activity.SelectViedoActivity;
import co.uk.createanet.mixsuit2.test.MyIntentService;


public class SearchVideoActivity extends Activity {

    public final static String ACTION = "co.uk.createanet.mixsuit2.SearchVideoActivity.BroadcastReceiver";
    private String keyword = null;
    private ProgressDialog progressBar;

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            progressBar.dismiss();
            startActivity(new Intent(getBaseContext(), SelectViedoActivity.class));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_video);
        registerReceiver(mMessageReceiver, new IntentFilter(ACTION));

        RelativeLayout relative = (RelativeLayout) findViewById(R.id.search_layout);
        Drawable drawable = getResources().getDrawable(R.drawable.background);
        relative.setBackground(drawable);
      final  EditText et = (EditText) findViewById(R.id.searchEt);
        et.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search_black_24dp, 0, 0, 0);

    }

    @Override
    public void onResume() {
        super.onResume();
        // Register mMessageReceiver to receive messages.
        registerReceiver(mMessageReceiver, new IntentFilter(ACTION));
    }

    @Override
    protected void onPause() {
        // Unregister since the activity is not visible
        unregisterReceiver(mMessageReceiver);
        super.onPause();
    }

    void searchYoutubeVideo(String keyword) {
        Intent intent = new Intent(this, MyIntentService.class);
        Bundle bundle = new Bundle();
        bundle.putString("keyword", keyword);
        intent.putExtras(bundle);
        startService(intent);
   //     Log.i(MainActivity.TAG, "done");
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

    public void onCancel(View view) {
        finish();
    }
}


