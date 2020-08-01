package com.demo.interview.animation;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;

/**
 * 文 件 名：AnimationActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/22 11:21
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：动画
 */
public class AnimationActivity extends BaseActivity {

    private Button btn_tween_animation;


    @Override
    public int getLayoutId() {
        return R.layout.activity_tween_animation;
    }

    @Override
    public void initView() {
        // 步骤1:创建 需要设置动画的 视图View
        btn_tween_animation = findViewById(R.id.btn_translate);
        btn_tween_animation.setOnClickListener(v -> {
//            // 步骤2:创建 动画对象 并传入设置的动画效果xml文件
//            Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.animation);
//            // 步骤3:播放动画
//            btn_tween_animation.startAnimation(animation);
            translateAn();
        });

        findViewById(R.id.btn_alpha).setOnClickListener(v -> alphaAn());
        findViewById(R.id.btn_rotate).setOnClickListener(v -> rotateAn());
        findViewById(R.id.btn_scale).setOnClickListener(v -> scaleAn());

    }

    @Override
    public void initData() {

    }

    private void translateAn() {
        Button mButton = findViewById(R.id.btn_translate);
// 步骤1:创建 需要设置动画的 视图View
        Animation translateAnimation = new TranslateAnimation(0, 500, 0, 500);
// 步骤2：创建平移动画的对象：平移动画对应的Animation子类为TranslateAnimation
// 参数分别是：
// 1. fromXDelta ：视图在水平方向x 移动的起始值
// 2. toXDelta ：视图在水平方向x 移动的结束值
// 3. fromYDelta ：视图在竖直方向y 移动的起始值
// 4. toYDelta：视图在竖直方向y 移动的结束值
        translateAnimation.setDuration(3000);
// 固定属性的设置都是在其属性前加“set”，如setDuration（）
        mButton.startAnimation(translateAnimation);
// 步骤3:播放动画
    }

    private void scaleAn() {
        Button mButton = (Button) findViewById(R.id.btn_scale);
// 步骤1:创建 需要设置动画的 视图View

        Animation scaleAnimation = new ScaleAnimation(0, 2, 0, 2, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
// 步骤2：创建缩放动画的对象 & 设置动画效果：缩放动画对应的Animation子类为RotateAnimation
// 参数说明:
// 1. fromX ：动画在水平方向X的结束缩放倍数
// 2. toX ：动画在水平方向X的结束缩放倍数
// 3. fromY ：动画开始前在竖直方向Y的起始缩放倍数
// 4. toY：动画在竖直方向Y的结束缩放倍数
// 5. pivotXType:缩放轴点的x坐标的模式
// 6. pivotXValue:缩放轴点x坐标的相对值
// 7. pivotYType:缩放轴点的y坐标的模式
// 8. pivotYValue:缩放轴点y坐标的相对值

// pivotXType = Animation.ABSOLUTE:缩放轴点的x坐标 =  View左上角的原点 在x方向 加上 pivotXValue数值的点(y方向同理)
// pivotXType = Animation.RELATIVE_TO_SELF:缩放轴点的x坐标 = View左上角的原点 在x方向 加上 自身宽度乘上pivotXValue数值的值(y方向同理)
// pivotXType = Animation.RELATIVE_TO_PARENT:缩放轴点的x坐标 = View左上角的原点 在x方向 加上 父控件宽度乘上pivotXValue数值的值 (y方向同理)

        scaleAnimation.setDuration(3000);
// 固定属性的设置都是在其属性前加“set”，如setDuration（）

        mButton.startAnimation(scaleAnimation);
// 步骤3：播放动画
    }

    private void alphaAn() {
        Button mButton = findViewById(R.id.btn_alpha);
// 步骤1:创建 需要设置动画的 视图View

        Animation alphaAnimation = new AlphaAnimation(1, 0);
// 步骤2：创建透明度动画的对象 & 设置动画效果：透明度动画对应的Animation子类为AlphaAnimation
// 参数说明:
// 1. fromAlpha:动画开始时视图的透明度(取值范围: -1 ~ 1)
// 2. toAlpha:动画结束时视图的透明度(取值范围: -1 ~ 1)

        alphaAnimation.setDuration(3000);
// 固定属性的设置都是在其属性前加“set”，如setDuration（）

        mButton.startAnimation(alphaAnimation);
// 步骤3：播放动画     
    }


    private void rotateAn() {
        Button mButton = (Button) findViewById(R.id.btn_rotate);
// 步骤1:创建 需要设置动画的 视图View

        Animation rotateAnimation = new RotateAnimation(0, 270, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
// 步骤2：创建旋转动画的对象 & 设置动画效果：旋转动画对应的Animation子类为RotateAnimation
// 参数说明:
// 1. fromDegrees ：动画开始时 视图的旋转角度(正数 = 顺时针，负数 = 逆时针)
// 2. toDegrees ：动画结束时 视图的旋转角度(正数 = 顺时针，负数 = 逆时针)
// 3. pivotXType：旋转轴点的x坐标的模式
// 4. pivotXValue：旋转轴点x坐标的相对值
// 5. pivotYType：旋转轴点的y坐标的模式
// 6. pivotYValue：旋转轴点y坐标的相对值

// pivotXType = Animation.ABSOLUTE:旋转轴点的x坐标 =  View左上角的原点 在x方向 加上 pivotXValue数值的点(y方向同理)
// pivotXType = Animation.RELATIVE_TO_SELF:旋转轴点的x坐标 = View左上角的原点 在x方向 加上 自身宽度乘上pivotXValue数值的值(y方向同理)
// pivotXType = Animation.RELATIVE_TO_PARENT:旋转轴点的x坐标 = View左上角的原点 在x方向 加上 父控件宽度乘上pivotXValue数值的值 (y方向同理)

        rotateAnimation.setDuration(3000);
// 固定属性的设置都是在其属性前加“set”，如setDuration（）

        mButton.startAnimation(rotateAnimation);
// 步骤3：播放动画
    }
}
