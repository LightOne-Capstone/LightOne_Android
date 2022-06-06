package com.lightone.lighthouse.kotlin.src.search

import android.app.Application
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentRecentSearchBinding
import com.lightone.lighthouse.kotlin.src.detail.DetailFragmentArgs
import com.lightone.lighthouse.kotlin.src.search.adapter.RecentsAdapter
import com.lightone.lighthouse.kotlin.src.search.model.Search
import com.lightone.lighthouse.kotlin.src.suggest_detail.SuggestDetailFragmentDirections
import com.lightone.lighthouse.kotlin.viewmodel.SearchViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecentSearchFragment : BaseFragment<FragmentRecentSearchBinding, SearchViewModel>(R.layout.fragment_recent_search) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_recent_search // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SearchViewModel by sharedViewModel()

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
        viewModel.searchWord.observe(viewLifecycleOwner, Observer {
            if(it == ""){
                viewModel.getRecentList().observe(this, Observer { item ->
                    binding.recentTxt.visibility = View.VISIBLE
                    recentAdapter.clear()
                    item.forEach { items ->
                        val new = Search(items.idx.toString(), items.contents, "최근 검색", false)
                        recentAdapter.addItem(new)
                    }
                    recentAdapter.notifyDataSetChanged()
                })
            }
        })

        viewModel.searchResponse.observe(viewLifecycleOwner) {
            recentAdapter.clear()
            binding.recentTxt.visibility = View.GONE
            it.forEach { item ->
                item.check = true
                recentAdapter.addItem(item)
            }
            recentAdapter.notifyDataSetChanged()
        }
    }

    override fun initAfterBinding() {
        // Recycler view item delete
        recentAdapter.delteItemClickListener(object : RecentsAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val request = recentAdapter.getItem(a_position)

                viewModel.delete(request.id)
                recentAdapter.deleteItem(a_position)
                viewModel.deletesuccessResponse.observe(this@RecentSearchFragment, Observer {
                    recentAdapter.notifyDataSetChanged()
                })
            }
        })

        // item next to detail
        recentAdapter.nextItemClickListener(object : RecentsAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val request = recentAdapter.getItem(a_position)

                val args = request.id
                val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(args, "null")
                navController.navigate(action)
            }
        })
    }

}