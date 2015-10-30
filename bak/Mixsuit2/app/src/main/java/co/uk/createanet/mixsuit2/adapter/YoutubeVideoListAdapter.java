package co.uk.createanet.mixsuit2.adapter;

/**
 * Created by masum on 22/10/15.
 */

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import co.uk.createanet.mixsuit2.R;
import co.uk.createanet.mixsuit2.model.YouTubeVideo;


public class YoutubeVideoListAdapter extends ArrayAdapter<YouTubeVideo> {
    Context context;

    public YoutubeVideoListAdapter(Context context, int resourceId,
                                   List<YouTubeVideo> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
/*    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;
        ImageView selectedImg;

    }*/

    public View getView(int position, View convertView, ViewGroup parent) {
       YouTubeVideo.YouTubeViewHolder holder = null;
        YouTubeVideo youTubeVideo = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.youtube_list_item, null);
            holder = new YouTubeVideo.YouTubeViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.youtube_thumbnail);
            holder.selectedImg = (ImageView) convertView.findViewById(R.id.youtube_selected);
            convertView.setTag(holder);
        } else
            holder = (YouTubeVideo.YouTubeViewHolder) convertView.getTag();

        holder.txtDesc.setText(youTubeVideo.getViewCount()+ " views");
        holder.txtTitle.setText(youTubeVideo.getVideoTitle());
        holder.imageView.setImageBitmap(youTubeVideo.getBitmap());
      //  Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.selected);
      //  holder.selectedImg.setImageBitmap(bitmap);
        holder.selectedImg.setImageResource(R.drawable.selected);
        return convertView;
    }

}