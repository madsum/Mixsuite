package co.uk.createanet.mixsuit2.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.uk.createanet.mixsuit2.R;
import co.uk.createanet.mixsuit2.model.AudioViewHolder;
import co.uk.createanet.mixsuit2.model.PhoneAudio;

public class AudioListAdapter extends ArrayAdapter<PhoneAudio> {
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    List<PhoneAudio> PhoneAudioList;
    private SparseBooleanArray mSelectedItemsIds;

    public AudioListAdapter(Context context, int resourceId,
                            List<PhoneAudio> PhoneAudioList) {
        super(context, resourceId, PhoneAudioList);
        mSelectedItemsIds = new SparseBooleanArray();
        this.context = context;
        this.PhoneAudioList = PhoneAudioList;
        inflater = LayoutInflater.from(context);
    }
/*
    private class ViewHolder {
        TextView audio_title;
        TextView audio_des;
        ImageView audio_art;
    }*/

    public View getView(int position, View view, ViewGroup parent) {
        final AudioViewHolder holder;
        if (view == null) {
            holder = new AudioViewHolder();
            view = inflater.inflate(R.layout.audio_list_item, null);
            // Locate the TextViews in listview_item.xml
            holder.audio_title = (TextView) view.findViewById(R.id.audio_title);
            holder.audio_des = (TextView) view.findViewById(R.id.audio_desc);

            // Locate the ImageView in listview_item.xml
            holder.audio_art = (ImageView) view.findViewById(R.id.audio_art);
            holder.audio_selected = (ImageView) view.findViewById(R.id.audio_selected);
            view.setTag(holder);
        } else {
            holder = (AudioViewHolder) view.getTag();
        }
        // Capture position and set to the TextViews
        holder.audio_title.setText(PhoneAudioList.get(position).getAudio_title());
        holder.audio_des.setText(PhoneAudioList.get(position).getAudio_des());
        //holder.audio_art.setImageBitmap(PhoneAudioList.get(position).getBitmap());
        holder.audio_art.setImageResource(PhoneAudioList.get(position).getBitmap_id());
        holder.audio_selected.setImageResource(R.drawable.selected);
        return view;
    }

    @Override
    public void remove(PhoneAudio object) {
        PhoneAudioList.remove(object);
        notifyDataSetChanged();
    }

    public List<PhoneAudio> getWorldPopulation() {
        return PhoneAudioList;
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
}