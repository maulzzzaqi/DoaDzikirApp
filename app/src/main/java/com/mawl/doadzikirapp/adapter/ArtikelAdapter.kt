package com.mawl.doadzikirapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mawl.doadzikirapp.databinding.ItemArtikelBinding
import com.mawl.doadzikirapp.model.ArtikelItem
import com.mawl.doadzikirapp.presentation.DetailArtikelActivity
import com.mawl.doadzikirapp.utils.OnItemCallback

class ArtikelAdapter : RecyclerView.Adapter<ArtikelAdapter.ArtikelViewHolder>() {
    // variable used to store dataset
    private var listArtikel = ArrayList<ArtikelItem>()
    // variable to give event click callback in Activity through setOnItemClickCallback
    private var onItemCallback: OnItemCallback? = null

    fun setData (list: List<ArtikelItem>){
        listArtikel.clear()
        listArtikel.addAll(list)
    }

    // function to set event click in item using interface
    fun setOnItemCLickCallback(onItemCallback: OnItemCallback) {
        this.onItemCallback = onItemCallback
    }

    inner class ArtikelViewHolder(val view: ItemArtikelBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArtikelViewHolder (
        ItemArtikelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = listArtikel.size

    override fun onBindViewHolder(holder: ArtikelViewHolder, position: Int) {
        val data = listArtikel[position]
        holder.view.itemArticle.setImageResource(data.image!!)
        // this gives click event for each item in ViewPager
        holder.itemView.setOnClickListener {
            // set event click in activity through interface
            onItemCallback?.onItemClicked(data)
            // set event click directly through adapter

            /* it.context.apply {
                val intent = Intent(this, DetailArtikelActivity::class.java)
                intent.putExtra(DetailArtikelActivity.EXTRA_DATA_TITLE, data.title)
                intent.putExtra(DetailArtikelActivity.EXTRA_DATA_CONTENT, data.content)
                intent.putExtra(DetailArtikelActivity.EXTRA_DATA_IMAGE, data.image)
            }*/
        }
    }


}