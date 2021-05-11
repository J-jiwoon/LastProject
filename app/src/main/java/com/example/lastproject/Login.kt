package com.example.lastproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        joinbtn.setOnClickListener {
            var join_layout = Intent(applicationContext, Join::class.java)
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