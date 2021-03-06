package com.fitrianti.inibolaapp.data.remote

import com.fitrianti.inibolaapp.BuildConfig
import com.fitrianti.inibolaapp.data.remote.response.*
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TheSportDbApiService {

    // League Endpoint, strSport == soccer
    // https://www.thesportsdb.com/api/v1/json/1/all_leagues.php
    @GET("/api/v1/json/${BuildConfig.TSDB_API_KEY}/all_leagues.php")
    fun getLeagues(): Single<LeaguesRes>

    // League detail endpoint {leagueId}
    // https://www.thesportsdb.com/api/v1/json/1/lookupleague.php?id=4346
    @GET("/api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupleague.php")
    fun getLeagueDetail(@Query("id") leagueId: Int): Single<Response<LeagueDetailRes>>

    // League standings (klasemen) endpoint {leagueId}
    // https://www.thesportsdb.com/api/v1/json/1/lookuptable.php?l=4387
    @GET("/api/v1/json/${BuildConfig.TSDB_API_KEY}/lookuptable.php")
    fun getLeagueStandings(@Query("l") leagueId: String): Single<Response<LeagueStandingsRes>>

    // Match in League Endpoint {leagueId}
    // Next 15 https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328
    @GET("/api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php")
    fun getNextMatchByLeagueId(@Query("id") leagueId: String): Single<Response<EventsRes>>

    // Last 15 https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328
    @GET("/api/v1/json/${BuildConfig.TSDB_API_KEY}/eventspastleague.php")
    fun getLastMatchByLeagueId(@Query("id") leagueId: String): Single<Response<EventsRes>>

    // Detail Match endpoint {eventId}
    // https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=441613
    // Kontennya kok sama kaya next15 / last15 ??
    @GET("/api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php")
    fun getMatchDetailByEventId(@Query("id") eventId: String): Single<Response<EventsRes>>

    // Search match endpoint {stringQuery}
    // https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e=Arsenal_vs_Chelsea
    @GET("/api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php")
    fun searchMatches(@Query("e") query: String): Single<SearchEventsRes>

    // Search team endpoint {stringQuery}
    // https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=Arsenal
    @GET("/api/v1/json/${BuildConfig.TSDB_API_KEY}/searchteams.php")
    fun searchTeams(@Query("t") query: String): Single<TeamsRes>

    // Team Detail endpoint {teamId}
    // https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=133604
    @GET("/api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php")
    fun getTeamDetail(@Query("id") teamId: String): Single<Response<TeamsRes>>

    // Team List endpoint {leagueId}
    // https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id={idLeague}
    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_teams.php")
    fun getTeamListByLeagueId(@Query("id") leagueId: String): Single<Response<TeamsRes>>

    // Player List endpoint {teamId}
    // https://www.thesportsdb.com/api/v1/json/1/lookup_all_players.php?id={idTeam}
    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_players.php")
    fun getPlayerList(@Query("id") teamId: String): Single<Response<PlayerListRes>>

    // Player Detail endpoint {playerId}
    // https://www.thesportsdb.com/api/v1/json/1/lookupplayer.php?id=34145937
    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupplayer.php")
    fun getPlayerDetail(@Query("id") playerId: String): Single<Response<PlayerDetailRes>>


    companion object Factory {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        fun create(): TheSportDbApiService {
            val retrofit = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()

            return retrofit.create(TheSportDbApiService::class.java)
        }
    }
}