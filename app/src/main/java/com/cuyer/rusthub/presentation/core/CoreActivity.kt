package com.cuyer.rusthub.presentation.core

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cuyer.rusthub.R
import com.cuyer.rusthub.TestViewModel
import com.cuyer.rusthub.presentation.dashboard.DashboardFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_core.*


@AndroidEntryPoint
class CoreActivity : AppCompatActivity() {

    private val viewModel by viewModels<TestViewModel>()
    private lateinit var dashboardFragment: DashboardFragment
    private lateinit var topBarTextView: TextView
    private lateinit var currentFragmentTag: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_core)
        viewModel.getItems()
        viewModel.getServers()
        dashboardFragment = DashboardFragment()
        topBarTextView = TopBarTextView

        currentFragmentTag = viewModel.currentFragmentTag.value.toString()

        Log.d("FragmentTag", "onCreate: $currentFragmentTag")
        val currentFragment = supportFragmentManager
            .findFragmentByTag(currentFragmentTag)
        if (currentFragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(FragmentContainer.id, currentFragment, currentFragmentTag)
                .commit()
        } else {
            Log.d("FragmentTag", "I was called")
            supportFragmentManager.beginTransaction().replace(
                FragmentContainer.id,
                dashboardFragment,
                "dashboard_fragment")
                .commit()
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        topBarTextView.text = getString(R.string.app_name)
    }
}