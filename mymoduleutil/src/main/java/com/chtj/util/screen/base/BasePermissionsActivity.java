package com.chtj.util.screen.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.chtj.util.load.LoadingDialog;
import com.chtj.util.screen.util.ScreenUtils;
import com.example.mymoduleutil.R;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePermissionsActivity extends AppCompatActivity
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


    public static final String TAG = "BaseActivity";
    public static  int REQUEST_CODE = 0;




    /**
     * 请求权限
     * @param permissions
     * @param requestCode
     */
    public void requestPermission(String[] permissions, int requestCode) {
        this.REQUEST_CODE = requestCode;
        //检查权限是否授权
        if(checkPermissions(permissions)) {
            permissionSucceed(REQUEST_CODE);
        }else{
            List<String> needPermissions = getPermissions(permissions);
            ActivityCompat.requestPermissions(this, needPermissions.toArray(new String[needPermissions.size()]), REQUEST_CODE);
        }
    }

    /**
     * 获取权限
     * @param permissions
     * @return
     */
    protected List<String> getPermissions(String[] permissions) {
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                permissionList.add(permission);
            }
        }
        return permissionList;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (verificationPermissions(grantResults)) {
                permissionSucceed(REQUEST_CODE);
            } else {
                permissionFailing(REQUEST_CODE);
                showFailingDialog();
            }
        }
    }

    /**
     * 验证权限
     * @param results
     * @return
     */
    private boolean verificationPermissions(int[] results){
        for (int result : results) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检测所有的权限是否都已授权
     * @param permissions
     * @return
     */
    protected boolean checkPermissions(String[] permissions){
        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.M) {
            return true;
        }

        for(String permission:permissions) {
            if(ContextCompat.checkSelfPermission(BasePermissionsActivity.this,permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 无权限弹出系统提示框
     */
    private void showFailingDialog() {
        new AlertDialog.Builder(this)
                .setTitle("系统提示")
                .setMessage("请求权限失败，是否手动开启？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startSettings();
                    }
                }).show();
    }

    /**
     * 打开权限设置界面
     */
    private void startSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    public void permissionFailing(int code) {
        Log.d(TAG, "获取权限失败=" + code);
    }

    public void permissionSucceed(int code) {
        Log.d(TAG, "获取权限成功=" + code);
    }
}
