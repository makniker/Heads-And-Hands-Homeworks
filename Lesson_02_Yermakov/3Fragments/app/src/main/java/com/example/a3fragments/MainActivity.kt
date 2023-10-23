package com.example.a3fragments

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout = findViewById<LinearLayout>(R.id.button_list)
        val firstButton = layout.getChildAt(0)
        firstButton.isEnabled = false
        val secondButton = layout.getChildAt(2)
        val thirdButton = layout.getChildAt(4)

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