package com.neppplus.colosseum_211024

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    val mContext = this

    lateinit var backBtn : ImageView

    abstract fun setupEvents()
    abstract fun setValues()

    fun setCustomActionBar() {

//        일반 함수를 물려준다 > 그 실행 내용까지 같이 내려줌
//        자식클래스(다른 화면) 에서는 > 이 함수를 실행만 하면 바로 사용 가능

        val defActionBar = supportActionBar!!

        defActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

        defActionBar.setCustomView(R.layout.my_custom_action_bar)

        val toolBar = defActionBar.customView.parent as Toolbar
        toolBar.setContentInsetsAbsolute(0,0)


        backBtn = defActionBar.customView.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {

            // 모든 화면에 백버튼은 기능이 동일
//            화면 종료
            finish()

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 여기에 적는 코드? > 자식(다른화면)들의 super.onCreate > 실행되는 내용

//        모든 화면이 만들어질때 > 액션바가 있다면 그때만 실행 > 액션바 커마기능 실행

            supportActionBar?.let {
                setCustomActionBar()
            }

    }

}