package com.example.android_searchimagepage

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (
    private val fragmentList : ArrayList<Fragment>,
    container: AppCompatActivity) : FragmentStateAdapter(container.supportFragmentManager, container.lifecycle){
    //화면에 표시할 페이지의 개수 반환
    override fun getItemCount(): Int {
        return fragmentList.count()
    }

    //각 페이지마다 보여줄 View 반환
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}