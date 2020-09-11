package com.soo.github.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val disposable = CompositeDisposable()
    val errorMessage = MutableLiveData<String>()

    protected val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    protected fun hideLoading() {
        _loadingState.value = false
    }

    protected fun showLoading() {
        _loadingState.value = true
    }
}
