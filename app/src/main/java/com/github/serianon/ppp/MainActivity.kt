package com.github.serianon.ppp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * A single-activity-approach.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.activity_toolbar))

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_content, GridFragment())
                .commit()
    }

}
