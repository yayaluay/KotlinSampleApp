package com.example.kotlinsampleapp.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.ImageLoader
import coil.api.load
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.kotlinsampleapp.R
import com.example.kotlinsampleapp.common.Const
import com.example.kotlinsampleapp.common.Logger
import com.example.kotlinsampleapp.common.VolleySingleton
import com.example.kotlinsampleapp.data.UserData
import com.example.kotlinsampleapp.databinding.FragmentDetailBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

const val USER_URL = "USER_URL"

/**
 * ユーザ詳細画面Fragment
 */
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var userUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userUrl = it.getString(USER_URL).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // JsonObjectRequest作成
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, userUrl, null,
            Response.Listener { response ->
                Logger.d("DetailFragment", response.toString())
                // ユーザ情報取得成功
                // 受信したjson文字列をUserDataオブジェクトに変換
                val listType = object : TypeToken<UserData>() {}.type
                val user = Gson().fromJson<UserData>(response.toString(), listType)
                // ユーザ画像表示
                val imageLoader = context?.let {
                    ImageLoader(it) {
                        placeholder(R.drawable.no_img)
                        error(R.drawable.no_img)
                        availableMemoryPercentage(0.1)
                        bitmapPoolPercentage(0.1)
                    }
                }
                if (imageLoader != null) {
                    binding.imgView.load(user.avatar_url, imageLoader)
                }
                // login表示
                if (user.login != null) {
                    binding.login.visibility = View.VISIBLE
                    binding.login.text = user.login
                }
                // name表示
                if (user.name != null) {
                    binding.name.visibility = View.VISIBLE
                    binding.name.text = user.name
                }
                // followers・following表示
                if (user.followers != null && user.following != null) {
                    binding.rowFollow.visibility = View.VISIBLE
                    binding.followers.text = user.followers.toString()
                    binding.following.text = user.following.toString()
                }
                // company表示
                if (user.company != null) {
                    binding.rowCompany.visibility = View.VISIBLE
                    binding.company.text = user.company
                }
                // location表示
                if (user.location != null) {
                    binding.rowLocation.visibility = View.VISIBLE
                    binding.location.text = user.location
                }
                // email表示
                if (user.email != null) {
                    binding.rowEmail.visibility = View.VISIBLE
                    binding.email.text = user.email
                }
                // blog表示
                if (user.blog != null) {
                    binding.rowBlog.visibility = View.VISIBLE
                    binding.blog.text = user.blog
                }
                // twitter_username表示
                if (user.twitter_username != null) {
                    binding.rowTwitter.visibility = View.VISIBLE
                    binding.twitter.text = user.twitter_username
                }
            },
            Response.ErrorListener { error ->
                // ユーザリスト情報取得失敗
                // エラーダイアログ表示
                AlertDialog.Builder(activity) // FragmentではActivityを取得して生成
                    .setTitle(Const.ERROR_TITLE)
                    .setMessage(Const.ERR_MSG_GET_DATA_FAILED)
                    .setPositiveButton(Const.CLOSE, { dialog, which ->
                        Logger.e("DetailFragment", error.toString())
                    })
                    .show()
            }
        )
        // ユーザリスト情報取得
        VolleySingleton.getInstance(requireActivity()).addToRequestQueue(jsonObjectRequest)
    }

    override fun onStop() {
        super.onStop()
        // 実行中のQueueをキャンセル
        val requestQueue = VolleySingleton.getInstance(requireActivity()).requestQueue
        if (activity != null) {
            requestQueue.cancelAll(activity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}