package com.josephuszhou.shanhaiguan.ui

import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import androidx.fragment.app.Fragment

class PermissionFragment : Fragment() {


    @TargetApi(Build.VERSION_CODES.M)
    fun isGranted(permission: String): Boolean {
        return activity?.checkSelfPermission(permission) ?:
                PackageManager.PERMISSION_DENIED == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissions() {

    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
