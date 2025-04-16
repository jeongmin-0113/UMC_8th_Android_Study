package com.example.week3.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BannerVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentList: ArrayList<Fragment> = ArrayList()

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position] // idx 0~position-1 까지 프래그먼트 반환

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
        notifyItemInserted(fragmentList.size-1) // view pager에게 새로 추가된 인자의 인덱스를 알려줌
        // 프래그먼트 추가되었음을 알려주면 VP는 추가된 프래그먼트까지 vp에 추가해 보여줌
    }
}