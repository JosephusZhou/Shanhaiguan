package com.josephuszhou.shanhaiguan

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.josephuszhou.shanhaiguan.config.Config
import com.josephuszhou.shanhaiguan.listener.OnPermissionRequestListener
import com.josephuszhou.shanhaiguan.ui.PermissionFragment
import java.lang.ref.WeakReference

/**
 * @author senfeng.zhou
 * @date 2019-11-07
 * @desc
 */
class Shanhaiguan private constructor(activity: Activity) {

    companion object {

        fun with(activity: Activity) = Shanhaiguan(activity)

        fun with(fragment: Fragment) {
            fragment.activity?.let {
                with(it)
            }
        }

    }

    private val mActivityReference = WeakReference<Activity>(activity)

    private val mConfig = Config.getInitialInstance()

    private var permissionFragment: PermissionFragment = PermissionFragment()

    fun permissions(permissions: Array<String>): Shanhaiguan {
        mConfig.mPermissions = permissions
        return this
    }

    fun on(onPermissionRequestListener: OnPermissionRequestListener) {
        mConfig.mOnPermissionRequestListener = onPermissionRequestListener
        /*mActivityReference.get()?.let {
            it.supportFragmentManager.beginTransaction()
        }*/
    }

    private fun isMarshmallow() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    fun isGranted(permission: String): Boolean {
        return !isMarshmallow() || permissionFragment.isGranted(permission)
    }
}