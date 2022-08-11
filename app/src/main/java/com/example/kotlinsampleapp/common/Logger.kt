package com.example.kotlinsampleapp.common

import android.util.Log

/**
 * ログ出力クラス
 */
class Logger {
    companion object {
        private val NO_LOG: Int = -1

        fun v(tag: String, msg: String) {
            if (Perfs.LOG_OUTPUT_FLG_V) {
                Log.v(tag, msg)
            }
            NO_LOG
        }

        fun v(tag: String, msg: String, tr: Throwable) {
            if (Perfs.LOG_OUTPUT_FLG_V) {
                Log.v(tag, msg, tr)
            }
            NO_LOG
        }

        fun d(tag: String, msg: String) {
            if (Perfs.LOG_OUTPUT_FLG_D) {
                Log.d(tag, msg)
            }
            NO_LOG
        }

        fun d(tag: String, msg: String, tr: Throwable) {
            if (Perfs.LOG_OUTPUT_FLG_D) {
                Log.d(tag, msg, tr)
            }
            NO_LOG
        }

        fun i(tag: String, msg: String) {
            if (Perfs.LOG_OUTPUT_FLG_I) {
                Log.i(tag, msg)
            }
            NO_LOG
        }

        fun i(tag: String, msg: String, tr: Throwable) {
            if (Perfs.LOG_OUTPUT_FLG_I) {
                Log.i(tag, msg, tr)
            }
            NO_LOG
        }

        fun w(tag: String, msg: String) {
            if (Perfs.LOG_OUTPUT_FLG_W) {
                Log.w(tag, msg)
            }
            NO_LOG
        }

        fun w(tag: String, msg: String, tr: Throwable) {
            if (Perfs.LOG_OUTPUT_FLG_W) {
                Log.w(tag, msg, tr)
            }
            NO_LOG
        }

        fun e(tag: String, msg: String) {
            if (Perfs.LOG_OUTPUT_FLG_E) {
                Log.e(tag, msg)
            }
            NO_LOG
        }

        fun e(tag: String, msg: String, tr: Throwable) {
            if (Perfs.LOG_OUTPUT_FLG_E) {
                Log.e(tag, msg, tr)
            }
            NO_LOG
        }
    }
}