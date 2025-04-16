package com.example.week3.ui.player

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.week3.R
import com.example.week3.databinding.ActivitySongBinding
import com.example.week3.model.Song

class SongActivity : AppCompatActivity() {

    lateinit var binding: ActivitySongBinding
    lateinit var song: Song
    lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 플레이어 닫기 아이콘에 대한 클릭 리스너
        binding.songBtnPlayerCloseIb.setOnClickListener {
            finish()
        }

        // 재생-정지 아이콘에 대한 클릭 리스너
        binding.songMiniplayerIv.setOnClickListener {
            setPlayerStatus(false)
        }
        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(true)
        }

        // intent로 받은 song에 대한 정보를 전역변수에 저장
        initSong()
        // song에 대한 정보를 player에 적용
        setPlayer(song)

        // 반복재생 아이콘 클릭시 색상 변경
        var isRepeat = false
        binding.songRepeatIv.setOnClickListener {
            isRepeat = !isRepeat

            if (isRepeat) {
                binding.songRepeatIv.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.select_color
                    )
                )
            } else {
                binding.songRepeatIv.clearColorFilter()
            }
        }

        // 반복재생 아이콘 클릭시 색상 변경
        var isRandom = false
        binding.songRandomIv.setOnClickListener {
            isRandom = !isRandom

            if (isRandom) {
                binding.songRandomIv.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.select_color
                    )
                )
            } else {
                binding.songRandomIv.clearColorFilter()
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        timer.interrupt()
    }

    // intent로 받은 song에 대한 정보를 전역변수에 저장
    private fun initSong() {
        if (intent.hasExtra("title") && intent.hasExtra("singer") && intent.hasExtra("cover")) {
            song = Song(
                title = intent.getStringExtra("title")!!,
                singer = intent.getStringExtra("singer")!!,
                cover = intent.getIntExtra("cover", -1),
                second = intent.getIntExtra("second", 0),
                playTime = intent.getIntExtra("playTime", 0),
                isPlaying = intent.getBooleanExtra("isPlaying", false),
            )
            startTimer()
        }
    }

    // song에 대한 정보를 player에 적용
    @SuppressLint("DefaultLocale")
    private fun setPlayer(song: Song) {
        binding.songTitleTv.text = intent.getStringExtra("title")
        binding.songSingerTv.text = intent.getStringExtra("singer")

        val resId = intent.getIntExtra("cover", -1)
        if (resId != -1) {
            binding.songCoverIv.setImageResource(resId)
        }

        binding.songStartTimeTv.text = String.format("%02d:%02d", song.second / 60, song.second % 60)
        binding.songEndTimeTv.text = String.format("%02d:%02d", song.playTime / 60, song.playTime % 60)
        binding.songProgressSb.progress = (song.second * 1000 / song.playTime)
        setPlayerStatus(song.isPlaying)
    }

    // 플레이어 재생-정지 버튼 설정
    private fun setPlayerStatus(isPlaying: Boolean) {
        song.isPlaying = isPlaying
        timer.isPlaying = isPlaying

        if (isPlaying) {
            // 현 상태: 음악 재생중 (아이콘: 멈추기 버튼)
            // 기대 값: 음악 멈춤 (아이콘: 재생 버튼)
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        } else {
            // 현 상태: 음악 멈춤 (아이콘: 재생 버튼)
            // 기대 값: 음악 재생 (아이콘: 멈추기 버튼)
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
        }
    }

    private fun startTimer() {
        timer = Timer(playTime = song.playTime, isPlaying = song.isPlaying)
        timer.start()
    }

    inner class Timer(private val playTime: Int, var isPlaying: Boolean = true): Thread()  {
        private var second: Int = 0
        private var mills: Float = 0f

        @SuppressLint("DefaultLocale")
        override fun run() {
            super.run()
            try {
                while (true) {
                    if (second >= playTime) {
                        break
                    }

                    if (isPlaying) {
                        sleep(50)
                        mills += 50

                        runOnUiThread {
                            binding.songProgressSb.progress = ((mills / playTime)*100).toInt()
                        }

                        if (mills % 1000 == 0f) {
                            runOnUiThread {
                                binding.songStartTimeTv.text = String.format("%02d:%02d", second / 60, second % 60)
                            }
                            second++
                        }
                    }
                }
            } catch (e: InterruptedException) {
                Log.d("Song", "Thread 죽음: ${e.message}")
            }

        }
    }
}