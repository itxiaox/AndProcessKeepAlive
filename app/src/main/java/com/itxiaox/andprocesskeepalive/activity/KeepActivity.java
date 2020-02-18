package com.itxiaox.andprocesskeepalive.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class KeepActivity extends Activity {
    private static final String TAG = "KeepActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "启动Keep" );
        Window window = getWindow();
        //就在屏幕左上角
        window.setGravity(Gravity.START|Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        //设置宽高
        params.width = 1;
        params.height = 1;
        //设置起始坐标
        params.x = 0;
        params.y = 0;
        window.setAttributes(params);

        //keepActivity创建一个弱引用
        KeepManager.getInstance().setKeep(this);
    }
}
