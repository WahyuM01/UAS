package com.fitrianti.inibolaapp.di

import com.fitrianti.inibolaapp.data.IRepository
import com.fitrianti.inibolaapp.data.Repository
import com.fitrianti.inibolaapp.data.local.database
import com.fitrianti.inibolaapp.data.remote.TheSportDbApiService
import com.fitrianti.inibolaapp.views.favorites.match.FavoriteMatchViewModel
import com.fitrianti.inibolaapp.views.favorites.teams.FavoriteTeamViewModel
import com.fitrianti.inibolaapp.views.leaguedetail.LeagueDetailViewModel
import com.fitrianti.inibolaapp.views.leaguedetail.matchschedule.MatchScheduleViewModel
import com.fitrianti.inibolaapp.views.leaguedetail.standings.StandingsViewModel
import com.fitrianti.inibolaapp.views.leaguedetail.teamlist.TeamListViewModel
import com.fitrianti.inibolaapp.views.matchdetail.MatchDetailViewModel
import com.fitrianti.inibolaapp.views.search.SearchViewModel
import com.fitrianti.inibolaapp.views.teamdetail.TeamDetailViewModel
import com.fitrianti.inibolaapp.views.teamdetail.playerlist.PlayerListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { TheSportDbApiService.create() }
    single { Repository(Schedulers.io(), AndroidSchedulers.mainThread(), get()) as IRepository }
    viewModel { LeagueDetailViewModel(get()) }
    viewModel { MatchDetailViewModel(androidContext().database, get()) }
    viewModel { MatchScheduleViewModel(get()) }
    viewModel { FavoriteMatchViewModel(androidContext().database, get()) }
    viewModel { FavoriteTeamViewModel(androidContext().database, get()) }
    viewModel { TeamListViewModel(get()) }
    viewModel { TeamDetailViewModel(androidContext().database, get()) }
    viewModel { PlayerListViewModel(get()) }
    viewModel { StandingsViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}