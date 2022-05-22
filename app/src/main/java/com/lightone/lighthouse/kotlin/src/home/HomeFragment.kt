package com.lightone.lighthouse.kotlin.src.home

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentHomeBinding
import com.lightone.lighthouse.kotlin.src.home.adapter.DaysAdapter
import com.lightone.lighthouse.kotlin.src.home.adapter.SectorAdapter
import com.lightone.lighthouse.kotlin.src.home.model.Days
import com.lightone.lighthouse.kotlin.src.home.model.Sectors
import com.lightone.lighthouse.kotlin.util.ScrapTouchCallback
import com.lightone.lighthouse.kotlin.viewmodel.HomeViewModel
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_home // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: HomeViewModel by viewModel()
    private val daysAdapter : DaysAdapter by inject()
    private val sectorAdapter : SectorAdapter by inject()

    lateinit var navController: NavController

    override fun initStartView() {
        navController = Navigation.findNavController(requireView())

        binding.daysRecycler.run {
            adapter = daysAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        val sectors1 = Sectors("카카오", 95000, 129000, "BUY", "유진투자증권")
        val sectors2 = Sectors("위메프", 95000, 100000, "NR", "유진투자증권")
        val sectors3 = Sectors("배달의민족", 95000, 129000, "HOLD", "유진투자증권")
        val sectors4 = Sectors("요기요", 95000, 200000, "BUY", "유진투자증권")

        var sectorList = ArrayList<Sectors>()
        sectorList.add(sectors1)
        sectorList.add(sectors2)
        sectorList.add(sectors3)
        sectorList.add(sectors4)

        for(i in 0..6){
            val days = Days("2022.09.01 MON", sectorList)
            daysAdapter.addItem(days)
        }
        daysAdapter.notifyDataSetChanged()
    }

    override fun initAfterBinding() {
        // move detail
        daysAdapter.moveItemClickListener(object : DaysAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                navController.navigate(R.id.action_homeFragment_to_detailFragment)
            }
        })

        binding.scrapBtn.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_scrapFragment)
        }
    }

}