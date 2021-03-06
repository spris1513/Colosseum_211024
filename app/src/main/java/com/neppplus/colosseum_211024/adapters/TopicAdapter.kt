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
import com.neppplus.colosseum_211024.datas.TopicData

class TopicAdapter(
    val mContext : Context,
    resId: Int,
    val mList: List<TopicData>) : ArrayAdapter<TopicData>(mContext,resId,mList) {

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if(tempRow == null){
            tempRow = mInflater.inflate(R.layout.topic_list_item,null)
        }

        val row = tempRow!!

        val topicData = mList[position]

        val topicImg = row.findViewById<ImageView>(R.id.topicImg)
        val topicTitleTxt = row.findViewById<TextView>(R.id.topicTitleTxt)

        val replyCountTxt = row.findViewById<TextView>(R.id.replyCountTxt)

        topicTitleTxt.text=topicData.title

        Glide.with(mContext).load(topicData.imageUrl).into(topicImg)

        replyCountTxt.text = "현재 의견 : ${topicData.replyCount}개"

        return row
    }
}