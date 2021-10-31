package com.neppplus.colosseum_211024.utils

import android.content.Context

class ContextUtil {

    companion object{

        private val prefName = "ColosseumPref"

        private val TOKEN = "TOKEN"

        // setter - 토큰 저장 기능. SAVE
        fun setToken( context:Context, token : String ) {

//            메모장을 불러 내자.
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
//            불러낸 메모장에 token 값 기록
            pref.edit().putString(TOKEN, token).apply()

        }

        // getter - 토큰 조회 기능. LOAD

        fun getToken ( context:Context ) : String {
//            메모장을 불러내자.
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
//            불러낸 메모장에서 token 값을 찾아서 리턴(결과로 지정)
            return pref.getString(TOKEN,"")!!  //getString 2번째 재료는 아무것도없을때 결과값
        }

    }
}