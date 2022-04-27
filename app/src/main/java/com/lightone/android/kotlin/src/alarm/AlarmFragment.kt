package com.lightone.android.kotlin.src.alarm

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lightone.android.kotlin.R
import com.lightone.android.kotlin.config.BaseFragment
import com.lightone.android.kotlin.databinding.FragmentHomeBinding
import com.lightone.android.kotlin.viewmodel.AlarmViewModel
import com.lightone.android.kotlin.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class AlarmFragment : BaseFragment<FragmentHomeBinding, AlarmViewModel>(R.layout.fragment_alarm) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_alarm // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: AlarmViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}