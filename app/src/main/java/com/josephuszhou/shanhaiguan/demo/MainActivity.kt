package com.josephuszhou.shanhaiguan.demo

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.josephuszhou.shanhaiguan.Shanhaiguan
import com.josephuszhou.shanhaiguan.listener.OnPermissionRequestListener

class MainActivity : AppCompatActivity(), OnPermissionRequestListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_camera -> {
                Shanhaiguan.with(this).request(
                    arrayOf(Manifest.permission.CAMERA),
                    this
                )
            }
            R.id.btn_read_storage -> {
                Shanhaiguan.with(this).request(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    this
                )
            }
            R.id.btn_geo_mic -> {
                Shanhaiguan.with(this).request(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.RECORD_AUDIO
                    ), this
                )
            }
        }
    }

    override fun onGranted(grantedPermissions: Array<String>) {
        for(permission in grantedPermissions) {
            Toast.makeText(this, permission + "成功", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDenied(deniedPermissions: Array<String>) {
        for(permission in deniedPermissions) {
            Toast.makeText(this, permission + "失败", Toast.LENGTH_SHORT).show()
        }
    }
}
