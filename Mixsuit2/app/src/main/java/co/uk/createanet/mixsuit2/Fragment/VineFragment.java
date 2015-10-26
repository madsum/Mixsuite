package co.uk.createanet.mixsuit2.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import co.uk.createanet.mixsuit2.R;
import co.uk.createanet.mixsuit2.adapter.YoutubeVideoListAdapter;
import co.uk.createanet.mixsuit2.model.YouTubeVideo;

/**
 * Created by masum on 23/10/15.
 */
public class VineFragment extends Fragment implements
        AdapterView.OnItemClickListener {

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
        View view = inflater.inflate(R.layout.activity_vimeo_fragment, container, false);

        listView = (ListView) view.findViewById(R.id.list_vimeo);
        YoutubeVideoListAdapter adapter = new YoutubeVideoListAdapter(getActivity(),
                R.layout.youtube_list_item, youTubeVideoList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
