package co.uk.createanet.mixsuit2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import co.uk.createanet.mixsuit2.R;
import co.uk.createanet.mixsuit2.model.Country;
import co.uk.createanet.mixsuit2.model.VideoAudioSelection;

public class VideoAudioActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener, YouTubeThumbnailView.OnInitializedListener {


    public static final String API_KEY = "AIzaSyBVkZ5JOLaEaSjxykSy6aP18cMubn8UlIU";
   // public static final String VIDEO_ID = "o7VVHhK9zf0";

    private YouTubePlayer youTubePlayer;
    private YouTubePlayerView youTubePlayerView;
    private YouTubeThumbnailView youTubeThumbnailView;
    private YouTubeThumbnailLoader youTubeThumbnailLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_audio);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubeplayerview);
       // youTubePlayerView.setVisibility(View.GONE);

        try {
            youTubePlayerView.initialize(API_KEY, this);
        }catch (Exception e){
            String s = e.getMessage();
            Log.i("tag", s);
        }

     /*   youTubeThumbnailView = (YouTubeThumbnailView) findViewById(R.id.youtubethumbnailview);
        youTubeThumbnailView.initialize(API_KEY, this);
        youTubeThumbnailView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (youTubePlayer != null) {
                    youTubePlayerView.setVisibility(View.VISIBLE);
                    youTubePlayer.play();
                }
            }
        });*/
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult result) {
        Toast.makeText(getApplicationContext(),
                "YouTubePlayer.onInitializationFailure()",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {

        youTubePlayer = player;

        Toast.makeText(getApplicationContext(),
                "YouTubePlayer.onInitializationSuccess()",
                Toast.LENGTH_LONG).show();

        if (!wasRestored) {
           // player.cueVideo(VIDEO_ID);
            player.cueVideo(VideoAudioSelection.videoId);

        }
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView thumbnailView,
                                        YouTubeInitializationResult error) {

        Toast.makeText(getApplicationContext(),
                "YouTubeThumbnailView.onInitializationFailure()",
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onInitializationSuccess(YouTubeThumbnailView thumbnailView,
                                        YouTubeThumbnailLoader thumbnailLoader) {

        Toast.makeText(getApplicationContext(),
                "YouTubeThumbnailView.onInitializationSuccess()",
                Toast.LENGTH_LONG).show();

        youTubeThumbnailLoader = thumbnailLoader;
        thumbnailLoader.setOnThumbnailLoadedListener(new ThumbnailLoadedListener());

        youTubeThumbnailLoader.setVideo(VideoAudioSelection.videoId);

    }

    public void playVideo(View view) {
        if (youTubePlayer != null) {
            youTubePlayer.play();
        }

    }

    public void saveVideo2(View view) {
        GrideViewWithBaseAdapter.list.add(new Country(1, VideoAudioSelection.bitmap));
        startActivity(new Intent(this, GrideViewWithBaseAdapter.class));
    }

    public void onCancel2(View view) {
        finish();
    }

  /*  public void saveVideo(View view) {

       // GrideViewWithBaseAdapter.list.add(new Country(R.drawable.youvido_thumb));
        GrideViewWithBaseAdapter.list.add(new Country(1, VideoAudioSelection.bitmap));
        startActivity(new Intent(this, GrideViewWithBaseAdapter.class));
    }

    public void onCancel(View view) {

    }*/

    private final class ThumbnailLoadedListener implements
            YouTubeThumbnailLoader.OnThumbnailLoadedListener {

        @Override
        public void onThumbnailError(YouTubeThumbnailView arg0, YouTubeThumbnailLoader.ErrorReason arg1) {
            Toast.makeText(getApplicationContext(),
                    "ThumbnailLoadedListener.onThumbnailError()",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onThumbnailLoaded(YouTubeThumbnailView arg0, String arg1) {
            Toast.makeText(getApplicationContext(),
                    "ThumbnailLoadedListener.onThumbnailLoaded()",
                    Toast.LENGTH_LONG).show();

        }

    }

}

