package com.chtj.util.screen.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chtj.util.screen.util.DialogHelper;
import com.chtj.util.screen.util.ScreenUtils;
import com.chtj.util.screen.util.ToastUtils;
import com.example.mymoduleutil.R;


/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/09/27
 *     desc  : demo about ScreenUtils
 * </pre>
 */
public class ScreenAdaptActivity extends BaseActivity {

    private TextView tvUp;
    private TextView tvDown;

    public static void start(Context context) {
        Intent starter = new Intent(context, ScreenAdaptActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        if (ScreenUtils.isPortrait()) {
            ScreenUtils.adaptScreen4VerticalSlide(this, 720);
        } else {
            ScreenUtils.setFullScreen(this);
            ScreenUtils.adaptScreen4HorizontalSlide(this, 720);
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_screen_adapt;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        if (ScreenUtils.isPortrait()) {
            findViewById(R.id.btn_show_system_toast).setOnClickListener(this);
            findViewById(R.id.btn_show_util_toast).setOnClickListener(this);
            findViewById(R.id.btn_system_dialog).setOnClickListener(this);
            findViewById(R.id.btn_system_dialog_without_adapt).setOnClickListener(this);
        }
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        if(view.getId()==R.id.btn_show_system_toast) {
            Toast.makeText(this, "System Toast", Toast.LENGTH_SHORT).show();
        }else if(view.getId()==R.id.btn_show_util_toast){
            ToastUtils.showShort("Util Toast");
        }else if(view.getId()==R.id.btn_system_dialog){
            DialogHelper.showAdaptScreenDialog();
        }else if(view.getId()==R.id.btn_system_dialog_without_adapt){
            ScreenUtils.cancelAdaptScreen(this);
            DialogHelper.showAdaptScreenDialog();
            ScreenUtils.adaptScreen4VerticalSlide(this, 720);
        }
    }

    @Override
    protected void onDestroy() {
//        ScreenUtils.cancelAdaptScreen(this);
        super.onDestroy();
    }
}
