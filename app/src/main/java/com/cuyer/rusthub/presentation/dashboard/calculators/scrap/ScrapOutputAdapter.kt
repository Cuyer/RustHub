package com.cuyer.rusthub.presentation.dashboard.calculators.scrap

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.cuyer.rusthub.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.scrap_amount_recyclerview_item.view.*
import kotlinx.android.synthetic.main.scrap_output_recyclerview_item.view.*

class ScrapOutputAdapter(private val scrapData: List<ScrapItems>, context: Context): RecyclerView.Adapter<ScrapOutputAdapter.ViewHolder>() {

    private val mContext = context

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val mainImage: ImageView
        val scrapChildOutput: RecyclerView
        val parentAmount: TextView
        init {
            mainImage = view.ScrapOutputParentIcon
            parentAmount = view.ScrapOutputParentAmount
            scrapChildOutput = view.ScrapOutputChildRecyclerview
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
        holder.scrapChildOutput.layoutManager = LinearLayoutManager(mContext)
        holder.scrapChildOutput.adapter = ScrapOutputChildAdapter(ScrapDataHolder.getData()[position].scrap)
        holder.parentAmount.text = scrapData[position].amount
        Picasso.get()
            .load(scrapData[position].mainIcon)
            .error(R.drawable.ic_missing_icon)
            .placeholder(R.drawable.ic_placeholder_image)
            .into(holder.mainImage)
    }
}