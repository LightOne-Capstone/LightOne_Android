package com.lightone.lighthouse.kotlin.src.search

import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentHomeBinding
import com.lightone.lighthouse.kotlin.viewmodel.SearchViewModel

class SearchFragment : BaseFragment<FragmentHomeBinding, SearchViewModel>(R.layout.fragment_search) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_search // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SearchViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}