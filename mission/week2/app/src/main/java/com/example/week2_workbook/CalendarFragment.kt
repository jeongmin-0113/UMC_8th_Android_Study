package com.example.week2_workbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.week2_workbook.databinding.FragmentCalendarBinding
import com.example.week2_workbook.databinding.FragmentHomeBinding

class CalendarFragment : Fragment() {
    private lateinit var viewBinding: FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewBinding = FragmentCalendarBinding.inflate(layoutInflater)
        return viewBinding.root
    }
}