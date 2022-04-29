package com.lightone.lighthouse.kotlin.src.home

import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentHomeBinding
import com.lightone.lighthouse.kotlin.src.home.adapter.DaysAdapter
import com.lightone.lighthouse.kotlin.src.home.adapter.SectorAdapter
import com.lightone.lighthouse.kotlin.src.home.model.Days
import com.lightone.lighthouse.kotlin.src.home.model.Sectors
import com.lightone.lighthouse.kotlin.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_home // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: HomeViewModel by viewModel()
    private val daysAdapter : DaysAdapter by inject()
    private val sectorAdapter : SectorAdapter by inject()

    override fun initStartView() {
        days_recycler.run {
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

        // @SerializedName("days") val days: String,
        //    @SerializedName("sectors") val sectors: List<Sectors>

        for(i in 0..6){
            val days = Days("2022.09.01 MON", sectorList)
            daysAdapter.addItem(days)
        }
        daysAdapter.notifyDataSetChanged()
    }

    override fun initAfterBinding() {
        // move detail
//        sectorAdapter.setOnItemClickListener { a_view, a_position ->
//            val item: Sectors = sectorAdapter.getItem(a_position)
//
//        }
    }

}