package com.example.mobileapps

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {

    private var isFragmentA = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load the initial FragmentA if the activity is first created
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, FragmentA())
            }
        }

        // Set up the button click listener to switch between FragmentA and FragmentB
        findViewById<View>(R.id.switchFragmentButton).setOnClickListener {
            val fragment: Fragment = if (isFragmentA) {
                FragmentB()
            } else {
                FragmentA()
            }
            supportFragmentManager.commit {
                replace(R.id.fragment_container, fragment)
            }
            isFragmentA = !isFragmentA // Toggle the flag
        }
    }
}
