package com.riyaldi.moviecatalogue.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewpagerAdapter (private val fragmentList: List<Fragment>, fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm ,lifecycle) {
    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}
