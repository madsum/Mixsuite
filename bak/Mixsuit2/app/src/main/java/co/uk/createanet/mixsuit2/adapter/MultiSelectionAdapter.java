package co.uk.createanet.mixsuit2.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import co.uk.createanet.mixsuit2.R;


public class MultiSelectionAdapter<T> extends BaseAdapter {
    Context mContext;
    LayoutInflater mInflater;
    ArrayList<T> mList;
    SparseBooleanArray mSparseBooleanArray;


    public MultiSelectionAdapter(Context context, ArrayList<T> list) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mSparseBooleanArray = new SparseBooleanArray();
        mList = new ArrayList<T>();
        this.mList = list;
    }

    public ArrayList<T> getCheckedItems() {
        ArrayList<T> mTempArry = new ArrayList<T>();
        for (int i = 0; i < mList.size(); i++) {
            if (mSparseBooleanArray.get(i)) {
                mTempArry.add(mList.get(i));
            }
        }
        return mTempArry;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.aurdio_list_row, null);
        }
        TextView tvTitle = (TextView) convertView.findViewById(R.id.audio_title);
        tvTitle.setText("This is song title");
        TextView tvDes = (TextView) convertView.findViewById(R.id.audio_desc);
        tvDes.setText("This is song details");
        ImageView imageView = (ImageView) convertView.findViewById(R.id.audio_art);
        imageView.setImageResource(R.drawable.a10);
        CheckBox mCheckBox = (CheckBox) convertView.findViewById(R.id.chkEnable);
        mCheckBox.setTag(position);
        mCheckBox.setChecked(mSparseBooleanArray.get(position));
        mCheckBox.setOnCheckedChangeListener(mCheckedChangeListener);
        return convertView;
    }

    OnCheckedChangeListener mCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);
        }
    };
}