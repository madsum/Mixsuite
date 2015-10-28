package co.uk.createanet.mixsuit2.model;

import android.graphics.Bitmap;

/**
 * Created by masum on 27/10/2015.
 */
public class VideoAudioSelection {

    public static int[] audio_id;
    public static String thumbnailsUrl;
    public static String videoId;
    public static String videoTitle;
    public static Bitmap bitmap;

    public int[] getAudio_id() {
        return audio_id;
    }

    public void setAudio_id(int[] audio_id) {
        this.audio_id = audio_id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
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
