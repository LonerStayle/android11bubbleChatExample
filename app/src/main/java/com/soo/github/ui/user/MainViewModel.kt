package com.soo.github.ui.user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soo.github.base.BaseViewModel
import com.soo.github.network.model.User
import com.soo.github.network.repository.GithubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(private val githubRepository: GithubRepository) :
    BaseViewModel() {

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    fun getUserList() {
        showLoading()
        githubRepository.getUserList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _userList.value = it
                hideLoading()
            }, {
                Timber.e("${it.printStackTrace()}")
                errorMessage.value = it.message
            })
            .addTo(disposable)
    }

    fun showUserDetail(data: User) {
        _user.value = data
    }
}