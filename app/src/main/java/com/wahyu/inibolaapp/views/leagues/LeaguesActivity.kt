package com.fitrianti.inibolaapp.views.leagues

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.fitrianti.inibolaapp.R
import com.fitrianti.inibolaapp.data.IRepository
import com.fitrianti.inibolaapp.views.favorites.FavoritesActivity
import com.fitrianti.inibolaapp.views.leaguedetail.LeagueDetailActivity
import com.fitrianti.inibolaapp.views.search.SearchActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.android.ext.android.inject

class LeaguesActivity : AppCompatActivity() {

    // Should be injected / or use viewmodel, tapi pengecualian
    private val repository: IRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val leagues = repository.getLeagues(this.resources)

        verticalLayout {
            background = ColorDrawable(Color.parseColor("#efefef"))

            // Favorites
            cardView {
                foreground = with(TypedValue()) {
                    context.theme.resolveAttribute(R.attr.selectableItemBackground, this, true)
                    ContextCompat.getDrawable(context, resourceId)
                }
                isClickable = true
                radius = dip(8).toFloat()
                onClick { startActivity(intentFor<FavoritesActivity>()) }
                frameLayout {
                    imageView {
                        setImageResource(R.drawable.favorite_match_bg)
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }.lparams(matchParent, dip(64))
                    imageView {
                        setImageResource(R.drawable.appbar_scrim)
                    }.lparams(matchParent, dip(64))
                    textView("Favorite Matches & Teams") {
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textSize = 24f
                        textColor = Color.WHITE
                        gravity = Gravity.CENTER
                    }.lparams(matchParent, matchParent)
                }
            }.lparams(matchParent, wrapContent) {
                rightMargin = dip(12)
                leftMargin = dip(12)
                topMargin = dip(4)
                bottomMargin = dip(8)
            }

            // League List
            recyclerView {
                id = R.id.rv_leagues
                padding = dip(8)
                lparams(matchParent, matchParent)
                adapter = LeagueAdapter(leagues) { leagueModel ->
                    startActivity(
                        intentFor<LeagueDetailActivity>(
                            LeagueDetailActivity.LEAGUE_PARCEL to leagueModel
                        )
                    )
                }
                layoutManager = GridLayoutManager(context, 2)
            }
        }
    }

    private var exitToast: Toast? = null
    override fun onBackPressed() {
        if (exitToast == null || exitToast!!.view == null || exitToast!!.view.windowToken == null) {
            exitToast = Toast.makeText(this, "Press again to exit", Toast.LENGTH_LONG)
            exitToast!!.show()
        } else {
            exitToast!!.cancel()
            super.onBackPressed()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.league_detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.league_event_search_menu -> {
                startActivity(intentFor<SearchActivity>())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
