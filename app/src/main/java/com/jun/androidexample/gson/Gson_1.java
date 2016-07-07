package com.jun.androidexample.gson;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 解析简单数据
 */
public class Gson_1 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 获取Json数据
        String jsonData = getJsonData("JsonData_1.json");

        // 解析简单的Json数据
        Gson gson = new Gson();
        Person person = gson.fromJson(jsonData, Person.class);

        // 通过Log展示解析的结果
        Logger.i("name: " + person.getName()
                + "\n"
                + "age: " + person.getAge());
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
        Intent intent = new Intent(context, Gson_1.class);
        context.startActivity(intent);
    }
}
