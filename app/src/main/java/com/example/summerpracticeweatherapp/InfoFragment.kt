package com.example.summerpracticeweatherapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ScrollState
import com.example.summerpracticeweatherapp.databinding.FragmentInfoBinding
import com.example.summerpracticeweatherapp.utils.SharedPrefsUtils
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle

class InfoFragment : Fragment(R.layout.fragment_info) {
    var binding : FragmentInfoBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)

        val adapter = ViewPagerAdapter(InfoData.info)
        binding?.let{
            with(it)
            {
                btnContinue.setOnClickListener {
                    SharedPrefsUtils.setOpenState(requireContext())
                    findNavController().navigate(R.id.action_infoFragment_to_searchFragment)
                }

                vpPager.adapter = adapter
                ivIndicator.apply {
                    setSlideMode(IndicatorSlideMode.SCALE)
                    setIndicatorStyle(IndicatorStyle.CIRCLE)
                    setPageSize(vpPager.adapter!!.itemCount)

                    setSliderColor(Color.LTGRAY, Color.rgb(123, 75, 168))
                    setSliderWidth(
                        resources.getDimension(R.dimen.default_6dp),
                        resources.getDimension(R.dimen.default_16dp)
                    )
                    notifyDataChanged()
                }


                vpPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                        ivIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels)

                    }

                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        ivIndicator.onPageSelected(position)

                        activity?.let {
                            val button = it.findViewById<AppCompatButton>(R.id.btn_continue)
                            when (position) {
                                1 -> {
                                    button.text = "Start"
                                }
                                else -> {
                                    button.text = "Skip info page"
                                }
                            }
                        }
                    }
                })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}