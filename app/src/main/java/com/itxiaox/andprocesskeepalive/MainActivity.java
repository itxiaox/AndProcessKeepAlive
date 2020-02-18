package com.itxiaox.andprocesskeepalive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.itxiaox.andprocesskeepalive.account.AccountHelper;
import com.itxiaox.andprocesskeepalive.activity.KeepManager;
import com.itxiaox.andprocesskeepalive.jobservice.JobService;
import com.itxiaox.andprocesskeepalive.service.ForegroundService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1像素保活法
        KeepManager.getInstance().registerKeep(this);

        //前台服务保活
        startService(new Intent(this, ForegroundService.class));
        /**
         * 4.3之前：直接启动前台服务，不会有通知
         *
         * 4.3-7.0：
         */

        //sticky 拉活
        startService(new Intent(this,StickyService.class));

        //账户同步拉活、
        //添加账户失败，需要权限，sdk 22(Android 5.1+)没有权限，不能使用
        AccountHelper.addAccount(this);
        AccountHelper.autoSync();

        //Job 拉活 >21 ,android 7.0+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            JobService.startJob(this);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeepManager.getInstance().unRegisterKeep(this);
    }
}
