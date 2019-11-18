package com.josephuszhou.shanhaiguan.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val fragment = SecondFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.layout_root, fragment)
            .commitNow()
    }
}
