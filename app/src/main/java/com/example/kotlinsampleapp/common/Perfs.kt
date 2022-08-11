package com.example.kotlinsampleapp.common

/**
 * 設定情報クラス
 */
class Perfs {
    companion object {
        // ログ出力制御フラグ true:出力　false:出力しない
        const val LOG_OUTPUT_FLG_V = true
        const val LOG_OUTPUT_FLG_D = true
        const val LOG_OUTPUT_FLG_I = true
        const val LOG_OUTPUT_FLG_W = true
        const val LOG_OUTPUT_FLG_E = true

        // 画像ロード設定
        const val IMG_AVAILABLE_MEMORY_PERCENTAGE = 0.1// 画像利用可能メモリ割合
        const val IMG_BITMAP_POOL_PERCENTAGE = 0.1// 画像bitmap pool割合
    }
}