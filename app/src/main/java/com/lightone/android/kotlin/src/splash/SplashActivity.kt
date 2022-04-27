package com.lightone.android.kotlin.src.splash

import com.lightone.android.kotlin.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.android.kotlin.config.BaseActivity
import com.lightone.android.kotlin.databinding.ActivitySplashBinding
import com.lightone.android.kotlin.viewmodel.SplashViewModel


class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_splash // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SplashViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }
}


