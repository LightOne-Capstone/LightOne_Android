package com.lightone.lighthouse.kotlin.src.suggest

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentSuggestBinding
import com.lightone.lighthouse.kotlin.src.suggest.adapter.SuggestAdapter
import com.lightone.lighthouse.kotlin.src.suggest.model.Suggests
import com.lightone.lighthouse.kotlin.viewmodel.SuggestViewModel
import org.koin.android.ext.android.inject

class SuggestFragment : BaseFragment<FragmentSuggestBinding, SuggestViewModel>(R.layout.fragment_suggest) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_suggest // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SuggestViewModel by viewModel()
    private val suggestAdapter : SuggestAdapter by inject()

    lateinit var navController: NavController

    override fun initStartView() {
        navController = Navigation.findNavController(requireView())

        binding.suggestRecycler.run {
            adapter = suggestAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }

        viewModel.getSuggest("30")
    }

    override fun initDataBinding() {
        viewModel.suggestResponse.observe(this){
            it.forEach { item ->
                suggestAdapter.addItem(item)
            }
            suggestAdapter.notifyDataSetChanged()
            binding.headTitle.text = it.size.toString()+"개의 증권사 리포트가\n주목하는 종목"
        }
    }

    override fun initAfterBinding() {
        // move detail
        suggestAdapter.moveItemClickListener(object : SuggestAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                navController.navigate(R.id.action_suggestFragment_to_suggestDetailFragment)
            }
        })
    }
}