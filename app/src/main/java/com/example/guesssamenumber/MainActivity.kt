package com.example.guesssamenumber

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.guesssamenumber.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragmentPageOne = FragmentPageOne()
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.frameContainer,
                    fragmentPageOne,
                    FragmentPageOne::class.java.simpleName
                )
                .commit()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.frameContainer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val fragmentManager = supportFragmentManager
        return when (item.itemId) {
            R.id.halaman_satu -> {
                val fragmentPageOne = FragmentPageOne()
                fragmentManager.beginTransaction()
                    .replace(
                        R.id.frameContainer,
                        fragmentPageOne,
                        FragmentPageOne::class.java.simpleName
                    )
                    .commit()
                true
            }

            R.id.halaman_dua -> {
                val fragmentPageTwo = FragmentPageTwo()
                fragmentManager.beginTransaction()
                    .replace(
                        R.id.frameContainer,
                        fragmentPageTwo,
                        FragmentPageTwo::class.java.simpleName
                    )
                    .commit()
                true
            }

            R.id.halaman_tiga -> {
                val fragmentPageThree = FragmentPageThree()
                fragmentManager.beginTransaction()
                    .replace(
                        R.id.frameContainer,
                        fragmentPageThree,
                        FragmentPageThree::class.java.simpleName
                    )
                    .commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

