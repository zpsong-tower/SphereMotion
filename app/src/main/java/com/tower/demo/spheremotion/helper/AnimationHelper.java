package com.tower.demo.spheremotion.helper;

import android.animation.Animator;
import android.view.View;

import com.tower.demo.spheremotion.Constants;
import com.tower.demo.spheremotion.base.EndAnimatorListener;
import com.tower.demo.spheremotion.base.IAnimatedViewGroup;

import java.util.List;

/**
 * AnimationHelper
 *
 * @author zpsong-tower <pingzisong2012@gmail.com>
 * @since 2021/9/6 22:24
 */
public class AnimationHelper {
    // 动画背景
    private static IAnimatedViewGroup mViewGroup;

    // 运动View集合
    private static List<? extends View> mViews;

    // 每次碰撞X轴上所需平移的距离
    private static float mDistanceX;

    // 每次碰撞Y轴上所需平移的距离
    private static float mDistanceY;

    /**
     * 动画初始化
     *
     * @param viewGroup 背景
     * @param views     运动View集合
     */
    public static void init(IAnimatedViewGroup viewGroup, List<? extends View> views) {
        mViewGroup = viewGroup;
        mViews = views;
        if (mViewGroup == null || mViews == null || mViews.size() <= 0) {
            return;
        }
        mDistanceX = mViewGroup.getDistanceX();
        mDistanceY = mViewGroup.getDistanceY();
        for (int i = 0; i < mViews.size(); i++) {
            View view = mViews.get(i);
            if (view == null) {
                continue;
            }
            view.animate()
                    .setDuration(Constants.TIMES[i] / Constants.NUMS[i])
                    .setListener(new MyListener(i, Constants.NUMS[i]));
        }
    }

    /**
     * 动画开始
     */
    public static void start() {
        if (mViewGroup == null || mViews == null || mViews.size() <= 0) {
            return;
        }
        View view = mViews.get(0);
        if (view == null) {
            return;
        }
        view.setVisibility(View.VISIBLE);
        view.animate()
                .translationX(mDistanceX)
                .translationY(-mDistanceY)
                .start();
    }

    /**
     * 每个动画View需要绑定的监听
     */
    private static class MyListener extends EndAnimatorListener {
        // 球的序号
        private int mIndex;

        // 需要碰撞的次数
        private int mCount;

        // 已经碰撞的次数
        private int mProgress = 0;

        // 下次撞击要到达的X轴坐标
        private float mReachX = mDistanceX;

        /**
         * 构造方法
         *
         * @param index 球的序号
         * @param count 需要碰撞的次数
         */
        public MyListener(int index, int count) {
            this.mIndex = index;
            this.mCount = count;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            mProgress++;
            if (mProgress == 1 && mIndex < mViews.size() - 1) {
                // 播放下一个View的首次撞击动画
                View nextView = mViews.get(mIndex + 1);
                if (nextView != null) {
                    nextView.setVisibility(View.VISIBLE);
                    nextView.animate()
                            .translationX(mDistanceX)
                            .translationY(-mDistanceY).start();
                }
            }
            if (mProgress == mCount) {
                // 当前View动画已全部完成
                return;
            }

            // 计算下次撞击要到达的坐标
            mReachX += mDistanceX;
            float reachY;
            if (mProgress % 2 == 0) {
                // 偶数次撞击后往上
                reachY = -mDistanceY;
            } else {
                // 奇数次撞击后往下
                reachY = 0;
            }

            View view = mViews.get(mIndex);
            if (view != null) {
                // 播放当前View的下一步动画
                view.animate()
                        .translationX(mReachX)
                        .translationY(reachY)
                        .start();
            }
        }
    }
}
