package com.fitrianti.inibolaapp.views.favorites.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitrianti.inibolaapp.data.IRepository
import com.fitrianti.inibolaapp.data.local.DatabaseHelper
import com.fitrianti.inibolaapp.data.model.Team

class FavoriteTeamViewModel(private val db: DatabaseHelper, val repository: IRepository) : ViewModel() {
    private var favoritedTeam = MutableLiveData<List<Team>>()

    fun loadFavoritedTeam(): LiveData<List<Team>> {
        favoritedTeam.postValue(repository.getFavoriteTeams(db))
        return favoritedTeam
    }
}