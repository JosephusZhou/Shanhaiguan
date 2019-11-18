package com.josephuszhou.shanhaiguan

import android.os.Build
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.josephuszhou.shanhaiguan.listener.OnPermissionRequestListener
import com.josephuszhou.shanhaiguan.ui.PermissionFragment

/**
 * @author senfeng.zhou
 * @date 2019-11-07
 * @desc
 */
class Shanhaiguan private constructor(activity: FragmentActivity) {

    companion object {

        private const val FRAGMENT_TAG = "Shanhaiguan"

        fun with(activity: FragmentActivity) = Shanhaiguan(activity)

        fun with(fragment: Fragment): Shanhaiguan {
            fragment.activity?.let {
                return with(it)
            } ?: throw IllegalStateException("No Activity attached")
        }
    }

    private var permissionFragment: PermissionFragment

    init {
        val fragment = activity.supportFragmentManager.findFragmentByTag(FRAGMENT_TAG)
        if (fragment is PermissionFragment) {
            permissionFragment = fragment
        } else {
            permissionFragment = PermissionFragment()
            activity.supportFragmentManager.beginTransaction()
                .add(permissionFragment, FRAGMENT_TAG)
                .commit()
            activity.supportFragmentManager.executePendingTransactions()
        }
    }

    fun request(
        permissions: Array<String>,
        onPermissionRequestListener: OnPermissionRequestListener?
    ) {
        if (!isMarshmallow()) {
            onPermissionRequestListener?.onGranted(permissions)
            return
        }

        permissionFragment.requestPermissions(permissions, onPermissionRequestListener)
    }

    fun request(
        permissions: Array<String>,
        onGranted: ((grantedPermissions: Array<String>) -> Unit)? = null,
        onDenied: ((deniedPermissions: Array<String>) -> Unit)? = null,
        onRevoked: ((revokedPermissions: Array<String>) -> Unit)? = null
    ) {
        if (!isMarshmallow()) {
            onGranted?.invoke(permissions)
            return
        }

        permissionFragment.requestPermissions(permissions, onGranted, onDenied, onRevoked)
    }

    private fun isMarshmallow() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
}