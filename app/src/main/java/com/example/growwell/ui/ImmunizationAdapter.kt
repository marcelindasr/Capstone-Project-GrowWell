// ImmunizationAdapter.kt
package com.example.growwell.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.growwell.R
import com.example.growwell.ui.detail.DetailActivity

data class ImmunizationItem(val image: Int, val title: String, val description: String)

class ImmunizationAdapter(private val immunizationList: List<ImmunizationItem>) :
    RecyclerView.Adapter<ImmunizationAdapter.ImmunizationViewHolder>() {

    class ImmunizationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleView: TextView = itemView.findViewById(R.id.titleView)
        val descriptionView: TextView = itemView.findViewById(R.id.descriptionView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImmunizationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_immunization, parent, false)
        return ImmunizationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImmunizationViewHolder, position: Int) {
        val currentItem = immunizationList[position]
        holder.imageView.setImageResource(currentItem.image)
        holder.titleView.text = currentItem.title
        holder.descriptionView.text = currentItem.description

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("title", currentItem.title)
                putExtra("description", currentItem.description)
                putExtra("image", currentItem.image)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = immunizationList.size
}
