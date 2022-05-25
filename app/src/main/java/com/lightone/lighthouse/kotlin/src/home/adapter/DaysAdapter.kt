package com.lightone.lighthouse.kotlin.src.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.home.model.Days
import com.lightone.lighthouse.kotlin.src.home.model.Sectors

class DaysAdapter() :
    RecyclerView.Adapter<DaysHolderPage>(){
    var datas = ArrayList<Days>()

    private val itemList = ArrayList<Days>()

    interface OnItemClickEventListener {
        fun onItemClick(a_view: View?, a_position: Int)
    }

    private var mItemClickListener: OnItemClickEventListener? = null

    fun moveItemClickListener(a_listener: OnItemClickEventListener) {
        mItemClickListener = a_listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.days_item, parent, false)
        return DaysHolderPage(view, mItemClickListener)
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