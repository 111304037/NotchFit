package com.wcl.notchfit.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wcl.notchfit.args.NotchProperty;
import com.wcl.notchfit.config.NotchConfig;
import com.wcl.notchfit.config.OnNotchPropertyListener;
import com.wcl.notchfit.utils.LogUtils;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotchConfig.NotchPropertyListener = new OnNotchPropertyListener() {
            @Override
            public void onNotchProperty(NotchProperty notchProperty) {
                LogUtils.i(notchProperty.toString());
            }
        };

        findViewById(R.id.btn_notch_translucent).setOnClickListener(this);
        findViewById(R.id.btn_notch_fullscreen).setOnClickListener(this);
        findViewById(R.id.btn_notch_custom).setOnClickListener(this);

        findViewById(R.id.btn_notch_translucent_unuse).setOnClickListener(this);
        findViewById(R.id.btn_notch_fullscreen_unuse).setOnClickListener(this);

        try {
            Class<?> androidId = Class.forName(getPackageName() + ".R$id");
            Field decor_content_parent = androidId.getField("decor_content_parent");
            decor_content_parent.setAccessible(true);
            int resId = (int) decor_content_parent.get(null);
            Log.i("aa", resId+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_notch_translucent:
                startActivity(new Intent(this, NotchTranslucentActivity.class));
                break;
            case R.id.btn_notch_translucent_unuse:
                startActivity(new Intent(this, UnUseNotchTranslucentActivity.class));
                break;
            case R.id.btn_notch_custom:
                startActivity(new Intent(this, NotchCustomActivity.class));
                break;
            case R.id.btn_notch_fullscreen:
                startActivity(new Intent(this, NotchFullScreenActivity.class));
                break;
            case R.id.btn_notch_fullscreen_unuse:
                startActivity(new Intent(this, UnUseNotchFullScreenActivity.class));
                break;
        }
    }
}
