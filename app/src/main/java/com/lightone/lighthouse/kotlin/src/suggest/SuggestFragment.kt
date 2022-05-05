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
    }

    override fun initDataBinding() {
        val item1 = Suggests(R.drawable.ic_airplane, "항공사", 0)
        val item2 = Suggests(R.drawable.ic_medical, "의약품", 7)
        val item3 = Suggests(R.drawable.ic_automobile, "자동차", 13)
        val item4 = Suggests(R.drawable.ic_enter, "엔터테이트먼트", 90)
        val item5 = Suggests(R.drawable.ic_moive, "영화", 30)

        suggestAdapter.addItem(item1)
        suggestAdapter.addItem(item2)
        suggestAdapter.addItem(item3)
        suggestAdapter.addItem(item4)
        suggestAdapter.addItem(item5)
        suggestAdapter.notifyDataSetChanged()
    }

    override fun initAfterBinding() {
        // move detail
        suggestAdapter.moveItemClickListener(object : SuggestAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                navController.navigate(R.id.action_homeFragment_to_detailFragment)
            }
        })
    }
}