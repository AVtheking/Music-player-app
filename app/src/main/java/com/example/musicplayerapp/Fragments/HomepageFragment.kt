package com.example.musicplayerapp.Fragments

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayerapp.Music
import com.example.musicplayerapp.R
import com.example.musicplayerapp.adapters.MusicAdapter
import java.io.File


class HomepageFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MusicAdapter
    private lateinit var musicList: ArrayList<Music>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_homepage, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        musicList = getAllAudio()
        adapter = MusicAdapter(requireContext(), musicList)
        recyclerView.adapter = adapter



        return view
    }

    private fun requestPermission()
    {
        val  activity=requireActivity()
        if(ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
            PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),13)
        }


    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==13)
        {
            if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(requireContext(),"Permission Granted", Toast.LENGTH_LONG).show()
            }
            else
            {
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),13)
            }
        }
    }


    @SuppressLint("Range")
    private fun getAllAudio():ArrayList<Music>
    {
        val tempList=ArrayList<Music>()
        val  selection="${MediaStore.Audio.Media.IS_MUSIC} != 0 AND ${MediaStore.Audio.Media.IS_RINGTONE} = 0"

        val projection= arrayOf(
            MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE
            , MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.DATE_ADDED, MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ALBUM)
        val cursor=requireContext().contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection,
            null, MediaStore.Audio.Media.DATE_ADDED + " DESC" ,null)
        if(cursor!=null)
        {
            if(cursor.moveToFirst())
            {
                do{
                    val titleC=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    val idC=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                    val DurationC=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
                    val DateC=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATE_ADDED))
                    val pathC=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val albumC=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
                    val music=Music(idC,titleC, DurationC,pathC,albumC)
                    val file= File(music.path)
                    if(file.exists())
                        tempList.add(music)
                }while(cursor.moveToNext())
                cursor.close()
            }
        }

        return tempList
    }
}