package com.cuyer.rusthub.presentation.dashboard.calculators.scrap

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.data.remote.dto.items.Scrap
import com.cuyer.rusthub.domain.model.Items
import com.cuyer.rusthub.presentation.dashboard.calculators.crafting.CraftingExpandableListAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_scrap.view.*
import kotlinx.android.synthetic.main.scrap_viewpager_item.view.*

class ScrapImageAdapter(private var images: List<Items>, private val listener: OnImageClickListener?, context: Context?):  RecyclerView.Adapter<ScrapImageAdapter.ViewHolder>() {

    private val mContext = context
    private var isAdapterSet: Boolean = false
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val scrapDetails: RecyclerView

        init {
            val activity  = mContext as AppCompatActivity
            scrapDetails = activity.findViewById(R.id.ScrapAmountsRecyclerView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.scrap_viewpager_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (!isAdapterSet) {
            holder.scrapDetails.layoutManager = LinearLayoutManager(mContext)
            holder.scrapDetails.adapter = ScrapAmountsAdapter(images[holder.adapterPosition].scrappedComponents[0].scrap.toMutableList(), images[position].image, mContext)
            isAdapterSet = true
        }

        val imageView = holder.view.ScrapViewPagerImageView
        holder.view.setOnClickListener {
            listener?.onImageClick(images[position].image, position)
            (holder.scrapDetails.adapter as ScrapAmountsAdapter).updateList(images[position].scrappedComponents[0].scrap.toMutableList())
            (holder.scrapDetails.adapter as ScrapAmountsAdapter).updateImage(images[position].image)
        }
        Picasso.get()
            .load(images[position].image)
            .placeholder(R.drawable.ic_placeholder_image)
            .error(R.drawable.ic_missing_icon)
            .into(imageView)
    }

    fun updateList(filteredList: List<Items>) {
        images = filteredList
        notifyDataSetChanged()
    }

    interface OnImageClickListener {
        fun onImageClick(imageUrl: String, position: Int)
    }

}