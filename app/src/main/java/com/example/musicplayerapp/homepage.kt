    package com.example.musicplayerapp

    import android.annotation.SuppressLint
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import androidx.viewpager2.widget.ViewPager2
    import com.example.musicplayerapp.adapters.ViewPagerAdater
    import com.google.android.material.tabs.TabLayout
    import com.google.android.material.tabs.TabLayoutMediator

    class Homepage : AppCompatActivity() {
        @SuppressLint("MissingInflatedId")
//        var Musiclist:ArrayList<Music> = ArrayList()
        val tabsArray = arrayOf("Home", "Albums", "Ringtones", "Favorites")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
//            requestpermission()
            setContentView(R.layout.activity_listview)
            val tablayout = findViewById<TabLayout>(R.id.tablayout)
            val viewPager2 = findViewById<ViewPager2>(R.id.viewpager2)
            val myadapter = ViewPagerAdater(supportFragmentManager, lifecycle)
            viewPager2.adapter = myadapter

            TabLayoutMediator(tablayout, viewPager2)
            { tab, postion ->
                tab.text = tabsArray[postion]
            }.attach()

//           val recyclerview=findViewById<RecyclerView>(R.id.recycleview)
//            recyclerview.layoutManager=LinearLayoutManager(this@listview)
//
//            //Set data source
//            Musiclist=getAllAudio()
//
//            //set adapter
//          val adapter=MusicAdapter(this,Musiclist)
//            recyclerview.adapter=adapter
//        }
//        //for requesting permission from user
//        private fun requestpermission()
//        {
//           if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
//                   PackageManager.PERMISSION_GRANTED)
//           {
//               ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),13)
//           }
//        }
//
//        override fun onRequestPermissionsResult(
//            requestCode: Int,
//            permissions: Array<out String>,
//            grantResults: IntArray
//        ) {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//            if(requestCode==13)
//            {
//                if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
//                {
//                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_LONG).show()
//                }
//                else
//                {
//                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),13)
//                }
//            }
//        }
//
//
//
//        @SuppressLint("Recycle", "Range", "SuspiciousIndentation")
//        private fun getAllAudio():ArrayList<Music>
//        {
//            val tempList=ArrayList<Music>()
//            val  selection="${MediaStore.Audio.Media.IS_MUSIC} != 0 AND ${MediaStore.Audio.Media.IS_RINGTONE} = 0"
//
//            val projection= arrayOf(MediaStore.Audio.Media._ID,MediaStore.Audio.Media.TITLE
//            ,MediaStore.Audio.Media.DURATION,MediaStore.Audio.Media.DATE_ADDED,MediaStore.Audio.Media.DATA,
//            MediaStore.Audio.Media.ALBUM)
//            val cursor=this.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection,
//            null,MediaStore.Audio.Media.DATE_ADDED + " DESC" ,null)
//            if(cursor!=null)
//            {
//                if(cursor.moveToFirst())
//                {
//                    do{
//                        val titleC=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
//                        val idC=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
//                        val DurationC=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
//                        val DateC=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATE_ADDED))
//                        val pathC=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
//                        val albumC=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
//                        val music=Music(idC,titleC, DurationC,pathC,albumC)
//                        val file= File(music.path)
//                            if(file.exists())
//                                tempList.add(music)
//                    }while(cursor.moveToNext())
//                    cursor.close()
//                }
//            }
//
//            return tempList
//        }
        }
    }