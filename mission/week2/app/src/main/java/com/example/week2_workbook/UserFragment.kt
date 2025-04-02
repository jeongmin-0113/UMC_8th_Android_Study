package com.example.week2_workbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.week2_workbook.databinding.FragmentHomeBinding
import com.example.week2_workbook.databinding.FragmentUserBinding

class UserFragment : Fragment() {
    private lateinit var viewBinding: FragmentUserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewBinding = FragmentUserBinding.inflate(layoutInflater)
        return viewBinding.root
    }
}