package com.lightone.android.kotlin.src.main

import com.lightone.android.kotlin.R
import com.lightone.android.kotlin.config.BaseActivity
import com.lightone.android.kotlin.databinding.ActivityMainBinding
import com.lightone.android.kotlin.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: MainViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }
}