package com.neppplus.colosseum_211024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.colosseum_211024.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setupEvents()
    }
    override fun setupEvents() {

    }

    override fun setValues() {

//        연습 - 내 정보 받아오기 호출 => 닉네임 파싱, 텍스트뷰에 반영영


    }

}