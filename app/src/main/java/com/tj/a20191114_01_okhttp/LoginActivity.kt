package com.tj.a20191114_01_okhttp

import android.os.Bundle
import com.tj.a20191114_01_okhttp.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*

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

            ServerUtil.postRequestLogin(mContext, inputId, inputPwd,null)
        }
    }

    override fun setValue() {
    }
}
