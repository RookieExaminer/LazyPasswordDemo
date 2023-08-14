package com.example.demo

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import com.blankj.utilcode.util.ResourceUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder

/**
 * @author azheng
 * @date 2023/8/11.
 * GitHub：https://github.com/RookieExaminer
 * email：wei.azheng@foxmail.com
 * description：
 */
class CircleAdapter : BaseQuickAdapter<CircleBean, QuickViewHolder>() {
    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: CircleBean?) {
      if (item!!.isContent){
          holder.getView<ImageView>(R.id.ivIcon).setImageDrawable(ResourceUtils.getDrawable(item.icon))
      }else{
          holder.getView<ImageView>(R.id.ivIcon).setImageDrawable(ResourceUtils.getDrawable(item.greyIcon))
      }

    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.item_circle, parent)
    }
}