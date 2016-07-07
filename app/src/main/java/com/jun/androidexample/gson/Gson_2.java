package com.jun.androidexample.gson;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jun.androidexample.model.FooBean;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * 将Json数据解析成实体含Date类型
 */
public class Gson_2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 获取Json数据
        String jsonData = getJsonData("JsonData_2");

        // 解析Json数据
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        Gson gson = builder.create();
        FooBean fooBean = gson.fromJson(jsonData, FooBean.class);

        Date date = fooBean.getCreated_at();
        String created_at = date.toString();

        // 通过Log展示解析的结果
        Logger.i("id: " + fooBean.getId()
                + "\n"
                + "body: " + fooBean.getBody()
                + "\n"
                + "number: " + fooBean.getNumber()
                + "\n"
                + "created_at: " + created_at);
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
        Intent intent = new Intent(context, Gson_2.class);
        context.startActivity(intent);
    }
}
