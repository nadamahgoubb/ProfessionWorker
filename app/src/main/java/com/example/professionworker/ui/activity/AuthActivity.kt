package com.example.professionworker.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.professionworker.R
import com.example.professionworker.base.BaseActivity
import com.example.professionworker.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
                as NavHostFragment
        val    navController = navHostFragment.navController
     //   try {
            val inflater = navController.navInflater
            val graph = inflater.inflate(R.navigation.auth_navigation)
         /*   val extras = intent.extras
            if (extras != null) {
                val value = extras.getInt(Constants.Start)

                if (value == Constants.login) {
                    graph.setStartDestination(R.id.loginFragment)

                } else {
                    graph.setStartDestination(R.id.splashFragment)
                }

                val navController = navHostFragment.navController
                navController.setGraph(graph, intent.extras)

            }
        }
        catch (e:Exception){

        }
*/



    }}
