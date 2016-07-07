package com.jun.androidexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jun.androidexample.gson.GsonActivity;
import com.jun.androidexample.parcelable.Parcelable_1;
import com.jun.androidexample.pull.Pull_1;
import com.jun.androidexample.serializable.Serializable_1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        findViewById(R.id.btn_gson).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GsonActivity.launch(MainActivity.this);
                    }
                });

        findViewById(R.id.btn_parcelable_1).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Parcelable_1.launch(MainActivity.this);
                    }
                });

        findViewById(R.id.btn_serializable_1).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Serializable_1.launch(MainActivity.this);
                    }
                });

        findViewById(R.id.btn_pull_1).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Pull_1.launch(MainActivity.this);
                    }
                });
    }
}
