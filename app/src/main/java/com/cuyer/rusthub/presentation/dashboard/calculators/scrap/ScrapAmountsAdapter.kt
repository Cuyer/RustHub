package com.cuyer.rusthub.presentation.dashboard.calculators.scrap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.data.remote.dto.items.Ingredient
import com.cuyer.rusthub.data.remote.dto.items.Scrap
import com.cuyer.rusthub.data.remote.dto.items.ScrappedComponents
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_scrap.view.*
import kotlinx.android.synthetic.main.scrap_amount_recyclerview_item.view.*

class ScrapAmountsAdapter(
    private var scrapAmountsList: List<Scrap>)
    : RecyclerView.Adapter<ScrapAmountsAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val icon: ImageView
        val amount: TextView

        init {
            name = view.ScrapChildName
            icon = view.ScrapChildIcon
            amount = view.ScrapChildAmount
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scrap_amount_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return scrapAmountsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = scrapAmountsList[position].name
        holder.amount.text = scrapAmountsList[position].amount
        Picasso.get()
            .load(scrapAmountsList[position].src)
            .error(R.drawable.ic_missing_icon)
            .placeholder(R.drawable.ic_placeholder_image)
            .into(holder.icon)
    }

    fun updateList(scrapAmountsList: List<Scrap>) {
        this.scrapAmountsList = scrapAmountsList
        notifyDataSetChanged()
    }
}