package com.demo.interview.kotlin

import com.demo.interview.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.reflect.Array
import kotlin.Array as Array1

/**
 * 文 件 名：KotlinTest
 * 创 建 人：李跃龙
 * 创建日期：2020/6/9 10:43
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
class KotlinTest: BaseActivity() {
    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val num: Int = 3
    val isss: Boolean = false
    var age: String? = "test"


    fun add(name: String, age: Int): Int {
        return age
    }

    fun sum(a: Int, b: Int): Int {
        return a + b
    }

}