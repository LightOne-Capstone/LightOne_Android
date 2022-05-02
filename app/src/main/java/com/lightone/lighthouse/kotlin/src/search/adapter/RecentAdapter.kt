package com.lightone.lighthouse.kotlin.src.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.search.model.Recents
import com.lightone.lighthouse.kotlin.src.search.adapter.RecentHolderPage

class RecentAdapter() :
    RecyclerView.Adapter<RecentHolderPage>(){
    var datas = ArrayList<Recents>()

    private val itemList = ArrayList<Recents>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.recents_item, parent, false)
        return RecentHolderPage(view)
    }

    override fun onBindViewHolder(holder: RecentHolderPage, position: Int) {
        if (holder is RecentHolderPage) {
            val viewHolder: RecentHolderPage = holder as RecentHolderPage
            viewHolder.onBind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: Recents) {
        itemList.add(item)
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}