package com.bassem.forvia_app_store.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * A base fragment class that simplifies the usage of ViewBinding.
 *
 * @param VB The type of the ViewBinding associated with the fragment.
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    val binding: VB?
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateViewBinding(inflater, container)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Inflate the ViewBinding for the fragment.
     *
     * @param inflater The LayoutInflater for the fragment.
     * @param container The parent container, if available.
     * @return The initialized ViewBinding instance.
     */
    abstract fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    /**
     * Safely execute a block of code using the ViewBinding if it is available.
     */
    inline fun withBinding(block: VB.() -> Unit) {
        binding?.let(block)
    }
}
