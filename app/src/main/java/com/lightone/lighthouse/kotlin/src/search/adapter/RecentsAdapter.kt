package com.lightone.lighthouse.kotlin.src.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.Database.model.Search
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.search.model.Recents

class RecentsAdapter() :
    RecyclerView.Adapter<RecentsHolderPage>(){
    var datas = ArrayList<Search>()

    private val itemList = ArrayList<Search>()

    interface OnItemClickEventListener {
        fun onItemClick(a_view: View?, a_position: Int)
    }

    private var mItemClickListener: OnItemClickEventListener? = null

    fun delteItemClickListener(a_listener: OnItemClickEventListener) {
        mItemClickListener = a_listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentsHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.recents_item, parent, false)
        return RecentsHolderPage(view, mItemClickListener!!)
    }

    override fun onBindViewHolder(holder: RecentsHolderPage, position: Int) {
        if (holder is RecentsHolderPage) {
            val viewHolder: RecentsHolderPage = holder as RecentsHolderPage
            viewHolder.onBind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: Search) {
        itemList.add(item)
    }

    fun getItem(position: Int): Search {
        return itemList.get(position)
    }

    fun deleteItem(position: Int) {
        itemList.removeAt(position)
        this.notifyDataSetChanged()
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}