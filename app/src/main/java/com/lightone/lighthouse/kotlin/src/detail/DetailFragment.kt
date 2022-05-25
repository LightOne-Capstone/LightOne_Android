package com.lightone.lighthouse.kotlin.src.detail

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentDetailBinding
import com.lightone.lighthouse.kotlin.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(R.layout.fragment_detail) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_detail // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: DetailViewModel by viewModel()

    lateinit var navController: NavController

    lateinit var chart: LineChart


    override fun initStartView() {
        navController = Navigation.findNavController(requireView())
        val args: DetailFragmentArgs by navArgs()
        val request = args.idx
        viewModel.detailChart(request)
    }

    override fun initDataBinding() {

        viewModel.detailchartResponse.observe(viewLifecycleOwner, Observer {
            chart = binding.detailChart
            val value: ArrayList<Entry> = ArrayList()

            for(i in 0..100){
                value.add(Entry(i.toFloat(), (it.prices[i].terminalPrice/100).toFloat()))
            }

            val set = LineDataSet(value, "평균가격")

            // add the data sets
            val dataSets: ArrayList<ILineDataSet> = ArrayList()
            dataSets.add(set)

            // line color
            set.color = Color.parseColor("#0DB7B7")
            set.lineWidth = 2f

            // 꼭지점
            set.setCircleColor(Color.parseColor("#4D0DB7B7"))

            // chart 하단
            set.valueTextSize = 0f
            set.setDrawFilled(true) //그래프 밑부분 색칠
            set.setDrawCircles(false) // 그래프 둥글게

            set.fillDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.chart_color)

            // create a data object with the data sets
            val data = LineData(dataSets)

            // set data
            chart.data = data
        })
    }

    override fun initAfterBinding() {
        binding.backBtn.setOnClickListener {
            navController.popBackStack()
        }

//        binding.scrapBtn.setOnClickListener {
//            navController.navigate(R.id)
//        }
    }
}