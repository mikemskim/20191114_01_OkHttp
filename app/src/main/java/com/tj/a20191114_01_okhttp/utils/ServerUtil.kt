package com.tj.a20191114_01_okhttp.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

    interface  jsonResponseHandler {
        fun onResponse(json:JSONObject)
    }

    companion object {

        var BASE_URL = "http://192.168.0.26:5000"

//        이 메소드를 복사해서 회원가입용으로 수정
//        SignUpActivity에서 회원가입으로 사용

        fun postRequestLogin(context: Context, loginId:String, loginPw:String, handler: jsonResponseHandler?) {

//            우리가 만드는 앱을 클라이언트 역할로 동작하게 해주는 클래스
            var client = OkHttpClient()

//            기능 주소와 서버 주소를 조합해서 실제 요청 주소 완성
            var url = "${BASE_URL}/auth"

//            POST 메소드에서 요구하는 파라미터를 FormBody에 담아줌
            var formBody = FormBody.Builder().add("login_id", loginId).add("password",loginPw).build()

//            실제로 날아갈 요청(request)을 생성
            var request = Request.Builder().url(url).post(formBody).build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("서버통신에러", e.localizedMessage)
                }

                override fun onResponse(call: Call, response: Response) {
                    var body = response.body!!.string()
//                    Log.d("서버", body)
                    var json = JSONObject(body)

                    handler?.onResponse(json)
                }
            })
        }

        fun putRequestSignUp(context: Context, loginId:String, password:String, name:String, phone:String,  handler: jsonResponseHandler?) {

//            우리가 만드는 앱을 클라이언트 역할로 동작하게 해주는 클래스
            var client = OkHttpClient()

//            기능 주소와 서버 주소를 조합해서 실제 요청 주소 완성
            var url = "${BASE_URL}/auth"

//            POST 메소드에서 요구하는 파라미터를 FormBody에 담아줌
            var formBody = FormBody.Builder().add("login_id", loginId).add("password",password).add("name", name).add("phone", phone).build()

//            실제로 날아갈 요청(request)을 생성
            var request = Request.Builder().url(url).put(formBody).build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("서버통신에러", e.localizedMessage)
                }

                override fun onResponse(call: Call, response: Response) {
                    var body = response.body!!.string()
//                    Log.d("서버", body)
                    var json = JSONObject(body)

                    handler?.onResponse(json)
                }
            })
        }
    }

}