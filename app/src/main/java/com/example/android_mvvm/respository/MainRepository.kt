package com.example.android_mvvm.respository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.android_advanced_kotlin.activity.model.Post
import com.example.android_mvc.network.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainRepository {
    val allPosts = MutableLiveData<ArrayList<Post>>()
    val deletedPost = MutableLiveData<Post>()

    fun apiPostList(): MutableLiveData<ArrayList<Post>>? {
        RetrofitHttp.postService.listPost().enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(
                call: Call<ArrayList<Post>>,
                response: Response<ArrayList<Post>>
            ) {
                Log.d("@@@allPosts ",allPosts.toString())
                allPosts.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                Log.d("@@@allPosts ",t.toString())
                allPosts.value = null
            }
        })
        return allPosts
    }

    fun apiPostDelete(post: Post): MutableLiveData<Post>? {
        RetrofitHttp.postService.deletePost(post.id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                deletedPost.value = response.body()
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                deletedPost.value = null
            }
        })
        return deletedPost
    }

}