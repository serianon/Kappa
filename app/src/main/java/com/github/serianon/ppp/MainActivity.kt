package com.github.serianon.ppp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

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
                .replace(R.id.activity_content, CardViewPagerFragment.newInstance(0))
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.to_overview -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.activity_content, CardGridViewFragment())
                        .commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
