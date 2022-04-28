package com.lightone.lighthouse.kotlin.src.home.adapter

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.home.model.Sectors
import com.lightone.lighthouse.kotlin.util.priceFormatter


class SectorHolderPage internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val company_txt :TextView
    private val status_txt :TextView
    private val targetprice_txt: TextView
    private val stockcompany_txt: TextView



    var data: Sectors? = null
    fun onBind(data: Sectors) {
        this.data = data

        company_txt.text = data.companyName+" "+priceFormatter(data.nowPrice)+"원"

        if(data.status == "BUY"){
            status_txt.text = data.status
            status_txt.setBackgroundResource(R.drawable.buy_custom)
        }
        else if(data.status =="NR"){
            status_txt.text = data.status
            status_txt.setBackgroundResource(R.drawable.nr_custom)
        }
        else{
            status_txt.text = data.status
            status_txt.setBackgroundResource(R.drawable.hold_custom)
        }

        targetprice_txt.text = priceFormatter(data.taegetPrice) +"원"
        stockcompany_txt.text = data.stockCompany
    }

    init {
        company_txt = itemView.findViewById(R.id.company_txt)
        status_txt = itemView.findViewById(R.id.status_txt)
        targetprice_txt = itemView.findViewById(R.id.targetprice_txt)
        stockcompany_txt = itemView.findViewById(R.id.stockcompany_txt)
    }
}
