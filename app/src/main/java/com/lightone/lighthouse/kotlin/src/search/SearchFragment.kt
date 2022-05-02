package com.lightone.lighthouse.kotlin.src.search

import android.R.id
import androidx.core.widget.addTextChangedListener
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentSearchBinding
import com.lightone.lighthouse.kotlin.src.search.adapter.SearchViewpagerFragmentAdapter
import com.lightone.lighthouse.kotlin.viewmodel.SearchViewModel
import android.text.Editable

import android.text.TextWatcher

import android.R.id.edit
import android.view.View


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_search // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SearchViewModel by viewModel()

    override fun initStartView() {
        val viewPager: ViewPager2 = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout

        val viewpagerFragmentAdapter = SearchViewpagerFragmentAdapter(this)
        viewPager.adapter = viewpagerFragmentAdapter

        val tabTitles = listOf("최근 검색", "태그 검색")
        // 2. TabLayout과 ViewPager2를 연결하고, TabItem의 메뉴명을 설정한다.
        TabLayoutMediator(tabLayout, viewPager, {tab, position ->
            tab.text = tabTitles[position]
        }).attach()
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        binding.searchTxt.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // 입력난에 변화가 있을 시 조치
                if(s.length != 0){
                    binding.searchCloseBtn.visibility = View.VISIBLE
                }
                else{
                    binding.searchCloseBtn.visibility = View.GONE
                }
            }

            override fun afterTextChanged(arg0: Editable) = Unit
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit
        })

        binding.searchCloseBtn.setOnClickListener {
            binding.searchTxt.text = null
        }
    }

}