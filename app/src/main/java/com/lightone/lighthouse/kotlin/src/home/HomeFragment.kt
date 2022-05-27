package com.lightone.lighthouse.kotlin.src.home

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.lightone.lighthouse.kotlin.Database.model.UserScrap
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.config.MyApplication
import com.lightone.lighthouse.kotlin.databinding.FragmentHomeBinding
import com.lightone.lighthouse.kotlin.src.home.adapter.DaysAdapter
import com.lightone.lighthouse.kotlin.src.home.adapter.SectorAdapter
import com.lightone.lighthouse.kotlin.src.home.model.Days
import com.lightone.lighthouse.kotlin.src.home.model.Reports
import com.lightone.lighthouse.kotlin.util.DeleteScrapTouchCallback
import com.lightone.lighthouse.kotlin.viewmodel.HomeViewModel
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_home // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: HomeViewModel by viewModel()
    private val daysAdapter : DaysAdapter by inject()
    private val sectorAdapter : SectorAdapter by inject()

    lateinit var navController: NavController
    val itemList = ArrayList<Reports>()

    override fun initStartView() {
        navController = Navigation.findNavController(requireView())

        binding.daysRecycler.run {
            adapter = daysAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
        viewModel.getReportList()
        showLoadingDialog(requireContext())
    }

    override fun initDataBinding() {
        viewModel.reportdResponse.observe(this, Observer {
            daysAdapter.clear()
            itemList.clear()
            var dateCheck = it[0].date
            var dayCount = 1

            for(i in it.indices){
                if(dayCount > 7){
                    break
                }
                val item = it[i]
                itemList.add(item)
                if(item.date != dateCheck){
                    val request = Days(dateCheck, itemList)
                    dateCheck = item.date
                    daysAdapter.addItem(request)
                    dayCount+=1
                }
            }
            daysAdapter.notifyDataSetChanged()

            CoroutineScope(Dispatchers.Main).launch {
                delay(100)
                dismissLoadingDialog()
            }
        })
    }

    override fun initAfterBinding() {
        // move detail
        daysAdapter.moveItemClickListener(object : DaysAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val args = MyApplication.sSharedPreferences.getString("Idx", null)
                Log.d("click_log", args.toString())
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(args!!)
                navController.navigate(action)
            }
        })

        // scrap
        daysAdapter.scrapItemClickListener(object : DaysAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val sceapIdx = MyApplication.sSharedPreferences.getString("scrapIdx", null)
                var request: Reports? = null
                for(i in 0 until itemList.size){
                    if(itemList[i].company_id == sceapIdx){request = itemList[i]}
                }

                val insert = UserScrap(request!!.company_id, request.company_name, request.suggestion, request.currentPrice,
                    request.targetPrice, request.writerCompany, request.writer)
                viewModel.insertSearch(insert)
                showLoadingDialog(requireContext())

                viewModel.scrapResponse.observe(this@HomeFragment, Observer {
                    if(it){
                        dismissLoadingDialog()
                    }
                })
            }
        })

        binding.scrapBtn.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_scrapFragment)
        }
    }

}