package com.lightone.lighthouse.kotlin.src.search

import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.annotations.SerializedName
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentRecentSearchBinding
import com.lightone.lighthouse.kotlin.src.search.adapter.RecentsAdapter
import com.lightone.lighthouse.kotlin.src.search.model.Search
import com.lightone.lighthouse.kotlin.viewmodel.RecentSearchViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecentSearchFragment(val search: String?) : BaseFragment<FragmentRecentSearchBinding, RecentSearchViewModel>(R.layout.fragment_recent_search) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_recent_search // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: RecentSearchViewModel by viewModel()
    private val recentAdapter : RecentsAdapter by inject()

    lateinit var navController: NavController

    override fun initStartView() {

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)

        binding.recentRecycler.run {
            adapter = recentAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        if(search != null){
            viewModel.search(search)
        }
        else{
            viewModel.getRecentList().observe(this, Observer { item ->
                recentAdapter.clear()
                item.forEach { items ->
                    val new = Search(items.idx.toString(), items.contents, "최근 검색", false)
                    recentAdapter.addItem(new)
                }
                recentAdapter.notifyDataSetChanged()
            })
        }

        viewModel.searchResponse.observe(this, Observer {
            recentAdapter.clear()
            it.forEach { item ->
                item.check = true
                recentAdapter.addItem(item)
            }
            recentAdapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {
        // Recycler view item delete
        recentAdapter.delteItemClickListener(object : RecentsAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val request = recentAdapter.getItem(a_position)

                viewModel.delete(request.id)
                viewModel.deletesuccessResponse.observe(this@RecentSearchFragment, Observer {
                    if(it){
                        recentAdapter.deleteItem(a_position)
                    }
                })
            }
        })

        // item next to detail
        recentAdapter.nextItemClickListener(object : RecentsAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val request = recentAdapter.getItem(a_position)

                navController.navigate(R.id.action_searchFragment_to_detailFragment)
            }
        })
    }

}