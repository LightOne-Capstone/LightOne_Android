package com.lightone.lighthouse.kotlin.src.search

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentRecentSearchBinding
import com.lightone.lighthouse.kotlin.src.search.adapter.RecentsAdapter
import com.lightone.lighthouse.kotlin.src.search.adapter.SearchAdapter
import com.lightone.lighthouse.kotlin.src.search.model.GetSearchResponse
import com.lightone.lighthouse.kotlin.src.search.model.Recents
import com.lightone.lighthouse.kotlin.viewmodel.RecentSearchViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception


class RecentSearchFragment : BaseFragment<FragmentRecentSearchBinding, RecentSearchViewModel>(R.layout.fragment_recent_search) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_recent_search // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: RecentSearchViewModel by viewModel()
    private val recentAdapter : RecentsAdapter by inject()
    private val searchAdpater : SearchAdapter by inject()

    lateinit var navController: NavController

    var search = MutableLiveData<String>()

    override fun initStartView() {
        binding.recentRecycler.run {
            adapter = recentAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
        search.postValue("")
    }

    override fun initDataBinding() {
        search.observe(this, Observer {
            if(it == null || it == ""){
                binding.recentRecycler.adapter = recentAdapter

                viewModel.getRecentList().observe(this, Observer { item ->
                    recentAdapter.clear()
                    item.forEach { items ->
                        recentAdapter.addItem(items)
                    }
                    recentAdapter.notifyDataSetChanged()
                })
            }
            else{
                viewModel.search(it)
                binding.recentRecycler.adapter = searchAdpater
            }
        })

        viewModel.searchResponse.observe(this, Observer {
            searchAdpater.clear()
            it.forEach { item ->
                searchAdpater.addItem(item)
            }
            searchAdpater.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {
        // Recycler view item click event 처리
        recentAdapter.delteItemClickListener(object : RecentsAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val request = recentAdapter.getItem(a_position)

                viewModel.delete(request.contents)
                viewModel.deletesuccessResponse.observe(this@RecentSearchFragment, Observer {
                    if(it){
                        recentAdapter.deleteItem(a_position)
                        recentAdapter.notifyDataSetChanged()
                    }
                })
            }
        })

        searchAdpater.moveItemClickListener(object : SearchAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                navController.navigate(R.id.action_recentFragment_to_detailFragment)
            }
        })
    }

}