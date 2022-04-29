package com.lightone.lighthouse.kotlin.src.detail

import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentHomeBinding
import com.lightone.lighthouse.kotlin.src.home.adapter.DaysAdapter
import com.lightone.lighthouse.kotlin.src.home.model.Days
import com.lightone.lighthouse.kotlin.src.home.model.Sectors
import com.lightone.lighthouse.kotlin.viewmodel.DetailViewModel
import com.lightone.lighthouse.kotlin.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class DetailFragment : BaseFragment<FragmentHomeBinding, DetailViewModel>(R.layout.fragment_detail) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_detail // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: DetailViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }
}