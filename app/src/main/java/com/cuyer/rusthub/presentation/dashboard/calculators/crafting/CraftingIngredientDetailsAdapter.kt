package com.cuyer.rusthub.presentation.dashboard.calculators.crafting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.data.remote.dto.items.Ingredient
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.crafting_ingredient_details_recyclerview.view.*

class CraftingIngredientDetailsAdapter(
    private val craftingIngredientList: List<Ingredient>,
    context: Context?
): RecyclerView.Adapter<CraftingIngredientDetailsAdapter.ViewHolder>() {

    private val mContext = context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val ingredientsDetailsName: TextView
        val ingredientsDetailsAmount: TextView
        val ingredientsDetailsIcon: ImageView

        init {
            ingredientsDetailsIcon = view.CraftingIngredientDetailsIcon
            ingredientsDetailsAmount = view.CraftingIngredientDetailsAmount
            ingredientsDetailsName = view.CraftingIngredientDetailsName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.crafting_ingredient_details_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return craftingIngredientList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get()
            .load(craftingIngredientList[position].href)
            .placeholder(R.drawable.ic_placeholder_image)
            .error(R.drawable.ic_missing_icon)
            .into(holder.ingredientsDetailsIcon)
        holder.ingredientsDetailsName.text = craftingIngredientList[position].title
        holder.ingredientsDetailsAmount.text = craftingIngredientList[position].value
    }


}