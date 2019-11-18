package com.josephuszhou.shanhaiguan.demo

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.josephuszhou.shanhaiguan.Shanhaiguan
import com.josephuszhou.shanhaiguan.listener.OnPermissionRequestListener

class ReqPermissionActivity : AppCompatActivity(), OnPermissionRequestListener {

    companion object {

        @JvmStatic
        private val TAG = "Shanhaiguan"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_req_permission)
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
            Log.e(TAG, permission + "已授权")
        }
    }

    override fun onDenied(deniedPermissions: Array<String>) {
        for(permission in deniedPermissions) {
            Log.e(TAG, permission + "未授权")
        }
    }

    override fun onRevoked(revokedPermissions: Array<String>) {
        for(permission in revokedPermissions) {
            Log.e(TAG, permission + "被策略拒绝")
        }
    }
}
