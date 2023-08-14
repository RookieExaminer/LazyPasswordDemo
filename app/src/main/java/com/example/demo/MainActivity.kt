package com.example.demo

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.blankj.utilcode.util.StringUtils
import com.example.demo.databinding.ActivityMainBinding
import com.hjq.toast.Toaster
import com.kongzue.dialogx.dialogs.FullScreenDialog
import com.kongzue.dialogx.interfaces.OnBackPressedListener
import com.kongzue.dialogx.interfaces.OnBindView

class MainActivity : BaseActivity(R.layout.activity_main) {
    private val viewBinding: ActivityMainBinding by viewBinding()

    override fun initView(savedInstanceState: Bundle?) {
        viewBinding.btnShowDialog.setOnClickListener {
            showDialog()
        }
    }

    private var fullScreenDialog: FullScreenDialog? = null
    private var passWord: String = "123456"

    /**
     * 打开密码弹框
     */
    private fun showDialog() {
        val passWordCustomFragment = CustomFragment.newInstance()
            .setAddButtonClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    fullScreenDialog?.dismiss()
                    finish()
                }
            }).setAddItemClickListener(object : CustomFragment.OnItemClickListener {
                override fun setOnItemClickListener(text: StringBuilder) {
                    if (text.length == 6) {
                        if (StringUtils.equals(text, passWord)) {
                            Toaster.show("输入正确")
                            //输入正确
                            fullScreenDialog?.dismiss()
                        } else {
                            Toaster.show("密码输入错误，请重新输入")
                        }
                    }
                }
            })
        FullScreenDialog.show(object :
            OnBindView<FullScreenDialog?>(passWordCustomFragment) {
            override fun onBind(dialog: FullScreenDialog?, v: View) {
                fullScreenDialog = dialog
            }
        })
            .setOnBackPressedListener(object : OnBackPressedListener<FullScreenDialog> {
                override fun onBackPressed(dialog: FullScreenDialog?): Boolean {
                    return false
                }

            })
            .isAllowInterceptTouch = true //禁止滑动

    }
}