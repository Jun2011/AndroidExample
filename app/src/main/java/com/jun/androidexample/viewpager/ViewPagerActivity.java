package com.jun.androidexample.viewpager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.jun.androidexample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager
 */
public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;

    /*private List<View> viewList;
    private WithoutFragmentPagerAdapter withoutFragmentPagerAdapter;*/

    private List<Fragment> fragmentList;
    private WithFragmentPagerAdapter withFragmentPagerAdapter;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        /*// 初始化数据（未结合Fragment使用）
        initDataWithoutFragment();*/

        // 初始化数据（结合Fragment使用）
        initDataWithFragment();

        // 初始化控件
        initView();
    }

    // 初始化数据（结合Fragment使用）
    private void initDataWithFragment() {

        fragmentList = new ArrayList<>();

        Fragment fragment1 = new Fragment1();
        fragmentList.add(fragment1);
        Fragment fragment2 = new Fragment2();
        fragmentList.add(fragment2);
        Fragment fragment3 = new Fragment3();
        fragmentList.add(fragment3);
    }

    // 初始化控件
    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.without_fragment_view_pager);

        /*withoutFragmentPagerAdapter = new WithoutFragmentPagerAdapter(viewList);
        viewPager.setAdapter(withoutFragmentPagerAdapter);*/

        withFragmentPagerAdapter = new WithFragmentPagerAdapter(
                getSupportFragmentManager(),
                fragmentList);
        viewPager.setAdapter(withFragmentPagerAdapter);
    }

    /*// 初始化数据（未结合Fragment使用）
    private void initDataWithoutFragment() {
        viewList = new ArrayList<>();
        View view1 = getLayoutInflater().inflate(R.layout.view_page1, null);
        viewList.add(view1);
        View view2 = getLayoutInflater().inflate(R.layout.view_page2, null);
        viewList.add(view2);
        View view3 = getLayoutInflater().inflate(R.layout.view_page3, null);
        viewList.add(view3);
    }*/

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, ViewPagerActivity.class);
        context.startActivity(intent);
    }
}
