package com.example.basicviewsactivitydemo

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.basicviewsactivitydemo.databinding.FragmentGridLayoutBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GridLayoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GridLayoutFragment : Fragment() {
    private var _binding: FragmentGridLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGridLayoutBinding.inflate(inflater, container, false)

//        if (false) {
//            val gridLayout = binding.topGridLayout
////            val gridLayout = GridLayout(context)
//            gridLayout.columnCount = 3
//            gridLayout.rowCount = 2
//
//            val button1 = Button(context)
//            button1.text = "Button 1"
//            gridLayout.addView(button1)
//
//            val button2 = Button(context)
//            button2.text = "Button 2"
//            gridLayout.addView(button2)
//
//            val button3 = Button(context)
//            button3.text = "Button 3"
//            gridLayout.addView(button3)
//
//            val button4 = Button(context)
//            button4.text = "Button 4"
//            gridLayout.addView(button4)
//
//// 添加子视图到 GridLayout
//
//// 将 GridLayout 添加到父布局
////            binding.topView.addView(gridLayout)
//        }

        val dynamicData = mutableListOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9")






        return binding.root
    }

    // 将 dp 转换为像素
    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GridLayoutFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GridLayoutFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}