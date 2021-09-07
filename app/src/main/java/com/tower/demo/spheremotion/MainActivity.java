package com.tower.demo.spheremotion;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.tower.demo.spheremotion.base.BaseActivity;
import com.tower.demo.spheremotion.databinding.ActivityMainBinding;
import com.tower.demo.spheremotion.helper.AnimationHelper;
import com.tower.demo.spheremotion.view.SphereView;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity
 *
 * @author zpsong-tower <pingzisong2012@gmail.com>
 * @since 2021/9/6 21:03
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getName();

    private List<SphereView> mSphereViews = new ArrayList<>();

    private ActivityMainBinding mBinding;

    @Override
    protected boolean initArgs(Bundle bundle) {
        return Constants.isAvailable() && super.initArgs(bundle);
    }

    @NonNull
    @Override
    protected View initBinding() {
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        return mBinding.getRoot();
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        // 初始化球体
        ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        SphereView sphereView;
        for (int i = 0; i < Constants.NUMS.length; i++) {
            sphereView = new SphereView.Builder(this)
                    .setNumber(i + 1)
                    .build();
            mSphereViews.add(sphereView);
            mBinding.vgRect.addView(sphereView, layoutParams);
        }

        // 初始化点击监听
        mBinding.btnBottom.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        AnimationHelper.init(mBinding.vgRect, mSphereViews);
    }

    private void onBottomClick() {
        Log.i(TAG, "onBottomClick");
        mBinding.btnBottom.setEnabled(false);
        mBinding.vgRect.drawLine();
        AnimationHelper.start();
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }
        int id = v.getId();
        if (id == mBinding.btnBottom.getId()) {
            // 底部按钮点击
            onBottomClick();
        } else {
            Log.w(TAG, "onClick: illegal param: " + id);
        }
    }
}