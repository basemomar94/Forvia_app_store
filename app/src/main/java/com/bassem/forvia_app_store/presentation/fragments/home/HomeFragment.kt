package com.bassem.forvia_app_store.presentation.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bassem.forvia_app_store.data.models.ApiResult
import com.bassem.forvia_app_store.data.models.Responses
import com.bassem.forvia_app_store.databinding.FragmentHomeBinding
import com.bassem.forvia_app_store.presentation.adapter.EditorChoiceAdapter
import com.bassem.forvia_app_store.presentation.adapter.OnItemClickListener
import com.bassem.forvia_app_store.presentation.adapter.SmallItemsAdapter
import com.bassem.forvia_app_store.presentation.base.BaseFragment
import com.bassem.forvia_app_store.presentation.models.AppsUi
import com.bassem.forvia_app_store.presentation.viewmodels.HomeViewModel
import com.bassem.forvia_app_store.utils.Logger
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselStrategy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), OnItemClickListener {
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
        viewModel.appsList.collect { apps ->

            when (apps) {
                is ApiResult.Success -> {
                    val appsList = apps.data as List<AppsUi>
                    populateLocalApps(appsList)
                    populateEditorChoiceApps(appsList)
                }

                is ApiResult.Fail -> {

                }

                is ApiResult.Loading -> {

                }

                null -> {

                }
            }
        }
    }

    private fun populateLocalApps(list: List<AppsUi>) {
        val adapter = SmallItemsAdapter(list, this)
        withBinding {
            localTopAppsRv.adapter = adapter
            localTopAppsRv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            localTopAppsRv.setHasFixedSize(true)

        }
    }

    private fun populateEditorChoiceApps(list: List<AppsUi>) {
        val adapter = EditorChoiceAdapter(list, this)
        val carouselLayoutManager = CarouselLayoutManager()
        carouselLayoutManager.setOrientation(CarouselLayoutManager.HORIZONTAL)
        carouselLayoutManager.setCarouselAlignment(CarouselLayoutManager.ALIGNMENT_CENTER)

        withBinding {
            editorsChoiceRv.adapter = adapter
            editorsChoiceRv.layoutManager =
             carouselLayoutManager

            localTopAppsRv.setHasFixedSize(true)

        }
    }

    override fun onItemClick(item: AppsUi) {

    }
}