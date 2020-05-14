package com.panch.stravactivities.ui.main.listFragment

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.panch.stravactivities.R
import com.panch.stravactivities.base.BaseFragmentWithDI
import com.panch.stravactivities.base.DataWrapper
import com.panch.stravactivities.data.model.AthleteActivity
import com.panch.stravactivities.ui.main.MainActivity
import com.panch.stravactivities.ui.renderers.AthleteActivityRenderer
import com.panch.stravactivities.util.hide
import com.panch.stravactivities.util.show
import com.pedrogomez.renderers.RVRendererAdapter
import com.pedrogomez.renderers.RendererBuilder
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.layout_loading.*
import javax.inject.Inject


class AthleteActivitiesListFragment : BaseFragmentWithDI() {
    override val layoutId: Int = R.layout.fragment_list

    lateinit var adapter: RVRendererAdapter<AthleteActivity>

    @Inject
    lateinit var mainActivity: MainActivity

    @Inject
    lateinit var athleteActivitiesListViewModel: AthleteActivitiesListViewModel

    private val athleteActivityClickListener =
        object : AthleteActivityRenderer.AthleteActivityOnClickListener {
            override fun onActivityClick(activity: AthleteActivity) {
                Toast.makeText(context, "TODO: Implement activity detail page.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    private val onlyFirstTwoActivities = false

    @SuppressLint("SetTextI18n")
    private val athleteActivitiesObserver = Observer<DataWrapper<List<AthleteActivity>>> {
        if (it.loading) {
            swipeRefreshLayout.isRefreshing = true
            return@Observer
        }
        if (it.data == null || it.data.isEmpty()) {
            layout_loading.show()
            progressBar.hide()
            swipeRefreshLayout.isRefreshing = false
            infoText.text = getString(R.string.no_data)
            return@Observer
        }
        if (it.offline) {
            textViewOfflineMode.show()
        } else {
            textViewOfflineMode.hide()
        }
        layout_loading.hide()
        swipeRefreshLayout.isRefreshing = false
        if (onlyFirstTwoActivities && it.data.size >= 2) {
            adapter.diffUpdate(it.data.take(2))
        } else {
            adapter.diffUpdate(it.data)
        }

        recyclerViewAthleteActivities.scrollToPosition(0)
    }

    override fun onCreated() {
        recyclerViewAthleteActivities.layoutManager = LinearLayoutManager(activity)
        adapter =
            RVRendererAdapter(RendererBuilder(AthleteActivityRenderer(athleteActivityClickListener)))
        recyclerViewAthleteActivities.adapter = adapter

        athleteActivitiesListViewModel.activities.observe(this, athleteActivitiesObserver)
        athleteActivitiesListViewModel.loadActivities()

        swipeRefreshLayout.setColorSchemeColors(
            ContextCompat.getColor(
                context!!,
                R.color.colorAccent
            )
        )
        swipeRefreshLayout.setOnRefreshListener {
            athleteActivitiesListViewModel.loadActivities()
        }

    }

    companion object {
        private var instance: AthleteActivitiesListFragment? = null
        fun newInstance(): AthleteActivitiesListFragment {
            if (instance == null) {
                instance = AthleteActivitiesListFragment()
            }
            return instance!!
        }
    }
}