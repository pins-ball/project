package com.pinball.molegame

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.pinball.molegame.mole

class MainActivity : Activity() {
    var pref: SharedPreferences? = null
    var spoint = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        pre_call()
        //setContentView(R.layout.activity_main);
        setContentView(pref?.let { mole(this, spoint, it) })
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    fun pre_call() {
        pref = getSharedPreferences("num", MODE_PRIVATE)
        spoint = pref?.getInt("maxjumsu", 0)!!
        Toast.makeText(
            applicationContext, "" +
                    "최고점수" + spoint, Toast.LENGTH_SHORT
        ).show()
    }
}