package com.cuyer.rusthub.presentation.dashboard.calculators.crafting

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.cuyer.rusthub.presentation.core.CoreViewModel
import kotlinx.android.synthetic.main.fragment_crafting_filters_dialog.view.*


class CraftingFiltersDialog : DialogFragment() {

    private val viewModel by activityViewModels<CoreViewModel>()

    private lateinit var toolbar: Toolbar
    private lateinit var autoCompleteTextView: AutoCompleteTextView

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
        val rootView = inflater.inflate(R.layout.fragment_crafting_filters_dialog, container, false)

        viewModel.getItemsList.observe(viewLifecycleOwner) { itemsList ->
            if (itemsList.isNotEmpty()) {
                val typesList = itemsList.map { it.type }.toSet()
                autoCompleteTextView = rootView.CraftingFilterType
                val arrayAdapter = ArrayAdapter(requireContext(), R.layout.filter_dropdown_item, typesList.toTypedArray())
                autoCompleteTextView.setAdapter(arrayAdapter)
            }
        }



        toolbar = rootView.findViewById(R.id.toolbar);

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener { v: View? -> dismiss() }
        toolbar.title = "Filter"
        toolbar.inflateMenu(R.menu.crafting_filters_menu)
        toolbar.setOnMenuItemClickListener { item: MenuItem? ->
            dismiss()
            true
        }
    }

    companion object {

    }
}