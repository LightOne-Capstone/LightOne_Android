package com.lightone.lighthouse.kotlin.src.search

import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentHomeBinding
import com.lightone.lighthouse.kotlin.databinding.FragmentTagSearchBinding
import com.lightone.lighthouse.kotlin.viewmodel.SearchViewModel
import com.lightone.lighthouse.kotlin.viewmodel.TagSearchViewModel

class TagSearchFragment : BaseFragment<FragmentTagSearchBinding, TagSearchViewModel>(R.layout.fragment_tag_search) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_tag_search // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: TagSearchViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}