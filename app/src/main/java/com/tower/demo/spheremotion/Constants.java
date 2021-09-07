package com.tower.demo.spheremotion;

/**
 * Constants
 *
 * @author zpsong-tower <pingzisong2012@gmail.com>
 * @since 2021/9/6 21:15
 */
public class Constants {
    /**
     * 每个小球在第几个撞击点停止
     */
    public static final int[] NUMS = {
            1, 3, 4, 6, 7, 9
    };

    /**
     * 每个小球运动所花费的时间
     */
    public static final long[] TIMES = {
            1000, 2000, 3000, 4000, 4000, 4000
    };

    /**
     * 判断常量数组是否合法
     * 碰撞次数不能小于1且不能大于9，运动耗时不能小于0
     *
     * @return 配置的常量是否合法
     */
    public static boolean isAvailable() {
        if (NUMS.length < 0 || NUMS.length != TIMES.length) {
            return false;
        }
        for (int i = 0; i < NUMS.length; i++) {
            if (NUMS[i] < 1 || NUMS[i] > 9 || TIMES[i] < 0) {
                return false;
            }
        }
        return true;
    }
}
