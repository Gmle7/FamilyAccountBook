package com.cc.familyaccountbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by 陈超 on 2016/11/29.
 * 功能：使用ViewPager实现出次进入应用时的引导页
 * （1）判断是否是第一次加载应用--读取sharedPreference的方法
 * （2）是，则进去引导页，否则进入主页面MainActivity
 * （3）2S后执行（2）操作
 */

public class SplashActivity extends Activity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        boolean mFirst=isFirstEnter(SplashActivity.this,SplashActivity.this.getClass().getName());
        if(mFirst){
            mHandler.sendEmptyMessageDelayed(SWICH_GUIDEACTIVITY,2000);
        }else{
            mHandler.sendEmptyMessageDelayed(SWICH_MAINACTIVITY,2000);
        }
    }//判断应用是否初次加载，读取sharePreferences中的guide_activity字段
    private static final String SHAREDPREFERENCES_NAME="my_pref";
    private static final String KEY_GUIDE_ACTIVITY="guide_activity";
    private boolean isFirstEnter(Context context,String className){
        if(context==null||className==null||"".equalsIgnoreCase(className))
        {
            return false;
        }
        String mResultStr=context.getSharedPreferences(SHAREDPREFERENCES_NAME,
                Context.MODE_WORLD_READABLE).getString(KEY_GUIDE_ACTIVITY,"");
        if(mResultStr.equalsIgnoreCase("false")){
            return false;
        }else {
            return true;
        }
    }
    private final static int SWICH_MAINACTIVITY=1000;
    private final static int SWICH_GUIDEACTIVITY=1001;
    public Handler mHandler=new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SWICH_MAINACTIVITY:
                    Intent mIntent = new Intent();
                    mIntent.setClass(SplashActivity.this, Login.class);//进入主页面
                    SplashActivity.this.startActivity(mIntent);
                    SplashActivity.this.finish();
                    break;
                case SWICH_GUIDEACTIVITY:
                    Intent nIntent = new Intent();
                    nIntent.setClass(SplashActivity.this, GuideActivity.class);//进入引导页
                    SplashActivity.this.startActivity(nIntent);
                    SplashActivity.this.finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
