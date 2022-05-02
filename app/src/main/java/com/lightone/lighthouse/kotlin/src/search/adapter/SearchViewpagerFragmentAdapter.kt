package com.lightone.lighthouse.kotlin.src.search.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lightone.lighthouse.kotlin.src.search.RecentSearchFragment
import com.lightone.lighthouse.kotlin.src.search.SearchFragment
import com.lightone.lighthouse.kotlin.src.search.TagSearchFragment

class SearchViewpagerFragmentAdapter(searchFragment: SearchFragment): FragmentStateAdapter(searchFragment) {

    val fragmentList = listOf<Fragment>(RecentSearchFragment(), TagSearchFragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemId(position: Int): Long {
        // generate new id
        return fragmentList[position].hashCode().toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        // false if item is changed
        return fragmentList.find { it.hashCode().toLong() == itemId } != null
    }
}
