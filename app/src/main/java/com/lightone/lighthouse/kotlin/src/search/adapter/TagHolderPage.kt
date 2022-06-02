package com.lightone.lighthouse.kotlin.src.search.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.Database.model.UserSearch
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.search.model.Search
import com.lightone.lighthouse.kotlin.src.search.model.Tags
import com.lightone.lighthouse.kotlin.src.suggest.model.Suggests


class TagHolderPage internal constructor(
    itemView: View,
    private val n_itemClickListener: TagAdapter.OnItemClickEventListener
) : RecyclerView.ViewHolder(itemView) {

    private val contents_txt :TextView
    private val delete_btn :ImageView
    private val next_btn :ImageView
    private val num_txt :TextView

    var data: Tags? = null
    fun onBind(data: Tags) {
        this.data = data

        num_txt.text = data.Idx.toString()
        contents_txt.text = data.category
        delete_btn.visibility = View.GONE
        next_btn.visibility = View.VISIBLE

        next_btn.setOnClickListener(View.OnClickListener { a_view ->
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                n_itemClickListener.onItemClick(a_view, position)
            }
        })
    }

    init {
        num_txt = itemView.findViewById(R.id.num_txt)
        contents_txt = itemView.findViewById(R.id.contents_txt)
        delete_btn = itemView.findViewById(R.id.delete_btn)
        next_btn = itemView.findViewById(R.id.next_btn)
    }
}
