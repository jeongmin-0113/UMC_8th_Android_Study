package com.example.week3.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.week3.R
import com.example.week3.model.Song
import com.example.week3.databinding.FragmentPlaylistRecommendBinding

class PlaylistRecommendFragment(
    val title: String = "",
    val backgroundImg: Int = R.drawable.img_default_4_x_1,
    val songList: ArrayList<Song> = arrayListOf(
        Song("Butter", "BTS", R.drawable.img_album_exp),
        Song("LILAC", "아이유 (IU)", R.drawable.img_album_exp2)
    )
) : Fragment() {

    lateinit var binding: FragmentPlaylistRecommendBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaylistRecommendBinding.inflate(layoutInflater)

        binding.homePanelTitleTv.text = title
        binding.homePanelBackgroundIv.setImageResource(backgroundImg)

        binding.homePanelAlbum1ImgIv.setImageResource(songList[0].cover)
        binding.homePanelAlbum1TitleTv.text = songList[0].title
        binding.homePanelAlbum1SingerTv.text = songList[0].singer

        binding.homePanelAlbum2ImgIv.setImageResource(songList[1].cover)
        binding.homePanelAlbum2TitleTv.text = songList[1].title
        binding.homePanelAlbum2SingerTv.text = songList[1].singer

        return binding.root
    }
}