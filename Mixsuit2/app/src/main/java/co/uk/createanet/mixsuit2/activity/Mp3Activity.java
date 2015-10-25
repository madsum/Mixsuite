package co.uk.createanet.mixsuit2.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.beaglebuddy.id3.enums.PictureType;
import com.beaglebuddy.mp3.MP3;

import java.io.File;
import java.io.IOException;

import co.uk.createanet.mixsuit2.R;

//import c

public class Mp3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3);
        listFile();
    }

    void listFile(){
        String path = Environment.getExternalStorageDirectory().toString()+"/Music";
       String path2 = "/storage/extSdCard/Music/audio.mp3";
        File f2 = new File(path2);
        if(f2.exists())
        {
            Log.i("tag", f2.getName());
            test(f2.getAbsolutePath());
        }
        Log.i("tag", f2.getName());
        /*
        Log.d("Files", "Path: " + path);
        File f = new File(path);
        File file[] = f.listFiles();
        Log.d("Files", "Size: "+ file.length);
        for (int i=0; i < file.length; i++)
        {
            Log.d("Files", "FileName:" + file[i].getName());
            test(file[i].getName());
        }
        */
    }


    public void test(String path) {

        TextView tb_title = (TextView) findViewById(R.id.title);
        TextView tb_lyrics = (TextView) findViewById(R.id.lyrics);
        ImageView mp3_img = (ImageView) findViewById(R.id.mp3_pic);

        try {
            MP3 mp3 = new MP3(path); //new MP3("/Users/masum/practice/java/Mp3Tag/2.mp3");

            // if there was any invalid information (ie, ID3v2.x frames) in the .mp3 file,
            // then display the errors to the user
            if (mp3.hasErrors()) {
                mp3.displayErrors(System.out);      // display the errors that were found
                mp3.save();                         // discard the invalid information (ID3v2.x frames) and
            }                                      // save only the valid frames back to the .mp3 file

            if (mp3.getAudioDuration() == 0)       // if the length of the song hasn't been specified,
                mp3.setAudioDuration();             // then calculate it from the mpeg audio frames

            // print out all the internal information about the .mp3 file
            // System.out.println(mp3);

            // get some specific information about the song
            System.out.println("audio duration.....: " + mp3.getAudioDuration() + " s\n" +
                    "audio size.........: " + mp3.getAudioSize() + " bytes\n" +
                    "album..............: " + mp3.getAlbum() + "\n" +
                    "artist.............: " + mp3.getBand() + "\n" +
                    "contributing artist: " + mp3.getLeadPerformer() + "\n" +
                    "lyrics by..........: " + mp3.getLyricsBy() + "\n" +
                    "music by...........: " + mp3.getMusicBy() + "\n" +
                    "picture............: " + mp3.getPicture(PictureType.FRONT_COVER) + "\n" +
                    "publisher..........: " + mp3.getPublisher() + "\n" +
                    "rating.............: " + mp3.getRating() + "\n" +
                    "title..............: " + mp3.getTitle() + "\n" +
                    "track #............: " + mp3.getTrack() + "\n" +
                    "year recorded......: " + mp3.getYear() + "\n" +
                    "lyrics.............: " + mp3.getLyrics() + "\n");


            tb_title.setText(mp3.getTitle());
            tb_lyrics.setText(mp3.getLyrics());
            Bitmap bmp = BitmapFactory.decodeByteArray(mp3.getPicture(PictureType.FRONT_COVER).getImage(), 0, mp3.getPicture(PictureType.FRONT_COVER).getImage().length);
            mp3_img.setImageBitmap(bmp);
            //ImageView image = (ImageView) findViewById(R.id.imageView1);
            //Bitmap bitmap = BitmapFactory.decodeStream( mp3.getPicture(PictureType.FRONT_COVER).getImage());
            //Drawable d = new D
            //mp3.getPicture(PictureType.FRONT_COVER).getImage();


        } catch (IOException ex) {
            System.out.println("An error occurred while reading/saving the mp3 file.");
            ex.printStackTrace();
        }
    }
}
