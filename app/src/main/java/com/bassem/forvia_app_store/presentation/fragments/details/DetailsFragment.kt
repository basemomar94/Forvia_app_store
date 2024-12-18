package com.bassem.forvia_app_store.presentation.fragments.details

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bassem.forvia_app_store.databinding.FragmentDetailsBinding
import com.bassem.forvia_app_store.presentation.base.BaseFragment

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding {
        return FragmentDetailsBinding.inflate(layoutInflater, container, false)
    }
}