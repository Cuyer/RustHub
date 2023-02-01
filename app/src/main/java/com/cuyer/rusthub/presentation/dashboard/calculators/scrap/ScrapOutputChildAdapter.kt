package com.cuyer.rusthub.presentation.dashboard.calculators.scrap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.data.remote.dto.items.Scrap
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.scrap_output_child_recyclerview_item.view.*
import kotlinx.android.synthetic.main.scrap_output_recyclerview_item.view.*

class ScrapOutputChildAdapter(private val scrapData: List<Scrap>): RecyclerView.Adapter<ScrapOutputChildAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView
        val amount: TextView
        val name: TextView
        init {
            image = view.ScrapOutputChildIcon
            amount = view.ScrapOutputChildAmount
            name = view.ScrapOutputChildName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scrap_output_child_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return scrapData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            amount.text = scrapData[position].amount
            name.text = scrapData[position].name
            Picasso.get()
                .load(scrapData[position].src)
                .error(R.drawable.ic_missing_icon)
                .placeholder(R.drawable.ic_placeholder_image)
                .into(image)
        }
    }
}