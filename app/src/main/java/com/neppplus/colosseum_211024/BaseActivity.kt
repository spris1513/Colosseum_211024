package com.neppplus.colosseum_211024

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun setupEvents()
    abstract fun setValues()

}