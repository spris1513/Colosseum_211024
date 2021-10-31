package com.neppplus.colosseum_211024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.neppplus.colosseum_211024.databinding.ActivitySignUpBinding
import com.neppplus.colosseum_211024.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    lateinit var binding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

        binding.okBtn.setOnClickListener {
            val inputEmail = binding.emailEdt.text.toString()
            val inputPw = binding.pwEdt.text.toString()
            val inputNickname = binding.nicknameEdt.text.toString()

//            입력 데이터를 > 서버에 회원가입 기능에 요청 > ServerUtil 함수 활용 > 함수가 아직 없으니 추가로 만들자
            ServerUtil.putRequestSignUp(inputEmail,inputPw,inputNickname, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

//                    jsonObj 분석 > UI 반영 코드만 작성

//                    code: 성공(200) / 실패(그 외) 여부

                    val code = jsonObj.getInt("code")
                    if(code == 200) {
//                        회원가입성공
                    }
                    else{
//                        회원가입 실패 > 왜? message String 에 담겨있음
                        val message = jsonObj.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }

                    }


                }

            })
        }

    }

    override fun setValues() {

    }


}