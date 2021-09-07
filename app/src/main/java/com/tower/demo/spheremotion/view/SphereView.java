package com.tower.demo.spheremotion.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.tower.demo.spheremotion.helper.ScreenHelper;

/**
 * SphereView
 *
 * @author zpsong-tower <pingzisong2012@gmail.com>
 * @since 2021/9/6 21:06
 */
public class SphereView extends View {
    // 屏幕宽度
    private int mScreenWidth;

    // 圆形半径
    private int mRadius;

    // 圆形画笔
    private Paint mCirclePaint;

    // 文本画笔
    private Paint mTextPaint;

    // 文本基线坐标
    private float mTextY;

    // 文本内容
    private String mTextContent = "";

    private SphereView(Context context) {
        super(context);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context 上下文
     */
    private void init(Context context) {
        if (context == null) {
            return;
        }

        // 不可见
        setVisibility(INVISIBLE);

        // 屏幕宽度
        mScreenWidth = ScreenHelper.getScreenWidth(context);

        // 计算该View的基本像素单位 (圆形半径)
        mRadius = mScreenWidth / 40;

        // 画圆形的画笔 抗锯齿 红色
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.RED);

        // 画文本的画笔 抗锯齿 白色 半径大小 文本居中
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(mRadius);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        // 初始化文本基线坐标
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextY = mRadius + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 测量值为两倍半径
        int length = MeasureSpec.makeMeasureSpec(mRadius * 2, MeasureSpec.AT_MOST);
        setMeasuredDimension(length, length);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 画圆形
        canvas.drawCircle(mRadius, mRadius, mRadius, mCirclePaint);

        // 画文本
        canvas.drawText(mTextContent, mRadius, mTextY, mTextPaint);
    }

    /**
     * 建造者模式
     */
    public static class Builder {
        private SphereView mSphereView;

        public Builder(Context context) {
            this.mSphereView = new SphereView(context);
        }

        /**
         * 设置圆形序号
         *
         * @param number 序号
         * @return Builder
         */
        public Builder setNumber(int number) {
            this.mSphereView.mTextContent = String.valueOf(number);
            return this;
        }

        /**
         * 返回设置好的SphereView
         *
         * @return SphereView
         */
        public SphereView build() {
            return this.mSphereView;
        }
    }
}
