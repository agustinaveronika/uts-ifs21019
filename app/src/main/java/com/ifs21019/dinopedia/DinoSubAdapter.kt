package com.ifs21019.dinopedia

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21019.dinopedia.databinding.ItemRowDinoBinding


class DinoSubAdapter(private val listDino: ArrayList<Dino>) :
    RecyclerView.Adapter<DinoSubAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback:  OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowDinoBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    // Ubah method onBindViewHolder di dalam kelas DinoSubAdapter
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dino = listDino[position]
        holder.binding.ivItemDino.setImageResource(dino.image)
        holder.binding.tvItemDino.text = dino.name
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailDinosub::class.java)
            intent.putExtra(DetailDinosub.EXTRA_DINO, dino)
            holder.itemView.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int = listDino.size
    class ListViewHolder(var binding: ItemRowDinoBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Dino)
    }
}