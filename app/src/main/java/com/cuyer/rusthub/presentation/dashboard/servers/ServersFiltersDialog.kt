package com.cuyer.rusthub.presentation.dashboard.servers

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.cuyer.rusthub.R
import com.cuyer.rusthub.data.local.entity.FiltersEntity
import com.cuyer.rusthub.data.local.entity.ServersFiltersEntity
import com.cuyer.rusthub.presentation.core.CoreViewModel
import kotlinx.android.synthetic.main.fragment_crafting_filters_dialog.view.*
import kotlinx.android.synthetic.main.fragment_servers_filters_dialog.view.*

class ServersFiltersDialog : DialogFragment() {

    private val viewModel by activityViewModels<CoreViewModel>()

    private lateinit var toolbar: Toolbar
    private lateinit var groupAutoCompleteTextView: AutoCompleteTextView
    private lateinit var ratingAutoCompleteTextView: AutoCompleteTextView
    private lateinit var typeAutoCompleteTextView: AutoCompleteTextView
    private lateinit var regionAutoCompleteTextView: AutoCompleteTextView
    private var filtersList = listOf<ServersFiltersEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
            dialog.window?.setWindowAnimations(R.style.Slide);
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_servers_filters_dialog, container, false)

        viewModel.serversFiltersList.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                val groupList = listOf("Any Group", "Solo", "Duo", "Trio")
                val ratingList = listOf("70%", "80%", "90%", "100%")
                val difficultyList = listOf("Any", "Vanilla", "Official", "Modded", "Softcore")
                val regionList = listOf("Any Region", "Europe", "Asia", "North America", "South America", "Australia/Oceania", "Africa")
                groupAutoCompleteTextView = rootView.ServersFilterGroup
                ratingAutoCompleteTextView = rootView.ServersFilterRating
                typeAutoCompleteTextView = rootView.ServersFilterType
                regionAutoCompleteTextView = rootView.ServersFilterRegion
                val groupArrayAdapter = ArrayAdapter(requireContext(), R.layout.filter_dropdown_item, groupList)
                val ratingArrayAdapter = ArrayAdapter(requireContext(), R.layout.filter_dropdown_item, ratingList)
                val typeArrayAdapter = ArrayAdapter(requireContext(), R.layout.filter_dropdown_item, difficultyList)
                val regionArrayAdapter = ArrayAdapter(requireContext(), R.layout.filter_dropdown_item, regionList)
                groupAutoCompleteTextView.setAdapter(groupArrayAdapter)
                ratingAutoCompleteTextView.setAdapter(ratingArrayAdapter)
                typeAutoCompleteTextView.setAdapter(typeArrayAdapter)
                regionAutoCompleteTextView.setAdapter(regionArrayAdapter)

                if (it.find { it.type == "group" } != null) {
                    groupAutoCompleteTextView.setText(it.find { it.type == "group" }?.value, false)
                }
                if (it.find { it.type == "rating" } != null) {
                    ratingAutoCompleteTextView.setText(it.find { it.type == "rating" }?.value, false)
                }
                if (it.find { it.type == "type" } != null) {
                    typeAutoCompleteTextView.setText(it.find { it.type == "type" }?.value, false)
                }
                if (it.find { it.type == "region" } != null) {
                    regionAutoCompleteTextView.setText(it.find { it.type == "region" }?.value, false)
                }
            } else {
                val groupList = listOf("Any Group", "Solo", "Duo", "Trio")
                val ratingList = listOf("70%", "80%", "90%", "100%")
                val difficultyList = listOf("Any", "Vanilla", "Official", "Modded", "Softcore")
                val regionList = listOf("Any Region", "Europe", "Asia", "North America", "South America", "Australia/Oceania", "Africa")
                groupAutoCompleteTextView = rootView.ServersFilterGroup
                ratingAutoCompleteTextView = rootView.ServersFilterRating
                typeAutoCompleteTextView = rootView.ServersFilterType
                regionAutoCompleteTextView = rootView.ServersFilterRegion
                val groupArrayAdapter = ArrayAdapter(requireContext(), R.layout.filter_dropdown_item, groupList)
                val ratingArrayAdapter = ArrayAdapter(requireContext(), R.layout.filter_dropdown_item, ratingList)
                val typeArrayAdapter = ArrayAdapter(requireContext(), R.layout.filter_dropdown_item, difficultyList)
                val regionArrayAdapter = ArrayAdapter(requireContext(), R.layout.filter_dropdown_item, regionList)
                groupAutoCompleteTextView.setAdapter(groupArrayAdapter)
                ratingAutoCompleteTextView.setAdapter(ratingArrayAdapter)
                typeAutoCompleteTextView.setAdapter(typeArrayAdapter)
                regionAutoCompleteTextView.setAdapter(regionArrayAdapter)
            }
        }

        toolbar = rootView.findViewById(R.id.ServersToolbar);
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener { v: View? -> dismiss() }
        toolbar.title = "Filter"
        toolbar.inflateMenu(R.menu.crafting_filters_menu)
        toolbar.setOnMenuItemClickListener { item: MenuItem? ->
            when(item?.itemId) {
                R.id.action_delete -> {
                    viewModel.deleteServersFilter()
                    dismiss()
                }
                R.id.action_save -> {
                    if (!TextUtils.isEmpty(groupAutoCompleteTextView.text.toString())) {
                        viewModel.setServersFilter(ServersFiltersEntity(type = "group", value = groupAutoCompleteTextView.text.toString()))
                    }
                    if (!TextUtils.isEmpty(ratingAutoCompleteTextView.text.toString())) {
                        viewModel.setServersFilter(ServersFiltersEntity(type = "rating", value = ratingAutoCompleteTextView.text.toString()))
                    }
                    if (!TextUtils.isEmpty(typeAutoCompleteTextView.text.toString())) {
                        viewModel.setServersFilter(ServersFiltersEntity(type = "type", value = typeAutoCompleteTextView.text.toString()))
                    }
                    if (!TextUtils.isEmpty(regionAutoCompleteTextView.text.toString())) {
                        viewModel.setServersFilter(ServersFiltersEntity(type = "region", value = regionAutoCompleteTextView.text.toString()))
                    }
                    dismiss()
                }
                else -> {
                    // Handle other items
                }
            }
            true
        }
    }

    companion object {

    }
}