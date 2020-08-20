package com.hyob.hyobtodoapp.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.hyob.hyobtodoapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.todoMainFragment)

//        TEST COMMIT

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

}
