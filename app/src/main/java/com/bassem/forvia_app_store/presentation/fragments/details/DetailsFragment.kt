package com.bassem.forvia_app_store.presentation.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bassem.forvia_app_store.databinding.FragmentDetailsBinding
import com.bassem.forvia_app_store.presentation.base.BaseFragment
import com.bassem.forvia_app_store.presentation.models.AppsUi
import com.bassem.forvia_app_store.presentation.utils.Constants.APP_DETAILS
import com.bassem.forvia_app_store.presentation.utils.loadImage

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding {
        return FragmentDetailsBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appData: AppsUi? = requireArguments().getParcelable(APP_DETAILS)
        if (appData != null) {
            updateUi(appData)
        }

    }

    private fun updateUi(appsUi: AppsUi) {
        with(appsUi) {
            withBinding {
                appNameText.text = name
                ratingText.text = rating.toFloat().toString()
                appThumbnail.loadImage(iconUrl)
            }
        }

    }
}