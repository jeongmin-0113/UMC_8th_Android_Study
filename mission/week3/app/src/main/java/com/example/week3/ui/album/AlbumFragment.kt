package com.example.week3.ui.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.week3.ui.MainActivity
import com.example.week3.R
import com.example.week3.databinding.FragmentAlbumBinding
import com.example.week3.ui.main.HomeFragment
import com.google.android.material.tabs.TabLayoutMediator

class AlbumFragment : Fragment() {

    lateinit var binding: FragmentAlbumBinding
    private val tabInfo = arrayListOf("수록곡", "상세정보", "영상")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        setupBackBtnClickListener()

        val albumAdapter = AlbumVPAdapter(this)
        binding.albumContentVp.apply {
            adapter = albumAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        TabLayoutMediator(binding.albumContentTb, binding.albumContentVp) { tab, position ->
            tab.text = tabInfo[position] // 탭뷰의 아이템 이름 설정
        }.attach() // 탭뷰와 vp 연결

        return binding.root
    }

    private fun setupBackBtnClickListener() {
        binding.albumBackIv.setOnClickListener {
            (context as MainActivity)
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }
    }
}