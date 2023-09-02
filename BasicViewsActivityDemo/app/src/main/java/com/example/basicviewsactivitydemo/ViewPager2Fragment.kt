package com.example.basicviewsactivitydemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.basicviewsactivitydemo.databinding.FragmentSecondBinding
import com.example.basicviewsactivitydemo.databinding.FragmentViewPager2Binding

class ViewPager2ItemModel {
    var str: String = ""
}

class ViewPager2ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var textView: TextView = itemView.findViewById(R.id.textView)

    var model: ViewPager2ItemModel = ViewPager2ItemModel()
        set(value) {
            textView.text = value.str
            field = value
        }
}

class ViewPager2Adapter(private val dataArray: Array<ViewPager2ItemModel>): RecyclerView.Adapter<ViewPager2ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPager2ItemViewHolder {
        return ViewPager2ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_pager2_item, parent, false))
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    override fun onBindViewHolder(holder: ViewPager2ItemViewHolder, position: Int) {
        holder.model = dataArray[position]
    }
}

/**
 * A simple [Fragment] subclass.
 * Use the [ViewPager2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewPager2Fragment : Fragment() {
    private var _binding: FragmentViewPager2Binding? = null
    private val binding get() = _binding!!
    private val mArr = mutableListOf<ViewPager2ItemModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewPager2Binding.inflate(inflater, container, false)


        val model0 = ViewPager2ItemModel()
        model0.str = "page0"
        mArr.add(model0)

        val model1 = ViewPager2ItemModel()
        model1.str = "page1"
        mArr.add(model1)

        val model2 = ViewPager2ItemModel()
        model2.str = "page2"
        mArr.add(model2)
        binding.viewPager2.adapter = ViewPager2Adapter(mArr.toTypedArray())
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }
}