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
    @Headers("Authorization: token 618184e7f0c79f82a115e749e74951b2a9c1ad1c")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 618184e7f0c79f82a115e749e74951b2a9c1ad1c")
    fun getUserDetail(
        @Path("username") username : String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 618184e7f0c79f82a115e749e74951b2a9c1ad1c")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 618184e7f0c79f82a115e749e74951b2a9c1ad1c")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}