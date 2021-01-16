package com.fitrianti.inibolaapp.views.favorites.match

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitrianti.inibolaapp.data.IRepository
import com.fitrianti.inibolaapp.data.local.DatabaseHelper
import com.fitrianti.inibolaapp.data.model.FavoriteEvent

class FavoriteMatchViewModel(private val db: DatabaseHelper, val repository: IRepository) : ViewModel() {
    private var favoritedEventsSchedule = MutableLiveData<List<FavoriteEvent>>()

    fun loadFavoritedEvents(): LiveData<List<FavoriteEvent>> {
        favoritedEventsSchedule.postValue(repository.getFavoriteEvents(db))
        return favoritedEventsSchedule
    }

}