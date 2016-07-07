package com.jun.androidexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jun.androidexample.gson.SimpleJsonDataActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        findViewById(R.id.btn_gson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleJsonDataActivity.launch(MainActivity.this);
            }
        });
    }
}
