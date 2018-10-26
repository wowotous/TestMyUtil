package com.test.testmyutil;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.chtj.util.nicespinner.NiceSpinner;
import com.chtj.util.screen.base.BaseActivity;
import com.chtj.util.screen.base.BasePermissionsActivity;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;

import java.util.List;

/**
 * Created by Administrator on 2018/10/24.
 *  需要使用蓝牙方案 请引用：compile 'com.clj.fastble:FastBleLib:2.3.2'
 */

public class BlueActivity extends BasePermissionsActivity {
    Button btn_scan,btn_diaconnect;
    EditText et_result;
    BleManager bleManager;
    NiceSpinner niceSpinner;

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_blue;
    }

    private final int PERMISSION_REQUEST_CODE = 1000;

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        requestPermission(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSION_REQUEST_CODE);

        btn_scan=contentView.findViewById(R.id.btn_scan);
        et_result=contentView.findViewById(R.id.et_result);
        btn_diaconnect=contentView.findViewById(R.id.btn_diaconnect);
        niceSpinner=contentView.findViewById(R.id.tv_value);
        btn_diaconnect.setOnClickListener(this);
        btn_scan.setOnClickListener(this);
        bleManager=BleManager.getInstance()
                .enableLog(true)
                .setReConnectCount(1, 5000)
                .setSplitWriteNum(20)
                .setConnectOverTime(10000)
                .setOperateTimeout(5000);


    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()){
            case R.id.btn_scan:
                bleManager.scan(new BleScanCallback() {
                    @Override
                    public void onScanFinished(List<BleDevice> scanResultList) {
                        Log.e("SCAN>>>",""+scanResultList.size());
                        et_result.append("onScanFinished() scanResultList.size()="+scanResultList.size()+"\n\r");

                        /*niceSpinner.setAdapter(new ArrayAdapter<String>(this,
                                android.R.layout.simple_selectable_list_item, spinnerItem));*/
                    }

                    @Override
                    public void onScanStarted(boolean success) {
                        Log.e("SCAN>>>",""+success);
                        et_result.append("onScanStarted() success="+success+"\n\r");

                    }

                    @Override
                    public void onScanning(BleDevice bleDevice) {
                        Log.e("SCAN>>>","name="+bleDevice.getName()+",address="+bleDevice.getMac());
                        et_result.append("onScanning() name="+bleDevice.getName()+",address="+bleDevice.getMac()+"\n\r");

                    }
                });
                break;
            case R.id.btn_diaconnect:
                bleManager.destroy();
                break;
        }
    }
}
