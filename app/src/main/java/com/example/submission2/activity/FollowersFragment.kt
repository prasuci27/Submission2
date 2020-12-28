package com.example.submission2.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2.R
import com.example.submission2.adapter.FollowersAdapter
import com.example.submission2.viewmodel.FollowersViewModel
import kotlinx.android.synthetic.main.fragment_followers.*

class FollowersFragment : Fragment() {

    private lateinit var adapter: FollowersAdapter
    private lateinit var followersViewModel: FollowersViewModel

    companion object {
        const val EXTRA_FOLLOWERS = "extra_followers"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = FollowersAdapter()
        adapter.notifyDataSetChanged()

        showRecyclerView()

        followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        if (arguments != null) {
            val username = arguments?.getString(EXTRA_FOLLOWERS)
            followersViewModel.setFollowers(username.toString())
        }

        followersViewModel.getFollowers().observe(viewLifecycleOwner, { followers ->
            if (!followers.isNullOrEmpty()) {
                adapter.setData(followers)
            }
        })

    }

    private fun showRecyclerView() {
        rv_followers.layoutManager = LinearLayoutManager(context)
        rv_followers.adapter = adapter
    }
}