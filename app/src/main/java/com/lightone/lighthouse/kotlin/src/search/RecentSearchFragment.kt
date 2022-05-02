package com.lightone.lighthouse.kotlin.src.search

import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentHomeBinding
import com.lightone.lighthouse.kotlin.databinding.FragmentRecentSearchBinding
import com.lightone.lighthouse.kotlin.src.home.model.Days
import com.lightone.lighthouse.kotlin.src.home.model.Sectors
import com.lightone.lighthouse.kotlin.src.search.adapter.RecentAdapter
import com.lightone.lighthouse.kotlin.src.search.model.Recents
import com.lightone.lighthouse.kotlin.viewmodel.RecentSearchViewModel
import com.lightone.lighthouse.kotlin.viewmodel.SearchViewModel
import okhttp3.internal.notify
import org.koin.android.ext.android.inject

class RecentSearchFragment : BaseFragment<FragmentRecentSearchBinding, RecentSearchViewModel>(R.layout.fragment_recent_search) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_recent_search // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: RecentSearchViewModel by viewModel()
    private val recentAdapter : RecentAdapter by inject()

    override fun initStartView() {
        binding.recentRecycler.run {
            adapter = recentAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        var recentList = ArrayList<Recents>()
        val items1 = Recents("삼성전자")
        val items2 = Recents("카카오")
        val items3 = Recents("위메프")
        val items4 = Recents("위대한 상상")

        for(i in 0..4){
            recentAdapter.addItem(items1)
            recentAdapter.addItem(items2)
            recentAdapter.addItem(items3)
            recentAdapter.addItem(items4)
        }
        recentAdapter.notifyDataSetChanged()
    }

    override fun initAfterBinding() {
    }

}