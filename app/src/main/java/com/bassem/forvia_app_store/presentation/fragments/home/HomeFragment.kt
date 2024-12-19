package com.bassem.forvia_app_store.presentation.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bassem.forvia_app_store.databinding.FragmentHomeBinding
import com.bassem.forvia_app_store.presentation.base.BaseFragment
import com.bassem.forvia_app_store.presentation.viewmodels.HomeViewModel
import com.bassem.forvia_app_store.utils.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val log = Logger("HomeFragment")
    private val viewModel: HomeViewModel by viewModels()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectApps()
    }


    private fun collectApps() = lifecycleScope.launch {
        viewModel.appsList.collect {
            log.i("got apps $it")

        }
    }
}