package co.uk.createanet.mixsuit2.model;

import android.graphics.Bitmap;

/**
 * Created by masum on 27/10/2015.
 */
public class VideoAudioSelection {
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

   public static int[] audio_id;
    public static Bitmap bitmap;
 }
