package com.jun.androidexample.parcelable;

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
public class Parcelable_1 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parcelable_1);

        findViewById(R.id.btn_parcelable_2)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student student = new Student();
                student.setName("Tom");
                student.setAge(18);

                Parcelable_2.launch(Parcelable_1.this, student);
            }
        });
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, Parcelable_1.class);
        context.startActivity(intent);
    }
}
