package com.jun.androidexample.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jun.androidexample.BaseActivity;
import com.jun.androidexample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView
 * 1、Item分为头部、普通、足部
 * 2、Item的点击事件
 * 3、分割线
 * 4、动画
 * 5、排列方式
 */
public class RecyclerViewActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<NewsList> newsLists;

    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        // 初始化数据
        initData();

        // 初始化Adapter
        initAdapter();

        // 初始化RecyclerView
        initRecyclerView();

        // 获取数据
        getData();
    }

    private void initAdapter() {

        recyclerViewAdapter = new RecyclerViewAdapter(
                RecyclerViewActivity.this, newsLists);
    }

    private void initData() {

        newsLists = new ArrayList<>();

        NewsList newsList1 = new NewsList();
        newsList1.setImageUrl("http://pic2.zhimg.com/9a9c3c008066ee0f724327560eac380d.jpg");
        newsList1.setTitle("从投行这个高学历人才挤破头想进的地方，离开");
        NewsList newsList2 = new NewsList();
        newsList2.setImageUrl("http://pic2.zhimg.com/a4ea2636507026b1068a23931409b1d1.jpg");
        newsList2.setTitle("英国脱欧日元升值，这事儿正说明日元并不保值");
        NewsList newsList3 = new NewsList();
        newsList3.setImageUrl("http://pic2.zhimg.com/9554aecb66d4c64055857c0c038bd571.jpg");
        newsList3.setTitle("最近刷屏的「葛优躺」，背后的故事还要说回 23 年前");

        newsLists.add(newsList1);
        newsLists.add(newsList2);
        newsLists.add(newsList3);
    }

    private void initRecyclerView() {

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(RecyclerViewActivity.this));
    }

    public void getData() {

        NewsList newsList1 = new NewsList();
        newsList1.setImageUrl("http://pic2.zhimg.com/ded2566d08246dc9c41e04c5429d6b71.jpg");
        newsList1.setTitle("作为妇产科男医生，我来说说卫生棉条");
        NewsList newsList2 = new NewsList();
        newsList2.setImageUrl("http://pic4.zhimg.com/e857c60bfcfdf7336d2c3c59d353331b.jpg");
        newsList2.setTitle("读读日报 24 小时热门 TOP 5 · 一个贯穿篮球游戏历史的男人");
        NewsList newsList3 = new NewsList();
        newsList3.setImageUrl("http://pic1.zhimg.com/73a31284b742aec277e01e53f2621170.jpg");
        newsList3.setTitle("瞎扯 · 如何正确地吐槽");
        NewsList newsList4 = new NewsList();
        newsList4.setImageUrl("http://pic1.zhimg.com/eb6f366b634cf0f1d1e69152be0f2ecc.jpg");
        newsList4.setTitle("这里是广告 · 月薪八千闺蜜聚会指南");

        newsLists.add(newsList1);
        newsLists.add(newsList2);
        newsLists.add(newsList3);
        newsLists.add(newsList4);

        // 添加数据
        recyclerViewAdapter.addData(newsLists);
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, RecyclerViewActivity.class);
        context.startActivity(intent);
    }
}
