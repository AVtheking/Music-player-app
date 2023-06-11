package com.example.musicplayerapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayerapp.MainActivity
import com.example.musicplayerapp.Music
import com.example.musicplayerapp.R

class MusicAdapter(val context:Context  ,val Songlist:ArrayList<Music>):
    RecyclerView.Adapter<MusicAdapter.Myviewholder>() {
    inner class Myviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var musicimg: ImageView = itemview.findViewById(R.id.img)
        var musictitle: TextView = itemview.findViewById(R.id.textView)
        var album:TextView=itemview.findViewById(R.id.textView2)

       init{
           itemview.setOnClickListener()
           {
               val position=adapterPosition
               if(position!=null){
                   val intent = Intent(context, MainActivity::class.java)
                   intent.putExtra("songTitle", Songlist[adapterPosition].title)
                   intent.putExtra("songPath", Songlist[adapterPosition].path)
               context.startActivity(intent)}
           }
       }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
        val v=LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_item,parent,false)

        return Myviewholder(v)
    }

    override fun getItemCount(): Int {
            return Songlist.size
    }

    override fun onBindViewHolder(holder: Myviewholder, position: Int) {
           holder.musicimg.setImageResource(R.drawable.music_logo)
           holder.musictitle.setText(Songlist[position].title)
           holder.album.setText(Songlist[position].Album)
    }
}
