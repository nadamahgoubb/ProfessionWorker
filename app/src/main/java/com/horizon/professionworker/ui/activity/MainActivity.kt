package com.horizon.professionworker.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.horizon.professionworker.R

import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.horizon.professionworker.base.BaseActivity
import com.horizon.professionworker.data.repo.PrefsHelper
import com.horizon.professionworker.databinding.ActivityMainBinding
import com.horizon.professionworker.util.Constants
 import com.horizon.professionworker.util.ext.loadImage
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.nav_header.view.*

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(),
NavigationView.OnNavigationItemSelectedListener {

    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.progress = baseShowProgress
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

        reloadImage()

        binding.navViewSideNav.setNavigationItemSelectedListener(this)
        binding.tvLogout.setOnClickListener {

            PrefsHelper.clear()
            var intent = Intent(this, AuthActivity::class.java)
            intent.putExtra(Constants.Start, Constants.login)
            startActivity(intent)
            this?.finish()
        }
    }
    fun reloadImage() {
        var headerview = binding.navViewSideNav.getHeaderView(0);
        var menu =  binding.navViewSideNav.menu

        headerview.tv_name.setText(PrefsHelper.getUserData()?.name)
        headerview.iv_user.loadImage(PrefsHelper.getUserData()?.photo, placeHolderImage = R.drawable.empty_user, isCircular = true , errorImage = R.drawable.empty_user)

        //     menu.findItem(androidx.navigation.ui.R.id.logout).isVisible= true
        //    menu.findItem(androidx.navigation.ui.R.id.login).isVisible= false

        headerview.iv_cancel.setOnClickListener {
            closeDrawer()

        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        var fragment: Fragment? = null
        val fragmentManager: FragmentManager = supportFragmentManager
       if (id ==  R.id.settingsFragment) {

            navController.navigate(R.id.settingsFragment)
        } else if (id == R.id.rightAndTermsFragment) {
            navController.navigate(R.id.rightAndTermsFragment)


        } else if (id == R.id.customerServiceFragment) {
            navController.navigate(R.id.customerServiceFragment)

        } else if (id == R.id.contactUsFragment) {
            navController.navigate(R.id.contactUsFragment)

        }
        closeDrawer()

        return true
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
        binding.drawerLayout.openDrawer(Gravity.END);

    }

    fun closeDrawer() {
        binding.drawerLayout.closeDrawer(Gravity.END)
    }

    fun showBottomNav(isVisible: Boolean) {
        binding.navViewBottom.isVisible = isVisible
    }

    fun showSideNav(isVisible: Boolean) {
        binding.navViewSideNav.isVisible = isVisible
    }

}