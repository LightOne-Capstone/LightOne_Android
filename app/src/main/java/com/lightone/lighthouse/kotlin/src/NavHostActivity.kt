package com.lightone.lighthouse.kotlin.src

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseActivity
import com.lightone.lighthouse.kotlin.databinding.ActivityNavhostBinding
import com.lightone.lighthouse.kotlin.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavHostActivity : BaseActivity<ActivityNavhostBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_navhost // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: MainViewModel by viewModel()

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun initStartView() {
        initNavController()
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    private fun initNavController() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showBottomNav()
                R.id.suggestFragment -> showBottomNav()
                R.id.searchFragment -> showBottomNav()
                R.id.suggestDetailFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
        binding.bottomNavi.setupWithNavController(navController)
    }

    private fun showBottomNav() {
        binding.bottomNavi.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.bottomNavi.visibility = View.GONE
    }
}