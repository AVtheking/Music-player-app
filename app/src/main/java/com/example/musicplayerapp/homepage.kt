    package com.example.musicplayerapp

    import android.annotation.SuppressLint
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import androidx.databinding.DataBindingUtil
    import androidx.fragment.app.Fragment
    import androidx.viewpager2.widget.ViewPager2
    import com.example.musicplayerapp.Fragments.FavFragment
    import com.example.musicplayerapp.Fragments.HomeFragment
    import com.example.musicplayerapp.Fragments.OnlineFragment
    import com.example.musicplayerapp.Fragments.playlistFragment
    import com.example.musicplayerapp.adapters.ViewPagerAdater
    import com.example.musicplayerapp.databinding.ActivityListviewBinding
    import com.google.android.material.bottomnavigation.BottomNavigationView
    import com.google.android.material.tabs.TabLayout
    import com.google.android.material.tabs.TabLayoutMediator

    class Homepage : AppCompatActivity() {


        private lateinit var bottomnavigationview: BottomNavigationView
        private lateinit var binding:ActivityListviewBinding




        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
//            requestpermission()

            binding=DataBindingUtil.setContentView(this,R.layout.activity_listview)

            switchFragment(HomeFragment())

           //bottom navigation view
            binding.bottomNavigationView.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> {
                        switchFragment(HomeFragment())
                        return@setOnItemSelectedListener true
                    }
                    R.id.navigation_online -> {
                        switchFragment(OnlineFragment())
                        return@setOnItemSelectedListener true
                    }
                    R.id.navigation_playlist -> {
                        switchFragment(playlistFragment())
                        return@setOnItemSelectedListener true
                    }
                    R.id.navigation_favorites -> {
                        switchFragment(FavFragment())
                        true
                    }
                    else -> false
                }
            }

//
        }
        private  fun switchFragment(fragment :Fragment)
        {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout,fragment).commit()
        }

    }