package com.example.kotlinsampleapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.volley.Response
import com.android.volley.Request
import com.example.kotlinsampleapp.common.VolleySingleton
import com.example.kotlinsampleapp.data.UsersData
import com.example.kotlinsampleapp.databinding.FragmentListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.app.AlertDialog
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.*
import com.example.kotlinsampleapp.R
import com.example.kotlinsampleapp.adapter.ListAdapter
import com.example.kotlinsampleapp.common.Const
import com.example.kotlinsampleapp.common.Logger

/**
 * ユーザ一覧画面Fragment
 */
class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!! //!!non-nullに変更する

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // JsonArrayRequest作成
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, Const.URL_GET_USERS, null,
            Response.Listener { response ->
                Logger.d("ListFragment", response.toString())
                // ユーザリスト情報取得成功
                // 受信したjson文字列をList<UsersData>オブジェクトに変換
                val listType = object : TypeToken<List<UsersData>>() {}.type
                val users = Gson().fromJson<List<UsersData>>(response.toString(), listType)

                binding.root.apply {
                    layoutManager = LinearLayoutManager(context)
//                    // RecyclerViewの分割線を表示
//                    val dividerItemDecoration =
//                        DividerItemDecoration(context, LinearLayout.VERTICAL)
//                    binding.list.addItemDecoration(dividerItemDecoration)
                    // ListAdapterの初期化及びItemClickListener設定
                    adapter = ListAdapter(context, users).apply {
                        setOnItemClickListener { position: Int ->
                            fragmentManager?.let { manager: FragmentManager ->
                                val tag = "ListFragment"
                                var fragment = manager.findFragmentByTag(tag)
                                if (fragment == null) {
                                    // ユーザ詳細Fragmentを作成
                                    fragment = DetailFragment()
                                    // ユーザ詳細Fragmentに渡すパラメータ設定
                                    fragment.arguments = Bundle().apply {
                                        putString(USER_URL, users[position].url) // ユーザ詳細情報取得url
                                    }
                                    // 画面遷移
                                    manager.beginTransaction().apply {
                                        add(R.id.content, fragment, tag)
                                        addToBackStack(null)
                                    }.commit()
                                }
                            }
                        }
                    }
                }
            },
            Response.ErrorListener { error ->
                // ユーザリスト情報取得失敗
                // エラーダイアログ表示
                AlertDialog.Builder(activity) // FragmentではActivityを取得して生成
                    .setTitle(Const.ERROR_TITLE)
                    .setMessage(Const.ERR_MSG_GET_DATA_FAILED)
                    .setPositiveButton(Const.CLOSE, { dialog, which ->
                        Logger.e("ListFragment", error.toString())
                    })
                    .show()
            }
        )
        // ユーザリスト情報取得
        VolleySingleton.getInstance(requireActivity()).addToRequestQueue(jsonArrayRequest)
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