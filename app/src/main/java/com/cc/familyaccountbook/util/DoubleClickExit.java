package com.cc.familyaccountbook.util;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Created by admin on 2016/12/13.
 */
public class DoubleClickExit {
    /**
     * 双击退出工具类
     *
     * @author wxc
     */
    private final Activity mActivity;
    private boolean isOnKeyBacking;
    private Handler mHandler;
    private Toast mBackToast;

    public DoubleClickExit(Activity activity) {
        mActivity = activity;
        mHandler = new Handler(Looper.getMainLooper());
    }
        /**
         * Activity onKeyDown事件
         */
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode != KeyEvent.KEYCODE_BACK) {
                return false;
            }
            if (isOnKeyBacking) {
                mHandler.removeCallbacks(onBackTimeRunnable);
                if (mBackToast != null) {
                    mBackToast.cancel();
                }
                mActivity.finish();
                return true;
            } else {
                isOnKeyBacking = true;
                if (mBackToast == null) {
                    mBackToast = Toast.makeText(mActivity, "再次点击返回退出应用", Toast.LENGTH_SHORT);
                }
                mBackToast.show();
                mHandler.postDelayed(onBackTimeRunnable, 2000);
                return true;
            }
        }

        private Runnable onBackTimeRunnable = new Runnable() {
            @Override
            public void run() {
                isOnKeyBacking = false;
                if (mBackToast != null) {
                    mBackToast.cancel();
                }
            }
        };

}
