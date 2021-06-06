package com.example.lastproject

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_game.*


class GameFragment : Fragment() {

    companion object{
        const val TAG : String = "로그"

        fun newInstance(): GameFragment {
            return GameFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        testbtn.setOnClickListener {
            var kcal = Integer.parseInt(testkcal.getText().toString())

            if(kcal >= 1200){
                healthychar.setVisibility(View.INVISIBLE)
                fatchar.setVisibility(View.VISIBLE)
                hungrychar.setVisibility(View.INVISIBLE)
                fatchar.playAnimation()
            }
            else if((kcal < 1200 ) && (kcal >= 600)){
                healthychar.setVisibility(View.VISIBLE)
                fatchar.setVisibility(View.INVISIBLE)
                hungrychar.setVisibility(View.INVISIBLE)
                healthychar.playAnimation()
            }
            else if(kcal < 600){
                healthychar.setVisibility(View.INVISIBLE)
                fatchar.setVisibility(View.INVISIBLE)
                hungrychar.setVisibility(View.VISIBLE)
                hungrychar.playAnimation()
            }
        }

        fatchar.setOnClickListener{
            fatText.setVisibility(View.VISIBLE)
            Handler().postDelayed({
                fatText.setVisibility(View.INVISIBLE)
            }, 3000L)
        }

        healthychar.setOnClickListener{
            healthText.setVisibility(View.VISIBLE)
            Handler().postDelayed({
                healthText.setVisibility(View.INVISIBLE)
            }, 3000L)
        }

        hungrychar.setOnClickListener{
            hungryText.setVisibility(View.VISIBLE)
            Handler().postDelayed({
                hungryText.setVisibility(View.INVISIBLE)
            }, 3000L)
        }
    }
}