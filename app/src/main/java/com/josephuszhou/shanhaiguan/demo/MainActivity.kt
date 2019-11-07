package com.josephuszhou.shanhaiguan.demo

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.josephuszhou.shanhaiguan.Shanhaiguan

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_camera -> {
                Shanhaiguan.with(this)
                    .permissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))
                    .on()
            }
            R.id.btn_read_storage -> {
                Shanhaiguan.with(this)
                    .permissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))
                    .on()
            }
            R.id.btn_geo_mic -> {
                Shanhaiguan.with(this)
                    .permissions(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.RECORD_AUDIO
                        )
                    )
                    .on()
            }
        }
    }
}
