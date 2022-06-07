package com.lightone.lighthouse.kotlin.src.home.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.config.MyApplication
import com.lightone.lighthouse.kotlin.src.home.model.Days
import com.lightone.lighthouse.kotlin.util.AddScrapTouchCallback
import com.lightone.lighthouse.kotlin.util.DeleteScrapTouchCallback
import io.reactivex.Single
import java.util.concurrent.TimeUnit


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
        var count = 0
        data.sectors.forEach { item ->
            if(item.date == data.days){
                sectorAdapter.addItem(item)
                count+1
            }
        }

        sectorAdapter.moveItemClickListener(object : SectorAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val editor = MyApplication.editor.putString("Idx", sectorAdapter.getReports(a_position).company_id)
                editor.putString("date", sectorAdapter.getReports(a_position).date)
                editor.commit()
                mItemClickListener!!.onItemClick(a_view, a_position)
            }
        })

        val swipeHelperCallback = AddScrapTouchCallback().apply {
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
                swipeHelperCallback.removeNowClamp(this)
                false
            }
            setHasFixedSize(true)
        }

        sectorAdapter.scrapItemClickListener(object : SectorAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val editor = MyApplication.editor
                editor.putString("scrapIdx", sectorAdapter.getReports(a_position).company_id)
                editor.putString("Idx", sectorAdapter.getReports(a_position).company_id)
                editor.putString("date", sectorAdapter.getReports(a_position).date)
                editor.commit()
                swipeHelperCallback.removeNowClamp(sector_recycler)
                scrapItemClickListener!!.onItemClick(a_view, a_position)
            }
        })
    }

    init {
        days_txt = itemView.findViewById(R.id.days_txt)
        sector_recycler = itemView.findViewById(R.id.sector_recycler)
    }
}
