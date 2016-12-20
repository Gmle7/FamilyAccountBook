package com.cc.familyaccountbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 陈超 on 2016/12/1.
 */
public class WelcomeActivity extends Activity{
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        getActionBar().hide();
        String VersionCode =getVersionName(WelcomeActivity.this);
        Log.i("welcome","最新版本号："+VersionCode);
        float nowVersionCode=Float.parseFloat(VersionCode);

        SharedPreferences sp=getSharedPreferences("firstEnter",MODE_PRIVATE);
        float spVersionCode=sp.getFloat("spVersinCode",5);
        Log.i("welcome","sp版本号："+spVersionCode);
        if(nowVersionCode>spVersionCode){
            setContentView(R.layout.guide_activity);
            SharedPreferences.Editor edit =sp.edit();
            edit.putFloat("spVersionCode",nowVersionCode);
            edit.apply();
            Toast.makeText(WelcomeActivity.this,"第一次",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent();
            intent.setClass(WelcomeActivity.this,GuideActivity.class);
            WelcomeActivity.this.startActivity(intent);
            finish();
        }else {
            Intent intent=new Intent();
            intent.setClass(WelcomeActivity.this, MainActivity.class);
            WelcomeActivity.this.startActivity(intent);
            finish();
        }
    }
/*
* 获取软件版本号
* */
    private String getVersionName(Context context) {
        String versionName = "";
        try{
            //获取版本号，对应AndroidManifest.xml文件下versionCode
            versionName=context.getPackageManager().getPackageInfo(
                    context.getPackageName(),0).versionName;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return versionName;

    }

}
