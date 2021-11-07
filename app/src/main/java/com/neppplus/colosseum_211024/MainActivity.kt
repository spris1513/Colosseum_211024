package com.neppplus.colosseum_211024

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.colosseum_211024.databinding.ActivityMainBinding
import com.neppplus.colosseum_211024.datas.TopicData
import com.neppplus.colosseum_211024.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding

    val mTopicList = ArrayList<TopicData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

    }

    override fun setValues() {

//        연습 - 내 정보 받아오기 호출 => 닉네임 파싱, 텍스트뷰에 반영영

//        ServerUtil.getRequestMyInfo(mContext,object:ServerUtil.JsonResponseHandler{
//            override fun onResponse(jsonObj: JSONObject) {
//                val dataObj = jsonObj.getJSONObject("data")
//                val userObj = dataObj.getJSONObject("user")
//                val nickname = userObj.getString("nick_name")
//
//                binding.nicknameTxt.text = nickname
//            }
//
//        })


//        /v2/main_info API 가 토론 주제 목록을 내려줌
//        서버 호출 > 파싱해서 mTopicList 를 채워주자
        fun getTopicListFromServer(){

            ServerUtil.getRequestMainInfo(mContext,object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

                }
            })

        }


    }

}