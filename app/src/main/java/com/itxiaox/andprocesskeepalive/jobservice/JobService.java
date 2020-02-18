package com.itxiaox.andprocesskeepalive.jobservice;


import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

//api>21
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobService extends android.app.job.JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            startJob(this);
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    public static void startJob(Context context){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

            //setPersisted 在设备重启依然执行
            //需要增加权限，RECEIVE_BOOT_COMPLETED
            JobInfo.Builder builder = new JobInfo.Builder(8,new ComponentName(context.getPackageName()
            ,JobService.class.getName())).setPersisted(true);

            // < 7.0
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
                //每隔1s执行一次 job
                //版本23开始，进行了改进，最新周期为5s
                builder.setPeriodic(1000);
            }else {
                builder.setMinimumLatency(1000);
            }

        }

    }
}
