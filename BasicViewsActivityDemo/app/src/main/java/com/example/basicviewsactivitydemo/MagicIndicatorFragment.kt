package com.example.basicviewsactivitydemo

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.basicviewsactivitydemo.databinding.FragmentMagicIndicatorBinding
import com.example.basicviewsactivitydemo.databinding.FragmentRecyclerViewGridLayoutBinding
import com.example.basicviewsactivitydemo.databinding.FragmentScrollViewBinding
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import java.util.Arrays


class MypagerAdapter(classmate: Array<String>) : PagerAdapter() {

    private val mTest: List<String> = Arrays.asList(*classmate)

    override fun getCount(): Int {
        return mTest.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val textView = TextView(container.context)
        textView.text = mTest[position]
        textView.gravity = Gravity.CENTER
        container.addView(textView)
        return textView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getItemPosition(`object`: Any): Int {
        val textView = `object` as TextView
        val text = textView.text.toString()
        val index = mTest.indexOf(text)
        return if (index >= 0) {
            index
        } else {
            POSITION_NONE
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTest[position]
    }
}


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MagicIndicatorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MagicIndicatorFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentMagicIndicatorBinding? = null
    private val binding get() = _binding!!
    private var mArr: MutableList<RecyclerViewGridLayoutModel> = mutableListOf()
    private val mTitleDataList = mutableListOf("Tab 1", "Tab 2", "Tab 3")

    // MagicIndicator 指示器数据
    private val channels = arrayOf(
        "CUPCAKE", "DONUT", "ECLAIR", "GINGERBREAD", "HONEYCOMB", "ICE_CREAM_SANDWICH",
        "JELLY_BEAN", "KITKAT", "LOLLIPOP", "M", "NOUGAT"
    )
    private val mDataList: List<String> = ArrayList(Arrays.asList(*channels))

    // testArray，ViewPager 要显示的数据
    private val classmate = arrayOf(
        "曹博", "张汪洋", "金威", "智慧", "李庄", "草纸", "销毁", "张自豪感", "棒棒糖", "嚣张", "导员"
    )

    // ViewPager 实例
    private lateinit var mViewPager: ViewPager

    // 自定义适配器实例
    private lateinit var mAdapter: MypagerAdapter

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
        _binding = FragmentMagicIndicatorBinding.inflate(inflater, container, false)


        // 生成自定义 pagerAdapter 对象
        mAdapter = MypagerAdapter(classmate)
//        binding.viewPager.also { mViewPager = it }
        mViewPager.adapter = mAdapter

        // MagicIndicator 指示器部分
        val magicIndicator = binding.magicIndicator
        val commonNavigator = CommonNavigator(context)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return mDataList.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val clipPagerTitleView = ClipPagerTitleView(context)
                clipPagerTitleView.text = mDataList[index]
                clipPagerTitleView.setTextColor(Color.parseColor("#f2c4c4"))
                clipPagerTitleView.clipColor = Color.WHITE

                clipPagerTitleView.setOnClickListener { mViewPager.currentItem = index }
                return clipPagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator? {
                return null // 没有指示器，因为 title 的指示作用已经很明显了
            }
        }
        magicIndicator.navigator = commonNavigator

        // 指示器滑动跟随的效果
        ViewPagerHelper.bind(magicIndicator, mViewPager)


        return binding.root
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MagicIndicatorFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MagicIndicatorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}