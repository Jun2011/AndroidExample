package com.jun.androidexample.qualifier;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jun.androidexample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻标题列表Fragment
 */
public class NewsTitleFragment extends Fragment {

    private ListView newsTitleListView;
    private List<News> newsList;
    private NewsTitleAdapter newsTitleAdapter;
    private boolean isTwoPane;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // 初始化数据
        initData();

        // 初始化Adapter
        newsTitleAdapter = new NewsTitleAdapter(context, R.layout.item_news_title, newsList);
    }

    // 初始化数据
    private void initData() {

        newsList = new ArrayList<>();

        News news1 = new News();
        news1.setNewsTitle("Succeed in College as a Learning Disabled Student");
        news1.setNewsContent("College freshmen will soon learn to live with a\n" +
                "roommate, adjust to a new social scene and survive less-than-stellar\n" +
                "dining hall food. Students with learning disabilities will face these\n" +
                "transitions while also grappling with a few more hurdles.");
        newsList.add(news1);

        News news2 = new News();
        news2.setNewsTitle("Google Android exec poached by China's Xiaomi");
        news2.setNewsContent("China's Xiaomi has poached a key Google executive\n" +
                "involved in the tech giant's Android phones, in a move seen as a coup\n" +
                "for the rapidly growing Chinese smartphone maker.");
        newsList.add(news2);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_title, container, false);
        newsTitleListView = (ListView) view.findViewById(R.id.news_title_listview);
        newsTitleListView.setAdapter(newsTitleAdapter);
        newsTitleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // 获取数据
                News news = newsList.get(position);

                // 如果是双页就刷新新闻内容页面Fragment
                if (isTwoPane) {

                    NewsContentFragment newsContentFragment =
                            (NewsContentFragment) getFragmentManager().
                                    findFragmentById(R.id.news_content_fragment);
                    // 刷新NewsContentFragment数据
                    newsContentFragment.refresh(news.getNewsTitle(), news.getNewsContent());
                } else { // 如果是单页就启动新闻内容页面Activity

                    NewsContentActivity.launch(getActivity(),
                            news.getNewsTitle(),
                            news.getNewsContent());
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 能找到news_content_layout就是双页否则就是单页
        View view = getActivity().findViewById(R.id.news_content_layout);
        if (view != null && view.getVisibility() == View.VISIBLE) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }
}
