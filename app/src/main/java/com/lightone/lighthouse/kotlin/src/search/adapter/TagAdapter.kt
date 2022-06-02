package com.lightone.lighthouse.kotlin.src.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.Database.model.UserSearch
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.search.model.Recents
import com.lightone.lighthouse.kotlin.src.search.model.Search
import com.lightone.lighthouse.kotlin.src.search.model.Tags
import com.lightone.lighthouse.kotlin.src.suggest.model.Suggests

class TagAdapter() :
    RecyclerView.Adapter<TagHolderPage>(){
    var datas = ArrayList<Tags>()

    private val itemList = ArrayList<Tags>()

    interface OnItemClickEventListener {
        fun onItemClick(a_view: View?, a_position: Int)
    }

    private var nItemClickListener: OnItemClickEventListener? = null

    fun nextItemClickListener(a_listener: OnItemClickEventListener) {
        nItemClickListener = a_listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.tags_item, parent, false)
        return TagHolderPage(view, nItemClickListener!!)
    }

    override fun onBindViewHolder(holder: TagHolderPage, position: Int) {
        if (holder is TagHolderPage) {
            val viewHolder: TagHolderPage = holder as TagHolderPage
            viewHolder.onBind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: Tags) {
        itemList.add(item)
    }

    fun getItem(position: Int): Tags {
        return itemList[position]
    }

    fun deleteItem(position: Int) {
        itemList.removeAt(position)
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}