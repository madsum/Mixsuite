package co.uk.createanet.mixsuit2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import co.uk.createanet.mixsuit2.R;
import co.uk.createanet.mixsuit2.adapter.AudioListAdapter;
import co.uk.createanet.mixsuit2.model.PhoneAudio;

public class SelectAudioActivity extends AppCompatActivity {
    ListView list;
    AudioListAdapter listviewadapter;
    List<PhoneAudio> phoneAudioList = new ArrayList<PhoneAudio>();
    String[] audio_title;
    String[] audio_des;
    int[] audio_bitmap_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.activity_select_audio);

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
            phoneAudioList.add(phoneAudio);
        }


        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.audio_list);

        // Pass results to ListViewAdapter Class
        listviewadapter = new AudioListAdapter(this, R.layout.audio_list_item,
                phoneAudioList);

        // Binds the Adapter to the ListView
        list.setAdapter(listviewadapter);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        // Capture ListView item click
        list.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode,
                                                  int position, long id, boolean checked) {
                // Capture total checked items
                final int checkedCount = list.getCheckedItemCount();
                // Set the CAB title according to total checked items
                mode.setTitle(checkedCount + " Selected");
                // Calls toggleSelection method from ListViewAdapter Class
                listviewadapter.toggleSelection(position);
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.audio_select:
                        // Calls getSelectedIds method from ListViewAdapter Class
                       /* SparseBooleanArray selected = listviewadapter
                                .getSelectedIds();
                        // Captures all selected ids with a loop
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                PhoneAudio selecteditem = listviewadapter
                                        .getItem(selected.keyAt(i));
                                // Remove selected items following the ids
                                listviewadapter.remove(selecteditem);
                            }
                        }
                        // Close CAB
                        mode.finish();

 */
                        startActivity(new Intent(getBaseContext(), VideoAudioActivity.class));
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.audio_selection_menu, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // TODO Auto-generated method stub
                listviewadapter.removeSelection();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }
}
