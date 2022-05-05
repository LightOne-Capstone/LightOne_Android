package com.lightone.lighthouse.kotlin.src.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.lightone.lighthouse.kotlin.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.config.BaseActivity
import com.lightone.lighthouse.kotlin.databinding.ActivitySplashBinding
import com.lightone.lighthouse.kotlin.src.NavHostActivity
import com.lightone.lighthouse.kotlin.viewmodel.SplashViewModel


class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_splash // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SplashViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
        Handler(Looper.getMainLooper()).postDelayed({
            var intent = Intent(this, NavHostActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }

    override fun initAfterBinding() {
    }
}


