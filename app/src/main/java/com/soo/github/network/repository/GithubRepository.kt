package com.soo.github.network.repository

import com.soo.github.network.model.User
import com.soo.github.network.model.UserOverView
import com.soo.github.network.model.UserRepo
import com.soo.github.network.remote.GithubApiService
import com.soo.github.network.remote.GithubRemoteDataSource
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GithubRepository(private val githubApiService: GithubApiService) :
    GithubRemoteDataSource {

    private val ioScheduler = Schedulers.io()

    override fun getUserList(): Single<List<User>> {
        return githubApiService.getUserList().subscribeOn(ioScheduler)
    }

    override fun getUserOverView(user: String): Single<UserOverView> {
        return githubApiService.getUserOverView(user).subscribeOn(ioScheduler)
    }

    override fun getUserRepos(user: String): Single<List<UserRepo>> {
        return githubApiService.getUserRepos(user).subscribeOn(ioScheduler)
    }

    override fun getUserStarred(user: String): Single<List<UserRepo>> {
        return githubApiService.getUserStarred(user).subscribeOn(ioScheduler)
    }

}