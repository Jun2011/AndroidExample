package com.jun.androidexample.qualifier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jun.androidexample.R;

import java.util.List;

/**
 * 新闻标题列表Adapter
 */
public class NewsTitleAdapter extends ArrayAdapter<News> {

    private int resourceId;

    public NewsTitleAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);

        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 获取到数据
        News news = getItem(position);

        // 声明View和ViewHolder
        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            // 加载布局
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            // 实例化ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.newsTitleText = (TextView) view.findViewById(R.id.news_title_textview);

            // 将viewHolder存到view的tag中
            view.setTag(viewHolder);
        } else {
            // 直接获取view
            view = convertView;
            // 从view的tag中获取viewHolder
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.newsTitleText.setText(news.getNewsTitle());

        return view;
    }

    class ViewHolder {

        private TextView newsTitleText;
    }
}
