package com.neppplus.colosseum_211024.datas

import org.json.JSONObject

class ReplyData(
    var id : Int,
    var content : String) {

    var likeCount = 0
    var disLikeCount = 0
    var myLike = false
    var myDisLike = false
    var replyCount = 0

//    var user : UserData 댓글 적은 사람 정보 > 하위 데이터로.

    constructor() : this(0,"내용없음")

    companion object {

//        jsonObj > ReplyData 로 변환해주는 기능

        fun getReplyDataFromJson( jsonObj : JSONObject ) : ReplyData {

            val replyData = ReplyData()

            replyData.id = jsonObj.getInt("id")
            replyData.content = jsonObj.getString("content")

            replyData.likeCount = jsonObj.getInt("like_count")
            replyData.disLikeCount= jsonObj.getInt("dislike_count")
            replyData.myLike= jsonObj.getBoolean("my_like")
            replyData.myDisLike = jsonObj.getBoolean("my_dislike")
            replyData.replyCount = jsonObj.getInt("reply_count")

            return replyData

        }

    }

}