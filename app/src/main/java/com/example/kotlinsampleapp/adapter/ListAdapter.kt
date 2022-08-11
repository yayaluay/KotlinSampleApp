package com.example.kotlinsampleapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.ImageLoader
import com.example.kotlinsampleapp.R
import com.example.kotlinsampleapp.common.Perfs
import com.example.kotlinsampleapp.data.UsersData

/**
 * Userリスト用Adapter
 */
class ListAdapter(
    private val context: Context,
    private val users: List<UsersData> // ユーザリスト
) : RecyclerView.Adapter<ListViewHolder>() {

    private var listener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        // ユーザ名の表示
        holder.name.text = users[position].login
        // ImageLoaderの初期化
        val imageLoader = ImageLoader(context) {
            placeholder(R.drawable.no_img) // 初期表示画像
            error(R.drawable.no_img) // エラー画像
            availableMemoryPercentage(Perfs.IMG_AVAILABLE_MEMORY_PERCENTAGE) // 利用可能メモリ割合
            bitmapPoolPercentage(Perfs.IMG_BITMAP_POOL_PERCENTAGE) // bitmap pool割合
        }
        // ユーザ画像の表示
        holder.image.load(users[position].avatar_url, imageLoader)
        // リストアイテムClickListener
        holder.itemView.setOnClickListener {
            listener?.invoke(position)
        }
    }

    override fun getItemCount(): Int = users.size
}