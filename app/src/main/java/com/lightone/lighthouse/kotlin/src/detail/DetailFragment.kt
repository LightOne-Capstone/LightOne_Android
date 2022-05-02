package com.lightone.lighthouse.kotlin.src.detail

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentDetailBinding
import com.lightone.lighthouse.kotlin.databinding.FragmentHomeBinding
import com.lightone.lighthouse.kotlin.src.home.adapter.DaysAdapter
import com.lightone.lighthouse.kotlin.src.home.model.Days
import com.lightone.lighthouse.kotlin.src.home.model.Sectors
import com.lightone.lighthouse.kotlin.viewmodel.DetailViewModel
import com.lightone.lighthouse.kotlin.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(R.layout.fragment_detail) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_detail // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: DetailViewModel by viewModel()

    lateinit var navController: NavController

    override fun initStartView() {
        navController = Navigation.findNavController(requireView())
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        binding.backBtn.setOnClickListener {
            navController.navigate(R.id.action_detailFragment_to_homeFragment)
        }
    }
}