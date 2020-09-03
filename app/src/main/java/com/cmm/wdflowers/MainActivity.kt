package com.cmm.wdflowers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cmm.wdflowers.ui.orders.OrdersFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, OrdersFragment.newInstance())
                .commitNow()
        }
    }
}