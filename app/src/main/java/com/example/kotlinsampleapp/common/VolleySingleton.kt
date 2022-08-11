package com.example.kotlinsampleapp.common

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * Volleyシングルトンクラス
 */
class VolleySingleton constructor(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: VolleySingleton? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: VolleySingleton(context).also {
                    INSTANCE = it
                }
            }
    }

    val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }

    /** Coil利用のため、imagLoader未使用
    val imagLoader: ImageLoader by lazy {
    ImageLoader(requestQueue,
    object : ImageLoader.ImageCache {
    private val cache = LruCache<String, Bitmap>(20)
    override fun getBitmap(url: String): Bitmap {
    return cache.get(url)
    }

    override fun putBitmap(url: String, bitmap: Bitmap) {
    cache.put(url, bitmap)
    }
    })
    }
     */
}