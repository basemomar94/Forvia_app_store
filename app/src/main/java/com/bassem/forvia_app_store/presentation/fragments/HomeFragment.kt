package com.bassem.forvia_app_store.presentation.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bassem.forvia_app_store.databinding.FragmentHomeBinding
import com.bassem.forvia_app_store.presentation.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, container, false)
    }
}