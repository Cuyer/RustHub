package com.cuyer.rusthub.presentation.dashboard.calculators.crafting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.data.remote.dto.items.Ingredient
import com.cuyer.rusthub.domain.model.CraftingItems
import com.cuyer.rusthub.presentation.core.CoreViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_crafting_details_sheet.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class CraftingDetailsSheetFragment : BottomSheetDialogFragment() {

    private lateinit var adapter: CraftingDetailsSheetAdapter
    private val viewModel by activityViewModels<CoreViewModel>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_crafting_details_sheet, container, false)

        viewModel.craftingItemsList.observe(this) { craftingItemsList ->
            if (craftingItemsList.isNotEmpty()) {
                val mergedData = craftingItemsList.groupBy { it.mainIcon }
                    .map { (mainIcon, group) ->
                        val ingredients = group.flatMap { it.ingredients }
                            .groupBy { it.href }
                            .map { (href, ingredientGroup) ->
                                Ingredient(href, ingredientGroup.first().title, ingredientGroup.sumBy { it.value.toInt() }.toString())
                            }
                        CraftingItems(mainIcon, group.sumBy { it.amount.toInt() }.toString(), ingredients)
                    }

                recyclerView = rootView.CraftingDetailsSheetRecyclerView
                recyclerView.layoutManager = LinearLayoutManager(activity)
                adapter = CraftingDetailsSheetAdapter(mergedData, context)
                recyclerView.adapter = adapter
            }
        }
        return rootView
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onEvent(event: CraftingEvent) {
        viewModel.craftingItemsList.value?.let {
            viewModel.setCraftingItemsList(it + event.craftingItems)
        } ?: run {
            viewModel.setCraftingItemsList(event.craftingItems)
        }
        EventBus.getDefault().removeStickyEvent(event)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}