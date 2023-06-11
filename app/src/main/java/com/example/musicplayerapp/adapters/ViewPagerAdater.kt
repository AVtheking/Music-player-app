package com.example.musicplayerapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.musicplayerapp.Fragments.AlbumsFragment
import com.example.musicplayerapp.Fragments.FavouriteFragment
import com.example.musicplayerapp.Fragments.HomeFragment
import com.example.musicplayerapp.Fragments.RingtonesFragment

class ViewPagerAdater(fragmentmanager:FragmentManager,lifecycle: Lifecycle):FragmentStateAdapter(fragmentmanager,lifecycle) {
      private val Fragment_size=4
    override fun getItemCount(): Int {
        return Fragment_size
    }

    override fun createFragment(position: Int): Fragment {
        when(position)
        {
         0->return HomeFragment()
         1-> return AlbumsFragment()
         2-> return RingtonesFragment()
         3-> return FavouriteFragment()
        }
        return HomeFragment()
    }

}