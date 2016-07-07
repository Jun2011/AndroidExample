package com.jun.androidexample.gson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jun.androidexample.R;

/**
 * 解析简单数据
 */
public class GsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);

        initViews();
    }

    private void initViews() {

        findViewById(R.id.btn_gson_1)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Gson_1.launch(GsonActivity.this);
                    }
                });

        findViewById(R.id.btn_gson_2)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Gson_2.launch(GsonActivity.this);
                    }
                });

        findViewById(R.id.btn_gson_3)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Gson_3.launch(GsonActivity.this);
                    }
                });

        findViewById(R.id.btn_gson_4)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Gson_4.launch(GsonActivity.this);
                    }
                });
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, GsonActivity.class);
        context.startActivity(intent);
    }
}
