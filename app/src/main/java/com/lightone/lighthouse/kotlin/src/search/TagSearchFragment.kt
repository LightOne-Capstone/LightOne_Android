package com.lightone.lighthouse.kotlin.src.search

import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentHomeBinding
import com.lightone.lighthouse.kotlin.databinding.FragmentTagSearchBinding
import com.lightone.lighthouse.kotlin.src.search.adapter.RecentsAdapter
import com.lightone.lighthouse.kotlin.src.search.adapter.TagAdapter
import com.lightone.lighthouse.kotlin.src.search.model.Tags
import com.lightone.lighthouse.kotlin.src.suggest.SuggestFragmentDirections
import com.lightone.lighthouse.kotlin.src.suggest.model.Suggests
import com.lightone.lighthouse.kotlin.viewmodel.SearchViewModel
import com.lightone.lighthouse.kotlin.viewmodel.TagSearchViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TagSearchFragment : BaseFragment<FragmentTagSearchBinding, SearchViewModel>(R.layout.fragment_tag_search) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_tag_search // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SearchViewModel by sharedViewModel()

    private val tagAdapter : TagAdapter by inject()

    lateinit var navController: NavController

    override fun initStartView() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)

        binding.tagRecycler.run {
            adapter = tagAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }

        viewModel.tagList()
    }

    override fun initDataBinding() {
        viewModel.tagResponse.observe(this) {
            tagAdapter.clear()
            it.sortedBy { it.count }
            val tagList = it.slice(0..11)
            Log.d("tag_response", tagList.toString())
            var count = 1
            tagList.forEach { item ->
                val items = Tags(count, item.category, item.count)
                tagAdapter.addItem(items)
                count+=1
            }
            tagAdapter.notifyDataSetChanged()
        }
    }

    override fun initAfterBinding() {
        // item next to detail
        tagAdapter.nextItemClickListener(object : TagAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val request = tagAdapter.getItem(a_position)

                val categoryName = request.category
                val action = SearchFragmentDirections.actionSearchFragmentToSuggestDetailFragment(categoryName, "null")
                navController.navigate(action)
            }
        })
    }

}