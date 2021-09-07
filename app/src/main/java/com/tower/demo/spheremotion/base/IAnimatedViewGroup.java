package com.tower.demo.spheremotion.base;

/**
 * IAnimated
 *
 * @author zpsong-tower <pingzisong2012@gmail.com>
 * @since 2021/9/6 22:23
 */
public interface IAnimatedViewGroup {
    /**
     * 动画每次碰撞X轴上所需平移的距离
     *
     * @return float X
     */
    float getDistanceX();

    /**
     * 动画每次碰撞Y轴上所需平移的距离
     *
     * @return float Y
     */
    float getDistanceY();
}
