package com.cuyer.rusthub.presentation.dashboard

import android.content.Context
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.presentation.core.CoreActivity
import kotlinx.android.synthetic.main.dashboard_list_recyclerview.view.*

class DashboardListAdapter(
    private val dashboardList: List<DashboardListModel>,
    context: Context?)
    : RecyclerView.Adapter<DashboardListAdapter.ViewHolder>() {

    private val mContext = context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView
        val cardView: CardView
        val fragmentContainerView: FragmentContainerView
        val topBarTextView: TextView

        init {
            val activity = mContext as CoreActivity
            textView = view.DashboardListTextView
            imageView = view.DashboardListImageView
            cardView = view.DashboardList
            fragmentContainerView = activity.findViewById(R.id.FragmentContainer)
            topBarTextView = activity.findViewById(R.id.TopBarTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.dashboard_list_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dashboardList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textView.text = dashboardList[position].text
        holder.imageView.setImageResource(dashboardList[position].image)
        holder.cardView.setOnClickListener{
            val activity  = it.context as? AppCompatActivity
            val newFragmentTag = dashboardList[position].tag
            val newFragment: Fragment = dashboardList[position].action!!
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(R.anim.slide_in_right,
                    R.anim.slide_out_left,
                    R.anim.slide_in_left,
                    R.anim.slide_out_right)
                ?.replace(holder.fragmentContainerView.id, newFragment, newFragmentTag)
                ?.addToBackStack(newFragmentTag)
                ?.commit()
            holder.topBarTextView.text = dashboardList[position].text
        }

    }
}