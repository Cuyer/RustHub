package com.cuyer.rusthub.presentation.dashboard.calculators.crafting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
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
                recyclerView = rootView.CraftingDetailsSheetRecyclerView
                recyclerView.layoutManager = LinearLayoutManager(activity)
                adapter = CraftingDetailsSheetAdapter(craftingItemsList, context)
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