package com.lightone.lighthouse.kotlin.src.suggest.adapter

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.home.model.Sectors
import com.lightone.lighthouse.kotlin.src.suggest.model.Suggests

class SuggestHolderPage internal constructor(
    itemView: View,
    var d_itemClickListener: SuggestAdapter.OnItemClickEventListener?
    ) : RecyclerView.ViewHolder(itemView) {
    private val suggest_img :ImageView
    private val suggest_name :TextView
    private val suggest_count: TextView
    private val suggest_main: ConstraintLayout


    var data: Suggests? = null
    fun onBind(data: Suggests) {
        this.data = data

        suggest_img.setImageResource(data.img)
        suggest_name.text = data.name
        if(data.count<10){
            suggest_count.text = "+ 0"+data.count.toString()+"개"
        }
        else{
            suggest_count.text = "+ "+data.count.toString()+"개"
        }


        suggest_main.setOnClickListener(View.OnClickListener { a_view ->
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                d_itemClickListener!!.onItemClick(a_view, position)
            }
        })
    }

    init {
        suggest_img = itemView.findViewById(R.id.suggest_img)
        suggest_name = itemView.findViewById(R.id.suggest_name)
        suggest_count = itemView.findViewById(R.id.suggest_count)
        suggest_main = itemView.findViewById(R.id.suggest_main)
    }
}
