package com.example.testing.testingexample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wt on 17/4/21.
 */

public class WaveBubbleView
        extends View
{
    private final static int DEFAULT_WIDTH = 200;
    private final static int DEFAULT_HEIGHT = 200;
    private final static int DEFAULT_WAVE_COLOR = 0x30000088;
    private final static int DEFAULT_BUBBLE_COLOR = 0x10000044;
    private final static float DEFAULT_WAVE_LEVEL = 0.5F;
    private final static float DEFAULT_WAVE_RANGE = 0.1F;
    private final static int DEFAULT_WAVE_FREQUENCY = 2;

    private int mWaveColor[];
    private int mBubbleColor[];
    private int mWidth, mHeight;
    private Paint mPaint;
    private int mWaveSpeed = 0, mBubbleSpeed = 0;
    private float mWaveLevel = DEFAULT_WAVE_LEVEL;
    private int mBackgroundColor = Color.TRANSPARENT;
    private double mWaveLength = 4 * Math.PI;
    private Path mWavePath = new Path();
    private float mWaveRange = DEFAULT_WAVE_RANGE;
    private int mWaveFrequency = DEFAULT_WAVE_FREQUENCY;

    public WaveBubbleView(Context context)
    {
        this(context, null);
    }

    public WaveBubbleView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public WaveBubbleView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        mWaveColor = new int[]{DEFAULT_WAVE_COLOR};
        mBubbleColor = new int[]{DEFAULT_BUBBLE_COLOR};
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (widthMode)
        {
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.UNSPECIFIED:
                setMinimumWidth(DEFAULT_WIDTH);
                mWidth = DEFAULT_WIDTH;
                break;
            default:
                break;
        }

        switch (heightMode)
        {
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.UNSPECIFIED:
                setMinimumHeight(DEFAULT_HEIGHT);
                mHeight = DEFAULT_HEIGHT;
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        // draw background
        if (getBackground() != null)
            getBackground().draw(canvas);
        // draw the first wave
        mPaint.setColor(mWaveColor[0]);
        mPaint.setStyle(Paint.Style.FILL);
        setWavePath(0);
        canvas.drawPath(mWavePath, mPaint);
    }

    /**
     * @param offset the offset of wave curve
     */
    private void setWavePath(float offset)
    {
        mWavePath.reset();
        int x = 0, y = 0;
        for (int i = 0; i < getWidth(); i++)
        {
            x = i;
            y = (int) (mWaveRange * getHeight() * Math.sin(getWidth() / 2.0D / Math.PI * (x + offset)) / (2.0D - mWaveLevel));
            if (i == 0)
                mWavePath.moveTo(x, y);
            mWavePath.quadTo(x, y, x + 1, y);
        }
        mWavePath.lineTo(getWidth(), getHeight());
        mWavePath.lineTo(0, getHeight());
        mWavePath.close();
    }
}
