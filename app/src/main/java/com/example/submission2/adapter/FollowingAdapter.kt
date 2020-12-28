package com.example.submission2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission2.R
import com.example.submission2.model.User
import kotlinx.android.synthetic.main.item_follow.view.*

class FollowingAdapter : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    private val listFollowing = ArrayList<User>()

    fun setData(item: ArrayList<User>) {
        listFollowing.clear()
        listFollowing.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): FollowingAdapter.FollowingViewHolder {
        val mView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_follow, viewGroup, false)
        return FollowingViewHolder(mView)
    }

    override fun onBindViewHolder(holder: FollowingAdapter.FollowingViewHolder, position: Int) {
        holder.bind(listFollowing[position])
    }

    override fun getItemCount(): Int = listFollowing.size

    inner class FollowingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            with(itemView) {
                Glide.with(this)
                    .load(user.avatar)
                    .into(img_avatar)
                tv_username.text = user.username
            }
        }
    }

}