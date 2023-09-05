package com.example.basicviewsactivitydemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.basicviewsactivitydemo.databinding.FragmentSmartRefreshLayoutBinding
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import java.util.Random

class SmartRefreshLayoutModel {
    var str: String? = null
}
class SmartRefreshLayoutAdapter(var modelArr: Array<SmartRefreshLayoutModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class SmartRefreshLayoutViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        var model: SmartRefreshLayoutModel = SmartRefreshLayoutModel()
            set(value) {
                textView.text = value.str
                field = value
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cell = LayoutInflater.from(parent.context).inflate(R.layout.first_recycler_view_cell, parent, false)
        return SmartRefreshLayoutViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return modelArr.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SmartRefreshLayoutViewHolder) {
            holder.model = modelArr[position]
        }
    }



}
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SmartRefreshLayoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SmartRefreshLayoutFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentSmartRefreshLayoutBinding? = null
    private var mArr: MutableList<SmartRefreshLayoutModel> = mutableListOf()
    private val binding get() = _binding!!
    private var page = 1

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

        _binding = FragmentSmartRefreshLayoutBinding.inflate(inflater, container, false)

        val model0 = SmartRefreshLayoutModel()
        model0.str = "init-page$page-0"
        mArr.add(model0)

        val model1 = SmartRefreshLayoutModel()
        model1.str = "init-page$page-1"
        mArr.add(model1)

        val model2 = SmartRefreshLayoutModel()
        model2.str = "init-page$page-2"
        mArr.add(model2)

        val model3 = SmartRefreshLayoutModel()
        model3.str = "init-page$page-3"
        mArr.add(model3)

        val model4 = SmartRefreshLayoutModel()
        model4.str = "init-page$page-4"
        mArr.add(model4)

        val recyclerView = binding.recyclerView
        recyclerView.adapter = SmartRefreshLayoutAdapter(mArr.toTypedArray())

        val refreshLayout = binding.refreshLayout
        refreshLayout.setRefreshHeader(ClassicsHeader(requireContext()))
        refreshLayout.setRefreshFooter(ClassicsFooter(requireContext()))
        refreshLayout.setOnRefreshListener { refreshlayout ->

            page = 1
            val delayMillis = 2000 // 2秒
            val handler = Handler(Looper.getMainLooper())

            handler.postDelayed({
                // 在这里编写需要延迟执行的代码

                mArr.clear()

                val random = Random()
                val min = 0
                val max = 5
                val randomNumber = random.nextInt(max - min + 1) + min
                for (i in 0 until randomNumber) {
                    // 这里是循环体
                    val model = SmartRefreshLayoutModel()
                    model.str = "refresh - page$page - $i"
                    mArr.add(model)
                }

                if (mArr.size == 0) {
                    refreshlayout.finishRefreshWithNoMoreData()
                } else {
                    refreshlayout.finishRefresh(0)
                }
                val adapter = binding.recyclerView.adapter as SmartRefreshLayoutAdapter
                adapter.modelArr = mArr.toTypedArray()
                adapter.notifyDataSetChanged()
            }, delayMillis.toLong())

//            refreshlayout.finishRefresh(2000L/*, false*/) // 传入false表示刷新失败
        }
        refreshLayout.setOnLoadMoreListener { refreshlayout ->

            page = page + 1

            val tempMArr = mutableListOf<SmartRefreshLayoutModel>()
            val random = Random()
            val min = 0
            var max = 5
            if (page == 5) {
                max = 0
            }

            val randomNumber = random.nextInt(max - min + 1) + min
            for (i in 0 until randomNumber) {
                // 这里是循环体
                val model = SmartRefreshLayoutModel()
                model.str = "loadMore - page$page - $i"
                tempMArr.add(model)
            }

            if (tempMArr.size == 0) {
                refreshlayout.finishLoadMoreWithNoMoreData()
            } else {
                mArr.addAll(tempMArr)
                val adapter = binding.recyclerView.adapter as SmartRefreshLayoutAdapter
                adapter.modelArr = mArr.toTypedArray()
                adapter.notifyDataSetChanged()
                refreshlayout.finishLoadMore(0)
            }



        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SmartRefreshLayoutFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SmartRefreshLayoutFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}