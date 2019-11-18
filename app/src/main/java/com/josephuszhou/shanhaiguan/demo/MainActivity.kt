package com.josephuszhou.shanhaiguan.demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_req_in_activity -> {
                startActivity(Intent(this, ReqPermissionActivity::class.java))
            }
            R.id.btn_req_in_fragment -> {
                startActivity(Intent(this, ReqPermissionInFragmentActivity::class.java))
            }
        }
    }

}
