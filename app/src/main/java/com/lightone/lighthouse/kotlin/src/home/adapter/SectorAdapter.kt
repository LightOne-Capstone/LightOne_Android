package com.lightone.lighthouse.kotlin.src.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.home.model.Reports

class SectorAdapter : PagingDataAdapter<Reports, SectorHolderPage>(CharacterComparator){
//    RecyclerView.Adapter<SectorHolderPage>(){
    var datas = ArrayList<Reports>()

    private val itemList = ArrayList<Reports>()

    interface OnItemClickEventListener {
        fun onItemClick(a_view: View?, a_position: Int)
    }

    private var mItemClickListener: OnItemClickEventListener? = null

    fun moveItemClickListener(a_listener: OnItemClickEventListener) {
        mItemClickListener = a_listener
    }

    private var scrapItemClickListener: OnItemClickEventListener? = null

    fun scrapItemClickListener(a_listener: OnItemClickEventListener) {
        scrapItemClickListener = a_listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectorHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.sectors_item, parent, false)

        return SectorHolderPage(view, mItemClickListener, scrapItemClickListener)
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

    fun addItem(item: Reports) {
        itemList.add(item)
    }

    fun getReports(position: Int): Reports {
        return itemList[position]
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }

    object CharacterComparator : DiffUtil.ItemCallback<Reports>() {
        override fun areItemsTheSame(oldItem: Reports, newItem: Reports) =
            oldItem.company_id == newItem.company_id

        override fun areContentsTheSame(oldItem: Reports, newItem: Reports) =
            oldItem == newItem
    }
}