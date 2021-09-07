package com.tower.demo.spheremotion.base;

import android.animation.Animator;

/**
 * EndAnimatorListener
 *
 * @author zpsong-tower <pingzisong2012@gmail.com>
 * @since 2021/9/6 22:34
 */
public abstract class EndAnimatorListener implements Animator.AnimatorListener {
    @Override
    public void onAnimationStart(Animator animation) {
    }

    @Override
    public void onAnimationCancel(Animator animation) {
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
    }
}
