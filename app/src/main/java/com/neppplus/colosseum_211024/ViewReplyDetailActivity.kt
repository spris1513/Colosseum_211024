package com.neppplus.colosseum_211024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.colosseum_211024.databinding.ActivityViewReplyDetailBinding
import com.neppplus.colosseum_211024.datas.ReplyData
import com.neppplus.colosseum_211024.utils.ServerUtil
import org.json.JSONObject

class ViewReplyDetailActivity : BaseActivity() {

    lateinit var binding : ActivityViewReplyDetailBinding

    lateinit var mReplyData : ReplyData

    val mReReplyList = ArrayList<ReplyData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_view_reply_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        mReplyData = intent.getSerializableExtra("reply") as ReplyData

        binding.writerNicknameTxt.text = mReplyData.user.nickname
        binding.selectedSideTitleTxt.text = mReplyData.selectedSide.title
        binding.contentTxt.text = mReplyData.content

        getReplyDetailFromServer()
    }

    fun getReplyDetailFromServer(){

        ServerUtil.getRequestReplyDetail(mContext,mReplyData.id,object :ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")
                val replyObj = dataObj.getJSONObject("reply")
                val repliesArr = replyObj.getJSONArray("replies")

                for(i in 0 until repliesArr.length()){

                    //위치에 맞는 JSONObject {} 추출 > ReplyData 로 변환 > 대댓글 목록에 추가

                    mReReplyList.add(ReplyData.getReplyDataFromJson(repliesArr.getJSONObject(i)))

                }

            }


        })

    }


}