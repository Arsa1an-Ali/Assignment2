package com.example.assignment2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    private lateinit var addEventButton: Button
    private lateinit var viewEventsButton: Button
    private lateinit var buttonContainer: View
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        addEventButton = findViewById(R.id.add_event_button)
        viewEventsButton = findViewById(R.id.view_events_button)
        buttonContainer = findViewById(R.id.button_container)

        addEventButton.setOnClickListener {
            val dialogFragment: DialogFragment = AddEventDialogFragment()
            dialogFragment.show(supportFragmentManager, "AddEventDialog")
        }

        viewEventsButton.setOnClickListener {
            replaceFragment(ViewEventsFragment())
        }

        toolbar.visibility = View.VISIBLE
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                } else {
                    finish()
                }
            }
        })
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        if (fragment is ViewEventsFragment) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            buttonContainer.visibility = View.GONE
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            buttonContainer.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

