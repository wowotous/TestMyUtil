package com.test.testmyutil;

import android.app.Application;
import android.content.Context;

import com.clj.fastble.BleManager;

/**
 * Created by Administrator on 2018/10/24.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        BleManager.getInstance().init(this);
    }

}
