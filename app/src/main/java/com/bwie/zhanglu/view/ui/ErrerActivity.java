package com.bwie.zhanglu.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;


@ContentView(R.layout.activity_errer)
public class ErrerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
}
