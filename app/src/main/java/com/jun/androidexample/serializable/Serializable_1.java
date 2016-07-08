package com.jun.androidexample.serializable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jun.androidexample.R;

/**
 * Created by Jun on 2016/7/7.
 */
public class Serializable_1 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serializable_1);

        findViewById(R.id.btn_serializable_2)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Person person = new Person();
                        person.setName("Tom");
                        person.setAge(18);

                        Serializable_2.launch(Serializable_1.this, person);
                    }
                });
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, Serializable_1.class);
        context.startActivity(intent);
    }
}
