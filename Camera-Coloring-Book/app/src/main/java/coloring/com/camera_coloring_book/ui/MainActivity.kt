package coloring.com.camera_coloring_book.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import coloring.com.camera_coloring_book.R
import coloring.com.camera_coloring_book.ui.coloring.ColoringFragment
import coloring.com.camera_coloring_book.ui.palette.PaletteFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

/*
TODO : 사진 리스트가 아래에서 위로 올라오게 하기
TODO : 색깔들 저장 방법 찾기
*/
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout.addTab(tabLayout.newTab().setText("내 팔레트"))
        tabLayout.addTab(tabLayout.newTab().setText("내 컬러링"))

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