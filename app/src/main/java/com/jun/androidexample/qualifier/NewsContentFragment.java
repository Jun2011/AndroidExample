package com.jun.androidexample.qualifier;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jun.androidexample.R;

/**
 * 新闻内容页面Fragment
 */
public class NewsContentFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // 加载布局
        view = inflater.inflate(R.layout.fragment_news_content, container, false);

        return view;
    }

    // 刷新新闻
    public void refresh(String newsTitle, String newsContent) {

        TextView newsTitleText = (TextView) view.findViewById(R.id.news_title_textview);
        TextView newsContentText = (TextView) view.findViewById(R.id.news_content_textview);

        newsTitleText.setText(newsTitle);
        newsContentText.setText(newsContent);
    }
}
