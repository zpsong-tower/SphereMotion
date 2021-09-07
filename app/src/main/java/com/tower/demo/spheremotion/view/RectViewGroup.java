package com.tower.demo.spheremotion.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

import com.tower.demo.spheremotion.base.IAnimatedViewGroup;
import com.tower.demo.spheremotion.R;
import com.tower.demo.spheremotion.helper.ScreenHelper;

/**
 * RectViewGroup
 *
 * @author zpsong-tower <pingzisong2012@gmail.com>
 * @since 2021/9/6 21:04
 */
public class RectViewGroup extends ViewGroup implements IAnimatedViewGroup {
    // 上下文
    private Context mContext;

    // 矩形宽度
    private int mRectWidth;

    // 矩形高度
    private int mRectHeight;

    // 内边距
    private int mPadding;

    // 线条画笔
    private Paint mLinePaint;

    // 每条线在X轴上的距离
    private float mSingleX;

    // 线条坐标数组 9段线 每段线4个坐标
    private float[] mPoints = new float[36];

    // 是否需要绘制线条
    private boolean mIsNeedDraw = false;

    public RectViewGroup(Context context) {
        super(context);
        init(context);
    }

    public RectViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RectViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RectViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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

        // 背景色
        int color = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                ? context.getResources().getColor(R.color.colorAccent, null)
                : context.getResources().getColor(R.color.colorAccent);
        setBackgroundColor(color);

        // 矩形宽高
        mRectWidth = ScreenHelper.getScreenWidth(context);
        mRectHeight = (int) (mRectWidth * 0.35);

        // 计算内边距
        mPadding = mRectWidth / 40;

        // 线条画笔
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(Color.WHITE);

        // 线条坐标
        mSingleX = (mRectWidth - mPadding * 2) / (float) 9;
        float topY = mPadding;
        float bottomY = mRectHeight - mPadding;
        mPoints[0] = mPadding;
        mPoints[1] = bottomY;
        float allX = mPoints[0];
        boolean isTop = true;
        for (int i = 2; i < 31; i += 4) {
            mPoints[i] = mPoints[i + 2] = allX += mSingleX;
            mPoints[i + 1] = mPoints[i + 3] = isTop ? topY : bottomY;
            isTop = !isTop;
        }
        mPoints[34] = allX + mSingleX;
        mPoints[35] = topY;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.makeMeasureSpec(mRectWidth, MeasureSpec.EXACTLY);
        int height = MeasureSpec.makeMeasureSpec(mRectHeight, MeasureSpec.AT_MOST);
        setMeasuredDimension(width, height);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View child;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            if (child instanceof SphereView) {
                // 将球体初始化在左下角
                child.layout(0, mRectHeight - child.getMeasuredHeight(),
                        child.getMeasuredWidth(), mRectHeight);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mIsNeedDraw) {
            // 画线条
            canvas.drawLines(mPoints, mLinePaint);
        }
    }

    public void drawLine() {
        mIsNeedDraw = true;
        invalidate();
    }

    @Override
    public float getDistanceX() {
        return mSingleX;
    }

    @Override
    public float getDistanceY() {
        return mRectHeight - mPadding * 2;
    }
}
