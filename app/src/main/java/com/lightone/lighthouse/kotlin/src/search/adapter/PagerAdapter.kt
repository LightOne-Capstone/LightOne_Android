package com.lightone.lighthouse.kotlin.src.search.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.lightone.lighthouse.kotlin.src.search.RecentSearchFragment
import com.lightone.lighthouse.kotlin.src.search.TagSearchFragment


class PagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm!!) {
    var fragments: MutableList<Fragment> = ArrayList()
    override fun getItem(i: Int): Fragment {
        return fragments[i]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    init {
        fragments.add(RecentSearchFragment())
        fragments.add(TagSearchFragment())
    }
}