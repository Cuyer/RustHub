package com.cuyer.rusthub.presentation.dashboard.calculators.crafting

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.data.remote.dto.items.Ingredient
import com.cuyer.rusthub.domain.model.Items
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.crafting_list_recyclerview_expanded.view.*
import kotlinx.android.synthetic.main.footer_layout.view.*

class CraftingExpandableListAdapter(private val craftingExpandableList: List<Ingredient>, context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mContext = context
    private val FOOTER_TYPE = 0
    private val ITEM_TYPE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == FOOTER_TYPE) {
            val view = LayoutInflater.from(mContext)
                .inflate(R.layout.footer_layout, parent, false)
            FooterViewHolder(view)
        } else {
            val view = LayoutInflater.from(mContext)
                .inflate(R.layout.crafting_list_recyclerview_expanded, parent, false)
            ViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return craftingExpandableList.size + 1 // +1 to include the footer
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == craftingExpandableList.size) FOOTER_TYPE else ITEM_TYPE
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

    inner class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val editText: TextInputEditText = view.footerEditText
        val button: Button = view.footerButton

        init {
            val originalValues = mutableListOf<Int>()
            for (i in craftingExpandableList.indices) {
                originalValues.add(craftingExpandableList[i].value.toInt())
            }

            editText.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0.toString().isNotEmpty()) {
                        val enteredValue = p0.toString().toInt()
                        for (i in craftingExpandableList.indices) {
                            val newValue = (enteredValue * originalValues[i]).toString()
                            craftingExpandableList[i].value = newValue
                            notifyDataSetChanged()
                        }
                    } else {
                        for (i in craftingExpandableList.indices) {
                            craftingExpandableList[i].value = originalValues[i].toString()
                            notifyDataSetChanged()
                        }
                    }
                }

            })
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            Picasso.get()
                .load(craftingExpandableList[position].href)
                .error(R.drawable.ic_missing_icon)
                .placeholder(R.drawable.ic_placeholder_image)
                .into(holder.craftingChildIcon)
            holder.craftingChildAmount.text = craftingExpandableList[position].value
            holder.craftingChildItemName.text = craftingExpandableList[position].title
        }
    }
}
