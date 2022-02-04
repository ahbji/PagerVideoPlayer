package com.codingnight.android.pagerplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlayerFragment(private val url: String) : Fragment() {

    private val mediaPlayer = MediaPlayer()
    private lateinit var videoSurface: SurfaceView
    private lateinit var videoProgressBar: ProgressBar
    private lateinit var loadingBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoSurface = view.findViewById(R.id.videoSurface)
        videoProgressBar = view.findViewById(R.id.videoProgressBar)
        loadingBar = view.findViewById(R.id.loading)

        mediaPlayer.apply {
            setOnPreparedListener {
                videoProgressBar.max = mediaPlayer.duration
                seekTo(1)
                loadingBar.visibility = View.INVISIBLE
            }
            setDataSource(url)
            prepareAsync()
            loadingBar.visibility = View.VISIBLE
        }
        lifecycleScope.launch {
            while (true) {
                videoProgressBar.progress = mediaPlayer.currentPosition
                delay(500)
            }
        }
        videoSurface.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
                mediaPlayer.setDisplay(holder)
                mediaPlayer.setScreenOnWhilePlaying(true)
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
            }

        })
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer.start()
        lifecycleScope.launch {
            while (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                delay(500)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }
}