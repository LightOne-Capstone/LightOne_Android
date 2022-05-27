package com.lightone.lighthouse.kotlin.src.scrap

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentScrapBinding
import com.lightone.lighthouse.kotlin.src.scrap.adapter.ScrapeAdapter
import com.lightone.lighthouse.kotlin.util.DeleteScrapTouchCallback
import com.lightone.lighthouse.kotlin.viewmodel.ScraplViewModel
import kotlinx.android.synthetic.main.days_item.*
import org.koin.android.ext.android.inject

class ScrapFragment : BaseFragment<FragmentScrapBinding, ScraplViewModel>(R.layout.fragment_scrap) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_scrap // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: ScraplViewModel by viewModel()
    private val scrapAdapter : ScrapeAdapter by inject()

    lateinit var navController: NavController

    val swipeHelperCallback = DeleteScrapTouchCallback().apply {
        setClamp(200f)
    }

    override fun initStartView() {
        navController = Navigation.findNavController(requireView())

        val itemTouchHelper = ItemTouchHelper(swipeHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.scrapRecycler)

        binding.scrapRecycler.run {
            adapter = scrapAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setOnTouchListener { _, _ ->
                swipeHelperCallback.removePreviousClamp(this)
                false
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        viewModel.getScrapList().observe(this, Observer {
            scrapAdapter.clear()
            Log.d("scrap_list", it.toString())
            it.forEach { item ->
                scrapAdapter.addItem(item)
            }
            scrapAdapter.notifyDataSetChanged()
        })

        viewModel.deleteResponse.observe(this, Observer {
            dismissLoadingDialog()
        })
    }

    override fun initAfterBinding() {
        // move detail
        scrapAdapter.moveItemClickListener(object : ScrapeAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                navController.navigate(R.id.action_scrapFragment_to_detailFragment)
                val args = scrapAdapter.getItem(a_position).company_id
                Log.d("click_log", args.toString())
                val action = ScrapFragmentDirections.actionScrapFragmentToDetailFragment(args!!)
                navController.navigateUp()
                navController.navigate(action)
            }
        })

        // delete btn
        scrapAdapter.deleteItemClickListener(object : ScrapeAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val idx = scrapAdapter.getItem(a_position).idx
                showLoadingDialog(requireContext())
                viewModel.deleteScrap(idx)
            }
        })

        binding.backBtn.setOnClickListener {
            navController.popBackStack()
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