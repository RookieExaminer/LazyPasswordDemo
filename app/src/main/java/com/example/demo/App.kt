package com.example.demo

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.hjq.toast.Toaster

/**
 * @author azheng
 * @date 2023/8/11.
 * GitHub：https://github.com/RookieExaminer
 * email：wei.azheng@foxmail.com
 * description：
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        // 初始化 Toast 框架
        Toaster.init(this)
        LogUtils.getConfig().isLogSwitch =true
    }
}