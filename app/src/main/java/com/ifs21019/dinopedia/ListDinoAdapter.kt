package com.ifs21019.dinopedia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21019.dinopedia.databinding.ItemRowDinoBinding

class ListDinoAdapter(private val listDinosaurus: ArrayList<Dinosaurus>) :
    RecyclerView.Adapter<ListDinoAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowDinoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dinosaurus = listDinosaurus[position]
        holder.binding.ivItemDino.setImageResource(dinosaurus.image)
        holder.binding.tvItemDino.text = dinosaurus.name
        holder.itemView.setOnClickListener {
            if (this::onItemClickCallback.isInitialized) {
                onItemClickCallback.onItemClicked(dinosaurus)
            }
        }
    }

    override fun getItemCount(): Int = listDinosaurus.size

    inner class ListViewHolder(val binding: ItemRowDinoBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Dinosaurus)
    }
}
