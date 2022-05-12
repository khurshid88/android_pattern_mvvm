package com.example.android_mvc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.android_advanced_kotlin.activity.model.Post
import com.example.android_mvc.activity.MainActivity
import com.example.android_mvvm.R
import com.example.android_mvvm.utils.Utils

class PostAdapter(var activity: MainActivity, var items: ArrayList<Post>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_poster_list, parent, false)
        return PosterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post: Post = items[position]
        if (holder is PosterViewHolder) {
            val ll_poster = holder.ll_poster
            val tv_title = holder.tv_title
            val tv_body = holder.tv_body
            tv_title.setText(post.title.toUpperCase())
            tv_body.setText(post.body)

            ll_poster.setOnLongClickListener {
                deletePostDialog(post)
                false
            }
        }
    }

    inner class PosterViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var ll_poster: LinearLayout
        var tv_title: TextView
        var tv_body: TextView

        init {
            ll_poster = view.findViewById(R.id.ll_poster)
            tv_title = view.findViewById(R.id.tv_title)
            tv_body = view.findViewById(R.id.tv_body)
        }
    }

    private fun deletePostDialog(post: Post) {
        val title = "Delete"
        val body = "Do you want to delete?"
        Utils.customDialog(activity, title, body, object : Utils.DialogListener {
            override fun onPositiveClick() {
                activity.viewModel.apiPostDelete(post)
                activity.viewModel.deletedPost.observe(activity, Observer {
                    activity.viewModel.apiPostList()
                })
            }

            override fun onNegativeClick() {

            }
        })
    }
}