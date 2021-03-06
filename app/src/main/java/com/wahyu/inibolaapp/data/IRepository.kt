package com.fitrianti.inibolaapp.data

import android.content.res.Resources
import com.fitrianti.inibolaapp.data.local.DatabaseHelper
import com.fitrianti.inibolaapp.data.model.*
import com.fitrianti.inibolaapp.data.remote.response.LeagueDetailRes
import com.fitrianti.inibolaapp.data.remote.response.SearchEventsRes
import com.fitrianti.inibolaapp.data.remote.response.TeamsRes
import io.reactivex.Single

interface IRepository {
    fun getLeagues(resources: Resources): List<League>
    fun getLeagueDetail(id: Int): Single<LeagueDetailRes.League>
    fun getLeagueStandings(leagueId: String): Single<List<Standing>>
    fun getNextMatchByLeagueId(leagueId: String): Single<List<EventWithImage>>
    fun getLastMatchByLeagueId(leagueId: String): Single<List<EventWithImage>>
    fun searchMatches(query: String): Single<SearchEventsRes>
    fun searchTeams(query: String): Single<TeamsRes>
    fun getMatchDetail(eventId: String): Single<EventWithImage?>
    fun getTeamDetail(teamId: String): Single<Team>
    fun getFavoriteEvents(db: DatabaseHelper): List<FavoriteEvent>
    fun getFavoriteTeams(db: DatabaseHelper): List<Team>
    fun updateEventWithTeamBadge(event: EventWithImage): Single<List<String>>
    fun getTeamList(leagueId: String): Single<List<Team>>
    fun getPlayerList(teamId: String): Single<List<Player>>
    fun getPlayerDetail(playerId: String): Single<Player>
}