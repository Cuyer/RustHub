package com.cuyer.rusthub.presentation.core

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentContainerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.presentation.dashboard.DashboardFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_core.*


@AndroidEntryPoint
class CoreActivity : AppCompatActivity() {

    private val viewModel by viewModels<CoreViewModel>()
    private lateinit var dashboardFragment: DashboardFragment
    private lateinit var fragmentContainer: FragmentContainerView
    private lateinit var topBar: ConstraintLayout
    private lateinit var topBarTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var message: TextView
    private lateinit var currentFragmentTag: String
    private lateinit var slideAnimation : Animation
    private lateinit var splashScreen: SplashScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_core)
        dashboardFragment = DashboardFragment()
        topBarTextView = TopBarTextView
        progressBar = CoreProgressBar
        message = CoreMessage
        fragmentContainer = FragmentContainer
        topBar = TopBar
        slideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)


        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideBack = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.TRANSLATION_X,
                0f,
                -splashScreenView.view.width.toFloat()
            ).apply {
                interpolator = DecelerateInterpolator()
                duration = 300L
                doOnEnd { splashScreenView.remove() }
            }

            slideBack.start()
        }

        viewModel.currentFragmentTag.observe(this) { fragmentTag ->
            currentFragmentTag = fragmentTag.toString()
            Log.d("FragmentTag", "$currentFragmentTag")

            val currentFragment = supportFragmentManager
                .findFragmentByTag(currentFragmentTag)

            if (currentFragment != null) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(FragmentContainer.id, currentFragment, currentFragmentTag)
                    .commit()
            }
        }

        viewModel.currentFragmentName.observe(this) {fragmentName ->
            topBarTextView.text = fragmentName.toString()
        }


        viewModel.getItemsState.observe(this){
            if (it.isLoading && it.items.isEmpty()) {
                topBar.visibility = View.GONE
                fragmentContainer.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                message.visibility = View.VISIBLE
                message.text = getString(R.string.downloading_items)
            } else if (it.error.isNotBlank()){
                message.text = getString(R.string.download_error)
                message.gravity = Gravity.CENTER
                progressBar.visibility = View.GONE
                topBar.visibility = View.GONE
                fragmentContainer.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                message.visibility = View.GONE
                topBar.visibility = View.VISIBLE
                topBar.startAnimation(slideAnimation)
                fragmentContainer.visibility = View.VISIBLE
                fragmentContainer.startAnimation(slideAnimation)
            }
        }

        viewModel.getServersState.observe(this){
            if (it.isLoading && it.servers.isEmpty()) {
                topBar.visibility = View.GONE
                fragmentContainer.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                message.visibility = View.VISIBLE
                message.text = getString(R.string.downloading_servers)
            } else if (it.error.isNotBlank()){
                message.text = getString(R.string.download_error)
                message.gravity = Gravity.CENTER
                progressBar.visibility = View.GONE
                topBar.visibility = View.GONE
                fragmentContainer.visibility = View.GONE
            }
        }


    }
}