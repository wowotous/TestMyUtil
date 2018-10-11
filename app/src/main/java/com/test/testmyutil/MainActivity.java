package com.test.testmyutil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.chtj.util.gson.GsonUtil;
import com.chtj.util.nicespinner.NiceSpinner;
import com.chtj.util.webservice.WebServiceUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    NiceSpinner niceSpinner;
    String[] spinnerItem=new String[]{"teacher","student","kids","women"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
