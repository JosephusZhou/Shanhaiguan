package com.josephuszhou.shanhaiguan

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.josephuszhou.shanhaiguan.config.Config
import com.josephuszhou.shanhaiguan.listener.OnPermissionRequestListener
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

}