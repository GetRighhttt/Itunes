package com.example.stefanbayneitunes.view

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                SongForFragment.newInstance(SongForFragment.WAYNE)
            }

            1 -> {
                SongForFragment.newInstance(SongForFragment.KENDRICK)
            }

            2 -> {
                SongForFragment.newInstance(SongForFragment.COLE)
            }

            else -> {
                throw Resources.NotFoundException("Tab not found")
            }
        }
    }

    override fun getItemCount() = 3
}