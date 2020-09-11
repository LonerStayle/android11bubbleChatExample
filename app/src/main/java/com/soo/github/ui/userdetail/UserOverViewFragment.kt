package com.soo.github.ui.userdetail

import android.os.Bundle
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.soo.github.R
import com.soo.github.base.BaseFragment
import com.soo.github.constants.Constants
import com.soo.github.databinding.FragmentUserOverViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserOverViewFragment :
    BaseFragment<FragmentUserOverViewBinding>(layoutRes = R.layout.fragment_user_over_view) {

    private val userName by lazy { arguments?.getString(Constants.USERNAME) ?: "" }
    override val viewModel by viewModels<UserOverViewModel>()

    override fun setupViewModel() {
        binding.vm = viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getUserOverView(userName)
        setupBind()
    }

    private fun setupBind() {
        viewModel.userOverView.observe(viewLifecycleOwner, Observer {
            binding.userOverView = it
            Glide.with(binding.ivUserImage.context).load(it.avatarUrl).into(binding.ivUserImage)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        })
    }
}
