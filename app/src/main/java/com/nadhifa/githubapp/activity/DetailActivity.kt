package com.nadhifa.githubapp.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.nadhifa.githubapp.R
import com.nadhifa.githubapp.databinding.ActivityDetailBinding
import com.nadhifa.githubapp.model.Users
import com.nadhifa.githubapp.viewmodel.DetailViewModel
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var tvFollowers: TextView
    private lateinit var tvFollowing: TextView

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        val username = intent.getStringArrayListExtra(EXTRA_USERNAME) as String

        setViewModel(username = username)
        showProgressBar(false)
    }

    private fun showProgressBar(state: Boolean) {
        if (state) {
            detailBinding.PbDetail.visibility = View.VISIBLE
        } else {
            detailBinding.PbDetail.visibility = View.GONE
        }
    }

    private fun setViewModel(username: String) {
        detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)
        detailViewModel.setDetailUser(username, this)
        detailViewModel.getDetailUser().observe(this, { user ->
            if (user != null) {
                setData(user)
                showProgressBar(false)
            }
        })
    }

    private fun setData(user: Users) {
        detailBinding.tvNameDetail.text = user.name
//        detailBinding.tvLocationDetail.text = user.location
        tvFollowers.text = user.followers.toString()
        tvFollowing.text = user.following.toString()

        Glide.with(this).load(user.avatar).into(detailBinding.ivDetail)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = user.username
        }
    }
}