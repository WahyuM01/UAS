package com.fitrianti.inibolaapp.views.leaguedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fitrianti.inibolaapp.base.BaseViewModel
import com.fitrianti.inibolaapp.data.IRepository
import com.fitrianti.inibolaapp.data.model.*
import com.fitrianti.inibolaapp.data.remote.response.LeagueDetailRes
import io.reactivex.rxkotlin.addTo

class LeagueDetailViewModel(private val repository: IRepository) : BaseViewModel() {

    private var state = MutableLiveData<ResourceState<LeagueDetailRes.League>>(EmptyState())

    fun getLeagueDetail(id: Int): LiveData<ResourceState<LeagueDetailRes.League>> {
        state.postValue(LoadingState())
        repository.getLeagueDetail(id)
            .subscribe(
                {
                    state.postValue(PopulatedState(it))
                },
                {
                    state.postValue(ErrorState(it.localizedMessage))
                }
            )
            .addTo(compDisp)
        return state
    }

}