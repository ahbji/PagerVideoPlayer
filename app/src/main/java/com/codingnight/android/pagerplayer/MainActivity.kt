package com.codingnight.android.pagerplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewPager = findViewById(R.id.mainViewPager)
        tabLayout = findViewById(R.id.tabLayout)

        mainViewPager.apply {
            adapter = object : FragmentStateAdapter(this@MainActivity) {
                override fun getItemCount(): Int = 3

                override fun createFragment(position: Int): Fragment =
                    when(position) {
                        1 -> VideoFragment()
                        else -> FooFragment()
                    }
            }

            setCurrentItem(1, false)
        }

        TabLayoutMediator(tabLayout, mainViewPager) { tab: TabLayout.Tab, position: Int ->
            tab.text = when (position) {
                1 -> "Video"
                else -> "foo"
            }
        }.attach()


    }
}