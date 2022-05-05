package com.lightone.lighthouse.kotlin.src.suggest.adapter

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
import com.lightone.lighthouse.kotlin.src.suggest.model.Suggests

class SuggestAdapter() :
    RecyclerView.Adapter<SuggestHolderPage>(){
    var datas = ArrayList<Suggests>()

    private val itemList = ArrayList<Suggests>()


    interface OnItemClickEventListener {
        fun onItemClick(a_view: View?, a_position: Int)
    }

    private var mItemClickListener: OnItemClickEventListener? = null

    fun moveItemClickListener(a_listener: OnItemClickEventListener) {
        mItemClickListener = a_listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.suggest_item, parent, false)

        return SuggestHolderPage(view, mItemClickListener)
    }

    override fun onBindViewHolder(holder: SuggestHolderPage, position: Int) {
        if (holder is SuggestHolderPage) {
            val viewHolder: SuggestHolderPage = holder as SuggestHolderPage
            viewHolder.onBind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: Suggests) {
        itemList.add(item)
    }

    fun getItem(position: Int): Suggests {
        return itemList[position]
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}