package com.bryonnabaines.urbandictionaryapp.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * created by bryonnabaines on 2020-01-07
 */

class WordViewModel @Inject constructor(api: Api) : ViewModel() {

    val wordLiveData = WordLiveData()

    fun getDefinition(api: Api, term: String) = WordLiveData().loadWordDefinition(api, term)

    inner class WordLiveData : LiveData<WordListResponse>() {
        fun loadWordDefinition(api: Api, term: String) {
            api.define(term)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ wordsList: WordListResponse -> wordLiveData.value = wordsList },
                    { e -> wordLiveData.value = null})
        }
    }
}