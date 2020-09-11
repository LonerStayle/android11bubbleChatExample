package com.soo.github.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding>(@LayoutRes private val layoutRes: Int) :
    Fragment() {

    protected lateinit var binding: B
    abstract val viewModel: BaseViewModel

    open fun setupViewModel() = Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<B>(inflater, layoutRes, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        setupViewModel()
        return binding.root
    }

}