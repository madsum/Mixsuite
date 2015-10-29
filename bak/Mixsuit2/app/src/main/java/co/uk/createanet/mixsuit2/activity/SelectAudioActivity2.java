package co.uk.createanet.mixsuit2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import co.uk.createanet.mixsuit2.R;
import co.uk.createanet.mixsuit2.adapter.MultiSelectionAdapter;
import co.uk.createanet.mixsuit2.model.AudioViewHolder;
import co.uk.createanet.mixsuit2.model.PhoneAudio;

public class SelectAudioActivity2 extends AppCompatActivity {

    ListView mListView;
    ArrayList<PhoneAudio> mProducts;
    MultiSelectionAdapter<PhoneAudio> mAdapter;
    String[] audio_title;
    String[] audio_des;
    int[] audio_bitmap_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_audio2);

        mListView = (ListView) findViewById(R.id.audio_list);
        initDat();
    }

    private void initDat(){

        mProducts = new ArrayList<PhoneAudio>();
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
        //  requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, MY_REQUEST_CODE);
        mAdapter = new MultiSelectionAdapter<PhoneAudio>(this, mProducts);
        mListView.setAdapter(mAdapter);
    }

    public void selectAudio(View view) {

        if(mAdapter != null) {


            ArrayList<PhoneAudio> mArrayProducts = mAdapter.getCheckedItems();


            Log.d(SelectAudioActivity2.class.getSimpleName(), "Selected Items: " + mArrayProducts.toString());


            Toast.makeText(getApplicationContext(), "Selected Items: " + mArrayProducts.toString(), Toast.LENGTH_LONG).show();


        }

        startActivity(new Intent(getBaseContext(), VideoAudioActivity.class));
    }
}
