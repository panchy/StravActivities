package com.panch.stravactivities.ui.renderers

import android.annotation.SuppressLint
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.panch.stravactivities.R
import com.panch.stravactivities.data.model.AthleteActivity
import com.panch.stravactivities.util.*
import com.pedrogomez.renderers.Renderer
import kotlinx.android.synthetic.main.item_activity.view.*

class AthleteActivityRenderer(private val listener: AthleteActivityOnClickListener) :
    Renderer<AthleteActivity>() {
    override fun inflate(inflater: LayoutInflater?, parent: ViewGroup?): View {
        return inflater!!.inflate(R.layout.item_activity, parent, false)
    }

    override fun setUpView(rootView: View?) {

    }

    override fun hookListeners(rootView: View?) {
        rootView?.setOnClickListener {
            listener.onActivityClick(content)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun render() {
        if (content.name.isNullOrEmpty()) {
            rootView!!.textViewActivityName.hide()
        } else {
            rootView!!.textViewActivityName.show()
            rootView!!.textViewActivityName.text = content.name
        }

        if (content.type.isNullOrEmpty()) {
            rootView!!.textViewActivityType.hide()
        } else {
            rootView!!.textViewActivityType.show()
            rootView!!.textViewActivityType.text = content.type
        }

        if (content.distance == null || content.distance == 0.0f) {
            rootView!!.textViewActivityDistance.hide()
        } else {
            rootView!!.textViewActivityDistance.show()
            rootView!!.textViewActivityDistance.text = content.distance!!.toDistanceString()
        }

        if (content.elapsedTime == null || content.elapsedTime == 0) {
            rootView!!.textViewActivityElapsedTime.hide()
        } else {
            rootView!!.textViewActivityElapsedTime.show()
            rootView!!.textViewActivityElapsedTime.text =
                Html.fromHtml("<b>Duration:</b> ${content.elapsedTime!!.toTimeString()}")
        }

        if (content.startTime.isNullOrEmpty()) {
            rootView!!.textViewActivityTime.hide()
        } else {
            rootView!!.textViewActivityTime.show()
            rootView!!.textViewActivityTime.text = content.startTime!!.toFormattedDate()
        }

        if (content.commute == null || !content.commute!!) {
            rootView!!.textViewActivityCommute.hide()
        } else {
            rootView!!.textViewActivityCommute.show()
        }
    }


    interface AthleteActivityOnClickListener {
        fun onActivityClick(activity: AthleteActivity)
    }
}