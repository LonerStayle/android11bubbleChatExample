package com.soo.github.ui.userdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soo.github.base.BaseViewModel
import com.soo.github.network.model.UserOverView
import com.soo.github.network.repository.GithubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class UserOverViewModel @ViewModelInject constructor(private val githubRepository: GithubRepository) :
    BaseViewModel() {

    private val _userOverView = MutableLiveData<UserOverView>()
    val userOverView: LiveData<UserOverView>
        get() = _userOverView

    fun getUserOverView(userName: String) {
        showLoading()
        githubRepository.getUserOverView(userName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _userOverView.postValue(it)
                hideLoading()
            }, {
                Timber.e("${it.printStackTrace()}")
                errorMessage.value = it.message
            })
            .addTo(disposable)
    }

}