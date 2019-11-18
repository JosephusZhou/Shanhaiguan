package com.josephuszhou.shanhaiguan.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ReqPermissionInFragmentActivity : AppCompatActivity() {

    private lateinit var fragment: ReqPermissionFragment
    private lateinit var fragment2: ReqPermissionParentFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_req_permission_in_fragment)

        fragment = ReqPermissionFragment()
        fragment2 = ReqPermissionParentFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.layout_root, fragment2)
            .hide(fragment2)
            .add(R.id.layout_root, fragment)
            .commit()
    }

    fun toSecond2Fragment() {
        supportFragmentManager.beginTransaction()
            .hide(fragment)
            .show(fragment2)
            .commit()
    }
}
