package com.lightone.lighthouse.kotlin.src.searchdetail.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.home.adapter.SectorHolderPage
import com.lightone.lighthouse.kotlin.src.home.model.Sectors
import com.lightone.lighthouse.kotlin.src.scrap.adapter.ScrapHolderPage
import com.lightone.lighthouse.kotlin.src.suggest_detail.adapter.SuggestSectorHolderPage

class SearchDetailAdapter() :
    RecyclerView.Adapter<SearchDetailHolderPage>(){
    var datas = ArrayList<Sectors>()

    private val itemList = ArrayList<Sectors>()

    interface OnItemClickEventListener {
        fun onItemClick(a_view: View?, a_position: Int)
    }

    private var mItemClickListener: OnItemClickEventListener? = null

    fun moveItemClickListener(a_listener: OnItemClickEventListener) {
        mItemClickListener = a_listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchDetailHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.sectors_item, parent, false)

        return SearchDetailHolderPage(view, mItemClickListener)
    }

    override fun onBindViewHolder(holder: SearchDetailHolderPage, position: Int) {
        if (holder is SearchDetailHolderPage) {
            val viewHolder: SearchDetailHolderPage = holder as SearchDetailHolderPage
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
        return itemList[position]
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}