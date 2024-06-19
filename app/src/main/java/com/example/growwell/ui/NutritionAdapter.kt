// NutritionAdapter.kt
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

data class NutritionItem(val image: Int, val title: String, val description: String)

class NutritionAdapter(private val nutritionList: List<NutritionItem>) :
    RecyclerView.Adapter<NutritionAdapter.NutritionViewHolder>() {

    class NutritionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleView: TextView = itemView.findViewById(R.id.titleView)
        val descriptionView: TextView = itemView.findViewById(R.id.descriptionView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nutrition, parent, false)
        return NutritionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NutritionViewHolder, position: Int) {
        val currentItem = nutritionList[position]
        holder.imageView.setImageResource(currentItem.image)
        holder.titleView.text = currentItem.title
        holder.descriptionView.text = currentItem.description

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("title", currentItem.title)
                putExtra("description", currentItem.description)  // If there's no description for nutrition items
                putExtra("image", currentItem.image)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = nutritionList.size
}
