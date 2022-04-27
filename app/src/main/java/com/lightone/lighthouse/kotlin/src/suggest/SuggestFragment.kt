package com.lightone.lighthouse.kotlin.src.suggest

import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentHomeBinding
import com.lightone.lighthouse.kotlin.viewmodel.SuggestViewModel

class SuggestFragment : BaseFragment<FragmentHomeBinding, SuggestViewModel>(R.layout.fragment_suggest) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_suggest // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SuggestViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}