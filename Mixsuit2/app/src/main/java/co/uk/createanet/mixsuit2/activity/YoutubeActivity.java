package co.uk.createanet.mixsuit2.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import co.uk.createanet.mixsuit2.R;
import co.uk.createanet.mixsuit2.adapter.YoutubeVideoListAdapter;
import co.uk.createanet.mixsuit2.model.YouTubeVideo;

public class YoutubeActivity extends AppCompatActivity implements
        OnItemClickListener {

    ListView listView;

    public static List<YouTubeVideo> getYouTubeVideoList() {
        return youTubeVideoList;
    }

    public static void addYouTubeVideo(YouTubeVideo youTubeVideo) {
        youTubeVideoList.add(youTubeVideo);
    }

    private static List<YouTubeVideo> youTubeVideoList = new ArrayList<YouTubeVideo>();

    public List<Bitmap> getBitmapList() {
        return bitmapList;
    }

    public void setBitmapList(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
    }

    public static List<Bitmap> bitmapList = new ArrayList<Bitmap>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  downloadAllThumbnail();
        setContentView(R.layout.activity_youtube);

        listView = (ListView) findViewById(R.id.list);
        YoutubeVideoListAdapter adapter = new YoutubeVideoListAdapter(this,
                R.layout.youtube_list_item, youTubeVideoList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
      //  DemoTask demoTask = new DemoTask();
     //  new DemoTask().execute(youTubeVideoList.get(0).getThumbnailsUrl());
    }

    private void downloadAllThumbnail(){

    }
/*

    class DemoTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            Bitmap bm = null;
            try {
                URL aURL = new URL(url[0]);
                URLConnection conn = aURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bm = BitmapFactory.decodeStream(bis);
                bitmapList.add(bm);
                bis.close();
                is.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return " ";
            ///return bm;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String v) {
            super.onPostExecute(v);

            for(YouTubeVideo item : youTubeVideoList){
                // System.out.println(item);
                DemoTask demoTask = new DemoTask();
                demoTask.execute(item.getThumbnailsUrl());
            }
            youTubeVideoList.remove(0);

        }
    }
*/

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + (position + 1) + ": " + youTubeVideoList.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}
