package com.itxiaox.andprocesskeepalive.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

public class KeepManager {
    private static KeepManager manager = new KeepManager();
    //广播
    private KeepReceiver keepReceiver;
    //弱引用
    private WeakReference<Activity> weakReference;

    private KeepManager(){}

    public static  KeepManager getInstance(){
        return manager;
    }

    /**
     * 注册，开启，关闭，广播
     */
    public void registerKeep(Context context){
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        keepReceiver = new KeepReceiver();
        context.registerReceiver(keepReceiver,filter);
    }



    /**
     * 注销广播接收者
     * @param context
     */
    public void unRegisterKeep(Context context){
        if (keepReceiver!=null){
            context.unregisterReceiver(keepReceiver);
        }
    }

    /**
     * 开启1像素Activity
     * @param context
     */
    public void startKeep(Context context){
        Intent intent = new Intent(context,KeepActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 关闭1像素Activity
     */
    public void finishKeep(){
        if (weakReference!=null){
            Activity activity = weakReference.get();
            if(activity!=null){
                activity.finish();
            }
            weakReference = null;
        }
    }

    /**
     * 设置弱引用
     * @param keep
     */
    public void setKeep(KeepActivity keep){
        weakReference = new WeakReference<Activity>(keep);
    }
}
