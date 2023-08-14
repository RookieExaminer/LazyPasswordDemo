package com.example.demo

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.ColorUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder
import com.hjq.shape.layout.ShapeFrameLayout

/**
 * @author azheng
 * @date 2023/8/11.
 * GitHub：https://github.com/RookieExaminer
 * email：wei.azheng@foxmail.com
 * description：
 */
class KeyboardAdapter : BaseQuickAdapter<KeyboardBean, QuickViewHolder>() {
    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: KeyboardBean?) {
        val tvNumber = holder.getView<TextView>(R.id.tvNumber)
        val ivDelete = holder.getView<ImageView>(R.id.ivDelete)
        val flBg = holder.getView<ShapeFrameLayout>(R.id.flBg)
        tvNumber.text = "${item?.number}"
        if (item?.number == -1) {
            tvNumber.visibility = View.GONE
            ivDelete.visibility = View.GONE
            flBg.shapeDrawableBuilder
                .setSolidColor(ColorUtils.getColor(R.color.color_keyboard_number))
                .setSolidPressedColor(ColorUtils.getColor(R.color.color_keyboard_number))
                .intoBackground()
        } else if (item?.number == -2) {
            tvNumber.visibility = View.GONE
            ivDelete.visibility = View.VISIBLE
            flBg.shapeDrawableBuilder
                .setSolidColor(ColorUtils.getColor(R.color.color_keyboard_number))
                .setSolidPressedColor(ColorUtils.getColor(R.color.color_keyboard_press))
                .intoBackground()
        } else {
            tvNumber.visibility = View.VISIBLE
            ivDelete.visibility = View.GONE
            flBg.shapeDrawableBuilder
                .setSolidColor(ColorUtils.getColor(R.color.color_keyboard_number))
                .setSolidPressedColor(ColorUtils.getColor(R.color.color_keyboard_press))
                .intoBackground()
        }


    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.item_keyboard, parent)
    }
}