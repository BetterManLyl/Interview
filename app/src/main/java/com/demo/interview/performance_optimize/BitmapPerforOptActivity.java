package com.demo.interview.performance_optimize;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;

/**
 * 文 件 名：BitmapPerforOptActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/24 10:23
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：性能优化 之 Bitmap优化
 * 参考博客
 * https://www.jianshu.com/p/7643c6aadb53
 * <p>
 * 优化方案：
 * 1、使用完毕后 释放图片资源
 * 2、根据分辨率适配 & 缩放图片
 * 3、设置 图片缓存
 * 4、按需 选择合适的解码方式
 */
public class BitmapPerforOptActivity extends BaseActivity {

    private ImageView imageView;
    private Button btn_show;
    private TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bitmap_perfor_opt;
    }

    @Override
    public void initView() {
        imageView = findViewById(R.id.iv);
        btn_show = findViewById(R.id.btn_show);
        textView=findViewById(R.id.tv_result);
        btn_show.setOnClickListener(v -> recycler_bitmap());
        findViewById(R.id.btn_show_bitmap).setOnClickListener(v -> efficBitmap());
    }

    @Override
    public void initData() {

    }


    /**
     * 1、使用完毕后 释放图片资源
     * <p>
     * 优化原因
     * 使用完毕后若不释放图片资源，容易造成内存泄露，从而导致内存溢出
     * <p>
     * 优化方案
     * a. 在 Android2.3.3（API 10）前，调用 Bitmap.recycle()方法
     * b. 在 Android2.3.3（API 10）后，采用软引用（SoftReference）
     * 注：若调用了Bitmap.recycle()后，再绘制Bitmap，
     * 则会出现Canvas: trying to use a recycled bitmap错误
     */
    private void recycler_bitmap() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        imageView.setImageBitmap(bitmap);
        LogUtils.d("bitmap size:" + bitmap.getByteCount());
        textView.setText("图片大小："+bitmap.getByteCount());
        //出现异常 trying to use a recycled bitmap android.graphics.Bitmap@dabaa2c
//        if (!bitmap.isRecycled())
//            bitmap.recycle();

    }

    private void efficBitmap() {
        Bitmap bitmap = decodeSampledBitmapFromResource(getResources(),
                R.mipmap.ic_launcher, 50, 50);
        LogUtils.d("bitmap size:" + bitmap.getByteCount());
        textView.setText("图片大小："+bitmap.getByteCount());
        imageView.setImageBitmap(bitmap);
    }

    /**
     * 高效加载Bitmap
     * <p>
     * 那么如何能先不加载图片却能获得图片的宽高信息，通过inJustDecodeBounds=true，
     * 然后加载图片就可以实现只解析图片的宽高信息，并不会真正的加载图片，
     * <p>
     * 当获取了宽高信息，计算出缩放比后，然后在将inJustDecodeBounds=false,再重新加载图片，
     * 就可以加载缩放后的图片。
     *
     * @param resources
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static Bitmap decodeSampledBitmapFromResource(Resources resources, int resId,
                                                          int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, resId, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        LogUtils.d("inSampleSize:" + inSampleSize);
        return inSampleSize;
    }

}
