package com.proyekakhir.paging3.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.paging3.R
import com.proyekakhir.paging3.data.network.response.ResultsItem

class MoviesAdapter : PagingDataAdapter<ResultsItem, MoviesAdapter.ViewHolder>(COMPARE_DATA) {

    companion object {
        private val COMPARE_DATA = object : DiffUtil.ItemCallback<ResultsItem>() {
            override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
                return oldItem.id == oldItem.id
            }

            override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
                return oldItem == newItem
            }

        }
    }
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.tvTitle)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("TAG", "onBindViewHolder:" )
        holder.textView.text = getItem(position)?.title
        Log.e("TAG", "onBindViewHolder: ${getItem(position)?.title}" )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false)
        return ViewHolder(view)
    }
}