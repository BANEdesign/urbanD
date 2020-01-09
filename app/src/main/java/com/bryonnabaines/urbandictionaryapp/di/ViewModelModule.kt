package com.bryonnabaines.urbandictionaryapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bryonnabaines.urbandictionaryapp.api.WordViewModel
import com.bryonnabaines.urbandictionaryapp.api.WordViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * created by bryonnabaines on 2020-01-08
 */

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: WordViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WordViewModel::class)
    protected abstract fun wordViewModel(moviesListViewModel: WordViewModel): ViewModel
}