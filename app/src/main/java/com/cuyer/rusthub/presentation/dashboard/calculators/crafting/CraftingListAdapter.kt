package com.cuyer.rusthub.presentation.dashboard.calculators.crafting

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.cuyer.rusthub.R
import com.cuyer.rusthub.domain.model.Items
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.crafting_list_recyclerview.view.*


class CraftingListAdapter(
    private var craftingList: List<Items>,
    context: Context?
): RecyclerView.Adapter<CraftingListAdapter.ViewHolder>() {

    private val mContext = context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val craftingIcon: ImageView
        val craftingItemName: TextView
        val craftingItemType: TextView
        val craftingExpand: ImageView
        val craftingExpandLess: ImageView
        val expandableLayout: RecyclerView
        val slideDownAnimation = AnimationUtils.loadAnimation(mContext, R.anim.slide_down)
        val slideUpAnimation = AnimationUtils.loadAnimation(mContext, R.anim.slide_up)


        init {
            craftingIcon = view.CraftingIcon
            craftingItemName = view.CraftingItemName
            craftingItemType = view.CraftingItemType
            craftingExpand = view.CraftingExpand
            craftingExpandLess = view.CraftingExpandLess
            expandableLayout = view.ExpandableLayout

            craftingExpandLess.setOnClickListener{
                if (expandableLayout.visibility == View.GONE) {
                    expandableLayout.visibility = View.VISIBLE
                    expandableLayout.startAnimation(slideDownAnimation)
                    craftingExpand.visibility = View.GONE
                    craftingExpandLess.visibility = View.VISIBLE
                } else {
                    expandableLayout.visibility = View.GONE
                    expandableLayout.startAnimation(slideUpAnimation)
                    craftingExpand.visibility = View.VISIBLE
                    craftingExpandLess.visibility = View.GONE
                }
            }

            craftingExpand.setOnClickListener {
                if (expandableLayout.visibility == View.GONE) {
                    expandableLayout.visibility = View.VISIBLE
                    expandableLayout.startAnimation(slideDownAnimation)
                    craftingExpand.visibility = View.GONE
                    craftingExpandLess.visibility = View.VISIBLE
                } else {
                    expandableLayout.visibility = View.GONE
                    expandableLayout.startAnimation(slideUpAnimation)
                    craftingExpand.visibility = View.VISIBLE
                    craftingExpandLess.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.crafting_list_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return craftingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            Picasso.get()
                .load(craftingList[position].image)
                .placeholder(R.drawable.ic_placeholder_image)
                .error(R.drawable.ic_missing_icon)
                .into(holder.craftingIcon)

        holder.craftingItemName.text = craftingList[position].scrappedComponents[0].item
        holder.craftingItemType.text = craftingList[position].type
        holder.craftingItemType.alpha = 0.5f
        holder.expandableLayout.layoutManager = LinearLayoutManager(mContext)
        holder.expandableLayout.adapter = CraftingExpandableListAdapter(craftingList[position].ingredients, craftingList[position].image, mContext)
    }

    fun updateList(newList: List<Items>) {
        craftingList = newList
        notifyDataSetChanged()
    }

}