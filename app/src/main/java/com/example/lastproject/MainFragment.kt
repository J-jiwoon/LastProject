package com.example.lastproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import kotlinx.android.synthetic.main.activity_main.*
import lecho.lib.hellocharts.util.ChartUtils
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDayKcalChartValues()
        setNutrientChartValues()
    }

    companion object{
        const val TAG : String = "메인로그"

        fun newInstance(): MainFragment {
            return MainFragment()
        }
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