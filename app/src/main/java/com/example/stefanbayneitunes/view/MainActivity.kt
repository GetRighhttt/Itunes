package com.example.stefanbayneitunes.view

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.viewpager2.widget.ViewPager2
import com.example.stefanbayneitunes.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var tabViewPager: ViewPager2
    private lateinit var tabTabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        tabViewPager = findViewById(R.id.viewPager)
        tabTabLayout = findViewById(R.id.tabLayout)

        tabViewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tabTabLayout, tabViewPager) { tab, index ->
            tab.text = when (index) {
                0 -> {
                    "Lil Wayne"
                }

                1 -> {
                    "Kendrick"
                }

                2 -> {
                    "J Cole"
                }

                3 -> {
                    "Burna Boy"
                }

                else -> {
                    throw Resources.NotFoundException("Tab not found")
                }
            }

            tab.icon = when (index) {
                0 -> {
                    AppCompatResources.getDrawable(this, R.drawable.ic_baseline_account_circle_24)
                }

                1 -> {
                    AppCompatResources.getDrawable(this, R.drawable.ic_baseline_dashboard_24)
                }

                2 -> {
                    AppCompatResources.getDrawable(this, R.drawable.ic_baseline_deck_24)
                }

                3 -> {
                    AppCompatResources.getDrawable(this, R.drawable.baseline_music_note_24)
                }

                else -> {
                    throw Resources.NotFoundException("Tab not found")
                }
            }
        }.attach()

    }
}
