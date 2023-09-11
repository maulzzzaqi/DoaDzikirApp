package com.mawl.doadzikirapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mawl.doadzikirapp.R
import com.mawl.doadzikirapp.model.DoaDzikirItem

// Adapter is a subclass from RecyclerView which take responsibility for providing views that represent items in a data set
class DoaDzikirAdapter : RecyclerView.Adapter<DoaDzikirAdapter.DzikirViewHolder>(){
    private val listData = ArrayList<DoaDzikirItem>()

    fun setData(list: List<DoaDzikirItem>) {
        listData.clear()
        listData.addAll(list)
    }

    // ViewHolder takes responsibility to initialize item from layout
    // in this class we will describes our item view from layout
    inner class DzikirViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemTitle = view.findViewById<TextView>(R.id.item_title)
        val itemArabic = view.findViewById<TextView>(R.id.item_arabic)
        val itemTranslate = view.findViewById<TextView>(R.id.item_translate)
    }

    // onCreateViewHolder provides layout to be used by ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DzikirViewHolder {
        return DzikirViewHolder(
            // LayoutInflater is a class to inflate a layout/view
            LayoutInflater.from(parent.context).inflate(R.layout.item_doa, parent, false))
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: DzikirViewHolder, position: Int) {
        val data = listData[position]
        holder.apply {
            itemTitle.text = listData[position].title
            itemArabic.text = listData[position].arabic
            itemTranslate.text = listData[position].translate
        }
    }

}