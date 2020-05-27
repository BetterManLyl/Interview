package com.demo.interview.widget.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.R;

import androidx.annotation.Nullable;

/**
 * 文 件 名：MyTextView
 * 创 建 人：李跃龙
 * 创建日期：2020/5/27 8:48
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class MyCustomView extends View {

    

    private Paint paint;

    private Rect rect;

    private String txt = "你好啊！";

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView);

        String text = typedArray.getString(R.styleable.MyCustomView_mText);
        int size = typedArray.getDimensionPixelSize(R.styleable.MyCustomView_mSize, 20);
        int textColor = typedArray.getColor(R.styleable.MyCustomView_mTextColor, Color.BLACK);
//        txt = text;
        LogUtils.d("text:" + text + " size:" + size + " textColor:" + textColor);

        LogUtils.d("MyTextView");
        paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(size);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LogUtils.d("onMeasure:" + widthMeasureSpec + " heightMeasureSpec:" + heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int mode_width = MeasureSpec.getMode(widthMeasureSpec);
        int mode_height = MeasureSpec.getMode(heightMeasureSpec);
        int size_width = MeasureSpec.getSize(widthMeasureSpec);
        int size_height = MeasureSpec.getSize(heightMeasureSpec);

        LogUtils.d("mode_width:" + mode_width
                + " mode_height:" + mode_height
                + " size_width:" + size_width
                + " size_height:" + size_height);
        int width;
        int height;
        rect = new Rect();
        paint.getTextBounds(txt, 0, txt.length(), rect);
        if (mode_width == MeasureSpec.EXACTLY) {
            //如果match_parent或者具体的值，直接赋值
            width = size_width;
        } else {
            //如果是wrap_content，我们要得到控件需要多大的尺寸
            float textWidth = rect.width();   //文本的宽度
            //控件的宽度就是文本的宽度加上两边的内边距。内边距就是padding值，在构造方法执行完就被赋值
            width = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            LogUtils.d("文本的宽度:" + textWidth + "控件的宽度：" + width);
        }
        //高度跟宽度处理方式一样
        if (mode_height == MeasureSpec.EXACTLY) {
            height = size_height;
        } else {
            float textHeight = rect.height();
            height = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            LogUtils.d("文本的高度:" + textHeight + " 控件的高度：" + height);
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed,left,top,right,bottom);
        LogUtils.d("onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        LogUtils.d("onDraw");
        LogUtils.d("getWidth:" + getWidth() + " getHeight：" + getHeight());
        //文字居中显示

        canvas.drawText(txt, getWidth() / 2 - rect.width() / 2, getHeight() / 2 + rect.height() / 2, paint);
        RectF rectF = new RectF();
        rectF.bottom = 500;
        rectF.left = 100;
        rectF.right = 500;
        rectF.top = 100;

//      canvas.drawRect(rectF, paint);
    }

    /**
     * 设置文本
     *
     * @param text
     */
    public void setTxt(String text) {
        this.txt = text;
        //invalidate和postInvalidate只会调用onDraw()方法
        //必须在主线程调用
//        invalidate();
        //可在子线程调用
//        postInvalidate();
        //requestLayout会调onMeasure、onLayout和onDraw(特定条件下)方法
        requestLayout();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.d("onTouchEvent");
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                LogUtils.d("onTouchEvent ACTION_DOWN");
//                break;
//            case MotionEvent.ACTION_UP:
//                LogUtils.d("onTouchEvent ACTION_UP");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                LogUtils.d("onTouchEvent ACTION_MOVE");
//                break;
//        }
        
//        return true;
        return super.onTouchEvent(event);
    }
    

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtils.d("dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    //ViewGroup特有方法
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
//    }
}
