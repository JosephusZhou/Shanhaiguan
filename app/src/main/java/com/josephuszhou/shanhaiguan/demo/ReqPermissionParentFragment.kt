package com.josephuszhou.shanhaiguan.demo


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ReqPermissionParentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_req_permission_parent, container, false)

        childFragmentManager.beginTransaction()
            .add(R.id.layout_root, ReqPermissionChildFragment())
            .commit()

        return view
    }


}
