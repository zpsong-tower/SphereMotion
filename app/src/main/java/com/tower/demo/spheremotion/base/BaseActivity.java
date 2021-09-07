package com.tower.demo.spheremotion.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * BaseActivity
 *
 * @author zpsong-tower <pingzisong2012@gmail.com>
 * @since 2021/9/6 21:03
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (initArgs(getIntent().getExtras())) {
            View root = initBinding();
            setContentView(root);
            initWidget();
            initData();
        } else {
            finish();
        }
    }

    /**
     * 初始化相关参数
     *
     * @param bundle 参数Bundle
     * @return 如果参数正确返回True
     */
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    /**
     * 初始化视图绑定
     *
     * @return 当前界面根布局View
     */
    @NonNull
    protected abstract View initBinding();

    /**
     * 初始化控件
     */
    protected void initWidget() {
    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }
}
