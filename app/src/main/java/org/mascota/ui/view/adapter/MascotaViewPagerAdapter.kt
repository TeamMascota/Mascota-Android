package org.mascota.ui.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MascotaViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val _fragmentList = mutableListOf<Fragment>()
    var fragmentList: List<Fragment> = _fragmentList
        set(value) {
            _fragmentList.addAll(value)
        }

    override fun getItemCount(): Int {
        return _fragmentList.count()
    }

    override fun createFragment(position: Int): Fragment = _fragmentList[position]
}