package com.neppplus.colosseum_211024.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.neppplus.colosseum_211024.R
import com.neppplus.colosseum_211024.ViewTopicDetailActivity
import com.neppplus.colosseum_211024.datas.ReplyData
import com.neppplus.colosseum_211024.datas.TopicData
import com.neppplus.colosseum_211024.utils.ServerUtil
import org.json.JSONObject

class ReplyAdapter(
    val mContext : Context,
    resId: Int,
    val mList: List<ReplyData>) : ArrayAdapter<ReplyData>(mContext,resId,mList) {

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if(tempRow == null){
            tempRow = mInflater.inflate(R.layout.reply_list_item,null)
        }

        val row = tempRow!!

        val data = mList[position]

        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)
        val replyCountTxt = row.findViewById<TextView>(R.id.replyCountTxt)
        val likeCountTxt = row.findViewById<TextView>(R.id.likeCountTxt)
        val disLikeCountTxt = row.findViewById<TextView>(R.id.disLikeCountTxt)

        replyCountTxt.text = "답글 : ${data.replyCount}개"
        likeCountTxt.text = "좋아요 : ${data.likeCount}개"
        disLikeCountTxt.text = "싫어요 : ${data.disLikeCount}개"

//        내가 좋아요 찍었는지? > 글씨 색 빨간색(#FF0000). 안찍었다면 회색(#A0A0A0)
        if (data.myLike) {
            likeCountTxt.setTextColor(mContext.resources.getColor(R.color.red))
        }
        else {
            likeCountTxt.setTextColor(mContext.resources.getColor(R.color.gray))
        }

//        싫어요 여부에 따른 텍스트 컬러 변경
        if(data.myDisLike){
            disLikeCountTxt.setTextColor(mContext.resources.getColor(R.color.blue))
        }
        else {
            disLikeCountTxt.setTextColor(mContext.resources.getColor(R.color.gray))
        }

        contentTxt.text = data.content


//        좋아요 텍스트뷰 클릭 > 이 댓글에 대해 좋아요 누른걸로 호출

        likeCountTxt.setOnClickListener {

            ServerUtil.postRequestReplyLikeOrDislike(mContext,data.id, true,object :ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

//                    좋아요 찍고 돌아오면 할일

//                    토론 상세 화면 > 댓글 목록 새로고침 > 좋아요/싫어요 갯수반영

//                    어댑터 > 화면의 기능? mContext를 > 토론 상세 화면으로 변신시켜서 활용해보자

                    (mContext as ViewTopicDetailActivity).getTopicDetailFromServer()

                }


            })

        }
//                   싫어요 찍기 구현
        disLikeCountTxt.setOnClickListener {

            ServerUtil.postRequestReplyLikeOrDislike(mContext,data.id,false,object :ServerUtil.JsonResponseHandler{

                override fun onResponse(jsonObj: JSONObject) {
                    (mContext as ViewTopicDetailActivity).getTopicDetailFromServer()

                }

            })

        }


        return row
    }
}