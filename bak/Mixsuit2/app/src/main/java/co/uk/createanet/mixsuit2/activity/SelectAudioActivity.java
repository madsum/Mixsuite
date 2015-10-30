package co.uk.createanet.mixsuit2.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import co.uk.createanet.mixsuit2.R;
import co.uk.createanet.mixsuit2.adapter.MultiSelectionAdapter;
import co.uk.createanet.mixsuit2.model.PhoneAudio;

public class SelectAudioActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ListView mListView;
    ArrayList<PhoneAudio> mAudioList = new ArrayList<PhoneAudio>();
    MultiSelectionAdapter<PhoneAudio> mAdapter;
    public View mLayout;
    AllMusicFiles allMusicFiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_audio);

        mListView = (ListView) findViewById(R.id.audio_list);
        mLayout = findViewById(R.id.select_audio_layout);
        allMusicFiles = new AllMusicFiles();
        allMusicFiles.listMusicFiles();
        initDat();
    }

    private void initDat(){
/*

        mAudioList = new ArrayList<PhoneAudio>();
        // Generate sample data into string arrays
        audio_title = new String[] { "title 1", "title 2", "title 3", "title 4", "title 5", "title 6", " title 7", "title 8", "title 9", "title 10" };
        audio_des = new String[] { "descp 1", "descp 2", "descp 3",
                "descp 4", "descp 5", "descp 6", "descp 7", "descp 8",
                "descp 9", "descp 10" };

        audio_bitmap_id = new int[] { R.drawable.a1, R.drawable.a2,
                R.drawable.a3, R.drawable.a4,
                R.drawable.a5, R.drawable.a6, R.drawable.a7,
                R.drawable.a8, R.drawable.a9, R.drawable.a10 };

        for (int i = 0; i < audio_title.length; i++) {
            PhoneAudio phoneAudio = new PhoneAudio(audio_title[i],
                    audio_des[i], audio_bitmap_id[i]);
            mProducts.add(phoneAudio);
        }
*/


        //  requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, MY_REQUEST_CODE);
        mAdapter = new MultiSelectionAdapter<PhoneAudio>(this, mAudioList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
        mListView.setLongClickable(true);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }
    List<PhoneAudio> items  = new ArrayList<PhoneAudio>();
    String[] audio_title;
    String[] audio_des;
    int[] audio_bitmap_id;

    void fillData(){

        audio_title = new String[] { "title 1", "title 2", "title 3", "title 4", "title 5", "title 6", " title 7", "title 8", "title 9", "title 10" };

        audio_des = new String[] { "descp 1", "descp 2", "descp 3",
                "descp 4", "descp 5", "descp 6", "descp 7", "descp 8",
                "descp 9", "descp 10" };


        audio_bitmap_id = new int[] { R.drawable.a1, R.drawable.a2,
                R.drawable.a3, R.drawable.a4,
                R.drawable.a5, R.drawable.a6, R.drawable.a7,
                R.drawable.a8, R.drawable.a9, R.drawable.a10 };

        for (int i = 0; i < audio_title.length; i++) {
            PhoneAudio phoneAudio = new PhoneAudio(audio_title[i],
                    audio_des[i], audio_bitmap_id[i]);
            items.add(phoneAudio);
        }
    }

    public void selectAudio(View view) {

        if(mAdapter != null) {


            ArrayList<PhoneAudio> mArrayProducts = mAdapter.getCheckedItems();


            Log.d(SelectAudioActivity.class.getSimpleName(), "Selected Items: " + mArrayProducts.toString());


            Toast.makeText(getApplicationContext(), "Selected Items: " + mArrayProducts.toString(), Toast.LENGTH_LONG).show();


        }

        startActivity(new Intent(getBaseContext(), VideoAudioActivity.class));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("","");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("","");
        return true;
    }

    private class AllMusicFiles extends AppCompatActivity{

        private int STORAGE_PERMISSION = 0;

        public void listMusicFiles() {

            // Check if the READ_EXTERNAL_STORAGE permission is already available.
            if (ActivityCompat.checkSelfPermission(SelectAudioActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                // READ_EXTERNAL_STORAGE permission has not been granted.
                requestMusicPermission();
            } else {

                // Music permissions is already available, now list the files.
                getAllSongsFromSDCARD();
            }
        }

        /**
         * Requests the Storage permission.
         * If the permission has been denied previously, a SnackBar will prompt the user to grant the
         * permission, otherwise it is requested directly.
         */
        private void requestMusicPermission() {
            // BEGIN_INCLUDE(camera_permission_request)
            if (ActivityCompat.shouldShowRequestPermissionRationale(SelectAudioActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Provide an additional rationale to the user if the permission was not granted
                // and the user would benefit from additional context for the use of the permission.
                // For example if the user has previously denied the permission.
                Snackbar.make(mLayout, R.string.permission_music_rationale,
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityCompat.requestPermissions(SelectAudioActivity.this,
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                        STORAGE_PERMISSION);
                            }
                        })
                        .show();
            } else {
                // Music permission has not been granted yet. Request it directly.
                ActivityCompat.requestPermissions(SelectAudioActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION);
            }
        }

        /**
         * Callback received when a permissions request has been completed.
         */
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                               @NonNull int[] grantResults) {
            if (requestCode == STORAGE_PERMISSION) {
                Log.i("", "Received response for contact permissions request.");

                // We have requested multiple permissions for contacts, so all of them need to be
                // checked.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //if (PermissionUtil.verifyPermissions(grantResults)) {
                    // All required permissions have been granted, display contacts fragment.
                    Snackbar.make(mLayout, R.string.permission_music_granted,
                            Snackbar.LENGTH_SHORT)
                            .show();

                    getAllSongsFromSDCARD();
                } else {
                    Snackbar.make(mLayout, R.string.permissions_not_granted,
                            Snackbar.LENGTH_SHORT)
                            .show();
                }
            }else {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }

        public void getAllSongsFromSDCARD() {
            String[] STAR = {"*"};
            Uri allsongsuri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            //  Uri allsongsuri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
            String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
            Cursor cursor = null;

            try {
                this.grantUriPermission("co.uk.createanet.mymetaextractor", allsongsuri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                cursor = managedQuery(allsongsuri, STAR, selection, null, null);
            } catch (Exception e) {
                String msg = e.getMessage();
                Log.i("tag", "wait");

            }

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        PhoneAudio phoneAudio = new PhoneAudio();
                        phoneAudio.audio_title = cursor
                                .getString(cursor
                                        .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                        int song_id = cursor.getInt(cursor
                                .getColumnIndex(MediaStore.Audio.Media._ID));

                        phoneAudio.audio_fullpath = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.DATA));

                        phoneAudio.audio_album = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.ALBUM));

                        phoneAudio.audio_artist_name = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.ARTIST));

                        int artist_id = cursor.getInt(cursor
                                .getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));

                        int album_id = cursor.getInt(cursor
                                .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));

                        phoneAudio.audio_title= cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.TITLE));

                        try {
                            Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                            //  Uri sArtworkUri = Uri.parse("content://media/internal/audio/albumart");
                            Uri uri = ContentUris.withAppendedId(sArtworkUri, album_id);
                            ContentResolver res = this.getContentResolver();
                            InputStream in = res.openInputStream(uri);
                            phoneAudio.audio_art = BitmapFactory.decodeStream(in);
                           // phoneAudio.audio_art.setImageBitmap(artwork);
                            Log.i("tag", "wait");
                        } catch (Exception e) {
                            phoneAudio.audio_art = BitmapFactory.decodeResource(getResources(), R.drawable.empty_music_art;
                            String msg = e.getMessage();
                            Log.i("tag", msg);
                        }
                        mAudioList.add(phoneAudio);
                    } while (cursor.moveToNext());

                }
                cursor.close();
            }
        }


    }

}

