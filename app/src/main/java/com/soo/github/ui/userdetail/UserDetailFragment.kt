package com.soo.github.ui.userdetail

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.soo.github.R
import com.soo.github.base.BaseFragment
import com.soo.github.base.BaseViewModel
import com.soo.github.databinding.FragmentUserDetailBinding
import com.soo.github.ui.userdetail.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_detail.*

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<FragmentUserDetailBinding>(
    R.layout.fragment_user_detail
) {
    private lateinit var navController: NavController
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val name: UserDetailFragmentArgs by navArgs()

    override val viewModel: BaseViewModel = BaseViewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupAdapter()
        setupBackBtn()
        navController = Navigation.findNavController(this.requireActivity(), R.id.product_nav_host_fragment)
    }

    private fun setupToolbar() = with(toolbar) {
        title = name.takeUserNames
        setNavigationIcon(R.drawable.ic_arrow_back_24px)
        setNavigationOnClickListener {
            navController.navigate(R.id.action_detail_to_main)
        }
    }

    private fun setupAdapter() {
        viewPagerAdapter = ViewPagerAdapter(requireParentFragment().parentFragmentManager, name.takeUserNames)
        binding.vp.adapter = viewPagerAdapter
        binding.layoutTap.setupWithViewPager(binding.vp)
    }

    private fun setupBackBtn() {
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navController.navigate(R.id.action_detail_to_main)
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(callBack)
    }

}
