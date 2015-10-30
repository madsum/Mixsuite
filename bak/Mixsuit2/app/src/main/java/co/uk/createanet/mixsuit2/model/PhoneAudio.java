package co.uk.createanet.mixsuit2.model;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import co.uk.createanet.mixsuit2.R;

/**
 * Created by masum on 21/10/15.
 */
public class PhoneAudio {

    public String audio_title;
    public String audio_des;
    public Bitmap audio_art;
    public int audio_aret_resId;
    public String audio_fullpath;
    public String audio_album;
    public String audio_artist_name;

    public int getBitmap_id() {
        return audio_aret_resId;
    }

    public void setBitmap_id(int bitmap_id) {
        this.audio_aret_resId = bitmap_id;
    }

    public Bitmap getBitmap() {
        return audio_art;
    }

    public void setBitmap(Bitmap bitmap) {
        this.audio_art = bitmap;
    }


    public PhoneAudio(String audio_title, String audio_des, int bitmap_id) {
        this.audio_title = audio_title;
        this.audio_des = audio_des;
        this.audio_aret_resId = bitmap_id;
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

    public static class AudioViewHolder {
        public ImageView audio_art;
        public TextView audio_Title;
        public TextView audio_Desc;
        public ImageView audio_selected;
    }

}