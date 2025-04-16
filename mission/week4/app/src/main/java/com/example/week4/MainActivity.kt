package com.example.week4

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import androidx.lifecycle.lifecycleScope
import com.example.week4.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timer = Timer()

        binding.timerStartBtn.setOnClickListener {
            setTimerStatus(state = false)
            timer.start()
        }
        binding.timerPauseBtn.setOnClickListener {
            setTimerStatus(state = true)
            timer.pause()
        }
        binding.timerClearBtn.setOnClickListener {
            timer.clear()
        }


    }

    // 재생-정지 버튼 설정
    private fun setTimerStatus(state: Boolean) {

        if (state) {
            // 현 상태: 타이머 시간 흐르는 중 (아이콘: 멈추기 버튼)
            // 기대 값: 타이머 멈춤 (아이콘: 재생 버튼)
            binding.timerStartBtn.visibility = View.VISIBLE
            binding.timerPauseBtn.visibility = View.GONE
        } else {
            // 현 상태: 타이머 멈춤 (아이콘: 재생 버튼)
            // 기대 값: 타이머 시간 흐르기 (아이콘: 멈추기 버튼)
            binding.timerStartBtn.visibility = View.GONE
            binding.timerPauseBtn.visibility = View.VISIBLE
        }
    }

    inner class Timer() {
        private var milliSec = 0L
        private var isRunning = false
        private var job: Job? = null

        fun start() {
            isRunning = true
            job = lifecycleScope.launch {
                while (isRunning) {
                    delay(1L)
                    milliSec++
                    updateUI()
                }
            }
        }

        fun pause() {
            isRunning = false
            job?.cancel()
        }

        fun clear() {
            pause()
            milliSec = 0
            updateUI()
            setTimerStatus(state = true)
        }

        @SuppressLint("DefaultLocale")
        private fun updateUI() {
            val mins = (milliSec / 1000) / 60
            val secs = (milliSec / 1000) % 60
            val mills = (milliSec % 1000) / 10
            val time = String.format("%02d:%02d.%02d", mins, secs, mills)
            binding.timerTv.text = time
        }
    }
}