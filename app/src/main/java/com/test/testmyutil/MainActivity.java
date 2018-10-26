package com.test.testmyutil;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.chtj.util.gson.GsonUtil;
import com.chtj.util.nicespinner.NiceSpinner;
import com.chtj.util.screen.base.BasePermissionsActivity;
import com.chtj.util.screen.util.ToastUtils;

import java.util.List;

public class MainActivity extends BasePermissionsActivity {
    NiceSpinner niceSpinner;
    Button btn_load,btn_permissions,btn_blue;
    String[] spinnerItem=new String[]{"teacher","student","kids","women"};
    private final int PERMISSION_REQUEST_CODE = 1000;



    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        btn_load=contentView.findViewById(R.id.btn_load);
        btn_load.setOnClickListener(this);
        btn_permissions=contentView.findViewById(R.id.btn_permissions);
        btn_blue=contentView.findViewById(R.id.btn_blue);
        btn_blue.setOnClickListener(this);
        btn_permissions.setOnClickListener(this);
        /**__下拉菜单 __**/
        niceSpinner=findViewById(R.id.tv_value);
        niceSpinner.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item, spinnerItem));
        /**__ JSON转换 __**/
        String jsonStr="[{\"headImage\":\"back_1.png\",\"id\":\"1\",\"introduction\":\"my name is chtj...\",\"name\":\"chtj\",\"nickName\":\"chtj\",\"phone\":\"136-5241-2606\",\"pwd\":\"123\"},{\"headImage\":\"back_1.png\",\"id\":\"2\",\"introduction\":\"my name is ctj...\",\"name\":\"ctj\",\"nickName\":\"ctj\",\"phone\":\"136-5241-2606\",\"pwd\":\"123\"}]";
        List<userinfo> userinfoList =GsonUtil.jsonToArray(jsonStr,userinfo[].class);
        Log.e("userinfoList>>>","json="+jsonStr);
        Log.e("userinfoList>>>","List>>> id 1="+userinfoList.get(0).getId()+", name 1="+userinfoList.get(0).getNickName());
        Log.e("userinfoList>>>","List>>> id 2="+userinfoList.get(1).getId()+", name 2="+userinfoList.get(1).getNickName());

        String jsonBeanStr="{\"headImage\":\"back_1.png\",\"id\":\"3\",\"introduction\":\"my name is zs...\",\"name\":\"zs\",\"nickName\":\"zs\",\"phone\":\"136-5241-2606\",\"pwd\":\"123\"}";
        userinfo userinfos=GsonUtil.getBean(jsonBeanStr,userinfo.class);
        Log.e("userinfo>>>","json="+jsonBeanStr);
        Log.e("userinfo>>>","Bean >>> id="+userinfos.getId()+" ,name="+userinfos.getNickName());


    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()){
            case R.id.btn_load:
            showDialog();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    closeDialog();
                    ToastUtils.showShort("调用closeDialog()关闭");
                }
            },1500);
                break;
            case R.id.btn_permissions:
                requestPermission(new String[]{Manifest.permission.CAMERA},PERMISSION_REQUEST_CODE);
                break;
            case R.id.btn_blue:
                startActivity(new Intent(MainActivity.this,BlueActivity.class));
                break;
        }
    }



}
