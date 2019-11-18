package com.josephuszhou.shanhaiguan.demo


import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.josephuszhou.shanhaiguan.Shanhaiguan
import com.josephuszhou.shanhaiguan.listener.OnPermissionRequestListener

class SecondFragment : Fragment(), OnPermissionRequestListener {

    companion object {

        @JvmStatic
        private val TAG = "Shanhaiguan"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        view.findViewById<AppCompatButton>(R.id.btn_contact).setOnClickListener { v -> onClick(v) }

        return view
    }

    private fun onClick(v: View) {
        when (v.id) {
            R.id.btn_contact -> {
                Shanhaiguan.with(this).request(
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    this
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
