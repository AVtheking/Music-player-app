package com.example.musicplayerapp.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.provider.MediaStore
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.musicplayerapp.Music
import com.example.musicplayerapp.adapters.MusicAdapter
import com.example.musicplayerapp.R
import com.example.musicplayerapp.adapters.ViewPagerAdater
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.io.File

class HomeFragment : Fragment() {
    private val tabsArray = arrayOf("Home", "Albums", "Ringtones", "Favorites")


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_page, container, false)

        val tablayout = view.findViewById<TabLayout>(R.id.tablayout)
        val viewPager2 = view.findViewById<ViewPager2>(R.id.viewPager2)

        val myAdapter = ViewPagerAdater(childFragmentManager, lifecycle)
        viewPager2.adapter = myAdapter

        TabLayoutMediator(tablayout, viewPager2) { tab, position ->
            tab.text = tabsArray[position]
        }.attach()

        return view
    }

}
