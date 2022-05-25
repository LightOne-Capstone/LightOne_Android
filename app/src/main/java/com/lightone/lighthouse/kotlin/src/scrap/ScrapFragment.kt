package com.lightone.lighthouse.kotlin.src.scrap

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentScrapBinding
import com.lightone.lighthouse.kotlin.databinding.FragmentSuggestBinding
import com.lightone.lighthouse.kotlin.databinding.FragmentSuggestDetailBinding
import com.lightone.lighthouse.kotlin.src.home.adapter.SectorAdapter
import com.lightone.lighthouse.kotlin.src.home.model.Sectors
import com.lightone.lighthouse.kotlin.src.scrap.adapter.ScrapeAdapter
import com.lightone.lighthouse.kotlin.src.suggest.adapter.SuggestAdapter
import com.lightone.lighthouse.kotlin.src.suggest.model.Suggests
import com.lightone.lighthouse.kotlin.src.suggest_detail.adapter.SuggestSectorAdapter
import com.lightone.lighthouse.kotlin.viewmodel.ScraplViewModel
import com.lightone.lighthouse.kotlin.viewmodel.SuggestDetailViewModel
import com.lightone.lighthouse.kotlin.viewmodel.SuggestViewModel
import org.koin.android.ext.android.inject

class ScrapFragment : BaseFragment<FragmentScrapBinding, ScraplViewModel>(R.layout.fragment_scrap) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_scrap // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: ScraplViewModel by viewModel()
    private val scrapAdapter : ScrapeAdapter by inject()

    lateinit var navController: NavController

    override fun initStartView() {
        navController = Navigation.findNavController(requireView())

        binding.suggestRecycler.run {
            adapter = scrapAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        val sectors1 = Sectors("335870","카카오", 95000, 129000, "BUY", "유진투자증권")
        val sectors2 = Sectors("335870","위메프", 95000, 100000, "NR", "유진투자증권")
        val sectors3 = Sectors("335870","배달의민족", 95000, 129000, "HOLD", "유진투자증권")
        val sectors4 = Sectors("335870","요기요", 95000, 200000, "BUY", "유진투자증권")

        scrapAdapter.addItem(sectors1)
        scrapAdapter.addItem(sectors2)
        scrapAdapter.addItem(sectors3)
        scrapAdapter.addItem(sectors4)
        scrapAdapter.notifyDataSetChanged()
    }

    override fun initAfterBinding() {
        // move detail
        scrapAdapter.moveItemClickListener(object : ScrapeAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                navController.navigate(R.id.action_scrapFragment_to_detailFragment)
            }
        })

        binding.backBtn.setOnClickListener {
            navController.popBackStack()
        }

        binding.sortBtn.setOnClickListener {
            sortClear(binding.sortBtn, binding.sortBuyBtn, binding.sortHoldBtn, binding.sortNrBtn)

            scrapAdapter.clear()
            val sectors1 = Sectors("335870","카카오", 95000, 129000, "BUY", "유진투자증권")
            val sectors2 = Sectors("335870","위메프", 95000, 100000, "NR", "유진투자증권")
            val sectors3 = Sectors("335870","배달의민족", 95000, 129000, "HOLD", "유진투자증권")
            val sectors4 = Sectors("335870","요기요", 95000, 200000, "BUY", "유진투자증권")

            scrapAdapter.addItem(sectors1)
            scrapAdapter.addItem(sectors2)
            scrapAdapter.addItem(sectors3)
            scrapAdapter.addItem(sectors4)
            scrapAdapter.notifyDataSetChanged()
        }

        binding.sortBuyBtn.setOnClickListener {
            binding.sortBtn.setImageResource(R.drawable.ic_sort_click)
            scrapAdapter.clear()
            sortClick(binding.sortBtn, binding.sortBuyBtn, binding.sortNrBtn, binding.sortHoldBtn, "BUY")
            val sectors1 = Sectors("335870","카카오", 95000, 129000, "BUY", "유진투자증권")
            val sectors4 = Sectors("335870","요기요", 95000, 200000, "BUY", "유진투자증권")

            scrapAdapter.addItem(sectors1)
            scrapAdapter.addItem(sectors4)
            scrapAdapter.notifyDataSetChanged()
        }

        binding.sortNrBtn.setOnClickListener {
            scrapAdapter.clear()
            sortClick(binding.sortBtn, binding.sortNrBtn, binding.sortBuyBtn, binding.sortHoldBtn, "NR")
            val sectors2 = Sectors("335870","위메프", 95000, 100000, "NR", "유진투자증권")

            scrapAdapter.addItem(sectors2)
            scrapAdapter.notifyDataSetChanged()
        }

        binding.sortHoldBtn.setOnClickListener {
            scrapAdapter.clear()
            sortClick(binding.sortBtn, binding.sortHoldBtn, binding.sortNrBtn, binding.sortBuyBtn, "HOLD")
            val sectors3 = Sectors("335870","배달의민족", 95000, 129000, "HOLD", "유진투자증권")

            scrapAdapter.addItem(sectors3)
            scrapAdapter.notifyDataSetChanged()
        }
    }

    fun sortClick(all: ImageView, click: TextView, another1: TextView, another2: TextView, status: String){
        all.setImageResource(R.drawable.ic_sort_click)
        click.setTextColor(Color.parseColor("#FFFFFF"))
        if(status == "NR"){
            click.setBackgroundResource(R.drawable.nr_custom)
        }
        else if(status == "HOLD"){
            click.setBackgroundResource(R.drawable.hold_custom)
        }
        else{
            click.setBackgroundResource(R.drawable.buy_custom)
        }

        another1.setTextColor(Color.parseColor("#000000"))
        another1.setBackgroundResource(R.drawable.sort_btn_custom)
        another2.setTextColor(Color.parseColor("#000000"))
        another2.setBackgroundResource(R.drawable.sort_btn_custom)
    }

    fun sortClear(all: ImageView, item1: TextView, item2: TextView, item3: TextView){
        all.setImageResource(R.drawable.ic_sort)

        item1.setTextColor(Color.parseColor("#000000"))
        item1.setBackgroundResource(R.drawable.sort_btn_custom)
        item2.setTextColor(Color.parseColor("#000000"))
        item2.setBackgroundResource(R.drawable.sort_btn_custom)
        item3.setTextColor(Color.parseColor("#000000"))
        item3.setBackgroundResource(R.drawable.sort_btn_custom)
    }
}