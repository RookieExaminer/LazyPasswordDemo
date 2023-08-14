package com.example.demo

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.blankj.utilcode.util.LogUtils
import com.example.demo.databinding.FragmentCustomBinding


/**
 * @author azheng
 * @date 2023/7/25.
 * GitHub：https://github.com/RookieExaminer
 * email：wei.azheng@foxmail.com
 * description：自定义密码弹框
 */
class CustomFragment : BaseFragment(R.layout.fragment_custom) {

    private val viewBinding: FragmentCustomBinding by viewBinding()


    private val circleAdapter: CircleAdapter by lazy { CircleAdapter() }

    private val keyboardAdapter: KeyboardAdapter by lazy { KeyboardAdapter() }

    private var positionCircle: Int = 0

    private val inputString: StringBuilder by lazy {
        StringBuilder("")
    }
    private var isHideCancelButton:Boolean=false

    companion object {
        fun newInstance() = CustomFragment()
    }

    override fun initView(savedInstanceState: Bundle?) {
        //关闭界面
        viewBinding.ivClosure.setOnClickListener() {
            addButtonClickListener?.onClick(it)
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        if (isHideCancelButton) {
            viewBinding.ivClosure.visibility = View.GONE
        }
        val list: MutableList<CircleBean> = mutableListOf()
        list.add(
            CircleBean(
                R.drawable.baseline_circle_black,
                R.drawable.baseline_circle_grey,
                false,
                ""
            )
        )
        list.add(
            CircleBean(
                R.drawable.baseline_circle_black,
                R.drawable.baseline_circle_grey,
                false,
                ""
            )
        )
        list.add(
            CircleBean(
                R.drawable.baseline_circle_black,
                R.drawable.baseline_circle_grey,
                false,
                ""
            )
        )
        list.add(
            CircleBean(
                R.drawable.baseline_circle_black,
                R.drawable.baseline_circle_grey,
                false,
                ""
            )
        )
        list.add(
            CircleBean(
                R.drawable.baseline_circle_black,
                R.drawable.baseline_circle_grey,
                false,
                ""
            )
        )
        list.add(
            CircleBean(
                R.drawable.baseline_circle_black,
                R.drawable.baseline_circle_grey,
                false,
                ""
            )
        )
        viewBinding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 6)
        viewBinding.recyclerView.adapter = circleAdapter
        circleAdapter.submitList(list)


        val listKeyboard: MutableList<KeyboardBean> = mutableListOf()
        listKeyboard.add(KeyboardBean(1))
        listKeyboard.add(KeyboardBean(2))
        listKeyboard.add(KeyboardBean(3))
        listKeyboard.add(KeyboardBean(4))
        listKeyboard.add(KeyboardBean(5))
        listKeyboard.add(KeyboardBean(6))
        listKeyboard.add(KeyboardBean(7))
        listKeyboard.add(KeyboardBean(8))
        listKeyboard.add(KeyboardBean(9))
        listKeyboard.add(KeyboardBean(-1))
        listKeyboard.add(KeyboardBean(0))
        listKeyboard.add(KeyboardBean(-2))
        viewBinding.recyclerViewKeyboard.layoutManager = GridLayoutManager(requireContext(), 3)
        viewBinding.recyclerViewKeyboard.adapter = keyboardAdapter
        keyboardAdapter.submitList(listKeyboard)
        keyboardAdapter.setOnItemClickListener { adapter, view, position ->
            LogUtils.d(positionCircle)
            if (keyboardAdapter.items[position].number != -2 && positionCircle != 6) {
                positionCircle = positionCircle + 1
                circleAdapter.items[positionCircle - 1].src =
                    "${keyboardAdapter.items[position].number}"
                circleAdapter.items[positionCircle - 1].isContent = true
                circleAdapter.notifyItemChanged(positionCircle - 1)
                LogUtils.d(positionCircle - 1)
            } else if (keyboardAdapter.items[position].number == -2) {
                if (positionCircle==0){
                    return@setOnItemClickListener
                }
                LogUtils.d(positionCircle)
                if (positionCircle != 0) {
                    positionCircle = positionCircle - 1
                }
                circleAdapter.items[positionCircle].src = ""
                circleAdapter.items[positionCircle].isContent = false
                circleAdapter.notifyItemChanged(positionCircle)

            }
            inputString.clear()
            for (i in circleAdapter.items.indices) {
                inputString.append(circleAdapter.items[i].src)
            }
            addItemClickListener?.setOnItemClickListener(inputString)
        }
    }

    /**
     * 隐藏取消按钮
     */
    public fun hideCancelButton(isHideCancelButton: Boolean): CustomFragment {
        this.isHideCancelButton =isHideCancelButton
        return this
    }

    private var addButtonClickListener: View.OnClickListener? = null

    fun setAddButtonClickListener(addButtonClickListener: View.OnClickListener?): CustomFragment {
        this.addButtonClickListener = addButtonClickListener
        return this
    }

    public interface OnItemClickListener {
        fun setOnItemClickListener(text: StringBuilder)
    }

    private var addItemClickListener: OnItemClickListener? = null

    fun setAddItemClickListener(addItemClickListener: OnItemClickListener?): CustomFragment {
        this.addItemClickListener = addItemClickListener
        return this
    }

}