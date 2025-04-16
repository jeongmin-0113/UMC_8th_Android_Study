package com.example.week3.ui.locker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.week3.databinding.FragmentDeviceMusicBinding

class DeviceMusicFragment: Fragment() {

    lateinit var binding: FragmentDeviceMusicBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeviceMusicBinding.inflate(layoutInflater)

        return binding.root
    }
}