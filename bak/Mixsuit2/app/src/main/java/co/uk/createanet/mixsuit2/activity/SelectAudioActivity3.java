package co.uk.createanet.mixsuit2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import co.uk.createanet.mixsuit2.R;
import co.uk.createanet.mixsuit2.adapter.AudioListAdapter3;
import co.uk.createanet.mixsuit2.adapter.YoutubeVideoListAdapter;
import co.uk.createanet.mixsuit2.model.PhoneAudio;
import co.uk.createanet.mixsuit2.model.YouTubeVideo;

public class SelectAudioActivity3 extends AppCompatActivity implements
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ListView listView;

    public static List<PhoneAudio> getYouTubeVideoList() {
        return audioList;
    }

    public static void addYouTubeVideo(PhoneAudio phoneAudio) {
        audioList.add(phoneAudio);
    }

    private static List<PhoneAudio> audioList = new ArrayList<PhoneAudio>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_audio3);
        fillData();

        listView = (ListView) findViewById(R.id.audio_list);
        AudioListAdapter3 adapter = new AudioListAdapter3(this, R.layout.audio_list_item, audioList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        listView.setLongClickable(true);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PhoneAudio.AudioViewHolder holder = (PhoneAudio.AudioViewHolder) view.getTag();
        if(holder.audio_selected.getVisibility() == View.GONE){
            holder.audio_selected.setVisibility(View.VISIBLE);
        }else{
            holder.audio_selected.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        PhoneAudio.AudioViewHolder holder = (PhoneAudio.AudioViewHolder) view.getTag();
        if(holder.audio_selected.getVisibility() == View.GONE){
            holder.audio_selected.setVisibility(View.VISIBLE);
        }else{
            holder.audio_selected.setVisibility(View.GONE);
        }
        return true;
    }


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
            audioList.add(phoneAudio);
        }
    }
}
