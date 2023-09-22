package com.example.android_searchimagepage

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.android_searchimagepage.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var bookmarkItem = mutableListOf<SearchData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

    }
    //MainActivity에 띄울 View
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initView() {
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(SearchFragment())
        fragmentList.add(BookmarkFragment())

        binding.viewPager.adapter = ViewPagerAdapter(fragmentList, this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) {tab, position ->
            when (position) {
                0 -> {
                    tab.text = "검색"
                    tab.icon = resources.getDrawable(R.drawable.ic_search)
                }
                1 -> {
                    tab.text = "북마크"
                    tab.icon = resources.getDrawable(R.drawable.ic_bookmark)
                }
            }
        }.attach()
    }

    fun addBookmarkItem(item : SearchData) {
        if(bookmarkItem.contains(item)) {
        }
        else {
            bookmarkItem.add(item)
        }
        Log.d("addBookmark", "${bookmarkItem.map { it.title }}")
    }

    fun removeBookmarkItem(item : SearchData) {
        bookmarkItem.remove(item)
        Log.d("removeBookmark", "${bookmarkItem.map { it.title }}")
    }
}