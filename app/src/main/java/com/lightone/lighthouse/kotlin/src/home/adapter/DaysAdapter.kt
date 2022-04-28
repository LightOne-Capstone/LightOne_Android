package com.lightone.lighthouse.kotlin.src.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.home.model.Days

class DaysAdapter() :
    RecyclerView.Adapter<DaysHolderPage>(){
    var datas = ArrayList<Days>()

    private val itemList = ArrayList<Days>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.days_item, parent, false)
        return DaysHolderPage(view)
    }

    override fun onBindViewHolder(holder: DaysHolderPage, position: Int) {
        if (holder is DaysHolderPage) {
            val viewHolder: DaysHolderPage = holder as DaysHolderPage
            viewHolder.onBind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: Days) {
        itemList.add(item)
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}