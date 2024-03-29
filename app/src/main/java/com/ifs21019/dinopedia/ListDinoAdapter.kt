package com.ifs21019.dinopedia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21019.dinopedia.databinding.ItemRowDinoBinding

class ListDinoAdapter(private val listDinoFamily: ArrayList<Dinosaurus>) :
    RecyclerView.Adapter<ListDinoAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback:  OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowDinoBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dinosaurus = listDinoFamily[position]
        holder.binding.tvItemDino.text = dinosaurus.namefam
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listDinoFamily[position])
        }
    }

    override fun getItemCount(): Int = listDinoFamily.size

    class ListViewHolder(var binding: ItemRowDinoBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Dinosaurus)
    }
}
