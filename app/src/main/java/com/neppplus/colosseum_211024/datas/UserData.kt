package com.neppplus.colosseum_211024.datas

import com.neppplus.colosseum_211024.utils.ServerUtil
import org.json.JSONObject

class UserData {

    var id = 0 // int 가 들어올거라는 명시
    var email = "" // String이 들어올거라는 명시
    var nickname = "" // String이 들어올거라는 명시

    companion object{

        fun getUserDataFromJson(jsonObj : JSONObject) : UserData {

            val userData = UserData()

            userData.id = jsonObj.getInt("id")
            userData.email = jsonObj.getString("email")
            userData.nickname = jsonObj.getString("nick_name")

            return userData

        }

    }
}