package com.example.submission2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission2.model.User
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class DetailViewModel : ViewModel() {
    private val detailUser = MutableLiveData<User>()

    fun setDataUser(username: String) {
        val apiKey = "3e28fd18e4f7e5f5cd11eba05a129b27288f1b83"
        val url = "https://api.github.com/users/$username"

        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token: $apiKey")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val result = String(responseBody!!)
                try {
                    val responseObject = JSONObject(result)
                    val user = User()
                    user.name = responseObject.getString("name")
                    if (user.name == "null") {
                        user.name = "-"
                    } else {
                        user.name = user.name
                    }
                    user.repository = responseObject.getInt("public_repos")
                    user.company = responseObject.getString("company")
                    if (user.company == "null") {
                        user.company = "-"
                    } else {
                        user.company = user.company
                    }
                    user.location = responseObject.getString("location")
                    if (user.location == null) {
                        user.location = "-"
                    } else {
                        user.location = user.location
                    }
                    user.followers = responseObject.getInt("followers")
                    user.following = responseObject.getInt("following")
                    detailUser.postValue(user)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Log.d("DetailViewModel", errorMessage)
            }
        })
    }
    fun getDetailUser() : LiveData<User> {
        return detailUser
    }
}