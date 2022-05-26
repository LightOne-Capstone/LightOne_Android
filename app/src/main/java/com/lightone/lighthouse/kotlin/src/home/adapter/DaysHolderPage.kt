package com.lightone.lighthouse.kotlin.src.home.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.MyApplication
import com.lightone.lighthouse.kotlin.src.home.model.Days
import com.lightone.lighthouse.kotlin.util.ScrapTouchCallback


class DaysHolderPage internal constructor(
    itemView: View,
    val mItemClickListener: DaysAdapter.OnItemClickEventListener?,
    val scrapItemClickListener: DaysAdapter.OnItemClickEventListener?
) : RecyclerView.ViewHolder(itemView) {
    private val days_txt : TextView
    private val sector_recycler: RecyclerView

    var data: Days? = null
    fun onBind(data: Days) {
        this.data = data
        days_txt.text = data.days

        var sectorAdapter = SectorAdapter()
        data.sectors.forEach { item ->
            if(item.date == data.days){
                sectorAdapter.addItem(item)
            }
        }

        sectorAdapter.moveItemClickListener(object : SectorAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val editor = MyApplication.editor.putString("Idx", sectorAdapter.getItem(a_position).company_id)
                editor.commit()
                mItemClickListener!!.onItemClick(a_view, a_position)
            }
        })

        val swipeHelperCallback = ScrapTouchCallback().apply {
            setClamp(200f)
        }
        val itemTouchHelper = ItemTouchHelper(swipeHelperCallback)
        itemTouchHelper.attachToRecyclerView(sector_recycler)

        sector_recycler.apply {
            sector_recycler.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL,
                false)
            adapter = sectorAdapter
            setOnTouchListener { _, _ ->
                swipeHelperCallback.removePreviousClamp(this)
                false
            }
        }

        sectorAdapter.scrapItemClickListener(object : SectorAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val editor = MyApplication.editor
                editor.putString("scrapIdx", sectorAdapter.getItem(a_position).company_id)
                editor.commit()
                scrapItemClickListener!!.onItemClick(a_view, a_position)
            }
        })
    }

    init {
        days_txt = itemView.findViewById(R.id.days_txt)
        sector_recycler = itemView.findViewById(R.id.sector_recycler)
    }
}
