package com.example.lastproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.layout_drawer
import lecho.lib.hellocharts.model.Axis
import lecho.lib.hellocharts.model.Column
import lecho.lib.hellocharts.model.ColumnChartData
import lecho.lib.hellocharts.model.SubcolumnValue
import lecho.lib.hellocharts.util.ChartUtils
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent = intent
        var userName = intent.getStringExtra("userName")

        userid.text = userName

        btn_navi.setOnClickListener {
            layout_drawer.openDrawer(GravityCompat.END) //네비게이션 출력 방향
        }
        naviView.setNavigationItemSelectedListener(this) // 네비게이션 메뉴 아이템에 클릭 속성 부여

        setDayKcalChartValues()
        setNutrientChartValues()
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
                var diet = Intent(applicationContext, DietActivity::class.java)
                startActivity(diet)
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


    fun setDayKcalChartValues(){
        val xvalues = ArrayList<String>()
        var now = LocalDateTime.now()
        var onenow = now.minusDays(1)
        var twonow = now.minusDays(2)
        var threenow = now.minusDays(3)
        var fournow = now.minusDays(4)
        var Strnow = now.format(DateTimeFormatter.ofPattern("MM-dd"))
        var Stronenow = onenow.format(DateTimeFormatter.ofPattern("MM-dd"))
        var Strtwonow = twonow.format(DateTimeFormatter.ofPattern("MM-dd"))
        var Strthreenow = threenow.format(DateTimeFormatter.ofPattern("MM-dd"))
        var Strfournow = fournow.format(DateTimeFormatter.ofPattern("MM-dd"))
        xvalues.add(Strfournow)
        xvalues.add(Strthreenow)
        xvalues.add(Strtwonow)
        xvalues.add(Stronenow)
        xvalues.add(Strnow)

        //데이터 삽입
        val ykcal = arrayOf<Float>(4f, 5.7f, 8.4f, 1.9f, 4.3f)
        val barentries = ArrayList<BarEntry>()

        for(i in 0..ykcal.size-1){
            barentries.add(BarEntry(ykcal[i], i))
        }

        //데이터 적용용
        val bardataset = BarDataSet(barentries, "Kcal")
        bardataset.color = ChartUtils.pickColor()

        //데이터 만들기기
        val data = BarData(xvalues, bardataset)

        kcalchart.data = data
        kcalchart.animateXY(3000, 3000)

    }
    fun setNutrientChartValues(){
        val xvalues = ArrayList<String>()
        var now = LocalDateTime.now()
        var onenow = now.minusDays(1)
        var twonow = now.minusDays(2)
        var threenow = now.minusDays(3)
        var fournow = now.minusDays(4)
        var Strnow = now.format(DateTimeFormatter.ofPattern("MM-dd"))
        var Stronenow = onenow.format(DateTimeFormatter.ofPattern("MM-dd"))
        var Strtwonow = twonow.format(DateTimeFormatter.ofPattern("MM-dd"))
        var Strthreenow = threenow.format(DateTimeFormatter.ofPattern("MM-dd"))
        var Strfournow = fournow.format(DateTimeFormatter.ofPattern("MM-dd"))
        xvalues.add(Strfournow)
        xvalues.add(Strthreenow)
        xvalues.add(Strtwonow)
        xvalues.add(Stronenow)
        xvalues.add(Strnow)

        //데이터 삽입
        val ycarbo = arrayOf<Float>(4f, 5.7f, 8.4f, 1.9f, 4.3f)
        val yprotein = arrayOf<Float>(1f, 2f, 1.4f, 6.4f, 2.4f)
        val yfat = arrayOf<Float>(4f, 3.5f, 4.4f, 4.2f, 3f)

        val carbolist = ArrayList<BarEntry>()
        val proteinlist = ArrayList<BarEntry>()
        val fatlist = ArrayList<BarEntry>()

        for(i in 0..ycarbo.size-1){
            carbolist.add(BarEntry(ycarbo[i], i))
        }
        for(i in 0..yprotein.size-1){
            proteinlist.add(BarEntry(yprotein[i], i))
        }
        for(i in 0..yfat.size-1){
            fatlist.add(BarEntry(yfat[i], i))
        }

        //데이터 적용용
        val carbodata = BarDataSet(carbolist, "탄수화물")
        val proteindata = BarDataSet(proteinlist, "단백질")
        val fatdata = BarDataSet(fatlist, "지방")
        carbodata.color = ChartUtils.pickColor()
        proteindata.color = ChartUtils.pickColor()
        fatdata.color = ChartUtils.pickColor()

        val finalbardataset = ArrayList<BarDataSet>()
        finalbardataset.add(carbodata)
        finalbardataset.add(proteindata)
        finalbardataset.add(fatdata)

        //데이터 만들기기
        val data = BarData(xvalues, finalbardataset as List<IBarDataSet>?)

        daychart.data = data
        daychart.animateXY(3000, 3000)

    }
}