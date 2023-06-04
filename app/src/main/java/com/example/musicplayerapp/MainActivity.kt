package com.example.musicplayerapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import org.w3c.dom.Text
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    var starttime=0.0
    var endtime=0.0
    var Onetimeonly=0
    var mediaPlayer:MediaPlayer=MediaPlayer()
    var handler: Handler =Handler()
    var forward_time=10000
    var rewind_time=10000
    var flag=1
    lateinit var timeleft:TextView
    lateinit var seek_bar:SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rewind: Button = findViewById(R.id.rewind)
        val forward: Button = findViewById(R.id.forward)
        val play_btn: Button = findViewById(R.id.play_btn)

        val title: TextView = findViewById(R.id.title)
        seek_bar = findViewById(R.id.seek_bar)
        timeleft = findViewById(R.id.timeleft)

        //mediaplayer
        mediaPlayer = MediaPlayer.create(this, R.raw.hanuman_chalisa)
        seek_bar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser)
                {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        }
        )




        play_btn.setOnClickListener()
        {
            if(flag==1) {
                play_btn.setBackgroundResource(R.drawable.pause)
                mediaPlayer.start()

                endtime = mediaPlayer.duration.toDouble()
                starttime = mediaPlayer.currentPosition.toDouble()

                if (Onetimeonly == 0) {
                    seek_bar.max = endtime.toInt()
                    Onetimeonly = 1
                }
                if (starttime == endtime)
                    starttime = 0.00

                timeleft.text = starttime.toString()
                seek_bar.setProgress(starttime.toInt())

                handler.postDelayed(UpdateSongtime, 1000)
                flag=0
            }
            else
            {
                play_btn.setBackgroundResource(R.drawable.play)
                mediaPlayer.pause()
                flag=1
            }
        }


        title.text = "" + resources.getResourceEntryName(R.raw.hanuman_chalisa)
        forward.setOnClickListener() {
            var temp = starttime.toInt()
            if ((temp + forward_time) <= endtime) {
                starttime = starttime + forward_time
                mediaPlayer.seekTo(starttime.toInt())
            }
            else
            {
                starttime=endtime
                mediaPlayer.seekTo(starttime.toInt())
            }
        }
        rewind.setOnClickListener()
        {
            var temp=starttime.toInt()
            if((temp-rewind_time)>0)
            {
                starttime=starttime-rewind_time
                mediaPlayer.seekTo(starttime.toInt())
            }
            else
            {
                starttime=0.00
                mediaPlayer.seekTo(starttime.toInt())
            }

        }

            }

    val UpdateSongtime:Runnable=object:Runnable
    {
        override fun run() {
            starttime=mediaPlayer.currentPosition.toDouble()
            timeleft.text= "" +
                    String.format(
                        "%d min , %d sec",
                        TimeUnit.MILLISECONDS.toMinutes(starttime.toLong()),
                        TimeUnit.MILLISECONDS.toSeconds(starttime.toLong())-
                                TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(starttime.toLong())
                                )
                    )
            seek_bar.progress=starttime.toInt()
            handler.postDelayed(this,100)
        }

    }
}