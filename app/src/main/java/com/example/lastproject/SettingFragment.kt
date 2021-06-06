package com.example.lastproject

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.fragment_setting.*


class SettingFragment : Fragment() {

    companion object{
        const val TAG : String = "로그"

        fun newInstance(): SettingFragment {
            return SettingFragment()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firenotice.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if(isChecked){
                    Toast.makeText(requireContext(), "알림 설정", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(requireContext(), "알림 해제", Toast.LENGTH_SHORT).show()
                }
            }
        })
        firephone.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if(isChecked){
                    var builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("화재 발생 시 연락처")
                    builder.setIcon(R.mipmap.ic_launcher)

                    var v1 = layoutInflater.inflate(R.layout.dialog, null)
                    builder.setView(v1)

                    var listener = DialogInterface.OnClickListener { p0, p1 ->
                        var alert = p0 as AlertDialog
                        var edt: EditText? = alert.findViewById<EditText>(R.id.edtphone)

                        tv_result.text = "${edt?.text}"
                    }
                    builder.setPositiveButton("확인", listener)
                    builder.setNegativeButton("취소", null)

                    builder.show()

                    textview.setVisibility(View.VISIBLE)
                    tv_result.setVisibility(View.VISIBLE)
                }
                else{
                    Toast.makeText(requireContext(), "핸드폰 해제", Toast.LENGTH_SHORT).show()
                    textview.setVisibility(View.INVISIBLE)
                    tv_result.setVisibility(View.INVISIBLE)
                }
            }
        })
    }
}