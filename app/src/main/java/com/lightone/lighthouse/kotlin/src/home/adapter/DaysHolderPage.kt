package com.lightone.lighthouse.kotlin.src.home.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.home.model.Days


class DaysHolderPage internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val days_txt : TextView
    private val sector_recycler: RecyclerView

    var data: Days? = null
    fun onBind(data: Days) {
        this.data = data
        days_txt.text = data.days

        var sectorAdapter = SectorAdapter()
        data.sectors.forEach { item ->
            sectorAdapter.addItem(item)
        }
        sector_recycler.adapter = sectorAdapter
        sector_recycler.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL,
            false)
    }

    init {
        days_txt = itemView.findViewById(R.id.days_txt)
        sector_recycler = itemView.findViewById(R.id.sector_recycler)
    }
}
