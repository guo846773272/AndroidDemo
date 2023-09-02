package com.example.basicviewsactivitydemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.basicviewsactivitydemo.databinding.FragmentTabLayoutViewPager2Binding
import com.example.basicviewsactivitydemo.databinding.FragmentTabLayoutViewPager2ItemBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TabLayoutViewPager2ItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabLayoutViewPager2ItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentTabLayoutViewPager2ItemBinding? = null
    private var mArr: MutableList<FirstRecyclerViewCellModel> = mutableListOf()
    private val binding get() = _binding!!

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
        _binding = FragmentTabLayoutViewPager2ItemBinding.inflate(inflater, container, false)

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

//            val model2 = FirstRecyclerViewCellModel()
//            model2.str = "TabLayout"
//            model0.didClickCallback = {
//                it.findNavController().navigate(R.id.action_FirstFragment_to_TabLayoutFragment)
//            }
//            mArr.add(model2)

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

        binding.recyclerView.adapter = FirstRecyclerViewAdapter(mArr.toTypedArray())

        return binding.root
    }

    companion object {
        fun newInstance(tabIndex: Int): TabLayoutViewPager2ItemFragment {
            val fragment = TabLayoutViewPager2ItemFragment()
            val args = Bundle()
            args.putInt("tabIndex", tabIndex)
            fragment.arguments = args
            return fragment
        }
    }


//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment TabLayoutViewPager2ItemFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            TabLayoutViewPager2ItemFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}