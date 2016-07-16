package com.jun.androidexample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.jun.androidexample.BaseActivity;
import com.jun.androidexample.R;
import com.jun.androidexample.adapter.StringAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * CoordinatorLayout的使用
 */
public class CoordinatorLayoutActivity extends BaseActivity {

    @BindView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<String> dataList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_coordinator_layout;
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, CoordinatorLayoutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        initData();

        initView();
    }

    private void initView() {

        StringAdapter stringAdapter =
                new StringAdapter(this, dataList);
        recyclerView.setAdapter(stringAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // 设置排列方向
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initData() {

        dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add("Item: " + i);
        }
    }

    @OnClick(R.id.floating_action_button)
    public void onClick() {
        Snackbar snackbar =
                Snackbar.make(coordinatorLayout, "Snackbar", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
