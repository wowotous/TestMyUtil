package com.chtj.util.screen.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.chtj.util.load.LoadingDialog;
import com.chtj.util.screen.util.ScreenUtils;
import com.example.mymoduleutil.R;
import com.jaeger.library.StatusBarUtil;
public abstract class BaseActivity extends AppCompatActivity
        implements IBaseView {

    protected View     mContentView;
    protected Activity mActivity;

    public LoadingDialog loadingDialog;//菊花模态加载框

    /**
     * 初始化菊花模态加载框
     */
    public void initLoadingDialog(){
        loadingDialog = new LoadingDialog();
        loadingDialog.createLoadingDialog(this);
    }
    /**
     * 显示模态框
     */
    protected void showDialog(){
        loadingDialog.showDialog();
    }
    /**
     * 关闭模态框
     */
    protected void closeDialog(){
        loadingDialog.closeDialog();
    }
    /**
     * 上次点击时间
     */
    private long lastClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        ScreenUtils.adaptScreen4VerticalSlide(this, 720);
        super.onCreate(savedInstanceState);
        int color = ContextCompat.getColor(this,R.color.comm_cutline_1);
        StatusBarUtil.setColor(this, color, 30);
        //mToolbar.setBackgroundColor(color);
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setTransparent(this);
        initLoadingDialog();//初始化LoadingDialog
        mActivity = this;
        Bundle bundle = getIntent().getExtras();
        initData(bundle);
        setBaseView(bindLayout());
        initView(savedInstanceState, mContentView);
        doBusiness();
    }

    @SuppressLint("ResourceType")
    protected void setBaseView(@LayoutRes int layoutId) {
        if (layoutId <= 0) return;
        setContentView(mContentView = LayoutInflater.from(this).inflate(layoutId, null));
    }

    /**
     * 判断是否快速点击
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 200) {
            lastClick = now;
            return false;
        }
        return true;
    }

    @Override
    public void onClick(final View view) {
        if (!isFastClick()) onWidgetClick(view);
    }



}
