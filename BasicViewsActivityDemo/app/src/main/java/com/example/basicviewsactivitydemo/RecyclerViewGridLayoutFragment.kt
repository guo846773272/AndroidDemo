package com.example.basicviewsactivitydemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.basicviewsactivitydemo.databinding.FragmentRecyclerViewGridLayoutBinding
import com.example.basicviewsactivitydemo.databinding.FragmentSecondBinding

class RecyclerViewGridLayoutModel {
    var str: String = ""
    var isAdd: Boolean = false
    var didClickCallback: ((model: RecyclerViewGridLayoutModel) -> Unit)? = null
}

class RecyclerViewGridLayoutAdapter(var dataArray: Array<RecyclerViewGridLayoutModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class RecyclerViewGridLayoutViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView)
        var model: RecyclerViewGridLayoutModel = RecyclerViewGridLayoutModel()
            set(value) {
                textView.text = value.str
                field = value
            }
        init {
            itemView.setOnClickListener {
                if (model.isAdd) {
                    model.didClickCallback?.let { it1 -> it1(model) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cell = LayoutInflater.from(parent.context).inflate(R.layout.grid_layout_item, parent, false)
        return RecyclerViewGridLayoutViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecyclerViewGridLayoutViewHolder) {
            holder.model = dataArray[position]
        }
    }

}

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerViewGridLayoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerViewGridLayoutFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentRecyclerViewGridLayoutBinding? = null
    private val binding get() = _binding!!
    private var mArr: MutableList<RecyclerViewGridLayoutModel> = mutableListOf()

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
        _binding = FragmentRecyclerViewGridLayoutBinding.inflate(inflater, container, false)

        if (mArr.size == 0) {//返回执行的问题
            val model0 = RecyclerViewGridLayoutModel()
            model0.str = "第0个"
            mArr.add(model0)

            val model1 = RecyclerViewGridLayoutModel()
            model1.str = "第1个"
            mArr.add(model1)

            val model2 = RecyclerViewGridLayoutModel()
            model2.str = "第2个"
            mArr.add(model2)

            val model3 = RecyclerViewGridLayoutModel()
            model3.str = "第3个"
            mArr.add(model3)

            val model4 = RecyclerViewGridLayoutModel()
            model4.str = "第4个"
            mArr.add(model4)

            val model5 = RecyclerViewGridLayoutModel()
            model5.str = "isAdd"
            model5.isAdd = true
            model5.didClickCallback = {
                val index = mArr.indexOf(it)
                val model = RecyclerViewGridLayoutModel()
                model.str = "new ele"
                mArr.add(index, model)
                val adapter = binding.recyclerView.adapter as RecyclerViewGridLayoutAdapter
                adapter.dataArray = mArr.toTypedArray()
                adapter.notifyDataSetChanged()
            }
            mArr.add(model5)

        }


        binding.recyclerView.adapter = RecyclerViewGridLayoutAdapter(mArr.toTypedArray())

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecyclerViewGridLayoutFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecyclerViewGridLayoutFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}