package com.example.submission2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.submission2.R
import com.example.submission2.adapter.SectionsPagerAdapter
import com.example.submission2.model.User
import com.example.submission2.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail_user.*
import kotlinx.android.synthetic.main.activity_detail_user.progressBar

class DetailUser : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel

    companion object {
        const val EXTRA_USER = "extra_user"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showLoading(true)

        val dataUser = intent.getParcelableExtra<User>(EXTRA_USER)
        supportActionBar?.title = dataUser?.username

        Glide.with(this)
            .load(dataUser?.avatar)
            .into(profile_avatar)

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        detailViewModel.setDataUser(dataUser?.username!!)
        detailViewModel.getDetailUser().observe(this, { userGithub ->
            if (userGithub != null) {
                tv_name.text = userGithub.name
                tv_company.text = userGithub.company
                tv_location.text = userGithub.location
                tv_repo.text = userGithub.repository.toString()
                tv_followers1.text = userGithub.followers.toString()
                tv_following1.text = userGithub.following.toString()
            }
            showLoading(false)
        })

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        sectionsPagerAdapter.username = dataUser.username
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}