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
import com.lightone.lighthouse.kotlin.Database.model.UserScrap
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentScrapBinding
import com.lightone.lighthouse.kotlin.src.dialog.AddScrapDialog
import com.lightone.lighthouse.kotlin.src.dialog.DeleteScrapDialog
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

    var itemSuggestion: String = ""

    private val swipeHelperCallback = DeleteScrapTouchCallback().apply {
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
        viewModel.getScrapList().observe(this) {
            scrapAdapter.clear()
            it.forEach { item ->
                scrapAdapter.addItem(item)
            }

            scrapAdapter.notifyDataSetChanged()
        }

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
                val date = scrapAdapter.getItem(a_position).date
                Log.d("click_log", args.toString())
                val action = ScrapFragmentDirections.actionScrapFragmentToDetailFragment(args!!, date)
                navController.navigateUp()
                navController.navigate(action)
            }
        })

        // delete btn
        scrapAdapter.deleteItemClickListener(object : ScrapeAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val deleteScrapDialog: DeleteScrapDialog = DeleteScrapDialog {
                    when (it) {
                        1 -> {
                            val idx = scrapAdapter.getItem(a_position).idx
                            swipeHelperCallback.removeNowClamp(binding.scrapRecycler)
                            showLoadingDialog(requireContext())
                            viewModel.deleteScrap(idx)
                        }
                    }
                }
                deleteScrapDialog.show(requireActivity().supportFragmentManager, deleteScrapDialog.tag)

            }
        })

        binding.backBtn.setOnClickListener {
            navController.popBackStack()
        }

        binding.sortBuyBtn.setOnClickListener {
            sortClick(binding.sortBtn, binding.sortBuyBtn, binding.sortNrBtn, binding.sortHoldBtn, "BUY")
        }
        binding.sortNrBtn.setOnClickListener {
            sortClick(binding.sortBtn, binding.sortNrBtn, binding.sortBuyBtn, binding.sortHoldBtn, "NR")
        }
        binding.sortHoldBtn.setOnClickListener {
            sortClick(binding.sortBtn, binding.sortHoldBtn, binding.sortBuyBtn, binding.sortNrBtn, "HOLD")
        }

        binding.sortBtn.setOnClickListener {
            sortClear()
        }

    }

    private fun sortClick(all: ImageView, click: TextView, another1: TextView, another2: TextView, status: String){
        all.setImageResource(R.drawable.ic_sort_click)
        click.setTextColor(Color.parseColor("#FFFFFF"))
        itemSuggestion = status
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

        viewModel.getScrapList().observe(this) {
            scrapAdapter.clear()
            it.forEach { item ->
                if(item.suggestion == itemSuggestion){
                    scrapAdapter.addItem(item)
                }
            }
            scrapAdapter.notifyDataSetChanged()
        }
    }

    private fun sortClear(){
        binding.sortBtn.setImageResource(R.drawable.ic_sort)
        itemSuggestion = ""

        binding.sortBuyBtn.setTextColor(Color.parseColor("#000000"))
        binding.sortBuyBtn.setBackgroundResource(R.drawable.sort_btn_custom)

        binding.sortNrBtn.setTextColor(Color.parseColor("#000000"))
        binding.sortNrBtn.setBackgroundResource(R.drawable.sort_btn_custom)

        binding.sortHoldBtn.setTextColor(Color.parseColor("#000000"))
        binding.sortHoldBtn.setBackgroundResource(R.drawable.sort_btn_custom)

        viewModel.getScrapList().observe(this) {
            scrapAdapter.clear()
            it.forEach { item ->
                scrapAdapter.addItem(item)
            }
            scrapAdapter.notifyDataSetChanged()
        }
    }
}