package com.example.lastproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.idtext
import kotlinx.android.synthetic.main.activity_login.passtext
import kotlinx.android.synthetic.main.activity_register.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var myHelper  = MyDBHelper(this)

        loginbtn.setOnClickListener {
            if(myHelper.getLogin(idtext.getText().toString(), passtext.getText().toString()) == true){
                var loginfin = Intent(applicationContext, MainActivity::class.java)
                startActivity(loginfin)
            }
            else{
                Toast.makeText(applicationContext, "아이디또는 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show()
            }
        }

        joinbtn.setOnClickListener {
            var join_layout = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(join_layout)
        }
        findidbtn.setOnClickListener {
            var findid_layout = Intent(applicationContext, FindId::class.java)
            startActivity(findid_layout)
        }
        findpassbtn.setOnClickListener {
            var findpass_layout = Intent(applicationContext, FindPass::class.java)
            startActivity(findpass_layout)
        }

    }
}