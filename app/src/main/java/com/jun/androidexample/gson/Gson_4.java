package com.jun.androidexample.gson;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 2016/7/7.
 */
public class Gson_4 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 获取Json数据
        String jsonData = getJsonData("JsonData_4");

        // 解析Json数据
        // 1、解析成Array数组
        NewslistBean[] newslistBeans = new Gson().fromJson(jsonData, NewslistBean[].class);

        /**
         * // Array数组转换成List列表
         * List<NewslistBean> newslistBeanList = Arrays.asList(newslistBeans);
         */

        // 通过Log展示解析结果
        for (NewslistBean newslistBean :
                newslistBeans) {
            Logger.i("ctime: " + newslistBean.getCtime()
                    + "\n"
                    + "title: " + newslistBean.getTitle());
        }

        // 2、解析成List列表
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<NewslistBean>>() {
        }.getType();
        List<NewslistBean> newslistBeanList = gson.fromJson(jsonData, listType);

        // 通过Log展示解析结果
        for (NewslistBean newslistBean :
                newslistBeanList) {
            Logger.i("ctime: " + newslistBean.getCtime()
                    + "\n"
                    + "title: " + newslistBean.getTitle());
        }
    }

    // 根据Json文件获取数据
    private String getJsonData(String jsonFileName) {
        AssetManager assetManager = getAssets();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            assetManager.open(jsonFileName)));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, Gson_4.class);
        context.startActivity(intent);
    }
}
