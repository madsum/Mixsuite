package co.uk.createanet.mixsuit2.adapter;

/**
 * Created by masum on 22/10/15.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.uk.createanet.mixsuit2.R;
import co.uk.createanet.mixsuit2.model.PhoneAudio;



public class AudioListAdapter3 extends BaseAdapter { //ArrayAdapter<PhoneAudio> {
    Context context;
    int layout_resourceId;
    private  List<PhoneAudio> audioList = new ArrayList<PhoneAudio>();

    public AudioListAdapter3(Context context, int resourceId, List<PhoneAudio> list) {
        //super(context, resourceId, items);
        this.context = context;
        this.audioList = list;
       this.layout_resourceId = resourceId;
    } @Override
      public int getCount() {
        return audioList.size();
    }

    @Override
    public Object getItem(int position) {
        return audioList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // for simple static list this method is meaningless. But for database it means the row id.
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         /*
        1. find the root view (int this case )
        2. find the clid vies inside root
        3. set the values
         */

        View row = convertView;
        PhoneAudio.AudioViewHolder holder = null;
        /*
        Here is resource optimization tric. For the very fast time convertView is null.
        Once it is created it has ref to previously created view.
        */
        if(row == null){
            // If we want to find a existing view Just use findViewById. If want to create a new view we must use LayoutInflater
            // In this case we have to create new view each time for new row.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // row contains a ref to root view LinearLayout named activity_list_single_row.xml
            row = inflater.inflate(layout_resourceId, parent, false);
            // inflate and findViewById is an expensive call. So we will do once and save for future by view.setTag().
            holder = new PhoneAudio.AudioViewHolder(row);
            row.setTag(holder);
        }else{

            holder = (PhoneAudio.AudioViewHolder) row.getTag();

        }

        PhoneAudio temp = audioList.get(position);
        holder.audio_Title.setText(temp.getAudio_title());
        holder.audio_Desc.setText(temp.getAudio_des());
        holder.audio_art.setImageResource(temp.getBitmap_id());
        holder.audio_selected.setImageResource(R.drawable.selected);
        // saving temp to retive later in onItemClick to pass to other activity
        holder.audio_Title.setTag(temp);
        holder.audio_Desc.setTag(temp);
        holder.audio_art.setTag(temp);
        holder.audio_selected.setTag(temp);

        return row;

        /*PhoneAudio.AudioViewHolder holder = null;
        PhoneAudio phoneAudio = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(layout_resourceId, null);
            holder = new PhoneAudio.AudioViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.audio_desc);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.audio_title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.audio_art);
            holder.selectedImg = (ImageView) convertView.findViewById(R.id.audio_selected);
            convertView.setTag(holder);
        } else
            holder = (PhoneAudio.AudioViewHolder) convertView.getTag();

        holder.txtDesc.setText(phoneAudio.getAudio_des());
        holder.txtTitle.setText(phoneAudio.getAudio_title());
        holder.imageView.setImageResource(phoneAudio.getBitmap_id());
        holder.selectedImg.setImageResource(R.drawable.selected);

        return convertView;*/
    }

}