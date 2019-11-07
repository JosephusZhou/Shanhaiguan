package com.josephuszhou.shanhaiguan.config

import com.josephuszhou.shanhaiguan.listener.OnPermissionRequestListener

/**
 * @author senfeng.zhou
 * @date 2019-11-07
 * @desc
 */
class Config private constructor() {

    companion object {
        fun getInstance() = InstanceHolder.INSTANCE

        fun getInitialInstance(): Config {
            val instance = getInstance()
            instance.reset()
            return instance
        }
    }

    private object InstanceHolder {
        val INSTANCE = Config()
    }

    var mPermissions: Array<String> = emptyArray()

    var mOnPermissionRequestListener: OnPermissionRequestListener? = null

    private fun reset() {
        mPermissions = emptyArray()
        mOnPermissionRequestListener = null
    }
}