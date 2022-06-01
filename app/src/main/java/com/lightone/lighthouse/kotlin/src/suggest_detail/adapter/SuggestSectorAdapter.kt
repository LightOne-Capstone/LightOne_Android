package com.lightone.lighthouse.kotlin.src.suggest_detail.adapter

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
import com.lightone.lighthouse.kotlin.src.suggest_detail.model.SuggestDetailResponse

class SuggestSectorAdapter() :
    RecyclerView.Adapter<SuggestSectorHolderPage>(){
    var datas = ArrayList<SuggestDetailResponse>()

    private val itemList = ArrayList<SuggestDetailResponse>()


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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestSectorHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.sectors_item, parent, false)

        return SuggestSectorHolderPage(view, mItemClickListener, scrapItemClickListener)
    }

    override fun onBindViewHolder(holder: SuggestSectorHolderPage, position: Int) {
        if (holder is SuggestSectorHolderPage) {
            val viewHolder: SuggestSectorHolderPage = holder as SuggestSectorHolderPage
            viewHolder.onBind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: SuggestDetailResponse) {
        itemList.add(item)
    }

    fun getItem(position: Int): SuggestDetailResponse {
        return itemList[position]
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}