package com.soo.github.ui.userdetail.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.soo.github.constants.Constants.POSITION
import com.soo.github.constants.Constants.USERNAME
import com.soo.github.ui.userdetail.UserOverViewFragment
import com.soo.github.ui.userdetail.UserRepoAndStarredFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, private val userName: String) :
    FragmentStatePagerAdapter(
        fragmentManager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
    private val titles = mutableListOf("OVERVIEW", "REPOSITORIES", "STARRED")

    private val fragmentList: List<Fragment> = listOf(
        UserOverViewFragment(),
        UserRepoAndStarredFragment(),
        UserRepoAndStarredFragment()
    )

    override fun getPageTitle(position: Int): CharSequence? = titles[position]

    override fun getItem(position: Int): Fragment {
        return fragmentList[position].apply {
            arguments = bundleOf(
                POSITION to position,
                USERNAME to userName
            )
        }
    }

    override fun getCount(): Int = fragmentList.count()


}