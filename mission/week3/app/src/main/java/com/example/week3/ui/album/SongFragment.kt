package com.example.week3.ui.album

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.week3.databinding.FragmentSongBinding

class SongFragment : Fragment() {

    lateinit var binding: FragmentSongBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(layoutInflater)

        binding.songLalacLayout.setOnClickListener {
            val song_title = binding.songMusicTitle01Tv.text
            Toast.makeText(activity, "${song_title}이(가) 재생목록에 추가되었습니다.", Toast.LENGTH_SHORT).show()
        }

        setupSongMixTgClickListener()

        return binding.root
    }

    private fun setupSongMixTgClickListener() {
        binding.songMixonTg.setOnClickListener {
            setToggleSwitchStatus(true)
        }

        binding.songMixoffTg.setOnClickListener {
            setToggleSwitchStatus(false)
        }
    }

    private fun setToggleSwitchStatus(isToggleOn: Boolean) {
        if (isToggleOn) {
            // 현재 값: 토글이 켜져있음
            // 기대 값: 토글 끄기
            binding.songMixonTg.visibility = View.GONE
            binding.songMixoffTg.visibility = View.VISIBLE
        } else {
            // 현재 값: 토글이 꺼져있음
            // 기대 값: 토글 켜기
            binding.songMixonTg.visibility = View.VISIBLE
            binding.songMixoffTg.visibility = View.GONE
        }
    }
}