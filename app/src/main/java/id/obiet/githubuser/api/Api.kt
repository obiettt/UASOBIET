package id.obiet.githubuser.api

import id.obiet.githubuser.data.model.DetailUserResponse
import id.obiet.githubuser.data.model.User
import id.obiet.githubuser.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 9a07242bfb520bbc3ab8fb0a35abb7668b02b698")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 9a07242bfb520bbc3ab8fb0a35abb7668b02b698")
    fun getUserDetail(
        @Path("username") username : String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 9a07242bfb520bbc3ab8fb0a35abb7668b02b698")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 9a07242bfb520bbc3ab8fb0a35abb7668b02b698")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}