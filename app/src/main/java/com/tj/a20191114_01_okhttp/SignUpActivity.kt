package com.tj.a20191114_01_okhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tj.a20191114_01_okhttp.utils.ServerUtil
import com.tj.a20191114_01_okhttp.utils.ServerUtil.jsonResponseHandler
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setupEvent()
        setValue()
    }

    override fun setupEvent() {

        signUpReqBtn.setOnClickListener {

            var loginId = signUpIdTxt.text.toString()
            var password = signUpPwdTxt.text.toString()
            var name = signUpNameTxt.text.toString()
            var phone = signUpPhoneTxt.text.toString()

            ServerUtil.putRequestSignUp(mContext, loginId, password, name, phone, object : jsonResponseHandler {
                override fun onResponse(json: JSONObject) {
                    runOnUiThread {
                        Log.d("서버", json.toString())
                        val code = json.getInt("code")

                        if (code == 400) {
                            var message = json.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }

                        if (code == 200) {
                            Toast.makeText(mContext, "가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })
        }

    }

    override fun setValue() {
    }
}
