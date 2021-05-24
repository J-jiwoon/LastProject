package com.example.lastproject

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.idtext
import kotlinx.android.synthetic.main.activity_login.passtext
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONException
import org.json.JSONObject

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginbtn.setOnClickListener {

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