package com.example.android_mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_advanced_kotlin.activity.model.Post
import com.example.android_mvvm.respository.MainRepository

class MainViewModel : ViewModel() {
     var allPosts: MutableLiveData<ArrayList<Post>>? = null
     var deletedPost: MutableLiveData<Post>? = null

    fun apiPostList(): LiveData<ArrayList<Post>>?{
        allPosts = MainRepository.apiPostList()
        return allPosts
    }

    fun apiPostDelete(post: Post): LiveData<Post>?{
        deletedPost = MainRepository.apiPostDelete(post)
        return deletedPost
    }
}