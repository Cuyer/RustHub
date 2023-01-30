package com.cuyer.rusthub.presentation.dashboard.calculators.scrap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.scrap_amount_recyclerview_item.view.*
import kotlinx.android.synthetic.main.scrap_output_recyclerview_item.view.*

class ScrapOutputAdapter(private val scrapData: List<ScrapItems>): RecyclerView.Adapter<ScrapOutputAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val mainImage: ImageView
        init {
            mainImage = view.ScrapOutputParentIcon
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scrap_output_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return scrapData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get()
            .load(scrapData[position].mainIcon)
            .error(R.drawable.ic_missing_icon)
            .placeholder(R.drawable.ic_placeholder_image)
            .into(holder.mainImage)
    }
}