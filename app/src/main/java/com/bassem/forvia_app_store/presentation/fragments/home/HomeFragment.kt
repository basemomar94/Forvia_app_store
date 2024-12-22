package com.bassem.forvia_app_store.presentation.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bassem.forvia_app_store.R
import com.bassem.forvia_app_store.data.models.ApiResult
import com.bassem.forvia_app_store.databinding.FragmentHomeBinding
import com.bassem.forvia_app_store.presentation.adapter.EditorChoiceAdapter
import com.bassem.forvia_app_store.presentation.adapter.OnItemClickListener
import com.bassem.forvia_app_store.presentation.adapter.SmallItemsAdapter
import com.bassem.forvia_app_store.presentation.base.BaseFragment
import com.bassem.forvia_app_store.presentation.models.AppsUi
import com.bassem.forvia_app_store.presentation.utils.Constants.APP_DETAILS
import com.bassem.forvia_app_store.presentation.utils.getErrorMessage
import com.bassem.forvia_app_store.presentation.utils.gone
import com.bassem.forvia_app_store.presentation.utils.visible
import com.bassem.forvia_app_store.presentation.viewmodels.HomeViewModel
import com.bassem.forvia_app_store.utils.Logger
import com.google.android.material.carousel.CarouselLayoutManager
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
        viewModel.appsList.collect { result ->
            log.i("app list result is $result")

            when (result) {
                is ApiResult.Success -> {
                    withBinding {
                        progressBar.gone()
                    }
                    val appsList = result.data as List<AppsUi>
                    populateLocalApps(appsList)
                    populateEditorChoiceApps(appsList)
                }

                is ApiResult.Fail -> {
                    withBinding {
                        progressBar.gone()
                        errorMessage.visible()
                        errorMessage.text =
                            requireContext().getErrorMessage(result.errorTypes)
                    }

                }

                is ApiResult.Loading -> {
                    withBinding {
                        progressBar.visible()
                    }
                }

                null -> {

                }
            }
        }
    }

    private fun populateLocalApps(list: List<AppsUi>) {
        val adapter = SmallItemsAdapter(list, this)
        withBinding {
            localTopAppsTitle.visible()
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
            editorsChoiceTitle.visible()
            editorsChoiceRv.adapter = adapter
            editorsChoiceRv.layoutManager =
                carouselLayoutManager

            localTopAppsRv.setHasFixedSize(true)

        }
    }

    override fun onItemClick(item: AppsUi) {
        val bundle = Bundle().apply {
            putParcelable(APP_DETAILS, item)
        }
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
    }
}