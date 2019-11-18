package com.josephuszhou.shanhaiguan.ui

import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import androidx.fragment.app.Fragment
import com.josephuszhou.shanhaiguan.listener.OnPermissionRequestListener

class PermissionFragment : Fragment() {

    enum class Status {
        Denied,
        Granted,
        Revoked
    }

    companion object {

        private const val PERMISSIONS_REQUEST_CODE = 100
    }

    private var mOnPermissionRequestListener: OnPermissionRequestListener? = null

    private lateinit var mPermissionsMap: HashMap<String, Status>

    @TargetApi(Build.VERSION_CODES.M)
    fun isGranted(permission: String): Boolean {
        return activity?.checkSelfPermission(permission) ?: PackageManager.PERMISSION_DENIED == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun isRevoked(permission: String): Boolean {
        return activity?.let {
            it.packageManager?.isPermissionRevokedByPolicy(permission, it.packageName)
        } ?: throw IllegalStateException("No Activity attached")
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissions(
        permissions: Array<String>,
        onPermissionRequestListener: OnPermissionRequestListener?
    ) {
        mOnPermissionRequestListener = onPermissionRequestListener
        mPermissionsMap = HashMap()

        val mPermissions = ArrayList<String>()
        for (permission in permissions) {
            mPermissionsMap[permission] = when {
                isGranted(permission) -> Status.Granted
                isRevoked(permission) -> Status.Revoked
                else -> {
                    mPermissions.add(permission)
                    Status.Denied
                }
            }
        }

        if (mPermissions.size > 0) {
            requestPermissions(mPermissions.toTypedArray(), PERMISSIONS_REQUEST_CODE)
        } else {
            handlePermissionResult()
        }
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

        for (i in grantResults.indices) {
            mPermissionsMap[permissions[i]] =
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Status.Granted
                } else {
                    if (isRevoked(permissions[i]))
                        Status.Revoked
                    else
                        Status.Denied
                }
        }

        handlePermissionResult()
    }

    private fun handlePermissionResult() {
        val grantedPermissions = ArrayList<String>()
        val deniedPermissions = ArrayList<String>()
        val revokedPermissions = ArrayList<String>()

        for ((k, v) in mPermissionsMap) {
            when (v) {
                Status.Granted -> grantedPermissions.add(k)
                Status.Denied -> deniedPermissions.add(k)
                else -> revokedPermissions.add(k)
            }
        }

        if (grantedPermissions.size > 0) {
            mOnPermissionRequestListener?.onGranted(grantedPermissions.toTypedArray())
        }
        if (deniedPermissions.size > 0) {
            mOnPermissionRequestListener?.onDenied(deniedPermissions.toTypedArray())
        }
        if (revokedPermissions.size > 0) {
            mOnPermissionRequestListener?.onRevoked(revokedPermissions.toTypedArray())
        }
    }
}
