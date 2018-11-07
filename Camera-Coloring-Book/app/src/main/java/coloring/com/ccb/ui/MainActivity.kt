package coloring.com.ccb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import coloring.com.ccb.R
import coloring.com.ccb.ui.coloring.ColoringFragment
import coloring.com.ccb.ui.palette.PaletteFragment
import coloring.com.ccb.ui.palette.PaletteFragment.Companion.behavior
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout.addTab(tabLayout.newTab().setText("내 팔레트"))
        tabLayout.addTab(tabLayout.newTab().setText("컬러링"))

        viewPager.adapter = TabPagerAdapter(supportFragmentManager, tabLayout.tabCount)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabReselected(p0: TabLayout.Tab?) {}
        })
    }

    override fun onBackPressed() {
        if(behavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            viewPager.currentItem = 0
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        else super.onBackPressed()
    }
}


class TabPagerAdapter(fm: FragmentManager, private val tabCount : Int) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = tabCount

    override fun getItem(position: Int): Fragment? {
        return when(position){
            0 -> PaletteFragment()
            1 -> ColoringFragment()
            else -> null
        }
    }
}