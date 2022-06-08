package com.lightone.lighthouse.kotlin.src.detail

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.util.Linkify
import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.config.MyConstant.Companion.BASE_URL
import com.lightone.lighthouse.kotlin.databinding.FragmentDetailBinding
import com.lightone.lighthouse.kotlin.src.detail.model.Chart
import com.lightone.lighthouse.kotlin.src.dialog.NoReportDialog
import com.lightone.lighthouse.kotlin.util.priceFormatter
import com.lightone.lighthouse.kotlin.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.regex.Pattern


class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(R.layout.fragment_detail) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_detail // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: DetailViewModel by viewModel()

    lateinit var navController: NavController

    lateinit var chart: LineChart

    var itemList = ArrayList<Chart>()
    var date = ""

    override fun initStartView() {
        navController = Navigation.findNavController(requireView())
        val args: DetailFragmentArgs by navArgs()
        val request = args.idx
        date = args.date
        viewModel.detailChart(request)
        showLoadingDialog(requireContext())
    }

    override fun initDataBinding() {
        chart = binding.detailChart

        viewModel.detailchartResponse.observe(viewLifecycleOwner) { it ->
            itemList.clear()
            it.prices.forEach { item->
                itemList.add(item)
            }
            setupChart(null)
            monthClick(binding.dayBtn, binding.day30Btn, binding.day60Btn, binding.day90Btn)

            // 보고서 내용 관련
            if(it.reports.isEmpty()){
                val noReportDialog: NoReportDialog = NoReportDialog {
                    when (it) {
                        1 -> {
                            navController.popBackStack()
                        }
                    }
                }
                noReportDialog.show(requireActivity().supportFragmentManager, noReportDialog.tag)
            }
            else{
                var detail = it.reports[0]
                it.reports.forEach { item ->
                    if(item.date == date){
                        detail = item
                    }
                }

                binding.companyTxt.text = detail.company_name
                binding.serialTxt.text = detail.company_id
                binding.daysTxt.text = detail.date+"기준"
                binding.priceTxt.text = priceFormatter(detail.currentPrice) +"원"
                suggestBackground(detail.suggestion)
                binding.reportContent.text = detail.title
                binding.reportSummary.text = detail.summery
                binding.reportName.text = detail.writerCompany+" "+detail.writer
                if(detail.targetPrice == 0){
                    binding.targetPrice.text = "목표가 미정"
                }
                else{
                    binding.targetPrice.text = "목표가 "+priceFormatter(detail.targetPrice) +"원"
                }
                binding.reportDate.text = detail.date
                binding.reportPdfUrl.text = detail.pdfURL

                val text = detail.pdfURL
                val tvLinkify: TextView = binding.reportPdfUrl
                tvLinkify.text = text
                val mTransform =
                    Linkify.TransformFilter { _, _ -> "" }
                val pattern1: Pattern = Pattern.compile(text)
                Linkify.addLinks(tvLinkify, pattern1, text, null, mTransform)

                var word = detail.pdfURL.split("/")
                Log.d("glide_response", BASE_URL+"/"+word.last()+".png")
                Glide.with(this)
                    .load(BASE_URL+"/wordcloud/"+word.last()+".png")
                    .fitCenter()
                    .into(binding.wordImg)
            }
            dismissLoadingDialog()
        }
    }

    override fun initAfterBinding() {
        binding.backBtn.setOnClickListener {
            navController.popBackStack()
        }

        binding.scrapBtn.setOnClickListener {
            navController.navigate(R.id.action_detailFragment_to_scrapFragment)
        }

        binding.dayBtn.setOnClickListener {
            setupChart(null)
            monthClick(binding.dayBtn, binding.day30Btn, binding.day60Btn, binding.day90Btn)
        }
        binding.day30Btn.setOnClickListener {
            setupChart(30)
            monthClick(binding.day30Btn, binding.day60Btn, binding.dayBtn, binding.day90Btn)
        }
        binding.day60Btn.setOnClickListener {
            setupChart(60)
            monthClick(binding.day60Btn, binding.day30Btn, binding.dayBtn, binding.day90Btn)
        }
        binding.day90Btn.setOnClickListener {
            setupChart(90)
            monthClick(binding.day90Btn, binding.day30Btn, binding.day60Btn, binding.dayBtn)
        }

//        binding.reportPdfUrl.setOnClickListener {
//            val args = binding.reportPdfUrl.text.toString()
//            val name = binding.companyTxt.text.toString()
//            val action = DetailFragmentDirections.actionDetailFragmentToPdfFragment(args, name)
//            navController.navigate(action)
//        }
    }

    private fun suggestBackground(data: String) {
        binding.statusTxt.text = data
        when (data) {
            "BUY" -> {
                binding.statusTxt.setBackgroundResource(R.drawable.buy_custom)
            }
            "NR" -> {
                binding.statusTxt.setBackgroundResource(R.drawable.nr_custom)
            }
            else -> {
                binding.statusTxt.setBackgroundResource(R.drawable.hold_custom)
            }
        }
    }

    private fun setupChart(days: Int?){
        // 차트 관련
        val value: ArrayList<Entry> = ArrayList()
        if(days != null){
            for(i in 0..days){
                value.add(Entry(i.toFloat(), (itemList[i].terminalPrice/100).toFloat()))
            }
        }
        else{
            for(i in 0..100){
                value.add(Entry(i.toFloat(), (itemList[i].terminalPrice/100).toFloat()))
            }
        }

        val set = LineDataSet(value, "평균가격")

        // line color
        set.color = Color.parseColor("#0DB7B7")
        set.lineWidth = 2f

        // 꼭지점
        set.setCircleColor(Color.parseColor("#4D0DB7B7"))

        // chart 하단
        set.valueTextSize = 0f
        set.setDrawFilled(true) //그래프 밑부분 색칠
        set.setDrawCircles(false) // 그래프 꼭지점
        set.setDrawCircleHole(false) // 선 굵기

        set.fillDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.chart_color)

        // add the data sets
        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(set)

        // create a data object with the data sets
        val data = LineData(dataSets)
        // set data
        chart.data = data

        // 차트 최신화
        chart.invalidate()
    }

    private fun monthClick(select: TextView, other1: TextView, other2: TextView, other3: TextView){
        select.setBackgroundResource(R.drawable.chart_month_custom)
        select.setTextColor(Color.parseColor("#FFFFFF"))

        other1.setTextColor(Color.parseColor("#000000"))
        other1.setBackgroundColor(Color.parseColor("#FFFFFF"))
        other2.setTextColor(Color.parseColor("#000000"))
        other2.setBackgroundColor(Color.parseColor("#FFFFFF"))
        other3.setTextColor(Color.parseColor("#000000"))
        other3.setBackgroundColor(Color.parseColor("#FFFFFF"))
    }
}