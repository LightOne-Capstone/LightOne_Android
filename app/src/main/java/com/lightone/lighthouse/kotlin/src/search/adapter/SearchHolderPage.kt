package com.lightone.lighthouse.kotlin.src.search.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.Database.model.UserSearch
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.search.model.Search


class SearchHolderPage internal constructor(
    itemView: View,
    private val a_itemClickListener: SearchAdapter.OnItemClickEventListener,
) : RecyclerView.ViewHolder(itemView) {

    private val contents_txt :TextView
    private val next_btn :ImageView

    var data: Search? = null
    fun onBind(data: Search) {
        this.data = data

        contents_txt.text = data.name
        next_btn.setOnClickListener(View.OnClickListener { a_view ->
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                a_itemClickListener.onItemClick(a_view, position)
            }
        })
    }

    init {
        contents_txt = itemView.findViewById(R.id.contents_txt)
        next_btn = itemView.findViewById(R.id.delete_btn)
    }
}
