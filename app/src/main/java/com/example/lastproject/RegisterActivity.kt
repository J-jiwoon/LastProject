package com.example.lastproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        joinfinbtn.setOnClickListener {
            var memId = idedt.text.toString()
            var password = passedt.text.toString()
            var name = nameedt.text.toString()
            var phone = phoneedt.text.toString()
            var email = emailedt.text.toString()

            var myHelper = MyDBHelper(this)
            var sqlDB = myHelper.writableDatabase
            var insertSql = "memberTBL(memId, memPassword, name, phone, email) values ('" + memId + "', '" + password + "','" + name + "','" + phone + "', '" + email + "')"
            sqlDB.execSQL(insertSql)

            var intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
        }
    }
}