package com.soo.github.network.remote

import com.soo.github.network.model.User
import com.soo.github.network.model.UserOverView
import com.soo.github.network.model.UserRepo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {
    @GET("/users")
    fun getUserList(): Single<List<User>>

    @GET("/users/{user}")
    fun getUserOverView(@Path("user") user: String): Single<UserOverView>

    @GET("/users/{user}/repos")
    fun getUserRepos(@Path("user") user: String): Single<List<UserRepo>>

    @GET("/users/{user}/starred")
    fun getUserStarred(@Path("user") user: String): Single<List<UserRepo>>
}