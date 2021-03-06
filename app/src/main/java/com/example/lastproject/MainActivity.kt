package com.example.lastproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import kotlinx.android.synthetic.main.activity_main.*
import lecho.lib.hellocharts.util.ChartUtils
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity(){

    private lateinit var mainFragment: MainFragment
    private lateinit var accountFragment: AccountFragment
    private lateinit var dietRegisterFragment: DietRegisterFragment
    private lateinit var dietlistFragment: DietListFragment
    private lateinit var gameFragment: GameFragment
    private lateinit var settingFragment: SettingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bundle = Bundle()
        var intent = intent
        var userName = intent.getStringExtra("userName")
        var userID = intent.getStringExtra("userID")

        menu_home.setOnClickListener {
            mainFragment = MainFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.main,mainFragment).commit()
        }

        menu_account.setOnClickListener{
            bundle.putString("username", userName)
            accountFragment = AccountFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.main, AccountFragment().apply { arguments = bundle }).commit()
        }

        menu_diet.setOnClickListener{
            bundle.putString("userID", userID)
            dietRegisterFragment = DietRegisterFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.main, DietRegisterFragment().apply { arguments = bundle }).commit()
        }

        menu_dietlist.setOnClickListener{
            bundle.putString("userID", userID)
            dietlistFragment = DietListFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.main, DietListFragment().apply { arguments = bundle }).commit()
        }

        menu_game.setOnClickListener {
            gameFragment = GameFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.main, gameFragment).commit()
        }

        menu_setting.setOnClickListener {
            settingFragment = SettingFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.main, settingFragment).commit()
        }

        setDayKcalChartValues()
        setNutrientChartValues()
    }


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

        //????????? ??????
        val ykcal = arrayOf<Float>(4f, 5.7f, 8.4f, 1.9f, 4.3f)
        val barentries = ArrayList<BarEntry>()

        for(i in 0..ykcal.size-1){
            barentries.add(BarEntry(ykcal[i], i))
        }

        //????????? ?????????
        val bardataset = BarDataSet(barentries, "Kcal")
        bardataset.color = ChartUtils.pickColor()

        //????????? ????????????
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

        //????????? ??????
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

        //????????? ?????????
        val carbodata = BarDataSet(carbolist, "????????????")
        val proteindata = BarDataSet(proteinlist, "?????????")
        val fatdata = BarDataSet(fatlist, "??????")
        carbodata.color = ChartUtils.pickColor()
        proteindata.color = ChartUtils.pickColor()
        fatdata.color = ChartUtils.pickColor()

        val finalbardataset = ArrayList<BarDataSet>()
        finalbardataset.add(carbodata)
        finalbardataset.add(proteindata)
        finalbardataset.add(fatdata)

        //????????? ????????????
        val data = BarData(xvalues, finalbardataset as List<IBarDataSet>?)

        daychart.data = data
        daychart.animateXY(3000, 3000)

    }
}