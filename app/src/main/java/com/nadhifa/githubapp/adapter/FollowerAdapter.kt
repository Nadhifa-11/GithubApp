package com.nadhifa.githubapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nadhifa.githubapp.R
import com.nadhifa.githubapp.databinding.ItemUserBinding
import com.nadhifa.githubapp.model.Users

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(){
    private val users = ArrayList<Users>()

    fun setData(user : ArrayList<Users>){
        users.clear()
        users.addAll(user)
        notifyDataSetChanged()
    }

    inner class FollowerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val itemUserBinding = ItemUserBinding.bind(itemView)

        fun bind(user : Users){
            itemUserBinding.tvUsername.text = user.username
            Glide.with(itemView.context).load(user.avatar).apply(RequestOptions().override(55,55)).into(itemUserBinding.ivUser)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowerAdapter.FollowerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return FollowerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FollowerAdapter.FollowerViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size
}