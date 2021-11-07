package com.neppplus.colosseum_211024

import android.os.Bundle
import android.util.Log
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

//        /v2/main_info API 가 토론 주제 목록을 내려줌
//        서버 호출 > 파싱해서 mTopicList 를 채워주자
        getTopicListFromServer()
    }
    fun getTopicListFromServer(){
        ServerUtil.getRequestMainInfo(mContext,object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")
                val topicArr = dataObj.getJSONObject("topics")

//                0번째 주제 ~ topicArr 갯수 직전까지를 반복.
//                5개 주제: 0~4 번 주제까지 (5개)

                for ( index in 0 utill topicArr.length()){

//                    [  ] 안에 있는  {  } 를 순서대로 찾아내서 파싱하자

                    val topicObj = topicArr.getJSONObject(index)

//                  topicObj는 토론 주제에 필요한 데이터를 들고있다.
//                  TopicData() 형태로 변환해주자 > 목록에 추가해주자

                    val topicData = TopicData()
                    topicData.id = topicObj.getInt("id")
                    topicData.title = topicObj.getString("title")
                    topicData.imageUrl = topicObj.getString("img_url")

//                    만들어진 topicData를 목록에 추가
                    mTopicList.add(topicData)
                }

//                for문의 끝나면, mTopicList에 모든 토론 주제가 추가된 상태다.


            }
        })

    }


}