package com.fitrianti.inibolaapp.views.leaguedetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.fitrianti.inibolaapp.R
import com.fitrianti.inibolaapp.data.model.League
import com.fitrianti.inibolaapp.data.model.LoadingState
import com.fitrianti.inibolaapp.data.model.PopulatedState
import com.fitrianti.inibolaapp.data.remote.response.LeagueDetailRes
import com.fitrianti.inibolaapp.views._utils.ViewPagerAdapter
import com.fitrianti.inibolaapp.views.leaguedetail.matchschedule.LastMatchFragment
import com.fitrianti.inibolaapp.views.leaguedetail.matchschedule.NextMatchFragment
import com.fitrianti.inibolaapp.views.leaguedetail.standings.StandingsFragment
import com.fitrianti.inibolaapp.views.leaguedetail.teamlist.TeamListFragment
import com.fitrianti.inibolaapp.views.search.SearchActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_league_detail.*
import org.jetbrains.anko.intentFor
import org.koin.android.viewmodel.ext.android.viewModel

class LeagueDetailActivity : AppCompatActivity() {

    companion object {
        const val LEAGUE_PARCEL = "league_parcel"
    }

    private val mViewModel: LeagueDetailViewModel by viewModel()
    private lateinit var leagueModel: League

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)

        setSupportActionBar(toolbar)

        leagueModel = intent.getParcelableExtra(LEAGUE_PARCEL)
        ct_layout.title = leagueModel.name
        ct_layout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.white))
        ct_layout.setCollapsedTitleTextColor(ContextCompat.getColor(this, android.R.color.white))

        mViewModel.getLeagueDetail(leagueModel.id).observe(this,
            Observer { state ->
                when (state) {
                    is LoadingState -> {
                    }
                    is PopulatedState -> {
                        Picasso.get().load(state.data.strFanart1).into(iv_league_detail_banner)
                        Picasso.get().load(state.data.strBadge).into(civ_league_detail)
                        tv_title_league.text = state.data.strLeagueAlternate
                        tv_country.text = state.data.strCountry
                        initViewPagerFragment(state.data)
                    }
                }
            })

        tablayout.setupWithViewPager(viewpager)
        initViewPagerFragment(null)
    }

    private fun initViewPagerFragment(league: LeagueDetailRes.League?) {
        val vpAdapter = ViewPagerAdapter(supportFragmentManager).apply {
            addFragment(
                LeagueDetailFragment.newInstance(league),
                "Info"
            )
            addFragment(
                StandingsFragment.newInstance(league?.idLeague),
                "Standings"
            )
            addFragment(
                NextMatchFragment.newInstance(league?.idLeague),
                "Next Match"
            )
            addFragment(
                LastMatchFragment.newInstance(league?.idLeague),
                "Last Match"
            )
            addFragment(
                TeamListFragment.newInstance(league?.idLeague),
                "Teams"
            )
        }
        viewpager.adapter = vpAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.league_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.league_event_search_menu -> {
                startActivity(intentFor<SearchActivity>())
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
