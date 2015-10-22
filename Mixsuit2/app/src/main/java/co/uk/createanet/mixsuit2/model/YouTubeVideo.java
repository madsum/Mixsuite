package co.uk.createanet.mixsuit2.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masum on 21/10/15.
 */
public class YouTubeVideo {

    private String thumbnailsUrl;
    private String videoId;
    private String videoTitle;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    private Bitmap bitmap;

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    private String viewCount;

    public YouTubeVideo(String thumbnailsUrl, String videoId, String videoTitle) {
        this.thumbnailsUrl = thumbnailsUrl;
        this.videoId = videoId;
        this.videoTitle = videoTitle;
    }

    public YouTubeVideo(){

    }

    public String getThumbnailsUrl() {
        return thumbnailsUrl;
    }

    public void setThumbnailsUrl(String thumbnailsUrl) {
        this.thumbnailsUrl = thumbnailsUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }
}