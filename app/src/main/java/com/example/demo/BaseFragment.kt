package com.example.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.kongzue.dialogx.dialogs.WaitDialog

/**
 *   @auther : Aleyn
 *   time   : 2019/11/01
 */
abstract class BaseFragment : Fragment {

    @LayoutRes
    private var mCurrentLayoutId: Int = 0
    //是否第一次加载
    private var isFirst: Boolean = true

    constructor() : super()
    constructor(@LayoutRes layoutId: Int) : this() {
        mCurrentLayoutId = layoutId
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(mCurrentLayoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
        initData(savedInstanceState)
        onVisible()
    }


    override fun onResume() {
        super.onResume()
        onVisible()
    }

    abstract fun initView(savedInstanceState: Bundle?)
    public open fun initData(savedInstanceState: Bundle?) {}

    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            lazyLoadData()
            isFirst = false
        }
    }

    /**
     * 懒加载
     */
    open fun lazyLoadData() {}

    /**
     * 打开等待框
     */
    public fun showLoading(){
        WaitDialog.show("加载中...")
    }
    /**
     * 关闭等待框
     */
    public fun dismissLoading() {
        WaitDialog.dismiss()
    }

}