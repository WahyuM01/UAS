package com.fitrianti.inibolaapp.views.favorites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fitrianti.inibolaapp.R
import com.fitrianti.inibolaapp.views._utils.ViewPagerAdapter
import com.fitrianti.inibolaapp.views.favorites.match.FavoriteMatchFragment
import com.fitrianti.inibolaapp.views.favorites.teams.FavoriteTeamFragment
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoritesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        title = "Favorites"
        tablayout.setupWithViewPager(viewpager)
        initViewPagerFragment()
    }

    private fun initViewPagerFragment() {
        val vpAdapter = ViewPagerAdapter(supportFragmentManager).apply {
            addFragment(FavoriteMatchFragment(), "Match")
            addFragment(FavoriteTeamFragment(), "Team")
        }
        viewpager.adapter = vpAdapter
    }
}
