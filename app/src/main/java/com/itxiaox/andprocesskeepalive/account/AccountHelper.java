package com.itxiaox.andprocesskeepalive.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class AccountHelper {
    private static final String TAG = "AccountHelper";

    private static final String ACOUNT_TYPE = "com.itxiaox.andprocesskeepalive.account";
    private static final String AUTHORITY = "com.itxiaox.andprocesskeepalive.provider";
    private static final String ACCOUNT_NAMWE = "xiaox";
    private static final String ACCOUNT_PWD = "123456";

    /**
     * 添加一个账户
     * @param context
     */
    public static void addAccount(Context context){
        try{
            AccountManager accountManager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
            //获得此类型的账户
            //需要增加权限 GET_ACCOUNTS
            Account[] accounts = accountManager.getAccountsByType(ACOUNT_TYPE);

            if(accounts.length >0 ){
                Log.d(TAG, "addAccount: 账户已存在");
                return;
            }

            Account account = new Account(ACCOUNT_NAMWE,ACOUNT_TYPE);
            //给这个账户类型添加一个账户
            //需要添加权限，
            accountManager.addAccountExplicitly(account,ACCOUNT_PWD,new Bundle());
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 设置账户自动同步
     */

    public static void autoSync(){
        Account account = new Account(ACCOUNT_NAMWE,ACOUNT_TYPE);
        //下面桑额都需要同一个权限，WRILE_SYNC_SETTINGS

        //设置同步
        ContentResolver.setIsSyncable(account, AUTHORITY,1);
        //自动同步
        ContentResolver.setSyncAutomatically(account,AUTHORITY,true);
        //设置同步周期
        ContentResolver.addPeriodicSync(account,AUTHORITY,new Bundle(),1);
    }
}
