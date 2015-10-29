package co.uk.createanet.mixsuit2.adapter;

/**
 * Created by masum on 22/10/15.
 */

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import co.uk.createanet.mixsuit2.R;
import co.uk.createanet.mixsuit2.model.YouTubeVideo;
import co.uk.createanet.mixsuit2.model.YouTubeViewHolder;


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
        YouTubeViewHolder holder = null;
        YouTubeVideo youTubeVideo = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.youtube_list_item, null);
            holder = new YouTubeViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.youtube_thumbnail);
            holder.selectedImg = (ImageView) convertView.findViewById(R.id.youtube_selected);
            convertView.setTag(holder);
        } else
            holder = (YouTubeViewHolder) convertView.getTag();

        holder.txtDesc.setText(youTubeVideo.getViewCount()+ " views");
        holder.txtTitle.setText(youTubeVideo.getVideoTitle());
        holder.imageView.setImageBitmap(youTubeVideo.getBitmap());
      //  Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.selected);
      //  holder.selectedImg.setImageBitmap(bitmap);
        holder.selectedImg.setImageResource(R.drawable.selected);



        //  holder.imageView.setImageBitmap(getImageBitmap(youTubeVideo.getThumbnailsUrl()));
        //holder.imageView.setImageURI(Uri.parse(youTubeVideo.getThumbnailsUrl()));

        /*try {
            URL thumb_u = new URL(youTubeVideo.getThumbnailsUrl());
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            holder.imageView.setImageDrawable(thumb_d);
        }
        catch (Exception e) {
            // handle it
        }
        */
        return convertView;
    }

}