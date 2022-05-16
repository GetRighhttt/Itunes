package com.example.stefanbayneitunes.Model

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.stefanbayneitunes.Fragment.SongForFragment


class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {SongForFragment.newInstance(SongForFragment.HIPHOP)}
            1 -> {SongForFragment.newInstance(SongForFragment.JAZZ)}
            2 -> {SongForFragment.newInstance(SongForFragment.GOSPEL)}
            else -> {throw Resources.NotFoundException("Tab not found")}
        }
    }

    override fun getItemCount() = 3
}