package com.example.basicviewsactivitydemo

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.basicviewsactivitydemo.databinding.FragmentFirstBinding
import com.example.basicviewsactivitydemo.databinding.FragmentTabLayoutViewPager2Binding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import com.google.android.material.tabs.TabLayoutMediator

class MyFragmentStateAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3 // 选项卡数量
    }

    override fun createFragment(position: Int): Fragment {
        return TabLayoutViewPager2ItemFragment.newInstance(position)
    }
}

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TabLayoutViewPager2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabLayoutViewPager2Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentTabLayoutViewPager2Binding? = null
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
        _binding = FragmentTabLayoutViewPager2Binding.inflate(inflater, container, false)

        val tabLayout = binding.tabLayout
        val viewPager2 = binding.viewPager2

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // 获取点击的选项卡视图
                val tabView = tab?.view
                // 设置背景色为红色
                tabView?.setBackgroundColor(Color.BLUE)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // 当选项卡取消选中时，你可以在这里设置其他状态的背景色
                val tabView = tab?.view
                tabView?.setBackgroundColor(Color.RED)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // 当选项卡被重新选中时，你可以在这里执行其他操作
                val tabView = tab?.view
                tabView?.setBackgroundColor(Color.CYAN)
            }
        })


        val adapter = MyFragmentStateAdapter(this)
        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = "Tab $position"
        }.attach()

        // 将 TabLayout 和 ViewPager 关联起来
//        tabLayout.setupWithViewPager(viewPager)

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TabLayoutViewPager2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TabLayoutViewPager2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}