package com.jun.androidexample.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageView;

import com.jun.androidexample.BaseActivity;
import com.jun.androidexample.R;
import com.jun.androidexample.util.HttpCallbackListener;
import com.jun.androidexample.util.HttpUtil;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * HttpURLConnection
 */
public class HttpURLConnectionActivity extends BaseActivity {

    @BindView(R.id.show_image)
    ImageView showImage;
    @BindView(R.id.get_image)
    Button getImage;

    private Bitmap bitmap;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x001:
                    showImage.setImageBitmap(bitmap);
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_http_url_connection;
    }

    @OnClick(R.id.get_image)
    public void onClick() {
        HttpUtil.sendGetRequest(
                "https://pic3.zhimg.com/2585173a01d52b49d9714e2c50801d15.jpg",
                new HttpCallbackListener() {
                    @Override
                    public void onFinish(byte[] response) {
                        bitmap = BitmapFactory.decodeByteArray(response, 0, response.length);
                        handler.sendEmptyMessage(0x001);
                    }

                    @Override
                    public void onError(Exception e) {
                        // 在这里对异常情况进行处理
                        Logger.e(e.getMessage());
                    }
                });
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, HttpURLConnectionActivity.class);
        context.startActivity(intent);
    }
}
