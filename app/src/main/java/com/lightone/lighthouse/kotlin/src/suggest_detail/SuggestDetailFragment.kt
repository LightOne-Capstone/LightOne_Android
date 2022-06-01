package com.lightone.lighthouse.kotlin.src.suggest_detail

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.lightone.lighthouse.kotlin.Database.model.UserScrap
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.config.MyApplication
import com.lightone.lighthouse.kotlin.databinding.FragmentSuggestBinding
import com.lightone.lighthouse.kotlin.databinding.FragmentSuggestDetailBinding
import com.lightone.lighthouse.kotlin.src.detail.DetailFragmentArgs
import com.lightone.lighthouse.kotlin.src.home.HomeFragmentDirections
import com.lightone.lighthouse.kotlin.src.home.adapter.DaysAdapter
import com.lightone.lighthouse.kotlin.src.home.adapter.SectorAdapter
import com.lightone.lighthouse.kotlin.src.home.model.Reports
import com.lightone.lighthouse.kotlin.src.home.model.Sectors
import com.lightone.lighthouse.kotlin.src.suggest.adapter.SuggestAdapter
import com.lightone.lighthouse.kotlin.src.suggest.model.Suggests
import com.lightone.lighthouse.kotlin.src.suggest_detail.adapter.SuggestSectorAdapter
import com.lightone.lighthouse.kotlin.util.AddScrapTouchCallback
import com.lightone.lighthouse.kotlin.viewmodel.SuggestDetailViewModel
import com.lightone.lighthouse.kotlin.viewmodel.SuggestViewModel
import kotlinx.android.synthetic.main.days_item.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class SuggestDetailFragment : BaseFragment<FragmentSuggestDetailBinding, SuggestDetailViewModel>(R.layout.fragment_suggest_detail) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_suggest_detail // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SuggestDetailViewModel by viewModel()
    private val suggestsectorAdapter : SuggestSectorAdapter by inject()

    lateinit var navController: NavController

    override fun initStartView() {
        navController = Navigation.findNavController(requireView())
        val args: SuggestDetailFragmentArgs by navArgs()
        val categoryName = args.categoryName
        var days = args.days
        if(days == "null"){
            viewModel.suggestDetail(categoryName, null)
        }
        else{
            viewModel.suggestDetail(categoryName, days)
        }

        val swipeHelperCallback = AddScrapTouchCallback().apply {
            setClamp(200f)
        }
        val itemTouchHelper = ItemTouchHelper(swipeHelperCallback)

        itemTouchHelper.attachToRecyclerView(binding.suggestRecycler)
        binding.suggestRecycler.run {
            adapter = suggestsectorAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setOnTouchListener { _, _ ->
                swipeHelperCallback.removePreviousClamp(this)
                false
            }
            setHasFixedSize(true)
        }

        binding.detailName.text = categoryName
        if(days != "null"){
            binding.topTitle.text = days+"일 동안 발행된 리포트"
        }
        else{
            binding.topTitle.text = "전체기간 동안 발행된 리포트"
        }

    }

    override fun initDataBinding() {
        viewModel.suggestDetailResponse.observe(this) {
            it.forEach { item ->
                suggestsectorAdapter.addItem(item)
            }
            suggestsectorAdapter.notifyDataSetChanged()
            binding.countTxt.text = it.size.toString()+"개"
        }

        viewModel.scrapResponse.observe(this) {
            if(it){
                dismissLoadingDialog()
            }
        }
    }

    override fun initAfterBinding() {
        // move detail
        suggestsectorAdapter.moveItemClickListener(object : SuggestSectorAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val args = suggestsectorAdapter.getItem(a_position).company_id
                val action = SuggestDetailFragmentDirections.actionSuggestDetailFragmentToDetailFragment(args!!)
                navController.navigate(action)
            }
        })

        // scrap
        suggestsectorAdapter.scrapItemClickListener(object : SuggestSectorAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                var request = suggestsectorAdapter.getItem(a_position)

                val insert = UserScrap(request!!.company_id, request.company_name, request.suggestion, request.currentPrice,
                    request.targetPrice, request.writerCompany, request.writer)
                viewModel.insertSearch(insert)
                showLoadingDialog(requireContext())
            }
        })

        binding.backBtn.setOnClickListener {
            binding.sortBtn.setImageResource(R.drawable.ic_sort_click)
            navController.popBackStack()
        }

        binding.sortBtn.setOnClickListener {
            sortClear(binding.sortBtn, binding.sortBuyBtn, binding.sortHoldBtn, binding.sortNrBtn)
        }

        binding.sortBuyBtn.setOnClickListener {
            binding.sortBtn.setImageResource(R.drawable.ic_sort_click)
            suggestsectorAdapter.clear()
        }

        binding.sortNrBtn.setOnClickListener {
            suggestsectorAdapter.clear()
        }

        binding.sortHoldBtn.setOnClickListener {
            suggestsectorAdapter.clear()
            sortClick(binding.sortBtn, binding.sortHoldBtn, binding.sortNrBtn, binding.sortBuyBtn, "HOLD")
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