package com.soo.github.ui.user

import android.os.Bundle
import android.util.ArrayMap
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.soo.github.R
import com.soo.github.base.BaseAdapter
import com.soo.github.base.BaseFragment
import com.soo.github.base.BaseViewModel
import com.soo.github.constants.Constants
import com.soo.github.databinding.FragmentMainBinding
import com.soo.github.network.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(layoutRes = R.layout.fragment_main) {

    override val viewModel by viewModels<MainViewModel>()

    override fun setupViewModel() {
        binding.vm = viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
        setupObserve()
        showContents()
    }

    private fun showContents() {
        viewModel.getUserList()
    }

    private fun setupAdapter() {
        binding.rvUserList.adapter = BaseAdapter<User>(
            layoutBindingId = Pair(R.layout.item_user, BR.user),
            viewModels = ArrayMap<Int, BaseViewModel>().apply {
                put(BR.vm, viewModel)
            }
        )
    }

    private fun setupObserve() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            bundleOf(
                Constants.USERNAME to it.login
            )
            if (it.login == null) return@Observer
            val action = MainFragmentDirections.actionMainToDetail(takeUserNames = it.login)
            findNavController().navigate(action)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        })
    }

}
