package com.example.kotlinsampleapp.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinsampleapp.R

/**
 * UserリストAdapterのViewHolder
 */
class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.user_name)// ユーザ名（login）
    val image: ImageView = view.findViewById(R.id.img_view) // ユーザ画像
}