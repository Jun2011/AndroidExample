package com.jun.androidexample.sax;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * 使用SAX方式解析XML数据
 */
public class SaxActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 获取Xml数据
        String xmlData = getXmlData("XmlData_1");

        // 解析Xml数据
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            // 获取XMLReader实例
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHander hander = new ContentHander();
            // 将ContentHandler的实例设置到XMLReader中
            xmlReader.setContentHandler(hander);
            // 开始解析
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
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
        Intent intent = new Intent(context, SaxActivity.class);
        context.startActivity(intent);
    }
}
