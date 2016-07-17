package com.jun.androidexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jun.androidexample.FloatingActionButton.FloatingActionButtonActivity;
import com.jun.androidexample.activity.AIDLActivity;
import com.jun.androidexample.activity.CoordinatorLayoutActivity;
import com.jun.androidexample.activity.HttpURLConnectionActivity;
import com.jun.androidexample.activity.MessengerActivity;
import com.jun.androidexample.activity.ServiceActivity;
import com.jun.androidexample.activity.SocketActivity;
import com.jun.androidexample.bannerview.BannerViewActivity;
import com.jun.androidexample.customview.CustomViewActivity;
import com.jun.androidexample.customviewgroup.CustomViewGroupActivity;
import com.jun.androidexample.gson.GsonActivity;
import com.jun.androidexample.imageslider.ImageSliderActivity;
import com.jun.androidexample.parcelable.Parcelable_1;
import com.jun.androidexample.pull.PullActivity;
import com.jun.androidexample.qualifier.QualifierActivity;
import com.jun.androidexample.recyclerview.RecyclerViewActivity;
import com.jun.androidexample.sax.SaxActivity;
import com.jun.androidexample.serializable.Serializable_1;
import com.jun.androidexample.viewpager.ViewPagerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面Activity
 */
public class MainActivity extends AppCompatActivity {

    private ListView activityListView;
    private List<String> activityList;
    private ActivityAdapter activityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化数据
        initData();
        // 初始化控件
        initViews();
    }

    private void initData() {

        activityList = new ArrayList<>();
        activityList.add("使用Gson解析Json数据");
        activityList.add("Parcelable可打包的对象");
        activityList.add("Serializable序列化对象");
        activityList.add("使用Pull方式解析XML数据");
        activityList.add("使用SAX方式解析XML数据");
        activityList.add("自定义View");
        activityList.add("自定义ViewGroup");
        activityList.add("屏幕适配之限定符");
        activityList.add("ViewPager的使用");
        activityList.add("自定义BannerView图片轮播");
        activityList.add("使用AndroidImageSlider");
        activityList.add("使用RecyclerView");
        activityList.add("使用FloatingActionButton");
        activityList.add("使用Service服务");
        activityList.add("使用Messenger进行交互");
        activityList.add("使用AIDL");
        activityList.add("使用CoordinatorLayout");
        activityList.add("使用HttpURLConnection");
        activityList.add("Socket通信");
    }

    private void initViews() {

        activityAdapter = new ActivityAdapter(
                MainActivity.this,
                R.layout.item_activity,
                activityList);

        activityListView = (ListView) findViewById(R.id.activity_list_view);
        activityListView.setAdapter(activityAdapter);
        activityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                launchActivity(position);
            }
        });
    }

    private void launchActivity(int position) {

        switch (position) {
            case 0:
                GsonActivity.launch(MainActivity.this);
                break;
            case 1:
                Parcelable_1.launch(MainActivity.this);
                break;
            case 2:
                Serializable_1.launch(MainActivity.this);
                break;
            case 3:
                PullActivity.launch(MainActivity.this);
                break;
            case 4:
                SaxActivity.launch(MainActivity.this);
                break;
            case 5:
                CustomViewActivity.launch(MainActivity.this);
                break;
            case 6:
                CustomViewGroupActivity.launch(MainActivity.this);
                break;
            case 7:
                QualifierActivity.launch(MainActivity.this);
                break;
            case 8:
                ViewPagerActivity.launch(MainActivity.this);
                break;
            case 9:
                BannerViewActivity.launch(MainActivity.this);
                break;
            case 10:
                ImageSliderActivity.launch(MainActivity.this);
                break;
            case 11:
                RecyclerViewActivity.launch(MainActivity.this);
                break;
            case 12:
                FloatingActionButtonActivity.launch(MainActivity.this);
                break;
            case 13:
                ServiceActivity.launch(MainActivity.this);
                break;
            case 14:
                MessengerActivity.launch(MainActivity.this);
                break;
            case 15:
                AIDLActivity.launch(MainActivity.this);
                break;
            case 16:
                CoordinatorLayoutActivity.launch(MainActivity.this);
                break;
            case 17:
                HttpURLConnectionActivity.launch(MainActivity.this);
                break;
            case 18:
                SocketActivity.launch(MainActivity.this);
                break;
        }
    }
}
