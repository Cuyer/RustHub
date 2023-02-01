package com.cuyer.rusthub.presentation.dashboard.calculators.scrap

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.data.remote.dto.items.Ingredient
import com.cuyer.rusthub.data.remote.dto.items.Scrap
import com.cuyer.rusthub.data.remote.dto.items.ScrappedComponents
import com.cuyer.rusthub.domain.model.CraftingItems
import com.cuyer.rusthub.presentation.dashboard.calculators.crafting.CraftingDataHolder
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_scrap.view.*
import kotlinx.android.synthetic.main.scrap_amount_recyclerview_item.view.*

class ScrapAmountsAdapter(
    private var scrapAmountsList: List<Scrap>,
    private var currentImage: String,
    context: Context?)
    : RecyclerView.Adapter<ScrapAmountsAdapter.ViewHolder>() {

    private val mContext = context
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val icon: ImageView
        val amount: TextView
        val addButton: Button
        val deleteButton: Button
        val scrapOutput: RecyclerView

        val activity = mContext as AppCompatActivity
        var originalValues = mutableListOf<Double>()
        init {
            scrapOutput = activity.findViewById(R.id.ScrapOutputRecyclerview)
            deleteButton = activity.findViewById(R.id.ScrapTrashButton)
            addButton = activity.findViewById(R.id.ScrapAddButton)
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
        val activity = mContext as AppCompatActivity
        val editText = activity.findViewById<TextInputEditText>(R.id.ScrapEditText)

        for (i in scrapAmountsList.indices) {
            holder.originalValues.add(scrapAmountsList[i].amount.toDouble())
        }

        editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {

                if (p0.toString().isNotEmpty()) {
                    val enteredValue = p0.toString().toInt()
                    for (i in scrapAmountsList.indices) {
                        val newValue = (enteredValue * holder.originalValues[i]).toString()
                        scrapAmountsList[i].amount = newValue
                        notifyDataSetChanged()
                    }
                } else {
                    for (i in scrapAmountsList.indices) {
                        scrapAmountsList[i].amount = holder.originalValues[i].toString()
                        notifyDataSetChanged()
                    }
                }
            }

        })


        holder.addButton.setOnClickListener{
            val slideUpAnimation = AnimationUtils.loadAnimation(mContext, R.anim.slide_up)
            val slideDownAnimation = AnimationUtils.loadAnimation(mContext, R.anim.slide_down)
            val scrapList = mutableListOf<Scrap>()
            if (editText.text.toString() == "") {
                Toast.makeText(mContext, "In order to add item to the list, amount to scrap can't be empty", Toast.LENGTH_SHORT).show()
            } else {
                for (i in scrapAmountsList.indices) {
                    scrapList.add(
                        Scrap(
                            name = scrapAmountsList[i].name,
                            src = scrapAmountsList[i].src,
                            amount = scrapAmountsList[i].amount
                        )
                    )
                }
            }
            val scrapItems = ScrapItems(mainIcon = currentImage, amount = editText.text.toString(), scrapList)
            ScrapDataHolder.addData(scrapItems)
            Log.d("ScrapItems", "${ScrapDataHolder.getData()}")
            notifyDataSetChanged()
        }
        holder.scrapOutput.layoutManager = LinearLayoutManager(mContext)
        holder.scrapOutput.adapter = ScrapOutputAdapter(ScrapDataHolder.getData(), mContext)

        holder.name.text = scrapAmountsList[position].name
        holder.amount.text = scrapAmountsList[position].amount
        Picasso.get()
            .load(scrapAmountsList[position].src)
            .error(R.drawable.ic_missing_icon)
            .placeholder(R.drawable.ic_placeholder_image)
            .into(holder.icon)
    }

    fun updateImage(image: String) {
        this.currentImage = image
        notifyDataSetChanged()
    }
    fun updateList(scrapAmountsList: List<Scrap>) {
        this.scrapAmountsList = scrapAmountsList
        notifyDataSetChanged()
    }
}