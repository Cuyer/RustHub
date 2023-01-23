package com.cuyer.rusthub.presentation.dashboard.calculators.crafting

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.common.ItemTouchHelperAdapter
import com.cuyer.rusthub.domain.model.CraftingItems
import com.cuyer.rusthub.presentation.core.CoreActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.crafting_details_recyclerview.view.*
import kotlinx.android.synthetic.main.fragment_crafting.*

class CraftingDetailsSheetAdapter(
     val craftingDetailsList: MutableList<CraftingItems>,
    context: Context?
) : RecyclerView.Adapter<CraftingDetailsSheetAdapter.ViewHolder>(), ItemTouchHelperAdapter {

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

    val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val slideDownAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_down)
            val position = viewHolder.adapterPosition
            craftingDetailsList.removeAt(position)
            notifyItemRemoved(position)
            if (craftingDetailsList.isEmpty()) {
                CraftingDataHolder.removeData()
                val activity = mContext as CoreActivity
                activity.CraftingViewDetails.visibility = View.GONE
                activity.CraftingViewDetails.startAnimation(slideDownAnimation)
            }
        }
        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                val itemView = viewHolder.itemView
                val background = ColorDrawable(Color.parseColor("#CE422B"))
                background.setBounds(
                    itemView.right + dX.toInt(),
                    itemView.top,
                    itemView.right,
                    itemView.bottom
                )
                background.draw(c)

                val icon = AppCompatResources.getDrawable(mContext!!, R.drawable.ic_delete)
                val iconMargin = (itemView.height - icon!!.intrinsicHeight) / 2
                val iconTop = itemView.top + iconMargin
                val iconBottom = iconTop + icon.intrinsicHeight
                val iconLeft = itemView.right - iconMargin - icon.intrinsicWidth
                val iconRight = itemView.right - iconMargin
                val iconDest = Rect(iconLeft, iconTop, iconRight, iconBottom)
                icon.bounds = iconDest
                icon.draw(c)
            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }

    override fun onItemDismiss(position: Int) {
        craftingDetailsList.removeAt(position)
        notifyDataSetChanged()
    }
}