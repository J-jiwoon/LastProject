package com.example.lastproject

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_diet.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btn_navi
import kotlinx.android.synthetic.main.activity_main.layout_drawer
import kotlinx.android.synthetic.main.activity_main.naviView

class DietActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet)

        btn_navi.setOnClickListener {
            layout_drawer.openDrawer(GravityCompat.END) //네비게이션 출력 방향
        }
        naviView.setNavigationItemSelectedListener(this) // 네비게이션 메뉴 아이템에 클릭 속성 부여

        val items = mutableListOf<ListViewItem>()
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_diet)!!, "김밥", "칼로리 = 200kcal", "탄수화물 = 10g", "단백질 = 15g", "지방 = 11g"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_diet)!!, "국밥", "칼로리 = 240kcal", "탄수화물 = 15g", "단백질 = 25g", "지방 = 13g"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_diet)!!, "국밥", "칼로리 = 240kcal", "탄수화물 = 15g", "단백질 = 25g", "지방 = 13g"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_diet)!!, "국밥", "칼로리 = 240kcal", "탄수화물 = 15g", "단백질 = 25g", "지방 = 13g"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_diet)!!, "국밥", "칼로리 = 240kcal", "탄수화물 = 15g", "단백질 = 25g", "지방 = 13g"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_diet)!!, "국밥", "칼로리 = 240kcal", "탄수화물 = 15g", "단백질 = 25g", "지방 = 13g"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_diet)!!, "국밥", "칼로리 = 240kcal", "탄수화물 = 15g", "단백질 = 25g", "지방 = 13g"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_diet)!!, "국밥", "칼로리 = 240kcal", "탄수화물 = 15g", "단백질 = 25g", "지방 = 13g"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_diet)!!, "국밥", "칼로리 = 240kcal", "탄수화물 = 15g", "단백질 = 25g", "지방 = 13g"))

        val adapter = ListViewAdapter(items)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = parent.getItemAtPosition(position) as ListViewItem
            Toast.makeText(this, item.foodname, Toast.LENGTH_SHORT).show()
        }

        btn_dietregister.setOnClickListener {
            var dietregister = Intent(applicationContext, DietRegisterActivity::class.java)
            startActivity(dietregister)
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
    //네비게이션 종료
}