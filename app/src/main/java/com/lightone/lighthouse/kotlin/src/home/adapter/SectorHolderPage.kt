package com.lightone.lighthouse.kotlin.src.home.adapter

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.MyApplication
import com.lightone.lighthouse.kotlin.src.home.model.Reports
import com.lightone.lighthouse.kotlin.src.home.model.Sectors
import com.lightone.lighthouse.kotlin.util.priceFormatter


class SectorHolderPage internal constructor(
    itemView: View,
    var d_itemClickListener: SectorAdapter.OnItemClickEventListener?,
    var scrapItemClickListener: SectorAdapter.OnItemClickEventListener?
) : RecyclerView.ViewHolder(itemView) {
    private val company_txt :TextView
    private val status_txt :TextView
    private val targetprice_txt: TextView
    private val stockcompany_txt: TextView
    private val sector_item: ConstraintLayout
    private val add_btn: ConstraintLayout

    var data: Reports? = null
    fun onBind(data: Reports) {
        this.data = data

        company_txt.text = data.company_name+" "+priceFormatter(data.currentPrice)+"원"

        when (data.suggestion) {
            "BUY" -> {
                status_txt.text = data.suggestion
                status_txt.setBackgroundResource(R.drawable.buy_custom)
            }
            "NR" -> {
                status_txt.text = data.suggestion
                status_txt.setBackgroundResource(R.drawable.nr_custom)
            }
            else -> {
                status_txt.text = data.suggestion
                status_txt.setBackgroundResource(R.drawable.hold_custom)
            }
        }

        if(data.targetPrice == 0){
            targetprice_txt.text = "목표가 미정"
        }
        else{
            targetprice_txt.text = "목표가 "+priceFormatter(data.targetPrice) +"원"
        }

        stockcompany_txt.text = data.writerCompany

        sector_item.setOnClickListener { a_view ->
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                d_itemClickListener!!.onItemClick(a_view, position)
//                val editor = MyApplication.editor
//                editor.putString("Idx", data.company_id)
//                editor.commit()
            }
        }

        add_btn.setOnClickListener { a_view ->
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                scrapItemClickListener!!.onItemClick(a_view, position)
//                val editor = MyApplication.editor
//                editor.putString("scrapIdx", data.company_id)
//                editor.commit()
            }
        }
    }

    init {
        company_txt = itemView.findViewById(R.id.company_txt)
        status_txt = itemView.findViewById(R.id.status_txt)
        targetprice_txt = itemView.findViewById(R.id.targetprice_txt)
        stockcompany_txt = itemView.findViewById(R.id.stockcompany_txt)
        sector_item = itemView.findViewById(R.id.sector_item)
        add_btn = itemView.findViewById(R.id.add_btn)
    }
}
