package com.test.testmyutil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.chtj.util.nicespinner.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    NiceSpinner niceSpinner;
    String[] spinnerItem=new String[]{"teacher","student","kids","women"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        niceSpinner=findViewById(R.id.tv_value);
        niceSpinner.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item, spinnerItem));


    }
}
