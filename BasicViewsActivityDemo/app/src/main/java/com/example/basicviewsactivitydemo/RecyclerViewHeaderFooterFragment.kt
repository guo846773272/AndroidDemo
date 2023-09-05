package com.example.basicviewsactivitydemo

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.basicviewsactivitydemo.databinding.FragmentRecyclerViewHeaderFooterBinding


enum class RecyclerViewChildViewType(val value: Int) {
    MAIN_CELL(0),
    HEADER(1),
    FOOTER(2)
}
class RecyclerViewChildModel {
    var childViewType: RecyclerViewChildViewType = RecyclerViewChildViewType.MAIN_CELL
    var dataArr: Array<FirstRecyclerViewCellModel>? = null
}

class RecyclerViewHeaderFooterAdapter(var dataArray: Array<FirstRecyclerViewCellModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class FirstRecyclerViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(com.example.basicviewsactivitydemo.R.id.textView)
        var model: FirstRecyclerViewCellModel = FirstRecyclerViewCellModel()
            set(value) {
                textView.text = value.str
                field = value
            }
        init {
            itemView.setOnClickListener {
                model?.didClickCallback?.let { it1 -> it1(itemView) }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val cell = LayoutInflater.from(parent.context).inflate(com.example.basicviewsactivitydemo.R.layout.recycler_view_header, parent, false)
            return FirstRecyclerViewViewHolder(cell)
        }
        if (viewType == dataArray.size + 1) {
            val cell = LayoutInflater.from(parent.context).inflate(com.example.basicviewsactivitydemo.R.layout.recycler_view_header, parent, false)
            return FirstRecyclerViewViewHolder(cell)
        }
        val cell = LayoutInflater.from(parent.context).inflate(com.example.basicviewsactivitydemo.R.layout.first_recycler_view_cell, parent, false)
        return FirstRecyclerViewViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return dataArray.size + 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0 || position == dataArray.size + 1) {
            return
        }
        if (holder is FirstRecyclerViewViewHolder) {
            holder.model = dataArray[position - 1]
        }
    }
}


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerViewHeaderFooterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerViewHeaderFooterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentRecyclerViewHeaderFooterBinding? = null
    private val binding get() = _binding!!
    private val mArr = mutableListOf<RecyclerViewChildModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerViewHeaderFooterBinding.inflate(inflater, container, false)

        val mainCellArr = mutableListOf<FirstRecyclerViewCellModel>()

        val model0 = FirstRecyclerViewCellModel()
        model0.str = "ViewPager2"
//        model0.didClickCallback = {
//            it.findNavController().navigate(R.id.action_FirstFragment_to_ViewPager2Fragment)
//        }
        mainCellArr.add(model0)

        val model1 = FirstRecyclerViewCellModel()
        model1.str = "ScrollView"
//        model1.didClickCallback = {
//            it.findNavController().navigate(R.id.action_FirstFragment_to_ScrollViewFragment)
//        }
        mainCellArr.add(model1)

//            val model2 = FirstRecyclerViewCellModel()
//            model2.str = "TabLayout"
//            model0.didClickCallback = {
//                it.findNavController().navigate(R.id.action_FirstFragment_to_TabLayoutFragment)
//            }
//            mArr.add(model2)

        val model3 = FirstRecyclerViewCellModel()
        model3.str = "GridLayout"
//        model3.didClickCallback = {
//            it.findNavController().navigate(R.id.action_FirstFragment_to_GridLayoutFragment)
//        }
        mainCellArr.add(model3)

        val model4 = FirstRecyclerViewCellModel()
        model4.str = "RecyclerViewGridLayout"
//        model4.didClickCallback = {
//            it.findNavController().navigate(R.id.action_FirstFragment_to_RecyclerViewGridLayoutFragment)
//        }
        mainCellArr.add(model4)

        val model5 = FirstRecyclerViewCellModel()
        model5.str = "MagicIndicator"
//        model5.didClickCallback = {
//            it.findNavController().navigate(R.id.action_FirstFragment_to_MagicIndicatorFragment)
//        }
        mainCellArr.add(model5)

        val model6 = FirstRecyclerViewCellModel()
        model6.str = "TabLayoutViewPager2"
//        model6.didClickCallback = {
//            it.findNavController().navigate(R.id.action_FirstFragment_to_TabLayoutViewPager2Fragment)
//        }
        mainCellArr.add(model6)

//        val headerChildModel = RecyclerViewChildModel()
//        headerChildModel.childViewType = RecyclerViewChildViewType.HEADER
//        mArr.add(headerChildModel)
//
//        val mainCellChildModel = RecyclerViewChildModel()
//        mainCellChildModel.childViewType = RecyclerViewChildViewType.MAIN_CELL
//        mainCellChildModel.dataArr = mainCellArr.toTypedArray()
//        mArr.add(mainCellChildModel)
//
//        val footerChildModel = RecyclerViewChildModel()
//        footerChildModel.childViewType = RecyclerViewChildViewType.FOOTER
//        mArr.add(footerChildModel)

        binding.recyclerView.adapter = RecyclerViewHeaderFooterAdapter(mainCellArr.toTypedArray())

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecyclerViewHeaderFooterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecyclerViewHeaderFooterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}