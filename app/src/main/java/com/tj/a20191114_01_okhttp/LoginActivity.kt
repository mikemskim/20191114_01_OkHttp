package com.tj.a20191114_01_okhttp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tj.a20191114_01_okhttp.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvent()
        setValue()

//        ServerUtil.postRequestLogin(mContext, "cho881020", "test1234", null)
    }

    override fun setupEvent() {
        loginBtn.setOnClickListener {

            var inputId = loginIdTxt.text.toString()
            var inputPwd = loginPwTxt.text.toString()

            ServerUtil.postRequestLogin(mContext, inputId, inputPwd, object : ServerUtil.jsonResponseHandler {
                override fun onResponse(json: JSONObject) {
                    runOnUiThread {
                        Log.d("서버", json.toString())
                        val code = json.getInt("code")

                        if (code == 400) {
                            var message = json.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }

                        if (code == 200) {
//                            Toast.makeText(mContext, "로그인 성공", Toast.LENGTH_SHORT).show()
                            val data = json.getJSONObject("data")
                            val user = data.getJSONObject("user")
                            var name = user.getString("name")
                            Toast.makeText(mContext, "${name}님 로그인 성공", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })
        }
    }

    override fun setValue() {
    }
}
