package com.demo.interview.widget.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.SeekBar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatSeekBar;

import com.demo.interview.R;

/**
 * Created by wubo on 2020/7/24.
 */
public class DragZoomInSeekBar extends AppCompatSeekBar implements SeekBar.OnSeekBarChangeListener {

    private static final String TAG = "DragZoomInSeekBar";

    private ShapeDrawable mNormalShapeDrawable;
    private ShapeDrawable mZoomInShapeDrawable;

    private int mNormalThumbWidth;
    private int mZoomInThumbWidth;
    private int mMaxHeight;
    private Drawable mSeekBarNormalBg;
    private Drawable mSeekBarZoomBg;


    public DragZoomInSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, R.style.Widget_SeekBar_Normal2);
    }


    public DragZoomInSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDrawable();
        initView();
        this.setOnSeekBarChangeListener(this);
    }

    private void initView() {
        mMaxHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics());
        //Drawable indeterminateDrawable = getResources().getDrawable(R.drawable.progress_indeterminate_horizontal_bg);
        //setIndeterminateDrawable(indeterminateDrawable);
        // setIndeterminate(false);
        setProgressDrawable(mSeekBarNormalBg);
        setThumb(mNormalShapeDrawable);
        setPadding(0,0,0,0);
        setThumbOffset(0);
    }


    private void initDrawable() {
        mNormalThumbWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getResources().getDisplayMetrics());
        mZoomInThumbWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());

      

        mNormalShapeDrawable = new ShapeDrawable();
        Shape normalThumbShape = new Shape() {
            @Override
            public void draw(Canvas canvas, Paint paint) {
                paint.setColor(Color.parseColor("#4285F6"));
                canvas.drawCircle(mNormalThumbWidth / 2, mNormalThumbWidth / 2, mNormalThumbWidth / 2, paint);
            }
        };
        mNormalShapeDrawable.setIntrinsicWidth((int) mNormalThumbWidth);
        mNormalShapeDrawable.setIntrinsicHeight((int) mNormalThumbWidth);
        mNormalShapeDrawable.setShape(normalThumbShape);

        mZoomInShapeDrawable = new ShapeDrawable();
        Shape zoomInThumbShape = new Shape() {
            @Override
            public void draw(Canvas canvas, Paint paint) {
                paint.setColor(Color.parseColor("#4285F6"));
                canvas.drawCircle(mZoomInThumbWidth / 2, mZoomInThumbWidth / 2, mZoomInThumbWidth / 2, paint);
            }
        };
        mZoomInShapeDrawable.setIntrinsicWidth((int) mZoomInThumbWidth);
        mZoomInShapeDrawable.setIntrinsicHeight((int) mZoomInThumbWidth);
        mZoomInShapeDrawable.setShape(zoomInThumbShape);

        mSeekBarNormalBg = getResources().getDrawable(R.drawable.seekbar_horizontal_normal_bg);
        mSeekBarZoomBg = getResources().getDrawable(R.drawable.seekbar_horizontal_zoom_bg);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (null != seekBarListener) seekBarListener.onProgressChanged(seekBar, progress, fromUser);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        seekBar.setThumb(mZoomInShapeDrawable);
        setProgressDrawable(mSeekBarZoomBg);
        setPadding(0,0,0,0);
        setThumbOffset(0);
        if (null != seekBarListener) seekBarListener.onStartTrackingTouch(seekBar);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        seekBar.setThumb(mNormalShapeDrawable);
        setProgressDrawable(mSeekBarNormalBg);
        setPadding(0,0,0,0);
        setThumbOffset(0);
        //setMaxHeight(mNormalMaxHeight);
        if (null != seekBarListener) seekBarListener.onStopTrackingTouch(seekBar);
    }

    private SeekBarListener seekBarListener;

    public void setSeekBarListener(SeekBarListener seekBarListener) {
        this.seekBarListener = seekBarListener;
    }

    public interface SeekBarListener {
        void onStartTrackingTouch(SeekBar seekBar);

        void onStopTrackingTouch(SeekBar seekBar);

        void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser);
    }
}
