package com.example.stefanbayneitunes

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.viewpager2.widget.ViewPager2
import com.example.stefanbayneitunes.Model.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var tabViewPager : ViewPager2
    private lateinit var tabTabLayout : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        tabViewPager = findViewById(R.id.viewPager)
        tabTabLayout = findViewById(R.id.tabLayout)

        tabViewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tabTabLayout,tabViewPager){ tab, index ->
            tab.text = when(index){
                0 -> {"Rap"}
                1 -> {"Jazz"}
                2 -> {"Gospel"}
                else -> {throw Resources.NotFoundException("Tab not found")}
            }

            tab.icon = when(index){
                0 -> {
                    AppCompatResources.getDrawable(this, R.drawable.ic_baseline_account_circle_24)}
                1 -> {
                    AppCompatResources.getDrawable(this, R.drawable.ic_baseline_dashboard_24)}
                2 -> {
                    AppCompatResources.getDrawable(this, R.drawable.ic_baseline_deck_24)}
                else -> {throw Resources.NotFoundException("Tab not found")}
            }
        }.attach()

    }
}
