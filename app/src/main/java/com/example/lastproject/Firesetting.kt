package com.example.lastproject

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_firesetting.*
import kotlinx.android.synthetic.main.activity_firesetting.btn_navifire
import kotlinx.android.synthetic.main.activity_firesetting.layout_drawer
import kotlinx.android.synthetic.main.activity_firesetting.naviView

class Firesetting : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firesetting)

        btn_navifire.setOnClickListener {
            layout_drawer.openDrawer(GravityCompat.END) //네비게이션 출력 방향
        }
        naviView.setNavigationItemSelectedListener(this) // 네비게이션 메뉴 아이템에 클릭 속성 부여

        firenotice.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if(isChecked){
                    Toast.makeText(applicationContext, "알림 설정", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(applicationContext, "알림 해제", Toast.LENGTH_SHORT).show()
                }
            }
        })
        firephone.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if(isChecked){
                    var builder = AlertDialog.Builder(this@Firesetting)
                    builder.setTitle("화재 발생 시 연락처")
                    builder.setIcon(R.mipmap.ic_launcher)

                    var v1 = layoutInflater.inflate(R.layout.dialog, null)
                    builder.setView(v1)

                    var listener = DialogInterface.OnClickListener { p0, p1 ->
                        var alert = p0 as AlertDialog
                        var edt:EditText? = alert.findViewById<EditText>(R.id.edtphone)

                        tv_result.text = "${edt?.text}"
                    }
                    builder.setPositiveButton("확인", listener)
                    builder.setNegativeButton("취소", null)

                    builder.show()

                    textview.setVisibility(View.VISIBLE)
                    tv_result.setVisibility(View.VISIBLE)
                }
                else{
                    Toast.makeText(applicationContext, "핸드폰 해제", Toast.LENGTH_SHORT).show()
                    textview.setVisibility(View.INVISIBLE)
                    tv_result.setVisibility(View.INVISIBLE)
                }
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {    //네비게이션 메뉴 아이템 클릭 시 수행
        when(item.itemId){
            R.id.account->{ // 내 정보 버튼
                var account = Intent(applicationContext, Account::class.java) // 네비게이션 드로어 열기
                startActivity(account)
            }
            R.id.foodmenu->{ // 식품 관리 버튼
                Toast.makeText(applicationContext, "식품 관리", Toast.LENGTH_SHORT).show()
            }
            R.id.dietmenu->{ // 식단 관리 버튼
                Toast.makeText(applicationContext, "식단 관리", Toast.LENGTH_SHORT).show()
            }
            R.id.gamemenu->{ // 게임 버튼
                var game = Intent(applicationContext, Game::class.java)
                startActivity(game)
            }
            R.id.firemenu->{ // 화재 경고 설정 버튼
                var fireset = Intent(applicationContext, Firesetting::class.java)
                startActivity(fireset)
            }
            R.id.settingmenu->{ // 환경설정 버튼
                Toast.makeText(applicationContext, "환경설정", Toast.LENGTH_SHORT).show()
            }
            //테스트 용
            R.id.logingmenu->{ // 로그인(test) 버튼
                var intent1 = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent1)
            }
        }
        layout_drawer.closeDrawers()    // 네비게이션 뷰 닫기
        return false
    }

    override fun onBackPressed() {  //뒤로가기 클릭 시 수행하는 메소드
        if(layout_drawer.isDrawerOpen(GravityCompat.END)){  //네비게이션 메뉴가 켜져있을 시 수행
            layout_drawer.closeDrawers()
        }
        else {
            super.onBackPressed()   // 일반 뒤로가기 버튼 기능 수행
        }
    }
}