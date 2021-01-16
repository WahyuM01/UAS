package com.fitrianti.inibolaapp.views.teamdetail.playerlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fitrianti.inibolaapp.base.BaseViewModel
import com.fitrianti.inibolaapp.data.IRepository
import com.fitrianti.inibolaapp.data.model.*
import io.reactivex.rxkotlin.addTo

class PlayerListViewModel(private val repository: IRepository) : BaseViewModel() {
    private val state = MutableLiveData<ResourceState<List<Player>>>(EmptyState())

    fun getPlayerList(teamId: String): LiveData<ResourceState<List<Player>>> {
        state.postValue(LoadingState())
        repository.getPlayerList(teamId)
            .subscribe(
                { players ->
                    if (players.isNotEmpty()) state.postValue(PopulatedState(players))
                    else state.postValue(NoResultState("No Player Found"))
                },
                { t ->
                    state.postValue(ErrorState(t.localizedMessage))
                }
            )
            .addTo(compDisp)
        return state
    }
}