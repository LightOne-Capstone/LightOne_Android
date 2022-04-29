package com.lightone.lighthouse.kotlin.src.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.home.model.Sectors

class SectorAdapter() :
    RecyclerView.Adapter<SectorHolderPage>(){
    var datas = ArrayList<Sectors>()

    private val itemList = ArrayList<Sectors>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectorHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.sectors_item, parent, false)
        return SectorHolderPage(view)
    }

    override fun onBindViewHolder(holder: SectorHolderPage, position: Int) {
        if (holder is SectorHolderPage) {
            val viewHolder: SectorHolderPage = holder as SectorHolderPage
            viewHolder.onBind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: Sectors) {
        itemList.add(item)
    }

    fun getItem(position: Int): Sectors {
        return itemList.get(position)
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}