package com.jun.androidexample.pull;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * 使用Pull方式解析XML数据
 */
public class PullActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 获取Xml数据
        String xmlData = getXmlData("XmlData_1");

        // 解析Xml数据
        try {
            // 获取XmlPullParserFactory实例
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            // 获取XmlPullParser实例
            XmlPullParser parser = factory.newPullParser();
            // 传入Xml数据
            parser.setInput(new StringReader(xmlData));
            // 获取eventTypt
            int eventTypt = parser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventTypt != XmlPullParser.END_DOCUMENT) {
                String nodeName = parser.getName();
                switch (eventTypt) {
                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodeName)) {
                            id = parser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = parser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = parser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("app".equals(nodeName)) {

                            // 通过Log展示解析结果
                            Logger.i("id: " + id
                                    + "\n"
                                    + "name: " + name
                                    + "\n"
                                    + "version: " + version);
                        }
                        break;
                }
                eventTypt = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 根据Xml文件获取数据
    private String getXmlData(String xmlFileName) {
        AssetManager assetManager = getAssets();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            assetManager.open(xmlFileName)));
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
        Intent intent = new Intent(context, PullActivity.class);
        context.startActivity(intent);
    }
}
