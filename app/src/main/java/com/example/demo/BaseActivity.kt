package com.example.demo

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.kongzue.dialogx.dialogs.WaitDialog

/**
 *   @auther : Aleyn
 *   time   : 2019/11/01
 */
abstract class BaseActivity : AppCompatActivity {

    @LayoutRes
    private var mCurrentLayoutId: Int = 0
    private val TAG = "BaseMVVMActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        takeIf { mCurrentLayoutId != 0 }?.let {
            setContentView(mCurrentLayoutId)
        }

        initView(savedInstanceState)
        initData(savedInstanceState)
    }

    constructor() : super()
    constructor(@LayoutRes layoutId: Int) : this() {
        mCurrentLayoutId = layoutId
    }

    abstract fun initView(savedInstanceState: Bundle?)
    public open fun initData(savedInstanceState: Bundle?=null) {}


    /**
     * 打开等待框
     */
    public fun showLoading(content:String?="加载中..."){
        WaitDialog.show(content)
    }
    /**
     * 关闭等待框
     */
    public fun dismissLoading() {
        WaitDialog.dismiss()
    }

}