package com.lightone.lighthouse.kotlin.src.search.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.search.model.Recents

class RecentHolderPage internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val contents_txt : TextView
    private val delete_btn : ImageView

    var data: Recents? = null
    fun onBind(data: Recents) {
        this.data = data
        contents_txt.text = data.contents
    }

    init {
        contents_txt = itemView.findViewById(R.id.contents_txt)
        delete_btn = itemView.findViewById(R.id.delete_btn)
    }
}
