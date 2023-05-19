package com.example.stefanbayneitunes.view

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

// This is where we adapt our song fragment into our main activity.
// The viewpager is responsible for adapting views to the activity.
class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    // Each song choice is being assigned to our fragment SongForFragment
    // We need the adapter for the tablayout to create instances of each view
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {SongForFragment.newInstance(SongForFragment.WAYNE)}
            1 -> {SongForFragment.newInstance(SongForFragment.KENDRICK)}
            2 -> {SongForFragment.newInstance(SongForFragment.COLE)}
            else -> {throw Resources.NotFoundException("Tab not found")}
        }
    }

    override fun getItemCount() = 3
}