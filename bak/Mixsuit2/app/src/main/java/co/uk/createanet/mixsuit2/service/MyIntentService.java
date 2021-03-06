package co.uk.createanet.mixsuit2.service;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.Video;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

import co.uk.createanet.mixsuit2.Fragment.YouTubeFragment;
import co.uk.createanet.mixsuit2.SearchVideoActivity;
import co.uk.createanet.mixsuit2.model.YouTubeVideo;

/**
 * Created by masum on 26/05/15.
 */
public class MyIntentService extends android.app.IntentService {


    private static final String API_KEY = "AIzaSyDxXYr8VLPTIcjm38L69pzSNATUCqSDs0Q";

    /**
     * Global instance of the HTTP transport.
     */
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /**
     * Global instance of the JSON factory.
     */
    private final JsonFactory JSON_FACTORY = new JacksonFactory();

    /**
     * Global instance of the max number of videos we want returned (50 = upper limit per page).
     */
    private final long NUMBER_OF_VIDEOS_RETURNED = 50;

    /**
     * Global instance of Youtube object to make all API requests.
     */
    private YouTube youtube;


    public MyIntentService() {
        super("co.uk.createanet.mixsuit2.service.MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("youtube-cmdline-search-sample").build();
        String keyword = intent.getStringExtra("keyword");
        searchResults(youtube, keyword);
       // Log.i(MainActivity.TAG, "done");
    }

    private void searchResults(YouTube youtube, String queryTerm) {
        try {
            YouTube.Search.List search = youtube.search().list("id,snippet");
            search.setKey(API_KEY);
            search.setQ(queryTerm);
            search.setType("video");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            // This method reduces the info returned to only the fields we need and makes calls more efficient.
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();

            if (searchResultList != null) {
                setVideo(searchResultList.iterator(), queryTerm);
            }

        } catch (GoogleJsonResponseException e) {
         //   Log.e(MainActivity.TAG, e.getDetails().getCode() + " : " + e.getDetails().getMessage());
        } catch (IOException e) {
            //Log.e(MainActivity.TAG, e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();

        }
    }

    private void setVideo(Iterator<SearchResult> iteratorSearchResults, String query) {
        if (!iteratorSearchResults.hasNext()) {
          //  Log.e(MainActivity.TAG, " There aren't any results for your query.");
            return;
        }
        while (iteratorSearchResults.hasNext()) {
            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();
            // Double checks the kind is video.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = (Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");

                Video video = null;

                try {
                    YouTube.Videos.List list = youtube.videos().list("statistics");
                    list.setId(rId.getVideoId());
                    list.setKey(API_KEY);
                    video = list.execute().getItems().get(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bitmap bitmap = null;
                try {
                    URL aURL = new URL(thumbnail.getUrl());
                    URLConnection conn = aURL.openConnection();
                    conn.connect();
                    InputStream is = conn.getInputStream();

                    BufferedInputStream bis = new BufferedInputStream(is);
                    bitmap = BitmapFactory.decodeStream(bis);
                   // bitmap.
                   // bitmap = resizeBitmap(is, 130, 130);
                    bis.close();
                    is.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                YouTubeVideo youTubeVideo = new YouTubeVideo();
                youTubeVideo.setThumbnailsUrl(thumbnail.getUrl());
                youTubeVideo.setVideoId(rId.getVideoId());
                youTubeVideo.setVideoTitle(singleVideo.getSnippet().getTitle());
                youTubeVideo.setViewCount("" + video.getStatistics().getViewCount());
                youTubeVideo.setBitmap(bitmap);

                YouTubeFragment.addYouTubeVideo(youTubeVideo);
            }
        }
        Intent in = new Intent();
        in.setAction(SearchVideoActivity.ACTION);
        sendBroadcast(in);
    }


    public static Bitmap resizeBitmap(InputStream is, int targetWidth,
                                      int targetHeight  ){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;


        //BitmapFactory.de
        BitmapFactory.decodeStream(is, null, options);

        Boolean scaleByHeight = Math.abs(options.outHeight - targetHeight) >= Math.abs(options.outWidth - targetWidth);

        if(options.outHeight * options.outWidth * 2 >= 200*200*2){
            // Load, scaling to smallest power of 2 that'll get it <= desired dimensions
            double sampleSize = scaleByHeight
                    ? options.outHeight / targetHeight
                    : options.outWidth / targetWidth;
            options.inSampleSize =
                    (int)Math.pow(2d, Math.floor(
                            Math.log(sampleSize)/Math.log(2d)));
        }

        // Do the actual decoding
        options.inJustDecodeBounds = false;

       // is.close();
       // is = getHTTPConnectionInputStream(sUrl);
        Bitmap img = BitmapFactory.decodeStream(is, null, options);
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  img;
    }

    public static Bitmap resizeBitMapImage(String filePath, int targetWidth,
                                            int targetHeight) {
        Bitmap bitMapImage = null;
        // First, get the dimensions of the image
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
       // BitmapFactory.decodeStream()
        double sampleSize = 0;
        // Only scale if we need to
        // (16384 buffer for img processing)
        Boolean scaleByHeight = Math.abs(options.outHeight - targetHeight) >= Math
                .abs(options.outWidth - targetWidth);

        if (options.outHeight * options.outWidth * 2 >= 1638) {
            // Load, scaling to smallest power of 2 that'll get it <= desired
            // dimensions
            sampleSize = scaleByHeight ? options.outHeight / targetHeight
                    : options.outWidth / targetWidth;
            sampleSize = (int) Math.pow(2d,
                    Math.floor(Math.log(sampleSize) / Math.log(2d)));
        }

        // Do the actual decoding
        options.inJustDecodeBounds = false;
        options.inTempStorage = new byte[128];
        while (true) {
            try {
                options.inSampleSize = (int) sampleSize;
                bitMapImage = BitmapFactory.decodeFile(filePath, options);


                break;
            } catch (Exception ex) {
                try {
                    sampleSize = sampleSize * 2;
                } catch (Exception ex1) {

                }
            }
        }

        return bitMapImage;
    }
}

