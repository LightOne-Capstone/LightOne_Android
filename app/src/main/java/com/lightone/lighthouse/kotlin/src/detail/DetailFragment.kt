package com.lightone.lighthouse.kotlin.src.detail

import android.graphics.Color
import android.text.util.Linkify
import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.config.MyApplication
import com.lightone.lighthouse.kotlin.config.MyConstant.Companion.BASE_URL
import com.lightone.lighthouse.kotlin.databinding.FragmentDetailBinding
import com.lightone.lighthouse.kotlin.src.detail.model.ReportDetail
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

    override fun initStartView() {
        navController = Navigation.findNavController(requireView())
        val args: DetailFragmentArgs by navArgs()
        val request = args.idx
        viewModel.detailChart(request)
    }

    override fun initDataBinding() {

        viewModel.detailchartResponse.observe(viewLifecycleOwner, Observer { it ->
            // 차트 관련
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

            // 보고서 내용 관련
            if(it.reports.isEmpty()){
                navController.popBackStack()
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
                val detail = it.reports[0]
                binding.companyTxt.text = detail.company_name
                binding.serialTxt.text = detail.company_id
                binding.daysTxt.text = detail.date+"기준"
                binding.priceTxt.text = priceFormatter(detail.currentPrice) +"원"
                suggestBackground(detail.suggestion)
                binding.reportContent.text = detail.summery
                binding.reportDetailContent.text = detail.keyword
                binding.reportName.text = detail.writerCompany+" "+detail.writer
                binding.targetPrice.text = "목표주가 "+priceFormatter(detail.targetPrice)+"원"

                binding.reportPdfUrl.text = detail.pdfURL
                val text = detail.pdfURL
                val tvLinkify: TextView = binding.reportPdfUrl
                tvLinkify.text = text
                val mTransform =
                    Linkify.TransformFilter { match, url -> "" }
                val pattern1: Pattern = Pattern.compile(text)
                Linkify.addLinks(tvLinkify, pattern1, text, null, mTransform)

                var word = detail.pdfURL.split("/")
                Log.d("glide_response", BASE_URL+"/"+word.last()+".png")
                Glide.with(this)
                    .load(BASE_URL+"/wordcloud/"+word.last()+".png")
                    .fitCenter()
                    .into(binding.wordImg)
            }
        })
    }

    override fun initAfterBinding() {
        binding.backBtn.setOnClickListener {
            navController.popBackStack()
        }

        binding.scrapBtn.setOnClickListener {
            navController.navigate(R.id.action_detailFragment_to_scrapFragment)
        }
    }

    fun suggestBackground(data: String) {
        binding.statusTxt.text = data
        if(data == "BUY"){
            binding.statusTxt.setBackgroundResource(R.drawable.buy_custom)
        }
        else if(data =="NR"){
            binding.statusTxt.setBackgroundResource(R.drawable.nr_custom)
        }
        else{
            binding.statusTxt.setBackgroundResource(R.drawable.hold_custom)
        }
    }
}