package com.example.professionworker.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.professionworker.R

import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.professionworker.base.BaseActivity
import com.example.professionworker.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.nav_header.view.*

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavController()
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById( R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        //bottom nav
        binding.navViewBottom.setupWithNavController(navController)

        binding.navViewSideNav.setupWithNavController(navController)

        //actionBarDrawerToggle =
        ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
           R.string.nav_open,
           R.string.nav_close
        )


        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
           R.string.nav_open,
           R.string.nav_close
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        var headerview = binding.navViewSideNav.getHeaderView(0);
        headerview.iv_cancel.setOnClickListener {
            closeDrawer()

        }
    }

    //bottom nav
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    //open drawer when drawer icon clicked and back btn presse
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(
            appBarConfiguration
        )
    }

    fun openDrawer() {
        //    if(!binding.drawerLayout.isVisible)
        //    binding.drawerLayout.openDrawer(GravityCompat.END)
        binding.drawerLayout.openDrawer(Gravity.LEFT);

    }

    fun closeDrawer() {
        binding.drawerLayout.closeDrawer(Gravity.LEFT)
    }

    fun showBottomNav(isVisible: Boolean) {
        binding.navViewBottom.isVisible = isVisible
    }

    fun showSideNav(isVisible: Boolean) {
        binding.navViewSideNav.isVisible = isVisible
    }

}
