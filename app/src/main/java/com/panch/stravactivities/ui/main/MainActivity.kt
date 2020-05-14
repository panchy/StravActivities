package com.panch.stravactivities.ui.main

import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.panch.stravactivities.R
import com.panch.stravactivities.base.BaseActivity
import com.panch.stravactivities.base.BaseActivityWithDI
import com.panch.stravactivities.ui.main.listFragment.AthleteActivitiesListFragment
import javax.inject.Inject

class MainActivity : BaseActivityWithDI() {

    override val layoutRes: Int = R.layout.activity_main

    private val listFragment by lazy {
        return@lazy AthleteActivitiesListFragment.newInstance()
    }

    private val sfm by lazy {
        return@lazy supportFragmentManager
    }

    override fun onCreated() {
        replaceFragment(listFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val targetId = R.id.container
        val tag = fragment.javaClass.simpleName
        sfm.fragments.forEach { sfm.beginTransaction().hide(it).commit() }

        if (sfm.findFragmentByTag(tag) == null) {
            sfm.beginTransaction()
                .add(targetId, fragment, tag)
                .commit()
        } else {
            sfm.beginTransaction()
                .show(fragment)
                .commit()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(tag != listFragment.javaClass.simpleName)
    }

    private fun needToGoHome(): Boolean {
        val sfm = supportFragmentManager
        val shownFragmentName = sfm.fragments.firstOrNull { !it.isHidden }?.javaClass?.simpleName
        if (shownFragmentName != listFragment.javaClass.simpleName) {
            replaceFragment(listFragment)
            return true
        }
        return false
    }

    override fun onBackPressed() {
        if (!needToGoHome())
            super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId!!
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
