package com.cuyer.rusthub.presentation.dashboard.calculators.crafting

import android.media.Image
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.data.local.entity.FiltersEntity
import com.cuyer.rusthub.presentation.core.CoreViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_crafting.view.*
import org.greenrobot.eventbus.EventBus


class CraftingFragment : Fragment() {

    private val viewModel by activityViewModels<CoreViewModel>()
    private var onBackPressedCalled = false
    private lateinit var adapter: CraftingListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: TextInputEditText
    private lateinit var craftingDetailsButton: ImageView
    private lateinit var filterImageView: ImageView
    private var filtersList = listOf<FiltersEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getItemsFromDb()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_crafting, container, false)
        searchEditText = rootView.SearchEdit
        craftingDetailsButton = rootView.CraftingDetailsExpand

        craftingDetailsButton.setOnClickListener {
            CraftingDetailsSheetFragment().show(childFragmentManager, "crafting_details_sheet")
            EventBus.getDefault().postSticky(CraftingEvent(CraftingDataHolder.getData()))
            CraftingDataHolder.removeData()
        }

        viewModel.filtersList.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()){
                filtersList = it
                viewModel.getItemsList.observe(viewLifecycleOwner) { itemsList ->
                    if (itemsList.isNotEmpty()) {
                        val craftableItemsList = itemsList.filter { it.craftable!! == "Yes" && it.type == filtersList[0].name}
                        searchEditText.addTextChangedListener(object : TextWatcher {
                            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            }
                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                                val filteredList = craftableItemsList.filter { it.scrappedComponents[0]
                                    .item.contains(s.toString(), ignoreCase = true) }
                                viewModel.setSearchValue(s.toString())
                                adapter.updateList(filteredList)
                            }
                            override fun afterTextChanged(p0: Editable?) {
                            }
                        })

                        recyclerView = rootView.CraftingRecyclerView
                        val layoutManager = LinearLayoutManager(context)
                        recyclerView.layoutManager = layoutManager
                        layoutManager.scrollToPosition(viewModel.scrollPosition)

                        adapter = CraftingListAdapter(craftableItemsList, context)
                        recyclerView.adapter = adapter

                        searchEditText.setText(viewModel.searchValue.value)
                        val filteredList = craftableItemsList.filter { it.scrappedComponents[0].item.contains(viewModel.searchValue.value.toString(), ignoreCase = true) }
                        adapter.updateList(filteredList)
                    }
                }
            } else {
                viewModel.getItemsList.observe(viewLifecycleOwner) { itemsList ->
                    if (itemsList.isNotEmpty()) {
                        val craftableItemsList = itemsList.filter { it.craftable!! == "Yes"}
                        searchEditText.addTextChangedListener(object : TextWatcher {
                            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            }
                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                                val filteredList = craftableItemsList.filter { it.scrappedComponents[0]
                                    .item.contains(s.toString(), ignoreCase = true) }
                                viewModel.setSearchValue(s.toString())
                                adapter.updateList(filteredList)
                            }
                            override fun afterTextChanged(p0: Editable?) {
                            }
                        })

                        recyclerView = rootView.CraftingRecyclerView
                        val layoutManager = LinearLayoutManager(context)
                        recyclerView.layoutManager = layoutManager
                        layoutManager.scrollToPosition(viewModel.scrollPosition)

                        adapter = CraftingListAdapter(craftableItemsList, context)
                        recyclerView.adapter = adapter

                        searchEditText.setText(viewModel.searchValue.value)
                        val filteredList = craftableItemsList.filter { it.scrappedComponents[0].item.contains(viewModel.searchValue.value.toString(), ignoreCase = true) }
                        adapter.updateList(filteredList)
                    }
                }
            }
        }



        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.setCurrentFragmentName("Calculators")
            onBackPressedCalled = true
            isEnabled = false
            requireActivity().onBackPressed()
        }
        filterImageView = rootView.CraftingFilterIcon

        filterImageView.setOnClickListener {
            val craftingFiltersDialog = CraftingFiltersDialog()
            craftingFiltersDialog.show(childFragmentManager, "crafting_filters_dialog")
        }

        return rootView
    }


    override fun onDetach() {
        super.onDetach()
        viewModel.setCurrentFragmentTag(TAG)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        viewModel.scrollPosition = layoutManager.findFirstVisibleItemPosition()

        if(!onBackPressedCalled) {
            viewModel.setCurrentFragmentName("Crafting")
        }
    }


    fun dismissCraftingDetailsSheetFragment() {
        val fragment = childFragmentManager.findFragmentByTag("crafting_details_sheet") as CraftingDetailsSheetFragment?
        if (fragment != null && fragment.isVisible) {
            fragment.dismiss()
        }
    }

    companion object {
        const val TAG = "crafting_fragment"
    }

}