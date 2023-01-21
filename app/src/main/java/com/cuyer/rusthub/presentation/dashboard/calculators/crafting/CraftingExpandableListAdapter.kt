package com.cuyer.rusthub.presentation.dashboard.calculators.crafting

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.data.remote.dto.items.Ingredient
import com.cuyer.rusthub.domain.model.Items
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.crafting_list_recyclerview_expanded.view.*

class CraftingExpandableListAdapter(private val craftingExpandableList: List<Ingredient>, context: Context?)
    : RecyclerView.Adapter<CraftingExpandableListAdapter.ViewHolder>() {

    private val mContext = context


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.crafting_list_recyclerview_expanded, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return craftingExpandableList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val craftingChildIcon: ImageView
        val craftingChildItemName: TextView
        val craftingChildAmount: TextView

        init {
            craftingChildIcon = view.CraftingChildIcon
            craftingChildItemName = view.CraftingChildName
            craftingChildAmount = view.CraftingChildAmount
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get()
            .load(craftingExpandableList[position].href)
            .error(R.drawable.ic_missing_icon)
            .placeholder(R.drawable.ic_placeholder_image)
            .into(holder.craftingChildIcon)
        holder.craftingChildAmount.text = craftingExpandableList[position].value
        holder.craftingChildItemName.text = craftingExpandableList[position].title

    }
}

