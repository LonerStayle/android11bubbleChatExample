package com.soo.github.ui.userdetail

import android.os.Bundle
import android.util.ArrayMap
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.soo.github.R
import com.soo.github.base.BaseAdapter
import com.soo.github.base.BaseFragment
import com.soo.github.base.BaseViewModel
import com.soo.github.constants.Constants
import com.soo.github.constants.Constants.POSITION
import com.soo.github.databinding.FragmentUserRepositoryBinding
import com.soo.github.network.model.UserRepo
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserRepoAndStarredFragment :
    BaseFragment<FragmentUserRepositoryBinding>(layoutRes = R.layout.fragment_user_repository) {

    private val userName by lazy { arguments?.getString(Constants.USERNAME) }
    private val position by lazy { arguments?.getInt(POSITION) }

    override val viewModel by viewModels<UserRepoAndStarredViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showContent()
        setupAdapter()
        setupObserve()
    }

    override fun setupViewModel() {
        binding.vm = viewModel
    }

    private fun showContent() {
        when (position) {
            1 -> userName?.let { viewModel.getUserRepositories(it) }
            2 -> userName?.let { viewModel.getUserStarred(it) }
        }
    }

    private fun setupAdapter() {
        when (position) {
            1 -> binding.rvUserRepo.adapter = BaseAdapter<UserRepo>(
                layoutBindingId = Pair(R.layout.item_user_repository, BR.userRepo),
                viewModels = ArrayMap<Int, BaseViewModel>().apply {
                    put(BR.vm, viewModel)
                }
            )
            2 -> binding.rvUserStarred.adapter = BaseAdapter<UserRepo>(
                layoutBindingId = Pair(R.layout.item_user_starred, BR.userRepo),
                viewModels = ArrayMap<Int, BaseViewModel>().apply {
                    put(BR.vm, viewModel)
                }
            )
        }
    }

    private fun setupObserve(){
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        })
    }

}
