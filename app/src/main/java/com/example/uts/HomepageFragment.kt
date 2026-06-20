package com.example.uts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class HomepageFragment : Fragment (){


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_homepage, container, false)
        val btnMateri =view.findViewById<Button>(R.id.btnMateri)
        btnMateri.setOnClickListener {
            (activity as Dashboard).bukaMateri()
        }

        return view
    }

}