package com.lightone.lighthouse.kotlin.src.suggest

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.config.MyApplication
import com.lightone.lighthouse.kotlin.databinding.FragmentSuggestBinding
import com.lightone.lighthouse.kotlin.src.home.HomeFragmentDirections
import com.lightone.lighthouse.kotlin.src.suggest.adapter.SuggestAdapter
import com.lightone.lighthouse.kotlin.src.suggest.model.Suggests
import com.lightone.lighthouse.kotlin.viewmodel.SuggestViewModel
import org.koin.android.ext.android.inject

class SuggestFragment : BaseFragment<FragmentSuggestBinding, SuggestViewModel>(R.layout.fragment_suggest) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_suggest // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SuggestViewModel by viewModel()
    private val suggestAdapter : SuggestAdapter by inject()

    lateinit var navController: NavController

    var days: String? = null

    override fun initStartView() {
        navController = Navigation.findNavController(requireView())

        binding.suggestRecycler.run {
            adapter = suggestAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
        daySelect(binding.dayBtn, binding.day15Btn, binding.day30Btn, binding.day60Btn)
    }

    override fun initDataBinding() {
        viewModel.suggestResponse.observe(this){
            suggestAdapter.clear()
            it.forEach { item ->
                suggestAdapter.addItem(item)
            }
            suggestAdapter.notifyDataSetChanged()
            binding.headTitle.text = it.size.toString()+"개의 증권사 리포트가\n주목하는 종목"
        }
    }

    override fun initAfterBinding() {
        // move detail
        suggestAdapter.moveItemClickListener(object : SuggestAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val categoryName = suggestAdapter.getItem(a_position).category
                val action = SuggestFragmentDirections.actionSuggestFragmentToSuggestDetailFragment(categoryName!!,
                    days.toString())
                navController.navigate(action)
            }
        })

        binding.dayBtn.setOnClickListener {
            daySelect(binding.dayBtn, binding.day15Btn, binding.day30Btn, binding.day60Btn)
        }
        binding.day15Btn.setOnClickListener {
            daySelect(binding.day15Btn, binding.dayBtn, binding.day60Btn, binding.day30Btn)
        }
        binding.day30Btn.setOnClickListener {
            daySelect(binding.day30Btn, binding.dayBtn, binding.day15Btn, binding.day60Btn)
        }
        binding.day60Btn.setOnClickListener {
            daySelect(binding.day60Btn, binding.dayBtn, binding.day30Btn, binding.day15Btn)
        }
    }

    fun daySelect(select: TextView, other1: TextView, other2: TextView, other3: TextView) {
        select.setBackgroundResource(R.drawable.hold_custom)
        select.setTextColor(Color.parseColor("#FFFFFF"))

        other1.setTextColor(Color.parseColor("#000000"))
        other1.setBackgroundResource(R.drawable.sort_btn_custom)
        other2.setTextColor(Color.parseColor("#000000"))
        other2.setBackgroundResource(R.drawable.sort_btn_custom)
        other3.setTextColor(Color.parseColor("#000000"))
        other3.setBackgroundResource(R.drawable.sort_btn_custom)

        days = when (select.text) {
            "15일" -> {
                "15"
            }
            "30일" -> {
                "30"
            }
            "60일" -> {
                "60"
            }
            else -> {
                null
            }
        }
        viewModel.getSuggest(days)
    }
}