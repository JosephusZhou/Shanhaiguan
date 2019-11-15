package com.josephuszhou.shanhaiguan.ui

import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import androidx.fragment.app.Fragment
import com.josephuszhou.shanhaiguan.listener.OnPermissionRequestListener

class PermissionFragment : Fragment() {

    companion object {

        private const val PERMISSIONS_REQUEST_CODE = 100
    }

    private var mPermissions: Array<String> = emptyArray()

    private var mOnPermissionRequestListener: OnPermissionRequestListener? = null

    private lateinit var mGrantedPermissions: ArrayList<String>

    private lateinit var mDeniedPermissions: ArrayList<String>

    /*@TargetApi(Build.VERSION_CODES.M)
    fun isGranted(permission: String): Boolean {
        return activity?.checkSelfPermission(permission) ?: PackageManager.PERMISSION_DENIED == PackageManager.PERMISSION_GRANTED
    }*/

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissions(
        permissions: Array<String>,
        onPermissionRequestListener: OnPermissionRequestListener?
    ) {
        this.mPermissions = permissions
        this.mOnPermissionRequestListener = onPermissionRequestListener

        mGrantedPermissions = ArrayList()
        mDeniedPermissions = ArrayList()

        requestPermissions(mPermissions, PERMISSIONS_REQUEST_CODE)
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != PERMISSIONS_REQUEST_CODE)
            return

        for(i in grantResults.indices) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                mGrantedPermissions.add(permissions[i])
            } else {
                mDeniedPermissions.add(permissions[i])
            }
        }

        if (mGrantedPermissions.size > 0) {
            mOnPermissionRequestListener?.onGranted(mGrantedPermissions.toTypedArray())
        }
        if (mDeniedPermissions.size > 0) {
            mOnPermissionRequestListener?.onDenied(mDeniedPermissions.toTypedArray())
        }
    }
}
