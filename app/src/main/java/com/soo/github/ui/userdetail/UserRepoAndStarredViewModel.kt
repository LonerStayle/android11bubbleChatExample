package com.soo.github.ui.userdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soo.github.base.BaseViewModel
import com.soo.github.network.model.UserRepo
import com.soo.github.network.repository.GithubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class UserRepoAndStarredViewModel @ViewModelInject constructor(private val githubRepository: GithubRepository) :
    BaseViewModel() {

    private val _userRepositories = MutableLiveData<List<UserRepo>>()
    val userRepositories: LiveData<List<UserRepo>> get() = _userRepositories

    private val _userStarred = MutableLiveData<List<UserRepo>>()
    val userStarred: LiveData<List<UserRepo>> get() = _userStarred

    fun getUserRepositories(userName: String) {
        showLoading()
        githubRepository.getUserRepos(userName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _userRepositories.value = it
                hideLoading()
            }, {
                Timber.e("${it.printStackTrace()}")
                errorMessage.value = it.message
            })
            .addTo(disposable)
    }

    fun getUserStarred(userName: String) {
        showLoading()
        githubRepository.getUserStarred(userName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _userStarred.value = it
                hideLoading()
            }, {
                Timber.e("${it.printStackTrace()}")
                errorMessage.value = it.message
            })
            .addTo(disposable)

    }

}