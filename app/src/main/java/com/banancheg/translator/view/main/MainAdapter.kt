package com.banancheg.translator.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.banancheg.model.data.userdata.DataModel
import com.banancheg.translator.R
import com.banancheg.utils.convertMeaningsToString

class MainAdapter(private var onListItemClick: (DataModel) -> Unit) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    private var data: List<DataModel> = arrayListOf()

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_main_recycleview_item, parent, false) as View)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClick(listItemData)
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.header_textview_recycle_item).text = data.text
                itemView.findViewById<TextView>(R.id.description_textview_recycle_item).text =  convertMeaningsToString(data.meanings)
                itemView.setOnClickListener { openInNewWindow(data) }

            }
        }
    }
}