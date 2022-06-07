package com.lightone.lighthouse.kotlin.src.pdf

import android.graphics.Bitmap
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.lightone.lighthouse.kotlin.config.BaseFragment
import com.lightone.lighthouse.kotlin.databinding.FragmentPdfBinding
import com.lightone.lighthouse.kotlin.util.loadpdffromUrl
import com.lightone.lighthouse.kotlin.viewmodel.PdfViewModel
import kotlinx.android.synthetic.main.fragment_pdf.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection


class PdfFragment : BaseFragment<FragmentPdfBinding, PdfViewModel>(com.lightone.lighthouse.kotlin.R.layout.fragment_pdf) {

    override val layoutResourceId: Int
        get() = com.lightone.lighthouse.kotlin.R.layout.fragment_pdf // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: PdfViewModel by viewModel()

    lateinit var navController: NavController

    lateinit var pdfurl: String

    override fun initStartView() {
        navController = Navigation.findNavController(requireView())
        val args: PdfFragmentArgs by navArgs()
        pdfurl = args.url
        binding.companyTxt.text = args.name
        Log.d("pdf_response", pdfurl)
        initWebView()
//        val url = Uri.parse("https://docs.google.com/gview?embedded=true&url=" + URLEncoder.encode(pdfurl,"UTF-8"))
//        val url = "https://drive.google.com/viewerng/viewer?embedded=true&url=$pdfurl"
//        pdfView.fromAsset(url).load()
//        loadpdffromUrl(binding.pdfView, binding.progressBar)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {
        binding.backBtn.setOnClickListener {
            navController.popBackStack()
        }
    }

    private fun initWebView(){
        binding.pdfView.webViewClient = object : WebViewClient() {
            // 웹페이지 호출시 오류 발생에 대한 처리
            override fun onReceivedError(
                view: WebView, errorcode: Int,
                description: String, fallingUrl: String,
            ) {
                Toast.makeText(requireContext(),
                    "오류 : $description", Toast.LENGTH_SHORT).show()
            }

            // 페이지 로딩 시작시 호출
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                binding.progressBar.visibility = View.VISIBLE
            }

            //페이지 로딩 종료시 호출
            override fun onPageFinished(view: WebView, Url: String) {
                binding.progressBar.visibility = View.GONE
            }
        }
        binding.pdfView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        binding.pdfView.settings.javaScriptEnabled = true
//        val url = "https://docs.google.com/gview?embedded=true&url=" + URLEncoder.encode(pdfurl,"ISO-8859-1")
//        val url = "https://docs.google.com/gview?embedded=true&url=" + URLEncoder.encode(pdfurl,"UTF-8")
//        val url = "http://docs.google.com/gview?embedded=true&url=$pdfurl"
        val url = "https://drive.google.com/viewerng/viewer?embedded=true&url=" + pdfurl
//        binding.pdfView.clearCache(true)
        binding.pdfView.loadUrl(url)
    }
}