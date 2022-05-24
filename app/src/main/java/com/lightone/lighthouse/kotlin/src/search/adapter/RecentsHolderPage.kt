package com.lightone.lighthouse.kotlin.src.search.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.Database.model.UserSearch
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.search.model.Search


class RecentsHolderPage internal constructor(
    itemView: View,
    private val a_itemClickListener: RecentsAdapter.OnItemClickEventListener,
    private val n_itemClickListener: RecentsAdapter.OnItemClickEventListener
) : RecyclerView.ViewHolder(itemView) {

    private val contents_txt :TextView
    private val delete_btn :ImageView
    private val next_btn :ImageView

    var data: Search? = null
    fun onBind(data: Search) {
        this.data = data

        contents_txt.text = data.name
        // 검색목록인지
        if(data.check){
            delete_btn.visibility = View.GONE
            next_btn.visibility = View.VISIBLE
        }
        // 최근검색에 대한 내용인지
        else{
            delete_btn.visibility = View.VISIBLE
            next_btn.visibility = View.GONE
        }

        delete_btn.setOnClickListener(View.OnClickListener { a_view ->
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                a_itemClickListener.onItemClick(a_view, position)
            }
        })

        next_btn.setOnClickListener(View.OnClickListener { a_view ->
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                n_itemClickListener.onItemClick(a_view, position)
            }
        })
    }

    init {
        contents_txt = itemView.findViewById(R.id.contents_txt)
        delete_btn = itemView.findViewById(R.id.delete_btn)
        next_btn = itemView.findViewById(R.id.next_btn)
    }
}
