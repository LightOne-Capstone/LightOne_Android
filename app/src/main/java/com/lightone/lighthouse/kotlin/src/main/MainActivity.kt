package com.lightone.lighthouse.kotlin.src.main

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseActivity
import com.lightone.lighthouse.kotlin.databinding.ActivityMainBinding
import com.lightone.lighthouse.kotlin.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main // get() : 커스텀 접근자, 코틀린 문법

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
        bottom_navi.setupWithNavController(navController)
    }
}