package com.jun.androidexample.qualifier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jun.androidexample.R;

/**
 * 新闻内容页面Activity
 */
public class NewsContentActivity extends AppCompatActivity {

    public static void launch(Context context, String newsTitle, String newsContent) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        // 获取传递过来的数据
        String newsTitle = getIntent().getStringExtra("news_title");
        String newsContent = getIntent().getStringExtra("news_content");

        NewsContentFragment newsContentFragment =
                (NewsContentFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.news_content_fragment);
        // 刷新新闻内容页面Fragment
        newsContentFragment.refresh(newsTitle, newsContent);
    }
}
