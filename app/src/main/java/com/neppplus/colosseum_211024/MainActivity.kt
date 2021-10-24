package com.neppplus.colosseum_211024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.neppplus.colosseum_211024.databinding.ActivityMainBinding
import com.neppplus.colosseum_211024.utils.ServerUtil

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.loginBtn.setOnClickListener {

//              입력한 이메일/비번을 데이터바인딩으로 가져오기.
            val inputEmail = binding.emailEdt.text.toString()
            val inputPw = binding.passwordEdt.text.toString()

//            가져온 이메일/비번 로그로 출력
            Log.d("입력이메일",inputEmail)
            Log.d("입력비번",inputPw)

//            서버의 로그인 기능에 전달
            ServerUtil.postRequestLogin(inputEmail,inputPw)

        }


    }
}