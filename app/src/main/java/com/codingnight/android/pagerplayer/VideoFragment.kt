package com.codingnight.android.pagerplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

private val videoUrls: List<String> = listOf(
    "https://player.vimeo.com/external/328940142.hd.mp4?s=1ea57040d1487a6c9d9ca9ca65763c8972e66bd4&profile_id=172",
    "https://player.vimeo.com/external/403777550.hd.mp4?s=2317662f5d6d3710a232e43b623e0a35d0741d21&profile_id=172",
    "https://player.vimeo.com/external/161687568.hd.mp4?s=eff398051a1dd6ceaa16b85538940b07cebede70&profile_id=119",
    "https://player.vimeo.com/external/262150448.hd.mp4?s=2c50d6eb623251f4557653c9db92b588cb66e620&profile_id=172",
    "https://player.vimeo.com/external/318978093.hd.mp4?s=76df58f90e6a7a60567f3c20837627c8d9ed7596&profile_id=172",
    "https://player.vimeo.com/external/452154245.hd.mp4?s=5a091d182204cbbbab0ccbf6027fdef1028c2233&profile_id=175",
    "https://player.vimeo.com/external/606079270.hd.mp4?s=0ea9b87a4858279451b456fbc7ca7efbfbb2903c&profile_id=172",
    "https://player.vimeo.com/external/257440813.hd.mp4?s=0aedab8a29a68dd6077a37139375cd181576541c&profile_id=175",
    "https://player.vimeo.com/external/361827510.hd.mp4?s=49acd911a57ab5141163a36180dd2681157fb678&profile_id=172",
    "https://player.vimeo.com/external/618449906.hd.mp4?s=a38276ad8cabcf0d10e0cee2cc5a0106d6c72288&profile_id=172",
)

class VideoFragment : Fragment() {

    private lateinit var videoPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoPager = view.findViewById(R.id.videoPager)

        videoPager.apply {
            adapter = object: FragmentStateAdapter(this@VideoFragment) {
                override fun getItemCount(): Int = videoUrls.size

                override fun createFragment(position: Int): Fragment = PlayerFragment(videoUrls[position])
            }
        }
    }
}