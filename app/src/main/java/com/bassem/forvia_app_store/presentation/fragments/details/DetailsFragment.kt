package com.bassem.forvia_app_store.presentation.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.bassem.forvia_app_store.R
import com.bassem.forvia_app_store.databinding.FragmentDetailsBinding
import com.bassem.forvia_app_store.presentation.base.BaseFragment
import com.bassem.forvia_app_store.presentation.models.AppsUi
import com.bassem.forvia_app_store.presentation.utils.Constants.APP_DETAILS
import com.bassem.forvia_app_store.presentation.utils.formatToLocalDate
import com.bassem.forvia_app_store.presentation.utils.loadImage

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(), View.OnClickListener {
    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding {
        return FragmentDetailsBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.downloadButton?.setOnClickListener(this)

        requireArguments().getParcelable<AppsUi>(APP_DETAILS)?.let { appData ->
            updateUi(appData)
        }
    }

    private fun updateUi(appsUi: AppsUi) {
        with(appsUi) {
            withBinding {
                appNameText.text = name
                ratingText.text = "$rating"
                appThumbnail.loadImage(iconUrl)
                versionValue.text = "$versionCode"
                storeValue.text = storeName
                modifiedValue.text = formatToLocalDate(modifiedAt)
                appSizeValue.text = "$size"
            }
        }

    }

    override fun onClick(view: View?) {
        when (view) {
            binding?.downloadButton -> {
                AlertDialog.Builder(requireContext())
                    .setTitle(R.string.download_unavailable)
                    .setIcon(R.drawable.baseline_error_24)
                    .setPositiveButton(R.string.ok) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}