package com.neppplus.colosseum_211024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.neppplus.colosseum_211024.adapters.ReplyAdapter
import com.neppplus.colosseum_211024.databinding.ActivityViewTopicDetailBinding
import com.neppplus.colosseum_211024.datas.ReplyData
import com.neppplus.colosseum_211024.datas.TopicData
import com.neppplus.colosseum_211024.utils.ServerUtil
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var binding : ActivityViewTopicDetailBinding

    lateinit var mTopicData : TopicData

    val mReplyList = ArrayList<ReplyData>()

    lateinit var mReplyAdapter : ReplyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_topic_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.voteToFirstSideBtn.setOnClickListener {

//            첫번째 진영에 투표 > 새로 투표 현황 받아서 > UI반영

            ServerUtil.postRequestVote(mContext,mTopicData.sideList[0].id,object :ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

//                    새로 토론 상태 불러오기
                    getTopicDetailFromServer()
                }

            })

        }

        binding.voteToSecondSideBtn.setOnClickListener {

            ServerUtil.postRequestVote(mContext,mTopicData.sideList[1].id,object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

//                    새로 토론 상태 불러오기
                    getTopicDetailFromServer()
                }

            })
        }

    }

    override fun setValues() {
        mTopicData = intent.getSerializableExtra("topic") as TopicData

        binding.topicTitleTxt.text = mTopicData.title

        Glide.with(mContext).load(mTopicData.imageUrl).into(binding.topicImg)

        binding.replyCountTxt.text = "현재 의견 : ${mTopicData.replyCount}개"

        binding.firstSideTitleTxt.text = mTopicData.sideList[0].title
        binding.secondSideTitleTxt.text = mTopicData.sideList[1].title

        binding.firstSideVoteCountTxt.text = "${mTopicData.sideList[0].voteCount}표"
        binding.secondSideVoteCountTxt.text = "${mTopicData.sideList[1].voteCount}표"


        getTopicDetailFromServer()

        mReplyAdapter = ReplyAdapter(mContext, R.layout.reply_list_item,mReplyList)
        binding.replyListView.adapter = mReplyAdapter

    }

    fun getTopicDetailFromServer() {

        ServerUtil.getRequestTopicDetail(mContext,mTopicData.id,"NEW", object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

//                댓글목록 JSONArray > 파싱 > mReplyList의 자료로 추가
                val dataObj = jsonObj.getJSONObject("data")
                val topicObj = dataObj.getJSONObject("topic")

//                topicObj(JSONObject)를 새 TopicData 로 파싱 > 최신정보 반영

                mTopicData = TopicData.getTopicDataFromJSON(topicObj)
//                새 mTopicData 에 들어있는 데이터를 UI에 다시 반영
                runOnUiThread {
                    refreshUi()
                }


                val repliesArr = topicObj.getJSONArray("replies")

//                mReplyList 에 이미 데이터가 들어있는 상태로 추가 > 같은 데이터 여러번추가(중복)
//                기존 들어간 댓글 목록 삭제 후 add

                mReplyList.clear()
                
                for ( i in 0 until repliesArr.length() ) {
                    
                    val replyObj = repliesArr.getJSONObject(i)
                    
//                    JSONObjct > ReplyData 객채로 변환
                    val replyData = ReplyData()
                    replyData.id = replyObj.getInt("id")
                    replyData.content = replyObj.getString("content")
                    
//                    댓글 목록으로 추가
                    mReplyList.add(replyData)
                }
                
//                리스트뷰의 목록에 변경 > 어댑터 새로고침
                runOnUiThread {
                    mReplyAdapter.notifyDataSetChanged()
                }


            }

        })

    }

    fun refreshUi() {

//        득표 수 등은 자주 변경되는 데이터.
        binding.firstSideVoteCountTxt.text = "${mTopicData.sideList[0].voteCount}표"
        binding.secondSideVoteCountTxt.text = "${mTopicData.sideList[1].voteCount}표"

    }



}