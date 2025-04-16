package com.example.week3.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.week3.ui.MainActivity
import com.example.week3.R
import com.example.week3.model.Song
import com.example.week3.ui.home.PanelVPAdapter
import com.example.week3.databinding.FragmentHomeBinding
import com.example.week3.ui.home.BannerVPAdapter
import com.example.week3.ui.album.AlbumFragment
import com.example.week3.ui.home.BannerFragment
import com.example.week3.ui.home.PlaylistRecommendFragment

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupAlbumClickListener()
        setupBannerViewPager()
        setupPanelViewPager()

        return binding.root
    }

    // 오늘 발매 앨범 클릭시 -> 앨범 상세 화면으로 전환
    private fun setupAlbumClickListener() {
        binding.homeAlbum1Lo.setOnClickListener {
            (context as MainActivity)
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_frm, AlbumFragment()) // 바꿀 곳(메인 액티비티의 프래그먼트 프레임 아이디) - 무엇으로 바꿀 지
                .commitAllowingStateLoss()
        }
    }

    // 배너 이미지 추가, 설정
    private fun setupBannerViewPager() {
        val bannerAdapter = BannerVPAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))

        binding.homeBannerVp.apply {
            adapter = bannerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL // 가로방향 페이지 이동
        }
    }

    // 상단 추천 플레이리스트 화면 추가, 설정
    private fun setupPanelViewPager() {
        val songList = listOf(
            Song("Butter", "BTS", R.drawable.img_album_exp),
            Song("LILAC", "아이유 (IU)", R.drawable.img_album_exp2),
            Song("Next Level", "에스파", R.drawable.img_album_exp3),
            Song("작은 것들을 위한 시", "BTS", R.drawable.img_album_exp4),
            Song("BAAM", "모모랜드", R.drawable.img_album_exp5),
            Song("Weekend", "태연", R.drawable.img_album_exp6)
        )

        val panelAdapter = PanelVPAdapter(this).apply {
            addFragment(
                PlaylistRecommendFragment(
                    title = "BTS와 함께하는\n 플레이리스트 \uD83D\uDC9C",
                    songList = arrayListOf(songList[0], songList[3])
                )
            )
            addFragment(
                PlaylistRecommendFragment(
                    title = "마른 감성을 촉촉하게\n해줄 여성 솔로 음악",
                    songList = arrayListOf(songList[2], songList[5])
                )
            )
            addFragment(
                PlaylistRecommendFragment(
                    title = "최신 걸그룹 케이팝\n플레이리스트",
                    songList = arrayListOf(songList[2], songList[4])
                )
            )
        }

        binding.homePanelBackgroundVp.apply {
            adapter = panelAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL // 가로방향 페이지 이동
        }

        // 패널 인디케이터 연결
        val indicator = binding.indicator
        indicator.setViewPager(binding.homePanelBackgroundVp)
    }

}