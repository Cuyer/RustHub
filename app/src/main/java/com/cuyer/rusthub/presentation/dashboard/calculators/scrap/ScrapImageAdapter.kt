package com.cuyer.rusthub.presentation.dashboard.calculators.scrap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.domain.model.Items
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.scrap_viewpager_item.view.*

class ScrapImageAdapter(private val images: List<Items>, private val listener: OnImageClickListener?):  RecyclerView.Adapter<ScrapImageAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scrap_viewpager_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageView = holder.view.ScrapViewPagerImageView
        holder.view.setOnClickListener {
            listener?.onImageClick(images[position].image, position)
        }
        Picasso.get()
            .load(images[position].image)
            .placeholder(R.drawable.ic_placeholder_image)
            .error(R.drawable.ic_missing_icon)
            .into(imageView)
    }

    interface OnImageClickListener {
        fun onImageClick(imageUrl: String, position: Int)
    }

}