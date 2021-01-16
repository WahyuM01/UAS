package com.fitrianti.inibolaapp.views.leaguedetail.teamlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fitrianti.inibolaapp.base.BaseViewModel
import com.fitrianti.inibolaapp.data.IRepository
import com.fitrianti.inibolaapp.data.model.*
import io.reactivex.rxkotlin.addTo

class TeamListViewModel(private val repository: IRepository) : BaseViewModel() {
    private var state = MutableLiveData<ResourceState<List<Team>>>(EmptyState())

    fun getTeamList(leagueId: String): LiveData<ResourceState<List<Team>>> {
        state.postValue(LoadingState())
        repository.getTeamList(leagueId)
            .subscribe(
                { teams ->
                    if (teams.isNotEmpty()) state.postValue(PopulatedState(teams))
                    else state.postValue(NoResultState("No team list for $leagueId"))
                },
                { t ->
                    state.postValue(ErrorState(t.localizedMessage))
                }
            )
            .addTo(compDisp)
        return state
    }
}