package com.lchli.test.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lchli.jsbridgeweb.DefaultBridgeWebActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //WebActivity.start(this, "http://192.168.1.3:3000/");
       WebActivity.start(this, "https://lchli.github.io");
    }
}