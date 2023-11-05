package com.example.a3fragments

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstButton = findViewById<Button>(R.id.button1)
        firstButton.isEnabled = false
        val secondButton = findViewById<Button>(R.id.button2)
        val thirdButton = findViewById<Button>(R.id.button3)

        firstButton.setOnClickListener {
            supportFragmentManager.commit {
                replace<FirstFragment>(R.id.switch_fragment)
                addToBackStack(null)
            }
            firstButton.isEnabled = false
            secondButton.isEnabled = true
            thirdButton.isEnabled = true
        }
        secondButton.setOnClickListener {
            supportFragmentManager.commit {
                replace<SecondFragment>(R.id.switch_fragment)
                addToBackStack(null)
            }
            firstButton.isEnabled = true
            secondButton.isEnabled = false
            thirdButton.isEnabled = true
        }
        thirdButton.setOnClickListener {
            supportFragmentManager.commit {
                replace<ThirdFragment>(R.id.switch_fragment)
                addToBackStack(null)
            }
            firstButton.isEnabled = true
            secondButton.isEnabled = true
            thirdButton.isEnabled = false
        }
    }
}