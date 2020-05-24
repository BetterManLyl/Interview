package com.demo.interview.performance_optimize;

import com.demo.interview.base.BaseActivity;

/**
 * 文 件 名：LayoutOptActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/24 13:53
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 * <p>
 * 布局优化
 * https://www.jianshu.com/p/4e665e96b590
 * <p>
 * 优化方向：布局性能、布局层级、布局复用性 和 测量 & 绘制时间
 * <p>
 * 性能耗费低的布局 = 功能简单 =  FrameLayout、LinearLayout
 * 性能耗费高的布局 = 功能复杂 =  RelativeLayout
 * <p>
 * 注：
 * 1、选择 耗费性能较少的布局
 * 性能耗费低的布局 = 功能简单 =  FrameLayout、LinearLayout
 * 性能耗费高的布局 = 功能复杂 =  RelativeLayout
 * 即 布局过程需消耗更多性能（CPU资源 & 时间）
 * 嵌套所耗费的性能 > 单个布局本身耗费的性能
 * 即 完成需求时：宁选择 1个耗费性能高的布局，也不采用嵌套多个耗费性能低的布局
 * 2 、减少布局的层级（嵌套）
 * 使用<merge></merge> 标签
 * 合适选择布局类型
 * 通过合理选择布局类型，从而减少嵌套
 * 即：完成 复杂的UI效果时，尽可能选择1个功能复杂的布局（如RelativeLayout）完成，
 * 而不要选择多个功能简单的布局（如LinerLayout）通过嵌套完成
 * <p>
 * 3、 提高 布局 的复用性
 * 使用<include></include>
 * 4、减少初次测量 & 绘制时间
 * 使用 布局标签<ViewStub>
 * 尽可能少用布局属性 wrap_content
 * 5. 布局调优工具
 * Hierarchy Viewer
 * Lint
 * Systrace
 */
public class LayoutOptActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
