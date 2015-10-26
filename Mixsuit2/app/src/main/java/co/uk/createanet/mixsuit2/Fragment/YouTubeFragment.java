package co.uk.createanet.mixsuit2.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.uk.createanet.mixsuit2.R;
import co.uk.createanet.mixsuit2.adapter.YoutubeVideoListAdapter;
import co.uk.createanet.mixsuit2.model.YouTubeVideo;

/**
 * Created by masum on 23/10/15.
 */
public class YouTubeFragment extends Fragment implements
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ListView listView;

    public static List<YouTubeVideo> getYouTubeVideoList() {
        return youTubeVideoList;
    }

    public static void addYouTubeVideo(YouTubeVideo youTubeVideo) {
        youTubeVideoList.add(youTubeVideo);
    }

    private static List<YouTubeVideo> youTubeVideoList = new ArrayList<YouTubeVideo>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_youtube_fragment, container, false);

        listView = (ListView) view.findViewById(R.id.list_youtube);
        YoutubeVideoListAdapter adapter = new YoutubeVideoListAdapter(getActivity(),
                R.layout.youtube_list_item, youTubeVideoList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        listView.setLongClickable(true);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setSelector(android.R.color.holo_blue_bright);

       /* listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub

                Log.v("long clicked", "pos: " + pos);

                return true;
            }
        });*/

        return view;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast toast = Toast.makeText(getActivity(),
                "Item " + (position + 1) + ": " + youTubeVideoList.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
       // view.setSelected(true);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        view.setSelected(true);
        Log.v("long clicked", "pos: " + position);
        Toast toast = Toast.makeText(getActivity(),
                "####LONG press#### Item " + (position + 1) + ": " + youTubeVideoList.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        return true;
    }
}