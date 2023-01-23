package com.cuyer.rusthub.presentation.dashboard.calculators.crafting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.domain.model.CraftingItems
import com.cuyer.rusthub.presentation.core.CoreActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.crafting_details_recyclerview.view.*

class CraftingDetailsSheetAdapter(
    private val craftingDetailsList: List<CraftingItems>,
    context: Context?
) : RecyclerView.Adapter<CraftingDetailsSheetAdapter.ViewHolder>(){

    private val mContext = context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textview: TextView
        val imageView: ImageView
        val recyclerView: RecyclerView

        init {
            val activity = mContext as CoreActivity
            textview = view.IngredientsDetailsAmount
            imageView = view.IngredientsDetailsIcon
            recyclerView = view.IngredientsDetailsRecyclerView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.crafting_details_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return craftingDetailsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textview.text = craftingDetailsList[position].amount
        Picasso.get()
            .load(craftingDetailsList[position].mainIcon)
            .error(R.drawable.ic_missing_icon)
            .placeholder(R.drawable.ic_placeholder_image)
            .into(holder.imageView)
        holder.recyclerView.layoutManager = LinearLayoutManager(mContext)
        holder.recyclerView.adapter = CraftingIngredientDetailsAdapter(craftingDetailsList[position].ingredients, mContext)
    }

}