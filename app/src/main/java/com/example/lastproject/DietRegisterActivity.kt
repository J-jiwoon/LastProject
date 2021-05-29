package com.example.lastproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.mancj.materialsearchbar.MaterialSearchBar

class DietRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_register)

        //View - 변수 연결
        val foodlistview = findViewById(R.id.mListView) as ListView
        val searchBar = findViewById<MaterialSearchBar>(R.id.searchBar)
        searchBar.setHint("Search")
        //음성검색모드 끄기
        searchBar.setSpeechMode(false)
        //검색어 목록 넣기(리스트뷰 항목)
        var galaxies = arrayOf("Sombrero", "Cartwheel", "Pinwheel", "StarBust", "Whirlpool", "Ring Nebular", "Own Nebular", "Centaurus A", "Virgo Stellar Stream", "Canis Majos Overdensity", "Mayall's Object", "Leo", "Milky Way", "IC 1011", "Messier 81", "Andromeda", "Messier 87")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, galaxies)
        //리스트뷰 초기에 안보이게 설정
        foodlistview.visibility = View.VISIBLE
        //SearchBar와 ListView 연동
        foodlistview.setAdapter(adapter)


        searchBar.addTextChangeListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            //검색어 변경하면 ListView 내용 변경
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.getFilter().filter(s)
            }

        })

        //ListView 내의 아이템 누르면 Toast 발생
        foodlistview.setOnItemClickListener(object : AdapterView.OnItemClickListener{
            override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                Toast.makeText(applicationContext, adapter.getItem(position)!!.toString(), Toast.LENGTH_SHORT).show()
            }

        })


    }
}