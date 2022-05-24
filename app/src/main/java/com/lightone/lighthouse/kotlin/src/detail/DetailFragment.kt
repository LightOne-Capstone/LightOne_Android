package com.lightone.lighthouse.kotlin.src.detail

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentDetailBinding
import com.lightone.lighthouse.kotlin.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(R.layout.fragment_detail) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_detail // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: DetailViewModel by viewModel()

    lateinit var navController: NavController

    lateinit var chart: LineChart


    override fun initStartView() {
        navController = Navigation.findNavController(requireView())
    }

    override fun initDataBinding() {

        chart = binding.detailChart

        val values: ArrayList<Entry> = ArrayList()

        for (i in 0..30) {
            val value = (Math.random()).toFloat()
            values.add(Entry(i.toFloat(), value))
        }

        val set1 = LineDataSet(values, null)

        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(set1) // add the data sets
        // line color
        set1.color = Color.parseColor("#0DB7B7")
        set1.lineWidth = 2f
        // 꼭지점
        set1.setCircleColor(Color.parseColor("#4D0DB7B7"))
        // chart 하단
        set1.valueTextSize = 0f
        set1.setDrawFilled(true) //그래프 밑부분 색칠
        set1.setDrawCircles(false) // 그래프 둥글게

        set1.fillDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.chart_color)

        // create a data object with the data sets
        val data = LineData(dataSets)

        // set data
        chart.data = data
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