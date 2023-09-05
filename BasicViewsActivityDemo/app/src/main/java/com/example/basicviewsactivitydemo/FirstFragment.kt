package com.example.basicviewsactivitydemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.basicviewsactivitydemo.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class FirstRecyclerViewCellModel {
    var str: String = ""
    var didClickCallback: ((view: View) -> Unit)? = null
}

class FirstRecyclerViewAdapter(var dataArray: Array<FirstRecyclerViewCellModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class FirstRecyclerViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView)
        var model: FirstRecyclerViewCellModel = FirstRecyclerViewCellModel()
            set(value) {
                textView.text = value.str
                field = value
            }
        init {
            itemView.setOnClickListener {
                model?.didClickCallback?.let { it1 -> it1(itemView) }
//                if (model.str == "ViewPager2") {
//                    it.findNavController().navigate(R.id.action_FirstFragment_to_ViewPager2Fragment)
//                }
//                if (model.str == "ScrollView") {
//                    it.findNavController().navigate(R.id.action_FirstFragment_to_ScrollViewFragment)
//                }
//                if (model.str == "GridLayout") {
////                    it.findNavController().navigate(R.id.action_FirstFragment_to_GridLayoutFragment)
//                }
//                if (model.str == "RecyclerViewGridLayout") {
//                    it.findNavController().navigate(R.id.action_FirstFragment_to_RecyclerViewGridLayoutFragment)
//                }
//                if (model.str == "MagicIndicator") {
//                    it.findNavController().navigate(R.id.action_FirstFragment_to_MagicIndicatorFragment)
//                }
//                if (model.str == "TabLayoutViewPager2") {
//                    it.findNavController().navigate(R.id.action_FirstFragment_to_TabLayoutViewPager2Fragment)
//                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cell = LayoutInflater.from(parent.context).inflate(R.layout.first_recycler_view_cell, parent, false)
        return FirstRecyclerViewViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FirstRecyclerViewViewHolder) {
            holder.model = dataArray[position]
        }
    }

}

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var mArr: MutableList<FirstRecyclerViewCellModel> = mutableListOf()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()


    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        if (mArr.size == 0) {//返回执行的问题
            val model0 = FirstRecyclerViewCellModel()
            model0.str = "ViewPager2"
            model0.didClickCallback = {
                it.findNavController().navigate(R.id.action_FirstFragment_to_ViewPager2Fragment)
            }
            mArr.add(model0)

            val model1 = FirstRecyclerViewCellModel()
            model1.str = "ScrollView"
            model1.didClickCallback = {
                it.findNavController().navigate(R.id.action_FirstFragment_to_ScrollViewFragment)
            }
            mArr.add(model1)

            val model2 = FirstRecyclerViewCellModel()
            model2.str = "RecyclerViewHeaderFooter"
            model2.didClickCallback = {
                it.findNavController().navigate(R.id.action_FirstFragment_to_RecyclerViewHeaderFooterFragment)
            }
            mArr.add(model2)

            val model3 = FirstRecyclerViewCellModel()
            model3.str = "GridLayout"
            model3.didClickCallback = {
                it.findNavController().navigate(R.id.action_FirstFragment_to_GridLayoutFragment)
            }
            mArr.add(model3)

            val model4 = FirstRecyclerViewCellModel()
            model4.str = "RecyclerViewGridLayout"
            model4.didClickCallback = {
                it.findNavController().navigate(R.id.action_FirstFragment_to_RecyclerViewGridLayoutFragment)
            }
            mArr.add(model4)

            val model5 = FirstRecyclerViewCellModel()
            model5.str = "MagicIndicator"
            model5.didClickCallback = {
                it.findNavController().navigate(R.id.action_FirstFragment_to_MagicIndicatorFragment)
            }
            mArr.add(model5)

            val model6 = FirstRecyclerViewCellModel()
            model6.str = "TabLayoutViewPager2"
            model6.didClickCallback = {
                it.findNavController().navigate(R.id.action_FirstFragment_to_TabLayoutViewPager2Fragment)
            }
            mArr.add(model6)

            val model7 = FirstRecyclerViewCellModel()
            model7.str = "返回按钮事件拦截PopBackIntercept"
            model7.didClickCallback = {
                it.findNavController().navigate(R.id.action_FirstFragment_to_PopBackInterceptFragment)
            }
            mArr.add(model7)

            val model8 = FirstRecyclerViewCellModel()
            model8.str = "okhttp"
            model8.didClickCallback = {
                it.findNavController().navigate(R.id.action_FirstFragment_to_OkhttpFragment)
            }
            mArr.add(model8)

            val model9 = FirstRecyclerViewCellModel()
            model9.str = "SmartRefreshLayout"
            model9.didClickCallback = {
                it.findNavController().navigate(R.id.action_FirstFragment_to_SmartRefreshLayoutFragment)
            }
            mArr.add(model9)

        }

        binding.recyclerView.adapter = FirstRecyclerViewAdapter(mArr.toTypedArray())
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}