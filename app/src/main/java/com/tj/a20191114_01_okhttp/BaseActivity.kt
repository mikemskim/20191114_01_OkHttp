package com.tj.a20191114_01_okhttp

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    var mContext = this

    abstract fun setupEvent()
    abstract fun setValue()
}