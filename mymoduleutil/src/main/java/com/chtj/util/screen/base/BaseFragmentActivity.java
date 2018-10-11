package com.chtj.util.screen.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.chtj.util.screen.util.ScreenUtils;
import com.example.mymoduleutil.R;
import com.jaeger.library.StatusBarUtil;

public abstract class BaseFragmentActivity extends FragmentActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ScreenUtils.adaptScreen4VerticalSlide(this, 720);
        super.onCreate(savedInstanceState);

        int color = ContextCompat.getColor(this,R.color.comm_cutline_1);
        StatusBarUtil.setColor(this, color, 30);
        //mToolbar.setBackgroundColor(color);
        StatusBarUtil.setLightMode(this);

        StatusBarUtil.setTransparent(this);
    }
}
