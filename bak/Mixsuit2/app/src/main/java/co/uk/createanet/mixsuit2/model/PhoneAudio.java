package co.uk.createanet.mixsuit2.model;

import android.graphics.Bitmap;

/**
 * Created by masum on 21/10/15.
 */
public class PhoneAudio {

    private String audio_title;
    private String audio_des;
    private Bitmap bitmap;
    private int bitmap_id;

    public int getBitmap_id() {
        return bitmap_id;
    }

    public void setBitmap_id(int bitmap_id) {
        this.bitmap_id = bitmap_id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


    public PhoneAudio(String audio_title, String audio_des, int bitmap_id) {
        this.audio_title = audio_title;
        this.audio_des = audio_des;
        this.bitmap_id = bitmap_id;
    }

    public PhoneAudio() {

    }

    public String getAudio_title() {
        return audio_title;
    }

    public void setAudio_title(String audio_title) {
        this.audio_title = audio_title;
    }

    public String getAudio_des() {
        return audio_des;
    }

    public void setAudio_des(String audio_des) {
        this.audio_des = audio_des;
    }
}