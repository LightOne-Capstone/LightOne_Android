package com.lightone.lighthouse.kotlin.src.scrap.adapter

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.Database.model.UserScrap
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.home.model.Sectors
import com.lightone.lighthouse.kotlin.util.priceFormatter


class ScrapHolderPage internal constructor(
    itemView: View,
    var m_itemClickListener: ScrapeAdapter.OnItemClickEventListener?,
    var d_itemClickListener: ScrapeAdapter.OnItemClickEventListener?
    ) : RecyclerView.ViewHolder(itemView) {
    private val company_txt :TextView
    private val status_txt :TextView
    private val targetprice_txt: TextView
    private val stockcompany_txt: TextView
    private val sector_main: ConstraintLayout
    private val delete_btn: ConstraintLayout


    var data: UserScrap? = null
    fun onBind(data: UserScrap) {
        this.data = data

        company_txt.text = data.company_name+" "+priceFormatter(data.currentPrice)+"원"

        status_txt.text = data.suggestion
        if(data.suggestion == "BUY"){
            status_txt.setBackgroundResource(R.drawable.buy_custom)
        }
        else if(data.suggestion =="NR"){
            status_txt.setBackgroundResource(R.drawable.nr_custom)
        }
        else{
            status_txt.setBackgroundResource(R.drawable.hold_custom)
        }

        if(data.targetPrice == 0){
            targetprice_txt.text = "목표가 미정"
        }
        else{
            targetprice_txt.text = "목표가 "+priceFormatter(data.targetPrice) +"원"
        }
        stockcompany_txt.text = data.writerCompany

        sector_main.setOnClickListener(View.OnClickListener { a_view ->
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                m_itemClickListener!!.onItemClick(a_view, position)
            }
        })

        delete_btn.setOnClickListener(View.OnClickListener { a_view ->
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                d_itemClickListener!!.onItemClick(a_view, position)
            }
        })
    }

    init {
        company_txt = itemView.findViewById(R.id.company_txt)
        status_txt = itemView.findViewById(R.id.status_txt)
        targetprice_txt = itemView.findViewById(R.id.targetprice_txt)
        stockcompany_txt = itemView.findViewById(R.id.stockcompany_txt)
        sector_main = itemView.findViewById(R.id.sector_main)
        delete_btn = itemView.findViewById(R.id.delete_btn)
    }
}
