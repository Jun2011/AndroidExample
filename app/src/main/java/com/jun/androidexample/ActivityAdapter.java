package com.jun.androidexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * 主页面Adapter
 */
public class ActivityAdapter extends ArrayAdapter<String>{

    private int resourceId;

    public ActivityAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);

        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String activityAlias = getItem(position);

        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.activityText = (TextView) view.findViewById(R.id.activity_text_view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.activityText.setText(activityAlias);

        return view;
    }

    class ViewHolder {

        TextView activityText;
    }
}
