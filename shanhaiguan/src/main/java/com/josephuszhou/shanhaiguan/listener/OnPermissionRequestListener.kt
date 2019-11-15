package com.josephuszhou.shanhaiguan.listener

/**
 * @author senfeng.zhou
 * @date 2019-11-07
 * @desc
 */
interface OnPermissionRequestListener {

    fun onGranted(grantedPermissions: Array<String>)

    fun onDenied(deniedPermissions: Array<String>)

}