package com.cuyer.rusthub.presentation.dashboard.calculators.crafting

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.cuyer.rusthub.R
import com.cuyer.rusthub.presentation.core.CoreActivity
import com.cuyer.rusthub.presentation.core.CoreViewModel
import com.cuyer.rusthub.presentation.dashboard.calculators.CalculatorsFragment
import kotlinx.android.synthetic.main.activity_core.*


class CraftingFragment : Fragment() {

    private val viewModel by activityViewModels<CoreViewModel>()
    private var onBackPressedCalled = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_crafting, container, false)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.setCurrentFragmentName("Calculators")
            onBackPressedCalled = true
            isEnabled = false
            requireActivity().onBackPressed()
        }
        return rootView
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.setCurrentFragmentTag(TAG)
        if(!onBackPressedCalled) {
            viewModel.setCurrentFragmentName("Crafting")
        }
    }

    companion object {
        const val TAG = "crafting_fragment"
    }

}