package com.example.lastproject

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_firesetting.*
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.layout_drawer
import kotlinx.android.synthetic.main.activity_main.naviView
import java.time.Duration

class Game : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        naviView.setNavigationItemSelectedListener(this)

         var kcal = 500
            if(kcal >= 1200){
                fatchar.setVisibility(View.VISIBLE)
                fatchar.playAnimation()
            }
            else if((kcal < 1200 ) && (kcal >= 600)){
                healthychar.setVisibility(View.VISIBLE)
                healthychar.playAnimation()
            }
            else if(kcal < 600){
                hungrychar.setVisibility(View.VISIBLE)
                hungrychar.playAnimation()
            }



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
                Toast.makeText(applicationContext, "게임", Toast.LENGTH_SHORT).show()
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
                var intent1 = Intent(applicationContext, Login::class.java)
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