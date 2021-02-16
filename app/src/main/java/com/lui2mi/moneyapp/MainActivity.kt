package com.lui2mi.moneyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_ironsource).setOnClickListener(this)
        findViewById<Button>(R.id.btn_admob).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var activity: Class<*> = MainActivity::class.java
        when(v?.id) {
            R.id.btn_ironsource -> activity = IronSource::class.java
            R.id.btn_admob -> activity = AdMob::class.java
        }
        startActivity(Intent(this,activity))
    }
}